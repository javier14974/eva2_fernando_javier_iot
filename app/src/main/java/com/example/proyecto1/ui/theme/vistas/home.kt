package com.example.proyecto1.ui.theme.vistas

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




@Composable
fun FooterHome() {
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
