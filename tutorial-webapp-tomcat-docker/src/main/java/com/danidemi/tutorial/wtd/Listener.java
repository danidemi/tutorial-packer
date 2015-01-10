package com.danidemi.tutorial.wtd;

import static java.lang.String.format;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp2.BasicDataSource;
import org.flywaydb.core.Flyway;
import org.omg.CORBA.Environment;

import com.danidemi.jlubricant.utils.properties.EnvProperties;

@WebListener
public class Listener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		
		
		try {
			
			Properties defaults = System.getProperties();
			defaults.putAll( System.getenv() );
			
			Properties props = new EnvProperties(defaults);
			props.load( getClass().getResourceAsStream("/config.properties") );
			
			props.list(System.out);
			
			String itemsRepoFqn = (String) new InitialContext().lookup(Constants.ITEMS_REPOSITORY_FQN_JNDI_URL);
			
			ItemsDao itemsRepo = (ItemsDao) Class.forName(itemsRepoFqn).newInstance();
			
			if(itemsRepo.needDatasource()){
				
				BasicDataSource ds = new BasicDataSource();
				ds.setDriverClassName("org.postgresql.Driver");
				ds.setUsername( props.getProperty("DB_USERNAME") );
				ds.setPassword( props.getProperty("DB_PASSWORD") );
				String dbHost = props.getProperty("DB_HOST");
				String dbPort = props.getProperty("DB_PORT");
				String dbName = props.getProperty("DB_NAME");
				ds.setUrl( format("jdbc:postgresql://%s:%s/%s", dbHost, dbPort, dbName) );
				
		        // Create the Flyway instance
		        Flyway flyway = new Flyway();
		        flyway.setDataSource(ds);
		        flyway.migrate();				
				
				itemsRepo.setDatasource( ds );
		      				
			}
			
			
			
			
			// Set the servlet
			sce.getServletContext().addServlet("tableServlet", new TableServlet( itemsRepo )).addMapping("/table");
			
		} catch (NamingException | InstantiationException | IllegalAccessException | ClassNotFoundException | IOException e) {
			throw new RuntimeException("Problem during initialization.", e);
		}

	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {	
	}
	
}
