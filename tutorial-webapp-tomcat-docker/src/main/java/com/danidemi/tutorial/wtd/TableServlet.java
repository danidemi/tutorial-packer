package com.danidemi.tutorial.wtd;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;

//@WebServlet(name="table", urlPatterns={"/table"}) 
public class TableServlet extends HttpServlet {

	private static final long serialVersionUID = 3803075688578792228L;
	
	private ItemsDao itemsDao;
		
	public TableServlet(ItemsDao memoryItemsDao) {
		super();
		this.itemsDao = memoryItemsDao;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			doLogic(req, res);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}



	private void doLogic(HttpServletRequest req, HttpServletResponse res)
			throws Exception, UnknownHostException, ServletException,
			IOException {
		String action = String.valueOf( req.getParameter("action") );
		String user = (String) req.getSession().getAttribute("user");
		
		String destination = null;
		if(user != null){
			if("add".equals(action)){
				String valueToAdd = req.getParameter("value");
				itemsDao.addItem(user, valueToAdd);

				
				destination = "/WEB-INF/table.jsp";
				req.setAttribute("table", itemsDao.items(user));
				
			}else if("remove".equals(action)){
				String valueToRemove = req.getParameter("value");
				itemsDao.removeItem(user, valueToRemove);
				
				destination = "/WEB-INF/table.jsp";
				req.setAttribute("table", itemsDao.items(user));
				
			}else if("logout".equals(action)){
				req.getSession().invalidate();
				user = null;
				destination = "/WEB-INF/login.jsp";	
			}else{
				destination = "/WEB-INF/table.jsp";
				req.setAttribute("table", itemsDao.items(user));
			}
		}else{
			if("login".equals(action)){
				String newUser = req.getParameter("user");
				req.getSession().setAttribute( "user", newUser );
				destination = "/table";
			}else{
				destination = "/WEB-INF/login.jsp";							
			}
		}
		
		
		req.setAttribute("hostname", InetAddress.getLocalHost().getHostName());	
		req.setAttribute("user", user != null ? user : "<anonymous>");
		RequestDispatcher rd = req.getRequestDispatcher(destination);
		rd.forward(req, res);
	}



}
