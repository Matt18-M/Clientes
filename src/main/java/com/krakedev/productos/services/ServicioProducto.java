package com.krakedev.productos.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.krakedev.productos.entidades.Producto;

@Service
public class ServicioProducto {

	// Lista para almacenar productos en memoria
	private ArrayList<Producto> productos = new ArrayList<>();

	// 1. Crear producto si no existe uno con el mismo código
	public Producto crear(Producto producto) {

		Producto existente = buscarPorCodigo(producto.getCodigo());

		if (existente != null) {
			return null; // ya existe
		} else {
			productos.add(producto);
			return producto; // creado exitosamente
		}
	}

	// 2. Listar todos los productos
	public List<Producto> listar() {
		return productos;
	}

	// 3. Buscar producto por código
	public Producto buscarPorCodigo(int codigo) {
		for (Producto p : productos) {
			if (p.getCodigo() == codigo) {
				return p;
			}
		}
		return null;
	}

	// 4. Actualizar producto existente
	public Producto actualizar(int codigo, Producto productoActualizado) {

		Producto producto = buscarPorCodigo(codigo);

		if (producto != null) {
			producto.setNombre(productoActualizado.getNombre());
			producto.setPrecio(productoActualizado.getPrecio());
			producto.setStock(productoActualizado.getStock());
		}

		return producto; // actualizado o null
	}

	// 5. Eliminar producto por código
	public boolean eliminar(int codigo) {

		Producto producto = buscarPorCodigo(codigo);

		if (producto != null) {
			productos.remove(producto);
			return true;
		} else {
			return false;
		}
	}
}