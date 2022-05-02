import com.darkfan.build.logic.AppDependencies

plugins {
    id("com.android.library")
    id("com.darkfan.build.logic")
}

dependencies {
    // 方法数超过64K
    implementation(AppDependencies.AndroidX.multidex)
    // Androidx
    implementation(AppDependencies.AndroidX.androidxCore)
    implementation(AppDependencies.AndroidX.appcompat)
    // Google
    implementation(AppDependencies.Google.material)
    // UI
    implementation(AppDependencies.UI.immersionBar)
}