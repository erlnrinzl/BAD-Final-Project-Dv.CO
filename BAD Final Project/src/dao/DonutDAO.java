package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Donut;

public class DonutDAO extends DatabaseConfig {

	public void create(Donut donut) {
		String sql = "INSERT INTO msdonut (DonutID, DonutName, DonutDescription, DonutPrice) VALUES (?, ?, ?, ?)";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, donut.getDonutID());
			stmt.setString(2, donut.getDonutName());
			stmt.setString(3, donut.getDonutDescription());
			stmt.setDouble(4, donut.getDonutPrice());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Donut> read() {
		List<Donut> donuts = new ArrayList<>();
		String sql = "SELECT * FROM msdonut LIMIT 10";
		
		try {
			Connection conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				donuts.add(new Donut(rs.getString("DonutID"), rs.getString("DonutName"), rs.getString("DonutDescription"), rs.getDouble("DonutPrice")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	      return donuts;
	}
	
	public void update(Donut donut) {
		String sql = "UPDATE msdonut SET DonutName = ?, DonutDescription = ?, DonutPrice = ? WHERE DonutID = ?";
		
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, donut.getDonutName());
			stmt.setString(2, donut.getDonutDescription());
			stmt.setDouble(3, donut.getDonutPrice());
			stmt.setString(4, donut.getDonutID());
			
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(String donutId) {
		String sql = "DELETE FROM msdonut WHERE DonutID = ?";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, donutId);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Donut getDonutByID(String donutId) {
		String sql = "SELECT * FROM msdonut WHERE DonutID = ?";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, donutId);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				return new Donut(
					rs.getString("DonutID"), 
					rs.getString("DonutName"), 
					rs.getString("DonutDescription"), 
					rs.getDouble("DonutPrice")
				);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
