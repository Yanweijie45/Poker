package game;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mysql {
	
	// �������ݿ��������
	public static String driverClass = "com.mysql.jdbc.Driver";//�������ݿ�����
	public static String databaseName = "users";// ���ӵ���һ�����ݿ�
	public static String serverIp = "localhost";// ������Ip��ַ
	public static String username = "root";// �û���
	public static String password = "123456";// ����
	public static Connection con=null;
	
	// ƴ�ճ�һ��������URL��ַ
	public static String jdbcUrl = "jdbc:mysql://" + serverIp + ":3306/" + databaseName
			+ "?serverTimezone=Asia/Shanghai&useSSL=true";// ÿ�����ݿⶼ��һ��
	
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
	 * ����˺ź������Ƿ���ȷ ����ȷ����״̬��1
	 */
	public String Check(String id, String psw) {
		String info = "0"; // �˺Ż��������
		try {
			String sql = "select * from user where account=? and password=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, psw);
			ResultSet rs = ps.executeQuery();
			System.out.println("1");
			/*if (rs.next()) {
				if (rs.getString("state").trim().equals("1")) {
					info = "1"; // �ѵ�¼
				} else {
					info = "2"; // �ɹ���¼
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

