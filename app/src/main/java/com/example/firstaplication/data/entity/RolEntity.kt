package com.example.firstaplication.data.entity

import com.example.firstaplication.data.table.RolTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class RolEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<RolEntity>(RolTable)

    var descripcion by RolTable.descripcion
}