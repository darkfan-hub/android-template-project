package com.darkfan.repo.dfkernel.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 11/04/2022 09:53
 * @desc Activity 抽象类.
 */
abstract class AbstractActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}