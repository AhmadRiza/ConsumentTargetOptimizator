package riza.com.cto.view.maps

import okhttp3.FormBody
import riza.com.cto.data.net.MainAPI
import riza.com.cto.support.base.APIRepository

/**
 * Created by riza@deliv.co.id on 2/29/20.
 */

class MapsRepository(val api: MainAPI) : APIRepository(){



    suspend fun getCoordinate(body: FormBody){
        val result = callAPI { api.updateUserCoordinate(body) }

    }


}