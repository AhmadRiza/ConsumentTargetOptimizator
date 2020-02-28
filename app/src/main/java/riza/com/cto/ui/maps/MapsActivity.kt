package riza.com.cto.ui.maps

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import kotlinx.android.synthetic.main.activity_maps.*
import riza.com.cto.R
import riza.com.cto.support.debugLog
import riza.com.cto.support.getCompatColor

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private var isDrawing = true
    private var mPolygon : Polygon? = null

    private val vm by lazy { ViewModelProvider(this).get(MapsVM::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        initView()
        initObserver()
    }

    private fun initView() {
        btn_clear?.setOnClickListener { vm.clear() }
        btn_undo?.setOnClickListener { vm.undo() }
    }

    private fun initObserver() {
        vm.polygonData.observe(this, Observer {
            debugLog(it)
            if(it.size >= 3){
                if(mPolygon == null) createPolygon(it)
                else mPolygon?.points = it

                mPolygon?.isVisible = true
            }else{
                mPolygon?.isVisible = false
            }
        })

        vm.polygonOutput.observe(this, Observer {
            debugLog(it)
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        mMap.setOnMapClickListener {
            if(isDrawing) addPolygonPoint(it)
        }


    }

    private fun addPolygonPoint(it: LatLng) {
        vm.addPoint(it)
    }

    private fun createPolygon(data: List<LatLng>) {

        mPolygon = mMap.addPolygon(

            PolygonOptions()
                .fillColor(getCompatColor(R.color.polygonColor))
                .strokeWidth(1f)
                .addAll(data)
        )

        mPolygon?.tag = "drawing"

        debugLog(mPolygon)
    }


}
