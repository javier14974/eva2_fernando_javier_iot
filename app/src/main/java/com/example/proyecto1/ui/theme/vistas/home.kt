package com.example.proyecto1.ui.theme.vistas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.delay
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.ui.draw.shadow

@Composable
fun Home(navController: NavHostController, database: FirebaseDatabase) {

    val heartbeatRef = database.getReference("/sensores/heartbeat")
    val estadoArduino = remember { mutableStateOf("Cargando...") }

    LaunchedEffect(Unit) {
        while (true) {
            heartbeatRef.setValue("no")
            delay(5000)
            heartbeatRef.get().addOnSuccessListener { snapshot ->
                estadoArduino.value =
                    if (snapshot.getValue(String::class.java) == "si") "FireDect prendido"
                    else "FireDect apagado"
            }.addOnFailureListener { estadoArduino.value = "Error conexión" }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF255670))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("FireDect", fontSize = 38.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

            Spacer(Modifier.height(30.dp))

            Text(
                "Bienvenido a Home",
                fontSize = 28.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(Modifier.height(20.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f).padding(10.dp)
            ) {
                item { ActionCard("Ver estadísticas", Color.White) { navController.navigate("menu") } }
                item { ActionCard("Salir", Color.Red) { navController.navigate("login") } }
                item { ActionCard("Nueva cuenta", Color.Green) { navController.navigate("register") } }
                item { ActionCard(estadoArduino.value, Color(0xFF00A8E8)) {} }
            }

            Text(
                "FireDect se monitorea automáticamente cada 5 segundos.",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .width(300.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(10.dp)
            )

            Spacer(Modifier.height(10.dp))

            FooterHome()
        }
    }
}

@Composable
fun ActionCard(titulo: String, colorFondo: Color, onClick: () -> Unit) {
    val currentTitulo by rememberUpdatedState(titulo)
    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = colorFondo),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.height(150.dp).padding(10.dp).fillMaxWidth().clickable { onClick() }
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(
                text = currentTitulo,
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
        modifier = Modifier.fillMaxWidth().height(70.dp)
            .background(Color(0xFF2E3438), shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
            .shadow(8.dp, RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Fireplace,
                contentDescription = null,
                tint = Color(0xFFFF7043),
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("FireDect • 2025", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
