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
fun CotiCardVerDatosServicios3(data: sDataDetalle) {
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(0.8f)
            ) {
                Text(text = "Cant. de administración: ", color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Cant. personal de limpieza: ", color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Cant. jardineros: ", color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Cant. vigilantes: ", color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Tipo de servicio: ", color = Color.Black)
            }
            Column(
                modifier = Modifier.weight(0.2f)
            ) {
                Text(text = data.cant_administracion.toString(), color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.cant_limpieza.toString(), color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.cant_jardineria.toString(), color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.cant_vigilantes.toString(), color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.tipo_servicio, color = Color.Blue)
            }
        }
    }
}

