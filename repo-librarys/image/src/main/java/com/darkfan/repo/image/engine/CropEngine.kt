package com.darkfan.repo.image.engine

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.darkfan.repo.image.FwGlideApp
import com.luck.picture.lib.engine.CropFileEngine
import com.luck.picture.lib.utils.ActivityCompatHelper
import com.yalantis.ucrop.UCrop
import com.yalantis.ucrop.UCropActivity
import com.yalantis.ucrop.UCropImageEngine


/**
 * @author Fanfan Gu <a href="mailto:stefan.gufan@gmail.com">Contact me.</a>
 * @date 02/05/2022 20:56
 * @desc 裁剪引擎
 */
class CropEngine : CropFileEngine {

    companion object {

        private val _engine: CropEngine by lazy { CropEngine() }

        val engine: CropEngine
            get() = _engine
    }

    override fun onStartCrop(
        fragment: Fragment,
        srcUri: Uri,
        destinationUri: Uri,
        dataSource: ArrayList<String>?,
        requestCode: Int
    ) {
        // 注意* 如果你实现自己的裁剪库，需要在Activity的.setResult();
        // Intent中需要给MediaStore.EXTRA_OUTPUT，塞入裁剪后的路径；如果有额外数据也可以通过CustomIntentKey.EXTRA_CUSTOM_EXTRA_DATA字段存入；
        val uCrop = UCrop.of(srcUri, destinationUri, dataSource)
        uCrop.setImageEngine(object : UCropImageEngine {
            override fun loadImage(context: Context, url: String, imageView: ImageView) {
                if (!ActivityCompatHelper.assertValidRequest(context)) return

                FwGlideApp.with(context).load(url).centerCrop().into(imageView)
            }

            override fun loadImage(
                context: Context,
                url: Uri,
                maxWidth: Int,
                maxHeight: Int,
                call: UCropImageEngine.OnCallbackListener<Bitmap>?
            ) {
                if (!ActivityCompatHelper.assertValidRequest(context)) return

                FwGlideApp.with(context).asBitmap().override(maxWidth, maxHeight).load(url)
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
        })

        uCrop.withOptions(buildOptions())
        uCrop.start(fragment.requireActivity(), fragment, requestCode)
    }

    /**
     *   //初始化UCrop配置
     *   UCrop.Options options = new UCrop.Options();
     *   //设置裁剪图片可操作的手势
     *   options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
     *   //是否隐藏底部容器，默认显示
     *   options.setHideBottomControls(true);
     *   //设置toolbar颜色
     *   options.setToolbarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
     *   //设置状态栏颜色
     *   options.setStatusBarColor(ActivityCompat.getColor(activity, R.color.colorPrimary));
     *   //是否能调整裁剪框
     *   options.setFreeStyleCropEnabled(true);
     *   //UCrop配置
     *   uCrop.withOptions(options);
     *   //设置裁剪图片的宽高比，比如16：9
     *   uCrop.withAspectRatio(aspectRatioX, aspectRatioY);
     */
    private fun buildOptions(): UCrop.Options {
        val options = UCrop.Options()
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL)
        // 是否隐藏底部容器，默认显示
        options.setHideBottomControls(true)
        return options
    }
}