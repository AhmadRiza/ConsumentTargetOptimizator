package riza.com.cto.view.testarea

import riza.com.cto.data.db.Area
import riza.com.cto.data.db.MainDao

/**
 * Created by riza@deliv.co.id on 2/29/20.
 */

class MainRepository(val db: MainDao) {

    val areas = db.loadAllArea()
    suspend fun delete(area: Area) = db.delete(area)

}