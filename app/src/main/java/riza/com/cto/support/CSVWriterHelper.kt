package riza.com.cto.support

import android.content.Context
import android.os.Environment
import com.google.android.gms.maps.model.LatLng
import riza.com.cto.core.Polygon
import riza.com.cto.model.UserIds
import java.io.File
import java.io.FileWriter
import java.util.*

/**
 * Created by riza@deliv.co.id on 6/17/20.
 */

class CSVWriterHelper(context: Context) {


    private val filePath by lazy { context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS) }
    private val time by lazy { printCurrentTime(Calendar.getInstance()) }

    fun writePoly(poly: Polygon) {

        try {

            val targetFilePoly = File(filePath, "${time}-${poly.name}.csv")
            val fw = FileWriter(targetFilePoly)

            fw.append("No")
            fw.append(",")

            fw.append("Lat")
            fw.append(",")

            fw.append("Lon")
            fw.append(",")

            fw.append("\n")

            poly.points.forEachIndexed { index, it ->

                fw.append("${index + 1}")
                fw.append(",")

                fw.append(it.y.toFloat().toString())
                fw.append(",")

                fw.append(it.x.toFloat().toString())
                fw.append(",")

                fw.append("\n")
            }

            fw.flush()
        } catch (e: Exception) {
            e.printDebugLog()
        }

    }

    fun writeCheckResult(data: List<Pair<LatLng, Boolean>>, algorithm: String) {

        try {

            val targetFile = File(filePath, "${time}-test-$algorithm.csv")
            val fw = FileWriter(targetFile)

            fw.append("No")
            fw.append(",")

            fw.append("Lat")
            fw.append(",")

            fw.append("Lon")
            fw.append(",")

            fw.append("Inside")
            fw.append(",")

            fw.append("\n")

            data.forEachIndexed { index, it ->

                fw.append("${index + 1}")
                fw.append(",")

                fw.append("${it.first.latitude.toFloat()}")
                fw.append(",")

                fw.append("${it.first.longitude.toFloat()}")
                fw.append(",")

                fw.append("${it.second}")
                fw.append(",")

                fw.append("\n")

            }

            fw.flush()

        } catch (e: Exception) {
            e.printDebugLog()
        }


    }

    fun writeUserResult(data: List<UserIds>) {

        try {

            val targetFile = File(filePath, "${time}-users.csv")
            val fw = FileWriter(targetFile)

            fw.append("No")
            fw.append(",")

            fw.append("Name")
            fw.append(",")

            fw.append("Targetted")
            fw.append(",")


            fw.append("\n")

            data.forEachIndexed { index, it ->

                fw.append("${index + 1}")
                fw.append(",")

                fw.append("${it.name.replace(" [targeted]", "")}")
                fw.append(",")

                fw.append("${it.name.contains(" [targeted]")}")
                fw.append(",")

                fw.append("\n")

            }

            fw.flush()

        } catch (e: Exception) {
            e.printDebugLog()
        }


    }


}