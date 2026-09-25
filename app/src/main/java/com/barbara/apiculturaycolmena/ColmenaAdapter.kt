package com.barbara.apiculturaycolmena

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ColmenaAdapter(
    private val listaColmenas: MutableList<Colmena>
) : RecyclerView.Adapter<ColmenaAdapter.ColmenaViewHolder>() {

    class ColmenaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val codigo: TextView =
            itemView.findViewById(R.id.txtCodigoColmena)

        val tipo: TextView =
            itemView.findViewById(R.id.txtTipoColmena)

        val apiario: TextView =
            itemView.findViewById(R.id.txtApiarioColmena)

        val estado: TextView =
            itemView.findViewById(R.id.txtEstadoColmena)

        val editar: Button =
            itemView.findViewById(R.id.btnEditarColmena)

        val eliminar: Button =
            itemView.findViewById(R.id.btnEliminarColmena)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ColmenaViewHolder {

        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.i_colmena, parent, false)

        return ColmenaViewHolder(vista)
    }

    override fun onBindViewHolder(
        holder: ColmenaViewHolder,
        position: Int
    ) {

        val colmena = listaColmenas[position]

        holder.codigo.text = colmena.codigo
        holder.tipo.text = "Tipo: ${colmena.tipo}"
        holder.apiario.text = "Apiario: ${colmena.apiario}"
        holder.estado.text = "Estado: ${colmena.estado}"

        holder.editar.setOnClickListener {

            val posicion = holder.bindingAdapterPosition

            if (posicion != RecyclerView.NO_POSITION) {

                val intent = Intent(
                    holder.itemView.context,
                    FormColmenasActivity::class.java
                )

                intent.putExtra("indiceColmena", posicion)

                holder.itemView.context.startActivity(intent)
            }
        }

        holder.eliminar.setOnClickListener {

            val posicion = holder.bindingAdapterPosition

            if (posicion != RecyclerView.NO_POSITION) {

                AlertDialog.Builder(holder.itemView.context)
                    .setTitle("Eliminar colmena")
                    .setMessage("¿Estás seguro de que quieres eliminar esta colmena?")
                    .setNegativeButton("Cancelar", null)
                    .setPositiveButton("Eliminar") { _, _ ->

                        listaColmenas.removeAt(posicion)
                        notifyItemRemoved(posicion)
                    }
                    .show()
            }
        }
    }

    override fun getItemCount(): Int {
        return listaColmenas.size
    }
}