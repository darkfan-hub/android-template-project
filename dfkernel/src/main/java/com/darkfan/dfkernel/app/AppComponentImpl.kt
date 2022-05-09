package com.darkfan.dfkernel.app

import android.app.Application
import com.darkfan.dfkernel.BuildConfig

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 08/05/2022 16:06
 * @desc
 */
class AppComponentImpl(
    private val app: Application
) : IAppComponent {

    override fun application(): Application = app

    override fun enableDebug(): Boolean = BuildConfig.IS_DEV_MODE

    override fun fileDir(): String {
        return ""
    }
}