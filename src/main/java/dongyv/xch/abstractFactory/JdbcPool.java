package dongyv.xch.abstractFactory;

import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import javax.sql.DataSource;

import dongyv.xch.util.Pub;

public class JdbcPool implements DataSource {
	/**
	 * @Field: listConnections 使用LinkedList集合来存放数据库链接，
	 *         由于要频繁读写List集合，所以这里使用LinkedList存储数据库连接比较合适
	 */
	private static LinkedList<Connection> listConnections = new LinkedList<Connection>();
	private static LinkedBlockingQueue<Connection> blockQueue = new LinkedBlockingQueue<>(5);
	static {
		try {
			// 加载驱动程序
			Class.forName(Pub.DIRVER);
			// 1.getConnection()方法，连接MySQL数据库！！
			for (int i = 0; i < Pub.JDBC_POOL_INIT_SIZE; i++) {

				Connection con = (Connection) DriverManager.getConnection(Pub.URL, Pub.USER, Pub.PASSWORD);
				if (!con.isClosed()) {
					System.out.println("Succeeded connecting to the Database!");
				}
				listConnections.add(con);
			}
		} catch (ClassNotFoundException e) {
			// 数据库驱动类异常处理
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("数据库数据成功获取！！");
		}
	}
	/**
	 * 释放连接后 重新开启连接
	 * @param con
	 */
	public void realize() {
		for(int i=0;i<listConnections.size();i++) {
			Connection con = listConnections.get(i);
			try {
				con = (Connection) DriverManager.getConnection(Pub.URL, Pub.USER, Pub.PASSWORD);
				listConnections.set(i, con);
				if (!con.isClosed()) {
					System.out.println("Succeeded again to the Database!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				System.out.println("数据库数据重新成功获取！！");
			}
		}
	}
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setLoginTimeout(int arg0) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		// 如果数据库连接池中的连接对象的个数大于0
		if (listConnections.size() > 0) {
			// 从listConnections集合中获取一个数据库连接
			final Connection conn = listConnections.removeFirst();
			System.out.println("listConnections数据库连接池大小是" + listConnections.size());
			System.out.println("interface:"+conn.getClass().getInterfaces());
			// 返回Connection对象的代理对象 
			//目标对象为JdbcPool 目标方法为 Connection调用的方法 
			//调用规则则是 如果不是close方法就直接调用，是close方法则是需要把当前connection释放
			return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(),
					new InvocationHandler() {
						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							System.out.println("method.name=="+method.getName());
							if (!method.getName().equals("close")) {
								return method.invoke(conn, args);
							} else {
								// 如果调用的是Connection对象的close方法，就把conn还给数据库连接池
								listConnections.add(conn);
								System.out.println(conn + "被还给listConnections数据库连接池了！！");
								System.out.println("listConnections数据库连接池大小为" + listConnections.size());
								return null;
							}
						}
					});
		} else {
			throw new RuntimeException("对不起，数据库忙");
		}
	}

	@Override
	public Connection getConnection(String arg0, String arg1) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
