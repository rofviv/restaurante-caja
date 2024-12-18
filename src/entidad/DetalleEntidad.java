package entidad;

import java.util.ArrayList;

public class DetalleEntidad {

    private int idDetalle;
    private int cantidad;
    private String nota, ingrediente;
    private int idProducto;
    private int idVenta;

    private String nombreProducto;
    private double precioOriginal;
    private double precioProducto;
    private boolean tieneExtra;
    
    private ArrayList<SubguarnicionEntidad> guarniciones;

    public DetalleEntidad() {
        idDetalle = 1;
        cantidad = 0;
        nota = "N/A";
        ingrediente = "";
        idProducto = 1;
        idVenta = 1;
        
        nombreProducto = "";
        precioProducto = 0.0;
        precioOriginal = 0.0;
        tieneExtra = false;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(double precioProducto) {
        this.precioProducto = precioProducto;
    }

    public boolean tieneExtra() {
        return tieneExtra;
    }

    public void setExtra(boolean extra) {
        this.tieneExtra = extra;
    }

    public double getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(double precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public ArrayList<SubguarnicionEntidad> getGuarniciones() {
        return guarniciones;
    }

    public void setGuarnicion(SubguarnicionEntidad guarniciones) {
        this.guarniciones.add(guarniciones);
    }
    
    public SubguarnicionEntidad getGuarnicion(int pos) {
        return guarniciones.get(pos);
    }
    
    public void guardarGuarniciones(ArrayList<SubguarnicionEntidad> guarniciones) {
        this.guarniciones = guarniciones;
    }
}
