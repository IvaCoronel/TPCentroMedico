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
		
	}

	@Test
	public void agregarEspecialidadtest() {
		centro.agregarEspecialidad("Pediatria",2000);
		centro.agregarEspecialidad("Cardiologia",3000);
		centro.agregarEspecialidad("Traumatologia",2500);
		centro.agregarEspecialidad("Pediatria",2000);
		assertEquals(3,centro.especialidades.size());
	}
	@Test
	public void agregarMedicoTest() {
		centro.agregarMedico("Dr Perez", 55555, "Pediatria", 5000);
		centro.agregarMedico("Dr Rodriguez", 66666, "Cardiologia", 8000);
		centro.agregarMedico("Dr Curetta", 77777, "Traumatologia", 2000);
		centro.agregarMedico("Dr Rodriguez", 66666, "Cardiologia", 8000);
		assertEquals(3, centro.medicos.size());
	}
	@Test
	public void agregarPacientePrivadoTest() {
		centro.agregarPacientePrivado("Juan", 111, new Fecha(20,11,1980));
		centro.agregarPacientePrivado("Juan", 111, new Fecha(20,11,1980));
		assertEquals(1, centro.pacientes.size());	
	}
	@Test
	public void agregarPacienteAmbulatorioTest() {
		centro.agregarPacienteAmbulatorio("Pedro", 333, new Fecha(28,2,1970));
		centro.agregarPacienteAmbulatorio("Andres", 456, new Fecha(26,8,2000));
		centro.agregarPacientePrivado("Juan", 111, new Fecha(20,11,1980));
		assertEquals(3, centro.pacientes.size());
	}
	@Test
	public void agregarPacienteObraSociaTest() {
		centro.agregarPacienteObraSocial("Carlos", 222, new Fecha(15,1,1940), "Pami",0.3);
		centro.agregarPacienteObraSocial("Carlos", 222, new Fecha(15,1,1940), "Pami",0.3);
		centro.agregarPacienteObraSocial("Marisol", 252, new Fecha(15,1,1940), "Pami",0.3);
		assertEquals(2,centro.pacientes.size());
	}
	
}