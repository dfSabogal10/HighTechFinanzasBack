package com.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@RestController
public class ClientesController {
	  @Value("${spring.datasource.url}")
	  private String dbUrl;

	  @Autowired
	  private DataSource dataSource;
	  
	@RequestMapping("/clientes")
	public ArrayList<Cliente> greeting() {
		try (Connection connection = dataSource.getConnection()) {
		      Statement stmt = connection.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT * FROM clientes;");

		      ArrayList<Cliente> output = new ArrayList<Cliente>();
		      while (rs.next()) {
		    	 Cliente nuevo=new Cliente(rs.getString("apellido"),
		    			 					rs.getString("nombre"),
		    			 					rs.getInt("cedula"),
		    			 					rs.getLong("tarjeta"),
		    			 					rs.getDate("fechaVencimiento"));
		        output.add(nuevo);
		      }

		      return output;
		    } catch (Exception e) {
		      return null;
		    }
    }
	
	@Bean
	  public DataSource dataSource() throws SQLException {
	    if (dbUrl == null || dbUrl.isEmpty()) {
	      return new HikariDataSource();
	    } else {
	      HikariConfig config = new HikariConfig();
	      config.setJdbcUrl(dbUrl);
	      return new HikariDataSource(config);
	    }
	  }
}