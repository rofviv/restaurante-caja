package negocio;

import datos.UsuarioDatos;
import entidad.UsuarioEntidad;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioNegocio {
    
    public UsuarioEntidad loguin(UsuarioEntidad _usr) {
        UsuarioEntidad _usrAux = null;
        UsuarioDatos _usD = new UsuarioDatos();
        try {
            ResultSet rs = _usD.loguin(_usr);
            if (rs.next()) {
                _usrAux = new UsuarioEntidad();
                _usrAux.setIdUsuario(rs.getInt("id"));
                _usrAux.setNombre(rs.getString("nombre"));
                _usrAux.setApellido(rs.getString("apellido"));
                _usrAux.setUsuario(rs.getString("usuario"));
            }
        } catch (Exception e) {
            System.err.println("Problemas en el Login (NEGOCIO) " + e.getMessage());
        }
        return _usrAux;
    }
    
    public boolean nuevo_usuario(UsuarioEntidad us) {
        UsuarioDatos _us = new UsuarioDatos();
        boolean _r = false;
        try {
            _us.nuevo_usuario(us);
            _r = true;
        } catch (Exception ex) {
            System.err.println("Problemas al registrar nuevo usuario (NEGOCIO) " + ex.getMessage());
        }
        return _r;
    }
    
    public static String clave_admin() {
        UsuarioDatos _usD = new UsuarioDatos();
        String clave = "admin123**";
        try {
            ResultSet rs = _usD.clave_admin();
            if (rs.next()) {
                clave = rs.getString("clave");
            }
        } catch (Exception ex) {
            System.err.println("Problemas al obtener la clave Admin (NEGOCIO) " + ex.getMessage());
        }
        return clave;
    }
    
    public boolean cambiarClaveAdmin(String clave) {
        UsuarioDatos _us = new UsuarioDatos();
        boolean _r = false;
        try {
            _us.cambiarClaveAdmin(clave);
            _r = true;
        } catch (Exception ex) {
            System.err.println("Problemas al cambiar la clave ADMIN (NEGOCIO) " + ex.getMessage());
        }
        return _r;
    }
    
    public ArrayList<UsuarioEntidad> getUsuarioTotal() {
        UsuarioDatos _usD = new UsuarioDatos();
        ArrayList<UsuarioEntidad> _lst = new ArrayList<>();
        try {
            ResultSet _rs = _usD.getUsuarioTotal();
            while (_rs.next()) {
                UsuarioEntidad _usE = new UsuarioEntidad();
                _usE.setIdUsuario(_rs.getInt("id"));
                _usE.setNombre(_rs.getString("nombre"));
                _usE.setApellido(_rs.getString("apellido"));
                _usE.setUsuario(_rs.getString("usuario"));
                _usE.setClave(_rs.getString("clave"));
                _usE.setActivo(_rs.getInt("activo"));
                _lst.add(_usE);
            }
        } catch (Exception ex) {
            System.err.println("Problemas al cargar las Todos los USUARIOS (NEGOCIO) " + ex.getMessage());
        }
        return _lst;
    }

    public boolean deshabilitarUsuario(int id) {
        UsuarioDatos _usD = new UsuarioDatos();
        boolean r = false;
        try {
            _usD.deshabilitarUsuario(id);
            r = true;
        } catch (Exception e) {
            System.err.println("Problemas al habilitar Usuario (NEGOCIO) " + e.getMessage());
        }
        return r;
    }

    public boolean habilitarUsuario(int id) {
        UsuarioDatos _usD = new UsuarioDatos();
        boolean r = false;
        try {
            _usD.habilitarUsuario(id);
            r = true;
        } catch (Exception e) {
            System.err.println("Problemas al deshabilitar Usuario (NEGOCIO) " + e.getMessage());
        }
        return r;
    }

    public boolean actualizarUsuario(UsuarioEntidad _usE) {
        UsuarioDatos _usD = new UsuarioDatos();
        boolean _r = false;
        try {
            _usD.actualizarUsuario(_usE);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al ACTUALIZAR usuario (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }
}
