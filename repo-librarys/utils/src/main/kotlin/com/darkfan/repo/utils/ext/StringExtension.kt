package com.darkfan.repo.utils.ext

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 25/04/2022 01:03
 * @desc 字符串扩展.
 */


/**
 * 判断字符串是否为null或全为空格
 */
fun String?.isBlack(): Boolean {
    return (null == this || trimString().size() == 0)
}

/**
 * 判断字符串是否有效, 如果为空默认返回长度为0的字符串
 */
fun String?.invalid(invalid: String = ""): String {
    return if (isBlack()) {
        invalid
    } else {
        trimString()
    }
}

/**
 * 字符串去掉空格, 如果字符串为空返回长度为0的字符串
 */
fun String?.trimString(): String {
    return this?.trim() ?: ""
}

/**
 * 字符串长度, 如果字符串为空返回0
 */
fun String?.size(): Int {
    return this?.length ?: 0
}