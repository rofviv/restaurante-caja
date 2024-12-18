package presentacion;

import entidad.GuarnicionEntidad;
import entidad.SubguarnicionEntidad;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import negocio.GuarnicionNegocio;

public class NuevaGuarnicionPresentacion extends javax.swing.JDialog {

    ArrayList<SubguarnicionEntidad> arSubGuarnicion;
    ArrayList<SubguarnicionEntidad> arSubGuarnicionEditar;
    DefaultListModel<String> lstModel = new DefaultListModel<>();
    public int idGuarnicionEditar = 0;
    public static boolean EDITAR = false;
    private int filaEditar = -1;

    public NuevaGuarnicionPresentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        arSubGuarnicion = new ArrayList<>();
    }

    private void guardarSubGuarnicion(int id_g) {
        GuarnicionNegocio _guN = new GuarnicionNegocio();
        for (SubguarnicionEntidad sub_guarnicion : arSubGuarnicion) {
            sub_guarnicion.setIdguarnicion(id_g);
            _guN.guardar_sub_guarnicion(sub_guarnicion);
        }
    }
    
    private void editarSubGuarnicion() {
        GuarnicionNegocio _guN = new GuarnicionNegocio();
        for (SubguarnicionEntidad sub_guarnicion : arSubGuarnicionEditar) {
            _guN.editar_sub_guarnicion(sub_guarnicion);
        }
    }

    public void cargarDatos(String ref, String tit, String tipo) {
        txtReferencia.setText(ref);
        txtTitulo.setText(tit);
        if (tipo.equals("Aumentar")) {
            cbTipo.setSelectedIndex(0);
        } else {
            cbTipo.setSelectedIndex(1);
        }
    }

    public void cargarListaGuarnicion(int id) {
        idGuarnicionEditar = id;
        GuarnicionNegocio _guN = new GuarnicionNegocio();
        arSubGuarnicionEditar = _guN.getSubGuarnicion(id);
        for (SubguarnicionEntidad s : arSubGuarnicionEditar) {
            lstModel.addElement(s.getNombre());
        }
        lstGuarniciones.setModel(lstModel);
    }

    public void actualizarLista() {
        lstModel.removeAllElements();
        for (SubguarnicionEntidad s : arSubGuarnicionEditar) {
            lstModel.addElement(s.getNombre() + " : " + s.getPrecio());
        }
        for (SubguarnicionEntidad s : arSubGuarnicion) {
            lstModel.addElement(s.getNombre() + " : " + s.getPrecio());
        }
        lstGuarniciones.setModel(lstModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtReferencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbTipo = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGuarniciones = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        btnNueva = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nueva Guarnicion");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Referencia");

        txtReferencia.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel3.setText("Titulo");

        txtTitulo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel4.setText("Tipo");

        cbTipo.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sumar al precio", "Modificar el precio" }));
        cbTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoItemStateChanged(evt);
            }
        });
        cbTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cbTipoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cbTipoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtReferencia))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTitulo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtReferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Nombre");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Precio");

        txtNombre.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        txtPrecio.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
        });

        jButton1.setText("Adicionar subguarnicion");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lstGuarniciones.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Subguarniciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 0, 14))); // NOI18N
        lstGuarniciones.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstGuarnicionesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstGuarniciones);

        jButton3.setText("Eliminar Ultimo");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnNueva.setText("Nueva Guarnicion");
        btnNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnNueva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(3, 3, 3)))
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNueva)
                .addContainerGap(28, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Nueva Guarnicion");

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Guardar");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(162, 162, 162)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cbTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoItemStateChanged

    }//GEN-LAST:event_cbTipoItemStateChanged

    private void cbTipoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbTipoMousePressed

    }//GEN-LAST:event_cbTipoMousePressed

    private void cbTipoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbTipoMouseReleased

    }//GEN-LAST:event_cbTipoMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (EDITAR) {
            if (filaEditar >= 0) {
                SubguarnicionEntidad g = arSubGuarnicionEditar.get(filaEditar);
                g.setNombre(txtNombre.getText());
                g.setPrecio(Double.parseDouble(txtPrecio.getText()));
                arSubGuarnicionEditar.remove(filaEditar);
                arSubGuarnicionEditar.add(filaEditar, g);
                actualizarLista();
                filaEditar = -1;
                txtNombre.setText("");
                txtPrecio.setText("");
            } else {
                if (!txtNombre.getText().trim().equals("") && !txtPrecio.getText().trim().equals("")) {
                    SubguarnicionEntidad sge = new SubguarnicionEntidad();
                    sge.setNombre(txtNombre.getText().trim());
                    sge.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                    arSubGuarnicion.add(sge);
                    actualizarLista();
                    txtNombre.setText("");
                    txtPrecio.setText("");
                }
            }
        } else {
            if (!txtNombre.getText().trim().equals("") && !txtPrecio.getText().trim().equals("")) {
                SubguarnicionEntidad sge = new SubguarnicionEntidad();
                sge.setNombre(txtNombre.getText().trim());
                sge.setPrecio(Double.parseDouble(txtPrecio.getText().trim()));
                arSubGuarnicion.add(sge);
                lstModel.addElement(sge.getNombre() + ": " + sge.getPrecio());

                lstGuarniciones.setModel(lstModel);

                txtNombre.setText("");
                txtPrecio.setText("");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int pos = arSubGuarnicion.size() - 1;
        if (pos >= 0) {
            arSubGuarnicion.remove(pos);
            lstModel.removeElementAt(pos);
            lstGuarniciones.setModel(lstModel);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        try {
            double n = Double.parseDouble(txtPrecio.getText().trim());
        } catch (NumberFormatException n) {
            txtPrecio.setText("");
        }
    }//GEN-LAST:event_txtPrecioKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (EDITAR) {
            if (!txtReferencia.getText().trim().equals("") && !txtTitulo.getText().trim().equals("")) {
                if (arSubGuarnicionEditar.size() > 0) {
                    GuarnicionNegocio _guN = new GuarnicionNegocio();
                    GuarnicionEntidad guarnicion = new GuarnicionEntidad();
                    guarnicion.setId(idGuarnicionEditar);
                    guarnicion.setReferencia(txtReferencia.getText());
                    guarnicion.setTitulo(txtTitulo.getText());
                    if (cbTipo.getSelectedIndex() == 0) {
                        guarnicion.setTipo("Aumentar");
                    } else {
                        guarnicion.setTipo("Modificar");
                    }
                    _guN.editar_guarnicion(guarnicion);
                    editarSubGuarnicion();
                    guardarSubGuarnicion(idGuarnicionEditar);
                    JOptionPane.showMessageDialog(this, "Actualizado correctamente");
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Debes adicionar alguna sub guarnicion");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debes llenar los campos");
            }
            EDITAR = false;
        } else {
            if (!txtReferencia.getText().trim().equals("") && !txtTitulo.getText().trim().equals("")) {
                if (arSubGuarnicion.size() > 0) {
                    GuarnicionNegocio _guN = new GuarnicionNegocio();
                    GuarnicionEntidad guarnicion = new GuarnicionEntidad();
                    guarnicion.setReferencia(txtReferencia.getText());
                    guarnicion.setTitulo(txtTitulo.getText());
                    if (cbTipo.getSelectedIndex() == 0) {
                        guarnicion.setTipo("Aumentar");
                    } else {
                        guarnicion.setTipo("Modificar");
                    }
                    try {
                        int id_g = _guN.nueva_guarnicion(guarnicion);
                        guardarSubGuarnicion(id_g);
                        JOptionPane.showMessageDialog(this, "Guardado correctamente");
                        this.dispose();
                    } catch (Exception e) {
                        System.err.println("Error al ingresar una venta (PRESENTACION) " + e.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Debes adicionar alguna sub guarnicion");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Debes llenar los campos");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void lstGuarnicionesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstGuarnicionesValueChanged
        if (EDITAR) {
            filaEditar = lstGuarniciones.getSelectedIndex();
            if (filaEditar >= 0) {
                txtNombre.setText(arSubGuarnicionEditar.get(filaEditar).getNombre());
                txtPrecio.setText(arSubGuarnicionEditar.get(filaEditar).getPrecio() + "");
            }
        }
    }//GEN-LAST:event_lstGuarnicionesValueChanged

    private void btnNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaActionPerformed
        filaEditar = -1;
        txtNombre.setText("");
        txtPrecio.setText("");
    }//GEN-LAST:event_btnNuevaActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        
    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (!EDITAR) {
            btnNueva.setVisible(false);
        }
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
            java.util.logging.Logger.getLogger(NuevaGuarnicionPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NuevaGuarnicionPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NuevaGuarnicionPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NuevaGuarnicionPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                NuevaGuarnicionPresentacion dialog = new NuevaGuarnicionPresentacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnNueva;
    private javax.swing.JComboBox<String> cbTipo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> lstGuarniciones;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtReferencia;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables

}
