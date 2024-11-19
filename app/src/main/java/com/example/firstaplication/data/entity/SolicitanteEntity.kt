package com.example.firstaplication.data.entity

import com.example.firstaplication.data.table.SolicitanteTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SolicitanteEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SolicitanteEntity>(SolicitanteTable)

    var persona by PersonaEntity referencedOn SolicitanteTable.id_persona
    var rol by RolEntity referencedOn SolicitanteTable.id_rol
    var telefono by SolicitanteTable.telefono
    var correo by SolicitanteTable.correo
}