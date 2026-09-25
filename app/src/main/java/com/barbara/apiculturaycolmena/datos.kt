package com.barbara.apiculturaycolmena

object Datos {

    val apiarios = mutableListOf(

        Apiario(
            nombre = "Apiario Los Aromos",
            ubicacion = "Doñihue",
            cantidadColmenas = 20,
            descripcion = "Apiario dedicado a la producción de miel y cuidado de colmenas."
        ),

        Apiario(
            nombre = "Apiario Santa Rosa",
            ubicacion = "Rancagua",
            cantidadColmenas = 15,
            descripcion = "Apiario de producción con manejo y revisión periódica."
        ),

        Apiario(
            nombre = "Apiario El Roble",
            ubicacion = "Coltauco",
            cantidadColmenas = 28,
            descripcion = "Apiario ubicado en una zona rural con buena floración."
        )
    )

    val apicultores = mutableListOf(

        Apicultor(
            nombre = "Carlos Muñoz",
            telefono = "+56 9 8765 4321",
            correo = "carlos@apicola.cl",
            especialidad = "Producción de miel"
        ),

        Apicultor(
            nombre = "María González",
            telefono = "+56 9 7654 3210",
            correo = "maria@apicola.cl",
            especialidad = "Manejo de colmenas"
        ),

        Apicultor(
            nombre = "Juan Pérez",
            telefono = "+56 9 6543 2109",
            correo = "juan@apicola.cl",
            especialidad = "Apicultura orgánica"
        )
    )

    val colmenas = mutableListOf(

        Colmena(
            codigo = "Colmena 01",
            tipo = "Langstroth",
            apiario = "Apiario Los Aromos",
            estado = "Activa"
        ),

        Colmena(
            codigo = "Colmena 02",
            tipo = "Langstroth",
            apiario = "Apiario Santa Rosa",
            estado = "Activa"
        ),

        Colmena(
            codigo = "Colmena 03",
            tipo = "Dadant",
            apiario = "Apiario El Roble",
            estado = "En revisión"
        )
    )
}