import com.darkfan.build.logic.AppDependencies

plugins {
    id("com.android.library")
    id("com.darkfan.build.logic")
}

dependencies {
    implementation(AppDependencies.AndroidX.androidxCore)
    implementation(AppDependencies.AndroidX.resources)
}