kc-android
==========

knowledge camp 的 android 客户端

## 安装相关组件
### 首先要安装ActiveAndroid
```
git clone https://github.com/pardom/ActiveAndroid
cd ActiveAndroid
mvn clean install -Dmaven.test.skip=true
```

### 安装KCAuthenticator
```
git clone https://github.com/mindpin/KCAuthenticator
cd KCAuthenticator
mvn clean install
```

### clone 工程
```
git clone git@github.com:mindpin/kc-android.git
cd kc-android
git checkout develop
```

### 增加 http_site.xml
创建 app/res/values/http_site.xml 文件，内容如下
```
<resources>
    <!-- http_site 设置自己的开发服务器地址 -->
    <string name="http_site">http://192.168.1.38:3000</string>
</resources>
```

### 创建 env-release.properties 和 env-debug.properties 
创建 app/env-release.properties 文件 和 env-debug.properties 文件，两个文件内容如下
```
# 四个属性的值，如何设置（联系 fushang318 获取）
keystore.filename=
keystore.storepass=
keystore.keypass=
keystore.alias=
```

### 用支持 MAVEN 的 IDE打开工程项目
