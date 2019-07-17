package com.example;

import java.util.Date;

public class Cliente {
	private int id;
	private String apellido;
	private String nombre;
	private int cedula;
	private long tarjeta;
	private Date fechaVencimiento;
	
	public Cliente(int pId,String pApellido, String pNombre, int pCedula,long pTarjeta,Date pFechaVencimiento){
		this.id=pId;
		apellido=pApellido;
		nombre=pNombre;
		cedula=pCedula;
		tarjeta=pTarjeta;
		fechaVencimiento=pFechaVencimiento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
	}

	public long getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(long tarjeta) {
		this.tarjeta = tarjeta;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
}
