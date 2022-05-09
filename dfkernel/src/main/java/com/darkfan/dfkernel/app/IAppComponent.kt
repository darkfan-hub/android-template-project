package com.darkfan.dfkernel.app

import android.app.Application

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 05/05/2022 13:39
 * @desc app 组件.
 */
interface IAppComponent {

    /**
     * application 实例
     */
    fun application(): Application

    /**
     * 是否是debug模式
     */
    fun enableDebug(): Boolean

    /**
     * app 文件目录
     */
    fun fileDir(): String
}