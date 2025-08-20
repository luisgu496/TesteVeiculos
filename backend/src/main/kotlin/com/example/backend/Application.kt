package com.example.backend

import com.example.backend.routes.vehicleRoutes
import com.example.backend.plugins.configureSerialization
import com.example.backend.plugins.configureCORS
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.response.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        module()
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
    configureCORS()

    routing {
        get("/") {
            call.respondText("API de veículos funcionando!")
        }
        vehicleRoutes()
    }
}