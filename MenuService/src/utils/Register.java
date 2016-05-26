package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.menu.util.JdbcUtils;

public class Register {
	public static boolean isRegister()
	{
		Connection conn = JdbcUtils.getPoolConnection();
		//MD5Digest md5Digest = new MD5Digest();
		long count = 0;
		try {
			String sql = "select *  from register";
			PreparedStatement st = JdbcUtils.prepareStatement(conn, sql);
			ResultSet rs = JdbcUtils.executeQuery(st);
			while (rs.next()) {				
				count++;
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.disConnect(conn);
		}
		return count > 0;
	}
}
