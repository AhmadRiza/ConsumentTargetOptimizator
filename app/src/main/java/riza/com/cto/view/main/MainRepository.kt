package riza.com.cto.view.main

import okhttp3.FormBody
import riza.com.cto.data.db.Area
import riza.com.cto.data.db.MainDao
import riza.com.cto.data.net.MainAPI
import riza.com.cto.support.base.APIRepository

/**
 * Created by riza@deliv.co.id on 2/29/20.
 */

class MainRepository(val db: MainDao){

    val areas = db.loadAllArea()
    suspend fun delete(area: Area) = db.delete(area)

}