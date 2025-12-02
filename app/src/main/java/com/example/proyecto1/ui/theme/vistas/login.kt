package com.example.proyecto1.ui.theme.vistas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
<<<<<<< HEAD
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
=======
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
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
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
<<<<<<< HEAD
fun Login(navController: NavController, auth: FirebaseAuth, database: FirebaseDatabase) {
=======
fun Login(navController: NavController, auth: FirebaseAuth) {
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
    var correo by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
<<<<<<< HEAD
            .background(Color(0xFF255670))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))
=======
            .background(Color(color = 0xFF255670)),  //color global
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77

            Text(
                text = "FireDect",
                fontSize = 38.sp,
<<<<<<< HEAD
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Iniciar Sesión",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00796B)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo electrónico") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = contra,
                        onValueChange = { contra = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            validarCredencial(correo, contra, auth, database, context, navController)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00796B),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Ingresar", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { navController.navigate("register") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(text = "Crear cuenta", fontSize = 18.sp)
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                }
            }

            Spacer(modifier = Modifier.weight(1f))

=======
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
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
            Footer_login()
        }
    }
}


<<<<<<< HEAD
// VALIDACIÓN DE CREDENCIALES
private fun validarCredencial(
    correo: String,
    contra: String,
    auth: FirebaseAuth,
    database: FirebaseDatabase,
=======
private fun RowScope.validarCredencial(
    correo: String,
    contra: String,
    auth: FirebaseAuth,
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
    context: Context,
    navController: NavController
) {
    if (correo.isNotEmpty() && contra.isNotEmpty()) {
        auth.signInWithEmailAndPassword(correo, contra)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
<<<<<<< HEAD
                    Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
                    // Navegamos a Home y pasamos database
                    navController.navigate("home")
                } else {
                    Toast.makeText(context, "Error de login", Toast.LENGTH_SHORT).show()
=======
                    // login con exito
                    Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
                    navController.navigate("home") // Navegamos a home
                } else {
                    // Login error
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
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
<<<<<<< HEAD
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
=======
            .background(Color(color = 0xFF3E4449))
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "© 2025 FireDect",
        )
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
    }
}
