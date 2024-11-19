package com.example.firstaplication.data.entity

import com.example.firstaplication.data.table.SolicitudTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class SolicitudEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<SolicitudEntity>(SolicitudTable)

    var predio by PredioEntity referencedOn SolicitudTable.id_predio
    var solicitante by SolicitanteEntity referencedOn SolicitudTable.id_solicitante
    var servicio by ServicioEntity referencedOn SolicitudTable.id_servicio
    var area_predio by SolicitudTable.area_predio
    var num_casas by SolicitudTable.num_casas
    var cant_acomunes by SolicitudTable.cant_acomunes
    var area_acomunes by SolicitudTable.area_acomunes
    var cant_vigilantes by SolicitudTable.cant_vigilantes
    var cant_plimpieza by SolicitudTable.cant_plimpieza
    var cant_administracion by SolicitudTable.cant_administracion
    var cant_jardineria by SolicitudTable.cant_jardineria
    var fecha_solicitud by SolicitudTable.fecha_solicitud
}