package com.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dao.BarcoDAO;
import com.entity.Barco;
import com.general.Status;

@Path("barco")
public class BarcoService {
	Barco barco = null;
	BarcoDAO dao = new BarcoDAO();

	@Path("mostrar")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Barco> mostrar() {
		List<Barco> lista = dao.mostrar();
		return lista;
	}

	@Path("guardar")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Status guardar(Barco b) {
		Status status = new Status();
		status.setOb(b);
		String respuesta = dao.guardar(b);

		if (respuesta.equals("1")) {
			status.setMensaje("El barco se ha guardado con exito");
			status.setRespuesta(respuesta);
		} else {
			status.setMensaje("No fue posible guardar el barco");
			status.setRespuesta(respuesta);
		}
		return status;
	}
	@Path("editar")
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Status editar(Barco b) {
		Status status = new Status();
		status.setOb(b);
		String respuesta=dao.editar(b);
		if (respuesta.equals("1")) {
			status.setMensaje("El barco se ha editado con exito");
			status.setRespuesta(respuesta);
		} else {
			status.setMensaje("No fue posible editar el barco");
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
			status.setMensaje("El barco se ha eliminado de la base de datos con exito");
			status.setRespuesta(respuesta);
		} else {
			status.setMensaje("No fue posible eliminar el registro del barco");
			status.setRespuesta(respuesta);
		}
		return status;
	}
	@Path("buscar/{id}")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Barco buscar(@PathParam("id") int id) {
		barco=(Barco) dao.buscar(id);
		return barco;
	}
}