package com.android.example.androidstudio2dgame

import Game
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.android.example.androidstudio2dgame.ui.theme.AndroidStudio2DGameTheme

class MainActivity : ComponentActivity() {

    private lateinit var game: Game

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity.kt", "onCreate()")
        super.onCreate(savedInstanceState)

//        // Configurar la ventana en modo pantalla completa (ocultar barra de estado)
//        val window: Window = window
//        window.setFlags(
//            WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN
//        )
        game = Game(this)
        setContentView(game)

    }

    override fun onStart() {
        Log.d("MainActivity.kt", "onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity.kt", "onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity.kt", "onPause()")
        game.pause()
        super.onPause()
    }


    override fun onStop() {
        Log.d("MainActivity.kt", "onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity.kt", "onDestroy()")
        super.onDestroy()
    }


}