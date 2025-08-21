package com.example.backend.models

import kotlinx.serialization.Serializable

@Serializable
data class Veiculo(
    val id: Int,
    val veiculo: String,
    val marca: String,
    val ano: Int,
    val cor: String,
    val descricao: String,
    val vendido: Boolean,
    val created: String,
    val updated: String
)

@Serializable
data class VeiculoInput(
    val veiculo: String,
    val marca: String,
    val ano: Int,
    val cor: String,
    val descricao: String,
    val vendido: Boolean
)
