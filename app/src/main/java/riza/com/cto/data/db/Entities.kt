package riza.com.cto.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by riza@deliv.co.id on 2/29/20.
 */


@Entity
data class Area(
    @PrimaryKey(autoGenerate = true) val id: Long,
    var name: String,
    val points: String
)