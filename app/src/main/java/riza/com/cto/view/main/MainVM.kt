package riza.com.cto.view.main

import android.app.Application
import androidx.lifecycle.*
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson
import kotlinx.coroutines.launch
import riza.com.cto.core.Point
import riza.com.cto.data.db.AppDB
import riza.com.cto.data.db.Area
import riza.com.cto.support.debugLog

/**
 * Created by riza@deliv.co.id on 1/21/20.
 */

class MainVM(application: Application) : AndroidViewModel(application) {

    private val repository: MainRepository
    val areas : LiveData<List<Area>>

    init {
        val db = AppDB.getDatabase(application, viewModelScope)
        repository = MainRepository(db.mainDao())

        areas = repository.areas
    }

    fun deleteArea(area: Area) = viewModelScope.launch {
        repository.delete(area)
    }




}
