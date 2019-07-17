package com.example;

import java.net.URI;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class ClientesController {
	  @Value("${spring.datasource.url}")
	  private String dbUrl;

	  @Autowired
	  private DataSource dataSource;
	  
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/clientes", method=RequestMethod.GET)
	public ArrayList<Cliente> clientes() {
		try (Connection connection = dataSource.getConnection()) {
		      Statement stmt = connection.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT * FROM clientes;");

		      ArrayList<Cliente> output = new ArrayList<Cliente>();
		      while (rs.next()) {
		    	 Cliente nuevo=new Cliente(rs.getInt("id"),
		    			 					rs.getString("apellido"),
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
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value="/cliente", method=RequestMethod.GET)
	public Cliente cliente(@RequestParam(value="id") int id) {
		try (Connection connection = dataSource.getConnection()) {
		      Statement stmt = connection.createStatement();
		      ResultSet rs = stmt.executeQuery("SELECT * FROM clientes WHERE id="+id+";");

		      if(rs.next()) {
		    	 Cliente nuevo=new Cliente(rs.getInt("id"),
		    			 					rs.getString("apellido"),
		    			 					rs.getString("nombre"),
		    			 					rs.getInt("cedula"),
		    			 					rs.getLong("tarjeta"),
		    			 					rs.getDate("fechaVencimiento"));
		        return nuevo;
		      }
		      else{
		    	  return null;
		      }
		    } catch (Exception e) {
		      return null;
		    }
    }
	
	@CrossOrigin(origins = "*")
	@PostMapping(path= "/registrar", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> registrar(@RequestBody Cliente cliente) {
		try (Connection connection = dataSource.getConnection()) {
		      Statement stmt = connection.createStatement();
		      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		      String strDate= formatter.format(cliente.getFechaVencimiento());  
		      System.out.println(cliente.getFechaVencimiento());
		      stmt.executeUpdate("INSERT INTO clientes(apellido, nombre,cedula, tarjeta,fechaVencimiento) "
		      		+ "VALUES('"+cliente.getApellido()+"', "
		      				+ "'"+cliente.getNombre()+""
		      				+ "',"+cliente.getCedula()+", "
		      				+ ""+cliente.getTarjeta()+", "
		      				+ "TO_DATE('"+strDate+"', 'DD/MM/YYYY'));");
		      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(cliente.getId())
                      .toUri();
		     
		      return ResponseEntity.created(location).build();
		    } catch (Exception e) {
		    	e.printStackTrace();
		      return null;
		    }
    }
	
	@CrossOrigin(origins = "*")
	@PostMapping(path= "/actualizar", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> actualizar(@RequestParam(value="id") int id, @RequestBody Cliente cliente) {
		try (Connection connection = dataSource.getConnection()) {
		      Statement stmt = connection.createStatement();
		      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		      String strDate= formatter.format(cliente.getFechaVencimiento());  
		      System.out.println(cliente.getFechaVencimiento());
		      stmt.executeUpdate("UPDATE clientes "
		      		+ "SET apellido= '"+cliente.getApellido()+"', "
		      				+ "nombre= '"+cliente.getNombre()+""
		      				+ "', cedula= "+cliente.getCedula()+", "
		      				+ "tarjeta= "+cliente.getTarjeta()+", "
		      				+ "fechaVencimiento= TO_DATE('"+strDate+"', 'DD/MM/YYYY') "
		      						+ "WHERE id="+id+";");
		      URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                      .path("/{id}")
                      .buildAndExpand(cliente.getId())
                      .toUri();
		     
		      return ResponseEntity.created(location).build();
		    } catch (Exception e) {
		    	e.printStackTrace();
		      return null;
		    }
    }
	

}
