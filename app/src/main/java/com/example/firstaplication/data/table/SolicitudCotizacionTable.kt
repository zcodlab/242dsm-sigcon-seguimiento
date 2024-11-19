package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.date

object SolicitudCotizacionTable : LongIdTable("solicitud_cotizacion","id_solicitud_cotizacion") {
    val id_solicitud = reference("id_solicitud", SolicitudTable.id)
    val id_personal = reference("id_personal", PersonalTable.id)
    val fecha_cotizacion = date("fecha_cotizacion")
    val importe = double("importe")
    val id_estado = reference("id_estado", EstadoTable.id)
}