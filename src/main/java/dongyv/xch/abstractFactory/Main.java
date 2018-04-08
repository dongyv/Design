package dongyv.xch.abstractFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dongyv.xch.sqlAnnocation.MyMethod.SqlMethod;
import dongyv.xch.sqlAnnocation.MyParse.SelectAnnotation;

public class Main {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		AbstractFactory af = new AbstaFactImpl();
		JdbcFactory jf = af.factoryA();
		Connection con = jf.open();
		SelectAnnotation sa = new SelectAnnotation();
		String sql =(String) sa.parseMethod(SqlMethod.class,"select1");
		ResultSet rs = jf.createQuery(sql, con);
		System.out.println("执行结果如下所示:");
		System.out.println("-----------------");
		System.out.println("姓名" + "\t" + "职称");
		System.out.println("-----------------");

		String user1 = null;
		String id = null;
		int flag = 0;
		while (rs.next()) {
			// 获取stuname这列数据
			user1 = rs.getString("user");
			// 获取stuid这列数据
			id = rs.getString("id");
			flag = rs.getInt("flag");
			// 输出结果
			System.out.println(id + "\t" + user1 + "\t" + flag);
		}
		jf.close(con, rs);
		
		af.factoryB().method1();
		af.factoryB().method2();
	}

}
