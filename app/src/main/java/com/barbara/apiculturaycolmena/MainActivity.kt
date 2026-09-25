package com.barbara.apiculturaycolmena

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        configurarNavegacion(this)

        val btnApiarios = findViewById<Button>(R.id.btnApiarios)
        val btnApicultores = findViewById<Button>(R.id.btnApicultores)
        val btnColmenas = findViewById<Button>(R.id.btnColmenas)

        btnApiarios.setOnClickListener {
            startActivity(
                Intent(this, ApiariosActivity::class.java)
            )
        }

        btnApicultores.setOnClickListener {
            startActivity(
                Intent(this, ApicultoresActivity::class.java)
            )
        }

        btnColmenas.setOnClickListener {
            startActivity(
                Intent(this, ColmenasActivity::class.java)
            )
        }
    }

    override fun onResume() {
        super.onResume()

        val txtCantidadApiarios = findViewById<TextView>(R.id.txtCantidadApiarios)
        val txtCantidadApicultores = findViewById<TextView>(R.id.txtCantidadApicultores)
        val txtCantidadColmenas = findViewById<TextView>(R.id.txtCantidadColmenas)

        txtCantidadApiarios.text = Datos.apiarios.size.toString()
        txtCantidadApicultores.text = Datos.apicultores.size.toString()
        txtCantidadColmenas.text = Datos.colmenas.size.toString()
    }
}