package dh.com.example.finalBack;

import dh.com.example.finalBack.entity.Odontologo;
import dh.com.example.finalBack.repository.IOdontologoRepository;
import dh.com.example.finalBack.service.ImplementacionService.OdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
@DataJpaTest
class FinalBackApplicationTests {

		@Autowired
		private IOdontologoRepository odontologoRepository;

		@Test
		public void testGuardar() {
			OdontologoService odontologoService = new OdontologoService(odontologoRepository);

			Odontologo odontologo = new Odontologo();
			odontologo.setNombre("Juan");
			odontologo.setApellido("Pérez");
			odontologo.setMatricula("12345");

			Odontologo odontologoGuardado = odontologoService.guardar(odontologo);

			assertNotNull(odontologoGuardado.getId());
			assertEquals(odontologo.getNombre(), odontologoGuardado.getNombre());
			assertEquals(odontologo.getApellido(), odontologoGuardado.getApellido());
			assertEquals(odontologo.getMatricula(), odontologoGuardado.getMatricula());
		}

	@Test
	public void testListarTodos() {
		Odontologo odontologo1 = new Odontologo();
		odontologo1.setNombre("Jorgito");
		odontologo1.setApellido("Pérez");
		odontologo1.setMatricula("12345");


		Odontologo odontologo2 = new Odontologo();
		odontologo2.setNombre("Vanina");
		odontologo2.setApellido("Godoy");
		odontologo2.setMatricula("33344");

		OdontologoService odontologoService = new OdontologoService(odontologoRepository);
		odontologoService.guardar(odontologo1);
		odontologoService.guardar(odontologo2);

		List<Odontologo> odontologos = odontologoService.listarTodos();
		assertNotNull(odontologos);
		assertEquals(2, odontologos.size());
	}

	@Test
	public void testBuscarPorId() {
		Odontologo odontologo = new Odontologo();
		odontologo.setNombre("Juan");
		odontologo.setApellido("Pérez");
		odontologo.setMatricula("12345");

		odontologoRepository.save(odontologo);

		OdontologoService odontologoService = new OdontologoService(odontologoRepository);

		Long odontologoId = odontologo.getId();
		Odontologo odontologoEncontrado = odontologoService.buscarPorId(odontologoId);

		assertNotNull(odontologoEncontrado);
		assertEquals(odontologo.getNombre(), odontologoEncontrado.getNombre());
		assertEquals(odontologo.getApellido(), odontologoEncontrado.getApellido());
		assertEquals(odontologo.getMatricula(), odontologoEncontrado.getMatricula());
	}


	@Test
	public void testActualizar() {
		Odontologo odontologo = new Odontologo();
		odontologo.setId(1L);
		odontologo.setNombre("pepe");
		odontologo.setApellido("peras");
		odontologo.setMatricula("54321");

		odontologoRepository.save(odontologo);
		OdontologoService odontologoService = new OdontologoService(odontologoRepository);
		odontologoService.actualizar(odontologo);

		Optional<Odontologo> optionalOdontologoActualizado = odontologoRepository.findById(odontologo.getId());
		assertTrue(optionalOdontologoActualizado.isPresent());

		Odontologo odontologoActualizado = optionalOdontologoActualizado.get();
		assertEquals(odontologo.getNombre(), odontologoActualizado.getNombre());
		assertEquals(odontologo.getApellido(), odontologoActualizado.getApellido());
		assertEquals(odontologo.getMatricula(), odontologoActualizado.getMatricula());
	}

	@Test
	public void testEliminar() {
		Odontologo odontologo = new Odontologo();
		odontologo.setNombre("Juan");
		odontologo.setApellido("Perez");
		odontologo.setMatricula("12345");

		odontologoRepository.save(odontologo);

		OdontologoService odontologoService = new OdontologoService(odontologoRepository);
		odontologoService.eliminar(odontologo.getId());
		Optional<Odontologo> optionalOdontologoEliminado = odontologoRepository.findById(odontologo.getId());
		assertFalse(optionalOdontologoEliminado.isPresent());
	}
	}
