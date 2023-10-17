package com.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity//Identificamos que es una entidad de una base de datos
@Table(name = "SOCIOS")
public class Socios {
	
	@Id //Identifica que esta variable es una llave primaria
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="sid_auto")// generator equivale a un disparador trigg en una base de datos 
	@SequenceGenerator(name="sid_auto",sequenceName="SOCIO_SEQ")
	@Column(name="ID_SOCIO",columnDefinition = "NUMBER")
	private int idSocio;
	@Column(name="NOMBRE",columnDefinition = "NVARCHAR2(30)")
	private String nombre;
	@Column(name="DIRECCION",columnDefinition = "NVARCHAR2(30)")
	private String direccion;
	@Column(name="TELEFONO",columnDefinition = "NVARCHAR2(30)")
	private String telefono;
	@Column(name="ANTIGUEDAD",columnDefinition = "NUMBER")
	private int antiguedad;
	@Column(name="NACIONALIDAD",columnDefinition = "NVARCHAR2(30)")
	private String nacionalidad;
	@Column(name="SEXO",columnDefinition = "NVARCHAR2(30)")
	private String sexo;
	//Atributo relacional que no existe en la tabla socio	
	//Este atributo es el barco que le pertenece a cada socio
	//Anotacion OneToOne (1 1) nos permite establecer una relacion de que para un socio
	//hay solo un barco. Esta relacion queda mapeada por la entidad propietaria de dicha relacion
	/*@OneToOne(mappedBy="socio")
	private Barco barco;*/
	//La anotacion OneToMeny me permite establecer una relacion uno a muchos y para poder visualizar
	//dicha relacion, la tengo que mostrar en una coleccion de datos (lista) 
	@OneToMany(mappedBy="Socios")
	private List<Barco> barco;
	
	//CONSTRUCTORES
	public Socios() {}

	public Socios(int idSocio) {
		this.idSocio = idSocio;
	}
	//GETTERS Y SETTERS

	public int getIdSocio() {
		return idSocio;
	}

	public void setIdSocio(int idSocio) {
		this.idSocio = idSocio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<Barco> getBarco() {
		return barco;
	}

	public void setBarco(List<Barco> barco) {
		this.barco = barco;
	}

}