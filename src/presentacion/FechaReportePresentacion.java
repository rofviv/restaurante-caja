package presentacion;

import entidad.InfoTipoVenta;
import entidad.UsuarioEntidad;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import negocio.UsuarioNegocio;
import negocio.VentaNegocio;
import reportes.GenerarReportes;

public class FechaReportePresentacion extends javax.swing.JDialog {

    private boolean reporteDiario = false;
    ArrayList<UsuarioEntidad> arUsuario;

    public FechaReportePresentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarUsuarios();
        Date fecha_actual = new Date();
        txtFecha_1.setDate(fecha_actual);
        txtFecha_2.setDate(fecha_actual);
    }

    private void cargarUsuarios() {
        UsuarioNegocio _usN = new UsuarioNegocio();
        arUsuario = _usN.getUsuarioTotal();
        for (UsuarioEntidad u : arUsuario) {
            cbUsuarios.addItem(u.getUsuario());
        }
    }

    public void detalleVentas() {
        this.reporteDiario = true;
        lblTitulo.setText("Detalle de Ventas");
    }

    public void resumenVentas() {
        this.reporteDiario = false;
        lblTitulo.setText("Resumen de Ventas");
    }

    private void generarDetalleVentas() {
        String usuario = "%";
        if (cbUsuarios.getSelectedIndex() > 0) {
            usuario = arUsuario.get(cbUsuarios.getSelectedIndex() - 1).getUsuario();
        }
        GenerarReportes gdv = new GenerarReportes();
        VentaNegocio _veN = new VentaNegocio();
        Date date = txtFecha_1.getDate();
        Date date_2 = txtFecha_2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha_ini = sdf.format(date);
        String fecha_fin = sdf.format(date_2);
        ArrayList<InfoTipoVenta> datos = _veN.getResumenVenta(fecha_ini, fecha_fin, usuario);
        gdv.detalleVentas(datos, usuario, fecha_ini, fecha_fin);
    }

    private void generarResumenVentas() {
        String usuario = "%";
        double total_ventas = 0.0;
        int total_pedidos = 0;
        double promedio_ventas = 0.0;
        if (cbUsuarios.getSelectedIndex() > 0) {
            usuario = arUsuario.get(cbUsuarios.getSelectedIndex() - 1).getUsuario();
        }
        GenerarReportes gdv = new GenerarReportes();
        VentaNegocio _veN = new VentaNegocio();
        Date date = txtFecha_1.getDate();
        Date date_2 = txtFecha_2.getDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fecha_ini = sdf.format(date);
        String fecha_fin = sdf.format(date_2);
        ArrayList<InfoTipoVenta> datos = _veN.getResumenVenta(fecha_ini, fecha_fin, usuario);
        for (InfoTipoVenta inf : datos) {
            if (!inf.getTipo().equals("CONS. INTERNO")) {
                total_ventas += inf.getMonto();
                total_pedidos += inf.getCant();
                promedio_ventas += inf.getPromedio();
            }
        }
        gdv.resumenVentas(fecha_ini, fecha_fin, total_ventas + "", total_pedidos + "", promedio_ventas + "", usuario);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblDescripcion = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblFecha_1 = new javax.swing.JLabel();
        lblFecha_2 = new javax.swing.JLabel();
        txtFecha_1 = new com.toedter.calendar.JDateChooser();
        txtFecha_2 = new com.toedter.calendar.JDateChooser();
        cbUsuarios = new javax.swing.JComboBox<>();
        lblFecha_3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Reporte");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lblTitulo.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Reporte");

        lblDescripcion.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDescripcion.setText("Seleccione la fecha inicial y final para generar el Reporte");

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Generar Reporte");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lblFecha_1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblFecha_1.setText("Fecha Inicio");

        lblFecha_2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblFecha_2.setText("Fecha Fin");

        txtFecha_1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtFecha_2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        cbUsuarios.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos" }));

        lblFecha_3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblFecha_3.setText("Usuario");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFecha_1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha_1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecha_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblFecha_3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbUsuarios, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFecha_2, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha_1)
                    .addComponent(txtFecha_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha_2)
                    .addComponent(txtFecha_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFecha_3))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblDescripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (reporteDiario) {
            generarDetalleVentas();
        } else {
            generarResumenVentas();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(FechaReportePresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FechaReportePresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FechaReportePresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FechaReportePresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FechaReportePresentacion dialog = new FechaReportePresentacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cbUsuarios;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblDescripcion;
    private javax.swing.JLabel lblFecha_1;
    private javax.swing.JLabel lblFecha_2;
    private javax.swing.JLabel lblFecha_3;
    private javax.swing.JLabel lblTitulo;
    private com.toedter.calendar.JDateChooser txtFecha_1;
    private com.toedter.calendar.JDateChooser txtFecha_2;
    // End of variables declaration//GEN-END:variables
}
