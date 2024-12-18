package negocio;

import datos.ArqueoDatos;
import entidad.ArqueoEntidad;
import java.sql.ResultSet;

public class ArqueoNegocio {
    
    public double getVentaCajero(int id_c) {
        double total = 0.0;
        ArqueoDatos _arD = new ArqueoDatos();
        try {
            ResultSet _rs = _arD.getVentaCajero(id_c);
            while (_rs.next()) {
                total = _rs.getDouble("total");
            }
        } catch(Exception e) {
            System.err.println("Problemas al cargar la venta total (NEGOCIO) " + e.getMessage());
        }
        return total;
    }
    
    public boolean cierreCaja(ArqueoEntidad arE) {
        boolean r = false;
        ArqueoDatos _arD = new ArqueoDatos();
        try {
            _arD.cierreCaja(arE);
            r = true;
        } catch (Exception e) {
            System.err.println("Problemas al cerrar Caja (NEGOCIO) " + e.getMessage());
        }
        return r;
    }
}
