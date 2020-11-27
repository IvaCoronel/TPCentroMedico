
public class Internacion extends Atencion {
	private String area;
	private Fecha fechaAlta;
	private Fecha fechaIngreso;
	
	public Internacion(String area,  Fecha fechaIngreso) {
		super();
		this.area = area;
		this.fechaIngreso=fechaIngreso;
	}

	public String getArea() {
		return area;
	}

	public Fecha getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Fecha fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Fecha getFechaIngreso() {
		return fechaIngreso;
	}

	
}
