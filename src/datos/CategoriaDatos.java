package datos;

import entidad.CategoriaEntidad;
import entidad.ProductoEntidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class CategoriaDatos {
    
    private ConexionDB db = new ConexionDB();
    
    public ResultSet getCategoria() throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL categoria_activo}");
        ResultSet rs = _sen.executeQuery();
        return rs;
    }
    
    public ResultSet getCategoriaTotal() throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL categoria_total}");
        ResultSet rs = _sen.executeQuery();
        return rs;
    }
    
    public void nuevaCategoria(CategoriaEntidad _catE) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL nueva_categoria(?)}");
        _sen.setString(1, _catE.getCategoria());
        _sen.execute();
        
        _cn.close();
    }
    
    public void habilitarProducto(int id_c) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL habilitar_categoria(?)}");
        _sen.setInt(1, id_c);
        _sen.execute();
        
        _cn.close();
    }
    
    public void deshabilitarProducto(int id_c) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL deshabilitar_categoria(?)}");
        _sen.setInt(1, id_c);
        _sen.execute();
        
        _cn.close();
    }
    
    public void actualizarCategoria(CategoriaEntidad catE) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL actualizar_categoria(?, ?, ?)}");
        _sen.setInt(1, catE.getIdCategoria());
        _sen.setString(2, catE.getCategoria());
        _sen.setInt(3, catE.getActivo());
        _sen.execute();
        
        _cn.close();
    }
}
