package com.danidemi.tutorial.wtd;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().addServlet("tableServlet", new TableServlet( new MemoryItemsDao() )).addMapping("/table");
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {	
	}
	
}
