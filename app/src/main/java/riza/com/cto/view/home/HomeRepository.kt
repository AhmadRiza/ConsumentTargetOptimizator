package riza.com.cto.view.home

import riza.com.cto.data.api.MainAPI
import riza.com.cto.support.base.BaseRepository

/**
 * Created by riza@deliv.co.id on 5/8/20.
 */

class HomeRepository(
    private val api: MainAPI
) : BaseRepository() {

    suspend fun getAllPromo() = callAPI { api.getAllPromoAsync() }

}