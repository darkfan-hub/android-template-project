package com.darkfan.repo.storage.memory

import androidx.collection.LruCache

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 25/04/2022 00:12
 * @desc 内存存储.
 */
class MemoryStorage {

    companion object {
        private val max_memory_size = (Runtime.getRuntime().maxMemory() / 1024).toInt() / 8

        private val memory_cache: LruCache<String, Any> by lazy {
            LruCache<String, Any>(max_memory_size)
        }

        private val _memory_storage: MemoryStorage by lazy { MemoryStorage() }

        val memory_storage: MemoryStorage
            get() = _memory_storage
    }

    fun putValue(key: String, value: Any) {
        memory_cache.put(key, value)
    }

    fun getValue(key: String): Any? {
        return memory_cache.get(key)
    }

    fun removeValue(key: String) {
        memory_cache.remove(key)
    }

    fun clear() {
        memory_cache.evictAll()
    }
}