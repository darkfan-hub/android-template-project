package com.darkfan.repo.storage.api

import com.darkfan.repo.storage.memory.MemoryStorage

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 25/04/2022 00:45
 * @desc 内存存储 api.
 */
object MemoryRepository {

    fun putValue(key: String, value: Any) {
        MemoryStorage.memory_storage.putValue(key, value)
    }

    fun getValue(key: String): Any? {
        return MemoryStorage.memory_storage.getValue(key)
    }

    fun removeValue(key: String) {
        MemoryStorage.memory_storage.removeValue(key)
    }

    fun clear() {
        MemoryStorage.memory_storage.clear()
    }
}