package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.krakedev.clientes.entidades.Cliente;
import com.krakedev.clientes.services.ServicioCliente;

public class ServicioClienteTest {

	private ServicioCliente servicio;

	@BeforeEach
	public void inicializar() {
		servicio = new ServicioCliente();
	}

	@Test
	public void testCrearCliente() {

		Cliente cliente = new Cliente();
		cliente.setCedula("123");
		cliente.setNombre("Juan");
		cliente.setApellido("Perez");
		cliente.setEmail("juan@gmail.com");

		Cliente resultado = servicio.crear(cliente);

		assertNotNull(resultado);
		assertEquals("Juan", resultado.getNombre());
		assertEquals("juan@gmail.com", resultado.getEmail());
	}

	@Test
	public void testBuscarCliente() {

		Cliente cliente = new Cliente();
		cliente.setCedula("456");
		cliente.setNombre("Maria");
		cliente.setApellido("Lopez");
		cliente.setEmail("maria@gmail.com");

		servicio.crear(cliente);

		Cliente encontrado = servicio.buscarPorCedula("456");

		assertNotNull(encontrado);
		assertEquals("maria@gmail.com", encontrado.getEmail());
	}

	@Test
	public void testActualizarCliente() {

		Cliente cliente = new Cliente();
		cliente.setCedula("789");
		cliente.setNombre("Carlos");
		cliente.setApellido("Perez");
		cliente.setEmail("carlos@gmail.com");

		servicio.crear(cliente);

		Cliente actualizado = new Cliente();
		actualizado.setNombre("Carlos Alberto");
		actualizado.setApellido("Gomez");
		actualizado.setEmail("nuevo@gmail.com");

		Cliente resultado = servicio.actualizar("789", actualizado);

		assertNotNull(resultado);
		assertEquals("Carlos Alberto", resultado.getNombre());
		assertEquals("nuevo@gmail.com", resultado.getEmail());
	}

	@Test
	public void testEliminarCliente() {

		Cliente cliente = new Cliente();
		cliente.setCedula("999");
		cliente.setNombre("Pedro");
		cliente.setApellido("Ruiz");
		cliente.setEmail("pedro@gmail.com");

		servicio.crear(cliente);

		boolean eliminado = servicio.eliminar("999");

		assertTrue(eliminado);
		assertNull(servicio.buscarPorCedula("999"));
	}

	@Test
	public void testNoCrearDuplicado() {

		Cliente cliente1 = new Cliente();
		cliente1.setCedula("111");
		cliente1.setNombre("Ana");
		cliente1.setApellido("Torres");
		cliente1.setEmail("ana@gmail.com");

		Cliente cliente2 = new Cliente();
		cliente2.setCedula("111");
		cliente2.setNombre("Otra");
		cliente2.setApellido("Persona");
		cliente2.setEmail("otra@gmail.com");

		servicio.crear(cliente1);

		Cliente resultado = servicio.crear(cliente2);

		assertNull(resultado);
	}
}