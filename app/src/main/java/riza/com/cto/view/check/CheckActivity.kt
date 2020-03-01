package riza.com.cto.view.check

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.activity_check.*
import riza.com.cto.R
import riza.com.cto.core.PolygonUtils
import riza.com.cto.data.db.Area
import riza.com.cto.support.debugLog
import riza.com.cto.support.getCompatColor

class CheckActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private var mPolygon : Polygon? = null
    private var mMarkers = arrayListOf<Marker>()

    private val vm by lazy { ViewModelProvider(this).get(CheckVM::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun receiveExtra() {
        intent?.getParcelableExtra<Area>("area")?.let {
            vm.setPolygonData(it)
        }
    }

    private fun initView() {

        btn_back?.setOnClickListener { onBackPressed() }

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

        vm.centroid.observe(this, Observer {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(it, 18f))

        })

        vm.displayRadius.observe(this, Observer {
            createCircle(vm.centroid.value!!, it)
        })

        vm.listTest.observe(this, Observer {
            it.forEach { addMarkerOn(it) }
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        initView()
        initObserver()
        receiveExtra()
    }

    private fun createPolygon(data: List<LatLng>) {

        mPolygon = mMap.addPolygon(

            PolygonOptions()
                .fillColor(getCompatColor(R.color.polygonColor))
                .strokeWidth(1f)
                .addAll(data)
                .strokeJointType(JointType.BEVEL)

        )
        debugLog(mPolygon)
    }

    private fun createBounding(data: List<LatLng>) {

        mPolygon = mMap.addPolygon(

            PolygonOptions()
                .fillColor(Color.TRANSPARENT)
                .strokeWidth(1f)
                .addAll(data)
                .strokeJointType(JointType.BEVEL)

        )

        debugLog(mPolygon)
    }

    private fun addMarkerOn(p: LatLng) {

        val marker = mMap.addMarker(
            MarkerOptions().position(p).icon(BitmapDescriptorFactory.fromResource(R.drawable.placeholder)).title(p.toString())
        )
        mMarkers.add(marker)

    }

    private fun createCircle(center: LatLng, radius: Double){

        mMap.addCircle(CircleOptions()
            .center(center)
            .radius(radius)
            .fillColor(Color.TRANSPARENT)
            .strokeWidth(1f)
        )

    }


}
