import java.util.ArrayList;
import java.util.HashMap;
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
	 * internados esta en pacientes
	 * internados.size == sum(pacientes.values().filter(internados).size) ...
	 * un medico tiene que tener una especialidad existente en especialidades y una especialidad estar asignada a un medico.
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
			sb.append(i.toString()).append("\n");
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
	
	//Falta el test
	public double getSaldo(int hc){ 
		Paciente p = obtenerPaciente(hc);
		if(p!=null) {
			return p.getDeuda();
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}
	
	//Falta el test
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
	
	//Falta el test
	
	void agregarAtencion(int hc, Fecha fecha, int matricula) {
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

	//Falta el test
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
	
	//Falta el test
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
	
	//FALTA TEST
	public Map<Fecha, String> atencionesEnConsultorio(int hc){
		HashMap<Fecha,String> atenConsultorio= new HashMap<Fecha,String>();
		Paciente p = obtenerPaciente(hc);
		if(p!=null && p instanceof PacientePrivado){
			for(Atencion a: p.atenciones){
				if(a instanceof Consultorio){
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

public static void main(String[] args) {
	Centro centro=new Centro("Centro Medico Dolores Fuentes","30-36542563-0",3000);
	centro.agregarEspecialidad("Pediatria",2000);
	centro.agregarEspecialidad("Cardiologia",3000);
	centro.agregarEspecialidad("Traumatologia",2500);
	centro.agregarMedico("Dr Perez", 55555, "Pediatria", 5000);
	centro.agregarMedico("Dr Rodriguez", 66666, "Cardiologia", 8000);
	centro.agregarMedico("Dr Curetta", 77777, "Traumatologia", 2000);
	centro.agregarPacientePrivado("Juan", 111, new Fecha(20,11,1980));
	centro.agregarPacienteObraSocial("Carlos", 222, new Fecha(15,1,1940), "Pami",0.3);
	centro.agregarPacienteAmbulatorio("Pedro", 333, new Fecha(28,2,1970));
	centro.agregarPacienteObraSocial("Jose", 444, new Fecha(15,1,1940), "Ospe",0.2);
	centro.agregarAtencion(111, new Fecha(25,10,2020));
	centro.agregarAtencion(111, Fecha.hoy(), 55555);
	System.out.println("Lista de internacion:" + centro.listaInternacion());
	System.out.println("\nSe agrega una internacion...\n");
	centro.agregarInternacion(222,"Cardiologia",new Fecha(20,10,2020));
	System.out.println("Lista de internacion:" + centro.listaInternacion());
	System.out.println("\nSe da de alta la internacion...\n");
	centro.altaInternacion(222, new Fecha(14,11,2020));
	System.out.println("Lista de internacion:" + centro.listaInternacion());
	System.out.println("\nSe agregan dos nuevas internaciones...\n");
	centro.agregarInternacion(222,"General",new Fecha(16,11,2020));
	centro.agregarInternacion(444,"Pediatria",new Fecha(17,11,2020));
	System.out.println("Lista de internacion:" + centro.listaInternacion());
	centro.agregarTratamiento(333, 66666, "Angioplastia");
	System.out.println("\n\n" + centro.toString());
	System.out.println("Deuda paciente HC 111: " + centro.getSaldo(111));
	System.out.println("Deuda paciente HC 222: " + centro.getSaldo(222));
	System.out.println("Deuda paciente HC 333: " + centro.getSaldo(333));
	System.out.println("\nSaldando deudas...\n");
	centro.pagarSaldo(111);
	centro.pagarSaldo(333);
	centro.pagarSaldo(222);
	System.out.println("Deuda paciente HC 111: " + centro.getSaldo(111));
	System.out.println("Deuda paciente HC 222: " + centro.getSaldo(222));
	System.out.println("Deuda paciente HC 333: " + centro.getSaldo(333));
	System.out.println("\n\n"+centro.toString());
	System.out.println("\nAgrego nueva atencion paciente 111...\n");
	centro.agregarAtencion(111, new Fecha(18,11,2020),77777);
	System.out.println("Atenciones paciente 111:");
	System.out.println(centro.atencionesEnConsultorio(111));
	}

	

}	

