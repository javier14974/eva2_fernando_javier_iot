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

    val dbRef = FirebaseDatabase.getInstance().reference
        .child("sensores")
        .child("control_buzzer")

    // Estado interno que refleja el valor del buzzer (true = 1, false = 0)
    var estadoBuzzer by remember { mutableStateOf(false) }

    // Escuchar cambios en tiempo real
    LaunchedEffect(Unit) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val valor = snapshot.getValue(Int::class.java) ?: 0
                estadoBuzzer = valor == 1        // convierte 1 → true
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Control de Buzzer",
                color = Color.White,
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            // ------------------ SWITCH ------------------
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (estadoBuzzer) "Activo" else "Inactivo",
                    color = Color.White,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(end = 12.dp)
                )

                Switch(
                    checked = estadoBuzzer,
                    onCheckedChange = { nuevoEstado ->
                        estadoBuzzer = nuevoEstado
                        dbRef.setValue(if (nuevoEstado) 1 else 0)
                    }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            // ----------- ESTADO EN VIVO -----------
            Text(
                text = if (estadoBuzzer) "BUZZER PRENDIDO" else "BUZZER APAGADO",
                color = if (estadoBuzzer) Color.Green else Color.Red,
                fontSize = 28.sp
            )
        }
    }
}

