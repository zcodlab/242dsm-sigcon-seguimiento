package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable

object PredioTable : IntIdTable("predio", "id_predio") {
    val id_tipo_predio = reference("id_tipo_predio",TipoPredioTable.id)
    val descripcion = varchar("descripcion", 255)
    val ruc = varchar("ruc", 11)
    val telefono = varchar("telefono", 20)
    val correo = varchar("correo", 255)
    val direccion = varchar("direccion", 255)
}