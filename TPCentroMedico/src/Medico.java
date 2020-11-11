
public class Medico {
	private String nombre;
	private int matricula;
	private String especialidad;
	private double valorHonorarios;
	
	public Medico(String nombre, int matricula,String especialidad, double valorHonorarios) {
		super();
		this.nombre = nombre;
		this.matricula = matricula;
		this.especialidad = especialidad;
		this.valorHonorarios = valorHonorarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((especialidad == null) ? 0 : especialidad.hashCode());
		result = prime * result + matricula;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valorHonorarios);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (especialidad == null) {
			if (other.especialidad != null)
				return false;
		} else if (!especialidad.equals(other.especialidad))
			return false;
		if (matricula != other.matricula)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(valorHonorarios) != Double.doubleToLongBits(other.valorHonorarios))
			return false;
		return true;
	}

}
