package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.User;
import util.StringHelper;

public class UserDAO extends DAO<User> {

	@Override
	protected String getTableName() {
		return "msuser";
	}

	@Override
	protected String getKeyName() {
		return "UserID";
	}

	@Override
	protected String[] getTableColumns() {
		String[] columns = { "Username", "Email", "Password", "Age", "Gender", "Country", "PhoneNumber", "Role" };
		return columns;
	}

	@Override
	public User get(String id) {
		String sql = "SELECT * FROM " + this.getTableName() + " WHERE " + this.getKeyName() + " = ?";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, id);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				User user = new User();
				this.fillPropertiesFromSQLResultSet(user, rs);

				return user;
			}
		} catch (SQLException error) {
			handleSQLException(error);
		}

		return new User();
	}

	@Override
	public List<User> getAll() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM " + this.getTableName();

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				User user = new User();
				this.fillPropertiesFromSQLResultSet(user, rs);

				users.add(user);
			}
		} catch (SQLException error) {
			handleSQLException(error);
		}

		return users;
	}

	@Override
	public User save(User user) {
		return user.exists() ? this.update(user) : this.create(user);
	}

	@Override
	protected User create(User user) {
		String sql = this.buildInsertQuery();

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, this.getInsertId(user));
			statement.setString(2, user.getUsername());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getAge());
			statement.setString(6, user.getGender());
			statement.setString(7, user.getCountry());
			statement.setString(8, user.getPhoneNumber());
			statement.setString(9, user.getRole());
			
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 0) {
				throw new SQLException("Create operation failed. No rows affected.");
			}

			return user;
		} catch (SQLException error) {
			handleSQLException(error);
		}

		return user;
	}

	@Override
	protected User update(User user) {
		String sql = this.buildUpdateQuery();

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, user.getUsername());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getPassword());
			statement.setInt(4, user.getAge());
			statement.setString(5, user.getGender());
			statement.setString(6, user.getCountry());
			statement.setString(7, user.getPhoneNumber());
			statement.setString(8, user.getRole());
			statement.setString(9, user.getId());

			int rowsAffected = statement.executeUpdate();
			if (rowsAffected == 0) {
				throw new SQLException("Update operation failed. No rows affected.");
			}

			return user;
		} catch (SQLException error) {
			handleSQLException(error);
		}

		return user;
	}

	@Override
	public boolean delete(User model) {
		String id = model.getId();
		return this.delete(id);
	}

	@Override
	public boolean delete(String id) {
		String sql = "DELETE FROM " + this.getTableName() + " WHERE " + this.getKeyName() + " = ?";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, id);
			int rowsAffected = statement.executeUpdate();

			return rowsAffected > 0;
		} catch (SQLException error) {
			handleSQLException(error);
		}

		return false;
	}

	@Override
	protected String getInsertId(User user) throws SQLException {
		String sql = "SELECT " + this.getKeyName() + " FROM " + this.getTableName() + " ORDER BY " + this.getKeyName()
				+ " DESC LIMIT 1";

		Connection connection = DatabaseConfig.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		if (rs.next()) {
			String latestId = rs.getString(this.getKeyName());
			String latestIdDigits = latestId.replace(user.getIdPrefix(), "");
			Integer id = Integer.parseInt(latestIdDigits) + 1;

			return user.getIdPrefix() + StringHelper.addLeadingZeros(id, 3);
		}

		return null;
	}

	@Override
	protected void fillPropertiesFromSQLResultSet(User user, ResultSet rs) throws SQLException {
		user.setId(rs.getString(this.getKeyName()));
		user.setUsername(rs.getString("Username"));
		user.setEmail(rs.getString("Email"));
		user.setPassword(rs.getString("Password"));
		user.setAge(rs.getInt("Age"));
		user.setGender(rs.getString("Gender"));
		user.setCountry(rs.getString("Country"));
		user.setPhoneNumber(rs.getString("PhoneNumber"));
		user.setRole(rs.getString("Role"));
	}

}
