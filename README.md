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
