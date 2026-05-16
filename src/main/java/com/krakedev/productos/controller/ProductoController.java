package com.krakedev.productos.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.krakedev.productos.entidades.Producto;
import com.krakedev.productos.services.ServicioProducto;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	private final ServicioProducto servicioProducto;

	// Inyección de dependencias por constructor
	public ProductoController(ServicioProducto servicioProducto) {
		this.servicioProducto = servicioProducto;
	}

	// Crear producto
	@PostMapping
	public Producto crear(@RequestBody Producto producto) {
		return servicioProducto.crear(producto);
	}

	// Listar productos
	@GetMapping
	public List<Producto> listar() {
		return servicioProducto.listar();
	}

	// Buscar producto por código
	@GetMapping("/{codigo}")
	public Producto buscar(@PathVariable int codigo) {
		return servicioProducto.buscarPorCodigo(codigo);
	}

	// Actualizar producto
	@PutMapping("/{codigo}")
	public Producto actualizar(@PathVariable int codigo, @RequestBody Producto productoActualizado) {
		return servicioProducto.actualizar(codigo, productoActualizado);
	}

	// Eliminar producto
	@DeleteMapping("/{codigo}")
	public boolean eliminar(@PathVariable int codigo) {
		return servicioProducto.eliminar(codigo);
	}

}