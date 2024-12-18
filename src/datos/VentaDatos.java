package datos;

import entidad.VentaEntidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class VentaDatos {
    
    private ConexionDB db = new ConexionDB();
    
    public ResultSet nueva_venta(VentaEntidad _vt) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL nueva_venta(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
        _sen.setString(1, _vt.getCliente());
        _sen.setInt(2, _vt.getNroVenta());
        _sen.setString(3, _vt.getTipo());
        _sen.setString(4, _vt.getTipoPago());
        _sen.setString(5, _vt.getFecha());
        _sen.setString(6, _vt.getHora());
        _sen.setDouble(7, _vt.getTotal());
        _sen.setString(8, _vt.getObservacion());
        _sen.setInt(9, _vt.getIdUsuario());
        ResultSet rs = _sen.executeQuery();
        
        return rs;
    }
    
    public ResultSet getUltimoId() throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL id_ultima_venta}");
        ResultSet rs = _sen.executeQuery();
        
        return rs;
    }
    
    public ResultSet getVentaDiaria(String fecha) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL venta_diaria(?)}");
        _sen.setString(1, fecha);
        ResultSet rs = _sen.executeQuery();
        
        return rs;
    }
    
    public ResultSet getResumenVentas(String fecha_ini, String fecha_fin, String usuario) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL resumen_venta(?, ?, ?)}");
        _sen.setString(1, fecha_ini);
        _sen.setString(2, fecha_fin);
        _sen.setString(3, usuario);
        ResultSet rs = _sen.executeQuery();
        
        return rs;
    }

    public ResultSet getUltimasVentas(int id_u, String fecha) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL ultimas_ventas(?, ?)}");
        _sen.setInt(1, id_u);
        _sen.setString(2, fecha);
        ResultSet rs = _sen.executeQuery();
        return rs;
    }
}
