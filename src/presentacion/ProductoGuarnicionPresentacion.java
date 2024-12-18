package presentacion;

import entidad.GuarnicionEntidad;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import negocio.GuarnicionNegocio;

public class ProductoGuarnicionPresentacion extends javax.swing.JDialog {

    DefaultListModel<String> lstModel = new DefaultListModel<>();
    ArrayList<GuarnicionEntidad> arGuarnicion;
    ArrayList<GuarnicionEntidad> arNuevaGuarnicion;
    ArrayList<GuarnicionEntidad> arGuarnicionAntigua;
    ArrayList<GuarnicionEntidad> arParaEliminarDB;
    public int ID_PRODUCTO = 0;
    public boolean EDITO = false;

    public ProductoGuarnicionPresentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        arNuevaGuarnicion = new ArrayList<>();
        arParaEliminarDB = new ArrayList<>();
        cargarCombo();
    }

    private void cargarCombo() {
        GuarnicionNegocio _guN = new GuarnicionNegocio();
        this.arGuarnicion = _guN.getGuarniciones();
        for (GuarnicionEntidad guarnicion : arGuarnicion) {
            cbGuarnicion.addItem(guarnicion.getReferencia());
        }
    }

    public void cargarDatosCache(ArrayList<GuarnicionEntidad> arNuGua, ArrayList<GuarnicionEntidad> arEliDB) {
        if (arNuGua != null && arEliDB != null) {
            arNuevaGuarnicion = arNuGua;
            arParaEliminarDB = arEliDB;
            cargarGuarnicionProducto();
            limpiarArray();
            filtrarGuarnicionAntigua();
            juntarGuarniciones();
            llenarLista();
        } else if (ID_PRODUCTO > 0) {
            cargarGuarnicionProducto();
            juntarGuarniciones();
            llenarLista();
        }
    }

    public void cargarGuarnicionProducto() {
        GuarnicionNegocio _guN = new GuarnicionNegocio();
        arGuarnicionAntigua = _guN.getGuarnicionProducto(ID_PRODUCTO);
    }

    public void llenarLista() {
        lstModel.removeAllElements();
        for (GuarnicionEntidad g : arNuevaGuarnicion) {
            lstModel.addElement(g.getReferencia());
        }
        lstGuarnicion.setModel(lstModel);
    }

    public void filtrarGuarnicionAntigua() {
        arGuarnicionAntigua = new ArrayList<>();
        if (ID_PRODUCTO > 0) {
            GuarnicionNegocio _guN = new GuarnicionNegocio();
            ArrayList<GuarnicionEntidad> arAux = _guN.getGuarnicionProducto(ID_PRODUCTO);
            for (int i = 0; i < arAux.size(); i++) {
                if (!buscarGuarnicionEliminar(arAux.get(i).getId())) {
                    arGuarnicionAntigua.add(arAux.get(i));
                }
            }
        }
    }

    public boolean buscarGuarnicionEliminar(int id) {
        boolean sw = false;
        for (GuarnicionEntidad _g : arParaEliminarDB) {
            if (_g.getId() == id) {
                sw = true;
                break;
            }
        }
        return sw;
    }

    public void juntarGuarniciones() {
        for (GuarnicionEntidad _gu : arGuarnicionAntigua) {
            arNuevaGuarnicion.add(_gu);
        }
    }

    public boolean eliminarGuarnicion(int id) {
        boolean sw = false;
        for (int i = 0; i < arGuarnicionAntigua.size(); i++) {
            if (arGuarnicionAntigua.get(i).getId() == id) {
                arGuarnicionAntigua.remove(i);
                sw = true;
                break;
            }
        }
        return sw;
    }

    public boolean guarnicionNueva(int id) {
        boolean sw = true;
        for (GuarnicionEntidad _g : arGuarnicionAntigua) {
            if (_g.getId() == id) {
                sw = false;
                break;
            }
        }
        return sw;
    }

    public boolean guarnicionRepetida(int id) {
        boolean sw = false;
        for (GuarnicionEntidad _g : arNuevaGuarnicion) {
            if (_g.getId() == id) {
                sw = true;
                break;
            }
        }
        return sw;
    }

    public boolean paraEliminar(int id) {
        boolean sw = false;
        for (GuarnicionEntidad _g : arParaEliminarDB) {
            if (_g.getId() == id) {
                sw = true;
                break;
            }
        }
        return sw;
    }

    public ArrayList<GuarnicionEntidad> nuevasGuarniciones() {
        return arNuevaGuarnicion;
    }

    public ArrayList<GuarnicionEntidad> borrarGuarniciones() {
        return arParaEliminarDB;
    }

    private void limpiarArray() {
        if (arNuevaGuarnicion.size() > 0) {
            ArrayList<GuarnicionEntidad> arAux = new ArrayList<>();
            for (GuarnicionEntidad _g : arNuevaGuarnicion) {
                if (guarnicionNueva(_g.getId())) {
                    arAux.add(_g);
                }
            }
            arNuevaGuarnicion = arAux;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        cbGuarnicion = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstGuarnicion = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Guarnicion");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jLabel1.setText("Gestionar Guarnicion");

        cbGuarnicion.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        cbGuarnicion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton1.setText("Adicionar");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(cbGuarnicion, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cbGuarnicion, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lstGuarnicion.setBorder(javax.swing.BorderFactory.createTitledBorder("Guarniciones Guardadas"));
        jScrollPane1.setViewportView(lstGuarnicion);

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton3.setText("Quitar Guarnicion");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton4.setText("Guardar");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton2.setText("Cancelar");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jLabel1))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!guarnicionRepetida(arGuarnicion.get(cbGuarnicion.getSelectedIndex()).getId())) {
            GuarnicionEntidad _guE = arGuarnicion.get(cbGuarnicion.getSelectedIndex());
            arNuevaGuarnicion.add(_guE);
            lstModel.addElement(_guE.getReferencia());
            lstGuarnicion.setModel(lstModel);
        } else {
            JOptionPane.showMessageDialog(this, "Esta Guarnicion ya esta incluida");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int f = lstGuarnicion.getSelectedIndex();
        if (f >= 0) {
            if (NuevoProductoPresentacion.EDITAR && eliminarGuarnicion(arNuevaGuarnicion.get(f).getId())) {
                arParaEliminarDB.add(arNuevaGuarnicion.get(f));
            }
            arNuevaGuarnicion.remove(f);
            lstModel.remove(f);
            lstGuarnicion.setModel(lstModel);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una guarnicion para quitar");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (NuevoProductoPresentacion.EDITAR) {
            if (arNuevaGuarnicion.size() > arGuarnicionAntigua.size() || arParaEliminarDB.size() > 0) {
                limpiarArray();
            }
        }
        EDITO = true;
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        EDITO = false;
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
            java.util.logging.Logger.getLogger(ProductoGuarnicionPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductoGuarnicionPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductoGuarnicionPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductoGuarnicionPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProductoGuarnicionPresentacion dialog = new ProductoGuarnicionPresentacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<String> cbGuarnicion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstGuarnicion;
    // End of variables declaration//GEN-END:variables
}
