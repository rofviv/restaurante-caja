package entidad;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha {
    private static final Calendar fecha = new GregorianCalendar();
    private static final int anho = fecha.get(Calendar.YEAR);
    private static final int mes = fecha.get(Calendar.MONTH);
    private static final int dia = fecha.get(Calendar.DAY_OF_MONTH);
    
    public static String getFecha() {
        String diaAux = "";
        String mesAux = "";
        if (dia < 10) {
            diaAux = "0";
        }
        if ((mes + 1) < 10) {
           mesAux = "0";
        }
        return anho + "-" + mesAux + (mes + 1) + "-" + diaAux + (dia);
    }
    
}
