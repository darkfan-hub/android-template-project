package com.darkfan.repo.image.engine

import android.R.attr
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.darkfan.repo.image.FwGlideApp
import com.luck.picture.lib.engine.ImageEngine
import com.luck.picture.lib.interfaces.OnCallbackListener
import com.luck.picture.lib.utils.ActivityCompatHelper


/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 02/05/2022 20:08
 * @desc Glide加载引擎
 */
class GlideEngine : ImageEngine {

    companion object {

        private val _engine: GlideEngine by lazy { GlideEngine() }

        val engine: GlideEngine
            get() = _engine
    }

    /**
     * 加载图片
     */
    override fun loadImage(context: Context, url: String, imageView: ImageView) {
        if (!ActivityCompatHelper.assertValidRequest(context)) return

        FwGlideApp.with(context).load(url).centerCrop().into(imageView)
    }

    /**
     * 加载指定url并返回bitmap
     */
    override fun loadImageBitmap(
        context: Context,
        url: String,
        maxWidth: Int,
        maxHeight: Int,
        call: OnCallbackListener<Bitmap>?
    ) {
        if (!ActivityCompatHelper.assertValidRequest(context)) return

        FwGlideApp.with(context)
            .asBitmap()
            .override(attr.maxWidth, attr.maxHeight)
            .load(url)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    call?.onCall(resource)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    call?.onCall(null)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    /**
     * 加载相册目录封面
     */
    override fun loadAlbumCover(context: Context, url: String, imageView: ImageView) {
        if (!ActivityCompatHelper.assertValidRequest(context)) return

        // TODO 添加图片占位图

        Glide.with(context)
            .asBitmap()
            .load(url)
            .override(180, 180)
            .sizeMultiplier(0.5f)
            .transform(CenterCrop(), RoundedCorners(8))
            .into(imageView)
    }

    override fun loadGridImage(context: Context, url: String, imageView: ImageView) {
        if (!ActivityCompatHelper.assertValidRequest(context)) return

        // TODO 添加图片占位图

        Glide.with(context)
            .load(url)
            .override(200, 200)
            .centerCrop()
            .into(imageView)
    }

    override fun pauseRequests(context: Context) {
        Glide.with(context).pauseRequests()
    }

    override fun resumeRequests(context: Context) {
        Glide.with(context).resumeRequests()
    }
}