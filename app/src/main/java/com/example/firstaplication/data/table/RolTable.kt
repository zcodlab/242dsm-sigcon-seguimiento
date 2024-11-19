package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable

object RolTable: IntIdTable("rol","id_rol") {
    val descripcion = varchar("descripcion",60)
}