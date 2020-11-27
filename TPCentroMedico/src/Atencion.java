
public abstract class Atencion {
	private Fecha fecha;
	protected boolean pagado;
	protected double monto;
	
	public Atencion(Fecha fecha) {
		this.fecha = fecha;
	}
	public Atencion() {
		
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}
	
	
	
	
	
	
	

}
