package com.example;

import java.util.Date;

public class Cliente {
	private String apellido;
	private String nombre;
	private int cedula;
	private long tarjeta;
	private Date fechaVencimiento;
	
	public Cliente(String pApellido, String pNombre, int pCedula,long pTarjeta,Date pFechaVencimiento){
		apellido=pApellido;
		nombre=pNombre;
		cedula=pCedula;
		tarjeta=pTarjeta;
		fechaVencimiento=pFechaVencimiento;
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
