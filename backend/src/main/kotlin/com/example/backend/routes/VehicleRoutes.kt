package com.example.backend.routes

import com.example.backend.repository.VehicleRepository
import com.example.backend.models.VeiculoInput
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

fun Route.vehicleRoutes() {
    route("/veiculos") {

        get {
            val params = call.request.queryParameters
            val marca = params["marca"]
            val ano = params["ano"]?.toIntOrNull()
            val base = VehicleRepository.lista.toList()

            val filtrado = base.filter {
                (marca == null || it.marca == marca) &&
                (ano == null || it.ano == ano)
            }

            call.respond(filtrado)
        }

        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            val v = VehicleRepository.lista.find { it.id == id }
            if (v == null) {
                call.respondText("Veículo não encontrado", status = HttpStatusCode.NotFound)
            } else {
                call.respond(v)
            }
        }

        post {
            try {
                val input = call.receive<VeiculoInput>()
                val created = VehicleRepository.create(input)
                call.respond(HttpStatusCode.Created, created)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, mapOf("erro" to e.message))
            }
        }

        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) return@put call.respond(HttpStatusCode.BadRequest, "ID inválido")
            try {
                val input = call.receive<VeiculoInput>()
                val updated = VehicleRepository.updateFull(id, input)
                call.respond(updated)
            } catch (e: NoSuchElementException) {
                call.respond(HttpStatusCode.NotFound, mapOf("erro" to e.message))
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, mapOf("erro" to e.message))
            }
        }

        patch("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) return@patch call.respond(HttpStatusCode.BadRequest, "ID inválido")
            try {
                val json = call.receive<Map<String, Any?>>()
                val updated = VehicleRepository.updatePartial(id, json)
                call.respond(updated)
            } catch (e: NoSuchElementException) {
                call.respond(HttpStatusCode.NotFound, mapOf("erro" to e.message))
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, mapOf("erro" to e.message))
            }
        }

        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
            if (id == null) return@delete call.respond(HttpStatusCode.BadRequest, "ID inválido")
            val removed = VehicleRepository.delete(id)
            if (removed) call.respondText("Veículo removido com sucesso")
            else call.respondText("Veículo não encontrado", status = HttpStatusCode.NotFound)
        }
    }

    route("/estatisticas") {
        get("/nao-vendidos") {
            val count = VehicleRepository.lista.count { !it.vendido }
            call.respond(mapOf("naoVendidos" to count))
        }

        get("/por-decada") {
            val porDecada = VehicleRepository.lista.groupBy {
                val decada = (it.ano / 10) * 10
                "Década " + decada
            }.mapValues { it.value.size }
            call.respond(porDecada)
        }

        get("/por-marca") {
            val porMarca = VehicleRepository.lista.groupBy { it.marca }
                .mapValues { it.value.size }
            call.respond(porMarca)
        }

        get("/ultima-semana") {
            val umaSemanaAtras = LocalDateTime.now().minus(7, ChronoUnit.DAYS)
            val recentes = VehicleRepository.lista.filter {
                LocalDateTime.parse(it.created).isAfter(umaSemanaAtras)
            }
            call.respond(recentes)
        }
    }
}