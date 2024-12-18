package entidad;

public class InfoTipoVenta {
    private String tipo;
    private int cant;
    private double monto, promedio;

    public InfoTipoVenta() {
        this.tipo = "N/A";
        this.cant = 0;
        this.monto = this.promedio = 0.0;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = Double.parseDouble(String.format("%.2f", promedio).replace(",", "."));
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setMonto(double monto) {
        this.monto = Double.parseDouble(String.format("%.2f", monto).replace(",", "."));;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCant() {
        return cant;
    }

    public double getMonto() {
        return monto;
    }
    
    
}
