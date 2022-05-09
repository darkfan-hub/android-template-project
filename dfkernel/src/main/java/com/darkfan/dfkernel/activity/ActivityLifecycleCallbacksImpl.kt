package com.darkfan.dfkernel.activity

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.darkfan.dfkernel.annotation.DfkernelActivity
import com.darkfan.dfkernel.ktx.fullScreen
import com.darkfan.dfkernel.ktx.statusBarStyle
import com.darkfan.repo.log.api.Logger

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 05/05/2022 14:02
 * @desc
 */
class ActivityLifecycleCallbacksImpl : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        Logger.i("'${activity.javaClass.simpleName}'已创建~")

        // 如果当前activity有'DfkernelActivity'注解, 并且实现 IActivity接口
        if (activity.javaClass.isAnnotationPresent(DfkernelActivity::class.java) && activity is IActivity) {
            val dfkernelActivity = activity.javaClass.getAnnotation(DfkernelActivity::class.java)

            dfkernelActivity?.apply {
                // 沉浸式状态栏设置
                if (fullScreen) {
                    activity.fullScreen()
                } else {
                    activity.statusBarStyle(
                        statusBarColor = statusBarColor,
                        statusBarDarkFont = statusBarDarkText,
                        fitsSystemWindows = fitsSystemWindows
                    )
                }
            }
        }
    }

    override fun onActivityStarted(activity: Activity) {
        Logger.i("'${activity.javaClass.simpleName}'已启动~", activity.javaClass.simpleName)
    }

    override fun onActivityResumed(activity: Activity) {
        Logger.i("'${activity.javaClass.simpleName}'已可见~", activity.javaClass.simpleName)
    }

    override fun onActivityPaused(activity: Activity) {
        Logger.i("'${activity.javaClass.simpleName}'已暂停~", activity.javaClass.simpleName)
    }

    override fun onActivityStopped(activity: Activity) {
        Logger.i("'${activity.javaClass.simpleName}'已停止~", activity.javaClass.simpleName)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        Logger.i("'${activity.javaClass.simpleName}'缓存数据~", activity.javaClass.simpleName)
    }

    override fun onActivityDestroyed(activity: Activity) {
        Logger.i("'${activity.javaClass.simpleName}'已销毁~", activity.javaClass.simpleName)
    }
}