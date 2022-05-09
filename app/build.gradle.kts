plugins {
    id("com.android.application")
    id("com.darkfan.build.logic")
}

android {
    // app 模块增加配置, 打包会报错
    lint {
        baseline = file("lint-baseline.xml")
    }
}

dependencies {
    implementation(project(":dfkernel"))
}