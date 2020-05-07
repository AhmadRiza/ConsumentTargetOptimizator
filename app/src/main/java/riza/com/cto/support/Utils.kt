package riza.com.cto.support

import android.content.Context
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import riza.com.cto.BuildConfig

/**
 * Created by riza@deliv.co.id on 2/28/20.
 */


fun debugLog(message: Any?) {
    if (BuildConfig.DEBUG) Log.e("debugLog", message.toString())
}

fun Context.getCompatColor(resource: Int) = ContextCompat.getColor(this, resource)

fun <T> gsontoList(string: String): List<T> {
    val listType = object : TypeToken<List<T>>() {}.type
    return Gson().fromJson<List<T>>(string, listType)
}

fun View.visible(){
    visibility = View.VISIBLE
}

fun View.gone(){
    visibility = View.GONE
}

fun View.invisible(){
    visibility = View.INVISIBLE
}

fun Exception.printDebugLog(){
    debugLog(message)
    if(BuildConfig.DEBUG) printStackTrace()
}