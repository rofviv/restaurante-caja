package presentacion;

import datos.Core;
import entidad.CategoriaEntidad;
import entidad.ProductoEntidad;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import negocio.ProductoNegocio;

public class EditarProductoPresentacion extends javax.swing.JDialog {

    ArrayList<CategoriaEntidad> ar;
    ArrayList<ProductoEntidad> arrProd;

    public EditarProductoPresentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public void cargarInformacion(ArrayList<CategoriaEntidad> arCategoria) {
        this.ar = arCategoria;
        for (CategoriaEntidad categoriaEntidad : arCategoria) {
            cbCategoria.addItem(categoriaEntidad.getCategoria());
        }
    }

    public void cargarLista() {
        lstProducto.setFixedCellHeight(40);
        ProductoNegocio _prodN = new ProductoNegocio();
        arrProd = _prodN.getTodosProductos(ar.get(cbCategoria.getSelectedIndex()).getIdCategoria());
        DefaultListModel<String> ls = new DefaultListModel();
        for (ProductoEntidad pe : arrProd) {
            ls.addElement(pe.getNombre());
        }
        lstProducto.setModel(ls);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cbCategoria = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstProducto = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        checkActivo = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

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

        lstProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lstProductoMouseReleased(evt);
            }
        });
        lstProducto.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstProductoValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstProducto);

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton1.setText("Editar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        jButton2.setText("Volver");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Editar Producto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
                            .addComponent(checkActivo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkActivo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(6, 6, 6))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (lstProducto.getSelectedIndex() >= 0) {
            NuevoProductoPresentacion npp = new NuevoProductoPresentacion(null, true);
            npp.titulo = "Editar Producto";
            npp.cargarDatos(arrProd.get(lstProducto.getSelectedIndex()), ar, cbCategoria.getSelectedIndex());
            npp.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Debes seleccionar un producto para editar");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbCategoriaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoriaMouseReleased

    }//GEN-LAST:event_cbCategoriaMouseReleased

    private void cbCategoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbCategoriaMousePressed

    }//GEN-LAST:event_cbCategoriaMousePressed

    private void cbCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCategoriaItemStateChanged
        cargarLista();
    }//GEN-LAST:event_cbCategoriaItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void lstProductoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstProductoMouseReleased

    }//GEN-LAST:event_lstProductoMouseReleased

    private void checkActivoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkActivoStateChanged

    }//GEN-LAST:event_checkActivoStateChanged

    private void checkActivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkActivoItemStateChanged

    }//GEN-LAST:event_checkActivoItemStateChanged

    private void checkActivoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkActivoMouseClicked

    }//GEN-LAST:event_checkActivoMouseClicked

    private void checkActivoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkActivoMousePressed
        if (lstProducto.getSelectedIndex() >= 0) {
            ProductoNegocio _prodN = new ProductoNegocio();
            int f = lstProducto.getSelectedIndex();
            if (checkActivo.isSelected()) {
                int r = JOptionPane.showConfirmDialog(null, "Desea DESHABILITAR el producto ");
                if (r == 0) {
                    checkActivo.setSelected(false);
                    _prodN.deshabilitarProducto(arrProd.get(f).getIdProducto());
                } else {
                    checkActivo.setSelected(true);
                }
            } else {
                int r = JOptionPane.showConfirmDialog(null, "Desea HABILITAR el producto ");
                if (r == 0) {
                    checkActivo.setSelected(true);
                    _prodN.habilitarProducto(arrProd.get(f).getIdProducto());
                } else {
                    checkActivo.setSelected(false);
                }
            }
            cargarLista();
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un producto para realizar esta acción");
        }
    }//GEN-LAST:event_checkActivoMousePressed

    private void lstProductoValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstProductoValueChanged
        int f = lstProducto.getSelectedIndex();
        if (f >= 0) {
            if (arrProd.get(f).getActivo() == 1) {
                checkActivo.setSelected(true);
            } else {
                checkActivo.setSelected(false);
            }
        }
    }//GEN-LAST:event_lstProductoValueChanged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        cargarLista();
    }//GEN-LAST:event_formWindowActivated

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
            java.util.logging.Logger.getLogger(EditarProductoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditarProductoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditarProductoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditarProductoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditarProductoPresentacion dialog = new EditarProductoPresentacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JCheckBox checkActivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstProducto;
    // End of variables declaration//GEN-END:variables
}
