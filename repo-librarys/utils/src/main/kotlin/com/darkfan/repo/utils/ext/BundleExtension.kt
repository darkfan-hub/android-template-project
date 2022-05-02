package com.darkfan.repo.utils.ext

import android.os.Bundle
import android.os.Parcelable
import java.io.Serializable

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 25/04/2022 01:03
 * @desc Bundle扩展.
 */

fun Bundle.put(vararg params: Pair<String, Any>): Bundle {
    params.forEach {
        val key = it.first

        when (val value = it.second) {
            is Int -> putInt(key, value)
            is IntArray -> putIntArray(key, value)
            is Long -> putLong(key, value)
            is LongArray -> putLongArray(key, value)
            is CharSequence -> putCharSequence(key, value)
            is String -> putString(key, value)
            is Float -> putFloat(key, value)
            is FloatArray -> putFloatArray(key, value)
            is Double -> putDouble(key, value)
            is DoubleArray -> putDoubleArray(key, value)
            is Char -> putChar(key, value)
            is CharArray -> putCharArray(key, value)
            is Short -> putShort(key, value)
            is ShortArray -> putShortArray(key, value)
            is Boolean -> putBoolean(key, value)
            is BooleanArray -> putBooleanArray(key, value)
            is Serializable -> putSerializable(key, value)
            is Parcelable -> putParcelable(key, value)
            is Bundle -> putAll(value)
            is Array<*> -> when {
                value.isArrayOf<Parcelable>() -> putParcelableArray(
                    key,
                    value as Array<out Parcelable>?
                )
            }
        }
    }
    return this
}

fun Bundle?.booleanExtra(key: String, defaultValue: Boolean = false): Boolean {
    return if (hasExtra(key)) {
        this!!.getBoolean(key, defaultValue)
    } else {
        defaultValue
    }
}

fun Bundle?.intExtra(key: String, defaultValue: Int = 0): Int {
    return if (hasExtra(key)) {
        this!!.getInt(key, defaultValue)
    } else {
        defaultValue
    }
}

fun Bundle?.longExtra(key: String, defaultValue: Long = 0): Long {
    return if (hasExtra(key)) {
        this!!.getLong(key, defaultValue)
    } else {
        defaultValue
    }
}

fun Bundle?.floatExtra(key: String, defaultValue: Float = 0F): Float {
    return if (hasExtra(key)) {
        this!!.getFloat(key, defaultValue)
    } else {
        defaultValue
    }
}

fun Bundle?.doubleExtra(key: String, defaultValue: Double = 0.0): Double {
    return if (hasExtra(key)) {
        this!!.getDouble(key, defaultValue)
    } else {
        defaultValue
    }
}

fun Bundle?.stringExtra(key: String, defaultValue: String = ""): String {
    return if (hasExtra(key)) {
        this!!.getString(key, defaultValue)
    } else {
        defaultValue
    }
}

fun Bundle?.hasExtra(key: String): Boolean {
    return this?.containsKey(key) ?: false
}