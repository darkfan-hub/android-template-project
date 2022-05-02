package com.darkfan.repo.http

import com.darkfan.repo.http.gson.*
import com.darkfan.repo.http.log.LoggingInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.TypeAdapters
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigDecimal
import java.util.concurrent.TimeUnit

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 21/04/2022 18:53
 * @desc 网络请求client.
 */
object HttpClient {

    private const val call_timeout = 90L
    private const val connect_timeout = 60L

    private val _okHttpClient: OkHttpClient by lazy {
        val okBuilder = OkHttpClient.Builder()
        // 整个流程耗费的超时时间
        okBuilder.callTimeout(call_timeout, TimeUnit.SECONDS)
        // 三次握手 + SSL建立耗时
        okBuilder.connectTimeout(connect_timeout, TimeUnit.SECONDS)
        // source读取耗时 | rawSocket读取耗时
        okBuilder.readTimeout(call_timeout, TimeUnit.SECONDS)
        // sink写入耗时
        okBuilder.writeTimeout(call_timeout, TimeUnit.SECONDS)

        // 开发模式下添加日志打印
        if (BuildConfig.IS_DEV_MODE) {
            okBuilder.addInterceptor(LoggingInterceptor.Builder().build())
        }

        // 添加业务拦截器.
        okBuilder.addInterceptor(BusinessInterceptor())

        okBuilder.build()
    }

    @JvmStatic
    val okHttpClient: OkHttpClient
        get() = _okHttpClient

    private val _retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BuildConfig.HOST_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(_gson))
            .build()
    }

    private val retrofit: Retrofit
        get() = _retrofit

    @JvmStatic
    fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }

    private val _gson: Gson by lazy {
        val gsonBuilder = GsonBuilder()
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    String::class.java,
                    StringTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Boolean::class.java,
                    BooleanTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    BigDecimal::class.java,
                    BigDecimalTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Double::class.java,
                    DoubleTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Float::class.java,
                    FloatTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Int::class.java,
                    IntegerTypeAdapter()
                )
            )
            .registerTypeAdapterFactory(
                TypeAdapters.newFactory(
                    Long::class.java,
                    LongTypeAdapter()
                )
            )
            // 支持序列化null的参数
            .serializeNulls()
            // 支持将序列化key为object的map,默认只能序列化key为string的map
            .enableComplexMapKeySerialization()

        gsonBuilder.create()
    }

    @JvmStatic
    val gson: Gson
        get() = _gson
}