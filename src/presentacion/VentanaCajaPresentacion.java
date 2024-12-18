package presentacion;

import entidad.CategoriaEntidad;
import entidad.DetalleEntidad;
import entidad.DetalleTicket;
import entidad.Fecha;
import entidad.Hora;
import entidad.ProductoEntidad;
import entidad.Red;
import entidad.SubguarnicionEntidad;
import entidad.UsuarioEntidad;
import entidad.VentaEntidad;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import negocio.CategoriaNegocio;
import negocio.DetalleNegocio;
import negocio.ProductoNegocio;
import negocio.UsuarioNegocio;
import negocio.VentaNegocio;
import reportes.GenerarTicket;

public class VentanaCajaPresentacion extends javax.swing.JFrame {

    public static UsuarioEntidad CAJERO;
    public static int NRO_ORDEN = 1;
    DefaultTableModel tblModel;
    ArrayList<CategoriaEntidad> ar;
    ArrayList<DetalleEntidad> listaDetalle;
    public static Red redWifi = new Red("", "");
    double total = 0.00;
    public static String destino = "C:\\restaurante\\platos\\";

    public VentanaCajaPresentacion() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/imagenes/icono.png")));
        iniciarApp();
    }

    public void setUsuario(UsuarioEntidad _usr) {
        CAJERO = _usr;
        lblNombreCajero.setText(CAJERO.getNombre());
    }

    private void iniciarApp() {
        tblModel = (DefaultTableModel) tblDetalle.getModel();
        tblDetalle.setModel(tblModel);
        cargarLista();
        cargarTabla();
        listaDetalle = new ArrayList<>();
        new Thread(new Hora(lblHora)).start();
        lblFecha.setText(Fecha.getFecha());
    }

    public void cargarTabla() {
        tblDetalle.setRowHeight(50);
    }

    public void cargarLista() {
        lstCategoria.setFixedCellHeight(40);
        CategoriaNegocio _catN = new CategoriaNegocio();
        ar = _catN.getCategoria();
        DefaultListModel<String> ls = new DefaultListModel();
        for (CategoriaEntidad ce : ar) {
            ls.addElement(ce.getCategoria().toUpperCase());
        }
        lstCategoria.setModel(ls);
        lstCategoria.setSelectedIndex(0);
    }

    private void mostrarProductos(ArrayList<ProductoEntidad> ap) {
        pnlCargarProductos.removeAll();
        final JFrame jf = this;
        for (final ProductoEntidad p : ap) {
            JButton jb = new JButton();
            if (!p.getImagen().equals("noimagen") && ajustarImagen(p.getImagen()) != null) {
                ImageIcon imagenPlato = ajustarImagen(p.getImagen());
                jb.setIcon(imagenPlato);
            } else {
                jb.setText(p.getNombre());
                if (p.getNombre().length() >= 24) {
                    jb.setFont(new java.awt.Font("Times New Roman", 0, 16));
                } else if (p.getNombre().length() >= 20) {
                    jb.setFont(new java.awt.Font("Times New Roman", 0, 18));
                } else {
                    jb.setFont(new java.awt.Font("Times New Roman", 0, 20));
                }
            }
            jb.setPreferredSize(new Dimension(200, 200));
            jb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            jb.setToolTipText(p.getNombre());
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    AdicionarProductoPresentacion np = new AdicionarProductoPresentacion(jf, true);
                    np.cargarInformacion(p);
                    np.setVisible(true);
                    if (np.adiciono) {
                        cargarDetalle(np);
                    }
                }
            });
            pnlCargarProductos.add(jb);
        }
        ajustarCuadroProductos();
        pnlCargarProductos.updateUI();
    }

    public void ajustarCuadroProductos() {
        if (pnlCargarProductos.getComponentCount() < 7) {
            for (int i = pnlCargarProductos.getComponentCount(); i < 7; i++) {
                JLabel lbl2 = new JLabel(" ");
                lbl2.setPreferredSize(new Dimension(200, 200));
                pnlCargarProductos.add(lbl2);
            }
        }
    }

    public void cargarDetalle(AdicionarProductoPresentacion v) {
        DetalleEntidad de = new DetalleEntidad();
        de.setCantidad(v.getCantidad());
        de.setIdProducto(v.getProducto().getIdProducto());
        de.setNombreProducto(v.getProducto().getNombre());
        de.setPrecioProducto(v.getCantidad() * v.getProducto().getPrecio());
        de.setPrecioOriginal(v.getProducto().getPrecio());
        if (v.getNota().trim().equals("")) {
            de.setNota(v.getGuarnicion());
        } else {
            de.setNota(v.getGuarnicion() + " - " + v.getNota());
        }

        if (v.getArrayGuarnicion().size() > 0) {
            de.guardarGuarniciones(v.getArrayGuarnicion());
            de.setExtra(true);
        }
        
        de.setIngrediente(v.getProducto().getDescripcion());
        listaDetalle.add(de);
        mostrarTablaDetalle(v.getProducto(), de);

        /*for (SubguarnicionEntidad sg : v.getArrayGuarnicion()) {
            if (sg.getPrecio() > 0) {
                DetalleEntidad deg = new DetalleEntidad();
                deg.setCantidad(v.getCantidad());
                deg.setIdProducto(0);
                deg.setNombreProducto(sg.getNombre());
                deg.setPrecioProducto(v.getCantidad() * sg.getPrecio());
                deg.setPrecioOriginal(sg.getPrecio());
                deg.setExtra(true);
                listaDetalle.add(deg);
            }
        }*/

    }

    public void mostrarTablaDetalle(ProductoEntidad prodE, DetalleEntidad detE) {
        String datos[] = new String[4];
        datos[0] = detE.getCantidad() + "";
        if (detE.getNota().equals("")) {
            datos[1] = prodE.getNombre();
        } else {
            datos[1] = prodE.getNombre() + " - " + detE.getNota();
        }
        datos[2] = (prodE.getPrecio() + prodE.getPrecioExtra() + prodE.getPrecioAumentado()) + "";
        datos[3] = ((prodE.getPrecio() + prodE.getPrecioExtra() + prodE.getPrecioAumentado()) * detE.getCantidad()) + "";
        tblModel.addRow(datos);
        tblDetalle.setModel(tblModel);
        total += ((prodE.getPrecio() + prodE.getPrecioExtra() + prodE.getPrecioAumentado()) * detE.getCantidad());
        lblTotalDetalle.setText("" + total);
    }

    public ImageIcon ajustarImagen(String ico) {
        File archivo = new File(destino + ico);
        if (!archivo.exists()) {
            return null;
        }
        ImageIcon tmpIconAux = new ImageIcon(destino + ico);
        ImageIcon tmpIcon = new ImageIcon(tmpIconAux.getImage().getScaledInstance(185, 170, Image.SCALE_DEFAULT));
        return tmpIcon;
    }

    public void vaciarCarrito() {
        int filas = tblDetalle.getRowCount();
        for (int i = filas - 1; i >= 0; i--) {
            tblModel.removeRow(i);
            listaDetalle.remove(i);
        }
        //listaDetalle.clear();
        total = 0.00;
        lblTotalDetalle.setText(total + "");
    }

    public boolean accesoAdmin() {
        boolean sw = false;
        JLabel jPassword = new JLabel("Contraseña del Administrador");
        JTextField txtClave = new JPasswordField();
        Object[] ob = {jPassword, txtClave};
        int result = JOptionPane.showConfirmDialog(null, ob, "Administrador", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String clave_admin = UsuarioNegocio.clave_admin();
            if (txtClave.getText().equals(clave_admin)) {
                sw = true;
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña Incorrecta, Acceso Denegado", "Acceso Denegado", JOptionPane.ERROR_MESSAGE);
            }
        }
        return sw;
    }

    private boolean cambiarClaveAdmin() {
        boolean sw = false;
        JLabel jPassword = new JLabel("Nueva Contraseña del Administrador");
        JTextField txtClave = new JPasswordField();
        JLabel jPassword2 = new JLabel("Repite Contraseña del Administrador");
        JTextField txtClave2 = new JPasswordField();
        Object[] ob = {jPassword, txtClave, jPassword2, txtClave2};
        int result = JOptionPane.showConfirmDialog(null, ob, "Editar Clave", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            UsuarioNegocio _usD = new UsuarioNegocio();
            if (txtClave.getText().equals(txtClave2.getText())) {
                if (_usD.cambiarClaveAdmin(txtClave.getText())) {
                    sw = true;
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo Guardar la nueva contraseña", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Las contraseñas no Coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        return sw;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        pnlPrincipal = new javax.swing.JPanel();
        pnlSuperior = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnlInfo = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pnlGridInfo = new javax.swing.JPanel();
        lblFecha = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblNombreCajero = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        pnlCentral = new javax.swing.JPanel();
        pnlCategoria = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstCategoria = new javax.swing.JList<>();
        pnlProductos = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        pnlCargarProductos = new javax.swing.JPanel();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblTotalDetalle = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnCobrar = new javax.swing.JButton();
        pnlInferior = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        pnlPrincipal.setLayout(new java.awt.BorderLayout());

        pnlSuperior.setBackground(new java.awt.Color(255, 255, 255));
        pnlSuperior.setLayout(new java.awt.BorderLayout());

        jPanel3.setOpaque(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/logo_rest_pe.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(464, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlSuperior.add(jPanel3, java.awt.BorderLayout.CENTER);

        pnlInfo.setBackground(new java.awt.Color(153, 153, 255));
        pnlInfo.setOpaque(false);
        pnlInfo.setLayout(new java.awt.BorderLayout());

        jLabel2.setText("    ");
        pnlInfo.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        jLabel4.setText("     ");
        pnlInfo.add(jLabel4, java.awt.BorderLayout.PAGE_END);

        pnlGridInfo.setOpaque(false);
        pnlGridInfo.setLayout(new java.awt.GridLayout(2, 2, 15, 10));

        lblFecha.setBackground(new java.awt.Color(255, 255, 255));
        lblFecha.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFecha.setText("2018-05-18");
        pnlGridInfo.add(lblFecha);

        lblHora.setBackground(new java.awt.Color(255, 255, 255));
        lblHora.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        lblHora.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHora.setText("19:30:05");
        pnlGridInfo.add(lblHora);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel7.setText("CAJERO");
        pnlGridInfo.add(jLabel7);

        lblNombreCajero.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        lblNombreCajero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNombreCajero.setText("xxx-xxxx-xxx");
        pnlGridInfo.add(lblNombreCajero);

        pnlInfo.add(pnlGridInfo, java.awt.BorderLayout.CENTER);

        jLabel3.setText("            ");
        pnlInfo.add(jLabel3, java.awt.BorderLayout.LINE_START);

        jLabel10.setText("            ");
        pnlInfo.add(jLabel10, java.awt.BorderLayout.LINE_END);

        pnlSuperior.add(pnlInfo, java.awt.BorderLayout.LINE_END);

        pnlPrincipal.add(pnlSuperior, java.awt.BorderLayout.PAGE_START);

        pnlCentral.setLayout(new java.awt.BorderLayout());

        pnlCategoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlCategoria.setPreferredSize(new java.awt.Dimension(180, 500));
        pnlCategoria.setLayout(new java.awt.BorderLayout());

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("<html>Categorias<br />Productos</html>");
        jLabel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlCategoria.add(jLabel13, java.awt.BorderLayout.PAGE_START);

        lstCategoria.setBackground(new java.awt.Color(225, 236, 248));
        lstCategoria.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        lstCategoria.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstCategoria.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lstCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstCategoriaMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lstCategoriaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                lstCategoriaMouseReleased(evt);
            }
        });
        lstCategoria.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstCategoriaValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lstCategoria);

        pnlCategoria.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pnlCentral.add(pnlCategoria, java.awt.BorderLayout.LINE_START);

        pnlProductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pnlCargarProductos.setLayout(new java.awt.GridLayout(0, 3, 5, 5));
        jScrollPane4.setViewportView(pnlCargarProductos);

        javax.swing.GroupLayout pnlProductosLayout = new javax.swing.GroupLayout(pnlProductos);
        pnlProductos.setLayout(pnlProductosLayout);
        pnlProductosLayout.setHorizontalGroup(
            pnlProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlProductosLayout.setVerticalGroup(
            pnlProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlCentral.add(pnlProductos, java.awt.BorderLayout.CENTER);

        pnlDetalle.setLayout(new javax.swing.BoxLayout(pnlDetalle, javax.swing.BoxLayout.Y_AXIS));

        tblDetalle.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblDetalle.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CANT.", "DESCRIPCION", "P. UNIT.", "PRECIO"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetalle.getTableHeader().setReorderingAllowed(false);
        tblDetalle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblDetalleMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(tblDetalle);
        if (tblDetalle.getColumnModel().getColumnCount() > 0) {
            tblDetalle.getColumnModel().getColumn(0).setResizable(false);
            tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblDetalle.getColumnModel().getColumn(1).setResizable(false);
            tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(270);
            tblDetalle.getColumnModel().getColumn(2).setResizable(false);
            tblDetalle.getColumnModel().getColumn(2).setPreferredWidth(70);
            tblDetalle.getColumnModel().getColumn(3).setResizable(false);
            tblDetalle.getColumnModel().getColumn(3).setPreferredWidth(70);
        }

        pnlDetalle.add(jScrollPane3);

        jPanel1.setLayout(new java.awt.GridLayout(0, 4));

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mas.png"))); // NOI18N
        jButton1.setText("Cant.");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menos.png"))); // NOI18N
        jButton2.setText("Cant.");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/quitar.png"))); // NOI18N
        jButton3.setText("Quitar");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3);

        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/vaciar.png"))); // NOI18N
        jButton6.setText("Vaciar");
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6);

        pnlDetalle.add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel5.setLayout(new java.awt.BorderLayout());

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("TOTAL:   ");
        jPanel5.add(jLabel11, java.awt.BorderLayout.CENTER);

        lblTotalDetalle.setBackground(new java.awt.Color(255, 255, 204));
        lblTotalDetalle.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        lblTotalDetalle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotalDetalle.setText("0.00");
        lblTotalDetalle.setOpaque(true);
        lblTotalDetalle.setPreferredSize(new java.awt.Dimension(160, 35));
        jPanel5.add(lblTotalDetalle, java.awt.BorderLayout.EAST);

        jLabel12.setText("         ");
        jPanel5.add(jLabel12, java.awt.BorderLayout.PAGE_END);

        jLabel14.setText("     ");
        jPanel5.add(jLabel14, java.awt.BorderLayout.PAGE_START);

        jPanel2.add(jPanel5, java.awt.BorderLayout.PAGE_START);

        btnCobrar.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCobrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cobrar.png"))); // NOI18N
        btnCobrar.setText("COBRAR");
        btnCobrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCobrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCobrarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCobrar, java.awt.BorderLayout.CENTER);

        pnlDetalle.add(jPanel2);

        pnlCentral.add(pnlDetalle, java.awt.BorderLayout.LINE_END);

        pnlPrincipal.add(pnlCentral, java.awt.BorderLayout.CENTER);

        pnlInferior.setBackground(new java.awt.Color(255, 255, 204));

        jLabel9.setText("Sistema de restaurante");
        pnlInferior.add(jLabel9);

        pnlPrincipal.add(pnlInferior, java.awt.BorderLayout.PAGE_END);

        jScrollPane1.setViewportView(pnlPrincipal);

        jMenuBar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jMenuBar1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(344, 30));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icono_ini.png"))); // NOI18N
        jMenu1.setText("Sistema");

        jMenuItem1.setText("Cerrar Sesion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/usuarios.png"))); // NOI18N
        jMenu2.setText("Cajero");

        jMenuItem2.setText("Nuevo Cajero");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem13.setText("Editar Cajero");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem13);
        jMenu2.add(jSeparator2);

        jMenuItem3.setText("Modifcar Clave Admin");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/productos.png"))); // NOI18N
        jMenu3.setText("Productos");

        jMenuItem4.setText("Nuevo Producto");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Editar Producto");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);
        jMenu3.add(jSeparator1);

        jMenuItem7.setText("Nueva Categoria");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem7);

        jMenuItem8.setText("Editar Categoria");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem8);
        jMenu3.add(jSeparator3);

        jMenuItem14.setText("Nueva Guarnicion");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem14);

        jMenuItem15.setText("Editar Guarnicion");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem15);

        jMenuBar1.add(jMenu3);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/print.png"))); // NOI18N
        jMenu5.setText("Venta");

        jMenuItem10.setText("Imprimir ultima venta");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem10);

        jMenuItem16.setText("Anular Venta");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem16);

        jMenuBar1.add(jMenu5);

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/billetera.png"))); // NOI18N
        jMenu4.setText("Cierre");

        jMenuItem9.setText("Cierre de caja");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuBar1.add(jMenu4);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/wifi.png"))); // NOI18N
        jMenu6.setText("Wi Fi");

        jMenuItem11.setText("Modificar");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem11);

        jMenuBar1.add(jMenu6);

        jMenu7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/repotes.png"))); // NOI18N
        jMenu7.setText("Reportes");

        jMenuItem6.setText("Resumen de ventas");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem6);

        jMenuItem12.setText("Detalle de Ventas");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem12);

        jMenuBar1.add(jMenu7);

        jMenu8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/info.png"))); // NOI18N
        jMenu8.setText("Acerca De...");

        jMenuItem17.setText("Del Sistema");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem17);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1250, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 738, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        new LoguinPresentacion().setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnCobrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCobrarActionPerformed
        if (tblDetalle.getRowCount() > 0) {
            new TipoOrdenPresentacion(this, true).setVisible(true);
            if (!TipoOrdenPresentacion.tipoPedido.equals("")) {
                CobrarPresentacion cp = new CobrarPresentacion(this, true);
                cp.cargarInformacion(lblFecha.getText(), lblHora.getText(), total);
                cp.cargarCarrito(listaDetalle, tblDetalle);
                cp.setVisible(true);
                if (cp.imprimio) {
                    vaciarCarrito();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "No tienes nada para cobrar");
        }
    }//GEN-LAST:event_btnCobrarActionPerformed

    private void lstCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCategoriaMouseClicked

    }//GEN-LAST:event_lstCategoriaMouseClicked

    private void lstCategoriaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCategoriaMousePressed

    }//GEN-LAST:event_lstCategoriaMousePressed

    private void lstCategoriaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstCategoriaMouseReleased

    }//GEN-LAST:event_lstCategoriaMouseReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int f = tblDetalle.getSelectedRow();
        if (f >= 0) {
            int cantidad = Integer.parseInt(tblDetalle.getValueAt(f, 0).toString()) + 1;
            double precio = Double.parseDouble(tblDetalle.getValueAt(f, 2).toString());
            listaDetalle.get(f).setCantidad(cantidad);
            listaDetalle.get(f).setPrecioProducto(cantidad * listaDetalle.get(f).getPrecioOriginal());
            /*for (int i = f + 1; i < listaDetalle.size(); i++) {
                if (listaDetalle.get(i).isExtra()) {
                    listaDetalle.get(i).setCantidad(cantidad);
                    listaDetalle.get(i).setPrecioProducto(cantidad * listaDetalle.get(i).getPrecioOriginal());
                } else {
                    break;
                }
            }*/

            tblDetalle.setValueAt(cantidad, f, 0);
            tblDetalle.setValueAt(precio * cantidad, f, 3);
            total += precio;
            total = Double.parseDouble(String.format("%.2f", total).replace(",", "."));
            lblTotalDetalle.setText(total + "");
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para aumentar");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int f = tblDetalle.getSelectedRow();
        if (f >= 0) {
            int cantidad = Integer.parseInt(tblDetalle.getValueAt(f, 0).toString());
            if (cantidad > 1) {
                cantidad -= 1;
                double precio = Double.parseDouble(tblDetalle.getValueAt(f, 2).toString());
                listaDetalle.get(f).setCantidad(cantidad);
                listaDetalle.get(f).setPrecioProducto(cantidad * listaDetalle.get(f).getPrecioOriginal());
                /*for (int i = f + 1; i < listaDetalle.size(); i++) {
                    if (listaDetalle.get(i).isExtra()) {
                        listaDetalle.get(i).setCantidad(cantidad);
                        listaDetalle.get(i).setPrecioProducto(cantidad * listaDetalle.get(i).getPrecioOriginal());
                    } else {
                        break;
                    }
                }*/

                tblDetalle.setValueAt(cantidad, f, 0);
                tblDetalle.setValueAt(precio * cantidad, f, 3);
                total -= precio;
                total = Double.parseDouble(String.format("%.2f", total).replace(",", "."));
                lblTotalDetalle.setText(total + "");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para reducir");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int f = tblDetalle.getSelectedRow();
        if (f >= 0) {
            int r = JOptionPane.showConfirmDialog(this, "¿Esta segura de eliminar este producto de la lista?");
            tblDetalle.getValueAt(r, NORMAL);
            if (r == 0) {
                total -= Double.parseDouble(tblDetalle.getValueAt(f, 3).toString());
                tblModel.removeRow(f);
                listaDetalle.remove(f);
                lblTotalDetalle.setText(total + "");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        int filas = tblDetalle.getRowCount();
        if (filas > 0) {
            int r = JOptionPane.showConfirmDialog(this, "¿Esta segura de eliminar TODO?");
            if (r == 0) {
                vaciarCarrito();
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay productos en la lista");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        if (accesoAdmin()) {
            NuevoProductoPresentacion npp = new NuevoProductoPresentacion(this, true);
            npp.cargarInformacion(ar);
            npp.setVisible(true);
            cargarLista();
        }
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (accesoAdmin()) {
            EditarProductoPresentacion epp = new EditarProductoPresentacion(this, true);
            epp.cargarInformacion(ar);
            epp.setVisible(true);
            cargarLista();
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void lstCategoriaValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstCategoriaValueChanged
        int f = lstCategoria.getSelectedIndex();
        if (f >= 0) {
            ProductoNegocio _proN = new ProductoNegocio();
            mostrarProductos(_proN.getProducto(ar.get(f).getIdCategoria()));
        }
    }//GEN-LAST:event_lstCategoriaValueChanged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        //if (accesoAdmin()) {
        ArqueoPresentacion arp = new ArqueoPresentacion(this, false);
        arp.setVisible(true);
        //}
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        if (accesoAdmin()) {
            new NuevoUsuarioPresentacion(this, true).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        if (accesoAdmin()) {
            NuevaCategoriaPresentacion ncp = new NuevaCategoriaPresentacion(this, true);
            ncp.setVisible(true);
            cargarLista();
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        new WifiPresentacion(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        if (accesoAdmin()) {
            EditarCategoriaPresentacion ecp = new EditarCategoriaPresentacion(this, true);
            ecp.setVisible(true);
            cargarLista();
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        FechaReportePresentacion frp = new FechaReportePresentacion(this, false);
        frp.detalleVentas();
        frp.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        VentaNegocio _veN = new VentaNegocio();
        DetalleNegocio _detN = new DetalleNegocio();
        int id = _veN.getUltimoId();
        Map obj = (HashMap) _detN.getDetalleVenta(id);
        Collection<DetalleTicket> detalleTicket = (Collection<DetalleTicket>) obj.get("detalle");
        VentaEntidad _venE = (VentaEntidad) obj.get("venta");

        GenerarTicket gt = new GenerarTicket();

        gt.nuevoTicket(detalleTicket, _venE, "0.0", "0.0");
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        if (accesoAdmin()) {
            FechaReportePresentacion frp = new FechaReportePresentacion(this, false);
            frp.resumenVentas();
            frp.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (accesoAdmin()) {
            if (cambiarClaveAdmin()) {
                JOptionPane.showMessageDialog(this, "Cambiado correctamente");
            }
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        if (accesoAdmin()) {
            new EditarUsuarioPresentacion(this, true).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        if (accesoAdmin()) {
            new NuevaGuarnicionPresentacion(this, true).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void tblDetalleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleMouseClicked

    }//GEN-LAST:event_tblDetalleMouseClicked

    private void tblDetalleMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleMousePressed
        if (evt.getClickCount() == 2) {
            int f = tblDetalle.getSelectedRow();
            if (f >= 0) {
                String plato = tblDetalle.getValueAt(f, 1) + "";
                JOptionPane.showMessageDialog(this, plato, "Sabor", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_tblDetalleMousePressed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        if (accesoAdmin()) {
            new EditarGuarnicionPresentacion(this, true).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        if (accesoAdmin()) {
            new AnularVentaPresentacion(this, true).setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        new AcercaDePresentacion(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaCajaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaCajaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaCajaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaCajaPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaCajaPresentacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCobrar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    public static javax.swing.JLabel lblFecha;
    public static javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblNombreCajero;
    private javax.swing.JLabel lblTotalDetalle;
    private javax.swing.JList<String> lstCategoria;
    private javax.swing.JPanel pnlCargarProductos;
    private javax.swing.JPanel pnlCategoria;
    private javax.swing.JPanel pnlCentral;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlGridInfo;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JPanel pnlProductos;
    private javax.swing.JPanel pnlSuperior;
    private javax.swing.JTable tblDetalle;
    // End of variables declaration//GEN-END:variables
}
