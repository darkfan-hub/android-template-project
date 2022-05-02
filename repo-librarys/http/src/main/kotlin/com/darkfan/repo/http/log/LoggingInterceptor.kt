package com.darkfan.repo.http.log

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Response
import java.io.IOException
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 24/04/2022 11:22
 * @desc 日志拦截器.
 */
class LoggingInterceptor private constructor(private val builder: Builder) : Interceptor {

    private val charset: Charset by lazy { Charset.forName("UTF-8") }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBody = request.body

        var rContentType: MediaType? = null
        if (requestBody != null) {
            rContentType = requestBody.contentType()
        }

        var rSubtype: String? = null
        if (rContentType != null) {
            rSubtype = rContentType.subtype
        }

        if (request.method == "GET") {
            Logger.printJsonRequest(builder, request)
        } else {
            if (subtypeIsNotFile(rSubtype)) {
                Logger.printJsonRequest(builder, request)
            } else {
                Logger.printFileRequest(builder, request)
            }
        }

        val st = System.nanoTime()
        val response = chain.proceed(request)

        val requestUrl = request.url
        val chainMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - st)
        val header = response.headers.toString()
        val code = response.code
        val isSuccessful = response.isSuccessful
        val responseBody = response.body
        val contentType = responseBody?.contentType()

        val subtype = contentType?.subtype ?: response.headers["Content-Type"] ?: ""

        if (subtypeIsNotFile(subtype)) {

            responseBody?.let {
                val source = it.source()
                source.request(Long.MAX_VALUE)
                val buffer = source.buffer
                val bodyString = Logger.getJsonString(buffer.clone().readString(charset))
                Logger.printJsonResponse(builder, chainMs, isSuccessful, code, header, bodyString, requestUrl)
            }
        } else {
            Logger.printFileResponse(builder, chainMs, isSuccessful, code, header, requestUrl)
        }

        return response
    }

    private fun subtypeIsNotFile(subtype: String?)  = subtype != null && (subtype.contains("json")
            || subtype.contains("xml")
            || subtype.contains("plain")
            || subtype.contains("html"))

    class Builder {

        var enableThreadName: Boolean = true
        var urlLength:Int = 128
        var lineLength:Int = 128

        /**
         * 是否打印线程名，默认会打印
         * @param enable print thread name, default = true
         *
         * @return Builder
         */
        fun printThreadName(enable: Boolean): Builder {
            this.enableThreadName = enable
            return this
        }

        fun lineLength(lineLength:Int): Builder {
            this.lineLength = lineLength
            return this
        }

        fun build() =  LoggingInterceptor(this)
    }
}