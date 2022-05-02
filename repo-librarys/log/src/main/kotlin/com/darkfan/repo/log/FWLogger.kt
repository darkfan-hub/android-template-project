package com.darkfan.repo.log

import com.darkfan.repo.log.timber.TimberLog
import org.jetbrains.annotations.NonNls

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 17/04/2022 19:36
 * @desc logger.
 */
class FWLogger private constructor() {

    init {
        throw AssertionError()
    }

    companion object {

        private val _logger:AbstractLogFactory by lazy { TimberLog() }

        /**
         * init logger.
         */
        fun initLogger(debug: Boolean) {
            _logger.initLogger(debug)
        }

        /** Log a debug message with optional format args. */
        fun d(message: String?, vararg args: Any?) {
            _logger.d(message, args)
        }

        /** Log a debug exception and a message with optional format args. */
        fun d(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
            _logger.d(t, message, args)
        }

        /** Log a debug exception . */
        fun d(t: Throwable?) {
            _logger.d(t)
        }

        /** Log a info message with optional format args. */
        fun i(message: String?, vararg args: Any?) {
            _logger.i(message, args)
        }

        /** Log a info exception and a message with optional format args. */
        fun i(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
            _logger.i(t, message, args)
        }

        /** Log a info exception . */
        fun i(t: Throwable?) {
            _logger.i(t)
        }

        /** Log a warning message with optional format args. */
        fun w(message: String?, vararg args: Any?) {
            _logger.w(message, args)
        }

        /** Log a warning exception and a message with optional format args. */
        fun w(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
            _logger.w(t, message, args)
        }

        /** Log a warning exception . */
        fun w(t: Throwable?) {
            _logger.w(t)
        }

        /** Log a error message with optional format args. */
        fun e(message: String?, vararg args: Any?) {
            _logger.e(message, args)
        }

        /** Log a error exception and a message with optional format args. */
        fun e(t: Throwable?, @NonNls message: String?, vararg args: Any?) {
            _logger.e(t, message, args)
        }

        /** Log a error exception . */
        fun e(t: Throwable?) {
            _logger.e(t)
        }
    }
}