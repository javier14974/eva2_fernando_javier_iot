package com.example.proyecto1.ui.theme.vistas

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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