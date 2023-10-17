package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Socios;
import com.general.Metodos;

public class SociosDAO implements Metodos{
	//Interfase que carga los objetos o clases definidas en el archivo persistence.xml
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tables");
	//Cargar las operaciones CRUD
	EntityManager em = emf.createEntityManager();
	//Instanciamos a un objeto de nuestra clase socio
	Socios socio = null;

	@Override
	public String guardar(Object ob) {
		socio=(Socios) ob;
		String resultado=null;
		em.getTransaction().begin();//Indicamos el inicio de una transaccion en una base de datos
		try {
			em.persist(socio);//Aqui se indica la creacion del nuevo recurso(insert)
			em.getTransaction().commit();//Confirmamos la insercion con un commit
			resultado = "1";
			System.out.println("Registro insertado, nuevo socio creado");
		} catch (Exception e) {
			em.getTransaction().rollback();//Si no se puede realizar la insercion, instruimos un
			//rollback para no dejar cambios inconclusos
			resultado=e.getMessage();
			System.out.println("No se realizó la insercion");
		}
		em.close();	
		return resultado;
	}

	@Override
	public String editar(Object ob) {
		socio=(Socios) ob;//Aqui recae la info en formato JSON del exterior 
		//Buscamos al socio existente en la base de datos para editar sus atributor
		Socios sociodb = em.find(Socios.class, socio.getIdSocio());
		String resultado = null;
		em.getTransaction().begin();
		try {
			sociodb.setNombre(socio.getNombre());
			sociodb.setDireccion(socio.getDireccion());
			sociodb.setTelefono(socio.getTelefono());
			sociodb.setAntiguedad(socio.getAntiguedad());
			sociodb.setNacionalidad(socio.getNacionalidad());
			sociodb.setSexo(socio.getSexo());
			em.getTransaction().commit();
			resultado="1";
			System.out.println("Socio editado correctamente");
		} catch (Exception e) {
			em.getTransaction().rollback();
			resultado=e.getMessage();
			System.out.println("No pudo editarse el socio");
		}
		em.close();
		return resultado;
	}

	@Override
	public String eliminar(int id) {
		String resultado=null;
		//Identificamos el registro del socio a eliminar
		socio=em.find(Socios.class,id);
		em.getTransaction().begin();
		try {
			em.remove(socio);
			em.getTransaction().commit();
			resultado="1";
			System.out.println("Se ha eliminado el socio");
		} catch (Exception e) {
			em.getTransaction().rollback();
			resultado=e.getMessage();
			System.out.println("No se elimino el socio");
		}
		em.close();
		return resultado;
	}

	@Override
	public Object buscar(int id) {
		socio=em.find(Socios.class, id);
		return socio;
	}

	@Override
	public List<Socios> mostrar() {
		List<Socios> lista = em.createQuery("from Socios").getResultList();
		return lista;
	}
	//¿que diferencia exite entre JDBC y Hibernate?
	//1.-El tiempo de respuesta de una conexion mediante JDBC es mas rapida
	//El tiempo de desarrollo con hibernate es mas rapido
}