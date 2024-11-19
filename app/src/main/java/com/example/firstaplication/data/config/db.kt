package com.example.firstaplication.data.config

import org.jetbrains.exposed.sql.Database
object db {
    private val _user = "modulo4"
    private val _password = "modulo4"
    private val _database = "sigcon"
    private val _host = "137.184.120.127"
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://$_host/$_database",
            driver = "org.postgresql.Driver",
            user = _user,
            password = _password,
        )
    }
}