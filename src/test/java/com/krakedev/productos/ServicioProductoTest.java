package com.krakedev.productos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.productos.entidades.Producto;
import com.krakedev.productos.services.ServicioProducto;

public class ServicioProductoTest {

	private ServicioProducto servicio;

	// Se ejecuta antes de cada prueba para iniciar un servicio limpio
	@BeforeEach
	public void setUp() {
		servicio = new ServicioProducto();
	}

	// Crear un producto correctamente
	@Test
	public void testCrearProducto() {
		// Se crea un producto nuevo
		Producto p = new Producto("Laptop", 101, 799.99, 10);

		Producto resultado = servicio.crear(p);

		// Se espera que el producto se cree
		assertNotNull(resultado);
		assertEquals(101, resultado.getCodigo());
	}

	// No permitir crear un producto con código duplicado
	@Test
	public void testCrearProductoDuplicado() {
		// Producto inicial
		Producto p1 = new Producto("Mouse", 200, 19.99, 50);
		servicio.crear(p1);

		// Producto con el mismo código → debe fallar
		Producto p2 = new Producto("Teclado", 200, 29.99, 30);

		Producto resultado = servicio.crear(p2);

		// Se espera null porque ya existe
		assertNull(resultado);
	}

	// Listar productos luego de agregar algunos
	@Test
	public void testListarProductos() {
		servicio.crear(new Producto("Monitor", 300, 149.99, 15));
		servicio.crear(new Producto("Audífonos", 301, 49.99, 25));

		// Se espera que existan 2 productos en la lista
		assertEquals(2, servicio.listar().size());
	}

	// Buscar producto existente por código
	@Test
	public void testBuscarProductoExistente() {
		Producto p = new Producto("Tablet", 400, 199.99, 8);
		servicio.crear(p);

		Producto resultado = servicio.buscarPorCodigo(400);

		// Se espera encontrarlo
		assertNotNull(resultado);
		assertEquals("Tablet", resultado.getNombre());
	}

	// Buscar producto NO existente
	@Test
	public void testBuscarProductoNoExistente() {
		// No se agregó ningún producto con este código
		Producto resultado = servicio.buscarPorCodigo(999);

		// Se espera null
		assertNull(resultado);
	}

	// Actualizar producto existente
	@Test
	public void testActualizarProducto() {
		Producto original = new Producto("Impresora", 500, 120.00, 4);
		servicio.crear(original);

		// Nuevo estado del producto
		Producto actualizado = new Producto("Impresora Pro", 500, 150.00, 6);

		Producto resultado = servicio.actualizar(500, actualizado);

		// Se espera que se actualicen los atributos
		assertNotNull(resultado);
		assertEquals("Impresora Pro", resultado.getNombre());
		assertEquals(150.00, resultado.getPrecio());
		assertEquals(6, resultado.getStock());
	}

	// Intentar actualizar producto que no existe
	@Test
	public void testActualizarProductoNoExistente() {
		Producto actualizado = new Producto("Nuevo", 777, 10.0, 5);

		Producto resultado = servicio.actualizar(777, actualizado);

		// Se espera null porque no existe el producto con ese código
		assertNull(resultado);
	}

	// Eliminar producto existente
	@Test
	public void testEliminarProductoExistente() {
		servicio.crear(new Producto("Camara", 600, 300.00, 3));

		boolean eliminado = servicio.eliminar(600);

		// Se espera true
		assertTrue(eliminado);
		assertNull(servicio.buscarPorCodigo(600));
	}

	// Intentar eliminar producto que no existe
	@Test
	public void testEliminarProductoNoExistente() {
		// No existe un producto con código 888
		boolean eliminado = servicio.eliminar(888);

		// Se espera false
		assertFalse(eliminado);
	}
}