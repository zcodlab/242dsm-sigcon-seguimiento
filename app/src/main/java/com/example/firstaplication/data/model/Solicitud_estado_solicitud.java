/*package com.example.firstaplication.data.model


import com.example.firstaplication.data.table.SolicitudTable;

import org.jetbrains.exposed.dao.id.EntityID
import java.time.LocalDate

data class Solicitud_estado_solicitud(
        var id_solicitud_estado_solicitud: EntityID<Long> = EntityID(0, SolicitudTable),
        var fecha: LocalDate = LocalDate.now(),
        var id_solicitud: EntityID<Int> = EntityID(0, SolicitudTable),
        var id_estado_solicitud: EntityID<Int> = EntityID(0, EstadoSolicitudTable),
        var ind_ultimo: Int = 1
        )*/