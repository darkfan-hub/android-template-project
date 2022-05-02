package com.darkfan.repo.image

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.darkfan.repo.image.engine.CompressEngine
import com.darkfan.repo.image.engine.CropEngine
import com.darkfan.repo.image.engine.GlideEngine
import com.darkfan.repo.image.transformations.RoundedCornersTransformation
import com.luck.picture.lib.basic.PictureSelector
import com.luck.picture.lib.config.PictureConfig
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.config.SelectMimeType
import com.luck.picture.lib.config.SelectModeConfig
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.interfaces.OnResultCallbackListener
import com.luck.picture.lib.style.PictureSelectorStyle

/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 25/04/2022 01:03
 * @desc 图片扩展.
 */

inline fun ImageView.displayImage(url: String) {
    displayImage(this.context, url, null)
}

inline fun ImageView.displayImage(
    url: String,
    placeholder: Drawable
) {
    displayImage(this.context, url, placeholder)
}

inline fun ImageView.displayImage(
    context: Context,
    url: String,
    placeholder: Drawable?
) {
    val options = RequestOptions()
    if (placeholder == null) {
        // options.placeholder(R.drawable.frlib_iamge_placeholder)
    } else {
        options.placeholder(placeholder)
    }
    options.centerCrop()

    FwGlideApp.with(context)
        .load(url)
        .apply(options)
        .into(this)
}

inline fun ImageView.displayRadiusImage(
    url: String,
    radius: Int,
    overrideSize: Int = 0,
) {
    displayRadiusImage(this.context, url, radius, null, overrideSize)
}

inline fun ImageView.displayRadiusImage(
    url: String,
    radius: Int,
    placeholder: Drawable,
    overrideSize: Int = 0,
) {
    displayRadiusImage(this.context, url, radius, placeholder, overrideSize)
}

inline fun ImageView.displayRadiusImage(
    context: Context,
    url: String,
    radius: Int,
    placeholder: Drawable?,
    overrideSize: Int = 0,
) {
    val options = RequestOptions().transform(
        CenterCrop(),
        RoundedCornersTransformation(
            radius,
            0,
            RoundedCornersTransformation.CornerType.ALL
        )
    )

    if (overrideSize > 0) {
        options.override(overrideSize)
    }

    FwGlideApp.with(context)
        .load(url)
        .apply(options)
        .thumbnail(glideRequestBuilder(context, options, placeholder))
        .into(this)
}

inline fun ImageView.displayCircleImage(url: String) {
    displayCircleImage(this.context, url, null)
}

inline fun ImageView.displayCircleImage(
    url: String,
    placeholder: Drawable
) {
    displayCircleImage(this.context, url, placeholder)
}

inline fun ImageView.displayCircleImage(
    context: Context,
    url: String,
    placeholder: Drawable?
) {
    val options = RequestOptions().transform(CenterCrop(), CircleCrop())

    FwGlideApp.with(context)
        .load(url)
        .apply(options)
        .thumbnail(glideRequestBuilder(context, options, placeholder))
        .into(this)
}

fun glideRequestBuilder(
    context: Context,
    options: RequestOptions,
    placeholder: Drawable?
): RequestBuilder<Drawable> {
    return if (placeholder == null) {
        FwGlideApp.with(context).load(com.luck.picture.lib.R.drawable.ps_image_placeholder).apply(options)
    } else {
        FwGlideApp.with(context).load(placeholder).apply(options)
    }
}

/**
 * 单选图片
 */
fun FragmentActivity.singlePictureSelector(
    enableCompress: Boolean = true,
    enableCrop: Boolean = true,
    callback: OnResultCallbackListener<LocalMedia>? = null
) {
    // 只显示图片
    val selector = PictureSelector.create(this)
        .openGallery(SelectMimeType.ofImage())
        .setSelectionMode(SelectModeConfig.SINGLE)
        .setMaxSelectNum(1)
        .setMinSelectNum(1)
    selector.setImageEngine(GlideEngine.engine) // 图片加载引擎
    if (enableCompress) selector.setCompressEngine(CompressEngine.engine) // 图片压缩
    if (enableCrop) selector.setCropEngine(CropEngine.engine) // 设置相册裁剪引擎
    selector.forResult(callback)
}

/**
 * 多选图片, 默认选择9张
 */
fun FragmentActivity.multiplePictureSelector(
    maxSize: Int = 9,
    enableCompress: Boolean = true,
    selectionData: List<LocalMedia> = listOf(),
    callback: OnResultCallbackListener<LocalMedia>? = null
) {
    val selector = PictureSelector.create(this)
        .openGallery(SelectMimeType.ofImage())
        .setSelectionMode(SelectModeConfig.SINGLE)
        .setMaxSelectNum(maxSize)
        .setMinSelectNum(1)
        .setSelectedData(selectionData)
    selector.setImageEngine(GlideEngine.engine) // 图片加载引擎
    if (enableCompress) selector.setCompressEngine(CompressEngine.engine) // 图片压缩
    selector.forResult(callback)
}

/**
 * 拍照
 */
fun FragmentActivity.takePhoto(
    enableCompress: Boolean = true,
    enableCrop: Boolean = true,
    callback: OnResultCallbackListener<LocalMedia>? = null
) {
    val selector = PictureSelector.create(this)
        .openCamera(SelectMimeType.ofImage())

    if (enableCompress) selector.setCompressEngine(CompressEngine.engine) // 图片压缩
    if (enableCrop) selector.setCropEngine(CropEngine.engine) // 设置相册裁剪引擎

    selector.forResult(callback)
}