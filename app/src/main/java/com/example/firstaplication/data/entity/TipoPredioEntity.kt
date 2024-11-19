package com.example.firstaplication.data.entity

import com.example.firstaplication.data.table.TipoPredioTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TipoPredioEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<TipoPredioEntity>(TipoPredioTable)

    var nombre_predio by TipoPredioTable.nombre_predio
}