package negocio;

import datos.Core;
import datos.ProductoDatos;
import entidad.ProductoEntidad;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductoNegocio {

    public ArrayList<ProductoEntidad> getProducto(int id_c) {
        ProductoDatos _proD = new ProductoDatos();
        ArrayList<ProductoEntidad> _lst = new ArrayList<ProductoEntidad>();
        try {
            ResultSet _rs = _proD.getProductosCategoria(id_c);
            while (_rs.next()) {
                ProductoEntidad _proE = new ProductoEntidad();
                _proE.setIdProducto(_rs.getInt("id"));
                _proE.setNombre(_rs.getString("nombre"));
                _proE.setDescripcion(_rs.getString("descripcion"));
                _proE.setPrecio(_rs.getDouble("precio"));
                _proE.setImagen(_rs.getString("imagen"));
                _lst.add(_proE);
            }
        } catch (Exception ex) {
            System.err.println("Problemas al cargar los productos (NEGOCIO) " + ex.getMessage());
        }
        return _lst;
    }

    public ArrayList<ProductoEntidad> getTodosProductos(int id_c) {
        ProductoDatos _proD = new ProductoDatos();
        ArrayList<ProductoEntidad> _lst = new ArrayList<ProductoEntidad>();
        try {
            ResultSet _rs = _proD.getTodosProductosCategoria(id_c);
            while (_rs.next()) {
                ProductoEntidad _proE = new ProductoEntidad();
                _proE.setIdProducto(_rs.getInt("id"));
                _proE.setNombre(_rs.getString("nombre"));
                _proE.setDescripcion(_rs.getString("descripcion"));
                _proE.setPrecio(_rs.getDouble("precio"));
                _proE.setImagen(_rs.getString("imagen"));
                _proE.setActivo(_rs.getInt("activo"));
                if (_proE.getIdProducto()!= Core.PRODUCTO_NULO) {
                    _lst.add(_proE);
                }
            }
        } catch (Exception ex) {
            System.err.println("Problemas al cargar los productos (NEGOCIO)");
        }
        return _lst;
    }

    public int nuevoProducto(ProductoEntidad prodE) {
        ProductoDatos _prodD = new ProductoDatos();
        int _id = 0;
        try {
            ResultSet rs = _prodD.nuevoProducto(prodE);
            if (rs.next()) {
                _id = rs.getInt("id");
            }
        } catch (Exception e) {
            System.err.println("Problemas al insertar productos (NEGOCIO) " + e.getMessage());
        }
        return _id;
    }

    public boolean actualizarProducto(ProductoEntidad prodE) {
        ProductoDatos _prodD = new ProductoDatos();
        boolean _r = false;
        try {
            _prodD.actualizarProducto(prodE);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al ACTUALIZAR productos (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }

    public boolean habilitarProducto(int id_p) {
        ProductoDatos _prodD = new ProductoDatos();
        boolean _r = false;
        try {
            _prodD.habilitarProducto(id_p);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al habilitar productos (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }

    public boolean deshabilitarProducto(int id_p) {
        ProductoDatos _prodD = new ProductoDatos();
        boolean _r = false;
        try {
            _prodD.deshabilitarProducto(id_p);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al deshabilitar productos (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }

    public boolean borrarGuarnicion(int id_p, int id_g) {
        ProductoDatos _prodD = new ProductoDatos();
        boolean _r = false;
        try {
            _prodD.borrarGuarnicion(id_p, id_g);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al borrar guarniciones del producto (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }

    public boolean adicionarGuarnicion(int id_p, int id_g) {
        ProductoDatos _prodD = new ProductoDatos();
        boolean _r = false;
        try {
            _prodD.adicionarGuarnicion(id_p, id_g);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al insertar guarniciones al producto (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }
}
