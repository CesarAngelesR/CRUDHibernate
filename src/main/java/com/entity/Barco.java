package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "BARCO")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
property="idBarco")
//Es una anotacion que nos sirve para serializar objaetos
//La serializacion de objetos no es otra cosa que descomponer un objeto adecuadamente
//en bits para poder llevar la informacion hacia un front.
//La anotacion @JsonIdentityInfo se utiliza para poder resover problemas de referencias
//circulares (ciclos) y lo que hace es asiganr identificadores unicos para no serializar un objeto varias veces.
public class Barco {
	@Id // Identifica que esta variable es una llave primaria
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sid_auto")
	@SequenceGenerator(name = "sid_auto", sequenceName = "BARCO_SEQ")
	@Column(name = "ID_BARCO", columnDefinition = "NUMBER")
	private int idBarco;
	@Column(name = "NOMBRE", columnDefinition = "VARCHAR2(30 BYTE)")
	private String nombre;
	@Column(name = "NUME_AMARRE", columnDefinition = "NUMBER")
	private int numAmarre;
	@Column(name = "CUOTA", columnDefinition = "NUMBER(8,2)")
	private double cuota;
	/*@Column(name = "ID_SOCIO", columnDefinition = "NUMBER")
	private int idSocio;*/
	
	//Establecemos la relacion con la entidad socio uniendo un objeto de esa entidad
	//en la columna que comparten o que es llave foranea en esta entidad
	@ManyToOne
	@JoinColumn(name="ID_SOCIO", columnDefinition = "NUMBER")
	private Socios Socio;

	public Barco() {
	}

	public Barco(int idBarco) {
		super();
		this.idBarco = idBarco;
	}

	public int getIdBarco() {
		return idBarco;
	}

	public void setIdBarco(int idBarco) {
		this.idBarco = idBarco;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumAmarre() {
		return numAmarre;
	}

	public void setNumAmarre(int numAmarre) {
		this.numAmarre = numAmarre;
	}

	public double getCuota() {
		return cuota;
	}

	public void setCuota(double cuota) {
		this.cuota = cuota;
	}

	public Socios getSocio() {
		return Socio;
	}

	public void setSocio(Socios Socio) {
		this.Socio = Socio;
	}
	

}
