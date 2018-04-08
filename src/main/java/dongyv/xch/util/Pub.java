package dongyv.xch.util;

public class Pub {
	public static final String DIRVER = "com.mysql.jdbc.Driver";
	// URL指向要访问的数据库名mydata
	public static final String URL = "jdbc:mysql://localhost:3306/dongyv";
	// MySQL配置时的用户名
	public static final String USER = "root";
	// MySQL配置时的密码
	public static final String PASSWORD = "root";
	// MySQL连接池的大小
	public static final int JDBC_POOL_INIT_SIZE = 5;
	// MySQL 连接池最大线程
	public static final int JDBC_POOL_MAX_SIZE = 10;
}
