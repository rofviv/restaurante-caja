package entidad;

public class Recibo {
    String nroRecibo, concepto;
    double monto;

    public Recibo(String nroRecibo, String concepto, double monto) {
        this.nroRecibo = nroRecibo;
        this.concepto = concepto;
        this.monto = monto;
    }

    public String getNroRecibo() {
        return nroRecibo;
    }

    public String getConcepto() {
        return concepto;
    }

    public double getMonto() {
        return monto;
    }
    
    
}
