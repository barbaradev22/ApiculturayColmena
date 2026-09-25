package com.barbara.apiculturaycolmena

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ApiariosActivity : AppCompatActivity() {

    private lateinit var adapter: ApiarioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apiarios)

        configurarNavegacion(this)

        val btnVolver = findViewById<TextView>(R.id.btnVolverApiarios)
        val btnNuevoApiario = findViewById<Button>(R.id.btnNuevoApiario)
        val recyclerView = findViewById<RecyclerView>(R.id.rvApiarios)

        btnVolver.setOnClickListener {
            finish()
        }

        btnNuevoApiario.setOnClickListener {
            val intent = Intent(this, FormApiariosActivity::class.java)
            startActivity(intent)
        }

        adapter = ApiarioAdapter(Datos.apiarios)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()

        if (::adapter.isInitialized) {
            adapter.notifyDataSetChanged()
        }
    }
}