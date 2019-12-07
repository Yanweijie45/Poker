package game;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

	public Dao() {
		
	}


	public String Check(String id, String psw) {
		String info = "0"; 
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from userinfo where userid=? and password=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, psw);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (rs.getString("state").trim().equals("1")) {
					info = "1"; 
				} else {
					info = "2"; 
				}
			}
			ps.close();
		} catch (SQLException e) {
		}
		return info;
	}


	public boolean Check(String id) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "select * from userinfo where userid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				return true;
			ps.close();
		} catch (SQLException e) {
		}
		return false;
	}


	public void setState(String state, String id) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "update userinfo set state=? where userid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, state);
			ps.setString(2, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public void setAllState(String state) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "update userinfo set state=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, state);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateScore(String _id, int _score) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement sql = con.createStatement();
			sql.execute("update userinfo set score=score+" + _score
					+ "where userid='" + _id + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getScore(String _id) {
		int score = 0;
		try {
			Connection con = JDBCUtil.getConnection();
			Statement sql = con.createStatement();
			ResultSet rs = sql
					.executeQuery("select score from userinfo where userid='"
							+ _id + "'");
			rs.next();
			score = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return score;
	}

	public String getIcon(String _id) {
		String icon = "";
		try {
			Connection con = JDBCUtil.getConnection();
			Statement sql = con.createStatement();
			ResultSet rs = sql
					.executeQuery("select icon from userinfo where userid='"
							+ _id + "'");
			rs.next();
			icon = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return icon;
	}

	public void Adduser(String id, String psw) {
		try {
			Connection con = JDBCUtil.getConnection();
			Statement sql = con.createStatement();
			sql.execute("insert into userinfo(userid,password,state,score,icon)values('"
					+ id + "','" + psw + "','0',0,'0')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
