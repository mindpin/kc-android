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

### 安装KCAuthenticator v0.0.2
```
git clone https://github.com/mindpin/KCAuthenticator
cd KCAuthenticator
git checkout v0.0.2 -b v0.0.2
mvn clean install
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
