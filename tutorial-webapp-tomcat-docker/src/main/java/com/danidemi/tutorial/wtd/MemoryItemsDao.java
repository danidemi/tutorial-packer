package com.danidemi.tutorial.wtd;

import java.awt.ItemSelectable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryItemsDao implements ItemsDao {

	private static final Map<String,ArrayList<String>> values = new HashMap<String, ArrayList<String>>();

	@Override
	public void addItem(String user, String valueToAdd) {
		items(user).add(valueToAdd);	
	}
	
	@Override
	public void removeItem(String user, String valueToRemove) {
		items(user).remove(valueToRemove);
	}
	
	public List items(String user) {
		ArrayList<String> list = (ArrayList<String>)values.get(user);
		if(list == null) {
			list = new ArrayList<String>();
			values.put(user, list);
		}
		return list;
	}
	
}
