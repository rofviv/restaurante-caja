package negocio;

import datos.GuarnicionDatos;
import entidad.GuarnicionEntidad;
import entidad.SubguarnicionEntidad;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GuarnicionNegocio {
    
    public ArrayList<GuarnicionEntidad> getGuarnicionProducto(int id_p) {
        GuarnicionDatos _guD = new GuarnicionDatos();
        ArrayList<GuarnicionEntidad> _lst = new ArrayList<>();
        try {
            ResultSet _rs = _guD.getGuarnicionProducto(id_p);
            while (_rs.next()) {
                GuarnicionEntidad _guE = new GuarnicionEntidad();
                _guE.setId(_rs.getInt("id"));
                _guE.setReferencia(_rs.getString("referencia"));
                _guE.setTitulo(_rs.getString("titulo"));
                _guE.setTipo(_rs.getString("tipo"));
                _lst.add(_guE);
            }
        } catch (Exception ex) {
            System.err.println("Problemas al cargar las Guarniciones (NEGOCIO) " + ex.getMessage());
        }
        return _lst;
    }
    
    public ArrayList<SubguarnicionEntidad> getSubGuarnicion(int id_g) {
        GuarnicionDatos _guD = new GuarnicionDatos();
        ArrayList<SubguarnicionEntidad> _lst = new ArrayList<>();
        try {
            ResultSet _rs = _guD.getSubGuarnicion(id_g);
            while (_rs.next()) {
                SubguarnicionEntidad _guE = new SubguarnicionEntidad();
                _guE.setId(_rs.getInt("id"));
                _guE.setNombre(_rs.getString("nombre"));
                _guE.setPrecio(_rs.getDouble("precio"));
                _guE.setIdguarnicion(id_g);
                _lst.add(_guE);
            }
        } catch (Exception ex) {
            System.err.println("Problemas al cargar las SUBGuarniciones (NEGOCIO) " + ex.getMessage());
        }
        return _lst;
    }

    public int nueva_guarnicion(GuarnicionEntidad guarnicion) {
        GuarnicionDatos _guD = new GuarnicionDatos();
        int id = 0;
        try {
            ResultSet rs = _guD.nueva_guarnicion(guarnicion);
            if (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            System.err.println("Problemas en ingresar la guarnicion (NEGOCIO) " + e.getMessage());
        }
        return id;
    }
    
    public boolean editar_guarnicion(GuarnicionEntidad guarnicion) {
        GuarnicionDatos _guD = new GuarnicionDatos();
        boolean sw = false;
        try {
            _guD.editar_guarnicion(guarnicion);
            sw = true;
        } catch (Exception e) {
            System.err.println("Problemas en EDITAR la guarnicion (NEGOCIO) " + e.getMessage());
        }
        return sw;
    }

    public boolean guardar_sub_guarnicion(SubguarnicionEntidad sub_guarnicion) {
        GuarnicionDatos _guD = new GuarnicionDatos();
        boolean _r = false;
        try {
            _guD.nueva_sub_guarnicion(sub_guarnicion);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al insertar SUB-GUARNICIONES (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }

    public ArrayList<GuarnicionEntidad> getGuarniciones() {
        GuarnicionDatos _guD = new GuarnicionDatos();
        ArrayList<GuarnicionEntidad> _lst = new ArrayList<>();
        try {
            ResultSet _rs = _guD.getGuarniciones();
            while (_rs.next()) {
                GuarnicionEntidad _guE = new GuarnicionEntidad();
                _guE.setId(_rs.getInt("id"));
                _guE.setReferencia(_rs.getString("referencia"));
                _guE.setTitulo(_rs.getString("titulo"));
                _guE.setTipo(_rs.getString("tipo"));
                _lst.add(_guE);
            }
        } catch (Exception ex) {
            System.err.println("Problemas al cargar las Todas las GUARNICIONES (NEGOCIO) " + ex.getMessage());
        }
        return _lst;
    }

    public boolean editar_sub_guarnicion(SubguarnicionEntidad sub_guarnicion) {
        GuarnicionDatos _guD = new GuarnicionDatos();
        boolean _r = false;
        try {
            _guD.editar_sub_guarnicion(sub_guarnicion);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al EDITAR SUB-GUARNICIONES (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }

}
