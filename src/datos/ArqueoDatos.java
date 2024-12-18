package datos;

import entidad.ArqueoEntidad;
import entidad.Fecha;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;

public class ArqueoDatos {
    
    private ConexionDB db = new ConexionDB();
    
    public ResultSet getVentaCajero(int id_c) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL venta_usuario(?, ?)}");
        _sen.setInt(1, id_c);
        _sen.setDate(2, Date.valueOf(Fecha.getFecha()));
        ResultSet rs = _sen.executeQuery();
        return rs;
    }
    
    public void cierreCaja(ArqueoEntidad arqE) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL cierre_caja(?, ?, ?, ?, ?, ?, ?, ?)}");
        _sen.setString(1, arqE.getFecha());
        _sen.setString(2, arqE.getHora());
        _sen.setDouble(3, arqE.getArqueo());
        _sen.setDouble(4, arqE.getBalance());
        _sen.setDouble(5, arqE.getDiferencia());
        _sen.setString(6, arqE.getDetalle());
        _sen.setString(7, arqE.getNota());
        _sen.setInt(8, arqE.getId_cajero());
        _sen.execute();
        
        _cn.close();
    }
}
