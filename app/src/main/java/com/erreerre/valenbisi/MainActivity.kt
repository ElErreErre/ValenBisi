package com.erreerre.valenbisi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rvEstaciones)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Leer CSV y crear lista de estaciones
        val estaciones = leerCSV()
        val adapter = EstacionAdapter(estaciones)
        recyclerView.adapter = adapter
    }

    private fun leerCSV(): List<Estacion> {
        val estaciones = mutableListOf<Estacion>()
        val inputStream = assets.open("valenbisi.csv")
        val reader = BufferedReader(InputStreamReader(inputStream))

        reader.readLine() // Saltar cabecera
        reader.forEachLine { linea ->
            val partes = linea.split(";")
            if (partes.size >= 10) {
                val direccion = partes[0]
                val bicis = partes[3].toIntOrNull() ?: 0
                val espacios = partes[4].toIntOrNull() ?: 0

                // geo_point_2d = [lat, lng]
                val geo = partes[9].trim().removeSurrounding("[", "]").split(",")
                val lat = geo.getOrNull(0)?.toDoubleOrNull() ?: 0.0
                val lng = geo.getOrNull(1)?.toDoubleOrNull() ?: 0.0

                estaciones.add(Estacion(direccion, bicis, espacios, lat, lng))
            }
        }

        reader.close()
        return estaciones
    }

}
