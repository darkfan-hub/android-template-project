package com.darkfan.dfkernel.annotation

import androidx.annotation.ColorRes

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 05/05/2022 14:36
 * @desc
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class DfkernelActivity(
    /** 状态栏颜色, 默认透明色; */
    val statusBarColor: String = "#00000000",

    /** true: 留出空间可以显示状态栏及导航栏, false: 沉浸式状态栏, 默认沉浸式状态栏; */
    val fitsSystemWindows: Boolean = false,

    /** 是否全屏显示, 部分机型不好用, 默认不全屏; */
    val fullScreen: Boolean = false,

    /** 状态栏文字是否需要显示黑色, 默认白色; */
    val statusBarDarkText: Boolean = false,
)