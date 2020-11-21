import java.util.HashSet;

public class PacienteObraSocial extends Paciente {

	private String obraSocial;
	private int cantInternaciones; // chequear
	private double porcentaje; 
	
	public PacienteObraSocial(String nombre, int hc, Fecha nac, String ObraSocial, double porcentaje) {
		super(nombre, hc, nac);
		this.obraSocial = ObraSocial;
		this.porcentaje = porcentaje;
		
	}
	
}
