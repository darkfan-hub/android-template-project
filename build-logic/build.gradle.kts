plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

gradlePlugin {
    plugins.register("buildLogic") {
        id = "com.darkfan.build.logic"
        implementationClass = "com.darkfan.build.logic.BuildLogicPlugins"
    }
}

dependencies {
    implementation("com.android.tools.build:gradle:7.1.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.20")
}