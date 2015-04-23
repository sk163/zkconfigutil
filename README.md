zkconfigutil
============

configuration your project with zookeeper,it work with java annotation and easy play


============
我的工程中有个Demo是整个工程的配置项，如下：

     @TypeZkConfigurable
     public final class Demo {
 
        @FieldZkConfigurable(dynamicUpdate = true)
        public static String F1 = "F1";
 
        @FieldZkConfigurable(dynamicUpdate = true)
        public static String F2 = "F2";
 
        @FieldZkConfigurable(dynamicUpdate = true)
        public static Boolean F3 = false;
 
        @FieldZkConfigurable
        public static Boolean F4 = true;
    }
这个配置项F1字段dynamicUpdate = true，代表这个字段需要动态更新，即zookeeper上的值变化后F1需要做出相应修改。F4字段采用采用默认dynamicUpdate false，也就是不需要动态更新。

如何使用Demo：

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            System.out.println("Demo.F1 = " + Demo.F1);
            loop();
        }
    }
    public static void loop() throws InterruptedException {
        Thread.sleep(2000L);
    }
这段代码不需多解释，程序入口，2s打印一次F1（这个字段可是动态更新的哦！）

ok代码已经写完了，是不是对您的代码完全没有侵入呢？

 看启动：

    -javaagent:/home/jerry/ZKCUAgent.jar=zk@10.31.44.38:2181#class@com.jerry.zkconfigutil.Demo

 这里主要用了javaagent，在jvm参数中添加上述参数，首先-javaagent指定我们的agent的jar，这里在我的home目录下，然后=号用来指定agent的agentOps，也就是参数，格式为zk@z1,z2#class@c1,c2,c3。

ok，带上javaagent参数后直接启动，现在我们的Demo已经实现了zookeeper化配置。

 

  使用起立是不是非常easy，并且实用，通过jvm的javaagent参数进行控制，对服务实现零侵入。与zookeeper的eclipse插件是个完美的组合！！！

  这是小弟发布的第一个正式版本，在osc的git仓库和github上都有完整代码及测试Demo，如果你有更好的想法真切希望你加入！！！
