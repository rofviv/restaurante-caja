package negocio;

import datos.DetalleDatos;
import entidad.DetalleEntidad;
import entidad.DetalleTicket;
import entidad.VentaEntidad;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetalleNegocio {
    
    public boolean insertar_detalle(DetalleEntidad _detE) {
        DetalleDatos _detD = new DetalleDatos();
        boolean _r = false;
        try {
            _detD.insertar_detalle(_detE);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al insertar el detalle (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }
    
    public Map getDetalleVenta(int id_v) {
        Map obj = new HashMap();
        DetalleDatos _detD = new DetalleDatos();
        ArrayList<DetalleTicket> _lst = new ArrayList<>();
        try {
            String cliente = "";
            String tipo = "";
            double total = 0.0;
            int nroVenta = 0;
            ResultSet _rs = _detD.getDetalleVenta(id_v);
            VentaEntidad _vetE = new VentaEntidad();
            while (_rs.next()) {
                int cant = _rs.getInt("cantidad");
                cliente = _rs.getString("cliente");
                tipo = _rs.getString("tipo");
                String nombre = _rs.getString("nombre");
                double punit = _rs.getDouble("precio");
                _lst.add(new DetalleTicket(String.valueOf(cant), nombre, String.valueOf(punit), String.valueOf(cant * punit), "ING"));
                nroVenta = _rs.getInt("nroventa");
                total += cant * punit;
            }
            _vetE.setCliente(cliente);
            _vetE.setTipo(tipo);
            _vetE.setNroVenta(nroVenta);
            _vetE.setTotal(total);
            obj.put("venta", _vetE);
            obj.put("detalle", _lst);
        } catch (Exception ex) {
            System.err.println("Problemas al cargar detalle de venta ID (NEGOCIO) " + ex.getMessage());
        }
        return obj;
    }
}
