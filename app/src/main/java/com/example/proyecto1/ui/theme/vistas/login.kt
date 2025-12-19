package com.example.proyecto1.ui.theme.vistas

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.filled.Fireplace
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun Login(navController: NavController, auth: FirebaseAuth, database: FirebaseDatabase) {

    // Variables donde se guardan el correo y la contraseña
    var correo by remember { mutableStateOf("") }
    var contra by remember { mutableStateOf("") }

    // Contexto necesario para mostrar mensajes Toast
    val context = LocalContext.current

    // Contenedor principal de la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF255670))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            Spacer(Modifier.height(50.dp))

            // Título de la aplicación
            Text(
                "FireDect",
                fontSize = 38.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(Modifier.height(20.dp))

            // Tarjeta que contiene el formulario de login
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

                    // Título del formulario
                    Text(
                        "Iniciar Sesión",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00796B)
                    )

                    Spacer(Modifier.height(20.dp))

                    // Campo para ingresar el correo
                    val currentCorreo by rememberUpdatedState(correo)
                    OutlinedTextField(
                        value = currentCorreo,
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

                    Spacer(Modifier.height(16.dp))

                    // Campo para ingresar la contraseña
                    val currentContra by rememberUpdatedState(contra)
                    OutlinedTextField(
                        value = currentContra,
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

                    Spacer(Modifier.height(24.dp))

                    // Botón para iniciar sesión
                    Button(
                        onClick = {
                            validarCredencial(
                                currentCorreo,
                                currentContra,
                                auth,
                                database,
                                context,
                                navController
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00796B),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Ingresar", fontSize = 18.sp)
                    }

                    Spacer(Modifier.height(16.dp))

                    // Botón para ir a la pantalla de registro
                    Button(
                        onClick = { navController.navigate("register") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CAF50),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Crear cuenta", fontSize = 18.sp)
                    }

                    Spacer(Modifier.height(10.dp))
                }
            }

            Spacer(Modifier.weight(1f))

            // Footer inferior
            Footer_login()
        }
    }
}

// Función que valida el login usando Firebase Authentication
private fun validarCredencial(
    correo: String,
    contra: String,
    auth: FirebaseAuth,
    database: FirebaseDatabase,
    context: Context,
    navController: NavController
) {

    // Verifica que los campos no estén vacíos
    if (correo.isNotEmpty() && contra.isNotEmpty()) {

        // Intenta iniciar sesión con Firebase
        auth.signInWithEmailAndPassword(correo, contra)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    // Login correcto
                    Toast.makeText(context, "Login exitoso", Toast.LENGTH_SHORT).show()
                    navController.navigate("home")

                } else {

                    // Error en el login
                    Toast.makeText(context, "Error de login", Toast.LENGTH_SHORT).show()
                }
            }

    } else {

        // Campos vacíos
        Toast.makeText(
            context,
            "Ingrese el correo y la contraseña",
            Toast.LENGTH_SHORT
        ).show()
    }
}

@Composable
fun Footer_login() {

    // Footer con el nombre del proyecto
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                Color(0xFF2E3438),
                shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)
            )
            .shadow(8.dp, RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = androidx.compose.material.icons.Icons.Default.Fireplace,
                contentDescription = null,
                tint = Color(0xFFFF7043),
                modifier = Modifier.size(22.dp)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                "FireDect • 2025",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
