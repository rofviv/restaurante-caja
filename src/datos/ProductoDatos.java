package datos;

import entidad.ProductoEntidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class ProductoDatos {
    
    private ConexionDB db = new ConexionDB();
    
    public ResultSet getProductosCategoria(int id_c) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL productos_categoria(?)}");
        _sen.setInt(1, id_c);
        ResultSet rs = _sen.executeQuery();
        return rs;
    }
    
    public ResultSet getTodosProductosCategoria(int id_c) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL todos_productos_categoria(?)}");
        _sen.setInt(1, id_c);
        ResultSet rs = _sen.executeQuery();
        return rs;
    }
    
    public void habilitarProducto(int id_p) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL habilitar_producto(?)}");
        _sen.setInt(1, id_p);
        _sen.execute();
        
        _cn.close();
    }
    
    public void deshabilitarProducto(int id_p) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL deshabilitar_producto(?)}");
        _sen.setInt(1, id_p);
        _sen.execute();
        
        _cn.close();
    }
    
    public ResultSet nuevoProducto(ProductoEntidad prodE) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL nuevo_producto(?, ?, ?, ?, ?)}");
        _sen.setString(1, prodE.getNombre());
        _sen.setString(2, prodE.getDescripcion());
        _sen.setDouble(3, prodE.getPrecio());
        _sen.setString(4, prodE.getImagen());
        _sen.setInt(5, prodE.getIdCategoria());
        ResultSet rs = _sen.executeQuery();
        
        return rs;
    }
    
    public void actualizarProducto(ProductoEntidad prodE) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL actualizar_producto(?, ?, ?, ?, ?, ?)}");
        _sen.setInt(1, prodE.getIdProducto());
        _sen.setString(2, prodE.getNombre());
        _sen.setString(3, prodE.getDescripcion());
        _sen.setDouble(4, prodE.getPrecio());
        _sen.setString(5, prodE.getImagen());
        _sen.setInt(6, prodE.getIdCategoria());
        _sen.execute();
        
        _cn.close();
    }

    public void borrarGuarnicion(int id_p, int id_g) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL borrar_producto_guarnicion(?, ?)}");
        _sen.setInt(1, id_p);
        _sen.setInt(2, id_g);
        _sen.execute();
        
        _cn.close();
    }

    public void adicionarGuarnicion(int id_p, int id_g) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL nuevo_producto_guarnicion(?, ?)}");
        _sen.setInt(1, id_p);
        _sen.setInt(2, id_g);
        _sen.execute();
        
        _cn.close();
    }
}
