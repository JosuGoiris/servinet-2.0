/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Clientes;

import Datos.DDetalleFactura;
import Datos.DFactura;
import Datos.DServiciodelCliente;
import Logica.LFactura;
import Logica.LSolicitud;
import static Presentacion.Clientes.frmValoresSolicitudNuevo.diasActuales;
import static Presentacion.Clientes.frmValoresSolicitudNuevo.diasdeverdad;
import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author josug
 */
public final class frmGenerarFacturas extends javax.swing.JFrame {

    public int xx;
    public int xy;
    Calendar fecha_actual = new GregorianCalendar();
    SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
    int mesSiguiente;
    int ultimoDia;
    int faltantes;
    double sobrecargo;

    /**
     * Creates new form frmPrincipalSolicitudesNuevo
     */
    public frmGenerarFacturas() {
        initComponents();
        this.setLocationRelativeTo(null);
        GenerarFechas();
    }

    public void cargarValores() {
        frmGestionarServicios form = new frmGestionarServicios();
        txtIdCliente.setText(String.valueOf(frmGestionarServicios.idCliente));
        txtNombreCliente.setText(String.valueOf(frmGestionarServicios.nombreCliente));
        txtApellidoCliente.setText(String.valueOf(frmGestionarServicios.apellidoCliente));
        txtNumeroCedula.setText(String.valueOf(frmGestionarServicios.nroCedula));
        txtServicio.setText(String.valueOf(frmGestionarServicios.nombreServicio));
        txtPrecio.setText(String.valueOf(frmGestionarServicios.precio));
        jdFechaVencimiento.setDate(frmGestionarServicios.fvencimiento);
        txtSobrecargos.setText("0");
        double precio = Double.parseDouble(txtPrecio.getText());
        double total;
        total = precio;
        txtTotal.setText(String.valueOf(total));
    }

    public void cargarValoresConSobrecargo() {
        frmGestionarServicios form = new frmGestionarServicios();
        txtIdCliente.setText(String.valueOf(frmGestionarServicios.idCliente));
        txtNombreCliente.setText(String.valueOf(frmGestionarServicios.nombreCliente));
        txtApellidoCliente.setText(String.valueOf(frmGestionarServicios.apellidoCliente));
        txtNumeroCedula.setText(String.valueOf(frmGestionarServicios.nroCedula));
        txtServicio.setText(String.valueOf(frmGestionarServicios.nombreServicio));
        txtPrecio.setText(String.valueOf(frmGestionarServicios.precio));
        jdFechaVencimiento.setDate(frmGestionarServicios.fvencimiento);
        txtSobrecargos.setText(String.valueOf(frmGestionarServicios.multa));
        double precio = Double.parseDouble(txtPrecio.getText());
        double sobrecargo = Double.parseDouble(txtSobrecargos.getText());
        double total;
        total = precio + sobrecargo;
        txtTotal.setText(String.valueOf(total));
    }

    public void GenerarFechas() {
        //Se Inserta la fecha actual para la fecha de emision de la factura
        jdFechaEmision.setCalendar(fecha_actual);

        //YA TENGO OTRO QUE HACE LO MISMO
        //Se inserta la fecha final del mes siguiente para la fecha de pago
        /*Calendar mesActual = Calendar.getInstance();
            mesActual.set(Calendar.DAY_OF_MONTH, 1);
            mesActual.set(Calendar.DATE, mesActual.getActualMaximum(Calendar.DATE));
            diasActuales = Integer.parseInt(sdf2.format(mesActual.getTime()));
            int año = jdFechaEmision.getCalendar().get(Calendar.YEAR);
            int mes = jdFechaEmision.getCalendar().get(Calendar.MONTH) + 1;
            int dia = jdFechaEmision.getCalendar().get(Calendar.DAY_OF_MONTH);
            mesSiguiente = mes - 1;
            ultimoDia = Integer.parseInt(sdf2.format(mesActual.getTime()));
            jdFechaVencimiento.setDate(new Date(año - 1900, mesSiguiente, ultimoDia));
         */
        obtenerMes();
    }

    public void obtenerMes() {
        int mes = jdFechaEmision.getCalendar().get(Calendar.MONTH);
        String mesPago = null;
        switch (mes) {
            case 0: {
                mesPago = "Enero";
                break;
            }
            case 1: {
                mesPago = "Febrero";
                break;
            }
            case 2: {
                mesPago = "Marzo";
                break;
            }
            case 3: {
                mesPago = "Abril";
                break;
            }
            case 4: {
                mesPago = "Mayo";
                break;
            }
            case 5: {
                mesPago = "Junio";
                break;
            }
            case 6: {
                mesPago = "Julio";
                break;
            }
            case 7: {
                mesPago = "Agosto";
                break;
            }
            case 8: {
                mesPago = "Septiembre";
                break;
            }
            case 9: {
                mesPago = "Octubre";
                break;
            }
            case 10: {
                mesPago = "Noviembre";
                break;
            }
            case 11: {
                mesPago = "Diciembre";
                break;
            }
        }
        lblmespago.setText(mesPago);
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
        jLabel1 = new javax.swing.JLabel();
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
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtIdFactura = new javax.swing.JTextField();
        txtNumeroCedula = new javax.swing.JTextField();
        txtIdCliente = new javax.swing.JTextField();
        txtNombreCliente = new javax.swing.JTextField();
        txtApellidoCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        lblmespago = new javax.swing.JLabel();
        jdFechaEmision = new com.toedter.calendar.JDateChooser();
        jdFechaVencimiento = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        txtServicio = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtSobrecargos = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("DATOS DE LA FACTURA DEL CLIENTE");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 960, 50));

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
        jPanel1.add(mover, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 40));

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
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGenerarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnGenerarMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGenerarMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGenerarMouseEntered(evt);
            }
        });
        btnGenerar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_plus_math_30px.png"))); // NOI18N
        btnGenerar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 31, 36));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Generar");
        btnGenerar.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 12, -1, -1));
        btnGenerar.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 38, 97, 10));

        jPanel7.add(btnGenerar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 170, 50));

        btnSalir.setBackground(new java.awt.Color(102, 0, 0));
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalirMousePressed(evt);
            }
        });
        btnSalir.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_exit_30px.png"))); // NOI18N
        btnSalir.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 31, 36));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("Salir");
        btnSalir.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 12, -1, -1));
        btnSalir.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 38, 109, 10));

        jPanel7.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 170, 50));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("SERVINET");
        jPanel7.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, -1, -1));
        jPanel7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 140, 20));

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

        jPanel7.add(btnSalir3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 170, 50));

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

        jPanel7.add(btnSalir5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 170, 50));
        jPanel7.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 160, 20));
        jPanel7.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 160, 20));
        jPanel7.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 160, 20));

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 80, 210, 420));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("C.I.:");
        jPanel5.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, -1, -1));

        jLabel18.setBackground(new java.awt.Color(0, 0, 0));
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("MES DE PAGO:");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, -1));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("APELLIDOS:");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA VENCIMIENTO:");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("FECHA EMISION:");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, -1, -1));

        jLabel10.setBackground(new java.awt.Color(0, 0, 0));
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("NOMBRES:");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 90, -1, -1));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ID CLIENTE:");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel12.setBackground(new java.awt.Color(0, 0, 0));
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("DESCRIPCION:");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));
        jPanel5.add(txtIdFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 70, -1));
        jPanel5.add(txtNumeroCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 110, 100, -1));
        jPanel5.add(txtIdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 70, -1));
        jPanel5.add(txtNombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 120, -1));
        jPanel5.add(txtApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 130, -1));

        txtDescripcion.setColumns(20);
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 500, 200));

        lblmespago.setBackground(new java.awt.Color(0, 0, 0));
        lblmespago.setForeground(new java.awt.Color(255, 255, 255));
        lblmespago.setText("mes");
        jPanel5.add(lblmespago, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        jdFechaEmision.setForeground(new java.awt.Color(255, 255, 255));
        jdFechaEmision.setDateFormatString("yyyy/MM/dd");
        jPanel5.add(jdFechaEmision, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 180, -1));

        jdFechaVencimiento.setDateFormatString("yyyy/MM/dd");
        jPanel5.add(jdFechaVencimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 180, -1));

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("SERVICIO A PAGAR:");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, -1, -1));
        jPanel5.add(txtServicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 110, 130, -1));

        jLabel23.setBackground(new java.awt.Color(0, 0, 0));
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("PRECIO:");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 150, -1, -1));
        jPanel5.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 170, 100, -1));

        jLabel21.setText("Gs.");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 150, -1, -1));

        jLabel24.setText("Gs.");
        jPanel5.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 210, -1, -1));
        jPanel5.add(txtSobrecargos, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 100, -1));

        jLabel22.setBackground(new java.awt.Color(0, 0, 0));
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("SOBRECARGOS:");
        jPanel5.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 210, -1, -1));

        jLabel20.setBackground(new java.awt.Color(0, 0, 0));
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("TOTAL:");
        jPanel5.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 270, -1, -1));
        jPanel5.add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 290, 100, -1));

        jLabel25.setText("Gs.");
        jPanel5.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 270, -1, -1));
        jPanel5.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 700, 20));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 10, 340));

        jSeparator8.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 10, 370));
        jPanel5.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 700, 20));
        jPanel5.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 660, 20));

        jSeparator13.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator13, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 10, 380));

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 10, 320));

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel5.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 10, 350));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID FACTURA:");
        jPanel5.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, -1));
        jPanel5.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 660, 20));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 410));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 750, 410));

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
        DFactura df = new DFactura();
        DDetalleFactura ddf = new DDetalleFactura();
        LFactura fun = new LFactura();
        DServiciodelCliente dsc = new DServiciodelCliente();

        //Guarda la fecha de emision
        int año = jdFechaEmision.getCalendar().get(Calendar.YEAR);
        int mes = jdFechaEmision.getCalendar().get(Calendar.MONTH);
        int dia = jdFechaEmision.getCalendar().get(Calendar.DAY_OF_MONTH);
        df.setFechaEmision(new Date((año - 1900), mes, dia));

        //Guarda la fecha de vencimiento
        int añoV = jdFechaVencimiento.getCalendar().get(Calendar.YEAR);
        int mesV = jdFechaVencimiento.getCalendar().get(Calendar.MONTH);
        int diaV = jdFechaVencimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
        df.setFechaVencimiento(new Date((añoV - 1900), mesV, diaV));

        df.setClienteId(Integer.parseInt(txtIdCliente.getText()));
        ddf.setDescripcion(txtDescripcion.getText());
        ddf.setMesPago(lblmespago.getText());
        ddf.setPrecio(Double.parseDouble(txtPrecio.getText()));

        ddf.setSobrecargos(Double.parseDouble(txtSobrecargos.getText()));
        ddf.setTotal(Double.parseDouble(txtTotal.getText()));
        ddf.setServiciodelclienteId(Integer.parseInt(txtIdCliente.getText()));

        dsc.setIdServiciodelCliente(Integer.parseInt(txtIdCliente.getText()));

        fun.insertarFactura(df, ddf, dsc);
    }//GEN-LAST:event_btnGenerarMousePressed

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        this.dispose();
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

    void setColor(JPanel panel) {
        panel.setBackground(new Color(51, 0, 0));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(102, 0, 0));
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
            java.util.logging.Logger.getLogger(frmGenerarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGenerarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGenerarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGenerarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGenerarFacturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnGenerar;
    private javax.swing.JPanel btnSalir;
    private javax.swing.JPanel btnSalir3;
    private javax.swing.JPanel btnSalir5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private com.toedter.calendar.JDateChooser jdFechaEmision;
    private com.toedter.calendar.JDateChooser jdFechaVencimiento;
    private javax.swing.JLabel lblmespago;
    private javax.swing.JPanel mover;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdFactura;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNumeroCedula;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtServicio;
    private javax.swing.JTextField txtSobrecargos;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
