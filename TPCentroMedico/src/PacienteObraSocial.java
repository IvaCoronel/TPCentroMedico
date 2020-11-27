

public class PacienteObraSocial extends Paciente {

	private String obraSocial;
	private int cantInternaciones; // chequear
	private double porcentaje; 
	
	public PacienteObraSocial(String nombre, int hc, Fecha nac, String ObraSocial, double porcentaje) {
		super(nombre, hc, nac);
		this.obraSocial = ObraSocial;
		this.porcentaje = porcentaje;
		
	}

	public String getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}

	public int getCantInternaciones() {
		return cantInternaciones;
	}

	public void setCantInternaciones(int cantInternaciones) {
		this.cantInternaciones = cantInternaciones;
	}

	public double getPorcentaje() {
		return porcentaje;
	}

	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	
}
