package com.darkfan.repo.log.timber

import com.darkfan.repo.log.AbstractLogFactory
import timber.log.Timber

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 16/04/2022 18:43
 * @desc logger 框架之timber.
 */
class TimberLog : AbstractLogFactory() {

    override fun initLogger(debug: Boolean) {
        if (debug) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun d(message: String?, vararg args: Any?) {
        Timber.d(message, args)
    }

    override fun d(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.d(t, message, args)
    }

    override fun d(t: Throwable?) {
        Timber.d(t)
    }

    override fun i(message: String?, vararg args: Any?) {
        Timber.i(message, args)
    }

    override fun i(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.i(t, message, args)
    }

    override fun i(t: Throwable?) {
        Timber.i(t)
    }

    override fun w(message: String?, vararg args: Any?) {
        Timber.w(message, args)
    }

    override fun w(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.w(t, message, args)
    }

    override fun w(t: Throwable?) {
        Timber.w(t)
    }

    override fun e(message: String?, vararg args: Any?) {
        Timber.e(message, args)
    }

    override fun e(t: Throwable?, message: String?, vararg args: Any?) {
        Timber.e(t, message, args)
    }

    override fun e(t: Throwable?) {
        Timber.e(t)
    }
}