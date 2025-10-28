package com.example.proyecto1.ui.theme.vistas

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


@Composable
fun Menu(navController: NavController, database: FirebaseDatabase) {
    val context = LocalContext.current

    var temperatura by remember { mutableStateOf("") }
    var humedad by remember { mutableStateOf("") }
    var gas by remember { mutableStateOf("") }

    var temperaturaMax by remember { mutableStateOf("") }
    var incendio_contador by remember { mutableStateOf("") }

    val sensoresRef = database.getReference("sensores") //referenciar mi base de datos de firebase

    LaunchedEffect(Unit) {
        sensoresRef.addValueEventListener(object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                temperatura = snapshot.child("temperatura").getValue(String::class.java) ?: "0"
                humedad = snapshot.child("humedad").getValue(String::class.java) ?: "0"
                gas = snapshot.child("gas").getValue(String::class.java) ?: "0"
                temperaturaMax = snapshot.child("temperatura_max").getValue(String::class.java) ?: "0"
                incendio_contador = snapshot.child("incendios_contador").getValue(String::class.java) ?: "0"
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("Firebase", "Error al momento de leer los archivos", error.toException())
            }
        })
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF255670))
    ) {
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
                text = "Estadísticas en tiempo real",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = "Temperatura actual: $temperatura °C  | $humedad",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp)
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "nivel de humo actual actual: $gas",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp)
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            )
            Text(
                text = "Temperatura normal es 15-30 °C (depende de ciudad/pais)" ,
                fontSize = 13.sp,
                color = Color.Cyan
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Estadísticas criticas",
                fontSize = 20.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "maximo de temperatura: $temperaturaMax",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp)
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            )
            Spacer(modifier = Modifier.height(7.dp))
            Text(
                text = "Excesos térmicos detectados: $incendio_contador",
                color = Color.Black,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp)
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 16.dp, vertical = 14.dp)
            )
            Text(
                text = "Temperatura muy alta +50 °C (depende de ciudad/pais)" ,
                fontSize = 13.sp,
                color = Color.Cyan
            )
            Spacer(modifier = Modifier.height(25.dp))


            Button(
                onClick = {
                    navController.navigate("home")
                },
                modifier = Modifier.width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black,
                )
            ) {
                Text(text = "Volver a home")
            }

            Spacer(modifier = Modifier.height(110.dp))
            FooterMenu()
        }
    }

}




@Composable
fun FooterMenu() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3E4449))
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "© 2025 FireDect",
            color = Color.White
        )
    }
}


