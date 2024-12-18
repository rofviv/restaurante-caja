package presentacion;

import entidad.DetalleEntidad;
import entidad.DetalleTicket;
import entidad.SubguarnicionEntidad;
import entidad.VentaEntidad;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import negocio.DetalleNegocio;
import negocio.VentaNegocio;
import reportes.GenerarTicket;

public class CobrarPresentacion extends javax.swing.JDialog {

    public static boolean imprimio;
    String fecha;
    String hora;
    double total;
    ArrayList<DetalleEntidad> carrito;
    Collection<DetalleTicket> detalleTicket = new ArrayList<>();
    Collection<DetalleTicket> detalleCocina = new ArrayList<>();
    private static final int CONSUMO_INTERNO = 2;
    private static final int TARJETA = 1;

    public CobrarPresentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtCliente.requestFocus();
        txtRecibido.selectAll();
        imprimio = false;
        btnImprimir.setEnabled(false);
        lblTipoPedido.setText(TipoOrdenPresentacion.tipoPedido);
    }

    public void cargarInformacion(String fecha, String hora, double total) {
        this.fecha = fecha;
        this.hora = hora;
        this.total = total;
        txtCobrar.setText(this.total + "");
    }

    public void cargarCarrito(ArrayList<DetalleEntidad> listaDetalle, JTable detalle) {
        this.carrito = listaDetalle;
        for (int i = 0; i < detalle.getRowCount(); i++) {
            //detalleTicket.add(new DetalleTicket(detalle.getValueAt(i, 0).toString(), detalle.getValueAt(i, 1).toString().split("-")[0].trim(), detalle.getValueAt(i, 2).toString(), detalle.getValueAt(i, 3).toString(), null));
            detalleCocina.add(new DetalleTicket(detalle.getValueAt(i, 0).toString(), detalle.getValueAt(i, 1).toString(), null, null, carrito.get(i).getIngrediente()));
        }
        for (DetalleEntidad de : carrito) {
            detalleTicket.add(new DetalleTicket(de.getCantidad() + "", de.getNombreProducto(), null, de.getPrecioProducto() + "", null));
            if (de.tieneExtra()) {
                for (SubguarnicionEntidad sub : de.getGuarniciones()) {
                    if (sub.getPrecio() > 0 && sub.getTipo().equals("Aumentar")) {
                        detalleTicket.add(new DetalleTicket(" ", sub.getNombre(), null, "+" + (de.getCantidad() * sub.getPrecio()), null));
                    } else if (sub.getTipo().equals("Modificar")) {
                        detalleTicket.add(new DetalleTicket(" ", sub.getNombre(), null, "" + (de.getCantidad() * sub.getPrecio()), null));
                    }
                }
            }
        }
    }

    private void guardarVenta() {
        imprimio = true;

        VentaNegocio _veN = new VentaNegocio();
        VentaEntidad ventaE = new VentaEntidad();
        ventaE.setNroVenta(VentanaCajaPresentacion.NRO_ORDEN);
        ventaE.setCliente(txtCliente.getText());
        ventaE.setTipoPago(cbTipo.getSelectedItem().toString());
        ventaE.setFecha(fecha);
        ventaE.setTipo(TipoOrdenPresentacion.tipoPedido);
        ventaE.setHora(hora);
        ventaE.setTotal(total);
        ventaE.setObservacion(txtNota.getText());
        ventaE.setIdUsuario(VentanaCajaPresentacion.CAJERO.getIdUsuario());

        VentanaCajaPresentacion.NRO_ORDEN++;

        try {
            int id_v = _veN.nueva_venta(ventaE);
            guardarCarrito(id_v, ventaE);
        } catch (Exception e) {
            System.err.println("Error al ingresar una venta (PRESENTACION) " + e.getMessage());
        }
    }

    private void guardarCarrito(int id_v, VentaEntidad ventaE) {
        DetalleNegocio detN = new DetalleNegocio();

        for (DetalleEntidad detE : carrito) {
            if (detE.getIdProducto() != 0) {
                detE.setIdVenta(id_v);
                detN.insertar_detalle(detE);
            }
        }

        GenerarTicket g = new GenerarTicket();
        g.nuevoTicket(detalleTicket, ventaE, txtRecibido.getText(), txtCambio.getText());
        g.ticketCocina(detalleCocina, ventaE);
        this.dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel12 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblTipoPedido = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtCobrar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtRecibido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNota = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnImprimir = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "ORDEN", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N
        jPanel12.setOpaque(false);

        jTextField1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setText("1");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(240, 230, 226));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cobrar.png"))); // NOI18N
        jLabel1.setText("COBRAR");
        jLabel1.setPreferredSize(new java.awt.Dimension(80, 50));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo_rest_pe.png"))); // NOI18N

        lblTipoPedido.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblTipoPedido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTipoPedido.setText("TIPO");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblTipoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel18)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(lblTipoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel4.setLayout(new java.awt.BorderLayout());

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setOpaque(false);
        jPanel7.setLayout(new java.awt.GridLayout(0, 2));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/200.png"))); // NOI18N
        jPanel7.add(jLabel11);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/100.png"))); // NOI18N
        jPanel7.add(jLabel7);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/50.png"))); // NOI18N
        jPanel7.add(jLabel4);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/20.png"))); // NOI18N
        jPanel7.add(jLabel8);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/10.png"))); // NOI18N
        jPanel7.add(jLabel9);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/10 nuevo.png"))); // NOI18N
        jPanel7.add(jLabel6);

        jPanel4.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setOpaque(false);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/05.png"))); // NOI18N
        jPanel6.add(jLabel10);

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/02.png"))); // NOI18N
        jPanel6.add(jLabel12);

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/01.png"))); // NOI18N
        jPanel6.add(jLabel13);

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/050.png"))); // NOI18N
        jPanel6.add(jLabel14);

        jPanel4.add(jPanel6, java.awt.BorderLayout.SOUTH);

        jLabel16.setText("    ");
        jPanel4.add(jLabel16, java.awt.BorderLayout.EAST);

        jLabel17.setText("    ");
        jPanel4.add(jLabel17, java.awt.BorderLayout.WEST);

        jLabel26.setText("  ");
        jPanel4.add(jLabel26, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel10.setOpaque(false);
        jPanel10.setLayout(new java.awt.GridLayout(0, 2, 0, 5));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel28.setText("CLIENTE");
        jPanel10.add(jLabel28);

        txtCliente.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jPanel10.add(txtCliente);

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel15.setText("TIPO DE PAGO");
        jPanel10.add(jLabel15);

        cbTipo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EFECTIVO", "TARJETA", "CONS. INTERNO" }));
        cbTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItemStateChanged(evt);
            }
        });
        jPanel10.add(cbTipo);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel2.setText("TOTAL A COBRAR");
        jPanel10.add(jLabel2);

        txtCobrar.setEditable(false);
        txtCobrar.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtCobrar.setText("0.00");
        txtCobrar.setEnabled(false);
        jPanel10.add(txtCobrar);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel3.setText("RECIBIDO");
        jPanel10.add(jLabel3);

        txtRecibido.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtRecibido.setText("0.00");
        txtRecibido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtRecibidoMouseClicked(evt);
            }
        });
        txtRecibido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRecibidoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtRecibidoKeyReleased(evt);
            }
        });
        jPanel10.add(txtRecibido);

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel5.setText("CAMBIO");
        jPanel10.add(jLabel5);

        txtCambio.setEditable(false);
        txtCambio.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txtCambio.setText("0.00");
        txtCambio.setEnabled(false);
        jPanel10.add(txtCambio);

        jPanel3.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel9.setOpaque(false);
        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.Y_AXIS));
        jPanel9.add(jSeparator1);

        jLabel27.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("NOTA");
        jPanel9.add(jLabel27);

        txtNota.setColumns(20);
        txtNota.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtNota.setLineWrap(true);
        txtNota.setRows(3);
        txtNota.setTabSize(4);
        txtNota.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtNota);

        jPanel9.add(jScrollPane2);
        jPanel9.add(jSeparator2);

        jLabel20.setText("   ");
        jPanel9.add(jLabel20);

        jPanel3.add(jPanel9, java.awt.BorderLayout.SOUTH);

        jPanel5.add(jPanel3, java.awt.BorderLayout.CENTER);

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new java.awt.GridLayout(0, 1, 5, 5));

        btnImprimir.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/impresion.png"))); // NOI18N
        btnImprimir.setText("IMPRIMIR");
        btnImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimir.setPreferredSize(new java.awt.Dimension(117, 50));
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jPanel8.add(btnImprimir);

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/quitar.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton2);

        jPanel5.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jLabel19.setText("  ");
        jPanel5.add(jLabel19, java.awt.BorderLayout.LINE_START);

        jLabel22.setText("    ");
        jPanel5.add(jLabel22, java.awt.BorderLayout.LINE_END);

        jPanel2.add(jPanel5);

        jPanel13.add(jPanel2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtRecibidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecibidoKeyReleased
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                if (!txtRecibido.getText().equals("")) {
                    NumeroOrdenPresentacion nop = new NumeroOrdenPresentacion(null, true);
                    nop.setVisible(true);
                    guardarVenta();
                }
            } else {
                if (!txtRecibido.getText().equals("")) {
                    double recibido = Double.parseDouble(txtRecibido.getText());
                    double cambio = (recibido - total);
                    cambio = Double.parseDouble(String.format("%.2f", cambio).replace(",", "."));
                    txtCambio.setText(cambio + "");
                    btnImprimir.setEnabled(true);
                } else {
                    txtCambio.setText("0.00");
                }
            }
        } catch (NumberFormatException e) {
            txtRecibido.setText("0.0");
        }
    }//GEN-LAST:event_txtRecibidoKeyReleased

    private void txtRecibidoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRecibidoKeyPressed

    }//GEN-LAST:event_txtRecibidoKeyPressed

    private void txtRecibidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtRecibidoMouseClicked
        txtRecibido.selectAll();
    }//GEN-LAST:event_txtRecibidoMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        imprimio = false;
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        if (!txtCliente.getText().equals("")) {
            if (cbTipo.getSelectedIndex() == CONSUMO_INTERNO && txtNota.getText().trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor especifica el motivo del consumo.");
            } else {
                //NumeroOrdenPresentacion nop = new NumeroOrdenPresentacion(null, true);
                //nop.setVisible(true);
                btnImprimir.setEnabled(false);
                guardarVenta();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, escribe el nombre del cliente");
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void cbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItemStateChanged
        if (cbTipo.getSelectedIndex() == CONSUMO_INTERNO) {
            txtCobrar.setText("0.0");
            btnImprimir.setEnabled(true);
            txtRecibido.setEnabled(false);
            txtRecibido.setText("0.0");
        } else if (cbTipo.getSelectedIndex() == TARJETA) {
            txtCobrar.setText(total + "");
            txtRecibido.setText(total + "");
            btnImprimir.setEnabled(true);
            txtRecibido.setEnabled(false);
        } else {
            txtCobrar.setText(total + "");
            btnImprimir.setEnabled(false);
            txtRecibido.setEnabled(true);
            txtRecibido.setText("0.0");
        }
        txtCambio.setText("0.0");
    }//GEN-LAST:event_cbTipoItemStateChanged

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
            java.util.logging.Logger.getLogger(CobrarPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CobrarPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CobrarPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CobrarPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CobrarPresentacion dialog = new CobrarPresentacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnImprimir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbTipo;
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
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblTipoPedido;
    private javax.swing.JTextField txtCambio;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCobrar;
    private javax.swing.JTextArea txtNota;
    private javax.swing.JTextField txtRecibido;
    // End of variables declaration//GEN-END:variables
}
