package edu.martinez1Adrian.appdummy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import edu.martinez1Adrian.appdummy.ui.theme.AppDummyv1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDummyv1Theme {
                // Puedes cambiar aquí el composable para probar en el emulador:
                // PantallaBienvenida()
                PantallaListado()
                // PantallaGestionPermisos()
            }
        }
    }
}
