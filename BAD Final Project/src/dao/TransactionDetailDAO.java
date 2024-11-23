package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.TransactionDetail;

public class TransactionDetailDAO extends DatabaseConfig {
	public void create(TransactionDetail transactionDetail) {
		String sql = "INSERT INTO transactiondetail (TransactionID, DonutID, Quantity) VALUES (?, ?, ?)";
		try {
			Connection conn = getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, transactionDetail.getTransactionID());
			stmt.setString(2, transactionDetail.getDonutID());
			stmt.setInt(3, transactionDetail.getQuantity());
			
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
