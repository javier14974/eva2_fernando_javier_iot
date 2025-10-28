package com.example.proyecto1.ui.theme.vistas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun Login(navController: NavController, auth: FirebaseAuth) {
    var correo by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(color = 0xFF255670)),  //color global
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
                    .padding(all = 25.dp)
            )

            Spacer(modifier = Modifier.height(80.dp))

            Text(
                text = "Iniciar Sesión",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))

            //correo
            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("Correo electrónico") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor =  Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,      // color del texto de adentro
                    unfocusedTextColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            //contraseña1
            OutlinedTextField(
                value = contra,
                onValueChange = { contra = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor =  Color.White,
                    unfocusedContainerColor = Color.White,
                    focusedTextColor = Color.Black,      // color del texto de adentro
                    unfocusedTextColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))



            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        // enviamos las validaciones
                        validarCredencial(correo, contra, auth, context, navController) //enviamos neustro nav para poder navegar
                    },
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                    )
                ) {
                    Text(text = "Ingresar")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        navController.navigate("register")
                    },
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black,
                    )
                ) {
                    Text(text = "Registrarse")
                }
            }

            Spacer(modifier = Modifier.height(230.dp))
            Footer_login()
        }
    }
}


private fun RowScope.validarCredencial(
    correo: String,
    contra: String,
    auth: FirebaseAuth,
    context: Context,
    navController: NavController
) {
    if (correo.isNotEmpty() && contra.isNotEmpty()) {
        auth.signInWithEmailAndPassword(correo, contra)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // login con exito
                    Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
                    navController.navigate("home") // Navegamos a home
                } else {
                    // Login error
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
    } else {
        Toast.makeText(context, "Ingrese el correo y la contraseña", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun Footer_login() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(color = 0xFF3E4449))
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "© 2025 FireDect",
        )
    }
}
