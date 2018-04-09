# Java 设计

更新记录：

2018-04-08：
初步研发，实现俩个基本功能

2018-04-09：
整合nio功能，实现客户端和服务器通信

=================================================================

1. 项目背景：

由于笔者的知识太过碎片化,需要项目的整合,故研发次项目

2. 功能说明：

本项目通过nio实现客户端和服务器的通信.  
客户端为用户对象,通过ssm框架控制,页面ajax进行前后交互,并且整合观察者模式,实现每个用户登录都能通知系统.并且发送商品信息给客户端(商品信息为模拟)  
服务器为自定义的抽象工厂,注解反射sql，工厂装配自定义简易的数据库连接池,根据客户端传来的值查找到对应的数据反馈给客户端

3. 环境说明：

JDK1.8+

4. 实现代码和效果

>1.抽象工厂的数据库连接
```java
AbstractFactory af = new AbstaFactImpl();
JdbcFactory jf = af.factoryA();
Connection con = jf.open();
SelectAnnotation sa = new SelectAnnotation();
String sql =(String) sa.parseMethod(SqlMethod.class,"select1");
ResultSet rs = jf.createQuery(sql, con);
```

>![Image text](http://www.dongyv.com/picture/2018.4.8/cs1.png)

>2.观察者模式中用户注册的动作
```java
rgsobv.addUser(register);
List<Registers> regiter = rgsobv.lists;
for(Registers register1:regiter) {
	System.out.println("当前运行第"+register1.getUser().getUser());
}
rgsobv.noty(user);
```

>![Image text](http://www.dongyv.com/picture/2018.4.8/cs2.png)

>3.sql语句写法,添加了通配符
```java
@Select(table="goods")
@Where(keys = { "id" }, values = { "#{name}" })
public String selectGoods(String sql) {
    return sql;
}
//反射语句
String sql =(String) sa.parseMethod(SqlMethod.class,"selectGoods",String.valueOf(id);//id做了随机数处理，0-4整数
```

>4.nio客户端和服务器实现
>![Image text](http://www.dongyv.com/picture/2018.4.9/cs1.png)
>![Image text](http://www.dongyv.com/picture/2018.4.9/cs2.png)

5. 目标

根据注解中的通配符自动更新对应SQL  
完善连接池  
实现AOP和IOC