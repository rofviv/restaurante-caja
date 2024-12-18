package datos;

import entidad.GuarnicionEntidad;
import entidad.SubguarnicionEntidad;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class GuarnicionDatos {
    
    private ConexionDB db = new ConexionDB();
    
    public ResultSet getGuarnicionProducto(int id_p) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL get_guarnicion_producto(?)}");
        _sen.setInt(1, id_p);
        ResultSet rs = _sen.executeQuery();
        return rs;
    }

    public ResultSet getSubGuarnicion(int id_g) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL get_subguarnicion(?)}");
        _sen.setInt(1, id_g);
        ResultSet rs = _sen.executeQuery();
        return rs;
    }

    public ResultSet nueva_guarnicion(GuarnicionEntidad guarnicion) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL nueva_guarnicion(?, ?, ?)}");
        _sen.setString(1, guarnicion.getReferencia());
        _sen.setString(2,guarnicion.getTitulo());
        _sen.setString(3, guarnicion.getTipo());
        ResultSet rs = _sen.executeQuery();
        
        return rs;
    }

    public void nueva_sub_guarnicion(SubguarnicionEntidad sub_guarnicion) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL nueva_subguarnicion(?, ?, ?)}");
        _sen.setString(1, sub_guarnicion.getNombre());
        _sen.setDouble(2, sub_guarnicion.getPrecio());
        _sen.setInt(3, sub_guarnicion.getIdguarnicion());
        _sen.execute();
        
        _cn.close();
    }

    public ResultSet getGuarniciones() throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL todas_guarniciones}");
        ResultSet rs = _sen.executeQuery();
        return rs;
    }

    public void editar_guarnicion(GuarnicionEntidad guarnicion) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL editar_guarnicion(?, ?, ?, ?)}");
        _sen.setInt(1, guarnicion.getId());
        _sen.setString(2, guarnicion.getReferencia());
        _sen.setString(3, guarnicion.getTitulo());
        _sen.setString(4, guarnicion.getTipo());
        _sen.execute();
        
        _cn.close();
    }

    public void editar_sub_guarnicion(SubguarnicionEntidad sub_guarnicion) throws Exception {
        Connection _cn = db.conexion();
        CallableStatement _sen = _cn.prepareCall("{CALL editar_subguarnicion(?, ?, ?, ?)}");
        _sen.setInt(1, sub_guarnicion.getId());
        _sen.setString(2, sub_guarnicion.getNombre());
        _sen.setDouble(3, sub_guarnicion.getPrecio());
        _sen.setInt(4, sub_guarnicion.getIdguarnicion());
        _sen.execute();
        
        _cn.close();
    }
}
