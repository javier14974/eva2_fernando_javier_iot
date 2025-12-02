package com.example.proyecto1

<<<<<<< HEAD
=======
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyecto1.ui.theme.Proyecto1Theme
import com.example.proyecto1.ui.theme.vistas.*
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    // BASE DE DATOS CON URL FIJA
    private val database: FirebaseDatabase = FirebaseDatabase.getInstance(
        "https://base-de-datos-firedect-default-rtdb.firebaseio.com/"
    )
=======
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

>>>>>>> c69e0a2cff359739009f38b63929a33141157c77

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

<<<<<<< HEAD
=======
        //inicio de FireBase
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
        auth = Firebase.auth

        setContent {
            Proyecto1Theme {
                val navController = rememberNavController()

                Scaffold(
<<<<<<< HEAD
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)) {

=======
                    modifier = Modifier
                        .fillMaxSize()
                ) { innnerPadding ->
                    Box(modifier = Modifier.padding(paddingValues = innnerPadding)){
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
                        NavHost(
                            navController = navController,
                            startDestination = "login"
                        ) {
<<<<<<< HEAD
                            composable("login") { Login(navController, auth, database) }
                            composable("register") { Register(navController, auth) }
                            composable("activar") { Activar(navController) }
                            composable("menu") { Menu(navController, database) }
                            composable("home") { Home(navController, database) }
=======
                            composable ( route = "login" ) { Login(navController, auth) }
                            composable ( route = "register") { Register(navController, auth) }
                            composable ( route = "activar") { Activar(navController) }
                            composable ( route = "menu") { Menu(navController, database) }
                            composable ( route = "home") { Home(navController, database) }
>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
                        }
                    }
                }
            }
        }
    }
}

<<<<<<< HEAD
=======

>>>>>>> c69e0a2cff359739009f38b63929a33141157c77
