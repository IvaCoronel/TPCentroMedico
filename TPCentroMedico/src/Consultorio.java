
public class Consultorio extends Atencion {
	private Integer matricula;
	
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
	@Override
	public String toString() {
		String s= super.toString();
		
		return  "Consultorio [" + s + " matricula=" + matricula + "]";
	}


	
}
