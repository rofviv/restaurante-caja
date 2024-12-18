package entidad;

public class ProductoEntidad {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precio, precioExtra, precioAumentado;
    private String imagen;
    private int activo;
    private int idCategoria;

    public ProductoEntidad() {
        idProducto = 0;
        nombre = "";
        descripcion = "";
        precio = 0.0;
        imagen = "noimagen";
        activo = 1;
        idCategoria = 0;
        precioExtra = 0.0;
        precioAumentado = 0.0;
    }

    public double getPrecioAumentado() {
        return precioAumentado;
    }

    public void setPrecioAumentado(double precioAumentado) {
        this.precioAumentado = precioAumentado;
    }

    public double getPrecioExtra() {
        return precioExtra;
    }

    public void setPrecioExtra(double precioExtra) {
        this.precioExtra = precioExtra;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
