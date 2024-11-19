package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.date

object PersonalTable : IntIdTable("personal", "id_personal") {
    val id_persona = reference("id_persona",PersonaTable.id)
    val id_rol = reference("id_rol",RolTable.id)
    val fecha_contrato = date("fecha_contrato")
    val fecha_cese = date("fecha_cese").nullable()
}
