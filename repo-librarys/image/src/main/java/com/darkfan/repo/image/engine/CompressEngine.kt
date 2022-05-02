package com.darkfan.repo.image.engine

import android.content.Context
import android.net.Uri
import com.luck.picture.lib.engine.CompressFileEngine
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener
import top.zibin.luban.Luban
import top.zibin.luban.OnNewCompressListener
import java.io.File


/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 02/05/2022 20:43
 * @desc 图片压缩引擎
 */
class CompressEngine : CompressFileEngine {

    companion object {

        private val _engine: CompressEngine by lazy { CompressEngine() }

        val engine: CompressEngine
            get() = _engine
    }

    override fun onStartCompress(
        context: Context?,
        source: ArrayList<Uri>?,
        call: OnKeyValueResultCallbackListener?
    ) {
        Luban.with(context).load(source).ignoreBy(100)
            .setCompressListener(object : OnNewCompressListener {
                override fun onStart() {}

                override fun onSuccess(source: String, compressFile: File) {
                    call?.onCallback(source, compressFile.absolutePath)
                }

                override fun onError(source: String?, e: Throwable?) {
                    call?.onCallback(source, null)
                }
            })
    }
}