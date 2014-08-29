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

### 安装KCAuthenticator v0.0.5
```
git clone https://github.com/mindpin/KCAuthenticator
cd KCAuthenticator
git checkout v0.0.5 -b v0.0.5
mvn clean install
```

### 安装KCFlip v0.0.1-SNAPSHOT
```
git clone https://github.com/mindpin/kcflip
cd kcflip
mvn clean install
```

### 安装 LoadingView v0.1.1
```
git clone https://github.com/mindpin/LoadingView.git
cd LoadingView
git checkout v0.1.1 -b v0.1.1
mvn clean install
```

### 安装 android-menudrawer v0.1.3
```
git clone https://github.com/mindpin/android-menudrawer.git
cd android-menudrawer
git checkout v0.1.3 -b v0.1.3
mvn clean install
```

### 安装 KCRoundProgressBar v0.1.2
```
git clone https://github.com/mindpin/KCRoundProgressBar.git
cd KCRoundProgressBar
git checkout v0.1.2 -b v0.1.2
mvn clean install
```

### 安装 android.support 19.0.1 到本地 maven 库
测试库依赖这个版本的 android.support 所以需要安装

安装步骤:

1 下载 http://esharedev.oss-cn-hangzhou.aliyuncs.com/android-support-v4.jar 文件


2 安装到 本地 maven 库
```
mvn -q install:install-file -DgroupId=com.android.support -DartifactId=support-v4 -Dversion=19.0.1 -Dpackaging=jar -Dfile="<下载的android-support-v4.jar的完整文件路径>"
```

3 运行测试，如果测试能够运行说明没有问题
```
mvn test
```

## 迁出工程进行开发

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


## SQLite 数据库迁移说明

model 用的 KCAuthenticator 框架，数据库升级比较方便，增加一个 new_version.sql 文件，并写好要运行的SQL语句就可以了

工程写了一个脚本方便生成sql 文件，运行脚本:
```
cd kc-android
python migrate.py
```
就可以创建SQL文件
