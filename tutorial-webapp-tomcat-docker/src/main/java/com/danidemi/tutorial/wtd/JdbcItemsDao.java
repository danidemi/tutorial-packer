package com.danidemi.tutorial.wtd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.OperationNotSupportedException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class JdbcItemsDao implements ItemsDao {

	private DataSource ds;

	public JdbcItemsDao() {

	}

	@Override
	public void addItem(String user, String valueToAdd) throws SQLException {
		try (Connection conn = ds.getConnection()) {
			PreparedStatement prepareStatement = conn
					.prepareStatement("INSERT INTO app_items(username, item) VALUES ( ?, ? )");
			prepareStatement.setString(1, user);
			prepareStatement.setString(2, valueToAdd);
			prepareStatement.execute();
			prepareStatement.close();
		} catch (SQLException se) {
			throw se;
		}
	}

	@Override
	public void removeItem(String user, String valueToRemove)
			throws SQLException {
		try (Connection conn = ds.getConnection()) {
			PreparedStatement prepareStatement = conn
					.prepareStatement("DELETE FROM app_items WHERE username = ? and item = ?");
			prepareStatement.setString(1, user);
			prepareStatement.setString(2, valueToRemove);
			prepareStatement.execute();
			prepareStatement.close();
		} catch (SQLException se) {
			throw se;
		}
	}

	@Override
	public List<String> items(String user) throws SQLException {
		try (Connection conn = ds.getConnection()) {
			PreparedStatement prepareStatement = conn
					.prepareStatement("SELECT item FROM app_items WHERE username = ?");
			prepareStatement.setString(1, user);
			ResultSet rs = prepareStatement.executeQuery();
			ArrayList<String> r = new ArrayList<>();
			while (rs.next()) {
				r.add(rs.getString(1));
			}
			prepareStatement.execute();
			prepareStatement.close();
			return r;
		} catch (SQLException se) {
			throw se;
		}
	}

	@Override
	public boolean needDatasource() {
		return true;
	}

	@Override
	public void setDatasource(BasicDataSource ds) {
		this.ds = ds;
	}

}
