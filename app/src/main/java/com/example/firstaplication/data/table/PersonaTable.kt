package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object PersonaTable : IntIdTable("persona","id_persona") {
    val apellido_paterno = varchar("apellido_paterno", 60)
    val apellido_materno = varchar("apellido_materno", 60)
    val nombres = varchar("nombres", 60)
    val fecha_nacimiento = date("fecha_nacimiento")
    val id_tipo_documento = integer("id_tipo_documento")
    val ndocumento = varchar("ndocumento", 15)
    val direccion = varchar("direccion", 150)
    val idubigeo = varchar("idubigeo", 6)
}