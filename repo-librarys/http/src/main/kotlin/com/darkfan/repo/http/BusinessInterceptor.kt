package com.darkfan.repo.http

import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 24/04/2022 11:22
 * @desc 业务拦截器.
 */
class BusinessInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()

        // 添加业务需求.

        return chain.proceed(builder.build())
    }
}