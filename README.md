# Java 设计

更新记录：

2018-04-6：
初步研发，实现俩个基本功能

=============================================================================================

1. 项目背景：

由于笔者的知识太过碎片化,需要项目的整合,故研发次项目

2. 功能说明：

本项目实现注解实现工厂生产连接对象，数据库对象,通过注解得到sql语句,查询数据库.实现ssm框架中,观察者模式的运用


3. 环境说明：

JDK1.8+

4. 实现代码和效果
1.抽象工厂的数据库连接
~
		AbstractFactory af = new AbstaFactImpl();
		JdbcFactory jf = af.factoryA();
    	Connection con = jf.open();
		SelectAnnotation sa = new SelectAnnotation();
		String sql =(String) sa.parseMethod(SqlMethod.class,"select1");
		ResultSet rs = jf.createQuery(sql, con);
~

![Image text](http://www.dongyv.com/picture/2018.4.8/cs1.png)
2.观察者模式中用户注册的动作
~
        rgsobv.addUser(register);
		List<Registers> regiter = rgsobv.lists;
		for(Registers register1:regiter) {
			System.out.println("当前运行第"+register1.getUser().getUser());
		}
		rgsobv.noty(user);//通知具体用户方法
~
![Image text](http://www.dongyv.com/picture/2018.4.8/cs2.png)