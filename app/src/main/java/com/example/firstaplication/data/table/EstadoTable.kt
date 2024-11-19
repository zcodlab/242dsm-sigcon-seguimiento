package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable

object EstadoTable: IntIdTable("estado","id_estado") {
    val descripcion = varchar("descripcion",100)
}