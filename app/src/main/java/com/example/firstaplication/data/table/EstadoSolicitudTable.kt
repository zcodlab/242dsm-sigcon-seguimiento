package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object EstadoSolicitudTable: IntIdTable("estado_solicitud","id_estado_solicitud") {

        val descripcion = varchar("descripcion", length = 40)
        val ind_cotizacion = varchar("ind_cotizacion",  length = 40)
}