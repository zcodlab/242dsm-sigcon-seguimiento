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
import androidx.compose.ui.unit.dp
import com.example.firstaplication.data.model.sDataDetalle

@Composable
fun CotiCardVerDatosPredio2(data: sDataDetalle) {
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
                Text(text = "Dirección: ", color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Tipo de predio: ", color = Color.Black)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "RUC: ", color = Color.Black)
            }
            Column(
                modifier = Modifier.weight(0.6f)
            ) {
                Text(text = data.nombre_predio, color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.direccion_predio, color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.tipo_predio, color = Color.Blue, modifier = Modifier.align(Alignment.End))
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = data.ruc_predio, color = Color.Blue, modifier = Modifier.align(Alignment.End))
            }
        }
    }
}

