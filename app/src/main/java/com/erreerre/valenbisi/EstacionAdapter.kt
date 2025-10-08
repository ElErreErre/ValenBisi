package com.erreerre.valenbisi

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView


class EstacionAdapter(private val estaciones: List<Estacion>) :
    RecyclerView.Adapter<EstacionAdapter.EstacionViewHolder>() {

    class EstacionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val direccion: TextView = view.findViewById(R.id.direccion)
        val bicis: TextView = view.findViewById(R.id.bicis)
        val espacios: TextView = view.findViewById(R.id.espacios)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EstacionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_estacion, parent, false)
        return EstacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstacionViewHolder, position: Int) {
        val estacion = estaciones[position]
        holder.direccion.text = estacion.direccion
        holder.bicis.text = "Bicis: ${estacion.bicisDisponibles}"
        holder.espacios.text = "Espacios: ${estacion.espaciosLibres}"

        // ▸ Aquí: cambiar color de la tarjeta
        val card = holder.itemView as MaterialCardView
        val color = when {
            estacion.bicisDisponibles == 0 -> Color.parseColor("#19183B")
            estacion.bicisDisponibles in 1..4 -> Color.parseColor("#708993")
            else -> Color.parseColor("#A1C2BD")
        }
        card.setCardBackgroundColor(color)

        // Click a DetalleActivity
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetalleActivity::class.java)
            intent.putExtra("direccion", estacion.direccion)
            intent.putExtra("bicis", estacion.bicisDisponibles)
            intent.putExtra("espacios", estacion.espaciosLibres)
            intent.putExtra("lat", estacion.latitud)
            intent.putExtra("lng", estacion.longitud)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = estaciones.size
}
