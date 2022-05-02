import com.darkfan.build.logic.AppDependencies

plugins {
    id("com.android.library")
    id("com.darkfan.build.logic")
}

dependencies {
    implementation(project(":http"))
    implementation(project(":storage-api"))
}