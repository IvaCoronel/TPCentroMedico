import java.time.LocalDate;
import static java.time.temporal.ChronoUnit.DAYS;
public class Fecha {
	private LocalDate fecha;
	
	public Fecha(int dia, int mes, int año) {
		fecha =  LocalDate.of(año, mes, dia);
		
	}
	
	public static Fecha hoy(){
		Fecha f = new Fecha(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear());
		return f;
	}
	public  boolean DespuesDe(Fecha fecha1) {
		return this.fecha.isAfter(fecha1.fecha);
	}
	public static int cantDias(Fecha fecha1){
		long cantDias= DAYS.between(fecha1.fecha,hoy().fecha);
		return (int) cantDias;
	}


	
}
