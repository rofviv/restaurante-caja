package negocio;

import datos.VentaDatos;
import entidad.InfoTipoVenta;
import entidad.VentaEntidad;
import java.sql.ResultSet;
import java.util.ArrayList;

public class VentaNegocio {
    
    public int nueva_venta(VentaEntidad _vt) {
        VentaDatos _vtD = new VentaDatos();
        int id = 0;
        try {
            ResultSet rs = _vtD.nueva_venta(_vt);
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            System.err.println("Problemas en ingresar la venta (NEGOCIO) " + e.getMessage());
        }
        return id;
    }
    
    public int getUltimoId() {
        VentaDatos _vtD = new VentaDatos();
        int id = 0;
        try {
            ResultSet rs = _vtD.getUltimoId();
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            System.err.println("Problemas al OBTENER el ID de la venta (NEGOCIO) " + e.getMessage());
        }
        return id;
    }
    
    public Object[] getVentaDiaria(String fecha) {
        VentaDatos _vtD = new VentaDatos();
        Object[] datos = new Object[2];
        int cant = 0;
        double total = 0;
        try {
            ResultSet rs = _vtD.getVentaDiaria(fecha);
            if (rs.next()) {
                cant = rs.getInt("cant");
                total = rs.getDouble("total");
            }
            datos[0] = cant;
            datos[1] = total;
        } catch (Exception e) {
            System.err.println("Problemas al OBTENER la venta diaria (NEGOCIO) " + e.getMessage());
        }
        return datos;
    }
    
    public ArrayList<InfoTipoVenta> getResumenVenta(String fecha_ini, String fecha_fin, String usuario) {
        VentaDatos _vtD = new VentaDatos();
        ArrayList<InfoTipoVenta> arTipo = new ArrayList<>();
        try {
            ResultSet rs = _vtD.getResumenVentas(fecha_ini, fecha_fin, usuario);
            while (rs.next()) {
                InfoTipoVenta inf = new InfoTipoVenta();
                inf.setTipo(rs.getString("metodo_pago"));
                inf.setCant(rs.getInt("cant"));
                inf.setMonto(rs.getDouble("total"));
                inf.setPromedio(rs.getDouble("promedio"));
                arTipo.add(inf);
            }
        } catch (Exception e) {
            System.err.println("Problemas al OBTENER la resumen de ventas (NEGOCIO) " + e.getMessage());
        }
        return arTipo;
    }

    public ArrayList<VentaEntidad> getUltimasVentas(int id_u, String fecha) {
        VentaDatos _venD = new VentaDatos();
        ArrayList<VentaEntidad> _lst = new ArrayList<>();
        try {
            ResultSet _rs = _venD.getUltimasVentas(id_u, fecha);
            while (_rs.next()) {
                VentaEntidad _ent = new VentaEntidad();
                _ent.setIdVenta(_rs.getInt("id"));
                _ent.setCliente(_rs.getString("cliente"));
                _ent.setNroVenta(_rs.getInt("nroventa"));
                _ent.setTipoPago(_rs.getString("metodo_pago"));
                _ent.setTotal(_rs.getDouble("total"));
                _lst.add(_ent);
            }
        } catch (Exception ex) {
            System.err.println("Problemas al cargar las ultimas 10 ventas (NEGOCIO) " + ex.getMessage());
        }
        return _lst;
    }
}
