import static org.junit.Assert.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CentroTest {
	private Centro centro;
	
	@Before
	public void setUp(){
		centro=new Centro("Centro Medico Dolores Fuentes","30-36542563-0",3000);
		//ESPECIALIDADES
		centro.agregarEspecialidad("Cardiologia", 2000);
		centro.agregarEspecialidad("Dermatologia", 1500);
		centro.agregarEspecialidad("Pediatria", 1200);
		//MEDICOS
		centro.agregarMedico("Rene Vena", 12345, "Cardiologia", 2500);
		centro.agregarMedico("Susana Cortez", 34567, "Dermatologia", 1500);
		centro.agregarMedico("Mario Piazza", 56789, "Pediatria", 1200);
		//PACIENTES PRIVADOS
		centro.agregarPacientePrivado("Rosa Rios", 321, new Fecha(3,2,1971));
		//PACIENTES AMBULATORIOS
		centro.agregarPacienteAmbulatorio("Yoel Camino", 543, new Fecha(20,6,2015));
		//PACIENTES OBRA SOCIAL	
		centro.agregarPacienteObraSocial("Damian Moreno", 432, new Fecha(1,5,1963),"Osplad", 0.2);
		centro.agregarPacienteObraSocial("Yamila Choque", 654, new Fecha(20,6,2013),"Ospe", 0.3);
		centro.agregarPacienteObraSocial("Ramiro Delgado", 765, new Fecha(9,7,1989),"Osde", 0.1);
		//INTERNACION
		centro.agregarInternacion(432, "Cardiologia", new Fecha(15,11,2020));
		centro.agregarInternacion(654, "Pediatria", new Fecha(20,11,2020));
		
		centro.agregarInternacion(765, "Cardiologia", new Fecha(21,11,2013));
		//ALTA
		centro.altaInternacion(654,new Fecha(12,12,2020));
		centro.altaInternacion(765, Fecha.hoy());
		//AGREGAR ATENCION
		centro.agregarAtencion(321, new Fecha(10,10,2020),12345);
		centro.agregarAtencion(321, new Fecha(10,11,2020),56789);
		//AGREGAR GUARDIA
		centro.agregarAtencion(321, new Fecha(11,10,2020));

	}

	@Test
	public void agregarEspecialidadtest() {
		assertEquals(3,centro.especialidades.size());
	}
	
	@Test
	public void agregarMedicoTest() {
		assertEquals(3, centro.medicos.size());
	}
	@Test
	public void agregarPacienteTest() {
		assertEquals(5, centro.pacientes.size());	
	}
	
	@Test
	public void ListaInternacionTest() {
		List<Integer> inter = centro.listaInternacion();
		
		assertTrue(inter.contains(432));
		assertFalse(inter.contains(654));
	}

	@Test
	public void getSaldoTest() {
		assertEquals(centro.getSaldo(321),3200, 20);
		
	}
	
	@Test
	public void setSaldoTest() {
		centro.pagarSaldo(321);
		assertEquals(centro.getSaldo(321),0,20);
	}
	
	@Test
	public void agregarAtencionTest() {
		assertEquals(3, centro.obtenerPaciente(321).atenciones.size());
	}
	
	@Test
	public void listaInternaciontTest() {
		assertEquals(1,centro.listaInternacion().size());
	}
	@Test
	public void altaInternacionTest() {
		centro.altaInternacion(432, Fecha.hoy()); //Fecha valida
		assertEquals(centro.listaInternacion().size(),0);
	}
	@Test
	public void atencionesEnConsultorioTest() {
		assertEquals(2,centro.atencionesEnConsultorio(321).size());
		
	}
		
}

	
	