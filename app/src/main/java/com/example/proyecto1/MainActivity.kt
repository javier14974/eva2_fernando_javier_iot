package com.example.proyecto1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.ui.theme.vistas.Login
import com.example.proyecto1.ui.theme.vistas.Register
import com.example.proyecto1.ui.theme.vistas.Activar
import com.example.proyecto1.ui.theme.Proyecto1Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.proyecto1.ui.theme.vistas.Menu
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.database
import com.example.proyecto1.ui.theme.vistas.Home
class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth;

    val database = Firebase.database


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //inicio de FireBase
        auth = Firebase.auth

        setContent {
            Proyecto1Theme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                ) { innnerPadding ->
                    Box(modifier = Modifier.padding(paddingValues = innnerPadding)){
                        NavHost(
                            navController = navController,
                            startDestination = "login"
                        ) {
                            composable ( route = "login" ) { Login(navController, auth) }
                            composable ( route = "register") { Register(navController, auth) }
                            composable ( route = "activar") { Activar(navController) }
                            composable ( route = "menu") { Menu(navController, database) }
                            composable ( route = "home") { Home(navController, database) }
                        }
                    }
                }
            }
        }
    }
}


