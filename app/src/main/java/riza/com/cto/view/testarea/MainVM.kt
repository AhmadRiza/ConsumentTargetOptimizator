package riza.com.cto.view.testarea

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import riza.com.cto.data.db.AppDB
import riza.com.cto.data.db.Area
import riza.com.cto.view.selectarea.SelectAreaRepository

/**
 * Created by riza@deliv.co.id on 1/21/20.
 */

class MainVM(application: Application) : AndroidViewModel(application) {

    private val repository: SelectAreaRepository
    val areas: LiveData<List<Area>>

    init {
        val db = AppDB.getDatabase(application, viewModelScope)
        repository = SelectAreaRepository(db.mainDao())

        areas = repository.areas
    }

    fun deleteArea(area: Area) = viewModelScope.launch {
        repository.delete(area)
    }


}
