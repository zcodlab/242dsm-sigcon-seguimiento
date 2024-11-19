package com.example.firstaplication.data.model

import com.example.firstaplication.data.table.PredioTable
import com.example.firstaplication.data.table.ServicioTable
import com.example.firstaplication.data.table.SolicitanteTable
import com.example.firstaplication.data.table.SolicitudTable
import org.jetbrains.exposed.dao.id.EntityID
import java.math.BigDecimal
import java.time.LocalDate

data class Solicitud(
    val id_solicitud: EntityID<Int> = EntityID(0, SolicitudTable),
    val id_predio: EntityID<Int> = EntityID(0, PredioTable),
    val id_solicitante: EntityID<Int> = EntityID(0, SolicitanteTable),
    val id_servicio: EntityID<Int> = EntityID(0, ServicioTable),
    val area_predio: BigDecimal = BigDecimal.ZERO,
    val num_casas: Int = 0,
    val cant_acomunes: Int = 0,
    val area_acomunes: Int = 0,
    val cant_vigilantes: Int = 0,
    val cant_plimpieza: Int = 0,
    val cant_administracion: Int = 0,
    val cant_jardineria: Int = 0,
    val fecha_solicitud: LocalDate = LocalDate.now()
)

