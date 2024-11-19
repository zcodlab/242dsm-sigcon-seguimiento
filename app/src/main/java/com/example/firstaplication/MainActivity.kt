package com.example.firstaplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.firstaplication.data.table.db
import com.example.firstaplication.ui.views.Solicitudes.SolicitudViewModel
import com.example.firstaplication.ui.views.Solicitudes.SolicitudesScreen
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firstaplication.ui.views.infoSolicitudes.DetalleSolicitudViewModel
import com.example.firstaplication.ui.views.Solicitudes.VisualizacionSolicitudCotizadaScreen
import com.example.firstaplication.ui.theme.common.InfoCotizaciones.VisualizacionCotiRegistradaScreen
import com.example.firstaplication.ui.theme.common.InfoCotizaciones.VisualizacionCotisScreen
import com.example.firstaplication.ui.views.InfoCotizaciones.DetalleCotizacionViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.apache.log4j.BasicConfigurator
import org.apache.log4j.varia.NullAppender
import java.io.File

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() { //clase main
    private val solicitudViewModel: SolicitudViewModel by viewModels()
    private val detalleSolicitudViewModel: DetalleSolicitudViewModel by viewModels()
    private val detalleCotizacionViewModel: DetalleCotizacionViewModel by viewModels()

    companion object {
        private var instancia: MainActivity? = null
        fun obtenerInstancia(): MainActivity? {
            return instancia
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instancia = this
        Log.d(TAG, "onCreate Called")
        BasicConfigurator.configure(NullAppender()) // para el log4jzzz
        db.init() //inicializo la db
        setContent {
            val navController = rememberNavController() // Crear el controlador de navegación
            val context = LocalContext.current
            // Configurar la navegación con Navigation Component
            NavHost(
                navController = navController,
                startDestination = "pantalla1"
            ) {
                composable("pantalla1") {
                    // Pantalla 1
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        SolicitudesScreen(solicitudViewModel,navController,context,detalleCotizacionViewModel)
                    }
                }
                composable("VisualizacionCotizarPendiente/{idSolicitud}") { backStackEntry ->
                    val idSolicitud = backStackEntry.arguments?.getString("idSolicitud")
                    // Ahora puedes utilizar idSolicitud en tu pantalla de VisualizacionCotizarPendiente.
                    VisualizacionCotiRegistradaScreen(detalleSolicitudViewModel, navController, idSolicitud)
                }
                composable("VisualizacionCotizarAprobada/{idSolicitud}") { backStackEntry ->
                    val idSolicitud = backStackEntry.arguments?.getString("idSolicitud")
                    VisualizacionCotisScreen(detalleCotizacionViewModel,navController,idSolicitud)
                }
                composable("VisualizaracionSolicitudCotizada/{idSolicitud}"){ backStackEntry ->
                    val idSolicitud = backStackEntry.arguments?.getString("idSolicitud")
                    VisualizacionSolicitudCotizadaScreen(context,detalleSolicitudViewModel,navController,idSolicitud)
                }

            }
        }
    }

    fun getDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }
}
