package presentacion;

import entidad.CategoriaEntidad;
import entidad.UsuarioEntidad;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import negocio.CategoriaNegocio;
import negocio.UsuarioNegocio;

public class EditarUsuarioPresentacion extends javax.swing.JDialog {

    ArrayList<UsuarioEntidad> arUsuarios;

    public EditarUsuarioPresentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarComboBox();
    }

    public void cargarComboBox() {
        cbUsuarios.removeAllItems();
        UsuarioNegocio _usN = new UsuarioNegocio();
        this.arUsuarios = _usN.getUsuarioTotal();
        for (UsuarioEntidad usuarioEntidad : arUsuarios) {
            cbUsuarios.addItem(usuarioEntidad.getNombre() + " " + usuarioEntidad.getApellido());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbUsuarios = new javax.swing.JComboBox<>();
        checkActivo = new javax.swing.JCheckBox();
        checkEditar = new javax.swing.JCheckBox();
        txtNuevoUsuario = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtClave = new javax.swing.JPasswordField();
        txtClave2 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Editar Usuario");

        cbUsuarios.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbUsuariosItemStateChanged(evt);
            }
        });
        cbUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbUsuariosMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbUsuariosMouseReleased(evt);
            }
        });

        checkActivo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        checkActivo.setText("HABILITADO");
        checkActivo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        checkActivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkActivoItemStateChanged(evt);
            }
        });
        checkActivo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkActivoStateChanged(evt);
            }
        });
        checkActivo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkActivoMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                checkActivoMousePressed(evt);
            }
        });

        checkEditar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        checkEditar.setText("Editar Usuario");
        checkEditar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkEditarItemStateChanged(evt);
            }
        });

        txtNuevoUsuario.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNuevoUsuario.setEnabled(false);

        btnGuardar.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton2.setText("Volver");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Usuario");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("Contraseña");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("Repite Contraseña");

        txtClave.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtClave.setEnabled(false);

        txtClave2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtClave2.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(checkActivo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbUsuarios, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtNuevoUsuario)
                                            .addComponent(checkEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 92, Short.MAX_VALUE)
                                                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(90, 90, 90))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel4))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(txtClave)
                                            .addComponent(txtClave2))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(194, 194, 194))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(checkActivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkEditar)
                .addGap(7, 7, 7)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNuevoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtClave2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbUsuariosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbUsuariosItemStateChanged
        int pos = cbUsuarios.getSelectedIndex();
        if (pos >= 0) {
            txtNuevoUsuario.setText(arUsuarios.get(pos).getUsuario());
            if (arUsuarios.get(pos).getActivo() == 1) {
                checkActivo.setSelected(true);
            } else {
                checkActivo.setSelected(false);
            }
        }
    }//GEN-LAST:event_cbUsuariosItemStateChanged

    private void cbUsuariosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbUsuariosMousePressed

    }//GEN-LAST:event_cbUsuariosMousePressed

    private void cbUsuariosMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbUsuariosMouseReleased

    }//GEN-LAST:event_cbUsuariosMouseReleased

    private void checkActivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkActivoItemStateChanged

    }//GEN-LAST:event_checkActivoItemStateChanged

    private void checkActivoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkActivoStateChanged

    }//GEN-LAST:event_checkActivoStateChanged

    private void checkActivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkActivoMouseClicked

    }//GEN-LAST:event_checkActivoMouseClicked

    private void checkActivoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkActivoMousePressed
        int pos = cbUsuarios.getSelectedIndex();
        UsuarioNegocio _usN = new UsuarioNegocio();
        if (checkActivo.isSelected()) {
            int r = JOptionPane.showConfirmDialog(null, "Desea DESHABILITAR el Usuario?");
            if (r == 0) {
                checkActivo.setSelected(false);
                int id = arUsuarios.get(pos).getIdUsuario();
                boolean edito = _usN.deshabilitarUsuario(id);
                if (edito) {
                    arUsuarios.get(pos).setActivo(0);
                }
            } else {
                checkActivo.setSelected(true);
            }
        } else {
            int r = JOptionPane.showConfirmDialog(null, "Desea HABILITAR el Usuario?");
            if (r == 0) {
                checkActivo.setSelected(true);
                int id = arUsuarios.get(pos).getIdUsuario();
                boolean edito = _usN.habilitarUsuario(id);
                if (edito) {
                    arUsuarios.get(pos).setActivo(1);
                }
            } else {
                checkActivo.setSelected(false);
            }
        }
    }//GEN-LAST:event_checkActivoMousePressed

    private void checkEditarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkEditarItemStateChanged
        if (checkEditar.isSelected()) {
            txtNuevoUsuario.setEnabled(true);
            txtClave.setEnabled(true);
            txtClave2.setEnabled(true);
            btnGuardar.setEnabled(true);
        } else {
            txtNuevoUsuario.setEnabled(false);
            txtClave.setEnabled(false);
            txtClave2.setEnabled(false);
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_checkEditarItemStateChanged

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (txtClave.getText().trim().equals(txtClave2.getText().trim()) && !txtClave.getText().trim().equals("")) {
            if (checkEditar.isSelected()) {
                UsuarioEntidad _usE = new UsuarioEntidad();
                UsuarioNegocio _usN = new UsuarioNegocio();
                int pos = cbUsuarios.getSelectedIndex();
                _usE.setIdUsuario(arUsuarios.get(pos).getIdUsuario());
                _usE.setUsuario(txtNuevoUsuario.getText().trim());
                _usE.setClave(txtClave.getText().trim());
                _usE.setActivo(arUsuarios.get(pos).getActivo());

                boolean actualizo = _usN.actualizarUsuario(_usE);
                if (actualizo) {
                    cargarComboBox();
                    txtNuevoUsuario.setEnabled(false);
                    txtClave.setEnabled(false);
                    txtClave2.setEnabled(false);
                    btnGuardar.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "Actualizado Correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "Ocurrio un Error");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Las Contraseñas no Coinciden");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
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
            java.util.logging.Logger.getLogger(EditarUsuarioPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarUsuarioPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarUsuarioPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarUsuarioPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarUsuarioPresentacion dialog = new EditarUsuarioPresentacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbUsuarios;
    private javax.swing.JCheckBox checkActivo;
    private javax.swing.JCheckBox checkEditar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtClave;
    private javax.swing.JPasswordField txtClave2;
    private javax.swing.JTextField txtNuevoUsuario;
    // End of variables declaration//GEN-END:variables
}
