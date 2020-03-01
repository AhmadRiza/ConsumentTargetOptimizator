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
import riza.com.cto.core.PolygonUtils
import riza.com.cto.data.db.AppDB
import riza.com.cto.data.db.Area
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

    val testPoints = MutableLiveData<ArrayList<Pair<LatLng, Boolean>>>()

    val displayRadius = MutableLiveData<Double>()

    private var mPolygon: Polygon? = null

    private val repository: CheckRepository

    init {
        val db = AppDB.getDatabase(application, viewModelScope)
        repository = CheckRepository(db.mainDao())
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

        displayRadius.postValue(
            PolygonUtils.degreeToMeter(PolygonUtils.getOuterRadius(100.0, mPolygon!!))
        )

    }

    val listTest = Transformations.map(centroid){

        val result = arrayListOf<LatLng>()
        val radius = PolygonUtils.getOuterRadius(100.0, mPolygon!!)

        for(i in 0..5){

            val a = random() * 2 * PI
            val r = radius * sqrt(random())

            val x = r * cos(a)
            val y = r * sin(a)

            result.add(
                LatLng(it.latitude+y, it.longitude+x)
            )

        }



        result


    }




}
