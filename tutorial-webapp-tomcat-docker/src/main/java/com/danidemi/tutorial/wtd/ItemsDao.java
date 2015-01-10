package com.danidemi.tutorial.wtd;

import java.util.List;

import org.apache.commons.dbcp2.BasicDataSource;

public interface ItemsDao {

	public void addItem(String user, String valueToAdd) throws Exception;

	public void removeItem(String user, String valueToRemove) throws Exception;

	public List<String> items(String user) throws Exception;

	public boolean needDatasource();

	public void setDatasource(BasicDataSource ds);

}
