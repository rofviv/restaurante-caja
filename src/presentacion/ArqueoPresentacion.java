package presentacion;

import entidad.ArqueoEntidad;
import entidad.DetalleArqueo;
import entidad.Fecha;
import entidad.Recibo;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import negocio.ArqueoNegocio;
import reportes.GenerarArqueo;

public class ArqueoPresentacion extends javax.swing.JDialog {

    Collection<DetalleArqueo> detalleArqueo = new ArrayList<>();
    ArrayList<Recibo> detalleRecibo = new ArrayList<>();
    ArrayList<Recibo> detalleFactura = new ArrayList<>();
    String detalle;

    public ArqueoPresentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        iniciar();
        cargarVentaTotal();
        calcular();
        this.detalle = "";
    }

    private void cargarVentaTotal() {
        ArqueoNegocio _arN = new ArqueoNegocio();
        double total = _arN.getVentaCajero(VentanaCajaPresentacion.CAJERO.getIdUsuario());
        txtBalance.setText(total + "");
    }

    private void iniciar() {
        lblNombreCajero.setText(VentanaCajaPresentacion.CAJERO.getUsuario());
        txtDolar100.requestFocus();
        txtDolar100.selectAll();
        txtDolar50.selectAll();
        txtDolar20.selectAll();
        txtDolar10.selectAll();
        txtBol200.selectAll();
        txtBol100.selectAll();
        txtBol50.selectAll();
        txtBol20.selectAll();
        txtBol10.selectAll();
        txtMon5.selectAll();
        txtMon2.selectAll();
        txtMon1.selectAll();
        txtMon05.selectAll();
        txtMon02.selectAll();
        txtMon01.selectAll();
    }

    private void calcular() {
        double sumaDolar = Double.parseDouble(txtSumaDolar100.getText()) + Double.parseDouble(txtSumaDolar50.getText()) + Double.parseDouble(txtSumaDolar20.getText()) + Double.parseDouble(txtSumaDolar10.getText());
        double sumaBolivianos = Double.parseDouble(txtSumaBol200.getText()) + Double.parseDouble(txtSumaBol100.getText()) + Double.parseDouble(txtSumaBol50.getText()) + Double.parseDouble(txtSumaBol20.getText()) + Double.parseDouble(txtSumaBol10.getText());
        double sumaMonedas = Double.parseDouble(txtSumaMon5.getText()) + Double.parseDouble(txtSumaMon2.getText()) + Double.parseDouble(txtSumaMon1.getText()) + Double.parseDouble(txtSumaMon05.getText()) + Double.parseDouble(txtSumaMon02.getText()) + Double.parseDouble(txtSumaMon01.getText());
        double sumaEfectivo = sumaDolar + sumaBolivianos + sumaMonedas;
        sumaEfectivo = Double.parseDouble(String.format("%.2f", sumaEfectivo).replace(",", "."));
        double sumaRecibo = Double.parseDouble(txtRecibo.getText());
        double sumaFactura = Double.parseDouble(txtTarjeta.getText());
        double diferencia = (sumaEfectivo + sumaRecibo + sumaFactura) - Double.parseDouble(txtBalance.getText());
        diferencia = Double.parseDouble(String.format("%.2f", diferencia).replace(",", "."));

        txtEfectivo.setText(sumaEfectivo + "");
        txtArqueo.setText((sumaEfectivo + sumaRecibo + sumaFactura) + "");
        txtDiferencia.setText(diferencia + "");
    }

    private void anotarDolar(JTextField texto, int monto, JTextField mostrar) {
        if (!texto.getText().equals("")) {
            int c = Integer.parseInt(texto.getText()) * monto;
            double r = c * Double.parseDouble(txtTipoCambio.getText());
            mostrar.setText(r + "");
        } else {
            mostrar.setText("0.0");
        }
        calcular();
    }

    private void anotarBolivianos(JTextField texto, int monto, JTextField mostrar) {
        if (!texto.getText().equals("")) {
            int c = Integer.parseInt(texto.getText()) * monto;
            mostrar.setText(c + "");
        } else {
            mostrar.setText("0.0");
        }
        calcular();
    }

    private void anotarMonedas(JTextField texto, double monto, JTextField mostrar) {
        if (!texto.getText().equals("")) {
            double c = Double.parseDouble(texto.getText()) * monto;
            c = Double.parseDouble(String.format("%.2f", c).replace(",", "."));
            mostrar.setText(c + "");
        } else {
            mostrar.setText("0.0");
        }
        calcular();
    }

    private void actualizarMoneda() {
        anotarDolar(txtDolar100, 100, txtSumaDolar100);
        anotarDolar(txtDolar50, 50, txtSumaDolar50);
        anotarDolar(txtDolar20, 20, txtSumaDolar20);
        anotarDolar(txtDolar10, 10, txtSumaDolar10);
    }

    public void limpiarCampos() {
        txtDolar100.setText("0");
        txtSumaDolar100.setText("0.0");
        txtDolar50.setText("0");
        txtSumaDolar50.setText("0.0");
        txtDolar20.setText("0");
        txtSumaDolar20.setText("0.0");
        txtDolar10.setText("0");
        txtSumaDolar10.setText("0.0");
        txtBol200.setText("0");
        txtSumaBol200.setText("0.0");
        txtBol100.setText("0");
        txtSumaBol100.setText("0.0");
        txtBol50.setText("0");
        txtSumaBol50.setText("0.0");
        txtBol20.setText("0");
        txtSumaBol20.setText("0.0");
        txtBol10.setText("0");
        txtSumaBol10.setText("0.0");
        txtMon5.setText("0");
        txtSumaMon5.setText("0.0");
        txtMon2.setText("0");
        txtSumaMon2.setText("0.0");
        txtMon1.setText("0");
        txtSumaMon1.setText("0.0");
        txtMon05.setText("0");
        txtSumaMon05.setText("0.0");
        txtMon02.setText("0");
        txtSumaMon02.setText("0.0");
        txtMon01.setText("0");
        txtSumaMon01.setText("0.0");
        txtArqueo.setText("0.00");
        txtDiferencia.setText("0.00");
        txtEfectivo.setText("0.0");
        txtRecibo.setText("0.0");
        txtTarjeta.setText("0.0");
    }

    private void guardarEfectivo() {
        detalleArqueo.add(new DetalleArqueo("----EFECTIVO----"));
        if (!txtDolar100.getText().trim().equals("") && !txtDolar100.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Billetes " + txtDolar100.getText().trim() + " de 100 Dólares"));
            this.detalle += "Billetes " + txtDolar100.getText().trim() + " de 100 Dólares ";
        }
        if (!txtDolar50.getText().trim().equals("") && !txtDolar50.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Billetes " + txtDolar50.getText().trim() + " de 50 Dólares"));
            this.detalle += "Billetes " + txtDolar50.getText().trim() + " de 50 Dólares ";
        }
        if (!txtDolar20.getText().trim().equals("") && !txtDolar20.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Billetes " + txtDolar20.getText().trim() + " de 20 Dólares"));
            this.detalle += "Billetes " + txtDolar20.getText().trim() + " de 20 Dólares ";
        }
        if (!txtDolar10.getText().trim().equals("") && !txtDolar10.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Billetes " + txtDolar10.getText().trim() + " de 10 Dólares"));
            this.detalle += "Billetes " + txtDolar10.getText().trim() + " de 10 Dólares ";
        }
        if (!txtBol200.getText().trim().equals("") && !txtBol200.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Billetes " + txtBol200.getText().trim() + " de 200 Bolivianos"));
            this.detalle += "Billetes " + txtBol200.getText().trim() + " de 200 Bolivianos ";
        }
        if (!txtBol100.getText().trim().equals("") && !txtBol100.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Billetes " + txtBol100.getText().trim() + " de 100 Bolivianos"));
            this.detalle += "Billetes " + txtBol100.getText().trim() + " de 100 Bolivianos ";
        }
        if (!txtBol50.getText().trim().equals("") && !txtBol50.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Billetes " + txtBol50.getText().trim() + " de 50 Bolivianos"));
            this.detalle += "Billetes " + txtBol50.getText().trim() + " de 50 Bolivianos ";
        }
        if (!txtBol20.getText().trim().equals("") && !txtBol20.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Billetes " + txtBol20.getText().trim() + " de 20 Bolivianos"));
            this.detalle += "Billetes " + txtBol20.getText().trim() + " de 20 Bolivianos ";
        }
        if (!txtBol10.getText().trim().equals("") && !txtBol10.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Billetes " + txtBol10.getText().trim() + " de 10 Bolivianos"));
            this.detalle += "Billetes " + txtBol10.getText().trim() + " de 10 Bolivianos ";
        }
        if (!txtMon5.getText().trim().equals("") && !txtMon5.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Monedas " + txtMon5.getText().trim() + " de 5 Bolivianos"));
            this.detalle += "Monedas " + txtMon5.getText().trim() + " de 5 Bolivianos ";
        }
        if (!txtMon2.getText().trim().equals("") && !txtMon2.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Monedas " + txtMon2.getText().trim() + " de 2 Bolivianos"));
            this.detalle += "Monedas " + txtMon2.getText().trim() + " de 2 Bolivianos ";
        }
        if (!txtMon1.getText().trim().equals("") && !txtMon1.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Monedas " + txtMon1.getText().trim() + " de 1 Bolivianos"));
            this.detalle += "Monedas " + txtMon1.getText().trim() + " de 1 Bolivianos ";
        }
        if (!txtMon05.getText().trim().equals("") && !txtMon05.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Monedas " + txtMon05.getText().trim() + " de 50 Centavos"));
            this.detalle += "Monedas " + txtMon05.getText().trim() + " de 50 Centavos ";
        }
        if (!txtMon02.getText().trim().equals("") && !txtMon02.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Monedas " + txtMon02.getText().trim() + " de 20 Centavos"));
            this.detalle += "Monedas " + txtMon02.getText().trim() + " de 20 Centavos ";
        }
        if (!txtMon01.getText().trim().equals("") && !txtMon01.getText().trim().equals("0")) {
            detalleArqueo.add(new DetalleArqueo("Monedas " + txtMon01.getText().trim() + " de 10 Centavos"));
            this.detalle += "Monedas " + txtMon01.getText().trim() + " de 10 Centavos ";
        }
        detalleArqueo.add(new DetalleArqueo("TOTAL EFECTIVO: " + txtEfectivo.getText() + " Bs.-"));
    }

    private void guardarRecibo() {
        if (detalleRecibo.size() > 0) {
            detalleArqueo.add(new DetalleArqueo("----RECIBO----"));
            for (Recibo r : detalleRecibo) {
                detalleArqueo.add(new DetalleArqueo("Recibo nro. " + r.getNroRecibo() + " de " + r.getMonto() + " bolivianos."));
                this.detalle += "Recibo nro. " + r.getNroRecibo() + " de " + r.getMonto() + " bolivianos.";
            }
            detalleArqueo.add(new DetalleArqueo("TOTAL RECIBO: " + txtRecibo.getText() + " Bs.-"));
        }
    }

    private void guardarFactura() {
        if (detalleFactura.size() > 0) {
            detalleArqueo.add(new DetalleArqueo("----TARJETA----"));
            for (Recibo r : detalleFactura) {
                detalleArqueo.add(new DetalleArqueo("Factura nro. " + r.getNroRecibo() + " de " + r.getMonto() + " bolivianos."));
                this.detalle += "Factura nro. " + r.getNroRecibo() + " de " + r.getMonto() + " bolivianos.";
            }
            detalleArqueo.add(new DetalleArqueo("TOTAL TARJETA: " + txtTarjeta.getText() + " Bs.-"));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDerecho = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        pnlSuperior = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblNombreCajero = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        txtEfectivo = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        txtRecibo = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        pnlCentro = new javax.swing.JPanel();
        pnlDolares = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txtSumaDolar100 = new javax.swing.JTextField();
        txtDolar100 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtDolar50 = new javax.swing.JTextField();
        txtSumaDolar50 = new javax.swing.JTextField();
        txtDolar20 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtSumaDolar20 = new javax.swing.JTextField();
        txtDolar10 = new javax.swing.JTextField();
        txtSumaDolar10 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTipoCambio = new javax.swing.JTextField();
        pnlBolivianos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtBol200 = new javax.swing.JTextField();
        txtSumaBol200 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtBol100 = new javax.swing.JTextField();
        txtSumaBol100 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtBol50 = new javax.swing.JTextField();
        txtSumaBol50 = new javax.swing.JTextField();
        txtBol20 = new javax.swing.JTextField();
        txtSumaBol20 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtSumaBol10 = new javax.swing.JTextField();
        txtBol10 = new javax.swing.JTextField();
        pnlMon_det = new javax.swing.JPanel();
        pnlMoneda = new javax.swing.JPanel();
        txtMon5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtSumaMon5 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMon2 = new javax.swing.JTextField();
        txtSumaMon2 = new javax.swing.JTextField();
        txtMon1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSumaMon1 = new javax.swing.JTextField();
        pnlMoned = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtMon05 = new javax.swing.JTextField();
        txtSumaMon05 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtMon02 = new javax.swing.JTextField();
        txtSumaMon02 = new javax.swing.JTextField();
        lbl01 = new javax.swing.JLabel();
        txtMon01 = new javax.swing.JTextField();
        txtSumaMon01 = new javax.swing.JTextField();
        pnlDetalle = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtArqueo = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtBalance = new javax.swing.JTextField();
        txtDiferencia = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        pnlInferior = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        jLabel8.setText("Detalle");

        jLabel13.setText("Recibos");

        jLabel14.setText("Factura");

        jLabel24.setText("Efectivo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(140, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlDerechoLayout = new javax.swing.GroupLayout(pnlDerecho);
        pnlDerecho.setLayout(pnlDerechoLayout);
        pnlDerechoLayout.setHorizontalGroup(
            pnlDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDerechoLayout.createSequentialGroup()
                .addGroup(pnlDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDerechoLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel8))
                    .addGroup(pnlDerechoLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(127, Short.MAX_VALUE))
        );
        pnlDerechoLayout.setVerticalGroup(
            pnlDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDerechoLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel8)
                .addGap(46, 46, 46)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cierre de Efectivo en Caja");
        setResizable(false);

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText(" - MOVIMIENTO DE CAJA");
        jLabel1.setOpaque(true);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Cajero");

        lblNombreCajero.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblNombreCajero.setText("Nombre del Cajero");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNombreCajero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNombreCajero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel26.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel26.setText("Efectivo");

        txtEfectivo.setEditable(false);
        txtEfectivo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtEfectivo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtEfectivo.setText("0.0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEfectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel27.setText("Recibo");

        txtRecibo.setEditable(false);
        txtRecibo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtRecibo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtRecibo.setText("0.0");

        jButton1.setText("Declarar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel28.setText("Tarjeta");

        txtTarjeta.setEditable(false);
        txtTarjeta.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTarjeta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTarjeta.setText("0.0");

        jButton2.setText("Declarar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlSuperiorLayout = new javax.swing.GroupLayout(pnlSuperior);
        pnlSuperior.setLayout(pnlSuperiorLayout);
        pnlSuperiorLayout.setHorizontalGroup(
            pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE)
            .addGroup(pnlSuperiorLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlSuperiorLayout.setVerticalGroup(
            pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuperiorLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        getContentPane().add(pnlSuperior, java.awt.BorderLayout.PAGE_START);

        pnlCentro.setLayout(new javax.swing.BoxLayout(pnlCentro, javax.swing.BoxLayout.X_AXIS));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/100sus.png"))); // NOI18N

        txtSumaDolar100.setEditable(false);
        txtSumaDolar100.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaDolar100.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaDolar100.setText("0.0");
        txtSumaDolar100.setFocusable(false);
        txtSumaDolar100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaDolar100ActionPerformed(evt);
            }
        });

        txtDolar100.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDolar100.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDolar100.setText("0");
        txtDolar100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDolar100KeyReleased(evt);
            }
        });

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/50sus.png"))); // NOI18N

        txtDolar50.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDolar50.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDolar50.setText("0");
        txtDolar50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDolar50KeyReleased(evt);
            }
        });

        txtSumaDolar50.setEditable(false);
        txtSumaDolar50.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaDolar50.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaDolar50.setText("0.0");
        txtSumaDolar50.setFocusable(false);
        txtSumaDolar50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaDolar50ActionPerformed(evt);
            }
        });

        txtDolar20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDolar20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDolar20.setText("0");
        txtDolar20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDolar20KeyReleased(evt);
            }
        });

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/20sus.png"))); // NOI18N

        txtSumaDolar20.setEditable(false);
        txtSumaDolar20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaDolar20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaDolar20.setText("0.0");
        txtSumaDolar20.setFocusable(false);
        txtSumaDolar20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaDolar20ActionPerformed(evt);
            }
        });

        txtDolar10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtDolar10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDolar10.setText("0");
        txtDolar10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDolar10KeyReleased(evt);
            }
        });

        txtSumaDolar10.setEditable(false);
        txtSumaDolar10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaDolar10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaDolar10.setText("0.0");
        txtSumaDolar10.setFocusable(false);
        txtSumaDolar10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaDolar10ActionPerformed(evt);
            }
        });

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/10sus.png"))); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel12.setText("Tipo de Cambio");

        txtTipoCambio.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtTipoCambio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTipoCambio.setText("6.95");
        txtTipoCambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTipoCambioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTipoCambioKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlDolaresLayout = new javax.swing.GroupLayout(pnlDolares);
        pnlDolares.setLayout(pnlDolaresLayout);
        pnlDolaresLayout.setHorizontalGroup(
            pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDolaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDolaresLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDolar100, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaDolar100, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDolaresLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDolar50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaDolar50, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDolaresLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDolar20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaDolar20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDolaresLayout.createSequentialGroup()
                        .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlDolaresLayout.createSequentialGroup()
                                .addComponent(txtDolar10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSumaDolar10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pnlDolaresLayout.setVerticalGroup(
            pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDolaresLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDolaresLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDolar100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaDolar100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDolaresLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDolar50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaDolar50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDolaresLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDolar20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaDolar20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlDolaresLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDolar10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaDolar10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(28, 28, 28)
                .addGroup(pnlDolaresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtTipoCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCentro.add(pnlDolares);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/200.png"))); // NOI18N

        txtBol200.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtBol200.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBol200.setText("0");
        txtBol200.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBol200KeyReleased(evt);
            }
        });

        txtSumaBol200.setEditable(false);
        txtSumaBol200.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaBol200.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaBol200.setText("0.0");
        txtSumaBol200.setFocusable(false);
        txtSumaBol200.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaBol200ActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/100.png"))); // NOI18N

        txtBol100.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtBol100.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBol100.setText("0");
        txtBol100.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBol100KeyReleased(evt);
            }
        });

        txtSumaBol100.setEditable(false);
        txtSumaBol100.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaBol100.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaBol100.setText("0.0");
        txtSumaBol100.setFocusable(false);
        txtSumaBol100.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaBol100ActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/50.png"))); // NOI18N

        txtBol50.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtBol50.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBol50.setText("0");
        txtBol50.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBol50KeyReleased(evt);
            }
        });

        txtSumaBol50.setEditable(false);
        txtSumaBol50.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaBol50.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaBol50.setText("0.0");
        txtSumaBol50.setFocusable(false);
        txtSumaBol50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaBol50ActionPerformed(evt);
            }
        });

        txtBol20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtBol20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBol20.setText("0");
        txtBol20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBol20KeyReleased(evt);
            }
        });

        txtSumaBol20.setEditable(false);
        txtSumaBol20.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaBol20.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaBol20.setText("0.0");
        txtSumaBol20.setFocusable(false);
        txtSumaBol20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaBol20ActionPerformed(evt);
            }
        });

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/20.png"))); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/10.png"))); // NOI18N

        txtSumaBol10.setEditable(false);
        txtSumaBol10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaBol10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaBol10.setText("0.0");
        txtSumaBol10.setFocusable(false);
        txtSumaBol10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaBol10ActionPerformed(evt);
            }
        });

        txtBol10.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtBol10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBol10.setText("0");
        txtBol10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBol10KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlBolivianosLayout = new javax.swing.GroupLayout(pnlBolivianos);
        pnlBolivianos.setLayout(pnlBolivianosLayout);
        pnlBolivianosLayout.setHorizontalGroup(
            pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBolivianosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBol100, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaBol100, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBol200, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaBol200, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBol50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaBol50, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBol20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaBol20, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBol10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaBol10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        pnlBolivianosLayout.setVerticalGroup(
            pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBolivianosLayout.createSequentialGroup()
                .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBol200, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaBol200, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBol100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaBol100, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBol50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaBol50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBol20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaBol20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBolivianosLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(pnlBolivianosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtBol10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaBol10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlCentro.add(pnlBolivianos);

        txtMon5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMon5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMon5.setText("0");
        txtMon5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMon5KeyReleased(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/05.png"))); // NOI18N

        txtSumaMon5.setEditable(false);
        txtSumaMon5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaMon5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaMon5.setText("0.0");
        txtSumaMon5.setFocusable(false);
        txtSumaMon5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaMon5ActionPerformed(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/02.png"))); // NOI18N

        txtMon2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMon2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMon2.setText("0");
        txtMon2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMon2KeyReleased(evt);
            }
        });

        txtSumaMon2.setEditable(false);
        txtSumaMon2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaMon2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaMon2.setText("0.0");
        txtSumaMon2.setFocusable(false);
        txtSumaMon2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaMon2ActionPerformed(evt);
            }
        });

        txtMon1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMon1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMon1.setText("0");
        txtMon1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMon1KeyReleased(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/01.png"))); // NOI18N

        txtSumaMon1.setEditable(false);
        txtSumaMon1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaMon1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaMon1.setText("0.0");
        txtSumaMon1.setFocusable(false);
        txtSumaMon1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaMon1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMonedaLayout = new javax.swing.GroupLayout(pnlMoneda);
        pnlMoneda.setLayout(pnlMonedaLayout);
        pnlMonedaLayout.setHorizontalGroup(
            pnlMonedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMonedaLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMon5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaMon5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMonedaLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMon2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaMon2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMonedaLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMon1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaMon1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMonedaLayout.setVerticalGroup(
            pnlMonedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonedaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMonedaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlMonedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMon5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaMon5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMonedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMonedaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlMonedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaMon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMonedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMonedaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlMonedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaMon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/050.png"))); // NOI18N

        txtMon05.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMon05.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMon05.setText("0");
        txtMon05.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMon05KeyReleased(evt);
            }
        });

        txtSumaMon05.setEditable(false);
        txtSumaMon05.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaMon05.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaMon05.setText("0.0");
        txtSumaMon05.setFocusable(false);
        txtSumaMon05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaMon05ActionPerformed(evt);
            }
        });

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/020.png"))); // NOI18N

        txtMon02.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMon02.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMon02.setText("0");
        txtMon02.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMon02KeyReleased(evt);
            }
        });

        txtSumaMon02.setEditable(false);
        txtSumaMon02.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaMon02.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaMon02.setText("0.0");
        txtSumaMon02.setFocusable(false);
        txtSumaMon02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaMon02ActionPerformed(evt);
            }
        });

        lbl01.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl01.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/010.png"))); // NOI18N

        txtMon01.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtMon01.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtMon01.setText("0");
        txtMon01.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtMon01KeyReleased(evt);
            }
        });

        txtSumaMon01.setEditable(false);
        txtSumaMon01.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtSumaMon01.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSumaMon01.setText("0.0");
        txtSumaMon01.setFocusable(false);
        txtSumaMon01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSumaMon01ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlMonedLayout = new javax.swing.GroupLayout(pnlMoned);
        pnlMoned.setLayout(pnlMonedLayout);
        pnlMonedLayout.setHorizontalGroup(
            pnlMonedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMonedLayout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMon05, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaMon05, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMonedLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMon02, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaMon02, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlMonedLayout.createSequentialGroup()
                        .addComponent(lbl01)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMon01, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSumaMon01, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlMonedLayout.setVerticalGroup(
            pnlMonedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMonedLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMonedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMonedLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlMonedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMon05, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaMon05, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMonedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMonedLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlMonedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMon02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaMon02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMonedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl01, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMonedLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlMonedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMon01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSumaMon01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel19.setText("Arqueo");

        txtArqueo.setEditable(false);
        txtArqueo.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        txtArqueo.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtArqueo.setText("0.00");
        txtArqueo.setFocusable(false);

        jLabel20.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel20.setText("Balance");

        txtBalance.setEditable(false);
        txtBalance.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        txtBalance.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtBalance.setText("0.00");
        txtBalance.setFocusable(false);

        txtDiferencia.setEditable(false);
        txtDiferencia.setFont(new java.awt.Font("Times New Roman", 0, 22)); // NOI18N
        txtDiferencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDiferencia.setText("0.00");
        txtDiferencia.setFocusable(false);

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 22)); // NOI18N
        jLabel21.setText("Diferencia");

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtArqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlDetalleLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtArqueo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBalance))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtDiferencia)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNota.setLineWrap(true);
        txtNota.setRows(5);
        txtNota.setWrapStyleWord(true);
        txtNota.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nota", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jScrollPane1.setViewportView(txtNota);

        javax.swing.GroupLayout pnlMon_detLayout = new javax.swing.GroupLayout(pnlMon_det);
        pnlMon_det.setLayout(pnlMon_detLayout);
        pnlMon_detLayout.setHorizontalGroup(
            pnlMon_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMon_detLayout.createSequentialGroup()
                .addComponent(pnlMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMoned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 26, Short.MAX_VALUE))
            .addGroup(pnlMon_detLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMon_detLayout.setVerticalGroup(
            pnlMon_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMon_detLayout.createSequentialGroup()
                .addGroup(pnlMon_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlMoned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMon_detLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pnlCentro.add(pnlMon_det);

        getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);

        btnImprimir.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        btnLimpiar.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnCerrar.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(509, Short.MAX_VALUE)
                .addComponent(btnImprimir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCerrar)
                .addGap(100, 100, 100))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImprimir)
                    .addComponent(btnLimpiar)
                    .addComponent(btnCerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlInferiorLayout = new javax.swing.GroupLayout(pnlInferior);
        pnlInferior.setLayout(pnlInferiorLayout);
        pnlInferiorLayout.setHorizontalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlInferiorLayout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlInferiorLayout.setVerticalGroup(
            pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(pnlInferior, java.awt.BorderLayout.PAGE_END);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtSumaBol200ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaBol200ActionPerformed

    }//GEN-LAST:event_txtSumaBol200ActionPerformed

    private void txtSumaBol100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaBol100ActionPerformed

    }//GEN-LAST:event_txtSumaBol100ActionPerformed

    private void txtSumaBol50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaBol50ActionPerformed

    }//GEN-LAST:event_txtSumaBol50ActionPerformed

    private void txtSumaBol20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaBol20ActionPerformed

    }//GEN-LAST:event_txtSumaBol20ActionPerformed

    private void txtSumaBol10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaBol10ActionPerformed

    }//GEN-LAST:event_txtSumaBol10ActionPerformed

    private void txtSumaMon5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaMon5ActionPerformed

    }//GEN-LAST:event_txtSumaMon5ActionPerformed

    private void txtSumaMon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaMon2ActionPerformed

    }//GEN-LAST:event_txtSumaMon2ActionPerformed

    private void txtSumaMon1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaMon1ActionPerformed

    }//GEN-LAST:event_txtSumaMon1ActionPerformed

    private void txtSumaDolar100ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaDolar100ActionPerformed

    }//GEN-LAST:event_txtSumaDolar100ActionPerformed

    private void txtSumaDolar50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaDolar50ActionPerformed

    }//GEN-LAST:event_txtSumaDolar50ActionPerformed

    private void txtSumaDolar20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaDolar20ActionPerformed

    }//GEN-LAST:event_txtSumaDolar20ActionPerformed

    private void txtSumaDolar10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaDolar10ActionPerformed

    }//GEN-LAST:event_txtSumaDolar10ActionPerformed

    private void txtSumaMon05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaMon05ActionPerformed

    }//GEN-LAST:event_txtSumaMon05ActionPerformed

    private void txtSumaMon02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaMon02ActionPerformed

    }//GEN-LAST:event_txtSumaMon02ActionPerformed

    private void txtSumaMon01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSumaMon01ActionPerformed

    }//GEN-LAST:event_txtSumaMon01ActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtDolar100KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDolar100KeyReleased
        try {
            anotarDolar(txtDolar100, 100, txtSumaDolar100);
        } catch (NumberFormatException e) {
            txtDolar100.setText("0");
            anotarDolar(txtDolar100, 100, txtSumaDolar100);
        }
    }//GEN-LAST:event_txtDolar100KeyReleased

    private void txtDolar50KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDolar50KeyReleased
        try {
            anotarDolar(txtDolar50, 50, txtSumaDolar50);
        } catch (NumberFormatException e) {
            txtDolar50.setText("0");
            anotarDolar(txtDolar50, 50, txtSumaDolar50);
        }
    }//GEN-LAST:event_txtDolar50KeyReleased

    private void txtDolar20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDolar20KeyReleased
        try {
            anotarDolar(txtDolar20, 20, txtSumaDolar20);
        } catch (NumberFormatException e) {
            txtDolar20.setText("0");
            anotarDolar(txtDolar20, 20, txtSumaDolar20);
        }
    }//GEN-LAST:event_txtDolar20KeyReleased

    private void txtDolar10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDolar10KeyReleased
        try {
            anotarDolar(txtDolar10, 10, txtSumaDolar10);
        } catch (NumberFormatException e) {
            txtDolar10.setText("0");
            anotarDolar(txtDolar10, 10, txtSumaDolar10);
        }
    }//GEN-LAST:event_txtDolar10KeyReleased

    private void txtBol200KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBol200KeyReleased
        try {
            anotarBolivianos(txtBol200, 200, txtSumaBol200);
        } catch (NumberFormatException e) {
            txtBol200.setText("0");
            anotarBolivianos(txtBol200, 200, txtSumaBol200);
        }
    }//GEN-LAST:event_txtBol200KeyReleased

    private void txtBol100KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBol100KeyReleased
        try {
            anotarBolivianos(txtBol100, 100, txtSumaBol100);
        } catch (NumberFormatException e) {
            txtBol100.setText("0");
            anotarBolivianos(txtBol100, 100, txtSumaBol100);
        }
    }//GEN-LAST:event_txtBol100KeyReleased

    private void txtBol50KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBol50KeyReleased
        try {
            anotarBolivianos(txtBol50, 50, txtSumaBol50);
        } catch (NumberFormatException e) {
            txtBol50.setText("0");
            anotarBolivianos(txtBol50, 50, txtSumaBol50);
        }
    }//GEN-LAST:event_txtBol50KeyReleased

    private void txtBol20KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBol20KeyReleased
        try {
            anotarBolivianos(txtBol20, 20, txtSumaBol20);
        } catch (NumberFormatException e) {
            txtBol20.setText("0");
            anotarBolivianos(txtBol20, 20, txtSumaBol20);
        }
    }//GEN-LAST:event_txtBol20KeyReleased

    private void txtBol10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBol10KeyReleased
        try {
            anotarBolivianos(txtBol10, 10, txtSumaBol10);
        } catch (NumberFormatException e) {
            txtBol10.setText("0");
            anotarBolivianos(txtBol10, 10, txtSumaBol10);
        }
    }//GEN-LAST:event_txtBol10KeyReleased

    private void txtMon5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMon5KeyReleased
        try {
            anotarBolivianos(txtMon5, 5, txtSumaMon5);
        } catch (NumberFormatException e) {
            txtMon5.setText("0");
            anotarBolivianos(txtMon5, 5, txtSumaMon5);
        }
    }//GEN-LAST:event_txtMon5KeyReleased

    private void txtMon2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMon2KeyReleased
        try {
            anotarBolivianos(txtMon2, 2, txtSumaMon2);
        } catch (NumberFormatException e) {
            txtMon2.setText("0");
            anotarBolivianos(txtMon2, 2, txtSumaMon2);
        }
    }//GEN-LAST:event_txtMon2KeyReleased

    private void txtMon1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMon1KeyReleased
        try {
            anotarBolivianos(txtMon1, 1, txtSumaMon1);
        } catch (NumberFormatException e) {
            txtMon1.setText("0");
            anotarBolivianos(txtMon1, 1, txtSumaMon1);
        }
    }//GEN-LAST:event_txtMon1KeyReleased

    private void txtMon05KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMon05KeyReleased
        try {
            anotarMonedas(txtMon05, 0.5, txtSumaMon05);
        } catch (NumberFormatException e) {
            txtMon05.setText("0");
            anotarMonedas(txtMon05, 0.5, txtSumaMon05);
        }
    }//GEN-LAST:event_txtMon05KeyReleased

    private void txtMon02KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMon02KeyReleased
        try {
            anotarMonedas(txtMon02, 0.2, txtSumaMon02);
        } catch (NumberFormatException e) {
            txtMon02.setText("0");
            anotarMonedas(txtMon02, 0.2, txtSumaMon02);
        }
    }//GEN-LAST:event_txtMon02KeyReleased

    private void txtMon01KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMon01KeyReleased
        try {
            anotarMonedas(txtMon01, 0.1, txtSumaMon01);
        } catch (NumberFormatException e) {
            txtMon01.setText("0");
            anotarMonedas(txtMon01, 0.1, txtSumaMon01);
        }
    }//GEN-LAST:event_txtMon01KeyReleased

    private void txtTipoCambioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoCambioKeyPressed

    }//GEN-LAST:event_txtTipoCambioKeyPressed

    private void txtTipoCambioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTipoCambioKeyReleased
        if (!txtTipoCambio.getText().equals("")) {
            actualizarMoneda();
        }
    }//GEN-LAST:event_txtTipoCambioKeyReleased

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        guardarEfectivo();
        guardarRecibo();
        guardarFactura();

        ArqueoNegocio arqN = new ArqueoNegocio();
        ArqueoEntidad arqE = new ArqueoEntidad();
        arqE.setFecha(Fecha.getFecha());
        arqE.setHora(VentanaCajaPresentacion.lblHora.getText());
        arqE.setArqueo(Double.parseDouble(txtArqueo.getText()));
        arqE.setBalance(Double.parseDouble(txtBalance.getText()));
        arqE.setDiferencia(Double.parseDouble(txtDiferencia.getText()));
        arqE.setDetalle(this.detalle);
        arqE.setNota(txtNota.getText());
        arqE.setId_cajero(VentanaCajaPresentacion.CAJERO.getIdUsuario());

        boolean guardo = arqN.cierreCaja(arqE);
        if (guardo) {
            GenerarArqueo g = new GenerarArqueo();
            g.nuevoArqueo(detalleArqueo, arqE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Ocurrio un problema al guardar, favor comuniquese con el administrador.");
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DeclararReciboPresentacion drp = new DeclararReciboPresentacion(null, true);
        drp.cargarLista(detalleRecibo);
        drp.setVisible(true);
        detalleRecibo = drp.getListaDeclarados();
        double s = Double.parseDouble(String.format("%.2f", drp.sumaRecibos()).replace(",", "."));
        txtRecibo.setText(s + "");
        calcular();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DeclararFacturaPresentacion dfp = new DeclararFacturaPresentacion(null, true);
        dfp.cargarLista(detalleFactura);
        dfp.setVisible(true);
        detalleFactura = dfp.getListaDeclarados();
        double s = Double.parseDouble(String.format("%.2f", dfp.sumaFactura()).replace(",", "."));
        txtTarjeta.setText(s + "");
        calcular();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArqueoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArqueoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArqueoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArqueoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ArqueoPresentacion dialog = new ArqueoPresentacion(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lbl01;
    private javax.swing.JLabel lblNombreCajero;
    private javax.swing.JPanel pnlBolivianos;
    private javax.swing.JPanel pnlCentro;
    private javax.swing.JPanel pnlDerecho;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlDolares;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JPanel pnlMon_det;
    private javax.swing.JPanel pnlMoned;
    private javax.swing.JPanel pnlMoneda;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JTextField txtArqueo;
    private javax.swing.JTextField txtBalance;
    private javax.swing.JTextField txtBol10;
    private javax.swing.JTextField txtBol100;
    private javax.swing.JTextField txtBol20;
    private javax.swing.JTextField txtBol200;
    private javax.swing.JTextField txtBol50;
    private javax.swing.JTextField txtDiferencia;
    private javax.swing.JTextField txtDolar10;
    private javax.swing.JTextField txtDolar100;
    private javax.swing.JTextField txtDolar20;
    private javax.swing.JTextField txtDolar50;
    private javax.swing.JTextField txtEfectivo;
    private javax.swing.JTextField txtMon01;
    private javax.swing.JTextField txtMon02;
    private javax.swing.JTextField txtMon05;
    private javax.swing.JTextField txtMon1;
    private javax.swing.JTextField txtMon2;
    private javax.swing.JTextField txtMon5;
    private javax.swing.JTextArea txtNota;
    private javax.swing.JTextField txtRecibo;
    private javax.swing.JTextField txtSumaBol10;
    private javax.swing.JTextField txtSumaBol100;
    private javax.swing.JTextField txtSumaBol20;
    private javax.swing.JTextField txtSumaBol200;
    private javax.swing.JTextField txtSumaBol50;
    private javax.swing.JTextField txtSumaDolar10;
    private javax.swing.JTextField txtSumaDolar100;
    private javax.swing.JTextField txtSumaDolar20;
    private javax.swing.JTextField txtSumaDolar50;
    private javax.swing.JTextField txtSumaMon01;
    private javax.swing.JTextField txtSumaMon02;
    private javax.swing.JTextField txtSumaMon05;
    private javax.swing.JTextField txtSumaMon1;
    private javax.swing.JTextField txtSumaMon2;
    private javax.swing.JTextField txtSumaMon5;
    private javax.swing.JTextField txtTarjeta;
    private javax.swing.JTextField txtTipoCambio;
    // End of variables declaration//GEN-END:variables
}
