package com.example.firstaplication.data.entity

import com.example.firstaplication.data.table.PersonaTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID


class PersonaEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<PersonaEntity>(PersonaTable)

    var apellido_paterno by PersonaTable.apellido_paterno
    var apellido_materno by PersonaTable.apellido_materno
    var nombres by PersonaTable.nombres
    var fecha_nacimiento by PersonaTable.fecha_nacimiento
    var id_tipo_documento by PersonaTable.id_tipo_documento
    var ndocumento by PersonaTable.ndocumento
    var direccion by PersonaTable.direccion
    var idubigeo by PersonaTable.idubigeo
}