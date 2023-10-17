package com.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.SociosDAO;
import com.entity.Socios;
import com.general.Status;

@Path("socios")
public class SociosService {
	// Intanciar a un objeto Socios
	Socios socio = null;

	// Intanciar a la clase DAO
	SociosDAO dao = new SociosDAO();

	@Path("mostrar")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Socios> mostrar() {
		List<Socios> lista = dao.mostrar();
		return lista;
	}

	@Path("guardar")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Status guardar(Socios s) {
		Status status = new Status();
		status.setOb(s);
		String respuesta = dao.guardar(s);

		if (respuesta.equals("1")) {
			status.setMensaje("El socio se ha guardado con exito");
			status.setRespuesta(respuesta);
		} else {
			status.setMensaje("No fue posible guardar el socio");
			status.setRespuesta(respuesta);
		}
		return status;
	}
	@Path("editar")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Status editar(Socios s) {
		Status status = new Status();
		status.setOb(s);
		String respuesta=dao.editar(s);
		if (respuesta.equals("1")) {
			status.setMensaje("El socio se ha editado con exito");
			status.setRespuesta(respuesta);
		} else {
			status.setMensaje("No fue posible editar el socio");
			status.setRespuesta(respuesta);
		}
		return status;
	}
	@Path("eliminar/{id}")
	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	public Status eliminar(@PathParam("id")int id) {
		Status status = new Status();
		status.setOb(id);
		String respuesta=dao.eliminar(id);
		if (respuesta.equals("1")) {
			status.setMensaje("El socio se ha eliminado de la base de datos con exito");
			status.setRespuesta(respuesta);
		} else {
			status.setMensaje("No fue posible eliminar el registro del socio");
			status.setRespuesta(respuesta);
		}
		return status;
	}
	@Path("buscar/{id}")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Socios buscar(@PathParam("id") int id) {
		socio=(Socios) dao.buscar(id);
		return socio;
	}
}
