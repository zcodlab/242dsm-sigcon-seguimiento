package com.example.firstaplication.data.entity

import com.example.firstaplication.data.table.PersonalTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class PersonalEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PersonalEntity>(PersonalTable)

    var persona by PersonaEntity referencedOn PersonalTable.id_persona
    var rol by RolEntity referencedOn PersonalTable.id_rol
    var fecha_contrato by PersonalTable.fecha_contrato
    var fecha_cese by PersonalTable.fecha_cese
}