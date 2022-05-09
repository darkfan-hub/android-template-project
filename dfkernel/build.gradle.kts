import com.darkfan.build.logic.AppDependencies

plugins {
    id("com.android.library")
    id("com.darkfan.build.logic")
}

android {
    resourcePrefix = "dfkernel_"
}

dependencies {
    // 日志打印
    api(project(":log-api"))
    // 工具类
    api(project(":utils"))

    // startup
    implementation(AppDependencies.AndroidX.startup)
    // 方法数超过64K
    implementation(AppDependencies.AndroidX.multidex)
    // Androidx
    implementation(AppDependencies.AndroidX.androidxCore)
    implementation(AppDependencies.AndroidX.appcompat)
    // Google
    implementation(AppDependencies.Google.material)
    // UI
    implementation(AppDependencies.UI.immersionBar)
    implementation(AppDependencies.UI.smartSwipe)
    implementation(AppDependencies.UI.smartSwipeX)
    // mmkv
    api(AppDependencies.Storage.mmkv)
}