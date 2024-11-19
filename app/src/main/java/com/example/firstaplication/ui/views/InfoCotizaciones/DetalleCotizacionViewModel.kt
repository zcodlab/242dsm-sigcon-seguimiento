package com.example.firstaplication.ui.views.InfoCotizaciones

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.firstaplication.data.dao.solicitud_cotizacionDAO
import com.example.firstaplication.data.entity.SolicitudCotizacionEntity
import com.example.firstaplication.data.entity.SolicitudEntity
import com.example.firstaplication.data.model.sDataDetalle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

@HiltViewModel
class DetalleCotizacionViewModel @Inject constructor(private val scDAO: solicitud_cotizacionDAO): ViewModel(){
    var isLoading by mutableStateOf(true)
    var dataDetalle = sDataDetalle()

    suspend fun generateSolicitudesAprobadas(id: String): ArrayList<SolicitudCotizacionEntity> {
        return withContext(Dispatchers.IO) {
            scDAO.readAprobadaDetalle(id)
        }
    }
    fun obtenerDataAprobada(entity: SolicitudCotizacionEntity): sDataDetalle {
        val sData = sDataDetalle()
        sData.nombre = entity.solicitud.solicitante.persona.apellido_paterno + " " + entity.solicitud.solicitante.persona.apellido_materno + " " + entity.solicitud.solicitante.persona.nombres
        sData.nro_documento = entity.solicitud.solicitante.persona.ndocumento
        sData.rol = entity.solicitud.solicitante.rol.descripcion
        sData.correo = entity.solicitud.solicitante.correo
        sData.nombre_predio = entity.solicitud.predio.descripcion
        sData.direccion_predio = entity.solicitud.predio.direccion
        sData.tipo_predio = entity.solicitud.predio.tipo_predio.nombre_predio
        sData.ruc_predio = entity.solicitud.predio.ruc
        sData.cant_vigilantes = entity.solicitud.cant_vigilantes
        sData.precio_vigilante = entity.solicitud.servicio.precio.toDouble()
        sData.cant_limpieza = entity.solicitud.cant_plimpieza
        sData.precio_limpieza = entity.solicitud.servicio.precio.toDouble()
        sData.cant_administracion = entity.solicitud.cant_administracion
        sData.precio_administracion = entity.solicitud.servicio.precio.toDouble()
        sData.cant_jardineria = entity.solicitud.cant_jardineria
        sData.precio_jardineria = entity.solicitud.servicio.precio.toDouble()
        sData.tipo_servicio = entity.solicitud.servicio.descripcion
        return sData
    }

    suspend fun guardarDataCotizada(id: String) {
        val solicitudesAprobadas = generateSolicitudesAprobadas(id)
        var result = withContext(Dispatchers.IO) {
            transaction {
                var Data = sDataDetalle()
                Data = obtenerDataAprobada(solicitudesAprobadas.get(0))
                Data
            }
        }
        dataDetalle = result
    }
    suspend fun cargarDataCotizada(id: String){
        val jobAprobada = CoroutineScope(Dispatchers.IO).launch { guardarDataCotizada(id) }

        jobAprobada.join()
        isLoading = false
    }
}