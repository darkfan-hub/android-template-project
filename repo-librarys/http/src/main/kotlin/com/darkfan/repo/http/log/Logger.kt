package com.darkfan.repo.http.log

import com.darkfan.repo.log.api.LoggerApi
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.Request
import okio.Buffer
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 24/04/2022 11:22
 * @desc 业务拦截器.
 */
class Logger {

    companion object {

        private const val JSON_INDENT = 3
        private const val MAX_STRING_LENGTH = 4000
        private const val N = "\n"
        private const val T = "\t"

        private const val TOP_LEFT_CORNER = '╔'
        private const val BOTTOM_LEFT_CORNER = '╚'
        private const val DOUBLE_DIVIDER = "═════════════════════════════════════════════════"
        private val TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER
        private val BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER
        private val LINE_SEPARATOR = System.getProperty("line.separator")

        private fun String.isLineEmpty() =
            isEmpty() || N == this || T == this || this.trim { it <= ' ' }.isEmpty()

        private fun getDoubleSeparator() = "$LINE_SEPARATOR  $LINE_SEPARATOR"

        /**
         * 支持超长日志的打印
         */
        private fun printLog(logString: String) {
            // 判断日志是否 > 4000 行
            if (logString.length > MAX_STRING_LENGTH) {
                var i = 0
                while (i < logString.length) {
                    if (i + MAX_STRING_LENGTH < logString.length) {
                        LoggerApi.i(logString.substring(i, i + MAX_STRING_LENGTH))
                    } else {
                        LoggerApi.i(logString.substring(i, logString.length))
                    }

                    i += MAX_STRING_LENGTH
                }
            } else {
                LoggerApi.i(logString)
            }
        }

        @JvmStatic
        fun printJsonRequest(builder: LoggingInterceptor.Builder, request: Request) {
            val urlLength = builder.urlLength
            val lineLength = builder.lineLength
            val requestBody = request.body

            val requestString = StringBuilder().apply {
                append("  ")
                    .append(LINE_SEPARATOR)
                    .append(TOP_BORDER)
                    .append(LINE_SEPARATOR)
                    .append(
                        getRequest(
                            request,
                            builder.enableThreadName,
                            urlLength
                        )
                    )

                val header = request.headers.toString()

                if (!header.isLineEmpty()) {
                    append(" Headers:" + LINE_SEPARATOR + dotHeaders(header))
                }

                if (requestBody != null) {

                    val requestBodyString = " $LINE_SEPARATOR Body:$LINE_SEPARATOR"

                    val bodyString = bodyToString(request).split(LINE_SEPARATOR.toRegex())
                        .dropLastWhile { it.isEmpty() }.toTypedArray()

                    append(requestBodyString + logLines(bodyString, lineLength))
                }

                append(BOTTOM_BORDER)
            }.toString()

            LoggerApi.i(requestString)
        }

        @JvmStatic
        fun printFileRequest(builder: LoggingInterceptor.Builder, request: Request) {
            val urlLength = builder.urlLength
            val lineLength = builder.lineLength

            val requestString = StringBuilder().apply {

                append("  ")
                    .append(LINE_SEPARATOR)
                    .append(TOP_BORDER)
                    .append(LINE_SEPARATOR)
                    .append(getRequest(request, builder.enableThreadName, urlLength))

                val requestBodyString = " $LINE_SEPARATOR Body:$LINE_SEPARATOR"

                val binaryBodyString = binaryBodyToString(request).split(LINE_SEPARATOR.toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray()

                append(requestBodyString + logLines(binaryBodyString, lineLength))
                append(BOTTOM_BORDER)

            }.toString()

            LoggerApi.i(requestString)
        }

        @JvmStatic
        fun printJsonResponse(
            builder: LoggingInterceptor.Builder, chainMs: Long, isSuccessful: Boolean,
            code: Int, headers: String, bodyString: String, requestUrl: HttpUrl
        ) {

            val urlLength = builder.urlLength
            val lineLength = builder.lineLength

            val responseString = StringBuilder().apply {
                append("  ").append(LINE_SEPARATOR).append(TOP_BORDER).append(LINE_SEPARATOR)
                append(
                    getResponse(
                        headers,
                        chainMs,
                        code,
                        isSuccessful,
                        requestUrl,
                        builder.enableThreadName,
                        urlLength
                    )
                )

                val responseBody = " $LINE_SEPARATOR Body:$LINE_SEPARATOR"
                val bodyString = getJsonString(bodyString).split(LINE_SEPARATOR.toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray()

                append(responseBody + logLines(bodyString, lineLength))

                append(BOTTOM_BORDER)
            }.toString()

            printLog(responseString)
        }

        @JvmStatic
        fun printFileResponse(
            builder: LoggingInterceptor.Builder, chainMs: Long, isSuccessful: Boolean,
            code: Int, headers: String, requestUrl: HttpUrl
        ) {
            val urlLength = builder.urlLength

            val responseString = StringBuilder().apply {

                append("  ").append(LINE_SEPARATOR).append(TOP_BORDER).append(LINE_SEPARATOR)
                append(
                    getResponse(
                        headers,
                        chainMs,
                        code,
                        isSuccessful,
                        requestUrl,
                        builder.enableThreadName,
                        urlLength
                    )
                )
                append(BOTTOM_BORDER)
            }.toString()

            LoggerApi.i(responseString)
        }

        private fun getRequest(
            request: Request,
            enableThreadName: Boolean = true,
            urlLength: Int
        ): String {
            return if (request.url.toString().length > urlLength) {
                " URL: " + request.url.toString()
                    .take(urlLength) + "$LINE_SEPARATOR " + request.url.toString()
                    .substring(urlLength, request.url.toString().length) +
                        getDoubleSeparator() + " Method: @" + request.method + getDoubleSeparator() +
                        if (enableThreadName) {
                            " Thread: " + Thread.currentThread().name + getDoubleSeparator()
                        } else {
                            ""
                        }
            } else {
                " URL: " + request.url + getDoubleSeparator() + " Method: @" + request.method + getDoubleSeparator() +
                        if (enableThreadName) {
                            " Thread: " + Thread.currentThread().name + getDoubleSeparator()
                        } else {
                            ""
                        }
            }
        }

        private fun getResponse(
            header: String,
            tookMs: Long,
            code: Int,
            isSuccessful: Boolean,
            requestUrl: HttpUrl,
            enableThreadName: Boolean = true,
            urlLength: Int
        ): String {
            return if (requestUrl.toString().length > urlLength) {
                " URL: " + requestUrl.toString()
                    .take(urlLength) + "$LINE_SEPARATOR " + requestUrl.toString()
                    .substring(
                        urlLength,
                        requestUrl.toString().length
                    ) + getDoubleSeparator() + " is success : " + isSuccessful + " - " + "Received in: " + tookMs + "ms" + getDoubleSeparator() + " Status Code: " +
                        code + getDoubleSeparator() +
                        (if (enableThreadName) " Thread: " + Thread.currentThread().name + getDoubleSeparator() else "") +
                        if (header.isLineEmpty()) " " else " Headers:$LINE_SEPARATOR" + dotHeaders(
                            header
                        )
            } else {
                " URL: " + requestUrl + getDoubleSeparator() + " is success : " + isSuccessful + " - " + "Received in: " + tookMs + "ms" + getDoubleSeparator() + " Status Code: " +
                        code + getDoubleSeparator() +
                        (if (enableThreadName) " Thread: " + Thread.currentThread().name + getDoubleSeparator() else "") +
                        if (header.isLineEmpty()) " " else " Headers:$LINE_SEPARATOR" + dotHeaders(
                            header
                        )
            }
        }

        private fun dotHeaders(header: String): String {
            val headers = header.split(LINE_SEPARATOR.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

            return StringBuilder().apply {
                if (headers.isNotEmpty()) {
                    for (item in headers) {
                        append(" - ").append(item).append("\n")
                    }
                } else {
                    append(LINE_SEPARATOR)
                }
            }.toString()
        }

        private fun logLines(
            lines: Array<String>,
            lineLength: Int
        ) = StringBuilder().apply {
            for (line in lines) {
                val length = line.length
                for (i in 0..length / lineLength) {
                    val start = i * lineLength
                    var end = (i + 1) * lineLength
                    end = if (end > line.length) line.length else end
                    append(" " + line.substring(start, end)).append(LINE_SEPARATOR)
                }
            }
        }.toString()

        private fun bodyToString(request: Request): String {
            try {
                val copy = request.newBuilder().build()
                val buffer = Buffer()
                if (copy.body == null) return ""

                copy.body?.writeTo(buffer)
                return getJsonString(buffer.readUtf8())
            } catch (e: IOException) {
                return "{\"err\": \"" + e.message + "\"}"
            }
        }

        private fun binaryBodyToString(request: Request): String {
            val copy = request.newBuilder().build()
            val requestBody = copy.body ?: return ""

            var buffer: String?
            val contentType = requestBody.contentType()
            buffer = if (contentType != null) {
                "Content-Type: $contentType"
            } else {
                ""
            }

            if (requestBody.contentLength() > 0) {
                buffer += "${LINE_SEPARATOR}Content-Length: " + requestBody.contentLength()
            }

            if (contentType != null) {
                val contentTypeString = contentType.toString()
                if (contentTypeString.contains("application/x-www-form-urlencoded")) {
                    buffer += LINE_SEPARATOR
                    if (requestBody is FormBody) {
                        val size = requestBody.size
                        for (i in 0 until size) {
                            buffer += requestBody.name(i) + "=" + requestBody.value(i) + "&"
                        }

                        buffer = buffer.take(buffer.length - 1)
                    }
                }
            }

            return buffer
        }

        @JvmStatic
        fun getJsonString(msg: String): String {
            var message: String
            try {
                message = when {
                    msg.startsWith("{") -> {
                        val jsonObject = JSONObject(msg)
                        jsonObject.toString(JSON_INDENT)
                    }
                    msg.startsWith("[") -> {
                        val jsonArray = JSONArray(msg)
                        jsonArray.toString(JSON_INDENT)
                    }
                    else -> {
                        msg
                    }
                }

                message = message.replace("\\/", "/")
            } catch (e: JSONException) {
                message = msg
            }

            return message
        }
    }
}