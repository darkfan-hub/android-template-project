#### 项目结构

```
.AndroidLibsRepo
├── app	             // App 主模块
├── build-logic      // 项目编译相关
├── dfkernel         // App 核心模块
└── repo-librarys    // libs 库
    ├── http         // 网络模块
    ├── http-api     // 网络Api模块
    ├── im           // IM 模块
    ├── image        // 图片模块
    ├── log          // 日志模块
    ├── pay          // 支付模块
    ├── permission   // 权限模块
    ├── push         // 推送模块
    ├── share        // 分享模块
    ├── storage      // 存储模块
    ├── storage-api  // 存储Api模块
    └── utils        // 工具模块
```

#### 模块单独启动

1. 在`gradle.properties`中修改`isRunAlone`:
```
isRunAlone=true
```

2. 修改`build.gradle`:
```
val isRunAlone: String = (project.properties["isRunAlone"] ?: "false").toString()
if (isRunAlone.toBoolean()) {
    plugins {
        id("com.android.application")
        id("com.darkfan.build.logic")
    }
} else {
    plugins {
        id("com.android.library")
        id("com.darkfan.build.logic")
    }
}
```

#### 用法
