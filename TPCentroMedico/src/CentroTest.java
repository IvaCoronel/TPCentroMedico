import static org.junit.Assert.*;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CentroTest {
	private Centro centro;
	
	@Before
	public void setUp(){
		centro=new Centro("Centro Medico Dolores Fuentes","30-36542563-0",3000);
		System.out.println(centro);
	}

	@Test
	public void agregarEspecialidadtest() {
		centro.agregarEspecialidad("Cardiologia", 2000);
		centro.agregarEspecialidad("Dermatologia", 1500);
		centro.agregarEspecialidad("Pediatria", 1200);// no agrega repetidos
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
		centro.agregarPacientePrivado("Rosa Rios", 321, new Fecha(3,2,1971));// no agrega repetidos
		assertEquals(1, centro.pacientes.size());	
	}
	
	@Test
	public void agregarPacienteAmbulatorioTest() {
		centro.agregarPacienteAmbulatorio("Yoel Camino", 543, new Fecha(20,6,2015));
		centro.agregarPacienteAmbulatorio("Yoel Camino", 543, new Fecha(20,6,2015));//no agrega repetidos
		assertEquals(1, centro.pacientes.size());
	}
	
	@Test
	public void agregarPacienteObraSociaTest() {
		centro.agregarPacienteObraSocial("Damian Moreno", 432, new Fecha(1,5,1963),"Osplad", 0.2);
		centro.agregarPacienteObraSocial("Damian Moreno", 432, new Fecha(1,5,1963),"Osplad", 0.2);//no agrega repetidos
		assertEquals(1,centro.pacientes.size());
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
	
	public void getSaldoTest() {
		
		centro.agregarEspecialidad("Cardiologia", 2000.0);
		centro.agregarMedico("Rene Vena", 12345, "Cardiologia", 6565);
		centro.agregarPacientePrivado("Rosa Rios", 321, new Fecha(3,2,1971));
		
		centro.agregarAtencion(321, new Fecha(10,10,2020), 6565);
		centro.agregarAtencion(321, new Fecha(10,11,2020), 6565);
		assertEquals(centro.getSaldo(321),4000,5);
		
	}
	
	public void setSaldoTest() {
		centro.agregarEspecialidad("Cardiologia", 2000.0);
		centro.agregarMedico("Rene Vena", 12345, "Cardiologia", 6565);
		centro.agregarPacientePrivado("Rosa Rios", 321, new Fecha(3,2,1971));
		
		centro.agregarAtencion(321, new Fecha(10,10,2020), 6565);
		centro.agregarAtencion(321, new Fecha(10,11,2020), 6565);
		centro.pagarSaldo(321);
		assertEquals(centro.getSaldo(321),0,1);
	}
	
	public void agregarAtencionTest() {
		centro.agregarPacientePrivado("Rosa Rios", 321, new Fecha(3,2,1971));
		centro.agregarPacienteObraSocial("Damian Moreno", 432, new Fecha(1,5,1963),"Osplad", 0.2);
		// atenciones en guardia
		centro.agregarAtencion(321, new Fecha(11,10,2020));
		// atenciones en consultorio
		centro.agregarAtencion(321, new Fecha(19,10,2020),34567);
		//atencion de internacion
		centro.agregarInternacion(432, "Cardeologia", Fecha.hoy());
		centro.agregarInternacion(432, "Cardeologia", Fecha.hoy());
		assertEquals(1,centro.atencionesEnConsultorio(321));
		assertEquals(1,centro.listaInternacion());
	}
	
	public void agregarInternaciontTest() {
		centro.agregarEspecialidad("Cardiologia", 2000.0);
		centro.agregarMedico("Rene Vena", 12345, "Cardiologia", 6565);
		centro.agregarPacienteObraSocial("Damian Moreno", 432, new Fecha(1,5,1963),"Osplad", 0.2);
		centro.agregarPacienteObraSocial("Camila Romero", 785, new Fecha(4,7,1945),"UOM", 0.4);
		centro.agregarInternacion(432, "Cardeologia", Fecha.hoy()); //Dos veces pero se agrega 1.
		centro.agregarInternacion(432, "Cardeologia", Fecha.hoy()); 
		centro.agregarInternacion(785, "Cardelogia", new Fecha(12,5,2020));
		centro.altaInternacion(785, Fecha.hoy());
		assertEquals(1,centro.listaInternacion().size());
	}
	
	public void altaInternacionTest() {
		centro.agregarEspecialidad("Cardiologia", 2000.0);
		centro.agregarMedico("Rene Vena", 12345, "Cardiologia", 6565);
		centro.agregarPacienteObraSocial("Damian Moreno", 432, new Fecha(1,5,1963),"Osplad", 0.2);
		centro.agregarInternacion(432, "Cardiologia", new Fecha(5,1,2020));
		centro.altaInternacion(432, new Fecha(4,1,2020)); //Fecha invalida no se da el alta
		assertEquals(centro.listaInternacion().size(),1);
		centro.altaInternacion(432, Fecha.hoy()); //Fecha valida
		assertEquals(centro.listaInternacion().size(),0);
	}
	
	public void atencionesEnConsultorioTest() {
		centro.agregarPacientePrivado("Rosa Rios", 321, new Fecha(3,2,1971));
		centro.agregarEspecialidad("Cardiologia", 2000.0);
		centro.agregarMedico("Rene Vena", 12345, "Cardiologia", 6565);
		centro.agregarAtencion(321, new Fecha(19,10,2020),6565);
		centro.agregarAtencion(321, new Fecha(19,10,2020),6565); //No agrega mas de una atencion por fecha
		centro.agregarAtencion(321, new Fecha(11,10,2020));
		assertEquals(1,centro.atencionesEnConsultorio(321));
		
	}
	
	
	
	
	
	
	
	
	
}