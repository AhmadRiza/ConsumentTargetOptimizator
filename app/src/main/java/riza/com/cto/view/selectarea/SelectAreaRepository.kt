package riza.com.cto.view.selectarea

import riza.com.cto.data.api.MainAPI
import riza.com.cto.support.base.BaseRepository

/**
 * Created by riza@deliv.co.id on 2/29/20.
 */

class SelectAreaRepository(
    private val api: MainAPI
) : BaseRepository() {


    suspend fun getAllArea() = callAPI { api.getAllAreaAsync() }



}