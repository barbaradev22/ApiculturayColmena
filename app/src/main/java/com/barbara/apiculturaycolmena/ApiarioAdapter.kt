package com.barbara.apiculturaycolmena

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApiarioAdapter(
    private val listaApiarios: MutableList<Apiario>
) : RecyclerView.Adapter<ApiarioAdapter.ApiarioViewHolder>() {

    class ApiarioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nombre: TextView =
            itemView.findViewById(R.id.txtNombreApiario)

        val ubicacion: TextView =
            itemView.findViewById(R.id.txtUbicacionApiario)

        val cantidad: TextView =
            itemView.findViewById(R.id.txtCantidadApiario)

        val descripcion: TextView =
            itemView.findViewById(R.id.txtDescripcionApiario)

        val editar: Button =
            itemView.findViewById(R.id.btnEditarApiario)

        val eliminar: Button =
            itemView.findViewById(R.id.btnEliminarApiario)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApiarioViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.i_apiario, parent, false)

        return ApiarioViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: ApiarioViewHolder,
        position: Int
    ) {

        val apiario = listaApiarios[position]

        holder.nombre.text = apiario.nombre
        holder.ubicacion.text = apiario.ubicacion
        holder.cantidad.text = "Colmenas: ${apiario.cantidadColmenas}"
        holder.descripcion.text = apiario.descripcion

        holder.editar.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                FormApiariosActivity::class.java
            )

            intent.putExtra("indiceApiario", position)

            holder.itemView.context.startActivity(intent)
        }

        holder.eliminar.setOnClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Eliminar apiario")
                .setMessage("¿Estás seguro de que quieres eliminar este apiario?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Eliminar") { _, _ ->

                    listaApiarios.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, listaApiarios.size)
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return listaApiarios.size
    }
}