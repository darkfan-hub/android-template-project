package com.darkfan.dfkernel.activity

import android.os.Bundle

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 11/04/2022 09:54
 * @desc Activity 接口.
 */
interface IActivity {

    /**
     * 初始化view
     */
    fun initView(savedInstanceState: Bundle?)

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 添加 Observable
     */
    fun addObservables()

    /**
     * 添加 view点击事件
     */
    fun addViewListen()

    /**
     * 这个 Activity 是否会使用 Fragment,框架会根据这个属性判断是否注册 [FragmentManager.FragmentLifecycleCallbacks]
     * 如果返回`false`,那意味着这个 Activity 不需要绑定 Fragment,那你再在这个 Activity 中绑定继承于 [BaseFragment] 的 Fragment 将不起任何作用
     *
     * @return
     * @see [ActivityLifecycle.registerFragmentCallbacks]
     */
    fun useFragment(): Boolean

    /**
     * 是否使用缺省页
     *
     * 统一管理缺省页
     */
    fun useDefaultPages(): Boolean

    /**
     * 使用侧滑返回
     *
     * @return 返回 `true`, 会自动开启侧滑返回
     */
    fun useSwipe(): Boolean
}