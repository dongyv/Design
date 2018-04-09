package dongyv.xch.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dongyv.xch.abstractFactory.AbstaFactImpl;
import dongyv.xch.abstractFactory.AbstractFactory;
import dongyv.xch.abstractFactory.JdbcFactory;
import dongyv.xch.sqlAnnocation.MyMethod.SqlMethod;
import dongyv.xch.sqlAnnocation.MyParse.SelectAnnotation;

public class GoodsService {
	public String goodById(int id) {
		String name = null;
		AbstractFactory af = new AbstaFactImpl();
		JdbcFactory jf = af.factoryA();
		Connection con;
		try {
			con = jf.open();
			SelectAnnotation sa = new SelectAnnotation();
			String sql =(String) sa.parseMethod(SqlMethod.class,"selectGoods",String.valueOf(id));
			ResultSet rs = jf.createQuery(sql, con);
			System.out.println(sql);
			System.out.println("执行结果如下所示:");
			System.out.println("-----------------");
			System.out.println("id"+"\t"+"姓名" + "\t" + "价格");
			System.out.println("-----------------");
			
			String id1 = null;
			BigDecimal price = new BigDecimal(0);
			while (rs.next()) {
				// 获取stuname这列数据
				name = rs.getString("name");
				// 获取stuid这列数据
				id1 = rs.getString("id");
				price = rs.getBigDecimal("price");
				// 输出结果
				System.out.println(id1 + "\t" + name + "\t" + price);
			}
			jf.close(con, rs);
			
			af.factoryB().method1();
			af.factoryB().method2();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return name;
	}
}
