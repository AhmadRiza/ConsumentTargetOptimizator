package riza.com.cto.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert

/**
 * Created by riza@deliv.co.id on 2/29/20.
 */

@Dao
abstract class MainDao {

    @Insert
    abstract suspend fun insertArea(area: Area): Long

    @Delete
    abstract suspend fun delete(area: Area): Int


}