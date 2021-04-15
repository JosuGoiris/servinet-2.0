/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Clientes;

import Datos.DServiciodelCliente;
import Logica.LEstados;
import Logica.LVistas;
import Presentacion.Mantenimiento.frmVistaServicioNuevo;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author josug
 */
public class frmEditarServiciodelCliente extends javax.swing.JFrame {

    public int xx;
    public int xy;
    public static int control = 0;
    private LEstados es = new LEstados();
    Calendar fecha_actual = new GregorianCalendar();
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
    Calendar cal = Calendar.getInstance();

    public static String accion = null;
    public static String ruc = "";
    public static String nombres = "";
    public static String apellidos = "";
    public static String cedula = "";
    public static String telefono = "";
    public static int zonaId = 0;
    public static int direccionId = 0;
    public static String direccion = "";
    public static int tiposervicioId = 0;
    public static int estadoId = 0;
    public static int diasActuales;
    public static int diasdeverdad;
    int mesSiguiente;
    int ultimoDia;
    int faltantes;
    int meses = 12;

    /**
     * Creates new form frmValoresSolicitudNuevo
     */
    public frmEditarServiciodelCliente() {
        initComponents();
        this.setLocationRelativeTo(null);
        txtNombres.setEditable(false);
        txtApellidos.setEditable(false);
        txtCedula.setEditable(false);
        txtId.setEditable(false);
        txtVelocidad.setEditable(false);
        txtPrecio.setEditable(false);
        txtTipoSolicitud.setEditable(false);
        txtIdServicio.setVisible(false);
    }

    void setColor(JPanel panel) {
        panel.setBackground(new Color(51, 0, 0));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(102, 0, 0));
    }

    public void cargarValores() {
        txtId.setText(String.valueOf(frmGestionarServicios.idCliente));
        txtNombres.setText(frmGestionarServicios.nombreCliente);
        txtApellidos.setText(frmGestionarServicios.apellidoCliente);
        txtCedula.setText(frmGestionarServicios.nroCedula);
        txtTipoSolicitud.setText(frmGestionarServicios.nombreServicio);
        txtVelocidad.setText(String.valueOf(frmGestionarServicios.conexion));
        txtPrecio.setText(String.valueOf(frmGestionarServicios.precio));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator23 = new javax.swing.JSeparator();
        bg = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNombres = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtApellidos = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtTipoSolicitud = new javax.swing.JTextField();
        btnBuscarTipoServicio = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        btnGuardar4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtVelocidad = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JPanel();
        btnSalir = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        btnGuardar3 = new javax.swing.JPanel();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        jSeparator24 = new javax.swing.JSeparator();
        jSeparator25 = new javax.swing.JSeparator();
        jSeparator26 = new javax.swing.JSeparator();
        jSeparator27 = new javax.swing.JSeparator();
        jSeparator28 = new javax.swing.JSeparator();
        jSeparator29 = new javax.swing.JSeparator();
        mover = new javax.swing.JPanel();
        btnSalir1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnMini = new javax.swing.JPanel();
        lblMini = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        txtIdServicio = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        jPanel2.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 31, 53, -1));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombres:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 65, -1, -1));
        jPanel2.add(txtNombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 85, 138, -1));

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellidos:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 123, -1, -1));
        jPanel2.add(txtApellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 143, 138, -1));
        jPanel2.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 201, 138, -1));

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cédula de Identidad:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 181, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 170, 240));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Gs.");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 40, -1));
        jPanel3.add(txtTipoSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 200, -1));

        btnBuscarTipoServicio.setBackground(new java.awt.Color(102, 0, 0));
        btnBuscarTipoServicio.setPreferredSize(new java.awt.Dimension(40, 40));
        btnBuscarTipoServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarTipoServicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarTipoServicioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBuscarTipoServicioMousePressed(evt);
            }
        });
        btnBuscarTipoServicio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_search_20px.png"))); // NOI18N
        btnBuscarTipoServicio.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel3.add(btnBuscarTipoServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 40, 40));

        btnGuardar4.setBackground(new java.awt.Color(0, 0, 0));
        btnGuardar4.setEnabled(false);

        javax.swing.GroupLayout btnGuardar4Layout = new javax.swing.GroupLayout(btnGuardar4);
        btnGuardar4.setLayout(btnGuardar4Layout);
        btnGuardar4Layout.setHorizontalGroup(
            btnGuardar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        btnGuardar4Layout.setVerticalGroup(
            btnGuardar4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel3.add(btnGuardar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Precio:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));
        jPanel3.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 100, -1));

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tipo de Servicio");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("MB/s");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 40, -1));
        jPanel3.add(txtVelocidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 50, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 390, 120));

        btnGuardar.setBackground(new java.awt.Color(102, 0, 0));
        btnGuardar.setPreferredSize(new java.awt.Dimension(50, 50));
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGuardarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarMousePressed(evt);
            }
        });
        btnGuardar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_save_30px.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(25, 25));
        btnGuardar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 50, 50));

        btnGuardar1.setBackground(new java.awt.Color(0, 0, 0));
        btnGuardar1.setEnabled(false);

        javax.swing.GroupLayout btnGuardar1Layout = new javax.swing.GroupLayout(btnGuardar1);
        btnGuardar1.setLayout(btnGuardar1Layout);
        btnGuardar1Layout.setHorizontalGroup(
            btnGuardar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        btnGuardar1Layout.setVerticalGroup(
            btnGuardar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 250, -1, 50));

        btnSalir.setBackground(new java.awt.Color(102, 0, 0));
        btnSalir.setPreferredSize(new java.awt.Dimension(50, 50));
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalirMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalirMousePressed(evt);
            }
        });
        btnSalir.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_delete_30px.png"))); // NOI18N
        jLabel13.setPreferredSize(new java.awt.Dimension(25, 25));
        btnSalir.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 240, 50, 50));

        btnGuardar3.setBackground(new java.awt.Color(0, 0, 0));
        btnGuardar3.setEnabled(false);

        javax.swing.GroupLayout btnGuardar3Layout = new javax.swing.GroupLayout(btnGuardar3);
        btnGuardar3.setLayout(btnGuardar3Layout);
        btnGuardar3Layout.setHorizontalGroup(
            btnGuardar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        btnGuardar3Layout.setVerticalGroup(
            btnGuardar3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel1.add(btnGuardar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 250, 50, 50));
        jPanel1.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 480, 10));
        jPanel1.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 390, 10));
        jPanel1.add(jSeparator24, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 120, 10));

        jSeparator25.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator25, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 240, 20, 40));

        jSeparator26.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 20, 270));

        jSeparator27.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator27, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 20, 30));
        jPanel1.add(jSeparator28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 210, 20));

        jSeparator29.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator29, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 20, 150));

        bg.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 660, 310));

        mover.setBackground(new java.awt.Color(204, 204, 204));
        mover.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                moverMouseDragged(evt);
            }
        });
        mover.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                moverMousePressed(evt);
            }
        });
        mover.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalir1.setBackground(new java.awt.Color(102, 0, 0));
        btnSalir1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalir1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalir1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalir1MousePressed(evt);
            }
        });
        btnSalir1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_close_window_20px.png"))); // NOI18N
        btnSalir1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 20, 20));

        mover.add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 30, 30));

        jPanel12.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        mover.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 20, 30, 30));

        btnMini.setBackground(new java.awt.Color(102, 0, 0));
        btnMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMiniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMiniMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMiniMousePressed(evt);
            }
        });
        btnMini.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblMini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_minimize_window_20px.png"))); // NOI18N
        lblMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblMiniMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblMiniMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblMiniMousePressed(evt);
            }
        });
        btnMini.add(lblMini, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 20, 20));

        mover.add(btnMini, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 30, 30));

        jPanel13.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        mover.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 30, 30));
        mover.add(txtIdServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 50, -1));

        bg.add(mover, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        this.dispose();
    }//GEN-LAST:event_btnSalirMousePressed

    private void btnGuardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMousePressed
        DServiciodelCliente dsc = new DServiciodelCliente();
        LVistas fun = new LVistas();
        
        dsc.setDetalleservicioId(Integer.parseInt(txtIdServicio.getText()));
        dsc.setIdServiciodelCliente(Integer.parseInt(txtId.getText()));
        fun.actualizarVelocidad(dsc);
        
        JOptionPane.showMessageDialog(null, "Datos Actualizados!");
        this.dispose();
    }//GEN-LAST:event_btnGuardarMousePressed

    private void btnBuscarTipoServicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarTipoServicioMousePressed
        frmVistaServicioNuevo form = new frmVistaServicioNuevo();
        form.setVisible(true);
        form.toFront();
        control = 2;
    }//GEN-LAST:event_btnBuscarTipoServicioMousePressed

    private void btnBuscarTipoServicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarTipoServicioMouseEntered
        setColor(btnBuscarTipoServicio);
    }//GEN-LAST:event_btnBuscarTipoServicioMouseEntered

    private void btnBuscarTipoServicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarTipoServicioMouseExited
        resetColor(btnBuscarTipoServicio);
    }//GEN-LAST:event_btnBuscarTipoServicioMouseExited

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        setColor(btnGuardar);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnGuardarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseExited
        resetColor(btnGuardar);
    }//GEN-LAST:event_btnGuardarMouseExited

    private void btnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseEntered
        setColor(btnSalir);
    }//GEN-LAST:event_btnSalirMouseEntered

    private void btnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseExited
        resetColor(btnSalir);
    }//GEN-LAST:event_btnSalirMouseExited

    private void btnSalir1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MouseEntered
        setColor(btnSalir);
    }//GEN-LAST:event_btnSalir1MouseEntered

    private void btnSalir1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MouseExited
        resetColor(btnSalir);
    }//GEN-LAST:event_btnSalir1MouseExited

    private void btnSalir1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir1MousePressed
        this.dispose();
    }//GEN-LAST:event_btnSalir1MousePressed

    private void lblMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMouseEntered
        setColor(btnMini);
    }//GEN-LAST:event_lblMiniMouseEntered

    private void lblMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMouseExited
        resetColor(btnMini);
    }//GEN-LAST:event_lblMiniMouseExited

    private void lblMiniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMousePressed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_lblMiniMousePressed

    private void btnMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMouseEntered
        setColor(btnMini);
    }//GEN-LAST:event_btnMiniMouseEntered

    private void btnMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMouseExited
        resetColor(btnMini);
    }//GEN-LAST:event_btnMiniMouseExited

    private void btnMiniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMousePressed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMiniMousePressed

    private void moverMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_moverMouseDragged

    private void moverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_moverMousePressed

    
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
            java.util.logging.Logger.getLogger(frmEditarServiciodelCliente.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEditarServiciodelCliente.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEditarServiciodelCliente.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEditarServiciodelCliente.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmEditarServiciodelCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JPanel btnBuscarTipoServicio;
    private javax.swing.JPanel btnGuardar;
    private javax.swing.JPanel btnGuardar1;
    private javax.swing.JPanel btnGuardar3;
    private javax.swing.JPanel btnGuardar4;
    private javax.swing.JPanel btnMini;
    private javax.swing.JPanel btnSalir;
    private javax.swing.JPanel btnSalir1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JLabel lblMini;
    private javax.swing.JPanel mover;
    public static javax.swing.JTextField txtApellidos;
    public static javax.swing.JTextField txtCedula;
    public static javax.swing.JTextField txtId;
    public static javax.swing.JTextField txtIdServicio;
    public static javax.swing.JTextField txtNombres;
    public static javax.swing.JTextField txtPrecio;
    public static javax.swing.JTextField txtTipoSolicitud;
    public static javax.swing.JTextField txtVelocidad;
    // End of variables declaration//GEN-END:variables
}