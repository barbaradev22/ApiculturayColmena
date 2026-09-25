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

class ApicultoresActivity : AppCompatActivity() {

    private lateinit var adapter: ApicultorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apicultores)

        configurarNavegacion(this)

        val btnVolver = findViewById<TextView>(R.id.btnVolverApicultores)
        val btnNuevoApicultor = findViewById<Button>(R.id.btnNuevoApicultor)
        val recyclerView = findViewById<RecyclerView>(R.id.rvApicultores)

        btnVolver.setOnClickListener {
            finish()
        }

        btnNuevoApicultor.setOnClickListener {
            val intent = Intent(this, FormApicultoresActivity::class.java)
            startActivity(intent)
        }

        adapter = ApicultorAdapter(Datos.apicultores)

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