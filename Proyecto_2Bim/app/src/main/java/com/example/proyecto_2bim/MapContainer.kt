package com.example.proyecto_2bim

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*

class MapContainer : AppCompatActivity() , OnMapReadyCallback,
    GoogleMap.OnCameraIdleListener,
        GoogleMap.OnCameraMoveCanceledListener,
        GoogleMap.OnCameraMoveListener,
        GoogleMap.OnCameraMoveStartedListener,
        GoogleMap.OnPolygonClickListener,
        GoogleMap.OnPolylineClickListener,
        GoogleMap.OnMarkerClickListener
{

    private lateinit var mMap: GoogleMap
    var permisos=false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_container)
        getPermisos();
        val mapFrag=supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment;
        mapFrag.getMapAsync(this);

        val btnCarolina=findViewById<Button>(R.id.btn_caroline)
        btnCarolina.setOnClickListener {
            val quito=LatLng(-0.09656470649521982, -78.48981401820942)
            val tit="Quito Tennis"
            val zoom=17f

            centerCamera(quito,zoom)
            addMarker(quito,tit)
        }
    }

    //
    override fun onMapReady(googleMap: GoogleMap?) {
        if(googleMap!=null){
            mMap=googleMap
            mMap
                .setOnPolygonClickListener {
                    Toast.makeText(this, "CALLBACK POLYGON", Toast.LENGTH_SHORT).show()
                }
            mapConfiguraction(googleMap)
            val quicentro=LatLng(-0.176125,-78.480208)
            val tit="Quicentro"
            val zoom=17f
            addMarker(quicentro,tit)
            centerCamera(quicentro,zoom)

            //LINEA
            val linea=googleMap
                .addPolyline(
                    PolylineOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.1742655167137368, -78.48048152030225),
                            LatLng(-0.17734920426778106, -78.47805846173097),
                            LatLng(-0.18113064772397244, -78.47922356073752)
                            )
                )
            linea.tag="Line-1"

            //POLIGONO
            val poligono=googleMap
                .addPolygon(
                    PolygonOptions()
                        .clickable(true)
                        .add(
                            LatLng(-0.17295455279519015, -78.48400659876437),
                            LatLng(-0.17820769420188293, -78.48821730745468),
                            LatLng(-0.17315769773684664, -78.49749380287749)
                        )
                )
            poligono.fillColor=-0xc771c4
            poligono.tag="Figure 1"


        }
    }

    fun addMarker(latLng:LatLng,tit:String){
        mMap.addMarker(
            MarkerOptions()
                .position(latLng)
                .title(tit)
        )
    }

    fun centerCamera(latLng: LatLng,zoom:Float=10f){
        mMap.moveCamera(
            CameraUpdateFactory
                .newLatLngZoom(latLng,zoom)
        )
    }

    fun mapConfiguraction(map:GoogleMap){
        val context=this.applicationContext
        with(map){
            val permisosFineLocation=ContextCompat
                .checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
            val permisos=permisosFineLocation==PackageManager.PERMISSION_GRANTED
            if(permisos){
                map?.isMyLocationEnabled=true
            }
            uiSettings.isZoomControlsEnabled  =true
            uiSettings.isMyLocationButtonEnabled=true
        }

    }

    fun getPermisos(){
        val permisoFineLocation=ContextCompat
            .checkSelfPermission(
                this.applicationContext,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        val permisos=permisoFineLocation==PackageManager.PERMISSION_GRANTED
        if(permisos){
            Toast.makeText(this, "Tiene permiso FINE LOCATION", Toast.LENGTH_SHORT).show()
            this.permisos=true
        }else{
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        }

    }

    //OVERRIDES
    override fun onCameraIdle() {
        TODO("Not yet implemented")
    }

    override fun onCameraMoveCanceled() {
        TODO("Not yet implemented")
    }

    override fun onCameraMove() {
        Toast.makeText(this, "CALLBACK", Toast.LENGTH_SHORT).show()
    }

    override fun onCameraMoveStarted(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onPolygonClick(p0: Polygon?) {
        Toast.makeText(this, "CALLBACK POLYGON", Toast.LENGTH_SHORT).show()
    }

    override fun onPolylineClick(p0: Polyline?) {
        TODO("Not yet implemented")
    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        TODO("Not yet implemented")
    }


}