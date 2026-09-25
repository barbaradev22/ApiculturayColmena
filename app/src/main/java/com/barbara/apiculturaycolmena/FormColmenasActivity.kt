package com.barbara.apiculturaycolmena

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics

class FormColmenasActivity : AppCompatActivity() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form_colmenas)

        configurarNavegacion(this)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val etCodigo = findViewById<EditText>(R.id.etCodigoColmena)
        val etTipo = findViewById<EditText>(R.id.etTipoColmena)
        val spApiario = findViewById<Spinner>(R.id.spApiarioColmena)
        val spEstado = findViewById<Spinner>(R.id.spEstadoColmena)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarColmena)
        val btnVolver = findViewById<TextView>(R.id.btnVolverColmenas)
        val titulo = findViewById<TextView>(R.id.txtTituloColmena)
        val txtEstado = findViewById<TextView>(R.id.txtEstadoColmena)

        val apiarios = Datos.apiarios.map { it.nombre }

        val adapterApiarios = ArrayAdapter(
            this,
            R.layout.i_spinner,
            apiarios
        )

        adapterApiarios.setDropDownViewResource(
            R.layout.i_spinner
        )

        spApiario.adapter = adapterApiarios

        val estados = listOf(
            "Activa",
            "En revisión",
            "Inactiva"
        )

        val adapterEstados = ArrayAdapter(
            this,
            R.layout.i_spinner,
            estados
        )

        adapterEstados.setDropDownViewResource(
            R.layout.i_spinner
        )

        spEstado.adapter = adapterEstados

        val indice = intent.getIntExtra("indiceColmena", -1)

        if (indice != -1) {

            if (indice >= Datos.colmenas.size) {
                Toast.makeText(
                    this,
                    "No se pudo cargar la colmena",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
                return
            }

            val colmena = Datos.colmenas[indice]

            etCodigo.setText(colmena.codigo)
            etTipo.setText(colmena.tipo)

            val posicionApiario = apiarios.indexOf(colmena.apiario)

            if (posicionApiario >= 0) {
                spApiario.setSelection(posicionApiario)
            }

            val posicionEstado = estados.indexOf(colmena.estado)

            if (posicionEstado >= 0) {
                spEstado.setSelection(posicionEstado)
            }

            txtEstado.visibility = View.VISIBLE
            spEstado.visibility = View.VISIBLE

            titulo.text = "Editar colmena"
            btnGuardar.text = "Guardar cambios"
        }

        btnGuardar.setOnClickListener {

            val codigo = etCodigo.text.toString().trim()
            val tipo = etTipo.text.toString().trim()

            if (codigo.isEmpty() || tipo.isEmpty()) {

                Toast.makeText(
                    this,
                    "Completa todos los campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (spApiario.selectedItem == null) {

                Toast.makeText(
                    this,
                    "Selecciona un apiario",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val apiario = spApiario.selectedItem.toString()

            if (indice == -1) {

                val colmena = Colmena(
                    codigo = codigo,
                    tipo = tipo,
                    apiario = apiario,
                    estado = "Activa"
                )

                Datos.colmenas.add(colmena)

                firebaseAnalytics.logEvent("crear_colmena", null)

                Toast.makeText(
                    this,
                    "Colmena guardada correctamente",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                if (indice >= Datos.colmenas.size) {
                    Toast.makeText(
                        this,
                        "No se pudo actualizar la colmena",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                val estado = spEstado.selectedItem.toString()

                Datos.colmenas[indice].codigo = codigo
                Datos.colmenas[indice].tipo = tipo
                Datos.colmenas[indice].apiario = apiario
                Datos.colmenas[indice].estado = estado

                Toast.makeText(
                    this,
                    "Colmena actualizada correctamente",
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