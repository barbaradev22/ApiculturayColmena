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

class ColmenasActivity : AppCompatActivity() {

    private lateinit var adapter: ColmenaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_colmenas)

        configurarNavegacion(this)

        val btnVolver = findViewById<TextView>(R.id.btnVolverColmenas)
        val btnNuevaColmena = findViewById<Button>(R.id.btnNuevaColmena)
        val recyclerView = findViewById<RecyclerView>(R.id.rvColmenas)

        btnVolver.setOnClickListener {
            finish()
        }

        btnNuevaColmena.setOnClickListener {
            val intent = Intent(this, FormColmenasActivity::class.java)
            startActivity(intent)
        }

        adapter = ColmenaAdapter(Datos.colmenas)

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