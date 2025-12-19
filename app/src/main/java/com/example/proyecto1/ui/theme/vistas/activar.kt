package com.example.proyecto1.ui.theme.vistas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.database.*

@Composable
fun Activar(navController: NavController) {

    // Referencia a Firebase, específicamente al valor que controla el buzzer
    val dbRef = FirebaseDatabase.getInstance().reference
        .child("sensores")
        .child("control_buzzer")

    // Variable que guarda si el buzzer está activo o no dentro de la app
    var estadoBuzzer by remember { mutableStateOf(false) }

    // Se queda escuchando cambios en Firebase en tiempo real
    LaunchedEffect(Unit) {
        dbRef.addValueEventListener(object : ValueEventListener {

            // Cada vez que cambia el valor en Firebase
            override fun onDataChange(snapshot: DataSnapshot) {
                val valor = snapshot.getValue(Int::class.java) ?: 0
                estadoBuzzer = valor == 1   // si es 1 → activo, si es 0 → apagado
            }

            // Si ocurre un error (no se maneja nada acá)
            override fun onCancelled(error: DatabaseError) {}
        })
    }

    // Contenedor principal de la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF255670))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Título de la pantalla
            Text(
                text = "Control de Buzzer",
                color = Color.White,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // -------- SWITCH PARA ACTIVAR O DESACTIVAR --------
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Texto que indica el estado actual
                Text(
                    text = if (estadoBuzzer) "Activo" else "Inactivo",
                    color = Color.White,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(end = 12.dp)
                )

                // Switch que cambia el valor del buzzer
                Switch(
                    checked = estadoBuzzer,
                    onCheckedChange = { nuevoEstado ->
                        estadoBuzzer = nuevoEstado
                        // Se envía el nuevo valor a Firebase
                        dbRef.setValue(if (nuevoEstado) 1 else 0)
                    }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // -------- ESTADO EN TIEMPO REAL --------
            Text(
                text = if (estadoBuzzer) "BUZZER PRENDIDO" else "BUZZER APAGADO",
                color = if (estadoBuzzer) Color.Green else Color.Red,
                fontSize = 28.sp
            )
        }
    }
}
