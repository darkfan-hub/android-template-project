package com.darkfan.repo.utils.ext

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.darkfan.repo.utils.SysUtil

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 25/04/2022 01:03
 * @desc Context扩展.
 */


/**
 * 根据颜色资源id获取颜色
 */
fun Context.color(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

/**
 * 根据drawable资源id获取drawable
 */
fun Context.drawable(@DrawableRes drawableResId: Int): Drawable? {
    return AppCompatResources.getDrawable(this, drawableResId)
}

fun View.backgroundExt(drawable: Drawable) {
    if (SysUtil.isAndroidJelly()) {
        this.background = drawable
    } else {
        this.setBackgroundDrawable(drawable)
    }
}
