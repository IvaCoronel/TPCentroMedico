
public class Internacion extends Atencion {
	private String area;
	private Fecha fechaAlta;
	
	
	public Internacion(String area,  Fecha fechaIngreso) {
		super(fechaIngreso);
		this.area = area;
		
	}

	public String getArea() {
		return area;
	}

	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Fecha fechaalta) {
		this.fechaAlta = fechaalta;
	}
	
	
	
	
}
