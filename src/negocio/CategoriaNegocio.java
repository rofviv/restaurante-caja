package negocio;

import datos.CategoriaDatos;
import datos.ProductoDatos;
import entidad.CategoriaEntidad;
import entidad.ProductoEntidad;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriaNegocio {
    
    public ArrayList<CategoriaEntidad> getCategoria() {
        CategoriaDatos _catD = new CategoriaDatos();
        ArrayList<CategoriaEntidad> _lst = new ArrayList<>();
        try {
            ResultSet _rs = _catD.getCategoria();
            while (_rs.next()) {
                CategoriaEntidad _catE = new CategoriaEntidad();
                _catE.setIdCategoria(_rs.getInt("id"));
                _catE.setCategoria(_rs.getString("categoria"));
                _lst.add(_catE);
            }
        } catch (Exception ex) {
            System.err.println("Problemas al cargar las Categorias (NEGOCIO)");
        }
        return _lst;
    }
    public ArrayList<CategoriaEntidad> getCategoriaTotal() {
        CategoriaDatos _catD = new CategoriaDatos();
        ArrayList<CategoriaEntidad> _lst = new ArrayList<>();
        try {
            ResultSet _rs = _catD.getCategoriaTotal();
            while (_rs.next()) {
                CategoriaEntidad _catE = new CategoriaEntidad();
                _catE.setIdCategoria(_rs.getInt("id"));
                _catE.setCategoria(_rs.getString("categoria"));
                _catE.setActivo(_rs.getInt("activo"));
                _lst.add(_catE);
            }
        } catch (Exception ex) {
            System.err.println("Problemas al cargar las Todas las Categorias (NEGOCIO) " + ex.getMessage());
        }
        return _lst;
    }
    
    public boolean nuevaCategoria(CategoriaEntidad _catE) {
        CategoriaDatos _catD = new CategoriaDatos();
        boolean r = false;
        try {
            _catD.nuevaCategoria(_catE);
            r = true;
        } catch (Exception e) {
            
        }
        return r;
    }
    
    public boolean deshabilitarCategoria(int id) {
        CategoriaDatos _catD = new CategoriaDatos();
        boolean r = false;
        try {
            _catD.deshabilitarProducto(id);
            r = true;
        } catch (Exception e) {
            System.err.println("Problemas al habilitar Categoria (NEGOCIO) " + e.getMessage());
        }
        return r;
    }
    
    public boolean habilitarCategoria(int id) {
        CategoriaDatos _catD = new CategoriaDatos();
        boolean r = false;
        try {
            _catD.habilitarProducto(id);
            r = true;
        } catch (Exception e) {
            System.err.println("Problemas al deshabilitar Categoria (NEGOCIO) " + e.getMessage());
        }
        return r;
    }
    
    public boolean actualizarCategoria(CategoriaEntidad catE) {
        CategoriaDatos _catD = new CategoriaDatos();
        boolean _r = false;
        try {
            _catD.actualizarCategoria(catE);
            _r = true;
        } catch (Exception e) {
            System.err.println("Problemas al ACTUALIZAR Categoria (NEGOCIO) " + e.getMessage());
        }
        return _r;
    }
}
