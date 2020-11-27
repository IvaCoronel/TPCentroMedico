
public class Consultorio extends Atencion {
	private Integer matricula;
	private Fecha fecha;
	
	public Consultorio(Integer matricula, Fecha fecha) {
		super(fecha);
		this.matricula = matricula;
		
	}
	public Integer getMatricula() {
		return matricula;
	}

	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
	
	

}
