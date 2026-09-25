package com.barbara.apiculturaycolmena

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApicultorAdapter(
    private val listaApicultores: MutableList<Apicultor>
) : RecyclerView.Adapter<ApicultorAdapter.ApicultorViewHolder>() {

    class ApicultorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nombre: TextView =
            itemView.findViewById(R.id.txtNombreApicultor)

        val telefono: TextView =
            itemView.findViewById(R.id.txtTelefonoApicultor)

        val correo: TextView =
            itemView.findViewById(R.id.txtCorreoApicultor)

        val especialidad: TextView =
            itemView.findViewById(R.id.txtEspecialidadApicultor)

        val editar: Button =
            itemView.findViewById(R.id.btnEditarApicultor)

        val eliminar: Button =
            itemView.findViewById(R.id.btnEliminarApicultor)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApicultorViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.i_apicultor, parent, false)

        return ApicultorViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: ApicultorViewHolder,
        position: Int
    ) {

        val apicultor = listaApicultores[position]

        holder.nombre.text = apicultor.nombre
        holder.telefono.text = apicultor.telefono
        holder.correo.text = apicultor.correo
        holder.especialidad.text = "Especialidad: ${apicultor.especialidad}"

        holder.editar.setOnClickListener {

            val intent = Intent(
                holder.itemView.context,
                FormApicultoresActivity::class.java
            )

            intent.putExtra("indiceApicultor", position)

            holder.itemView.context.startActivity(intent)
        }

        holder.eliminar.setOnClickListener {

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Eliminar apicultor")
                .setMessage("¿Estás seguro de que quieres eliminar este apicultor?")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Eliminar") { _, _ ->

                    listaApicultores.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, listaApicultores.size)
                }
                .show()
        }
    }

    override fun getItemCount(): Int {
        return listaApicultores.size
    }
}