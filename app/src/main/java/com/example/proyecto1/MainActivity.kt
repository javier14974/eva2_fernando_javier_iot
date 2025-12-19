package com.example.proyecto1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.ui.theme.Proyecto1Theme
import com.example.proyecto1.ui.theme.vistas.*
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.ActivityCompat
import android.content.pm.PackageManager



class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth


    private val database: FirebaseDatabase = FirebaseDatabase.getInstance(
        "https://base-de-datos-firedect-default-rtdb.firebaseio.com/"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        auth = Firebase.auth


        noti()
        requisitos_fuego()

        setContent {
            Proyecto1Theme {
                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {

                        NavHost(
                            navController = navController,
                            startDestination = "login"
                        ) {
                            composable("login") { Login(navController, auth, database) }
                            composable("register") { Register(navController, auth) }
                            composable("menu") { Menu(navController, database) }
                            composable("home") { Home(navController, database) }
                        }
                    }
                }
            }
        }
    }


    private fun noti() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "FireDectChannel"
            val descriptionText = "Notificaciones de incendio"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("fire_alerts", name, importance)
            channel.description = descriptionText
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun requisitos_fuego() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
        }
    }
}


