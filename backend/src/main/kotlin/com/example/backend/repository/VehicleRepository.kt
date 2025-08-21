package com.example.backend.repository

import com.example.backend.models.Veiculo
import com.example.backend.models.VeiculoInput
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicInteger

object VehicleRepository {
    private val idCounter = AtomicInteger(1)
    val marcasValidas = setOf("Ford", "Honda", "Volkswagen", "Chevrolet", "Toyota", "Fiat", "Hyundai", "Nissan", "Renault", "Peugeot", "BMW", "Mercedes-Benz", "Audi")

    val lista = mutableListOf<Veiculo>()

    fun create(input: VeiculoInput): Veiculo {
        require(input.marca in marcasValidas) { "Marca inválida" }
        val now = LocalDateTime.now().toString()
        val v = Veiculo(
            id = idCounter.getAndIncrement(),
            veiculo = input.veiculo,
            marca = input.marca,
            ano = input.ano,
            cor = input.cor,
            descricao = input.descricao,
            vendido = input.vendido,
            created = now,
            updated = now
        )
        lista.add(v)
        return v
    }

    fun updateFull(id: Int, input: VeiculoInput): Veiculo {
        require(input.marca in marcasValidas) { "Marca inválida" }
        val idx = lista.indexOfFirst { it.id == id }
        if (idx == -1) throw NoSuchElementException("Veículo não encontrado")
        val current = lista[idx]
        val updated = current.copy(
            veiculo = input.veiculo,
            marca = input.marca,
            ano = input.ano,
            cor = input.cor,
            descricao = input.descricao,
            vendido = input.vendido,
            updated = LocalDateTime.now().toString()
        )
        lista[idx] = updated
        return updated
    }

    fun updatePartial(id: Int, map: Map<String, Any?>): Veiculo {
        val idx = lista.indexOfFirst { it.id == id }
        if (idx == -1) throw NoSuchElementException("Veículo não encontrado")
        val c = lista[idx]

        val novaMarca = (map["marca"] as? String) ?: c.marca
        if (novaMarca !in marcasValidas) throw IllegalArgumentException("Marca inválida")

        val updated = c.copy(
            veiculo = (map["veiculo"] as? String) ?: c.veiculo,
            marca = novaMarca,
            ano = when (val a = map["ano"]) { is Number -> a.toInt(); is String -> a.toIntOrNull() ?: c.ano; else -> c.ano },
            cor = (map["cor"] as? String) ?: c.cor,
            descricao = (map["descricao"] as? String) ?: c.descricao,
            vendido = when (val v = map["vendido"]) { is Boolean -> v; is String -> v.toBooleanStrictOrNull() ?: c.vendido; else -> c.vendido },
            updated = LocalDateTime.now().toString()
        )
        lista[idx] = updated
        return updated
    }

    fun delete(id: Int): Boolean = lista.removeIf { it.id == id }
}
