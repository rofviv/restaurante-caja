package entidad;

public class SubguarnicionEntidad {
    private int id, idguarnicion;
    private String nombre, tipo;
    private double precio;

    public SubguarnicionEntidad() {
        id = idguarnicion = 0;
        nombre = tipo = "";
        precio = 0;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdguarnicion() {
        return idguarnicion;
    }

    public void setIdguarnicion(int idguarnicion) {
        this.idguarnicion = idguarnicion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
}
