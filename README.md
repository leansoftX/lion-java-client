Lion Client SDK for JAVA 
===========================


快速开始
-----------

1. 下载 Java SDK

    导航至 [SDK下载页面](https://github.com/leansoftX/lion-java-client/releases) 下载最新版本SDK.

2. 导入命名空间

        import com.lion.client.sdk.LionClient;
        import com.lion.client.sdk.LionUser;

3. 在项目中创建LionClient对象

         LionClient lionClient = new LionClient("environment-sdk");

创建你的第一个功能开关
-----------------------

4. 在Lion门户上的对应业务系统里创建你需要控制的 “功能标记”。
5. 在你的业务系统里，通过创建好的 ”功能标记“ 唯一标示，来验证功能是否开启

        LionUser user = new LionUser("user-unique-key");        //Mandatory

        user.setName("user-name");                              //Optional

        user.setEmail("user-email");                            //Optional

        HashMap<String, String> customAttribute = user.getCustom();

        customAttribute.put("customer-attribute-key1", "customer-attribute-value1");              //Optional

        customAttribute.put("customer-attribute-key2", "customer-attribute-value2");              //Optional

        customAttribute.put("customer-attribute-key3", "customer-attribute-value3");              //Optional

        boolean featureStatus = client.BoolVariation("feature-key", user);

        if (featureStatus) {
            //Business code when feature is on
        }
        else {
            //Business code when feature is off
        }