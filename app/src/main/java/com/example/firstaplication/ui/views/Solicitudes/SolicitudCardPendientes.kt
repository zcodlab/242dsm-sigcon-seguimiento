package com.example.firstaplication.ui.views.Solicitudes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.firstaplication.data.model.spData

@Composable
fun CotizacionCardPendiente(navController: NavController,data: spData, viewModel: SolicitudViewModel) {
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
                modifier = Modifier.weight(0.6f)
            ) {
                Text(text = data.id_solicitud, color = Color.Red)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.name, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.namep, color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.fechaSolicitud, color = Color.Red)
            }
            Column(
                modifier = Modifier
                    .weight(0.4f)
                    .fillMaxHeight(),
            ) {
                Button(
                    onClick = {
                        navController.navigate("VisualizacionCotizarPendiente/${data.id_solicitud}")
                        viewModel.isLoading = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text = "Cotizar")
                }
            }
        }
   }
}



