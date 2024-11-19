package com.example.firstaplication.data.table

import org.jetbrains.exposed.dao.id.IntIdTable

object SolicitanteTable : IntIdTable("solicitante", "id_solicitante") {
    val id_persona = reference("id_persona",PersonaTable.id)
    val id_rol = reference("id_rol",RolTable.id)
    val telefono = integer("telefono")
    val correo = varchar("correo", 30)
}