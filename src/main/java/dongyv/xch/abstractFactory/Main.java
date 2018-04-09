package dongyv.xch.abstractFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dongyv.xch.sqlAnnocation.MyMethod.SqlMethod;
import dongyv.xch.sqlAnnocation.MyParse.SelectAnnotation;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
//		Object[] obj = new Object[1];
//		obj[0]="1";
		AbstractFactory af = new AbstaFactImpl();
		JdbcFactory jf = af.factoryA();
		Connection con = jf.open();
		SelectAnnotation sa = new SelectAnnotation();
		String sql =(String) sa.parseMethod(SqlMethod.class,"selectGoods","1");
		ResultSet rs = jf.createQuery(sql, con);
		System.out.println(sql);
		System.out.println("执行结果如下所示:");
		System.out.println("-----------------");
		System.out.println("id"+"\t"+"姓名" + "\t" + "价格");
		System.out.println("-----------------");

		String name = null;
		String id = null;
		BigDecimal price = new BigDecimal(0);
		while (rs.next()) {
			// 获取stuname这列数据
			name = rs.getString("name");
			// 获取stuid这列数据
			id = rs.getString("id");
			price = rs.getBigDecimal("price");
			// 输出结果
			System.out.println(id + "\t" + name + "\t" + price);
		}
		jf.close(con, rs);
		
		af.factoryB().method1();
		af.factoryB().method2();
	}

}
