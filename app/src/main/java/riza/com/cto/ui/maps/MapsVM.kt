package riza.com.cto.ui.maps

import android.app.Application
import androidx.lifecycle.*
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch
import riza.com.cto.core.Point
import riza.com.cto.support.debugLog

/**
 * Created by riza@deliv.co.id on 1/21/20.
 */

class MapsVM(application: Application) : AndroidViewModel(application) {

    val polygonData = MutableLiveData<ArrayList<LatLng>>()

    val polygonOutput: LiveData<ArrayList<Point>> = Transformations.map(polygonData) {

        val list = arrayListOf<Point>()
        it.forEach { p ->
            list.add(Point(p.longitude, p.latitude))
        }

        list
    }


    fun addPoint(p: LatLng) = viewModelScope.launch {

        val point = polygonData.value ?: arrayListOf()

        point.add(p)

        polygonData.postValue(point)

    }

    fun clear() = viewModelScope.launch {

        val point = polygonData.value ?: arrayListOf()

        point.clear()

        polygonData.postValue(point)

    }

    fun undo() = viewModelScope.launch {

        val point = polygonData.value ?: arrayListOf()

        if(point.isNotEmpty()) point.removeAt(point.lastIndex)

        polygonData.postValue(point)

    }



}
