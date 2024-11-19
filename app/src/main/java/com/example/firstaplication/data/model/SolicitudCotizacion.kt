package com.example.firstaplication.data.model

import com.example.firstaplication.data.table.EstadoTable
import com.example.firstaplication.data.table.PersonalTable
import com.example.firstaplication.data.table.SolicitudCotizacionTable
import com.example.firstaplication.data.table.SolicitudTable
import org.jetbrains.exposed.dao.id.EntityID
import java.time.LocalDate

data class SolicitudCotizacion(
    var id_solicitud: EntityID<Int> = EntityID(0, SolicitudTable),
    var id_personal: EntityID<Int> = EntityID(0, PersonalTable),
    var fecha_cotizacion: LocalDate = LocalDate.now(),
    var importe: Double = 0.0,
    var id_solicitud_cotizacion: EntityID<Long> = EntityID(1, SolicitudCotizacionTable),
    var id_estado: EntityID<Int> = EntityID(0, EstadoTable)
)
