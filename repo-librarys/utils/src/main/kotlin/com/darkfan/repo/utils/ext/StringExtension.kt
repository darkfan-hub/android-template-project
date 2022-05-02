package com.darkfan.repo.utils.ext

import com.darkfan.repo.utils.CardNoUtil
import java.math.BigDecimal

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 25/04/2022 01:03
 * @desc 字符串扩展.
 */

/**
 * 判断字符串是否为null或全为空格
 */
fun String?.isBlank(): Boolean {
    return (null == this || trimString().size() == 0)
}

fun String?.isNotBlank(): Boolean {
    return !isBlank()
}

/**
 * 判断字符串是否有效, 如果为空默认返回长度为0的字符串
 */
fun String?.invalid(invalid: String = ""): String {
    return if (isBlank()) {
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

/**
 * 检查银行卡号是否有效.
 */
fun String?.validCardNo(): Boolean {
    return CardNoUtil.validCardNo(this)
}

/**
 * 根据卡号获取银行卡开户行
 */
fun String?.getBankName(): String {
    return CardNoUtil.getBankName(this)
}

/**
 * 根据卡号获取银行卡开户行和卡种.
 */
fun String?.getBankNameAndType(): String {
    return CardNoUtil.getBankNameAndType(this)
}

/**
 * @param addComma 是否需要添加逗号，默认不加
 * @param modeFloor 是否使用去尾法，默认true 1.5->1   2.8->2
 * @param decimalNum 小数点后位数
 */
fun String.formatNumber(
    addComma: Boolean = false, modeFloor: Boolean = true,
    decimalNum: Int? = DEFAULT_DECIMAL_NUMBER
): String =
    this.toBigDecimalWithNull().formatNumber(addComma, modeFloor, decimalNum)

fun String?.toBigDecimalWithNull(default: BigDecimal = BigDecimal.ZERO) = isBlank().not().then({
    try {
        this!!.toBigDecimal()
    } catch (e: NumberFormatException) {
        default
    }
}, default)

fun String?.toIntWithNull(default: Int = 0) = isBlank().not().then({
    try {
        this!!.toInt()
    } catch (e: NumberFormatException) {
        default
    }
}, default)

fun String?.toFloatWithNull(default: Float = 0F) = isBlank().not().then({
    try {
        this!!.toFloat()
    } catch (e: NumberFormatException) {
        default
    }
}, default)

fun String?.toDoubleWithNull(default: Double = 0.toDouble()) = isBlank().not().then({
    try {
        this!!.toDouble()
    } catch (e: NumberFormatException) {
        default
    }
}, default)