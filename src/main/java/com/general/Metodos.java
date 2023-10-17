package com.general;
import java.util.List;
public interface Metodos {
public String guardar(Object ob);
public String editar(Object ob);
public String eliminar(int id);
public Object buscar(int id);
public List mostrar();
}