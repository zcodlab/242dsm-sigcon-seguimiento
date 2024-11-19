package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable

object ServicioTable : IntIdTable("servicio", "id_servicio") {
    val descripcion = varchar("descripcion", 255)
    val precio = decimal("precio", 10, 2)
}