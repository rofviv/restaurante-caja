package presentacion;

import entidad.GuarnicionEntidad;
import entidad.ProductoEntidad;
import entidad.SubguarnicionEntidad;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import negocio.GuarnicionNegocio;

public class AdicionarProductoPresentacion extends javax.swing.JDialog {

    ProductoEntidad prodE;
    public boolean adiciono;
    DefaultListModel<String> lstModel = new DefaultListModel<>();
    ArrayList<SubguarnicionEntidad> arGuarnicionAgregadas;

    public AdicionarProductoPresentacion(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        adiciono = false;
        arGuarnicionAgregadas = new ArrayList<>();
    }

    public void cargarInformacion(ProductoEntidad p) {
        prodE = p;
        lblNombre.setText(prodE.getNombre());
        lblPrecio.setText(prodE.getPrecio() + "");
        txtDescripcion.setText(prodE.getDescripcion());
        txtPrecioActual.setText(prodE.getPrecio() + "");
        prodE.setPrecioExtra(0.0);
        prodE.setPrecioAumentado(0.0);
        cargarGuarniciones();
    }

    public void cargarGuarniciones() {
        pnlGuarniciones.removeAll();
        GuarnicionNegocio _guN = new GuarnicionNegocio();
        ArrayList<GuarnicionEntidad> arGuarnicion = _guN.getGuarnicionProducto(prodE.getIdProducto());
        for (final GuarnicionEntidad guarnicion : arGuarnicion) {
            JButton jb = new JButton();
            jb.setText(guarnicion.getTitulo());
            jb.setFont(new java.awt.Font("Times New Roman", 0, 14));
            jb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jb.setPreferredSize(new Dimension(65, 65));
            jb.setToolTipText(guarnicion.getTitulo());
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    subguarnicion(guarnicion.getId(), guarnicion.getTitulo(), guarnicion.getTipo());
                }
            });
            pnlGuarniciones.add(jb);
        }
        ajustarCuadroGuarnicion();
    }

    public void ajustarCuadroGuarnicion() {
        if (pnlGuarniciones.getComponentCount() < 5 && pnlGuarniciones.getComponentCount() > 0) {
            for (int i = pnlGuarniciones.getComponentCount(); i < 5; i++) {
                JLabel lbl2 = new JLabel(" ");
                lbl2.setPreferredSize(new Dimension(65, 65));
                pnlGuarniciones.add(lbl2);
            }
        }
    }

    public boolean subguarnicion(int id_g, String titulo, String tipo) {
        GuarnicionNegocio _guN = new GuarnicionNegocio();
        String tipoGuarnicion = "";
        if (tipo.equals("Aumentar")) {
            tipoGuarnicion = " +";
        }
        ArrayList<SubguarnicionEntidad> arSubGuarnicion = _guN.getSubGuarnicion(id_g);
        boolean sw = false;
        JLabel jTitulo = new JLabel(titulo);
        JComboBox cbSubGuarnicion = new JComboBox();
        for (SubguarnicionEntidad sub : arSubGuarnicion) {
            cbSubGuarnicion.addItem(sub.getNombre() + " " + tipoGuarnicion + sub.getPrecio());
        }
        Object[] ob = {jTitulo, cbSubGuarnicion};
        int result = JOptionPane.showConfirmDialog(null, ob, "Guarnicion", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            actualizarPrecio(arSubGuarnicion.get(cbSubGuarnicion.getSelectedIndex()), tipo);
        }
        return sw;
    }

    private void actualizarPrecio(SubguarnicionEntidad subG, String tipo) {
        subG.setTipo(tipo);
        int cant = Integer.parseInt(txtCantidad.getText());
        if (tipo.equals("Aumentar")) {
            double precioActual = Double.parseDouble(txtPrecioActual.getText());
            double precioExtra = subG.getPrecio();
            txtPrecioActual.setText((precioActual + (cant * precioExtra)) + "");
            prodE.setPrecioExtra(prodE.getPrecioExtra() + precioExtra);
        } else {
            double precioAntiguo = prodE.getPrecio() + prodE.getPrecioAumentado();
            double nuevoPrecio = subG.getPrecio();
            if (nuevoPrecio > precioAntiguo) {
                prodE.setPrecioAumentado(nuevoPrecio - precioAntiguo);
            }
            txtPrecioActual.setText(((cant * prodE.getPrecio()) + (cant * prodE.getPrecioAumentado()) + (cant * prodE.getPrecioExtra())) + "");
        }
        actualizarLista(subG);
    }

    private void actualizarLista(SubguarnicionEntidad guarnicion) {
        arGuarnicionAgregadas.add(guarnicion);
        lstModel.addElement(guarnicion.getNombre() + " : " + guarnicion.getPrecio());
        lstGuarniciones.setModel(lstModel);
    }

    public ProductoEntidad getProducto() {
        return prodE;
    }

    public int getCantidad() {
        return Integer.parseInt(txtCantidad.getText());
    }

    public String getNota() {
        return txtNota.getText();
    }
    
    public String getNombreProd() {
        return lblNombre.getText();
    }
    
    public double getPrecioProd() {
        return Double.parseDouble(lblPrecio.getText());
    }

    public String getGuarnicion() {
        String m = "";
        for (SubguarnicionEntidad g : arGuarnicionAgregadas) {
            m += g.getNombre() + ", ";
        }
        if (!m.equals("")) {
            m = m.substring(0, m.length() - 2);
        }
        return m;
    }
    
    public ArrayList<SubguarnicionEntidad> getArrayGuarnicion() {
        return arGuarnicionAgregadas;
    }

    private void disminuirPrecio(SubguarnicionEntidad subG) {
        int cant = Integer.parseInt(txtCantidad.getText());
        if (subG.getTipo().equals("Aumentar")) {
            double precioActual = Double.parseDouble(txtPrecioActual.getText());
            double precioExtra = subG.getPrecio();
            txtPrecioActual.setText((precioActual - (cant * precioExtra)) + "");
            prodE.setPrecioExtra(prodE.getPrecioExtra() - precioExtra);
        } else {
            prodE.setPrecioAumentado(buscarPrecioMayor());
            txtPrecioActual.setText(((cant * prodE.getPrecio()) + (cant * prodE.getPrecioAumentado()) + (cant * prodE.getPrecioExtra())) + "");
        }
    }

    private double buscarPrecioMayor() {
        double precioMayor = 0.0;
        for (SubguarnicionEntidad g : arGuarnicionAgregadas) {
            if (g.getTipo().equals("Modificar")) {
                if (g.getPrecio() > precioMayor) {
                    precioMayor = g.getPrecio() - prodE.getPrecio();
                }
            }
        }
        return precioMayor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblPrecio = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        txtPrecio1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtCantidad = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtPrecioActual = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pnlGuarniciones = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstGuarniciones = new javax.swing.JList<>();
        jButton7 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Informacion");
        setResizable(false);

        lblNombre.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblNombre.setText("NOMBRE DEL PRODUCTO");

        lblPrecio.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblPrecio.setText("20");

        txtDescripcion.setEditable(false);
        txtDescripcion.setColumns(20);
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(3);
        txtDescripcion.setText("Descripcion larga del producto Descripcion larga del producto ");
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripcion"));
        jScrollPane1.setViewportView(txtDescripcion);

        txtPrecio1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtPrecio1.setText("Bs.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(lblNombre)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                .addComponent(txtPrecio1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtPrecio1)
                    .addComponent(lblPrecio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE))
        );

        txtCantidad.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setText("1");
        txtCantidad.setPreferredSize(new java.awt.Dimension(15, 30));
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton2.setText("-");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jButton1.setText("+");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtPrecioActual.setEditable(false);
        txtPrecioActual.setBackground(new java.awt.Color(255, 255, 204));
        txtPrecioActual.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txtPrecioActual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecioActual.setText("40.00");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel3.setText("CANTIDAD");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel4.setText("PRECIO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecioActual, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPrecioActual, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pnlGuarniciones.setLayout(new java.awt.GridLayout(0, 2));

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jButton5.setText("Borde");
        pnlGuarniciones.add(jButton5);

        jButton6.setText("Extra");
        pnlGuarniciones.add(jButton6);

        jScrollPane4.setViewportView(pnlGuarniciones);

        lstGuarniciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Guarniciones"));
        jScrollPane2.setViewportView(lstGuarniciones);

        jButton7.setText("Quitar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7))
        );

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel5.setText("Aclaraciones o Preferencias");

        txtNota.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jButton4.setText("AGREGAR");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setText("VOLVER");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(txtNota)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        int cant = Integer.parseInt(txtCantidad.getText()) + 1;
        txtCantidad.setText((cant) + "");
        txtPrecioActual.setText(((prodE.getPrecio() + prodE.getPrecioExtra() + prodE.getPrecioAumentado()) * cant) + "");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int cant = Integer.parseInt(txtCantidad.getText());
        if (cant > 1) {
            cant = Integer.parseInt(txtCantidad.getText()) - 1;
            txtCantidad.setText((cant) + "");
            txtPrecioActual.setText(((prodE.getPrecio() + prodE.getPrecioExtra() + prodE.getPrecioAumentado()) * cant) + "");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (txtCantidad.getText().equals("")) {
            txtCantidad.setText("1");
        }
        adiciono = true;
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        adiciono = false;
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        try {
            if (!txtCantidad.getText().equals("")) {
                int cant = Integer.parseInt(txtCantidad.getText());
                txtPrecioActual.setText((prodE.getPrecio() * cant) + "");
            } else {
                txtPrecioActual.setText(prodE.getPrecio() + "");
            }
        } catch (NumberFormatException e) {
            txtCantidad.setText("1");
            System.out.println("ERROR: " + e.getMessage());
        }
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int f = lstGuarniciones.getSelectedIndex();
        if (f >= 0) {
            SubguarnicionEntidad g = arGuarnicionAgregadas.get(f);
            arGuarnicionAgregadas.remove(f);
            disminuirPrecio(g);
            lstModel.remove(f);
            lstGuarniciones.setModel(lstModel);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(AdicionarProductoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdicionarProductoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdicionarProductoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdicionarProductoPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdicionarProductoPresentacion dialog = new AdicionarProductoPresentacion(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblPrecio;
    private javax.swing.JList<String> lstGuarniciones;
    private javax.swing.JPanel pnlGuarniciones;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtNota;
    private javax.swing.JLabel txtPrecio1;
    private javax.swing.JTextField txtPrecioActual;
    // End of variables declaration//GEN-END:variables
}
