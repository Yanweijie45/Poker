package game;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql {
	
	// 连接数据库的五大参数
	public static String driverClass = "com.mysql.jdbc.Driver";//加载数据库驱动
	public static String databaseName = "users";// 连接到哪一个数据库
	public static String serverIp = "localhost";// 服务器Ip地址
	public static String username = "root";// 用户名
	public static String password = "123456";// 密码
	public static Connection con=null;
	
	// 拼凑成一个完整的URL地址
	public static String jdbcUrl = "jdbc:mysql://" + serverIp + ":3306/" + databaseName
			+ "?serverTimezone=Asia/Shanghai&useSSL=true";// 每个数据库都不一样
	
	public Mysql() {
		try {
			if (con == null) {
				Class.forName(driverClass);
				con = DriverManager.getConnection(jdbcUrl, username, password);
				
			}
		} catch (Exception e) {
			System.out.println(con);
		}
	}

	/*
	 * 检查账号和密码是否正确 若正确，正状态置1
	 */
	public String Check(String id, String psw) {
		String info = "0"; // 账号或密码错误
		try {
			String sql = "select * from user where account=? and password=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, psw);
			ResultSet rs = ps.executeQuery();
			System.out.println("1");
			/*if (rs.next()) {
				if (rs.getString("state").trim().equals("1")) {
					info = "1"; // 已登录
				} else {
					info = "2"; // 成功登录
				}
			}*/
			if(rs!=null) {
				info="2";
			}
			ps.close();
		} catch (SQLException e) {
			System.out.println("2");
		}
		return info;
	}

