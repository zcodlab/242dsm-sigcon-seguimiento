package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object SolicitudTable : IntIdTable("solicitud","id_solicitud") {
    val id_predio = reference("id_predio",PredioTable.id)
    val id_solicitante = reference("id_solicitante",SolicitanteTable.id)
    val id_servicio = reference("id_servicio",ServicioTable.id)
    val area_predio = decimal("area_predio", 10, 2)
    val num_casas = integer("num_casas")
    val cant_acomunes = integer("cant_acomunes")
    val area_acomunes = integer("area_acomunes")
    val cant_vigilantes = integer("cant_vigilantes")
    val cant_plimpieza = integer("cant_plimpieza")
    val cant_administracion = integer("cant_administracion")
    val cant_jardineria = integer("cant_jardineria")
    val fecha_solicitud = date("fecha_solicitud")
}