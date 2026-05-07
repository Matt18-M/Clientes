package com.krakedev.clientes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

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

		Cliente resultado = servicio.crear(cliente);

		assertNotNull(resultado);
		assertEquals("123", resultado.getCedula());
		assertEquals("Juan", resultado.getNombre());
	}

	@Test
	public void testNoCrearClienteDuplicado() {

		Cliente cliente1 = new Cliente();
		cliente1.setCedula("123");
		cliente1.setNombre("Juan");
		cliente1.setApellido("Perez");

		Cliente cliente2 = new Cliente();
		cliente2.setCedula("123");
		cliente2.setNombre("Pedro");
		cliente2.setApellido("Lopez");

		servicio.crear(cliente1);

		Cliente resultado = servicio.crear(cliente2);

		assertNull(resultado);
	}

	@Test
	public void testBuscarPorCedulaExistente() {

		Cliente cliente = new Cliente();
		cliente.setCedula("456");
		cliente.setNombre("Maria");
		cliente.setApellido("Gomez");

		servicio.crear(cliente);

		Cliente encontrado = servicio.buscarPorCedula("456");

		assertNotNull(encontrado);
		assertEquals("Maria", encontrado.getNombre());
	}

	@Test
	public void testBuscarPorCedulaNoExistente() {

		Cliente encontrado = servicio.buscarPorCedula("999");

		assertNull(encontrado);
	}

	@Test
	public void testListarClientes() {

		Cliente cliente1 = new Cliente();
		cliente1.setCedula("111");
		cliente1.setNombre("Ana");
		cliente1.setApellido("Torres");

		Cliente cliente2 = new Cliente();
		cliente2.setCedula("222");
		cliente2.setNombre("Luis");
		cliente2.setApellido("Mena");

		servicio.crear(cliente1);
		servicio.crear(cliente2);

		List<Cliente> lista = servicio.listar();

		assertEquals(2, lista.size());
	}

	@Test
	public void testActualizarClienteExistente() {

		Cliente cliente = new Cliente();
		cliente.setCedula("777");
		cliente.setNombre("Carlos");
		cliente.setApellido("Perez");

		servicio.crear(cliente);

		Cliente actualizado = new Cliente();
		actualizado.setNombre("Carlos Alberto");
		actualizado.setApellido("Gomez");

		Cliente resultado = servicio.actualizar("777", actualizado);

		assertNotNull(resultado);
		assertEquals("Carlos Alberto", resultado.getNombre());
		assertEquals("Gomez", resultado.getApellido());
	}

	@Test
	public void testActualizarClienteNoExistente() {

		Cliente actualizado = new Cliente();
		actualizado.setNombre("Nuevo");
		actualizado.setApellido("Apellido");

		Cliente resultado = servicio.actualizar("000", actualizado);

		assertNull(resultado);
	}

	@Test
	public void testEliminarClienteExistente() {

		Cliente cliente = new Cliente();
		cliente.setCedula("888");
		cliente.setNombre("Pedro");
		cliente.setApellido("Ruiz");

		servicio.crear(cliente);

		boolean eliminado = servicio.eliminar("888");

		assertTrue(eliminado);
		assertNull(servicio.buscarPorCedula("888"));
	}

	@Test
	public void testEliminarClienteNoExistente() {

		boolean eliminado = servicio.eliminar("000");

		assertFalse(eliminado);
	}
}