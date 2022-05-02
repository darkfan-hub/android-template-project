package com.darkfan.repo.dfkernel.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 11/04/2022 09:52
 * @desc App 抽象类.
 */
abstract class AbstractApp : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }
}