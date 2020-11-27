
public class Guardia extends Atencion {
	private Fecha fecha;
	
	public Guardia(Fecha fecha) {
		super(fecha);
		
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
	
}
