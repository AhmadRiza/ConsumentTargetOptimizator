package riza.com.cto.support

import android.content.Context
import android.util.Log
import androidx.core.content.ContextCompat
import org.jetbrains.anko.generated.commons.BuildConfig

/**
 * Created by riza@deliv.co.id on 2/28/20.
 */


fun debugLog(message: Any?) = Log.e("debugLog", message.toString())

fun Context.getCompatColor(resource: Int) = ContextCompat.getColor(this, resource)