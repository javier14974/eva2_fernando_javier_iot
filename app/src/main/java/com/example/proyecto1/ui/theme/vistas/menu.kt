package com.example.proyecto1.ui.theme.vistas

import android.R
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

    val context = LocalContext.current
    val sensoresRef = database.getReference("sensores")
    val buzzerRef = database.getReference("sensores/control_buzzer")

    var temperatura by remember { mutableStateOf("0") }
    var humedad by remember { mutableStateOf("0") }
    var gas by remember { mutableStateOf("0") }
    var temperaturaMax by remember { mutableStateOf("0") }
    var incendio by remember { mutableStateOf("0") }
    var estadoBuzzer by remember { mutableStateOf(false) }
    var notificado by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        sensoresRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                temperatura = snapshot.child("temperatura").getValue(Float::class.java)?.toString() ?: "0"
                humedad = snapshot.child("humedad").getValue(Float::class.java)?.toString() ?: "0"
                gas = snapshot.child("gas").getValue(Int::class.java)?.toString() ?: "0"
                temperaturaMax = snapshot.child("temperatura_max").getValue(Float::class.java)?.toString() ?: "0"
                incendio = snapshot.child("incendio").getValue(Int::class.java)?.toString() ?: "0"
                estadoBuzzer = (snapshot.child("control_buzzer").getValue(Int::class.java) ?: 0) == 1
            }
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    LaunchedEffect(incendio) {
        if (incendio == "1" && !notificado) {
            val builder = NotificationCompat.Builder(context, "fire_alerts")
                .setSmallIcon(R.drawable.ic_dialog_alert)
                .setContentTitle("Incendio detectado!")
                .setContentText("Se ha detectado un incendio")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
            NotificationManagerCompat.from(context).notify(1001, builder.build())
            notificado = true
        } else if (incendio == "0") notificado = false
    }

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF255670))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Row(
                modifier = Modifier.fillMaxWidth().padding(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("FireDect", fontSize = 38.sp, fontWeight = FontWeight.Bold, color = Color.White)
                menu_movil(navController)
            }

            Text(
                "Estadísticas en tiempo real",
                fontSize = 22.sp,
                color = Color.White,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(Modifier.height(10.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.weight(1f).background(Color(0xFF255670)),
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { SensorCard("Temperatura", "$temperatura °C", Color(0xFFFFCDD2)) }
                item { SensorCard("Humedad", "$humedad %", Color(0xFFBBDEFB)) }
                item { SensorCard("Humo", gas, Color(0xFFC8E6C9)) }
                item { SensorCard("Temp Máxima", "$temperaturaMax °C", Color(0xFFFFF9C4)) }
                item {
                    SensorCard(
                        "Incendio",
                        if (incendio == "1") "Incendio ACTIVO" else "Sin riesgo",
                        if (incendio == "1") Color(0xFFFF8A80) else Color(0xFFD1C4E9)
                    )
                }
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

            FooterMenu()
        }
    }
}




@Composable
fun SensorCard(titulo: String, valor: String, colorFondo: Color) {
    val currentValor by rememberUpdatedState(valor)
    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = colorFondo),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.width(160.dp).height(100.dp).padding(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = titulo, color = Color.DarkGray, fontSize = 14.sp)
            Spacer(Modifier.height(5.dp))
            Text(text = currentValor, color = Color.Black, fontSize = 20.sp)
        }
    }
}

@Composable
fun BuzzerCard(
    estado: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val currentEstado by rememberUpdatedState(estado)
    OutlinedCard(
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE082)),
        border = BorderStroke(1.dp, Color.Black),
        modifier = Modifier.fillMaxWidth().padding(10.dp).height(200.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(14.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("Buzzer", fontSize = 16.sp, color = Color.DarkGray)
                    Text(
                        if (currentEstado) "Activo" else "Inactivo",
                        fontSize = 20.sp,
                        color = if (currentEstado) Color.Green else Color.Red
                    )
                }

                Switch(
                    checked = currentEstado,
                    onCheckedChange = onToggle
                )
            }

            Spacer(Modifier.height(10.dp))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "⚠ Alarma de incendio(ADVERTENCIA DESACTIVANDO LA ALARMA NO SONARA EN RIESGO DE INCENDIO)",
                    color = Color.Black,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.width(250.dp)
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
        modifier = Modifier.fillMaxWidth().height(70.dp)
            .background(Color(0xFF2E3438), shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
            .shadow(8.dp, RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(Icons.Default.Fireplace, contentDescription = null, tint = Color(0xFFFF7043), modifier = Modifier.size(22.dp))
            Spacer(Modifier.width(8.dp))
            Text("FireDect • 2025", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
fun menu_movil(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.padding(16.dp)) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
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



