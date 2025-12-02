package com.example.proyecto1.ui.theme.vistas

<<<<<<< HEAD
import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.database.FirebaseDatabase

@Composable
fun Home(navController: NavHostController, database: FirebaseDatabase) {

    // USAMOS LA INSTANCIA DE FIREBASE CORRECTA
    val heartbeatRef = database.getReference("/sensores/heartbeat")

    val estadoArduino = remember { mutableStateOf("Cargando...") }

    fun verificarArduino() {
        heartbeatRef.setValue("no")  // Mandamos "no" al ESP32

        Handler(Looper.getMainLooper()).postDelayed({
            heartbeatRef.get().addOnSuccessListener { snapshot ->
                val valor = snapshot.getValue(String::class.java)

                estadoArduino.value = if (valor == "si")
                    "FireDect prendido"
                else
                    "FireDect apagado"

            }.addOnFailureListener {
                estadoArduino.value = "Error conexión"
            }
        }, 3000)  // 3 segundos reales como dice la app
    }
=======
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.proyecto1.ui.theme.vistas.Login

@Composable
fun Home(navController: NavHostController, database: FirebaseDatabase) {
    val context = LocalContext.current


>>>>>>> c69e0a2cff359739009f38b63929a33141157c77

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF255670))
    ) {
<<<<<<< HEAD
        Column(modifier = Modifier.fillMaxSize()) {

            // Encabezado
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "FireDect",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                // Comentado para evitar crash si no está implementado
                // menu_movil(navController = navController)
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Bienvenido a Home",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Tarjetas del menú
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
            ) {
                item {
                    ActionCard(
                        titulo = "Ver estadísticas",
                        colorFondo = Color.White,
                        onClick = { navController.navigate("menu") }
                    )
                }
                item {
                    ActionCard(
                        titulo = "Salir",
                        colorFondo = Color.Red,
                        onClick = { navController.navigate("login") }
                    )
                }
                item {
                    ActionCard(
                        titulo = "Nueva cuenta",
                        colorFondo = Color.Green,
                        onClick = { navController.navigate("register") }
                    )
                }
                item {
                    ActionCard(
                        titulo = estadoArduino.value,
                        colorFondo = Color(0xFF00A8E8),
                        onClick = {
                            estadoArduino.value = "Verificando..."
                            verificarArduino()
                        }
                    )
                }
            }

            // Texto de ayuda
            Text(
                text = "Para ver si FireDect está activo, presiona el botón azul y espera 3 segundos",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(300.dp)
                    .background(Color.White, shape = RoundedCornerShape(12.dp))
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            FooterHome()
        }
    }
}

@Composable
fun ActionCard(titulo: String, colorFondo: Color, onClick: () -> Unit) {
    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = colorFondo),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .height(150.dp)
            .padding(10.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = titulo,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (colorFondo == Color.White) Color.Black else Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}
=======
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "FireDect",
                fontSize = 38.sp,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(25.dp)
            )

            Spacer(modifier = Modifier.height(70.dp))

            Text(
                text = "Home",
                fontSize = 31.sp,
                color = Color.White ,
                fontWeight = FontWeight.Bold,

            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Ver estadisticas de fireDect",
                fontSize = 20.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    navController.navigate("menu")
                },
                modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                )
            ) {
                Text(text = "Estadisticas")
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Controlar fireDect de forma remota",
                fontSize = 20.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(15.dp))


            Button(
                onClick = {
                    navController.navigate("activar")
                },
                modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                )
            ) {
                Text(text = "Control")
            }


            Spacer(modifier = Modifier.height(70.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        navController.navigate("login")
                    },
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.Black,
                    )
                ) {
                    Text(text = "Salir")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        navController.navigate("register")
                    },
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black,
                    )
                ) {
                    Text(text = "Nueva cuenta")
                }
            }





            Spacer(modifier = Modifier.height(145.dp))
            FooterHome()
        }
    }

}



>>>>>>> c69e0a2cff359739009f38b63929a33141157c77

@Composable
fun FooterHome() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
<<<<<<< HEAD
            .height(70.dp)
            .background(
                color = Color(0xFF2E3438),
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
            )
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Fireplace,
                contentDescription = null,
                tint = Color(0xFFFF7043),
                modifier = Modifier.size(22.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "FireDect • 2025",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
=======
            .background(Color(0xFF3E4449))
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "© 2025 FireDect",
            color = Color.White
        )
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
    }
}
