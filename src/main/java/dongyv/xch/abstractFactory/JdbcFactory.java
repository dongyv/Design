package dongyv.xch.abstractFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

//其中A工厂的产品
public interface JdbcFactory {
	public Connection open() throws SQLException;
	public ResultSet createQuery(String sql,Connection con);
	public void close(Connection con,ResultSet rs);
}
