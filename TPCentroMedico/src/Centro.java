import java.util.HashMap;
import java.util.HashSet;

public class Centro {
	private String nombre;
	private int CUIT;
	private HashMap<Integer, Paciente> pacientes;
	private HashSet<Especialidad> especialidades;
	private double valorInternacion;
	
	public Centro(int cuit, String nombre){
		this.CUIT=cuit;
		this.nombre= nombre;
	}
	public void cambiarValorInternacion(double valor){
		
	}
/*	
	public double deudaPaciente(Paciente paciente){
		
	}
	public void pagarDeudaPaciente(Paciente paciente){
		
	}
	
	public boolean agregarEspecialidad(String nombre, double valorConsulta){
		
	}
	public boolean agregarPacientePrivado(String nombre, int HistoriaClinica, Fecha nac){
		
	}
	public boolean agregarPacienteObraSocial(String nombre, int HistoriaClinica, Fecha nac, String ObraSocial, double porcentaje ){
		
	}
	public boolean agregarPacienteAmbulatorio(String nombre, int HistoriaClinica, Fecha nac){
		
	}
	public boolean agregarMedico(String nombre, Int matricula, String especialidad, double valorTratamiento){
		
	}
	public ArrayList<Atención> atencionesConsultorio(Paciente paciente){
		
	}
	public ArrayList<Paciente> pacientesInternados(){
		
	}
	*/
}
