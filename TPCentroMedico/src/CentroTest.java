import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class CentroTest {
	private Centro centro;
	
	@Before
	public void setUp(){
		centro=new Centro("Centro Medico Dolores Fuentes","30-36542563-0",3000);
			
		centro.agregarPacientePrivado("Rosa Rios", 321, new Fecha(3,2,1971));
		
		centro.agregarPacienteAmbulatorio("Yoel Camino", 543, new Fecha(20,6,2015));
		
		centro.agregarPacienteObraSocial("Damian Moreno", 432, new Fecha(1,5,1963),"Osplad", 0.2);
		
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
		assertEquals(3, centro.pacientes.size());	
	}
	@Test
	public void agregarPacienteAmbulatorioTest() {
		assertEquals(3, centro.pacientes.size());
	}
	@Test
	public void agregarPacienteObraSociaTest() {
		assertEquals(3,centro.pacientes.size());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}