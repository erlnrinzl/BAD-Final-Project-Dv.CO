package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;

public class CartDAO {
	DonutDAO donutDAO = new DonutDAO();

	public void create(Cart cart) {
		String sql = "INSERT INTO cart (UserID, DonutID, Quantity) VALUES (?, ?, ?)";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, cart.getUserID());
			statement.setString(2, cart.getDonutID());
			statement.setInt(3, cart.getQuantity());

			statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Cart> read(String userID) {
		List<Cart> carts = new ArrayList<>();
		String sql = "SELECT * FROM cart WHERE UserID = ?";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, userID);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				carts.add(new Cart(rs.getString("UserID"), rs.getInt("Quantity"),
						donutDAO.getDonutByID(rs.getString("DonutID"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return carts;
	}

	public void update(Cart cart) {
		String sql = "UPDATE cart SET Quantity = ? WHERE UserID = ? AND DonutID = ?";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, cart.getQuantity());
			statement.setString(2, cart.getUserID());
			statement.setString(3, cart.getDonutID());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(Cart cart) {
		String sql = "DELETE FROM cart WHERE UserID = ? AND DonutID = ?";
		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, cart.getUserID());
			statement.setString(2, cart.getDonutID());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
