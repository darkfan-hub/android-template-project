import com.darkfan.build.logic.AppDependencies

plugins {
    id("com.android.library")
    id("com.darkfan.build.logic")
}

android {
    resourcePrefix = "image_"
}

dependencies {
    implementation(project(":utils"))

    implementation(AppDependencies.AndroidX.androidxCore)
    implementation(AppDependencies.Image.glide)
    implementation(AppDependencies.Image.picture)
    implementation(AppDependencies.Image.pictureCompress)
    implementation(AppDependencies.Image.pictureCrop)

    implementation(AppDependencies.Http.okhttp)

    kapt(AppDependencies.Image.glideCompiler)
}