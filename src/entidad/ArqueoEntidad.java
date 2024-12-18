package entidad;

public class ArqueoEntidad {
    
    private int id_arqueo;
    private String fecha, hora, nota, detalle;
    private double arqueo, balance, diferencia;
    private int id_cajero;

    public ArqueoEntidad() {
        
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getId_arqueo() {
        return id_arqueo;
    }

    public void setId_arqueo(int id_arqueo) {
        this.id_arqueo = id_arqueo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public double getArqueo() {
        return arqueo;
    }

    public void setArqueo(double arqueo) {
        this.arqueo = arqueo;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    public int getId_cajero() {
        return id_cajero;
    }

    public void setId_cajero(int id_cajero) {
        this.id_cajero = id_cajero;
    }
    
    
}
