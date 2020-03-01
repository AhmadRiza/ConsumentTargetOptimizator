package riza.com.cto.view.check

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import riza.com.cto.core.Polygon
import riza.com.cto.core.Point
import riza.com.cto.core.PointInclusion
import riza.com.cto.core.PolygonUtils
import riza.com.cto.data.db.AppDB
import riza.com.cto.data.db.Area
import riza.com.cto.support.debugLog
import java.lang.Math.random
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Created by riza@deliv.co.id on 1/21/20.
 */

class CheckVM(application: Application) : AndroidViewModel(application) {

    val polygonData = MutableLiveData<ArrayList<LatLng>>()
    val centroid = MutableLiveData<LatLng>()
    val nUser = MutableLiveData<Int>()
    val radius = MutableLiveData<Int>()
    val milis = MutableLiveData<Long>()
    val listTest = MutableLiveData<ArrayList<Pair<LatLng, Boolean>>>()


    private var mPolygon: Polygon? = null
    private var isUsingWN = true
    private val repository: CheckRepository
    private val geofencing: PointInclusion

    init {
        val db = AppDB.getDatabase(application, viewModelScope)
        repository = CheckRepository(db.mainDao())
        geofencing = PointInclusion()
    }

    fun setPolygonData(area: Area) = viewModelScope.launch {

        val listType = object : TypeToken<List<Point>>() {}.type
        val points = Gson().fromJson<List<Point>>(area.points, listType)

        mPolygon = Polygon(area.name, ArrayList(points))

        val data = arrayListOf<LatLng>()

        points.forEach {

            data.add(
                LatLng(it.y, it.x)
            )

        }

        polygonData.postValue(data)

        val center = PolygonUtils.calculateCentroid(points)
        centroid.postValue(LatLng(center.y, center.x))

        radius.postValue(100)
        nUser.postValue(100)

    }

    fun setNUser(n: Int) {
        nUser.value = n
    }

    fun setRadius(r: Int) {
        radius.value = r
    }

    fun setisUsingWN(isUsingWN: Boolean) {
        this.isUsingWN = isUsingWN
    }

    val displayRadius = Transformations.map(radius) {
        PolygonUtils.degreeToMeter(PolygonUtils.getOuterRadius(it.toDouble(), mPolygon!!))
    }

    fun singleTest() = viewModelScope.launch {

        val it = centroid.value!!

        val result = arrayListOf<Pair<LatLng, Boolean>>()
        val radius = PolygonUtils.getOuterRadius((radius.value ?: 0).toDouble(), mPolygon!!)

        var time = 0L

        for (i in 0..(nUser.value ?: 0)) {

            //random point in circle

            val a = random() * 2 * PI
            val r = radius * sqrt(random())

            val x = r * cos(a)
            val y = r * sin(a)


            val now = System.currentTimeMillis()
            val isInside = if (isUsingWN) {
                geofencing.analyzePointByWN(mPolygon!!, Point(it.longitude + x, it.latitude + y))
            } else {
                geofencing.analyzePointByCN(mPolygon!!, Point(it.longitude + x, it.latitude + y))
            }
            val end = System.currentTimeMillis()

            time += (end - now)

            result.add(
                Pair(LatLng(it.latitude + y, it.longitude + x), isInside)
            )

        }

        debugLog("time = $time")

        milis.postValue(
            time
        )

        listTest.postValue(result)

    }


}
