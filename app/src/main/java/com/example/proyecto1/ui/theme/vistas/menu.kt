package com.example.proyecto1.ui.theme.vistas

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.ui.text.style.TextAlign
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Menu(navController: NavController, database: FirebaseDatabase) {


    var temperatura by remember { mutableStateOf("") }
    var humedad by remember { mutableStateOf("") }
    var gas by remember { mutableStateOf("") }
    var temperaturaMax by remember { mutableStateOf("") }
    var incendio_contador by remember { mutableStateOf("") }


    val buzzerRef = database.getReference("sensores/control_buzzer")
    var estadoBuzzer by remember { mutableStateOf(false) }

    val sensoresRef = database.getReference("sensores")


    LaunchedEffect(Unit) {
        sensoresRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                temperatura = snapshot.child("temperatura").getValue(String::class.java) ?: "0"
                humedad = snapshot.child("humedad").getValue(String::class.java) ?: "0"
                gas = snapshot.child("gas").getValue(String::class.java) ?: "0"
                temperaturaMax = snapshot.child("temperatura_max").getValue(String::class.java) ?: "0"
                incendio_contador = snapshot.child("incendios_contador").getValue(String::class.java) ?: "0"
                val buzzer = snapshot.child("control_buzzer").getValue(Int::class.java) ?: 0
                estadoBuzzer = buzzer == 1
            }

            override fun onCancelled(error: DatabaseError) {}
        })
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF255670))
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

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
                menu_movil(navController = navController)
            }

            Text(
                text = "Estadísticas en tiempo real",
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // --- Grid con scroll ---
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                content = {
                    item { SensorCard("Temperatura", "$temperatura °C", Color(0xFFFFCDD2)) }
                    item { SensorCard("Humedad", "$humedad %", Color(0xFFBBDEFB)) }
                    item { SensorCard("Humo", gas, Color(0xFFC8E6C9)) }
                    item { SensorCard("Temp Máxima", "$temperaturaMax °C", Color(0xFFFFF9C4)) }
                    item { SensorCard("Incendios", incendio_contador, Color(0xFFD1C4E9)) }

                    item(span = { GridItemSpan(2) }) {
                        BuzzerCard(
                            estado = estadoBuzzer,
                            onToggle = { nuevo ->
                                estadoBuzzer = nuevo
                                buzzerRef.setValue(if (nuevo) 1 else 0)
                            }
                        )
                    }
                }
            )


            FooterMenu()
        }
    }
}


@Composable
fun SensorCard(titulo: String, valor: String, colorFondo: Color) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = colorFondo
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .width(160.dp)
            .height(100.dp)
            .padding(10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = titulo, color = Color.DarkGray, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = valor, color = Color.Black, fontSize = 20.sp)
        }
    }
}

@Composable
fun BuzzerCard(
    estado: Boolean,
    onToggle: (Boolean) -> Unit
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFE082)
        ),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(200.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Buzzer", fontSize = 16.sp, color = Color.DarkGray)
                    Text(
                        if (estado) "Activo" else "Inactivo",
                        fontSize = 20.sp,
                        color = if (estado) Color.Green else Color.Red
                    )
                }

                Switch(
                    checked = estado,
                    onCheckedChange = onToggle
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Control de la alarma de incendio",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .width(250.dp)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(vertical = 12.dp)
                )
            }
        }
    }
}




@Composable
fun FooterMenu() {
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

@Composable
fun menu_movil(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Home") },
                onClick = {
                    navController.navigate("home")
                    expanded = false
                }
            )
        }
    }
}


