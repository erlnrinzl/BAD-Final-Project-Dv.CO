package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cart;
import model.User;

public class UserDAO {

	public User getByEmail(String email) {
		String sql = "SELECT * FROM msuser WHERE Email = ?";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				User user = new User();

				user.setUserID(rs.getString("UserID"));
				user.setUsername(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setAge(rs.getInt("Age"));
				user.setGender(rs.getString("Gender"));
				user.setCountry(rs.getString("Country"));
				user.setPhoneNumber(rs.getString("PhoneNumber"));
				user.setRole(rs.getString("Role"));

				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}
