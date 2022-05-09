package com.darkfan.dfkernel.ktx

import android.app.Activity
import android.view.View
import com.darkfan.repo.log.api.Logger
import com.gyf.immersionbar.ImmersionBar

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 08/05/2022 17:17
 * @desc view 扩展
 */

/**
 * 沉浸式状态栏全屏
 */
fun Activity.fullScreen() {
    ImmersionBar.with(this)
        .fullScreen(true)
        .init()
}

/**
 * 沉浸式状态栏默认样式
 */
fun Activity.statusBarStyle(
    statusBarColor: String = "#00000000",
    navigationBarColor: String = "#00000000",
    statusBarDarkFont: Boolean = true,
    navigationBarDarkIcon: Boolean = true,
    fitsSystemWindows: Boolean = false
) {
    ImmersionBar.with(this)
        .statusBarColor(statusBarColor)
        .statusBarDarkFont(statusBarDarkFont)
        .fitsSystemWindows(fitsSystemWindows)
        .navigationBarColor(navigationBarColor)
        .navigationBarDarkIcon(navigationBarDarkIcon)
        .init()
}

private var double_click_time = 800
private var lastClickTime = 0L
private var lastClickTag = 0

fun View.doOnClick(block: () -> Unit) {
    this.setOnClickListener {
        val currentTag = this.id
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime < double_click_time && lastClickTag == currentTag) {
            Logger.i("${double_click_time}ms内发生快速点击")
            return@setOnClickListener
        }

        lastClickTime = currentTimeMillis
        lastClickTag = currentTag
        block()
    }
}