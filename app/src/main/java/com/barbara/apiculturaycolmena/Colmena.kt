package com.barbara.apiculturaycolmena

data class Colmena(
    var codigo: String,
    var tipo: String,
    var apiario: String,
    var estado: String = "Activa"
)