
public class Guardia extends Atencion {

	public Guardia(Fecha fecha) {
		super(fecha);
		
	}

	

	
	@Override
	public String toString() {
		String s= super.toString();
		return  "Guardia ["+ s +  "]";
	}

}
