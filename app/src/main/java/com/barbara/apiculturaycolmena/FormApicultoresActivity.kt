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

class FormApicultoresActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_apicultores)

        configurarNavegacion(this)

        val etNombre = findViewById<EditText>(R.id.etNombreApicultor)
        val etTelefono = findViewById<EditText>(R.id.etTelefono)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val etEspecialidad = findViewById<EditText>(R.id.etEspecialidad)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarApicultor)
        val btnVolver = findViewById<TextView>(R.id.btnVolverApicultores)
        val titulo = findViewById<TextView>(R.id.txtTituloApicultor)

        val indice = intent.getIntExtra("indiceApicultor", -1)

        if (indice != -1) {

            val apicultor = Datos.apicultores[indice]

            etNombre.setText(apicultor.nombre)
            etTelefono.setText(apicultor.telefono)
            etCorreo.setText(apicultor.correo)
            etEspecialidad.setText(apicultor.especialidad)

            titulo.text = "Editar apicultor"
            btnGuardar.text = "Guardar cambios"
        }

        btnGuardar.setOnClickListener {

            val nombre = etNombre.text.toString()
            val telefono = etTelefono.text.toString()
            val correo = etCorreo.text.toString()
            val especialidad = etEspecialidad.text.toString()

            if (
                nombre.isEmpty() ||
                telefono.isEmpty() ||
                correo.isEmpty() ||
                especialidad.isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (indice == -1) {

                val apicultor = Apicultor(
                    nombre = nombre,
                    telefono = telefono,
                    correo = correo,
                    especialidad = especialidad
                )

                Datos.apicultores.add(apicultor)

                Toast.makeText(
                    this,
                    "Apicultor guardado correctamente",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                Datos.apicultores[indice].nombre = nombre
                Datos.apicultores[indice].telefono = telefono
                Datos.apicultores[indice].correo = correo
                Datos.apicultores[indice].especialidad = especialidad

                Toast.makeText(
                    this,
                    "Apicultor actualizado correctamente",
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