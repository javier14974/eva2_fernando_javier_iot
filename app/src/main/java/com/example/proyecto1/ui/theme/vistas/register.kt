package com.example.proyecto1.ui.theme.vistas

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Register(navController: NavController, auth: FirebaseAuth) {
    var nombre_nuevo by remember { mutableStateOf("") }
    var correo_nuevo by remember { mutableStateOf("") }
    var contra_nuevo by remember { mutableStateOf("") }
    var contra_confirmar_nuevo by remember { mutableStateOf("") }
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize().background(Color(0xFF255670))) {
        Column(modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)) {
            Spacer(Modifier.height(50.dp))
            Text("FireDect", fontSize = 38.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Spacer(Modifier.height(20.dp))

            Card(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE0F7FA)),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Registrar Usuario", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF00796B))
                    Spacer(Modifier.height(20.dp))

                    val currentNombre by rememberUpdatedState(nombre_nuevo)
                    OutlinedTextField(
                        value = currentNombre,
                        onValueChange = { nombre_nuevo = it },
                        label = { Text("Nombre") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedTextColor = Color.Black,
                            unfocusedTextColor = Color.Black
                        )
                    )

                    val currentCorreo by rememberUpdatedState(correo_nuevo)
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = currentCorreo,
                        onValueChange = { correo_nuevo = it },
                        label = { Text("Correo") },
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

                    val currentContra by rememberUpdatedState(contra_nuevo)
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = currentContra,
                        onValueChange = { contra_nuevo = it },
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

                    val currentConfirm by rememberUpdatedState(contra_confirmar_nuevo)
                    Spacer(Modifier.height(16.dp))
                    OutlinedTextField(
                        value = currentConfirm,
                        onValueChange = { contra_confirmar_nuevo = it },
                        label = { Text("Confirmar contraseña") },
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
                    Button(
                        onClick = { validarRegistro(currentNombre, currentCorreo, currentContra, currentConfirm, auth, context, navController) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00796B), contentColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("Enviar datos", fontSize = 18.sp) }

                    Spacer(Modifier.height(16.dp))
                    Button(
                        onClick = { navController.navigate("login") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50), contentColor = Color.White),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("Volver a Login", fontSize = 18.sp) }

                    Spacer(Modifier.height(10.dp))
                }
            }

            Spacer(Modifier.weight(1f))
            Footer_register()
        }
    }
}

private fun validarRegistro(
    nombreNuevo: String,
    correoNuevo: String,
    contraNuevo: String,
    contraConfirmarNuevo: String,
    auth: FirebaseAuth,
    context: Context,
    navController: NavController
) {
    when {
        correoNuevo.isBlank() || nombreNuevo.isBlank() || contraNuevo.isBlank() || contraConfirmarNuevo.isBlank() ->
            Toast.makeText(context, "Por favor rellenar todos los campos", Toast.LENGTH_SHORT).show()
        contraNuevo != contraConfirmarNuevo ->
            Toast.makeText(context, "Deben ser iguales las 2 contraseñas", Toast.LENGTH_SHORT).show()
        !Patterns.EMAIL_ADDRESS.matcher(correoNuevo).matches() ->
            Toast.makeText(context, "Correo inválido", Toast.LENGTH_SHORT).show()
        contraNuevo.length !in 6..10 ->
            Toast.makeText(context, "La contraseña debe tener entre 6 y 10 caracteres", Toast.LENGTH_SHORT).show()
        else ->
            auth.createUserWithEmailAndPassword(correoNuevo, contraNuevo)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                        navController.navigate("login")
                    } else {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
    }
}

@Composable
fun Footer_register() {
    Box(
        modifier = Modifier.fillMaxWidth().height(70.dp)
            .background(Color(0xFF2E3438), shape = RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
            .shadow(8.dp, RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Icon(
                imageVector = Icons.Default.Fireplace,
                contentDescription = null,
                tint = Color(0xFFFF7043),
                modifier = Modifier.size(22.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text("FireDect • 2025", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
        }
    }
}
