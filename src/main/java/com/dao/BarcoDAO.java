package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Barco;
import com.general.Metodos;

public class BarcoDAO implements Metodos{
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("Tables");
	EntityManager em = emf.createEntityManager();
	Barco barco = null;

	@Override
	public String guardar(Object ob) {
		barco=(Barco) ob;
		String resultado=null;
		em.getTransaction().begin();
		try {
			em.persist(barco);
			em.getTransaction().commit();
			resultado = "1";
			System.out.println("Registro insertado, nuevo socio creado");
		} catch (Exception e) {
			em.getTransaction().rollback();
			resultado=e.getMessage();
			System.out.println("No se realizó la insercion");
		}
		em.close();	
		return resultado;
	}

	@Override
	public String editar(Object ob) {
		barco=(Barco) ob;
		Barco barcodb = em.find(Barco.class, barco.getSocio());
		String resultado = null;
		em.getTransaction().begin();
		try {
			barcodb.setNombre(barco.getNombre());
			barcodb.setNumAmarre(barco.getNumAmarre());
			barcodb.setCuota(barco.getCuota());
			//barcodb.setIdSocio(barco.getIdSocio());
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
		barco=em.find(Barco.class,id);
		em.getTransaction().begin();
		try {
			em.remove(barco);
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
		barco=em.find(Barco.class, id);
		return barco;
	}

	@Override
	public List<Barco> mostrar() {
		List<Barco> lista = em.createQuery("from Barco").getResultList();
		return lista;
	}

}