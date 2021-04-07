/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Clientes;

import Datos.DCaja;
import Datos.DDetalleFactura;
import Datos.DDetallePagos;
import Datos.DFactura;
import Datos.DGestionarServicios;
import Datos.DPagos;
import Datos.DServiciodelCliente;
import Logica.ConexionSingleton;
import Logica.LCaja;
import Logica.LEstados;
import Logica.LFactura;
import Logica.LGestionarServicios;
import Logica.LPagos;
import static Presentacion.Clientes.frmValoresSolicitudNuevo.diasActuales;
import static Presentacion.Cuadrillas.frmRegistrarEntrada.txtCedula;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author josug
 */
public final class frmGenerarPago extends javax.swing.JFrame {

    LEstados es = new LEstados();
    public int xx;
    public int xy;
    Calendar fecha_actual = new GregorianCalendar();
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
    SimpleDateFormat formatoMes = new SimpleDateFormat("MM");
    SimpleDateFormat formatoAño = new SimpleDateFormat("yyyy");
    Connection cn = ConexionSingleton.getConnection();
    LCaja lc = new LCaja();
    int mesSiguiente;
    int ultimoDia;
    int faltantes;
    double sobrecargo;
    double monto;
    int idCaja;
    public static double multa;

    /**
     * Creates new form frmPrincipalSolicitudesNuevo
     */
    public frmGenerarPago() {
        initComponents();
        this.setLocationRelativeTo(null);
        cmbFormadePago.setModel(es.llenarComboFormadePago());
        GenerarFechas();
        cargarValores();
    }

    public void cargarValores() {
        txtIdFactura.setText(String.valueOf(frmGestionarPagos.idFactura));
        jdFechaVencimiento.setDate(Date.valueOf(frmGestionarPagos.fechaVencimiento));
        txtIdCliente.setText(String.valueOf(frmGestionarPagos.idCliente));
        txtNombreCliente.setText(String.valueOf(frmGestionarPagos.nombreCliente));
        txtNumeroCedula.setText(String.valueOf(frmGestionarPagos.nroCedula));
        txtServicio.setText(String.valueOf(frmGestionarPagos.servicioCliente));

        multa = frmGestionarPagos.multa;
        double pagar = frmGestionarPagos.total;
        double total = multa + pagar;
        txtTotal.setText(String.valueOf(total));
        txtPagar.setText(String.valueOf(total));
    }

    public void GenerarFechas() {
        //Se Inserta la fecha actual para la fecha de emision de la factura
        jdFechaRealizado.setCalendar(fecha_actual);
    }

    public void traerIdFormadePago() {
        if (cmbFormadePago.getSelectedItem().equals("Tarjeta")) {
            txtIdFormadePago.setText("2");
        } else if (cmbFormadePago.getSelectedItem().equals("Efectivo")) {
            txtIdFormadePago.setText("3");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        mover = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        btnGenerar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnSalir = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        btnSalir3 = new javax.swing.JPanel();
        btnSalir5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdFactura = new javax.swing.JTextField();
        txtNumeroCedula = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        jdFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        txtServicio = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        cmbFormadePago = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jdFechaRealizado = new com.toedter.calendar.JDateChooser();
        txtIdFormadePago = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtVuelto = new javax.swing.JTextField();
        txtPagar = new javax.swing.JTextField();
        txtPagando = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 630, 50));

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
        jPanel1.add(mover, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 40));

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGenerar.setBackground(new java.awt.Color(102, 0, 0));
        btnGenerar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGenerar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnGenerarFocusGained(evt);
            }
        });
        btnGenerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGenerarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGenerarMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGenerarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGenerarMouseReleased(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_plus_math_30px.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Generar");

        javax.swing.GroupLayout btnGenerarLayout = new javax.swing.GroupLayout(btnGenerar);
        btnGenerar.setLayout(btnGenerarLayout);
        btnGenerarLayout.setHorizontalGroup(
            btnGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnGenerarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(btnGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnGenerarLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 47, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        btnGenerarLayout.setVerticalGroup(
            btnGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnGenerarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnGenerarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btnGenerarLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel7.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 170, 50));

        btnSalir.setBackground(new java.awt.Color(102, 0, 0));
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalirMousePressed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_exit_30px.png"))); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("Salir");

        javax.swing.GroupLayout btnSalirLayout = new javax.swing.GroupLayout(btnSalir);
        btnSalir.setLayout(btnSalirLayout);
        btnSalirLayout.setHorizontalGroup(
            btnSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSalirLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(btnSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(btnSalirLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addContainerGap(85, Short.MAX_VALUE))
                    .addComponent(jSeparator4)))
        );
        btnSalirLayout.setVerticalGroup(
            btnSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnSalirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(btnSalirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(btnSalirLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel7.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 170, 50));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("SERVINET");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));
        jPanel7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 140, 20));

        btnSalir3.setBackground(new java.awt.Color(51, 0, 0));
        btnSalir3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSalir3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalir3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout btnSalir3Layout = new javax.swing.GroupLayout(btnSalir3);
        btnSalir3.setLayout(btnSalir3Layout);
        btnSalir3Layout.setHorizontalGroup(
            btnSalir3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        btnSalir3Layout.setVerticalGroup(
            btnSalir3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel7.add(btnSalir3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 170, 50));

        btnSalir5.setBackground(new java.awt.Color(51, 0, 0));
        btnSalir5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSalir5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalir5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout btnSalir5Layout = new javax.swing.GroupLayout(btnSalir5);
        btnSalir5.setLayout(btnSalir5Layout);
        btnSalir5Layout.setHorizontalGroup(
            btnSalir5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        btnSalir5Layout.setVerticalGroup(
            btnSalir5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel7.add(btnSalir5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, 50));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 210, 340));

        jPanel4.setBackground(new java.awt.Color(153, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("C.I.:");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 70, -1, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FECHA PAGO:");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("NOMBRES:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, -1, -1));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID CLIENTE:");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID FACTURA:");
        jPanel5.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));
        jPanel5.add(txtIdFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 70, -1));
        jPanel5.add(txtNumeroCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 90, 100, -1));
        jPanel5.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 70, -1));
        jPanel5.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 150, -1));

        jdFechaVencimiento.setForeground(new java.awt.Color(255, 255, 255));
        jdFechaVencimiento.setDateFormatString("yyyy/MM/dd");
        jPanel5.add(jdFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 130, -1));

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("FORMA DE PAGO:");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));
        jPanel5.add(txtServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 140, -1));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("TOTAL:");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, -1, -1));
        jPanel5.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 150, 100, -1));

        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Gs.");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        cmbFormadePago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFormadePagoActionPerformed(evt);
            }
        });
        jPanel5.add(cmbFormadePago, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 140, -1));

        jLabel21.setBackground(new java.awt.Color(0, 0, 0));
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("SERVICIO A PAGAR:");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("FECHA VENCIMIENTO:");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, -1, -1));

        jdFechaRealizado.setForeground(new java.awt.Color(255, 255, 255));
        jdFechaRealizado.setDateFormatString("yyyy/MM/dd");
        jPanel5.add(jdFechaRealizado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 30, 130, -1));
        jPanel5.add(txtIdFormadePago, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 70, -1));

        jLabel14.setBackground(new java.awt.Color(0, 0, 0));
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("ID CLIENTE:");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jButton1.setText("Prueba");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, -1, -1));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 240));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(txtVuelto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 90, -1));
        jPanel2.add(txtPagar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, -1));

        txtPagando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPagandoActionPerformed(evt);
            }
        });
        txtPagando.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPagandoKeyPressed(evt);
            }
        });
        jPanel2.add(txtPagando, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 90, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("=");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("-");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, -1, -1));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Vuelto:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jLabel15.setBackground(new java.awt.Color(0, 0, 0));
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Total a Pagar:");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Importe");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        jPanel4.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, 400, 70));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 420, 340));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarMousePressed
        DPagos dp = new DPagos();
        DDetalleFactura ddf = new DDetalleFactura();
        DDetallePagos ddp = new DDetallePagos();
        LPagos fun1 = new LPagos();
        DCaja dc = new DCaja();
        DServiciodelCliente dsc = new DServiciodelCliente();
        LGestionarServicios fun2 = new LGestionarServicios();

        Calendar siguiente = new GregorianCalendar();
        siguiente.set(Calendar.MONTH, Calendar.MONTH + 1);
        siguiente.set(Calendar.DAY_OF_MONTH, siguiente.getActualMaximum(Calendar.DAY_OF_MONTH));
        siguiente.set(Calendar.DATE, siguiente.getActualMaximum(Calendar.DATE));
        String diaSiguiente = sdf2.format(siguiente.getTime());
        String mesSiguiente = formatoMes.format(siguiente.getTime());
        String añoSiguiente = formatoAño.format(siguiente.getTime());
        int diaS = Integer.parseInt(diaSiguiente);
        int mesS = Integer.parseInt(mesSiguiente);
        int añoS = Integer.parseInt(añoSiguiente);
        System.out.println(añoS + "-" + mesS + "-" + diaS);
        dsc.setFechaPago(new Date((añoS - 1900), (mesS - 1), diaS));
        dsc.setIdServiciodelCliente(Integer.parseInt(txtIdCliente.getText()));
        if(multa > 0){
            dsc.setMulta(0);
            dsc.setEstadoMulta("No");
            fun2.multa(dsc);
        }
        fun2.EstadoActivo(dsc);

        idCaja = lc.traerId();
        monto = lc.traerMonto();
        JOptionPane.showMessageDialog(null, idCaja);
        JOptionPane.showMessageDialog(null, monto);
        double montoTotal;
        double total = Double.parseDouble(txtVuelto.getText());
        montoTotal = monto - total;
        dc.setMontoApertura(montoTotal);
        dc.setIdCaja(idCaja);
        fun1.restarMonto(dc);

        //Guarda la fecha de vencimiento
        int añoV = jdFechaVencimiento.getCalendar().get(Calendar.YEAR);
        int mesV = jdFechaVencimiento.getCalendar().get(Calendar.MONTH);
        int diaV = jdFechaVencimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
        dp.setFechaPago(new Date((añoV - 1900), mesV, diaV));

        //Guarda la fecha de emision
        int año = jdFechaRealizado.getCalendar().get(Calendar.YEAR);
        int mes = jdFechaRealizado.getCalendar().get(Calendar.MONTH);
        int dia = jdFechaRealizado.getCalendar().get(Calendar.DAY_OF_MONTH);
        dp.setFechaRealizado(new Date((año - 1900), mes, dia));

        dp.setServiciodelclienteId(Integer.parseInt(txtIdCliente.getText()));
        dp.setFormadepagoId(Integer.parseInt(txtIdFormadePago.getText()));
        dp.setDetallefacturaId(Integer.parseInt(txtIdFactura.getText()));

        ddf.setIdDetalleFactura(Integer.parseInt(txtIdFactura.getText()));
        fun1.RealizarPago(dp, ddf);
    }//GEN-LAST:event_btnGenerarMousePressed

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirMousePressed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed

    }//GEN-LAST:event_jPanel1MousePressed

    private void moverMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_moverMouseDragged

    private void moverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_moverMousePressed

    private void btnGenerarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarMouseReleased

    }//GEN-LAST:event_btnGenerarMouseReleased

    private void btnGenerarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnGenerarFocusGained

    }//GEN-LAST:event_btnGenerarFocusGained

    private void btnGenerarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarMouseEntered
        setColor(btnGenerar);
    }//GEN-LAST:event_btnGenerarMouseEntered

    private void btnGenerarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarMouseExited
        resetColor(btnGenerar);
    }//GEN-LAST:event_btnGenerarMouseExited

    private void btnSalir3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir3MousePressed

    private void btnSalir5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalir5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalir5MousePressed

    private void cmbFormadePagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFormadePagoActionPerformed
        traerIdFormadePago();
    }//GEN-LAST:event_cmbFormadePagoActionPerformed

    private void txtPagandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPagandoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPagandoActionPerformed

    private void txtPagandoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPagandoKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            double pagar, importe, total;
            pagar = Double.parseDouble(txtPagar.getText());
            importe = Double.parseDouble(txtPagando.getText());
            total = importe - pagar;
            txtVuelto.setText(String.valueOf(total));

        }
    }//GEN-LAST:event_txtPagandoKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Calendar siguiente = new GregorianCalendar();
        siguiente.set(Calendar.MONTH, Calendar.MONTH + 1);
        siguiente.set(Calendar.DAY_OF_MONTH, siguiente.getActualMaximum(Calendar.DAY_OF_MONTH));
        siguiente.set(Calendar.DATE, siguiente.getActualMaximum(Calendar.DATE));
        String diaSiguiente = sdf2.format(siguiente.getTime());
        String mesSiguiente = formatoMes.format(siguiente.getTime());
        String añoSiguiente = formatoAño.format(siguiente.getTime());
        int diaS = Integer.parseInt(diaSiguiente);
        int mesS = Integer.parseInt(mesSiguiente);
        int añoS = Integer.parseInt(añoSiguiente);
        System.out.println(añoS + "-" + diaS + "-" + mesS);
    }//GEN-LAST:event_jButton1ActionPerformed

    int obtenerMonto() {
        int id = 0;
        String SQL = "select idCaja, montoApertura from tblcaja order by idCaja desc limit 1";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);

            idCaja = rs.getInt("idCaja");
            monto = rs.getDouble("montoApertura");

        } catch (Exception e) {
        }
        return idCaja;
    }

    void setColor(JPanel panel) {
        panel.setBackground(new Color(85, 65, 118));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(153, 51, 255));
    }

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
            java.util.logging.Logger.getLogger(frmGenerarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGenerarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGenerarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGenerarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGenerarPago().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnGenerar;
    private javax.swing.JPanel btnSalir;
    private javax.swing.JPanel btnSalir3;
    private javax.swing.JPanel btnSalir5;
    private javax.swing.JComboBox<String> cmbFormadePago;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private com.toedter.calendar.JDateChooser jdFechaRealizado;
    private com.toedter.calendar.JDateChooser jdFechaVencimiento;
    private javax.swing.JPanel mover;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdFactura;
    private javax.swing.JTextField txtIdFormadePago;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNumeroCedula;
    private javax.swing.JTextField txtPagando;
    private javax.swing.JTextField txtPagar;
    private javax.swing.JTextField txtServicio;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtVuelto;
    // End of variables declaration//GEN-END:variables
}
