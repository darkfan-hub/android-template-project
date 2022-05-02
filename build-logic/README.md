## 编译模块

### 注意⚠️

1. 在`AppConfiguration`中查找并替换`applicationId`
```
const val applicationId = "com.darkfan.repo.simple"
```


2. 在`BuildLogicPlugins`中查找替换`host_url `
```
private companion object {
    const val release_host_url = "\"http://api.gufanfan.vip/v1/\""
    const val debug_host_url = "\"http://api.gufanfan.vip/v1/\""
}
```

### 依赖管理
详情见`AppDependencies`

### 版本号管理
详情见`Versions`



