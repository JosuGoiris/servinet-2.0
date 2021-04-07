/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Clientes;

import Datos.DDetalleFactura;
import Datos.DGestionarServicios;
import Datos.DServiciodelCliente;
import Logica.LCaja;
import Logica.LFactura;
import Logica.LGestionarServicios;
import Logica.LPagos;
import java.awt.Color;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author josug
 */
public class frmRevisarFacturas extends javax.swing.JFrame {

    public int xx;
    public int xy;
    public static int idFactura;
    public static int idCliente;
    public static String nombreCliente;
    public static String nroCedula;
    public static String fechaVencimiento;
    public static String servicioCliente;
    public static double total;
    LCaja lc = new LCaja();
    public static double montoD;
    public static String estado;

    Calendar fecha_actual = new GregorianCalendar();

    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
    
    LGestionarServicios lgs = new LGestionarServicios();

    /**
     * Creates new form frmPrincipalSolicitudesNuevo
     */
    public frmRevisarFacturas() {
        initComponents();
        mostrarBuscar("");
        ordenarTamaños();
        this.setLocationRelativeTo(null);
        jdFecha2.setCalendar(fecha_actual);
        revisarFechas();
        ponerMasMulta();
        jdFecha2.setVisible(false);
        lblrevertir.setVisible(false);
    }

    public void mostrarBuscar(String buscar) {
        try {
            DefaultTableModel miModelo;
            LPagos log = new LPagos();
            miModelo = log.mostrarPagos(buscar);
            tblFacturas.setModel(miModelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void mostrarNoPagado(String buscar) {
        try {
            DefaultTableModel miModelo;
            LPagos log = new LPagos();
            miModelo = log.mostrarFacturaNoPagada(buscar);
            tblFacturas.setModel(miModelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    void revisarFechas() {
        if ("No".equals(frmGestionarServicios.estado)) {
            int dia = jdFecha2.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mes = jdFecha2.getCalendar().get(Calendar.MONTH);
            int año = jdFecha2.getCalendar().get(Calendar.YEAR);
            Date fVencimiento = new Date((año - 1900), mes, dia);

            int[] idFactura = new int[tblFacturas.getRowCount()];
            int[] id = new int[tblFacturas.getRowCount()];
            Date[] fechas = new Date[tblFacturas.getRowCount()];
            String[] nombres = new String[tblFacturas.getRowCount()];
            for (int i = 0; i < tblFacturas.getRowCount(); i++) {
                fechas[i] = Date.valueOf(tblFacturas.getValueAt(i, 5).toString());
                id[i] = Integer.parseInt(tblFacturas.getValueAt(i, 1).toString());
                idFactura[i] = Integer.parseInt(tblFacturas.getValueAt(i, 0).toString());
                nombres[i] = tblFacturas.getValueAt(i, 2).toString();
                System.out.println(fechas[i]);
                System.out.println(fVencimiento);
                if (fVencimiento.equals(fechas[i])) {
                    System.out.println("El cliente " + nombres[i] + " está atrasado");
                    DServiciodelCliente dgs = new DServiciodelCliente();
                    LGestionarServicios fun = new LGestionarServicios();
                    DDetalleFactura ddf = new DDetalleFactura();
                    LFactura fun1 = new LFactura();

                    dgs.setIdServiciodelCliente(id[i]);
                    fun.actualizarEstados(dgs);

                    ddf.setIdDetalleFactura(idFactura[i]);
                    fun1.actualizarFactura(ddf);
                    
                    frmGestionarServicios.estado = "Si";
                    System.out.println(frmGestionarServicios.estado);
                } else {
                    System.out.println("Todavia puede pagarse");
                }
            }
        } else {
            System.out.println("YA SE HIZO");
        }
    }
    
    public void ponerMasMulta() {
        if ("No".equals(frmGestionarServicios.multapuesta)) {
            String estadoV = null;

            //optiene la fecha del jdatechooser
            int dia = jdFecha2.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mes = jdFecha2.getCalendar().get(Calendar.MONTH);
            int año = jdFecha2.getCalendar().get(Calendar.YEAR);
            Date fVencimiento = new Date((año - 1900), mes, dia);
            System.out.println("hola " + fVencimiento);

            //crea los arrays para el id del cliente y la fecha de vencimiento de sus servicios
            int[] id = new int[tblFacturas.getRowCount()];
            String[] estado = new String[tblFacturas.getRowCount()];
            int[] idDetalle = new int[tblFacturas.getRowCount()];
            String[] estadoMulta = new String[tblFacturas.getRowCount()];
            for (int i = 0; i < tblFacturas.getRowCount(); i++) {
                id[i] = Integer.parseInt(tblFacturas.getValueAt(i, 1).toString());
                estado[i] = lgs.traerEstadoFactura(id[i]);
                idDetalle[i] = lgs.traerId(id[i]);
                estadoMulta[i] = lgs.traerEstadoMulta(id[i]);
                System.out.println("Estado Factura: " + estadoMulta[i]);
                System.out.println("Estado Factura: " + estado[i]);
                //verifica cada fecha de vencimiento con la fecha real del dia
                //y si se cumple realiza el corte del servicio si este todavia no se ha pagado
                if ("Factura Sin Pagar".equals(estado[i]) && "No".equals(estadoMulta)) {
                    int diaNuevo = 23;
                    if (diaNuevo < dia) {
                        DServiciodelCliente dsc = new DServiciodelCliente();
                        LGestionarServicios fun = new LGestionarServicios();
                        System.out.println("Cliente " + id[i]);
                        double sobrecargo = lgs.traerSobrecargo(id[i]);
                        System.out.println(sobrecargo);
                        double masSobrecargo = lgs.traerPrecio(idDetalle[i]);
                        System.out.println(masSobrecargo);
                        double total = sobrecargo + masSobrecargo;
                        System.out.println(total);
                        dsc.setMulta(total);
                        dsc.setIdServiciodelCliente(id[i]);
                        fun.actualizarMulta(dsc);
                        String estadoM = "Si";
                        dsc.setEstadoMulta(estadoM);
                        fun.estadoMulta(dsc);
                        frmGestionarServicios.multapuesta = "Si";
                        System.out.println(frmGestionarServicios.multapuesta);
                    }
                } else {
                    System.out.println("Multa ya puesta por este mes");
                }
            }
        } else {
            System.out.println("La multa ya esta puesta");
        }
    }
    
    public void ordenarTamaños() {
        TableColumnModel Modelo = tblFacturas.getColumnModel();
        Modelo.getColumn(0).setPreferredWidth(10);
        Modelo.getColumn(0).setResizable(false);
        Modelo.getColumn(1).setPreferredWidth(10);
        Modelo.getColumn(1).setResizable(false);
        Modelo.getColumn(2).setPreferredWidth(90);
        Modelo.getColumn(2).setResizable(false);
        Modelo.getColumn(3).setPreferredWidth(35);
        Modelo.getColumn(3).setResizable(false);
        Modelo.getColumn(4).setPreferredWidth(40);
        Modelo.getColumn(4).setResizable(false);
        Modelo.getColumn(5).setPreferredWidth(45);
        Modelo.getColumn(5).setResizable(false);
        Modelo.getColumn(6).setPreferredWidth(80);
        Modelo.getColumn(6).setResizable(false);
        Modelo.getColumn(7).setPreferredWidth(30);
        Modelo.getColumn(7).setResizable(false);
        Modelo.getColumn(8).setPreferredWidth(55);
        Modelo.getColumn(8).setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the
     * form.+ WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnNuevaSolicitud = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnRevisarFacturas = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnEliminarSolicitud = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnVolver = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        btnNuevaSolicitud1 = new javax.swing.JPanel();
        btnNuevaSolicitud2 = new javax.swing.JPanel();
        btnNuevaSolicitud3 = new javax.swing.JPanel();
        btnNuevaSolicitud4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        btnEditarSolicitud = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        btnNuevaSolicitud6 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextField2 = new javax.swing.JTextField();
        jdFecha2 = new com.toedter.calendar.JDateChooser();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        btnReportes = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblFacturas = new javax.swing.JTable();
        mover = new javax.swing.JPanel();
        btnSalir = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnMini = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        btnMostrarServiciosActivos = new javax.swing.JPanel();
        lblrevertir = new javax.swing.JLabel();
        lblAceptado = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNuevaSolicitud.setBackground(new java.awt.Color(102, 0, 0));
        btnNuevaSolicitud.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaSolicitud.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaSolicitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnNuevaSolicitudFocusGained(evt);
            }
        });
        btnNuevaSolicitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitudMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitudMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitudMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitudMouseEntered(evt);
            }
        });
        btnNuevaSolicitud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_plus_math_30px_1.png"))); // NOI18N
        btnNuevaSolicitud.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 31, 36));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 204));
        jLabel13.setText("Realizar Pago");
        btnNuevaSolicitud.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 12, -1, -1));
        btnNuevaSolicitud.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 38, 167, 10));

        jPanel2.add(btnNuevaSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 240, 50));

        btnRevisarFacturas.setBackground(new java.awt.Color(102, 0, 0));
        btnRevisarFacturas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRevisarFacturas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRevisarFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRevisarFacturasMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnRevisarFacturasMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnRevisarFacturasMouseEntered(evt);
            }
        });
        btnRevisarFacturas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_edit_30px.png"))); // NOI18N
        btnRevisarFacturas.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 31, 36));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Revisar Facturas");
        btnRevisarFacturas.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 12, -1, -1));
        btnRevisarFacturas.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 38, 167, 10));

        jPanel2.add(btnRevisarFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 240, 50));

        btnEliminarSolicitud.setBackground(new java.awt.Color(102, 0, 0));
        btnEliminarSolicitud.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarSolicitud.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminarSolicitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEliminarSolicitudMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarSolicitudMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarSolicitudMouseEntered(evt);
            }
        });
        btnEliminarSolicitud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_trash_30px.png"))); // NOI18N
        btnEliminarSolicitud.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 31, 36));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Eliminar Factura");
        btnEliminarSolicitud.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 12, -1, -1));
        btnEliminarSolicitud.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 38, 167, 10));

        jPanel2.add(btnEliminarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 240, 50));

        btnVolver.setBackground(new java.awt.Color(102, 0, 0));
        btnVolver.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVolver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVolver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnVolverMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVolverMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVolverMouseEntered(evt);
            }
        });
        btnVolver.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_back_30px.png"))); // NOI18N
        btnVolver.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 31, 36));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("Volver");
        btnVolver.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 12, -1, -1));
        btnVolver.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 38, 179, 10));

        jPanel2.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 240, 50));

        btnNuevaSolicitud1.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevaSolicitud1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaSolicitud1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaSolicitud1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnNuevaSolicitud1FocusGained(evt);
            }
        });
        btnNuevaSolicitud1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud1MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud1MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud1MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout btnNuevaSolicitud1Layout = new javax.swing.GroupLayout(btnNuevaSolicitud1);
        btnNuevaSolicitud1.setLayout(btnNuevaSolicitud1Layout);
        btnNuevaSolicitud1Layout.setHorizontalGroup(
            btnNuevaSolicitud1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        btnNuevaSolicitud1Layout.setVerticalGroup(
            btnNuevaSolicitud1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnNuevaSolicitud1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 240, 50));

        btnNuevaSolicitud2.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevaSolicitud2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaSolicitud2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaSolicitud2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnNuevaSolicitud2FocusGained(evt);
            }
        });
        btnNuevaSolicitud2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud2MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud2MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud2MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout btnNuevaSolicitud2Layout = new javax.swing.GroupLayout(btnNuevaSolicitud2);
        btnNuevaSolicitud2.setLayout(btnNuevaSolicitud2Layout);
        btnNuevaSolicitud2Layout.setHorizontalGroup(
            btnNuevaSolicitud2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        btnNuevaSolicitud2Layout.setVerticalGroup(
            btnNuevaSolicitud2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnNuevaSolicitud2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 240, 50));

        btnNuevaSolicitud3.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevaSolicitud3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaSolicitud3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaSolicitud3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnNuevaSolicitud3FocusGained(evt);
            }
        });
        btnNuevaSolicitud3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud3MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud3MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud3MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout btnNuevaSolicitud3Layout = new javax.swing.GroupLayout(btnNuevaSolicitud3);
        btnNuevaSolicitud3.setLayout(btnNuevaSolicitud3Layout);
        btnNuevaSolicitud3Layout.setHorizontalGroup(
            btnNuevaSolicitud3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        btnNuevaSolicitud3Layout.setVerticalGroup(
            btnNuevaSolicitud3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnNuevaSolicitud3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 240, 50));

        btnNuevaSolicitud4.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevaSolicitud4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaSolicitud4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaSolicitud4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnNuevaSolicitud4FocusGained(evt);
            }
        });
        btnNuevaSolicitud4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud4MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud4MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud4MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout btnNuevaSolicitud4Layout = new javax.swing.GroupLayout(btnNuevaSolicitud4);
        btnNuevaSolicitud4.setLayout(btnNuevaSolicitud4Layout);
        btnNuevaSolicitud4Layout.setHorizontalGroup(
            btnNuevaSolicitud4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        btnNuevaSolicitud4Layout.setVerticalGroup(
            btnNuevaSolicitud4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnNuevaSolicitud4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 240, 50));

        jLabel21.setFont(new java.awt.Font("Bauhaus 93", 1, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 204, 204));
        jLabel21.setText("INTERNET");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 30, 50, -1));

        jLabel17.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("JOTZPOT");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel20.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 204, 204));
        jLabel20.setText("SERVINET");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 550, -1, -1));
        jPanel2.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 570, 40, 20));
        jPanel2.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, 40, 20));
        jPanel2.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 40, 10));

        jLabel23.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("FACTURAS DE CLIENTES");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 270, 50));

        btnEditarSolicitud.setBackground(new java.awt.Color(102, 0, 0));
        btnEditarSolicitud.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarSolicitud.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditarSolicitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEditarSolicitudMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarSolicitudMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarSolicitudMouseEntered(evt);
            }
        });
        btnEditarSolicitud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_edit_30px.png"))); // NOI18N
        btnEditarSolicitud.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 31, 36));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("Editar Factura");
        btnEditarSolicitud.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 12, -1, -1));
        btnEditarSolicitud.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 38, 167, 10));

        jPanel2.add(btnEditarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 240, 50));

        btnNuevaSolicitud6.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevaSolicitud6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaSolicitud6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaSolicitud6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnNuevaSolicitud6FocusGained(evt);
            }
        });
        btnNuevaSolicitud6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud6MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud6MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud6MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud6MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout btnNuevaSolicitud6Layout = new javax.swing.GroupLayout(btnNuevaSolicitud6);
        btnNuevaSolicitud6.setLayout(btnNuevaSolicitud6Layout);
        btnNuevaSolicitud6Layout.setHorizontalGroup(
            btnNuevaSolicitud6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        btnNuevaSolicitud6Layout.setVerticalGroup(
            btnNuevaSolicitud6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnNuevaSolicitud6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 240, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 590));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscar.setBackground(new java.awt.Color(102, 0, 0));
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarMouseEntered(evt);
            }
        });
        btnBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_search_30px.png"))); // NOI18N
        btnBuscar.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jPanel3.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 50, 50));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 50, 50));

        jTextField2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 32, 160, -1));

        jdFecha2.setDateFormatString("yyyy/MM/dd ");
        jPanel3.add(jdFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 130, 20));
        jPanel3.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 180, 10));
        jPanel3.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 180, 10));
        jPanel3.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 28, 180, 10));
        jPanel3.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 18, 180, 10));

        btnReportes.setBackground(new java.awt.Color(102, 0, 0));
        btnReportes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReportesMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReportesMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReportesMouseEntered(evt);
            }
        });
        btnReportes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_print_30px.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel11MousePressed(evt);
            }
        });
        btnReportes.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 30, 38));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(204, 204, 204));
        jLabel25.setText("Imprimir Reporte");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel25MousePressed(evt);
            }
        });
        btnReportes.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jPanel3.add(btnReportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 170, 40));

        jPanel11.setBackground(new java.awt.Color(0, 0, 0));
        jPanel11.setEnabled(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 170, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 930, 80));

        jScrollPane1.setBorder(null);

        tblFacturas.setAutoCreateRowSorter(true);
        tblFacturas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblFacturas.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblFacturas.setGridColor(new java.awt.Color(255, 255, 255));
        tblFacturas.setRowHeight(25);
        tblFacturas.setSelectionBackground(new java.awt.Color(102, 0, 0));
        tblFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblFacturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblFacturas);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 790, 370));

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

        btnSalir.setBackground(new java.awt.Color(102, 0, 0));
        btnSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSalirMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSalirMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSalirMouseEntered(evt);
            }
        });
        btnSalir.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_close_window_20px.png"))); // NOI18N
        btnSalir.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 20, 20));

        mover.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 10, 30, 30));

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

        mover.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, 30, 30));

        btnMini.setBackground(new java.awt.Color(102, 0, 0));
        btnMini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMiniMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMiniMouseEntered(evt);
            }
        });
        btnMini.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_minimize_window_20px.png"))); // NOI18N
        btnMini.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 20, 20));

        mover.add(btnMini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 30, 30));

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

        mover.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 30, 30));

        jPanel1.add(mover, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 60));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 10, 200));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 10, 200));

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 10, 200));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 10, 200));

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 10, 200));

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 10, 200));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 120, 110, 470));

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnMostrarServiciosActivos.setBackground(new java.awt.Color(102, 0, 0));
        btnMostrarServiciosActivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnMostrarServiciosActivosMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMostrarServiciosActivosMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMostrarServiciosActivosMouseEntered(evt);
            }
        });
        btnMostrarServiciosActivos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblrevertir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblrevertir.setForeground(new java.awt.Color(204, 204, 204));
        lblrevertir.setText("REVERTIR");
        btnMostrarServiciosActivos.add(lblrevertir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        lblAceptado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAceptado.setForeground(new java.awt.Color(204, 204, 204));
        lblAceptado.setText("ESTADO ACEPTADO");
        btnMostrarServiciosActivos.add(lblAceptado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel10.add(btnMostrarServiciosActivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 140, 30));

        jPanel9.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 140, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel10.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 140, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MOSTRAR:");
        jPanel10.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 530, 820, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevaSolicitudMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudMousePressed
        frmGenerarPago form = new frmGenerarPago();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnNuevaSolicitudMousePressed

    private void btnRevisarFacturasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRevisarFacturasMousePressed
        String[] nombres = new String[tblFacturas.getRowCount()];
        String[] estados = new String[tblFacturas.getRowCount()];
        for (int i = 0; i < tblFacturas.getRowCount(); i++) {
            estados[i] = tblFacturas.getValueAt(i, 8).toString();
            nombres[i] = tblFacturas.getValueAt(i, 2).toString();
            if ("Atrasado".equals(estados[i])) {
                System.out.println("El cliente " + nombres[i] + " está " + estados[i]);
            }
        }
        revisarFechas();
    }//GEN-LAST:event_btnRevisarFacturasMousePressed

    private void btnEliminarSolicitudMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSolicitudMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarSolicitudMousePressed

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

    private void btnNuevaSolicitudMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudMouseReleased

    }//GEN-LAST:event_btnNuevaSolicitudMouseReleased

    private void btnNuevaSolicitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudFocusGained

    }//GEN-LAST:event_btnNuevaSolicitudFocusGained

    private void btnNuevaSolicitudMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudMouseEntered
        setColor(btnNuevaSolicitud);
    }//GEN-LAST:event_btnNuevaSolicitudMouseEntered

    private void btnNuevaSolicitudMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudMouseExited
        resetColor(btnNuevaSolicitud);
    }//GEN-LAST:event_btnNuevaSolicitudMouseExited

    private void btnVolverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMousePressed
        frmPrincipalMenu form = new frmPrincipalMenu();
        form.setVisible(true);
        form.toFront();
        this.dispose();
    }//GEN-LAST:event_btnVolverMousePressed

    private void tblFacturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblFacturasMouseClicked
        int fila = tblFacturas.rowAtPoint(evt.getPoint());
        idFactura = Integer.parseInt(tblFacturas.getValueAt(fila, 0).toString());
        idCliente = Integer.parseInt(tblFacturas.getValueAt(fila, 1).toString());
        nombreCliente = tblFacturas.getValueAt(fila, 2).toString();
        nroCedula = tblFacturas.getValueAt(fila, 3).toString();
        fechaVencimiento = tblFacturas.getValueAt(fila, 5).toString();
        servicioCliente = tblFacturas.getValueAt(fila, 6).toString();
        total = Double.parseDouble(tblFacturas.getValueAt(fila, 7).toString());


    }//GEN-LAST:event_tblFacturasMouseClicked

    private void btnNuevaSolicitud1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud1FocusGained

    private void btnNuevaSolicitud1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud1MouseEntered

    private void btnNuevaSolicitud1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud1MouseExited

    private void btnNuevaSolicitud1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud1MousePressed

    private void btnNuevaSolicitud1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud1MouseReleased

    private void btnNuevaSolicitud2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2FocusGained

    private void btnNuevaSolicitud2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2MouseEntered

    private void btnNuevaSolicitud2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2MouseExited

    private void btnNuevaSolicitud2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2MousePressed

    private void btnNuevaSolicitud2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2MouseReleased

    private void btnNuevaSolicitud3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3FocusGained

    private void btnNuevaSolicitud3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3MouseEntered

    private void btnNuevaSolicitud3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3MouseExited

    private void btnNuevaSolicitud3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3MousePressed

    private void btnNuevaSolicitud3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3MouseReleased

    private void btnNuevaSolicitud4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud4FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud4FocusGained

    private void btnNuevaSolicitud4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud4MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud4MouseEntered

    private void btnNuevaSolicitud4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud4MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud4MouseExited

    private void btnNuevaSolicitud4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud4MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud4MousePressed

    private void btnNuevaSolicitud4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud4MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud4MouseReleased

    private void btnMostrarServiciosActivosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarServiciosActivosMousePressed

        if (lblAceptado.isVisible()) {
            lblAceptado.setVisible(false);
            lblrevertir.setVisible(true);
            mostrarNoPagado("");
            ordenarTamaños();
        } else {
            lblAceptado.setVisible(true);
            lblrevertir.setVisible(false);
            mostrarBuscar("");
            ordenarTamaños();
        }
    }//GEN-LAST:event_btnMostrarServiciosActivosMousePressed

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        if ("Abierto".equals(estado)) {
            JOptionPane.showMessageDialog(null, "La caja está abierta");
        } else {
            this.dispose();
        }
    }//GEN-LAST:event_btnSalirMousePressed

    private void btnNuevaSolicitud6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud6FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud6FocusGained

    private void btnNuevaSolicitud6MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud6MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud6MousePressed

    private void btnNuevaSolicitud6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud6MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud6MouseReleased

    private void btnNuevaSolicitud6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud6MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud6MouseExited

    private void btnNuevaSolicitud6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud6MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud6MouseEntered

    private void btnEditarSolicitudMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarSolicitudMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarSolicitudMousePressed

    private void jLabel11MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MousePressed
        frmElegirReporteServicios form = new frmElegirReporteServicios();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_jLabel11MousePressed

    private void jLabel25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel25MousePressed

    private void btnReportesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMousePressed
        frmElegirReporteFacturas form = new frmElegirReporteFacturas();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnReportesMousePressed

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
        resetColor(btnReportes);
    }//GEN-LAST:event_btnReportesMouseExited

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
        setColor(btnReportes);
    }//GEN-LAST:event_btnReportesMouseEntered

    private void btnRevisarFacturasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRevisarFacturasMouseEntered
        setColor(btnRevisarFacturas);
    }//GEN-LAST:event_btnRevisarFacturasMouseEntered

    private void btnRevisarFacturasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRevisarFacturasMouseExited
        resetColor(btnRevisarFacturas);
    }//GEN-LAST:event_btnRevisarFacturasMouseExited

    private void btnEditarSolicitudMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarSolicitudMouseEntered
        setColor(btnEditarSolicitud);
    }//GEN-LAST:event_btnEditarSolicitudMouseEntered

    private void btnEditarSolicitudMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarSolicitudMouseExited
        resetColor(btnEditarSolicitud);
    }//GEN-LAST:event_btnEditarSolicitudMouseExited

    private void btnEliminarSolicitudMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSolicitudMouseEntered
        setColor(btnEliminarSolicitud);
    }//GEN-LAST:event_btnEliminarSolicitudMouseEntered

    private void btnEliminarSolicitudMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSolicitudMouseExited
        resetColor(btnEliminarSolicitud);
    }//GEN-LAST:event_btnEliminarSolicitudMouseExited

    private void btnVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseEntered
        setColor(btnVolver);
    }//GEN-LAST:event_btnVolverMouseEntered

    private void btnVolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseExited
        resetColor(btnVolver);
    }//GEN-LAST:event_btnVolverMouseExited

    private void btnBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseEntered
        setColor(btnBuscar);
    }//GEN-LAST:event_btnBuscarMouseEntered

    private void btnBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseExited
        resetColor(btnBuscar);
    }//GEN-LAST:event_btnBuscarMouseExited

    private void btnMostrarServiciosActivosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarServiciosActivosMouseEntered
        setColor(btnMostrarServiciosActivos);
    }//GEN-LAST:event_btnMostrarServiciosActivosMouseEntered

    private void btnMostrarServiciosActivosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarServiciosActivosMouseExited
        resetColor(btnMostrarServiciosActivos);
    }//GEN-LAST:event_btnMostrarServiciosActivosMouseExited

    private void btnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseExited
        resetColor(btnSalir);
    }//GEN-LAST:event_btnSalirMouseExited

    private void btnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseEntered
        setColor(btnSalir);
    }//GEN-LAST:event_btnSalirMouseEntered

    private void btnMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMouseEntered
        setColor(btnMini);
    }//GEN-LAST:event_btnMiniMouseEntered

    private void btnMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMouseExited
        resetColor(btnMini);
    }//GEN-LAST:event_btnMiniMouseExited

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
            java.util.logging.Logger.getLogger(frmRevisarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmRevisarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmRevisarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmRevisarFacturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmRevisarFacturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnBuscar;
    private javax.swing.JPanel btnEditarSolicitud;
    private javax.swing.JPanel btnEliminarSolicitud;
    private javax.swing.JPanel btnMini;
    private javax.swing.JPanel btnMostrarServiciosActivos;
    private javax.swing.JPanel btnNuevaSolicitud;
    private javax.swing.JPanel btnNuevaSolicitud1;
    private javax.swing.JPanel btnNuevaSolicitud2;
    private javax.swing.JPanel btnNuevaSolicitud3;
    private javax.swing.JPanel btnNuevaSolicitud4;
    private javax.swing.JPanel btnNuevaSolicitud6;
    private javax.swing.JPanel btnReportes;
    private javax.swing.JPanel btnRevisarFacturas;
    private javax.swing.JPanel btnSalir;
    private javax.swing.JPanel btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField2;
    private com.toedter.calendar.JDateChooser jdFecha2;
    private javax.swing.JLabel lblAceptado;
    private javax.swing.JLabel lblrevertir;
    private javax.swing.JPanel mover;
    private javax.swing.JTable tblFacturas;
    // End of variables declaration//GEN-END:variables
}
