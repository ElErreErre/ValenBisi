package com.erreerre.valenbisi

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DetalleActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private lateinit var latLng: LatLng
    private var markerTitle: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle2)

        // Recibir datos de la estación
        val direccion = intent.getStringExtra("direccion")
        val bicis = intent.getIntExtra("bicis",0)
        val espacios = intent.getIntExtra("espacios",0)
        val lat = intent.getDoubleExtra("lat",0.0)
        val lng = intent.getDoubleExtra("lng",0.0)

        // Mostrar info en TextViews
        findViewById<TextView>(R.id.tvDireccion).text = direccion
        findViewById<TextView>(R.id.tvBicis).text = "Bicis: $bicis"
        findViewById<TextView>(R.id.tvEspacios).text = "Espacios: $espacios"

        // Preparar mapa
        latLng = LatLng(lat, lng)
        markerTitle = direccion ?: "Estación"

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        googleMap.addMarker(MarkerOptions().position(latLng).title(markerTitle))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15f))
    }
}