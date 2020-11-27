
public class Tratamiento extends Atencion{
	private Integer hc;
	private Integer matricula;
	private String area;
	
	public Tratamiento(Integer hc,Integer matricula, String area) {
		super();
		this.hc=hc;
		this.matricula=matricula;
		this.area=area;
	}

	@Override
	public String toString() {
		String m= Double.toString(super.getMonto());
		String p= Boolean.toString(super.isPagado());
		return "Tratamiento [monto="+ m + ", pagado="+ p + ", hc=" + hc + ", matricula=" + matricula + ", area=" + area + "]";
	}
	
}
 