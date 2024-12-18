package entidad;

public class Red {
    private String red;
    private String clave;

    public Red(String red, String clave) {
        this.red = red;
        this.clave = clave;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRed() {
        return red;
    }

    public void setRed(String red) {
        this.red = red;
    }
     
}
