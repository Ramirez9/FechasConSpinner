package fechasSpinner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
/**
 * 
 * @author Francisco Ramirez
 *
 */
public class Fecha {
	
	static DayOfWeek getDiaDeLaSemanaHoy() {
		LocalDate today = LocalDate.now();
		return today.getDayOfWeek();
	}

	/**
	 * Día de la semana de una fecha pasada
	 * 
	 * @throws FechaNoPasadaException si la fecha no es pasada.
	 */
	static DayOfWeek getDiaDeLaSemanaFechaPasada(LocalDate localDate) throws FechaNoPasadaException {
		if (esFechaFutura(localDate))
			throw new FechaNoPasadaException("La fecha no es pasada.");
		return localDate.getDayOfWeek();
	}

	/**
	 * Comprueba si la fecha es posterior a la actual.
	 * 
	 * @param localDate fecha a comparar.
	 * @return true si esta fecha es posterior al día actual
	 */
	private static boolean esFechaFutura(LocalDate localDate) {
		return localDate.isAfter(LocalDate.now());
	}

	/**
	 * Día de la semana de una fecha futura
	 * 
	 * @throws FechaNoFuturaException si la fecha no es futura.
	 */
	static DayOfWeek getDiaDeLaSemanaFechaFutura(LocalDate localDate) throws FechaNoFuturaException {
		if (!esFechaFutura(localDate))
			throw new FechaNoFuturaException("La fecha no es futura.");
		return localDate.getDayOfWeek();
	}

	/**
	 * Calcula el tiempo que falta para tu próximo cumpleaños
	 */
	String getTiempoAFechaFutura() {
		return null;
	}

	public static String cuentaAtrasCumpleannos(LocalDate fechaDeNacimiento) {
		LocalDate hoy = LocalDate.now();
		LocalDate cumpleannosActual = fechaDeNacimiento.withYear(hoy.getYear());

		// Si ya he cumplido años este año, tengo que esperar al siguiente.
		if (cumpleannosActual.isBefore(hoy) || cumpleannosActual.isEqual(hoy)) {
			cumpleannosActual = cumpleannosActual.plusYears(1);
		}

		Period p = Period.between(hoy, cumpleannosActual);
		long dias = ChronoUnit.DAYS.between(hoy, cumpleannosActual);
		return "Quedan " + p.getMonths() + " meses y " + p.getDays() + " días para tu próximo cumpleaños (" + dias
				+ " días en total)";
	}
}
