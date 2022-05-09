package com.darkfan.dfkernel.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.darkfan.dfkernel.activity.ActivityLifecycleCallbacksImpl

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 11/04/2022 09:52
 * @desc App 抽象类.
 */
abstract class AbstractApp : Application() {

    private lateinit var self: Application

    private lateinit var appComponent: IAppComponent

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
        registerActivityLifecycleCallbacks(ActivityLifecycleCallbacksImpl())
    }

    override fun onCreate() {
        super.onCreate()

        self = this
        appComponent = AppComponentImpl(self)
    }

    fun appComponent(): IAppComponent = appComponent
}