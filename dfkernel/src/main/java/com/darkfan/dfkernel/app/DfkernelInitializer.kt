package com.darkfan.dfkernel.app

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import com.billy.android.swipe.SmartSwipeBack
import com.darkfan.dfkernel.activity.IActivity
import com.darkfan.repo.log.api.BuildConfig
import com.darkfan.repo.log.api.Logger
import com.tencent.mmkv.MMKV

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 08/05/2022 15:43
 * @desc dfkernel 初始化.
 */
class DfkernelInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        Logger.initLogger(BuildConfig.IS_DEV_MODE)
        MMKV.initialize(context)

        // 全局侧滑返回
        SmartSwipeBack.activitySlidingBack(
            context as Application,
            object : SmartSwipeBack.ActivitySwipeBackFilter {
                override fun onFilter(activity: Activity): Boolean {
                    if (activity is IActivity) {
                        return activity.useSwipe()
                    }

                    return false
                }
            })
    }

    override fun dependencies() = emptyList<Class<out Initializer<*>>>()
}