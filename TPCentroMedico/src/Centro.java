import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class Centro {
	private String nombre;
	private String CUIT;
	protected HashMap<Integer, Paciente> pacientes;
	protected HashSet<Especialidad> especialidades;
	protected HashSet<Medico> medicos;
	private double valorInternacion;
	
	public Centro( String nombre,String string, double valorInt){
		this.CUIT=string;
		this.nombre= nombre;
		this.valorInternacion=valorInt;
		pacientes= new HashMap<Integer,Paciente>();
		especialidades = new HashSet<Especialidad>();
		medicos = new HashSet<Medico>();
		
	}
	
	public void cambiarValorInternacion(double valor){
		valorInternacion=valor;
	}
	
	public boolean agregarEspecialidad(String nombre, double valorConsulta){
		Especialidad esp= new Especialidad(nombre,valorConsulta);
		if(!especialidades.contains(esp)){
			return especialidades.add(esp);
		}
		else{
			return false;
		}
	}
	
	public boolean agregarMedico(String nombre, int matricula, String especialidad, double valorTratamiento){
		Medico med=new Medico( nombre ,matricula , especialidad ,valorTratamiento);
		if(!medicos.contains(med)){
			return medicos.add(med);
		}
		else{
			return false;
		}
	}	
	
	public boolean agregarPacientePrivado(String nombre, int hc, Fecha nac){
		PacientePrivado p= new PacientePrivado(nombre,hc,nac);
		if(!pacientes.containsKey(hc)){
			pacientes.put(hc, p);
			return true;
		}
		else{
			return false;
		}
		
		
	}
	
	public boolean agregarPacienteObraSocial(String nombre, int hc, Fecha nac, String ObraSocial, double porcentaje ){
		PacienteObraSocial p = new PacienteObraSocial(nombre, hc, nac, ObraSocial, porcentaje);
		if(!pacientes.containsKey(hc)) {
			pacientes.put(hc, p);
			return true;
		}
		else {
			return false;
		}	
	}
	
	public boolean agregarPacienteAmbulatorio(String nombre, int hc, Fecha nac){
		PacienteAmbulatorio p= new PacienteAmbulatorio(nombre,hc,nac);
		if(!pacientes.containsKey(hc)){
			pacientes.put(hc, p);
			return true;
		}
		else{
			return false;
		}
	}
	
	//Falta el test
	public double getSaldo(int hc){ 
		if(pacientes.get(hc)!=null) {
			return pacientes.get(hc).getDeuda();
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}
	
	//Falta el test
	public void pagarSaldo(int hc){
		if(pacientes.get(hc)!=null) {
			pacientes.get(hc).setDeuda(0);
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}
	
	//Falta el test
	void agregarAtencion(int hc, Fecha fecha, int matricula) {
		Consultorio c=new Consultorio(matricula, fecha);
		if(pacientes.get(hc)!=null) {
			pacientes.get(hc).atenciones.add(c);
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}

	//Falta el test
	void agregarAtencion(int hc, Fecha fecha) {
		Guardia g=new Guardia(fecha);
		if(pacientes.get(hc)!=null) {
			pacientes.get(hc).atenciones.add(g);
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}
	
	//Falta el test
	void agregarInternacion(int hc, String area, Fecha fingreso) {
		Internacion i = new Internacion(area,fingreso);
		if(pacientes.get(hc)!=null) {
			pacientes.get(hc).atenciones.add(i);
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}
	
	//Falta test
	void altaInternacion(int hc, Fecha fechaAlta) {
		if(pacientes.get(hc)!=null) {
			if(pacientes.get(hc) instanceof PacienteObraSocial) {
				Internacion i = (Internacion)pacientes.get(hc).atenciones.get(pacientes.size()-1);
				i.setFechaAlta(fechaAlta);
			}
			else {
				throw new RuntimeException("No es paciente de Obra social");
			}
		}
		else {
			throw new RuntimeException("Paciente invalido");
		}
	}
/*
	public ArrayList<Atencion> atencionesEnConsultorio(int hc){
		
	}
	public ArrayList<Paciente> listaInternacion(){
		
	}
	public void agregarAtencion(int m,Fecha f){
		
	}
	public void agregarInternacion(int d, String area, Fecha f){
		
	}
	public void altaInternacion(int j,Fecha f){
		
	}
	public void agregarTratamiento(int s, int a, String area){
		
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
	/*
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

	*/
}
