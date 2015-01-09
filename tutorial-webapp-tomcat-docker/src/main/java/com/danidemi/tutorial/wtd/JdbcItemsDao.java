package com.danidemi.tutorial.wtd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcItemsDao implements ItemsDao {

	@Override
	public void addItem(String user, String valueToAdd) throws SQLException {
		Connection conn = null;
		PreparedStatement prepareStatement = conn.prepareStatement("INSERT INTO ITEMS(user, item) VALUES ( ?, ? )");
		prepareStatement.setString(1, user);
		prepareStatement.setString(2, valueToAdd);
		prepareStatement.execute();
		prepareStatement.close();
		conn.close();
	}

	@Override
	public void removeItem(String user, String valueToRemove) throws SQLException {
		Connection conn = null;
		PreparedStatement prepareStatement = conn.prepareStatement("DELETE FROM ITEMS WHERE user = ? and item = ?");
		prepareStatement.setString(1, user);
		prepareStatement.setString(2, valueToRemove);
		prepareStatement.execute();
		prepareStatement.close();
		conn.close();
	}

	@Override
	public List<String> items(String user) throws SQLException {
		Connection conn = null;
		PreparedStatement prepareStatement = conn.prepareStatement("SELECT item FROM ITEMS WHERE user = ?");
		prepareStatement.setString(1, user);
		ResultSet rs = prepareStatement.executeQuery();
		ArrayList<String> r = new ArrayList<>();
		while(rs.next()){
			r.add( rs.getString(1) );
		}
		prepareStatement.execute();
		prepareStatement.close();
		conn.close();
		return r;
	}

}
