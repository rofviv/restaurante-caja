package datos;

import entidad.DetalleEntidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DetalleDatos {
    
    private ConexionDB db = new ConexionDB();
    
    public void insertar_detalle(DetalleEntidad detE) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL insertar_detalle(?, ?, ?, ?)}");
        _sen.setInt(1, detE.getCantidad());
        _sen.setString(2, detE.getNota());
        _sen.setInt(3, detE.getIdProducto());
        _sen.setInt(4, detE.getIdVenta());
        _sen.execute();
        
        _cn.close();
    }
    
    public ResultSet getDetalleVenta(int id_v) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL detalle_venta(?)}");
        _sen.setInt(1, id_v);
        ResultSet rs = _sen.executeQuery();
        
        return rs;
    }
}
