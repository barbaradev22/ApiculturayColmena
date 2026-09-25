package com.barbara.apiculturaycolmena

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormApiariosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_apiarios)

        configurarNavegacion(this)

        val etNombreApiario = findViewById<EditText>(R.id.etNombreApiario)
        val etUbicacion = findViewById<EditText>(R.id.etUbicacion)
        val etCantidadColmenas = findViewById<EditText>(R.id.etCantidadColmenas)
        val etDescripcion = findViewById<EditText>(R.id.etDescripcion)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarApiario)
        val btnVolver = findViewById<TextView>(R.id.btnVolverApiarios)

        val indice = intent.getIntExtra("indiceApiario", -1)

        if (indice != -1) {

            val apiario = Datos.apiarios[indice]

            etNombreApiario.setText(apiario.nombre)
            etUbicacion.setText(apiario.ubicacion)
            etCantidadColmenas.setText(apiario.cantidadColmenas.toString())
            etDescripcion.setText(apiario.descripcion)

            btnGuardar.text = "Guardar cambios"
        }

        btnGuardar.setOnClickListener {

            val nombre = etNombreApiario.text.toString()
            val ubicacion = etUbicacion.text.toString()
            val cantidad = etCantidadColmenas.text.toString()
            val descripcion = etDescripcion.text.toString()

            if (nombre.isEmpty() || ubicacion.isEmpty() || cantidad.isEmpty()) {

                Toast.makeText(
                    this,
                    "Completa los campos obligatorios",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (indice == -1) {

                val apiario = Apiario(
                    nombre = nombre,
                    ubicacion = ubicacion,
                    cantidadColmenas = cantidad.toInt(),
                    descripcion = descripcion
                )

                Datos.apiarios.add(apiario)

                Toast.makeText(
                    this,
                    "Apiario guardado correctamente",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // Editar apiario existente
                Datos.apiarios[indice].nombre = nombre
                Datos.apiarios[indice].ubicacion = ubicacion
                Datos.apiarios[indice].cantidadColmenas = cantidad.toInt()
                Datos.apiarios[indice].descripcion = descripcion

                Toast.makeText(
                    this,
                    "Apiario actualizado correctamente",
                    Toast.LENGTH_SHORT
                ).show()
            }

            finish()
        }

        btnVolver.setOnClickListener {
            finish()
        }
    }
}