package com.example.proyecto1.ui.theme.vistas

import android.content.Context
import android.util.Patterns
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


@Composable
fun Register(navController: NavController, auth: FirebaseAuth) {
    var nombre_nuevo by remember { mutableStateOf("") }
    var correo_nuevo by remember { mutableStateOf("") }
    var contra_nuevo by remember { mutableStateOf("") }
    var contra_confirmar_nuevo by remember { mutableStateOf("") }
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
                text = "Registrar usuario",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))


            //nombre
            OutlinedTextField(
                value = nombre_nuevo,
                onValueChange = { nombre_nuevo = it },
                label = { Text("Nombre") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),  //ancho
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor =  Color.White,     //fondo blanco siempre q este activo
                    unfocusedContainerColor = Color.White,      //fondo blanco al no estar activo
                    focusedTextColor = Color.Black,      // color del texto de adentro
                    unfocusedTextColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            //correo
            OutlinedTextField(
                value = correo_nuevo,
                onValueChange = { correo_nuevo = it },
                label = { Text("Correo") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),   //ancho
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor =  Color.White,     //fondo blanco siempre q este activo
                    unfocusedContainerColor = Color.White,      //fondo blanco al no estar activo
                    focusedTextColor = Color.Black,      // color del texto de adentro
                    unfocusedTextColor = Color.Black,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            //contra1
            OutlinedTextField(
                value = contra_nuevo,
                onValueChange = { contra_nuevo = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor =  Color.White,     //fondo blanco siempre q este activo
                    unfocusedContainerColor = Color.White,      //fondo blanco al no estar activo
                    focusedTextColor = Color.Black,      // color del texto de adentro
                    unfocusedTextColor = Color.Black,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            //contra2
            OutlinedTextField(
                value = contra_confirmar_nuevo,
                onValueChange = { contra_confirmar_nuevo = it },
                label = { Text("Confirmar contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor =  Color.White,     //fondo blanco siempre q este activo
                    unfocusedContainerColor = Color.White,      //fondo blanco al no estar activo
                    focusedTextColor = Color.Black,      // color del texto de adentro
                    unfocusedTextColor = Color.Black,
                )
            )


            Spacer(modifier = Modifier.height(24.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),

                horizontalArrangement = Arrangement.SpaceEvenly, //cada uno tiene su espacio
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        validarRegistro(nombre_nuevo, correo_nuevo, contra_nuevo,contra_confirmar_nuevo, auth, context, navController)
                    },
                    modifier = Modifier
                        .width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,          // el fondo del boton en blanco
                        contentColor = Color.Black,            //letras en negritas
                    )
                ) {
                    Text(text = "Enviar datos")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        navController.navigate("login")
                    },
                    modifier = Modifier
                        .width(150.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,          // el fondo del boton en blanco
                        contentColor = Color.Black,            //letras en negritas
                    )
                ) {
                    Text(text = "Volver a Login")
                }
            }



            Spacer(modifier = Modifier.height(65.dp))
            Footer_register()
        }
    }

}

private fun RowScope.validarRegistro( //logica del autenticador
    nombreNuevo: String,
    correoNuevo: String,
    contraNuevo: String,
    contraConfirmarNuevo: String,
    auth: FirebaseAuth,
    context: Context,
    navController: NavController,
) {
    if (correoNuevo.isBlank() || nombreNuevo.isBlank() || contraNuevo.isBlank() || contraConfirmarNuevo.isBlank()) {
        Toast.makeText(context, "Por favor rellenar todos los campos", Toast.LENGTH_SHORT).show()

    } else if (contraNuevo != contraConfirmarNuevo) {
        Toast.makeText(context, "Deben ser iguales las 2 contraseñas", Toast.LENGTH_SHORT).show()

    } else if (!Patterns.EMAIL_ADDRESS.matcher(correoNuevo).matches()) {
        Toast.makeText(context, "Correo inválido", Toast.LENGTH_SHORT).show()

    } else if (contraNuevo.length < 6 || contraNuevo.length > 10) {
        Toast.makeText(context, "La contraseña debe tener entre 6 y 10 caracteres", Toast.LENGTH_SHORT).show()

    } else {
        //si todo sale bien se registrara usuario
        auth.createUserWithEmailAndPassword(correoNuevo, contraNuevo)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    navController.navigate("login") // Navegamos a nuestrom login

                } else {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

@Composable
fun Footer_register() {
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