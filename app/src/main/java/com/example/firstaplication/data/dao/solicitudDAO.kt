package com.example.firstaplication.data.dao

import com.example.firstaplication.data.entity.SolicitudCotizacionEntity
import com.example.firstaplication.data.entity.SolicitudEntity
import com.example.firstaplication.data.model.Solicitud
import com.example.firstaplication.data.model.SolicitudCotizacion
import com.example.firstaplication.data.table.SolicitudCotizacionTable
import com.example.firstaplication.data.table.SolicitudTable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDate
import javax.inject.Inject

class solicitudDAO @Inject constructor() {
    fun readPendientes(): ArrayList<SolicitudEntity>{
        return runBlocking {
            withContext(Dispatchers.IO) {
                val result = ArrayList<SolicitudEntity>()
                transaction {
                    val query = SolicitudEntity.all()
                    query.forEach { e ->
                        result.add(e)
                    }
                }
                result
            }
        }
    }

    fun readPendienteDetalle(id: String): ArrayList<SolicitudEntity>{
        val id2 = id.toInt()

        return runBlocking {
            withContext(Dispatchers.IO) {
                val result = ArrayList<SolicitudEntity>()
                transaction {
                    val query = SolicitudEntity.findById(id2)
                    if (query != null) {
                        result.add(query)
                    }
                }
                result
            }
        }
    }
}