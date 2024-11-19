package com.example.firstaplication.data.model

import org.jetbrains.exposed.dao.id.EntityID

data class Estado(
    val id_estado: EntityID<Int>,
    val descripcion:String,
)
