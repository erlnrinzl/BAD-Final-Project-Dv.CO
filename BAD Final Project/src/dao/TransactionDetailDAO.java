package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.TransactionDetail;

public class TransactionDetailDAO {
	
	public void create(TransactionDetail transactionDetail) {
		String sql = "INSERT INTO transactiondetail (TransactionID, DonutID, Quantity) VALUES (?, ?, ?)";
		
		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, transactionDetail.getTransactionID());
			statement.setString(2, transactionDetail.getDonutID());
			statement.setInt(3, transactionDetail.getQuantity());
			
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
