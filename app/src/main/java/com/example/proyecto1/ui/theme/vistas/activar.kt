package com.example.proyecto1.ui.theme.vistas

import androidx.compose.foundation.background
<<<<<<< HEAD
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
=======
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
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
<<<<<<< HEAD
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

=======
import com.google.firebase.database.FirebaseDatabase

@Composable
fun Activar(navController: NavController) {
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
            text = "Botones de FireDect",
            fontSize = 25.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Botones de accion",
            fontSize = 20.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {

            },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.Black,
            )
        ) {
            Text(text = "Apagar alarma")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {

            },
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
                contentColor = Color.Black,
            )
        ) {
            Text(text = "prender alarma")
        }
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Botones de app",
            fontSize = 20.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(40.dp))

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

        Spacer(modifier = Modifier.height(200.dp))
        FooterActivar()
    }
}
}



@Composable
fun FooterActivar() {
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
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
