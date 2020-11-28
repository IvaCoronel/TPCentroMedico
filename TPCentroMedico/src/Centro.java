import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Centro {
	private String nombre;
	private String CUIT;
	protected HashMap<Integer, Paciente> pacientes;
	protected HashMap<String,Double> especialidades;
	protected HashMap<Integer,Medico> medicos;
	private double valorInternacion;
	private ArrayList<Integer> pacientesInternados;
	
	/*IREP 
	 * 
	 * para todo paciente "p" en pacientes.values(), p.historiaClinica es igual a pacientes.key
	 * matricula y medico.
	 * internados estan en pacientes
	 * internados.size == sum(pacientes.values().filter(internados).size) ...
	 * un medico tiene que tener una especialidad existente en especialidades y una especialidad estar asignada a un medico.
	 * Agregar una Atencion en Consultorio o un Tratamiento debe tener un Medico existente en medicos.
	 *
	 */
	public Centro( String nomb,String string, double valorInt){
		CUIT=string;
		nombre= nomb;
		valorInternacion=valorInt;
		pacientes= new HashMap<Integer,Paciente>();
		especialidades = new HashMap<String,Double>();
		medicos = new HashMap<Integer,Medico>();
		pacientesInternados = new ArrayList<Integer>();
		
	}
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder();
		sb.append("Centro: ");
		sb.append(nombre).append("\n");
		sb.append("CUIT: ");
		sb.append(CUIT).append("\n");
		sb.append("Valor de internación: ");
		sb.append(valorInternacion).append("\n");
		sb.append("Pacientes: ").append("\n");
		for(Paciente p: pacientes.values()){
			sb.append(p.toString()).append("\n");
		}
		sb.append("Especialidades: ").append("\n");
		for(String e:especialidades.keySet()){
			sb.append(e.toString()).append("\n");
		}
		sb.append("Medicos: ").append("\n");
		for(Medico m: medicos.values()){
			sb.append(m.toString()).append("\n");
		}
		sb.append("Pacientes Internados: ").append("\n");
		for(Integer i: pacientesInternados){
			sb.append(pacientes.get(i).toString()).append("\n");
		}
	
		return sb.toString();
	}

	public void cambiarValorInternacion(double valor){
		valorInternacion=valor;
	}
	
	public void agregarEspecialidad(String nombre, double valorConsulta){
		if(!especialidades.containsKey(nombre)){
			especialidades.put(nombre, valorConsulta);
		}
	}
	
	public void agregarMedico(String nombre, int matricula, String especialidad, double valorTratamiento){
		Medico med=new Medico( nombre ,matricula , especialidad ,valorTratamiento);
		if(!medicos.containsKey(matricula)){
			medicos.put(matricula, med);
		}
		
	}	
	
	private Paciente obtenerPaciente(Integer hc){
		return pacientes.get(hc);
	}
	
	public void agregarPacientePrivado(String nombre, int hc, Fecha nac){
		PacientePrivado privado= new PacientePrivado(nombre,hc,nac);	
		Paciente p = obtenerPaciente(hc);
		if(p==null){
			pacientes.put(hc, privado);
		}
	}
	
	public void agregarPacienteObraSocial(String nombre, int hc, Fecha nac, String ObraSocial, double porcentaje ){
		PacienteObraSocial pOSocial = new PacienteObraSocial(nombre, hc, nac, ObraSocial, porcentaje);
		Paciente p = obtenerPaciente(hc);
		if(p==null) {
			pacientes.put(hc, pOSocial);
		}
	}
	
	public void agregarPacienteAmbulatorio(String nombre, int hc, Fecha nac){
		PacienteAmbulatorio pA= new PacienteAmbulatorio(nombre,hc,nac);
		Paciente p = obtenerPaciente(hc);
		if(p==null){
			pacientes.put(hc, pA);
		}
	}
		
	public double getSaldo(int hc){ 
		Paciente p = obtenerPaciente(hc);
		if(p!=null) {
			return p.getDeuda();
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}
	
	public void pagarSaldo(int hc){
		Paciente p = obtenerPaciente(hc);
		if(p!=null) {
			p.setDeuda(0);
			for(Atencion a:p.atenciones){
				a.setPagado(true);
			}
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}
	
	public void agregarAtencion(int hc, Fecha fecha, int matricula) {
		Consultorio c=new Consultorio(matricula, fecha);
		Paciente p = obtenerPaciente(hc);
		if(p!=null && p instanceof PacientePrivado) {
			p.atenciones.add(c);
			String especialidad=obtenerEspecialidad(matricula);
			c.setMonto(especialidades.get(especialidad));
			p.setDeuda(p.getDeuda()+ especialidades.get(especialidad));
		}
		else {
			throw new RuntimeException("El paciente es inválido o no es de tipo Privado");
		}
	}
	
	public void agregarAtencion(int hc, Fecha fecha) {
		Guardia g=new Guardia(fecha);
		Paciente p = obtenerPaciente(hc);
		if(p!=null && p instanceof PacientePrivado) {
			p.atenciones.add(g);
		}
		else {
			throw new RuntimeException("El paciente es inválido o no es de tipo Privado");
		}
	}
	
	public void agregarTratamiento(int hc, int matricula, String area){
		Tratamiento t=new Tratamiento(hc,matricula,area);
		Paciente p = obtenerPaciente(hc);
		if(p!=null && p instanceof PacienteAmbulatorio) {
				p.atenciones.add(t);
				double costo=(medicos.get(matricula).getValorHonorarios());
				t.setMonto(costo);
				p.setDeuda(p.getDeuda()+ costo);
		}
		else {
			throw new RuntimeException("El paciente es inválido o no es un paciente Ambulatorio");
		}
	}
	
	public void agregarInternacion(int hc, String area, Fecha fingreso) {
		Internacion i = new Internacion(area,fingreso);
		Paciente p = obtenerPaciente(hc);
		if(p!=null && p instanceof PacienteObraSocial && !(pacientesInternados.contains(hc))){
				p.atenciones.add(i);
				pacientesInternados.add(hc);
		}
		else {
			throw new RuntimeException("El paciente es inválido o no tiene obra social");
		}
	}

	public void altaInternacion(int hc, Fecha fechaAlta) {
		Paciente p = obtenerPaciente(hc);
		if(p!=null && p instanceof PacienteObraSocial) {
			Internacion i = (Internacion)p.atenciones.get(p.atenciones.size()-1);
			if(fechaAlta.DespuesDe(i.getFecha())) {
				i.setFechaAlta(fechaAlta);
				pacientesInternados.remove((Integer)hc);
				double costo= costoInternacion(i.getFecha(), fechaAlta, ((PacienteObraSocial) p).getPorcentaje());
				i.setMonto(costo);
				p.setDeuda(p.getDeuda() + costo );
			}
			else {
				throw new RuntimeException("La fecha de ingreso es mas reciente que la de alta");
			}	
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}
	
	private double costoInternacion(Fecha ingreso, Fecha alta, double p){
			Integer dias= Fecha.cantDias(ingreso,alta);
			return (dias*valorInternacion)*p;
	}
	
	public List<Integer> listaInternacion() {
		return pacientesInternados;
	}
	
	public Map<Fecha, String> atencionesEnConsultorio(int hc){
		HashMap<Fecha,String> atenConsultorio= new HashMap<Fecha,String>();
		Paciente p = obtenerPaciente(hc);
		if(p!=null && p instanceof PacientePrivado){
			Iterator<Atencion> it =p.atenciones.iterator();
			while(it.hasNext()) {
				Atencion a = it.next();
				if(a instanceof Consultorio) {
					String especialidad=obtenerEspecialidad(((Consultorio) a).getMatricula());
					atenConsultorio.put(a.getFecha(), especialidad );
				}
			}
			return atenConsultorio;
		}
		else{
			throw new RuntimeException("El paciente es inválido o no es de tipo Privado");
		}
		
	}
	
	private String obtenerEspecialidad(Integer matricula){
		 Medico m= medicos.get(matricula);
		 return m.getEspecialidad();
	 }

}	

