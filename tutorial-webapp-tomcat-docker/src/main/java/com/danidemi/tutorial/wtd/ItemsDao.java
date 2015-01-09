package com.danidemi.tutorial.wtd;

import java.util.List;

public interface ItemsDao {

	public void addItem(String user, String valueToAdd) throws Exception;

	public void removeItem(String user, String valueToRemove) throws Exception;

	public List<String> items(String user) throws Exception;

}
