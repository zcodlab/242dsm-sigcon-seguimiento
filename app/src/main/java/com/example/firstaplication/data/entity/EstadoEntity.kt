package com.example.firstaplication.data.entity

import com.example.firstaplication.data.table.EstadoTable
import org.jetbrains.exposed.dao.Entity
import org.jetbrains.exposed.dao.EntityClass
import org.jetbrains.exposed.dao.id.EntityID

class EstadoEntity(id: EntityID<Int>) : Entity<Int>(id) {
    companion object : EntityClass<Int, EstadoEntity>(EstadoTable)

    var descripcion by EstadoTable.descripcion
}
