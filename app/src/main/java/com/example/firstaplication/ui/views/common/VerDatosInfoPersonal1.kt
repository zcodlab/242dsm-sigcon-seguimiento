package com.example.firstaplication.ui.views.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.firstaplication.data.model.sDataDetalle

@Composable
fun CotiCardVerDatosPersonal1(data: sDataDetalle) {
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
                Text(text = "Nombre: ", color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Num. documento: ", color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Rol: ", color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Correo: ", color = Color.Black)

            }
            Column(
                modifier = Modifier.weight(0.6f)
            ) {
                Text(text = data.nombre, color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.nro_documento, color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.rol, color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.correo, color = Color.Blue, modifier = Modifier.align(Alignment.End))
            }
        }
    }
}