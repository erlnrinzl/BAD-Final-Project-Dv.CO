package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;
import util.StringHelper;

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

	public User create(User user) {
		String sql = "INSERT INTO msuser (UserID, Username, Email, Password, Age, Gender, Country, PhoneNumber, Role) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			String userID = this.getCreateID();
			
			statement.setString(1, userID);
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getGender());
			statement.setString(7, user.getCountry());
			statement.setString(8, user.getPhoneNumber());
			statement.setString(9, user.getRole());

			if (statement.executeUpdate() > 0) {
				user.setUserID(userID);
				return user;
			}

			throw new SQLException("DB Error");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getCreateID() throws SQLException {
		String sql = "SELECT UserID FROM msuser ORDER BY UserID DESC LIMIT 1";

		Connection connection = DatabaseConfig.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		if (rs.next()) {
			String latestId = rs.getString("UserID");
			String latestIdDigits = latestId.replace("US", "");
			Integer id = Integer.parseInt(latestIdDigits) + 1;

			return "US" + StringHelper.addLeadingZeros(id, 3);
		}

		return null;
	}

}
