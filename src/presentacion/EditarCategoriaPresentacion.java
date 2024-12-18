package presentacion;

import entidad.CategoriaEntidad;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import negocio.CategoriaNegocio;

public class EditarCategoriaPresentacion extends javax.swing.JDialog {

    ArrayList<CategoriaEntidad> arCategoria;

    public EditarCategoriaPresentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargarComboBox();
    }

    public void cargarComboBox() {
        cbCategoria.removeAllItems();
        CategoriaNegocio _catN = new CategoriaNegocio();
        this.arCategoria = _catN.getCategoriaTotal();
        for (CategoriaEntidad categoriaEntidad : arCategoria) {
            cbCategoria.addItem(categoriaEntidad.getCategoria());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        checkActivo = new javax.swing.JCheckBox();
        btnGuardar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtNuevaCategoria = new javax.swing.JTextField();
        checkEditar = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Categoria");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Editar Categoria");

        cbCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCategoriaItemStateChanged(evt);
            }
        });
        cbCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbCategoriaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbCategoriaMouseReleased(evt);
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

        txtNuevaCategoria.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtNuevaCategoria.setEnabled(false);

        checkEditar.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        checkEditar.setText("Editar Categoria");
        checkEditar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkEditarItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkEditar)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkActivo, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                                .addComponent(txtNuevaCategoria)
                                .addComponent(jSeparator1)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkActivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNuevaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(6, 6, 6))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void checkActivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkActivoItemStateChanged

    }//GEN-LAST:event_checkActivoItemStateChanged

    private void checkActivoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkActivoStateChanged

    }//GEN-LAST:event_checkActivoStateChanged

    private void checkActivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkActivoMouseClicked

    }//GEN-LAST:event_checkActivoMouseClicked

    private void checkActivoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkActivoMousePressed
        int pos = cbCategoria.getSelectedIndex();
        CategoriaNegocio _catN = new CategoriaNegocio();
        if (checkActivo.isSelected()) {
            int r = JOptionPane.showConfirmDialog(null, "Desea DESHABILITAR la categoria?");
            if (r == 0) {
                checkActivo.setSelected(false);
                int id = arCategoria.get(pos).getIdCategoria();
                boolean edito = _catN.deshabilitarCategoria(id);
                if (edito) {
                    arCategoria.get(pos).setActivo(0);
                }
            } else {
                checkActivo.setSelected(true);
            }
        } else {
            int r = JOptionPane.showConfirmDialog(null, "Desea HABILITAR la Categoria?");
            if (r == 0) {
                checkActivo.setSelected(true);
                int id = arCategoria.get(pos).getIdCategoria();
                boolean edito = _catN.habilitarCategoria(id);
                if (edito) {
                    arCategoria.get(pos).setActivo(1);
                }
            } else {
                checkActivo.setSelected(false);
            }
        }
    }//GEN-LAST:event_checkActivoMousePressed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (checkEditar.isSelected()) {
            CategoriaEntidad _catE = new CategoriaEntidad();
            CategoriaNegocio _catN = new CategoriaNegocio();
            int pos = cbCategoria.getSelectedIndex();
            _catE.setIdCategoria(arCategoria.get(pos).getIdCategoria());
            _catE.setCategoria(txtNuevaCategoria.getText().trim());
            _catE.setActivo(arCategoria.get(pos).getActivo());
            
            boolean actualizo = _catN.actualizarCategoria(_catE);
            if (actualizo) {
                cargarComboBox();
                txtNuevaCategoria.setEnabled(false);
                btnGuardar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Ocurrio un Error");
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cbCategoriaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoriaMouseReleased

    }//GEN-LAST:event_cbCategoriaMouseReleased

    private void cbCategoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoriaMousePressed

    }//GEN-LAST:event_cbCategoriaMousePressed

    private void cbCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriaItemStateChanged
        int pos = cbCategoria.getSelectedIndex();
        if (pos >= 0) {
            txtNuevaCategoria.setText(arCategoria.get(pos).getCategoria());
            if (arCategoria.get(pos).getActivo() == 1) {
                checkActivo.setSelected(true);
            } else {
                checkActivo.setSelected(false);
            }
        }
    }//GEN-LAST:event_cbCategoriaItemStateChanged

    private void checkEditarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkEditarItemStateChanged
        if (checkEditar.isSelected()) {
            txtNuevaCategoria.setEnabled(true);
            btnGuardar.setEnabled(true);
        } else {
            txtNuevaCategoria.setEnabled(false);
            btnGuardar.setEnabled(false);
        }
    }//GEN-LAST:event_checkEditarItemStateChanged

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
            java.util.logging.Logger.getLogger(EditarCategoriaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarCategoriaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarCategoriaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarCategoriaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarCategoriaPresentacion dialog = new EditarCategoriaPresentacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JCheckBox checkActivo;
    private javax.swing.JCheckBox checkEditar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtNuevaCategoria;
    // End of variables declaration//GEN-END:variables
}
