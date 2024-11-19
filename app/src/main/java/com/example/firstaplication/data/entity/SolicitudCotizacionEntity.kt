package com.example.firstaplication.data.entity

import com.example.firstaplication.data.table.SolicitudCotizacionTable
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SolicitudCotizacionEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<SolicitudCotizacionEntity>(SolicitudCotizacionTable)

    var solicitud by SolicitudEntity referencedOn SolicitudCotizacionTable.id_solicitud
    var personal by PersonalEntity referencedOn SolicitudCotizacionTable.id_personal
    var fecha_cotizacion by SolicitudCotizacionTable.fecha_cotizacion
    var importe by SolicitudCotizacionTable.importe
    var estado by EstadoEntity referencedOn SolicitudCotizacionTable.id_estado
}