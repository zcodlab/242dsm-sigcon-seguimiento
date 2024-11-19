package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.date

object Solicitud_estado_solicitudTable: LongIdTable("solicitud_estado_solicitud","id_solicitud_estado_solicitud") {
        val fecha = date("fecha")
        val id_solicitud = reference("id_solicitud", SolicitudTable.id)
        val id_estado_solicitud=reference("id_estado_solicitud", EstadoSolicitudTable.id)
        val ind_ultimo = varchar("ind_ultimo",  length = 40)

}