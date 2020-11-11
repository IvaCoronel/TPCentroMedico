import java.util.HashSet;

public class PacienteAmbulatorio extends Paciente {
	
 
	public PacienteAmbulatorio(String nombre, int historiaClinica, Fecha nac) {
		super(nombre, historiaClinica, nac);
		
	}

	public boolean registrarTratamiento(Medico medico,String tratamiento){
		Tratamiento t= new Tratamiento(medico,tratamiento);
		return super.atenciones.add(t);
	}
	
	
} 
