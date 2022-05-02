#### 图片选择 3.0 API

* 主题
setStatusBarColor 状态栏背景色
setNavigationBarColor 导航栏的颜色
setStatusBarFontColor 状态栏字体颜色
isSelectNumberStyle  是否显示选择数量排号模式
isPreviewSelectNumberStyle 预览页勾选样式是否使用数量类型
isCompleteSelectRelativeTop 完成按钮从底部放在右上角
isPreviewSelectRelativeBottom 预览页选择按钮从顶部放在右下角
isPreviewDisplaySelectGallery 预览页是否显示选择画廊
setPreviewSelectMarginRight 预览页选择按钮MarginRight
setPreviewSelectText 预览页选择按钮文本
setPreviewSelectTextSize 预览页选择按钮字体大小
setPreviewSelectTextColor 预览页选择按钮字体颜色
setSelectBackground  勾选样式
setPreviewSelectBackground  预览页勾选样式
setSelectNormalText 底部默认选择文本
setSelectNormalTextSize 底部默认选择文本字体大小
setSelectNormalTextColor 底部默认选择文本字体色值
setSelectNormalBackgroundResources 选择默认背景
setSelectText 底部选择文本
setSelectTextSize 底部选择文本字体大小
setSelectTextColor 底部选择文本字体色值
setSelectBackgroundResources 选择背景
isAdapterItemIncludeEdge RecyclerView item间隙
setAdapterItemSpacingSize RecyclerView item间隙
setAdapterSelectTextSize  勾选样式字体大小
setAdapterSelectTextColor  勾选样式字体色值
setAdapterSelectStyleGravity 勾选样式位置
setAdapterSelectClickArea  勾选点击区域
setAdapterDurationDrawableLeft 资源类型标志
setAdapterDurationTextSize 时长文字字体大小
setAdapterDurationTextColor 时长文字颜色
setAdapterDurationBackgroundResources 时长文字阴影背景
setAdapterDurationGravity 时长文字背景
setAdapterCameraBackgroundColor 拍照按钮背景色
setAdapterCameraDrawableTop  拍照按钮图标
setAdapterCameraText 拍照按钮文本
setAdapterCameraTextColor 拍照按钮文本字体色值
setAdapterCameraTextSize  拍照按钮文本字体大小
setAdapterTagBackgroundResources 资源图标识的背景
setAdapterTagTextSize 资源标识的字体大小
setAdapterTagTextColor 资源标识的字体色值
setAdapterTagGravity 资源标识的位置
setAdapterImageEditorResources 图片被编辑标识
setAdapterImageEditorGravity 图片被编辑标识位置
setMainListBackgroundColor 列表背景色
setAdapterPreviewGalleryFrameResource 预览页画廊边框样式
setAdapterPreviewGalleryBackgroundResource 预览页画廊背景色
setAdapterPreviewGalleryItemSize 预览页画廊item大小


isHideTitleBar 是否隐藏标题栏
setTitleLeftBackResource 标题栏左边关闭样式
setPreviewTitleLeftBackResource 预览标题栏左边关闭样式
setTitleDefaultText 标题栏默认文案
setTitleTextSize 标题栏字体大小
setTitleTextColor 标题栏字体色值
setTitleBackgroundColor 标题栏背景
setPreviewTitleBackgroundColor 预览标题栏背景
setTitleBarHeight 标题栏高度
isAlbumTitleRelativeLeft 标题栏位置居左
setTitleAlbumBackgroundResource 标题栏专辑背景
setTitleDrawableRightResource 标题栏右边向上图标
setTitleCancelBackgroundResource 取消按钮背景
setTitleCancelText 取消按钮文本
setTitleCancelTextSize 取消按钮字体大小
setTitleCancelTextColor 取消按钮字体色值
setPreviewDeleteBackgroundResource  预览里的删除按钮
isHideCancelButton 是否隐藏取消按钮



setBottomNarBarBackgroundColor 底部NarBar背景色
setBottomPreviewNarBarBackgroundColor 底部预览页NarBar背景色
setBottomNarBarHeight 底部NarBar高度
setBottomPreviewText  底部预览文本
setBottomPreviewSelectText  选中底部预览文本
setBottomPreviewTextSize  底部预览文本字体大小
setBottomPreviewNormalTextColor 底部预览文本正常字体色值
setBottomPreviewSelectTextColor 底部预览文本选中字体色值
setBottomEditorText 底部编辑文字
setBottomEditorTextSize 底部编辑文字大小
setBottomEditorTextColor 底部编辑文字色值
setBottomOriginalDrawableLeft 底部原图文字DrawableLeft
setBottomOriginalText 底部原图文字
setBottomOriginalTextSize 底部原图文字大小
setBottomOriginalTextColor 底部原图文字色值
setBottomSelectNumResources 已选数量背景样式
setBottomSelectNumTextSize 已选数量文字大小
setBottomSelectNumTextColor 已选数量文字颜色
isCompleteCountTips 是否显示已选数量圆点提醒


setAlbumAdapterItemBackground  专辑列表item背景色值
setAlbumAdapterItemSelectStyle 专辑列表选中样式
setAlbumAdapterItemTitleSize	 专辑名称字体大小
setAlbumAdapterItemTitleColor  专辑名称字体色值

* LocalMedia

getPath(); 指从MediaStore查询返回的路径；SDK_INT >=29 返回content://类型；其他情况返回绝对路径。

getRealPath(); 绝对路径；SDK_INT >=29且处于沙盒环境下直接使用会报FileNotFoundException异常；

getOriginalPath(); 原图路径；isOriginalImageControl(true);
且勾选了原图选项时返回；但SDK_INT >=29且未实现.setSandboxFileEngine();
直接使用会报FileNotFoundException异常；

getCompressPath(); 压缩路径；实现了setCompressEngine();时返回；

getCutPath(); 裁剪或编辑路径；实现了setCropEngine();或setEditMediaInterceptListener();时返回；

getSandboxPath(); SDK_INT >=29且实现了.setSandboxFileEngine();返回；

getVideoThumbnailPath(); 视频缩略图，需要实现setVideoThumbnailListener接口

getWatermarkPath(); 水印地址，需要实现setAddBitmapWatermarkListener接口

getAvailablePath(); SDK_INT为任意版本都返回直接可用地址(但SDK_INT >29且未开启压缩、裁剪或未实现setSandboxFileEngine，请参考getPath())，但如果你需要具体业务场景下的地址，请参考如上几种路径；

* 功能

.setSelectorUIStyle(); 设置相册主题
.setLanguage(); 设置相册语言
.setImageEngine(); 设置相册图片加载引擎
.setCompressEngine(); 设置相册压缩引擎
.setCropEngine(); 设置相册裁剪引擎
.setSandboxFileEngine(); 设置相册沙盒目录拷贝引擎
.setOriginalFileEngine(); 设置相册图片原图处理引擎
.setExtendLoaderEngine(); 设置相册数据源加载引擎
.setCameraInterceptListener(); 拦截相机事件，实现自定义相机
.setEditMediaInterceptListener(); 拦截资源编辑事件，实现自定义编辑
.setPermissionsInterceptListener(); 拦截相册权限处理事件，实现自定义权限
.setSelectLimitTipsListener();拦截选择限制事件，可实现自定义提示
.setSelectFilterListener();拦截不支持的选择项
.isCameraForegroundService(); 拍照时是否开启一个前台服务
.setRequestedOrientation(); 设置屏幕旋转方向
.setSelectedData(); 相册已选数据
.setRecyclerAnimationMode(); 相册列表动画效果
.setImageSpanCount(); 相册列表每行显示个数
.isDisplayCamera(); 是否显示相机入口
.isPageStrategy(); 是否开启分页模式
.selectionMode(); 单选或是多选
.setMaxSelectNum(); 图片最大选择数量
.setMinSelectNum(); 图片最小选择数量
.setMaxVideoSelectNum(); 视频最大选择数量
.setMinVideoSelectNum(); 视频最小选择数量
.setRecordVideoMaxSecond(); 视频录制最大时长
.setRecordVideoMinSecond(); 视频录制最小时长
.setFilterVideoMaxSecond(); 过滤视频最大时长
.setFilterVideoMinSecond(); 过滤视频最小时长
.setSelectMaxDurationSecond(); 选择最大时长视频或音频
.setSelectMinDurationSecond(); 选择最小时长视频或音频
.setVideoQuality(); 系统相机录制视频质量
.isQuickCapture(); 使用系统摄像机录制后，是否支持使用系统播放器立即播放视频
.isPreviewAudio(); 是否支持音频预览
.isPreviewImage(); 是否支持预览图片
.isPreviewVideo(); 是否支持预览视频
.isPreviewFullScreenMode(); 预览点击全屏效果
.isEmptyResultReturn(); 支持未选择返回
.isWithSelectVideoImage(); 是否支持视频图片同选
.isSelectZoomAnim(); 选择缩略图缩放效果
.isOpenClickSound(); 是否开启点击音效
.isCameraAroundState(); 是否开启前置摄像头；系统相机 只支持部分机型
.isCameraRotateImage(); 拍照是否纠正旋转图片
.isGif(); 是否显示gif文件
.isWebp(); 是否显示webp文件
.isBmp(); 是否显示bmp文件
.isHidePreviewDownload(); 是否隐藏预览下载功能
.isAutoScalePreviewImage(); 预览图片自动放大充满屏幕
.setOfAllCameraType(); isWithSelectVideoImage模式下相机优先使用权
.isMaxSelectEnabledMask(); 达到最大选择数是否开启禁选蒙层
.isSyncCover(); isPageModel模式下是否强制同步封面，默认false
.isAutomaticTitleRecyclerTop(); 点击相册标题是否快速回到第一项
.isFastSlidingSelect(); 快速滑动选择
.isDirectReturnSingle(); 单选时是否立即返回
.setCameraImageFormat(); 拍照图片输出格式
.setCameraImageFormatForQ(); 拍照图片输出格式，Android Q以上
.setCameraVideoFormat(); 拍照视频输出格式
.setCameraVideoFormatForQ(); 拍照视频输出格式，Android Q以上
.setOutputCameraDir(); 使用相机输出路径
.setOutputAudioDir();使用录音输出路径
.setOutputCameraImageFileName(); 图片输出文件名
.setOutputCameraVideoFileName(); 视频输出文件名
.setOutputAudioFileName(); 录音输出文件名
.setQuerySandboxDir(); 查询指定目录下的资源
.isOnlyObtainSandboxDir(); 是否只查询指定目录下的资源
.setFilterMaxFileSize(); 过滤最大文件
.setFilterMinFileSize(); 过滤最小文件
.setSelectMaxFileSize(); 最大可选文件大小
.setSelectMinFileSize(); 最小可选文件大小
.setQueryOnlyMimeType(); 查询指定文件类型
.setSkipCropMimeType(); 跳过不需要裁剪的类型