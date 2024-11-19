package com.example.firstaplication.ui.views.infoSolicitudes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.firstaplication.data.dao.solicitud_cotizacionDAO
import androidx.lifecycle.ViewModel
import com.example.firstaplication.data.dao.solicitudDAO
import com.example.firstaplication.data.entity.SolicitudEntity
import com.example.firstaplication.data.model.Solicitud
import com.example.firstaplication.data.model.SolicitudCotizacion
import com.example.firstaplication.data.model.sDataDetalle
import com.example.firstaplication.data.table.EstadoSolicitudTable
import com.example.firstaplication.data.table.EstadoTable
import com.example.firstaplication.data.table.PersonalTable
import com.example.firstaplication.data.table.SolicitudCotizacionTable
import com.example.firstaplication.data.table.SolicitudTable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class DetalleSolicitudViewModel @Inject constructor(private val sDAO: solicitudDAO,
                                                    private val scDAO: solicitud_cotizacionDAO): ViewModel(){
    var isLoading by mutableStateOf(true)
    var dataDetalle = sDataDetalle()
    var dataCotizacion = SolicitudCotizacion()
    //var dataSoli_estado_Solo = addSolicitud_estado_Solicitud()
    fun add(importe:Double){
        dataCotizacion.importe = importe
        dataCotizacion.id_estado= EntityID(1, EstadoTable)
        val id = scDAO.generateMaxId()+1
        scDAO.create(dataCotizacion,id);
    }


    fun addSolicitud_estado_Solicitud(){
        var id = scDAO.generateMaxIdParaSOLI_ESTADO_SOLI()+1
        var fecha= dataCotizacion.fecha_cotizacion
        var id_solicitud = dataCotizacion.id_solicitud
        var id_estado_solicitud = EntityID(2, EstadoSolicitudTable)//cotizado
        var ind_ultimo_ = "1"
        scDAO.create_SOLI_ESTADO_SOLI(id,fecha,id_solicitud,id_estado_solicitud,ind_ultimo_)
    }

    suspend fun generateSolicitudesPendientes(id: String): ArrayList<SolicitudEntity> {
        return withContext(Dispatchers.IO) {
            sDAO.readPendienteDetalle(id)
        }
    }
    fun obtenerDataPendiente(entity: SolicitudEntity): sDataDetalle {
        val sData = sDataDetalle()
        sData.nombre = entity.solicitante.persona.apellido_paterno + " " + entity.solicitante.persona.apellido_materno + " " + entity.solicitante.persona.nombres
        sData.nro_documento = entity.solicitante.persona.ndocumento
        sData.rol = entity.solicitante.rol.descripcion
        sData.correo = entity.solicitante.correo
        sData.nombre_predio = entity.predio.descripcion
        sData.direccion_predio = entity.predio.direccion
        sData.tipo_predio = entity.predio.tipo_predio.nombre_predio
        sData.ruc_predio = entity.predio.ruc
        sData.cant_vigilantes = entity.cant_vigilantes
        sData.precio_vigilante = entity.servicio.precio.toDouble()
        sData.cant_limpieza = entity.cant_plimpieza
        sData.precio_limpieza = entity.servicio.precio.toDouble()
        sData.cant_administracion = entity.cant_administracion
        sData.precio_administracion = entity.servicio.precio.toDouble()
        sData.cant_jardineria = entity.cant_jardineria
        sData.precio_jardineria = entity.servicio.precio.toDouble()
        sData.tipo_servicio = entity.servicio.descripcion
        return sData
    }

    suspend fun guardarDataPendiente(id: String) {
        val solicitudesPendientes = generateSolicitudesPendientes(id)
        var result = withContext(Dispatchers.IO) {
            transaction {
                var Data = sDataDetalle()
                Data = obtenerDataPendiente(solicitudesPendientes.get(0))
                Data
            }
        }
        dataDetalle = result
    }

    suspend fun cargarDataPendiente(id: String){
        val jobPendiente = CoroutineScope(Dispatchers.IO).launch { guardarDataPendiente(id) }
        val jobPendiente2 = CoroutineScope(Dispatchers.IO).launch { cargarDataCotizacion(id) }
        jobPendiente.join()
        jobPendiente2.join()
        isLoading = false
    }

    fun obtenerDataCotizacion(entity: SolicitudEntity):SolicitudCotizacion{
        val cotizacion = SolicitudCotizacion()
        cotizacion.fecha_cotizacion = LocalDate.now()
        cotizacion.id_solicitud = entity.id
        cotizacion.id_personal = EntityID(4,PersonalTable)
        return cotizacion
    }
    suspend fun guardarDataCotizacion(id: String){
        val solicitudesPendientes = generateSolicitudesPendientes(id)
        var result = withContext(Dispatchers.IO) {
            transaction {
                var Data = obtenerDataCotizacion(solicitudesPendientes.get(0))
                Data
            }
        }
        dataCotizacion = result
    }
    suspend fun cargarDataCotizacion(id: String){
        val jobAprobada = CoroutineScope(Dispatchers.IO).launch { guardarDataCotizacion(id) }

        jobAprobada.join()
    }
}