package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import model.Model;

abstract public class DAO<T extends Model> {

	abstract protected String getTableName();

	abstract protected String getKeyName();

	abstract protected String[] getTableColumns();

	abstract public T get(String id);

	abstract public List<T> getAll();

	abstract public T save(T model);

	abstract protected T create(T model);

	abstract protected T update(T model);

	abstract public boolean delete(T model);

	abstract public boolean delete(String id);

	abstract protected String getInsertId(T model) throws SQLException;

	abstract protected void fillPropertiesFromSQLResultSet(T model, ResultSet rs) throws SQLException;

	protected String buildInsertQuery() {
		StringBuilder query = new StringBuilder();

		query.append("INSERT INTO ").append(getTableName()).append(" (");
		query.append(getKeyName());

		for (String column : getTableColumns()) {
			query.append(", ").append(column);
		}

		query.append(") VALUES (?");

		for (String column : getTableColumns()) {
			query.append(", ?");
		}

		query.append(")");

		return query.toString();
	}

	protected String buildUpdateQuery() {
		StringBuilder query = new StringBuilder();

		query.append("UPDATE ").append(getTableName()).append(" SET ");

		for (String column : getTableColumns()) {
			query.append(column).append(" = ?,");
		}
		
		query.deleteCharAt(query.length() - 1);
		query.append(" WHERE ").append(getKeyName()).append(" = ?");

		return query.toString();
	}
	
	protected void handleSQLException(SQLException error) {
		System.err.println("Database error: " + error.getMessage());
		error.printStackTrace();

		throw new RuntimeException("Database operation failed", error);
	}
}
