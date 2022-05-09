package com.darkfan.repo.log.api

import com.darkfan.repo.log.DfkernelLogger
import org.jetbrains.annotations.NonNls

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 24/04/2022 18:20
 * @desc 日志 api
 */
object Logger {

    /**
     * init logger.
     */
    fun initLogger(debug: Boolean) {
        DfkernelLogger.initLogger(debug)
    }

    /** Log a debug message with optional format args. */
    fun d(message: String?, vararg args: Any?) {
        DfkernelLogger.d(message, args)
    }

    /** Log a debug exception and a message with optional format args. */
    fun d(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
        DfkernelLogger.d(t, message, args)
    }

    /** Log a debug exception . */
    fun d(t: Throwable?) {
        DfkernelLogger.d(t)
    }

    /** Log a info message with optional format args. */
    fun i(message: String?, vararg args: Any?) {
        DfkernelLogger.i(message, args)
    }

    /** Log a info exception and a message with optional format args. */
    fun i(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
        DfkernelLogger.i(t, message, args)
    }

    /** Log a info exception . */
    fun i(t: Throwable?) {
        DfkernelLogger.i(t)
    }

    /** Log a warning message with optional format args. */
    fun w(message: String?, vararg args: Any?) {
        DfkernelLogger.w(message, args)
    }

    /** Log a warning exception and a message with optional format args. */
    fun w(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
        DfkernelLogger.w(t, message, args)
    }

    /** Log a warning exception . */
    fun w(t: Throwable?) {
        DfkernelLogger.w(t)
    }

    /** Log a error message with optional format args. */
    fun e(message: String?, vararg args: Any?) {
        DfkernelLogger.e(message, args)
    }

    /** Log a error exception and a message with optional format args. */
    fun e(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
        DfkernelLogger.e(t, message, args)
    }

    /** Log a error exception . */
    fun e(t: Throwable?) {
        DfkernelLogger.e(t)
    }

    /**
     * Print message to console.
     */
    fun printConsole(message: String?) {
        println(message)
    }
}