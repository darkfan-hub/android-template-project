import com.darkfan.build.logic.AppDependencies

plugins {
    id("com.android.library")
    id("com.darkfan.build.logic")
}

dependencies {
    implementation(project(":utils"))
    implementation(project(":log-api"))

    implementation(AppDependencies.Http.okhttp)
    implementation(AppDependencies.Http.retrofit)
    implementation(AppDependencies.Http.retrofitConverterGson)
    implementation(AppDependencies.Google.gson)
}