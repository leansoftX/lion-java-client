# lion-java-sdk
Lion Client SDK for Java & Android Native App

## 快速开始
### 1. 下载 Java SDK
导航至 [SDK下载页面](https://github.com/leansoftX/lion-java-client/releases) 下载最新版本SDK.
### 2. 引入导入命名空间
``` 
import com.lion.client.sdk.LionClient;
import com.lion.client.sdk.LionUser;

```
### 3. 在项目中创建LionClient对象
```
LionClient client = new LionClient("environment-sdk","api-url");
```

## 创建你的第一个功能开关
### 4. 在Lion门户上的对应业务系统里创建你需要控制的 “功能标记”
### 5. 在你的业务系统里，通过创建好的 ”功能标记“ 唯一标示，来验证功能是否开启
```
LionUser user = new LionUser("user-unique-key");                //Mandatory
user.setName("user-name");                                      //Optional
user.setEmail("user-email");                                    //Optional
HashMap<String, String> customAttribute = user.getCustom();
customAttribute.put("Age", "31");                               //Optional
customAttribute.put("Phone", "123456789");                      //Optional
customAttribute.put("City", "GuangZhou");                       //Optional
final boolean featureStatus = client.BoolVariation("feature-key", user);
if (featureStatus) {
    //Business code when feature is on
}
else {
    //Business code when feature is off
}
```