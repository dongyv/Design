package dongyv.xch.abstractFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dongyv.xch.sqlAnnocation.MyMethod.SqlMethod;
import dongyv.xch.sqlAnnocation.MyParse.SelectAnnotation;


//A工厂的实现类
public class JdbcFactAImpl implements JdbcFactory {
	private static Connection con = null;
	private static JdbcPool jp = new JdbcPool();//开启线程池 存放5个线程;
	
	//开启一个数据库连接 
	@Override
	public Connection open() throws SQLException{
		con = jp.getConnection();
		return con;
	}
	public static Connection open1() throws SQLException{
		con = jp.getConnection();
		return con;
	}
	
	public static void main(String args[]) throws SQLException {
		for(int i=0;i<6;i++	) {
			Connection open1 = open1();
			SelectAnnotation sa = new SelectAnnotation();
			String sql =(String) sa.parseMethod(SqlMethod.class,"select1");
			System.out.println(sql);
			ResultSet rs = createQuery1(sql,open1);
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
		}
	}
	
	public static ResultSet createQuery1(String sql,Connection con) {
		// TODO Auto-generated method stub
		// 2.创建statement类对象，用来执行SQL语句！！
		Statement statement;
		ResultSet rs = null;
		try {
			statement = (Statement) con.createStatement();
			rs = (ResultSet) statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	@Override
	public ResultSet createQuery(String sql,Connection con) {
		// TODO Auto-generated method stub
		// 2.创建statement类对象，用来执行SQL语句！！
		Statement statement;
		ResultSet rs = null;
		try {
			statement = (Statement) con.createStatement();
			rs = (ResultSet) statement.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 要执行的SQL语句
//		String sql = "select * from users";
		// 3.ResultSet类，用来存放获取的结果集！！
		/*System.out.println("-----------------");
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
			id = rs.getString("ename");
			flag = rs.getInt("flag");
			// 输出结果
			System.out.println(id + "\t" + user1 + "\t" + flag);
		}*/
		return rs;
	}
	@Override
	public void close(Connection con, ResultSet rs) {
		// TODO Auto-generated method stub
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
