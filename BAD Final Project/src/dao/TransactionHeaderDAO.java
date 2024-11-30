package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TransactionHeader;

public class TransactionHeaderDAO extends DatabaseConfig {
	
    public String generateTransactionID() {
        String sql = "SELECT * FROM transactionheader ORDER BY TransactionID DESC LIMIT 1";
        try {
        	Connection conn = getConnection();
        	PreparedStatement stmt = conn.prepareStatement(sql);
        	ResultSet rs = stmt.executeQuery();
        	
            if (rs.next()) {
            	String lastTransactionID = rs.getString("TransactionID");
                Integer nextTransactionID = Integer.parseInt(lastTransactionID.split("TR")[1]) + 1;
                return String.format("TR%03d", nextTransactionID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "TR001";
    }
    
	public void create(TransactionHeader transactionHeader) {
		String sql = "INSERT INTO transactionheader (TransactionID, UserID) VALUES (?, ?)";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, transactionHeader.getTransactionID());
			stmt.setString(2, transactionHeader.getUserID());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
