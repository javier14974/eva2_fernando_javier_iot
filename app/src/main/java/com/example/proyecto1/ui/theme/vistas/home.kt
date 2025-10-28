package com.example.proyecto1.ui.theme.vistas

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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF255670))
    ) {
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

@Composable
fun FooterHome() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
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
    }
}
