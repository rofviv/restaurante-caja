package entidad;

public class CategoriaEntidad {
    
    private int idCategoria;
    private String categoria;
    private int activo;

    public CategoriaEntidad() {
        idCategoria = 1;
        categoria = "";
        activo = 1;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }
}
