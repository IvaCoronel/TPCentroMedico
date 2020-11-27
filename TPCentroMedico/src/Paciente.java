import java.util.ArrayList;
import java.util.HashSet;

public abstract class Paciente {
	private String nombre;
	private int historiaClinica;
	private Fecha nac;
	protected ArrayList<Atencion> atenciones;
	private double deuda;
	
	
	
	public Paciente(String nombre, int historiaClinica, Fecha nac) {
		this.nombre = nombre;
		this.historiaClinica = historiaClinica;
		this.nac = nac;
		atenciones =new ArrayList<Atencion>();
	}
	 
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getHistoriaClinica() {
		return historiaClinica;
	}
	public void setHistoriaClinica(int historiaClinica) {
		this.historiaClinica = historiaClinica;
	}
	public Fecha getNac() {
		return nac;
	}
	public void setNac(Fecha nac) {
		this.nac = nac;
	}
	
	public double getDeuda() {
		return deuda;
	}
	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}

	@Override
	public String toString() {
		return "Paciente [nombre=" + nombre + ", historiaClinica=" + historiaClinica + ", nac=" + nac + ", atenciones="
				+ atenciones + ", deuda=" + deuda + "]";
	}
	
	
	
}
