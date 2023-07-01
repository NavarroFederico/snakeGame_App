package com.mukeshsolanki.snake.domain.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mukeshsolanki.snake.presentation.theme.SnakeTheme

abstract class BaseActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        // Inicializa la instancia de FirebaseAuth
        auth = Firebase.auth
        performAnonymousAuthentication()


        setContent {
            SnakeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { Content() }
            }
        }
    }

    @Composable
    abstract fun Content()

    private fun performAnonymousAuthentication() {
        auth.signInAnonymously().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // El usuario se autenticó exitosamente de forma anónima.
                val user = task.result?.user
                // Puedes guardar el ID del usuario para asociarlo con sus mejores puntos más adelante.
            } else {
                // La autenticación anónima falló. Maneja el error según corresponda.
            }
        }
    }
}