package com.darkfan.repo.storage.mmkv

import android.content.Context
import com.tencent.mmkv.MMKV

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 23/04/2022 19:28
 * @desc mmkv 存储.
 */
class MmkvStorage(val mmkv: MMKV) {

    companion object {

        @JvmStatic
        fun initMmkv(context: Context) {
            MMKV.initialize(context)
        }

        private val _mmkvStorage: MmkvStorage by lazy {
            MmkvStorage(MMKV.defaultMMKV())
        }

        val mmkvStorage: MmkvStorage
            get() = _mmkvStorage
    }

    fun putValue(key: String, value: Boolean): Boolean {
        return mmkv.encode(key, value)
    }

    fun getBoolValue(key: String, value: Boolean): Boolean {
        return mmkv.decodeBool(key, value)
    }

    fun putValue(key: String, value: Int): Boolean {
        return mmkv.encode(key, value)
    }

    fun getIntValue(key: String, value: Int): Int {
        return mmkv.decodeInt(key, value)
    }

    fun putValue(key: String, value: Long): Boolean {
        return mmkv.encode(key, value)
    }

    fun getLongValue(key: String, value: Long): Long {
        return mmkv.decodeLong(key, value)
    }

    fun putValue(key: String, value: Float): Boolean {
        return mmkv.encode(key, value)
    }

    fun getFloatValue(key: String, value: Float): Float {
        return mmkv.decodeFloat(key, value)
    }

    fun putValue(key: String, value: Double): Boolean {
        return mmkv.encode(key, value)
    }

    fun getDoubleValue(key: String, value: Double): Double {
        return mmkv.decodeDouble(key, value)
    }

    fun putValue(key: String, value: String): Boolean {
        return mmkv.encode(key, value)
    }

    fun getStringValue(key: String, value: String): String {
        return mmkv.decodeString(key, value) ?: ""
    }

    fun putValue(key: String, value: ByteArray): Boolean {
        return mmkv.encode(key, value)
    }

    fun getByteArray(key: String, value: ByteArray): ByteArray {
        return mmkv.decodeBytes(key, value) ?: byteArrayOf()
    }

    fun removeValue(key: String) {
        mmkv.removeValueForKey(key)
    }

    fun clearData() {
        mmkv.clearAll()
    }
}