package com.darkfan.repo.log

import org.jetbrains.annotations.NonNls

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 15/04/2022 23:26
 * @desc
 */
abstract class AbstractLogFactory {

    /**
     * init logger.
     */
    abstract fun initLogger(debug: Boolean)

    /** Log a debug message with optional format args. */
    abstract fun d(message: String?, vararg args: Any?)

    /** Log a debug exception and a message with optional format args. */
    abstract fun d(t: Throwable?, @NonNls message: String?, vararg args: Any?)

    /** Log a debug exception . */
    abstract fun d(t: Throwable?)

    /** Log a info message with optional format args. */
    abstract fun i(message: String?, vararg args: Any?)

    /** Log a info exception and a message with optional format args. */
    abstract fun i(t: Throwable?, @NonNls message: String?, vararg args: Any?)

    /** Log a info exception . */
    abstract fun i(t: Throwable?)

    /** Log a warning message with optional format args. */
    abstract fun w(message: String?, vararg args: Any?)

    /** Log a warning exception and a message with optional format args. */
    abstract fun w(t: Throwable?, @NonNls message: String?, vararg args: Any?)

    /** Log a warning exception . */
    abstract fun w(t: Throwable?)

    /** Log a error message with optional format args. */
    abstract fun e(message: String?, vararg args: Any?)

    /** Log a error exception and a message with optional format args. */
    abstract fun e(t: Throwable?, @NonNls message: String?, vararg args: Any?)

    /** Log a error exception . */
    abstract fun e(t: Throwable?)
}