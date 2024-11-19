package com.example.firstaplication.data.entity

import com.example.firstaplication.data.table.ServicioTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ServicioEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<ServicioEntity>(ServicioTable)

    var descripcion by ServicioTable.descripcion
    var precio by ServicioTable.precio
}