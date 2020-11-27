import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;

public class Fecha  {
	private LocalDate fecha;
	
	public Fecha(int dia, int mes, int a�o) {
		fecha =  LocalDate.of(a�o, mes, dia);
	}
	
	public static Fecha hoy(){
		Fecha f = new Fecha(LocalDate.now().getDayOfMonth(),LocalDate.now().getMonthValue(),LocalDate.now().getYear());
		return f;
	}
	public  boolean DespuesDe(Fecha fecha1) {
		return this.fecha.isAfter(fecha1.fecha);
	}
	
}
