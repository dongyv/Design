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

本项目实现注解实现工厂生产连接对象，数据库对象,通过注解得到sql语句,查询数据库.实现ssm框架中,观察者模式的运用


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