package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.simple.JSONObject;

import model.Todo;

public class ToDoDao {
	public static ToDoDao dao;
	private Connection conn;
	private PreparedStatement prep;
	
	private static final String SELECT_ALL = "select * from todo";
	private static final String ADD_TODO = "insert into todo(info) values(?)";
	private static final String GET_ID = "select id from todo order by id desc limit 1";
	private static final String REMOVE_TODO = "delete from todo where id = ?";
	private static final String CHECK_LIST = "update todo set checked = ? where id = ?";
	private static final String IS_CHECKED = "select checked from todo where id = ?";
	
	private ToDoDao() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tododata", "root", "fightclub");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			System.out.println("connection created");
		}
	}
	
	public static ToDoDao getDAO() {
		if (dao == null) {
			dao = new ToDoDao();
		}
		return dao;
	}
	
	public JSONObject getList() {
		JSONObject list = new JSONObject(), temp = new JSONObject();
		try {
			prep = conn.prepareStatement(SELECT_ALL);
			ResultSet rs = prep.executeQuery();
			while (rs.next()) {
				temp.put("id", rs.getInt("id"));
				temp.put("input", rs.getString("info"));
				temp.put("check", rs.getInt("checked"));
				list.put(rs.getInt("id"), new JSONObject(temp));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int addToList(String data) { 
		try {
			prep = conn.prepareStatement(ADD_TODO);
			prep.setString(1, data);
			prep.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return getLastId();
	}
	
	public int getLastId() {
		try {
			prep = conn.prepareStatement(GET_ID);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean removeFromList(int id) {
		try {
			prep = conn.prepareStatement(REMOVE_TODO);
			prep.setInt(1, id);
			prep.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean checkInList(int id, int check) {
		try {
			prep = conn.prepareStatement(CHECK_LIST);
			prep.setInt(1, check);
			prep.setInt(2, id);
			prep.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public int isChecked(int id) {
		try {
			prep = conn.prepareStatement(IS_CHECKED);
			prep.setInt(1, id);
			ResultSet rs = prep.executeQuery();
			if (rs.next()) {
				if (rs.getInt(1)==1) {
					checkInList(id, 0);
					return 0;
				}
				else {
					checkInList(id,1);
					return 1;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
