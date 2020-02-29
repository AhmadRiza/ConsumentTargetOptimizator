package riza.com.cto.view.check

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import riza.com.cto.core.Point
import riza.com.cto.core.PolygonUtils
import riza.com.cto.data.db.AppDB
import riza.com.cto.data.db.Area
import riza.com.cto.support.gsontoList

/**
 * Created by riza@deliv.co.id on 1/21/20.
 */

class CheckVM(application: Application) : AndroidViewModel(application) {

    val polygonData = MutableLiveData<ArrayList<LatLng>>()
    val centroid = MutableLiveData<LatLng>()

    private val repository: CheckRepository

    init {
        val db = AppDB.getDatabase(application, viewModelScope)
        repository = CheckRepository(db.mainDao())
    }

    fun setPolygonData(area: Area) = viewModelScope.launch {

        val listType = object : TypeToken<List<Point>>() {}.type
        val points = Gson().fromJson<List<Point>>(area.points, listType)

        val data = arrayListOf<LatLng>()

        points.forEach {

            data.add(
                LatLng(it.y, it.x)
            )

        }

        polygonData.postValue(data)


        val center = PolygonUtils.calculateCentroid(points)
        centroid.postValue(LatLng(center.y, center.x))

    }


}
