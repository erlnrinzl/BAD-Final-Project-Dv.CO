package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Donut;
import util.StringHelper;

public class DonutDAO {

	public Donut create(Donut donut) {
		String sql = "INSERT INTO msdonut (DonutID, DonutName, DonutDescription, DonutPrice) VALUES (?, ?, ?, ?)";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			String donutID = this.getCreateID();

			statement.setString(1, donutID);
			statement.setString(2, donut.getDonutName());
			statement.setString(3, donut.getDonutDescription());
			statement.setDouble(4, donut.getDonutPrice());

			if (statement.executeUpdate() > 0) {
				donut.setDonutID(donutID);
				return donut;
			}

			throw new SQLException("DB Error");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<Donut> read() {
		List<Donut> donuts = new ArrayList<>();
		String sql = "SELECT * FROM msdonut";

		try {
			Connection connection = DatabaseConfig.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);

			while (rs.next()) {
				Donut donut = new Donut(rs.getString("DonutID"), rs.getString("DonutName"),
						rs.getString("DonutDescription"), rs.getDouble("DonutPrice"));

				donuts.add(donut);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return donuts;
	}

	public void update(Donut donut) {
		String sql = "UPDATE msdonut SET DonutName = ?, DonutDescription = ?, DonutPrice = ? WHERE DonutID = ?";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, donut.getDonutName());
			statement.setString(2, donut.getDonutDescription());
			statement.setDouble(3, donut.getDonutPrice());
			statement.setString(4, donut.getDonutID());

			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(String donutId) {
		String sql = "DELETE FROM msdonut WHERE DonutID = ?";
		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, donutId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Donut getDonutByID(String donutId) {
		String sql = "SELECT * FROM msdonut WHERE DonutID = ?";

		try {
			Connection connection = DatabaseConfig.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, donutId);
			ResultSet rs = statement.executeQuery();

			if (rs.next()) {
				return new Donut(rs.getString("DonutID"), rs.getString("DonutName"), rs.getString("DonutDescription"),
						rs.getDouble("DonutPrice"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String getCreateID() throws SQLException {
		String prefixID = "DN";
		String sql = "SELECT DonutID FROM msdonut ORDER BY DonutID DESC LIMIT 1";

		Connection connection = DatabaseConfig.getConnection();
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);

		if (rs.next()) {
			String latestId = rs.getString("DonutID");
			String latestIdDigits = latestId.replace(prefixID, "");
			Integer id = Integer.parseInt(latestIdDigits) + 1;

			return prefixID + StringHelper.addLeadingZeros(id, 3);
		}

		return null;
	}
}
