package com.example.firstaplication.ui.views.Solicitudes

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.firstaplication.MainActivity
import com.example.firstaplication.R
import com.example.firstaplication.data.model.sData
import com.example.firstaplication.ui.views.InfoCotizaciones.DetalleCotizacionViewModel
import com.example.firstaplication.ui.views.infoSolicitudes.generatePDF as generatePDF

private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
val mainActivity = MainActivity.obtenerInstancia()
private fun foregroundPermissionApproved(context: Context): Boolean {
    val writePermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )
    val readPermissionFlag = PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
        context, android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    return writePermissionFlag && readPermissionFlag
}

private fun requestForegroundPermission(context: Context) {
    val provideRationale = foregroundPermissionApproved(context = context)
    if (provideRationale) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    } else {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE, android.Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    }
}

@Composable
fun CotizacionCardAprobada(navController: NavController, data: sData, context: Context, viewModelCotizado: DetalleCotizacionViewModel) {
    requestForegroundPermission(context)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier.weight(0.7f)
            ) {
                Text(text = data.id_solicitud, color = Color.Red)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.name, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.namep, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.fechaAprobacion, color = Color.Red)
            }
            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight(),
            ) {
                Button(
                    onClick = { },
                    modifier = Modifier
                        .widthIn(max = 100.dp)
                ) {
                    IconButton(
                        onClick = { navController.navigate("VisualizacionCotizarAprobada/${data.id}") }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "Ver Cotización",
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { /* Abre la opción de cotización */ },
                    modifier = Modifier
                        .widthIn(max = 100.dp)
                ) {
                    IconButton(
                        onClick = {
                            if (mainActivity != null) {
                                generatePDF(context, mainActivity.getDirectory(), data.id, viewModelCotizado)
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.descargar),
                            contentDescription = "Descargar Cotización",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

