package entidad;

public class DetalleTicket {
    
    private String cant;
    private String detalle;
    private String punit;
    private String precio;
    private String ingrediente;

    public DetalleTicket(String cant, String detalle, String punit, String precio, String ingrediente) {
        this.cant = cant;
        this.detalle = detalle;
        this.punit = punit;
        this.precio = precio;
        this.ingrediente = ingrediente;
    }

    /**
     * @return the cant
     */
    public String getCant() {
        return cant;
    }
    
    /**
     * @return the detalle
     */
    public String getDetalle() {
        return detalle;
    }

    /**
     * @return the punit
     */
    public String getPunit() {
        return punit;
    }

    /**
     * @return the precio
     */
    public String getPrecio() {
        return precio;
    }

    /**
     * @return the ingrediente
     */
    public String getIngrediente() {
        return ingrediente;
    }
    
}
