package datos;

import entidad.UsuarioEntidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class UsuarioDatos {
    
    private ConexionDB db = new ConexionDB();
    
    public ResultSet loguin(UsuarioEntidad _usr) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL loguin(?, ?)}");
        _sen.setString(1, _usr.getUsuario());
        _sen.setString(2, _usr.getClave());
        ResultSet rs = _sen.executeQuery();
        return rs;
    }
    
    public void nuevo_usuario(UsuarioEntidad _usr) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL nuevo_usuario(?, ?, ?, ?)}");
        _sen.setString(1, _usr.getNombre());
        _sen.setString(2, _usr.getApellido());
        _sen.setString(3, _usr.getUsuario());
        _sen.setString(4, _usr.getClave());
        _sen.execute();
        _cn.close();
    }

    public ResultSet clave_admin() throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL clave_admin()}");
        ResultSet rs = _sen.executeQuery();
        return rs;
    }

    public void cambiarClaveAdmin(String clave) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL cambiar_clave_admin(?)}");
        _sen.setString(1, clave);
        _sen.execute();
        _cn.close();
    }

    public ResultSet getUsuarioTotal() throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL usuarios_total}");
        ResultSet rs = _sen.executeQuery();
        return rs;
    }

    public void habilitarUsuario(int id) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL habilitar_usuario(?)}");
        _sen.setInt(1, id);
        _sen.execute();
        
        _cn.close();
    }

    public void deshabilitarUsuario(int id) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL deshabilitar_usuario(?)}");
        _sen.setInt(1, id);
        _sen.execute();
        
        _cn.close();
    }

    public void actualizarUsuario(UsuarioEntidad _usE) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL actualizar_usuario(?, ?, ?, ?)}");
        _sen.setInt(1, _usE.getIdUsuario());
        _sen.setString(2, _usE.getUsuario());
        _sen.setString(3, _usE.getClave());
        _sen.setInt(4, _usE.getActivo());
        _sen.execute();
        
        _cn.close();
    }
}
