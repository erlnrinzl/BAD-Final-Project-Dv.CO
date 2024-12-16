package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TransactionHeader;

public class TransactionHeaderDAO {
	
    public String generateTransactionID() {
        String sql = "SELECT * FROM transactionheader ORDER BY TransactionID DESC LIMIT 1";
        
        try {
        	Connection connection = DatabaseConfig.getConnection();
        	PreparedStatement statement = connection.prepareStatement(sql);
        	ResultSet rs = statement.executeQuery();
        	
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
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, transactionHeader.getTransactionID());
			statement.setString(2, transactionHeader.getUserID());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
