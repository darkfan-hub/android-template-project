package com.darkfan.repo.http.api

import com.darkfan.repo.http.HttpClient
import com.darkfan.repo.storage.api.MemoryStorageApi

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 23/04/2022 22:24
 * @desc 网络请求 api.
 */
object HttpApi {

    fun <T> obtainRetrofitService(service: Class<T>): T {
        var retrofitService: T?

        retrofitService = MemoryStorageApi.getValue(service.name) as T?

        if (retrofitService == null) {
            synchronized(service) {
                if (retrofitService == null) {
                    retrofitService = HttpClient.createService(service)

                    if (retrofitService != null) {
                        MemoryStorageApi.putValue(service.name, retrofitService!!)
                    }
                }
            }
        }

        return if (retrofitService == null) {
            HttpClient.createService(service)
        } else {
            retrofitService!!
        }
    }
}