package com.darkfan.dfkernel.activity

import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.darkfan.repo.utils.KeyboardUtil

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 11/04/2022 09:53
 * @desc Activity 抽象类.
 */
abstract class AbstractActivity<Binding : ViewBinding> : AppCompatActivity(), IActivity {

    /** activity 本身. */
    open lateinit var self: AppCompatActivity

    /** activity view. */
    open lateinit var viewBinding: Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        self = this
        supportActionBar?.hide()

        when {

        }

        initView(savedInstanceState)
        addObservables()
        addViewListen()
        initData()
    }



    override fun initView(savedInstanceState: Bundle?) {
    }

    override fun addObservables() {
    }

    override fun addViewListen() {
        // 隐藏软键盘
        findViewById<ViewGroup>(Window.ID_ANDROID_CONTENT).setOnClickListener {
            KeyboardUtil.hideSoftInput(self)
        }
    }

    override fun initData() {
    }

    override fun useFragment(): Boolean = false

    override fun useSwipe(): Boolean = true

    override fun useDefaultPages(): Boolean = true

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val currentView = currentFocus
            currentView?.let {
                it.clearFocus()
                KeyboardUtil.hideSoftInput(self, it)
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}