package com.darkfan.repo.storage.api

import android.content.Context
import com.darkfan.repo.storage.mmkv.MmkvStorage

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 23/04/2022 19:28
 * @desc mmkv 存储 api.
 */
object MmkvStorageApi {

    /**
     * 初始化 mmkv.
     */
    @JvmStatic
    fun initMmkv(context: Context) {
        MmkvStorage.initMmkv(context)
    }

    /**
     * 添加 mmkv 存储内容.
     */
    @JvmStatic
    fun putData(key: String, value: Any) {
        when (value) {
            is Boolean -> MmkvStorage.mmkvStorage.putValue(key, value)
            is Int -> MmkvStorage.mmkvStorage.putValue(key, value)
            is Long -> MmkvStorage.mmkvStorage.putValue(key, value)
            is Float -> MmkvStorage.mmkvStorage.putValue(key, value)
            is Double -> MmkvStorage.mmkvStorage.putValue(key, value)
            is String -> MmkvStorage.mmkvStorage.putValue(key, value)
            is ByteArray -> MmkvStorage.mmkvStorage.putValue(key, value)
        }
    }

    /**
     * 获取 bool 类型存储内容.
     */
    @JvmStatic
    fun getBoolValue(key: String, defaultValue: Boolean = false): Boolean {
        return MmkvStorage.mmkvStorage.getBoolValue(key, defaultValue)
    }

    /**
     * 获取 int 类型存储内容.
     */
    @JvmStatic
    fun getIntValue(key: String, defaultValue: Int = 0): Int {
        return MmkvStorage.mmkvStorage.getIntValue(key, defaultValue)
    }

    /**
     * 获取 long 类型存储内容.
     */
    @JvmStatic
    fun getLongValue(key: String, defaultValue: Long = 0): Long {
        return MmkvStorage.mmkvStorage.getLongValue(key, defaultValue)
    }

    /**
     * 获取 float 类型存储内容.
     */
    @JvmStatic
    fun getFloatValue(key: String, defaultValue: Float = 0f): Float {
        return MmkvStorage.mmkvStorage.getFloatValue(key, defaultValue)
    }

    /**
     * 获取 double 类型存储内容.
     */
    @JvmStatic
    fun getDoubleValue(key: String, defaultValue: Double = 0.0): Double {
        return MmkvStorage.mmkvStorage.getDoubleValue(key, defaultValue)
    }

    /**
     * 获取 string 类型存储内容.
     */
    @JvmStatic
    fun getStringValue(key: String, defaultValue: String = ""): String {
        return MmkvStorage.mmkvStorage.getStringValue(key, defaultValue)
    }

    /**
     * 获取 bytearray 类型存储内容.
     */
    @JvmStatic
    fun getByteArrayValue(key: String, defaultValue: ByteArray = byteArrayOf()): ByteArray {
        return MmkvStorage.mmkvStorage.getByteArray(key, defaultValue)
    }

    /**
     * 移除 mmkv 存储内容.
     */
    @JvmStatic
    fun removeData(key: String) {
        MmkvStorage.mmkvStorage.removeValue(key)
    }

    /**
     * 清除 mmkv 存储内容.
     */
    @JvmStatic
    fun clearData() {
        MmkvStorage.mmkvStorage.clearData()
    }
}