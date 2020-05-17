package riza.com.cto.view.net.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import riza.com.cto.data.api.APIServiceFactory
import riza.com.cto.data.api.MainAPI
import riza.com.cto.model.AreaPromo
import riza.com.cto.model.Promo
import riza.com.cto.model.UserIds

/**
 * Created by riza@deliv.co.id on 1/21/20.
 */

class HomeVM(application: Application) : AndroidViewModel(application) {

    private val repository = HomeRepository(
        APIServiceFactory.createMain(MainAPI::class.java)
    )

    val promos = MutableLiveData<List<Promo>>()
    val loading = MutableLiveData<Boolean>()


    val totalPromo = Transformations.map(promos) {
        it.size
    }

    val totalUser = Transformations.map(promos) {
        val listUser = arrayListOf<UserIds>()
        it.forEach {
            listUser.addAll(it.users)
        }
        listUser.groupBy { it.id }.size
    }

    val totalArea = Transformations.map(promos) {
        val areas = arrayListOf<AreaPromo>()
        it.forEach { areas.addAll(it.areas) }
        areas.groupBy { it.id }.size
    }


    fun getAllPromo() = viewModelScope.launch {

        loading.postValue(true)

        val response = repository.getAllPromo()

        if (response.success) {
            response.data?.let {
                promos.postValue(it)
            }

        }

        loading.postValue(false)

    }


}
