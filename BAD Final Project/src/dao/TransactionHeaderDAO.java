package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TransactionHeader;

public class TransactionHeaderDAO extends DatabaseConfig {
	
    public String generateTransactionID() {
        String sql = "SELECT COUNT(*) AS total FROM transactionheader";
        try {
        	Connection conn = getConnection();
        	PreparedStatement stmt = conn.prepareStatement(sql);
        	ResultSet rs = stmt.executeQuery();
        	
            if (rs.next()) {
                int totalTransactions = rs.getInt("total") + 1;
                return String.format("TR%03d", totalTransactions);
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
