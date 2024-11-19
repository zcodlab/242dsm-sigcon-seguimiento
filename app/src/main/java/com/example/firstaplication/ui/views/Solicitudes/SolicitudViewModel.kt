package com.example.firstaplication.ui.views.Solicitudes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.firstaplication.data.dao.solicitud_cotizacionDAO
import androidx.lifecycle.ViewModel
import com.example.firstaplication.data.dao.solicitudDAO
import com.example.firstaplication.data.entity.SolicitudCotizacionEntity
import com.example.firstaplication.data.entity.SolicitudEntity
import com.example.firstaplication.data.model.sData
import com.example.firstaplication.data.model.spData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.transactions.transaction
import javax.inject.Inject

@HiltViewModel
class SolicitudViewModel @Inject constructor(private val scDAO: solicitud_cotizacionDAO,
                                             private val sDAO: solicitudDAO): ViewModel(){
    var isLoading by mutableStateOf(true)
    val dataPendiente = ArrayList<spData>()
    val dataAprobada = ArrayList<sData>()
    suspend fun generateSolicitudesAprobadas(): ArrayList<SolicitudCotizacionEntity> {
        return withContext(Dispatchers.IO) {
            scDAO.readAprobadas() //de solicitud_cotizacionDAO
        }
    }
    suspend fun generateSolicitudesPendientes(): ArrayList<SolicitudEntity> {
        return withContext(Dispatchers.IO) {
            sDAO.readPendientes()
        }
    }
    fun obtenerDataAprobada(entity: SolicitudCotizacionEntity): sData {
        val sData = sData()
        sData.id = entity.id.toString().padStart(6, '0')
        sData.name = entity.solicitud.solicitante.persona.apellido_paterno + " " + entity.solicitud.solicitante.persona.apellido_materno + " " + entity.solicitud.solicitante.persona.nombres
        sData.namep = entity.solicitud.predio.descripcion
        sData.fechaAprobacion = entity.solicitud.fecha_solicitud.toString()
        sData.id_solicitud = entity.solicitud.id.toString().padStart(6, '0')
        return sData
    }
    fun obtenerDataPendiente(entity: SolicitudEntity): spData {
        val spData = spData()
        spData.id_solicitud = entity.id.toString().padStart(6, '0')
        spData.name = entity.solicitante.persona.apellido_paterno + " " + entity.solicitante.persona.apellido_materno + " " + entity.solicitante.persona.nombres
        spData.namep = entity.predio.descripcion
        spData.fechaSolicitud = entity.fecha_solicitud.toString()
        return spData
    }
    suspend fun guardarDataAprobada() {
        dataAprobada.clear()
        val solicitudesAprobadas = generateSolicitudesAprobadas()
        var result = withContext(Dispatchers.IO) {
            transaction {
                val listData = mutableListOf<sData>()
                solicitudesAprobadas.forEach { sp ->
                    listData.add(obtenerDataAprobada(sp))
                }
                listData
            }
        }

        dataAprobada.addAll(result)
    }
    suspend fun guardarDataPendiente() {
        dataPendiente.clear()
        val solicitudesPendientes = generateSolicitudesPendientes()
        var result = withContext(Dispatchers.IO) {
            transaction {
                val listData = mutableListOf<spData>()
                solicitudesPendientes.forEach { sp ->
                    listData.add(obtenerDataPendiente(sp))
                }
                listData
            }
        }

        dataPendiente.addAll(result)
    }

    fun filtrarSolicitudesPendientes(){
        val iterator = dataPendiente.iterator()
        while (iterator.hasNext()) {
            val pendienteItem = iterator.next()

            for (aprobadaItem in dataAprobada) {
                if (pendienteItem.id_solicitud == aprobadaItem.id_solicitud) {
                    iterator.remove()
                    break
                }
            }
        }
    }
    suspend fun cargarData(){
        val jobPendiente = CoroutineScope(Dispatchers.IO).launch { guardarDataPendiente() }
        val jobAprobada = CoroutineScope(Dispatchers.IO).launch { guardarDataAprobada() }

        // Esperar a que ambas funciones suspendidas finalicen
        jobPendiente.join()
        jobAprobada.join()

        filtrarSolicitudesPendientes()

        isLoading = false
    }
}