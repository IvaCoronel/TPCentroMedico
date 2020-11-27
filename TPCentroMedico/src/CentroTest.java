import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CentroTest {
	private Centro centro;
	
	@Before
	public void setUp(){
		centro=new Centro("Centro Medico Dolores Fuentes","30-36542563-0",3000);
	}

	@Test
	public void agregarEspecialidadtest() {
		centro.agregarEspecialidad("Cardiologia", 2000);
		centro.agregarEspecialidad("Dermatologia", 1500);
		centro.agregarEspecialidad("Pediatria", 1200);
		assertEquals(3,centro.especialidades.size());
	}
	@Test
	public void agregarMedicoTest() {
		centro.agregarMedico("Rene Vena", 12345, "Cardiologia", 20000);
		centro.agregarMedico("Susana Cortez", 34567, "Dermatologia", 15000);
		centro.agregarMedico("Mario Piazza", 56789, "Pediatria", 12000);
		assertEquals(3, centro.medicos.size());
	}
	@Test
	public void agregarPacientePrivadoTest() {
		centro.agregarPacientePrivado("Rosa Rios", 321, new Fecha(3,2,1971));
		assertEquals(1, centro.pacientes.size());	
	}
	@Test
	public void agregarPacienteAmbulatorioTest() {
		centro.agregarPacienteAmbulatorio("Yoel Camino", 543, new Fecha(20,6,2015));
		assertEquals(1, centro.pacientes.size());
	}
	@Test
	public void agregarPacienteObraSociaTest() {
		
		assertEquals(0,centro.pacientes.size());
	}
	@Test
	public void ListaInternacionTest() {
		centro.agregarPacienteObraSocial("Damian Moreno", 432, new Fecha(1,5,1963),"Osplad", 0.2);
		centro.agregarInternacion(432, "Cardiologia", new Fecha(15,11,2020));
		
		centro.agregarPacienteObraSocial("Yamila Choque", 654, new Fecha(20,6,2013),"Ospe", 0.3);
		centro.agregarInternacion(654, "Pediatria", new Fecha(20,11,2020));
		
		
		centro.agregarPacienteObraSocial("Ramiro Delgado", 765, new Fecha(9,7,1989),"Osde", 0.1);
		centro.agregarInternacion(765, "Cardiologia", new Fecha(21,11,2013));
		
		centro.altaInternacion(654,new Fecha(12,12,2020));

		List<Integer> inter = centro.listaInternacion();
		
		assertTrue(inter.contains(432));
		assertFalse(inter.contains(654));
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}