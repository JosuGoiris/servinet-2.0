/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Clientes;

import Datos.DDetalleFactura;
import Datos.DFactura;
import Datos.DServiciodelCliente;
import Logica.ConexionSingleton;
import Logica.LFactura;
import Logica.LGestionarServicios;
import Logica.LSolicitud;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author josug
 */
public final class frmGestionarServicios extends javax.swing.JFrame {

    public int xx;
    public int xy;
    public static int idServiciodelCliente;
    public static int idCliente;
    public static String nombreCliente;
    public static String apellidoCliente;
    public static String nroCedula;
    public static int idServicio;
    public static String nombreServicio;
    public static double precio;
    public static int conexion;
    public static int idConexion;
    public static Date fvencimiento;
    public int num = 0;
    LGestionarServicios lgs = new LGestionarServicios();
    LFactura lf = new LFactura();
    public static String ya = "";
    public static double multa = 0;
    Calendar fecha_actual = new GregorianCalendar();

    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

    public static String multapuesta = null;
    public static String estado = null;

    /**
     * Creates new form frmPrincipalSolicitudesNuevo
     */
    public frmGestionarServicios() {
        initComponents();
        mostrarBuscar("");
        this.setLocationRelativeTo(null);
        ordenarTamaños();
        verificarFecha();
        jdFecha2.setCalendar(fecha_actual);
        jdFecha2.setVisible(false);
        lblrevertir.setVisible(false);
        obtenerFecha();
        ponerMasMulta();
        empezarMes();
    }

    public void empezarMes() {
        int dia = jdFecha2.getCalendar().get(Calendar.DAY_OF_MONTH);
        int diaPrimero = 01;
        if (diaPrimero == dia || dia > diaPrimero != dia > 23) {
            int[] id = new int[tblGestion.getRowCount()];
            String[] estadoMulta = new String[tblGestion.getRowCount()];
            for (int i = 0; i < tblGestion.getRowCount(); i++) {
                id[i] = Integer.parseInt(tblGestion.getValueAt(i, 0).toString());
                estadoMulta[i] = lgs.traerEstadoMulta(id[i]);
                System.out.println(" Factura: " + estadoMulta[i]);
                //verifica cada fecha de vencimiento con la fecha real del dia
                //y si se cumple realiza el corte del servicio si este todavia no se ha pagado
                if ("SI".equals(estadoMulta[i])) {
                    DServiciodelCliente dsc = new DServiciodelCliente();
                    LGestionarServicios fun = new LGestionarServicios();
                    System.out.println("Cliente " + id[i]);
                    String estado = "NO";
                    dsc.setEstadoMulta(estado);
                    dsc.setIdServiciodelCliente(id[i]);
                    fun.estadoMulta(dsc);
                } else {
                    System.out.println("La Multa ya esta cambiada");
                }
            }
        } else {
            System.out.println("Se tiene que aplicar una multa");
        }
    }

    public void obtenerEstados() {
        String estado = "ATRASADO";
        String[] nombres = new String[tblGestion.getRowCount()];
        String[] estados = new String[tblGestion.getRowCount()];
        for (int i = 0; i < tblGestion.getRowCount(); i++) {
            estados[i] = tblGestion.getValueAt(i, 9).toString();
            nombres[i] = tblGestion.getValueAt(i, 1).toString();
            if ("ATRASADO".equals(estados[i])) {
                System.out.println("El cliente " + nombres[i] + " está " + estados[i]);
            }
        }
    }

    public void obtenerFecha() {
        int[] ids = new int[tblGestion.getRowCount()];
        String[] estadoMulta = new String[tblGestion.getRowCount()];
        for (int i = 0; i < tblGestion.getRowCount(); i++) {
            ids[i] = Integer.parseInt(tblGestion.getValueAt(i, 0).toString());
            estadoMulta[i] = lgs.traerEstadoMulta(ids[i]);
            System.out.println(" Factura: " + estadoMulta[i]);
            
            if ("NO".equals(estadoMulta[i])) {
                //optiene la fecha del jdatechooser
                int dia = jdFecha2.getCalendar().get(Calendar.DAY_OF_MONTH);
                int mes = jdFecha2.getCalendar().get(Calendar.MONTH);
                int año = jdFecha2.getCalendar().get(Calendar.YEAR);
                Date fVencimiento = new Date((año - 1900), mes, dia);
                System.out.println("hola " + fVencimiento);

                //crea los arrays para el id del cliente y la fecha de vencimiento de sus servicios
                int[] id = new int[tblGestion.getRowCount()];
                Date[] fechas = new Date[tblGestion.getRowCount()];

                for (int y = 0; y < tblGestion.getRowCount(); y++) {
                    fechas[y] = Date.valueOf(tblGestion.getValueAt(y, 8).toString());
                    id[y] = Integer.parseInt(tblGestion.getValueAt(y, 0).toString());

                    //verifica cada fecha de vencimiento con la fecha real del dia
                    //y si se cumple realiza el corte del servicio si este todavia no se ha pagado
                    if (fVencimiento.equals(fechas[y])) {
                        System.out.println("Funciona " + fVencimiento + " " + fechas[i]);
                        DServiciodelCliente dsc = new DServiciodelCliente();
                        LGestionarServicios fun = new LGestionarServicios();
                        DDetalleFactura ddf = new DDetalleFactura();
                        LFactura fun1 = new LFactura();

                        System.out.println("Cliente " + id[y]);
                        int idFactura = lf.traerIdFactura(id[y]);
                        System.out.println("Factura " + idFactura);
                        dsc.setIdServiciodelCliente(id[y]);
                        fun.actualizarEstados(dsc);

                        ddf.setIdDetalleFactura(idFactura);
                        fun1.actualizarFactura(ddf);
                        estado = "SI";
                        System.out.println(estado);
                    } else {
                        System.out.println("Hola " + fVencimiento + " " + fechas[i]);
                    }
                }
            } else {
                System.out.println("YA SE HIZO");
            }
        }
    }

    public void ponerMasMulta() {
        if ("NO".equals(multapuesta)) {
            String estadoV = null;

            //optiene la fecha del jdatechooser
            int dia = jdFecha2.getCalendar().get(Calendar.DAY_OF_MONTH);
            int mes = jdFecha2.getCalendar().get(Calendar.MONTH);
            int año = jdFecha2.getCalendar().get(Calendar.YEAR);
            Date fVencimiento = new Date((año - 1900), mes, dia);
            System.out.println("hola " + fVencimiento);

            //crea los arrays para el id del cliente y la fecha de vencimiento de sus servicios
            int[] id = new int[tblGestion.getRowCount()];
            String[] estado = new String[tblGestion.getRowCount()];
            int[] idDetalle = new int[tblGestion.getRowCount()];
            String[] estadoMulta = new String[tblGestion.getRowCount()];
            for (int i = 0; i < tblGestion.getRowCount(); i++) {
                id[i] = Integer.parseInt(tblGestion.getValueAt(i, 0).toString());
                estado[i] = lgs.traerEstadoFactura(id[i]);
                idDetalle[i] = lgs.traerId(id[i]);
                estadoMulta[i] = lgs.traerEstadoMulta(id[i]);
                System.out.println("Estado Factura: " + estadoMulta[i]);
                System.out.println("Estado Factura: " + estado[i]);
                //verifica cada fecha de vencimiento con la fecha real del dia
                //y si se cumple realiza el corte del servicio si este todavia no se ha pagado
                if ("FACTURA SIN PAGAR".equals(estado[i]) && "NO".equals(estadoMulta)) {
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
                        String estadoM = "SI";
                        dsc.setEstadoMulta(estadoM);
                        fun.estadoMulta(dsc);
                        multapuesta = "SI";
                        System.out.println(multapuesta);
                    }
                } else {
                    System.out.println("Multa ya puesta por este mes");
                }
            }
        } else {
            System.out.println("La multa ya esta puesta");
        }
    }

    //esta funcion ayuda a verificar si el dia ya es el 24 de cada mes para realizar la carga de facturas
    public String verificarFecha() {
        Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DATE);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int año = fecha.get(Calendar.YEAR);
        System.out.println(año + "/" + mes + "/" + dia);

        int diaRealizar = 24;

        Timer timer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (diaRealizar == dia) {
                    System.out.println("Hoy se realizan las facturas");
                    frmMensajeFactura form = new frmMensajeFactura();
                    form.setVisible(true);
                    form.toFront();
                    ya = "si";
                } else {
                    System.out.println("Todavia no es la fecha");
                    System.out.println(dia);
                }

            }
        });
        timer.setRepeats(false);
        timer.start();

        //innecesario 
        int a = 0;
        String estado = null;
        while (estado == "Pagado") {
            for (int i = 0; i < tblGestion.getRowCount(); i++) {
                estado = tblGestion.getValueAt(i, 9).toString();
                System.out.println(estado);
            }
            return estado;
        }
        return null;

    }

    public void ordenarTamaños() {
        TableColumnModel Modelo = tblGestion.getColumnModel();
        Modelo.getColumn(0).setPreferredWidth(15);
        Modelo.getColumn(0).setResizable(false);
        Modelo.getColumn(1).setPreferredWidth(70);
        Modelo.getColumn(1).setResizable(false);
        Modelo.getColumn(2).setPreferredWidth(70);
        Modelo.getColumn(2).setResizable(false);
        Modelo.getColumn(3).setPreferredWidth(40);
        Modelo.getColumn(3).setResizable(false);
        Modelo.getColumn(4).setPreferredWidth(95);
        Modelo.getColumn(4).setResizable(false);
        Modelo.getColumn(5).setPreferredWidth(40);
        Modelo.getColumn(5).setResizable(false);
        Modelo.getColumn(6).setPreferredWidth(40);
        Modelo.getColumn(6).setResizable(false);
        Modelo.getColumn(7).setPreferredWidth(55);
        Modelo.getColumn(7).setResizable(false);
        Modelo.getColumn(8).setPreferredWidth(55);
        Modelo.getColumn(8).setResizable(false);
        Modelo.getColumn(9).setPreferredWidth(35);
        Modelo.getColumn(9).setResizable(false);
    }

    public void mostrarBuscar(String buscar) {
        try {
            DefaultTableModel miModelo;
            LGestionarServicios log = new LGestionarServicios();
            miModelo = log.mostrarServiciosdelCliente(buscar);
            tblGestion.setModel(miModelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void mostrarBuscarInactivos(String buscar) {
        try {
            DefaultTableModel miModelo;
            LGestionarServicios log = new LGestionarServicios();
            miModelo = log.mostrarServiciosInactivos(buscar);
            tblGestion.setModel(miModelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel24 = new javax.swing.JLabel();
        btnGenerarFacturas = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        btnVolver = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        btnNuevaSolicitud1 = new javax.swing.JPanel();
        btnNuevaSolicitud3 = new javax.swing.JPanel();
        btnNuevaSolicitud5 = new javax.swing.JPanel();
        btnEditarSolicitud = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btnNuevaSolicitud4 = new javax.swing.JPanel();
        btnEliminarSolicitud = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btnNuevaSolicitud7 = new javax.swing.JPanel();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        btnNuevaSolicitud2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        txtBusqueda = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        btnReportes = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jdFecha2 = new com.toedter.calendar.JDateChooser();
        jPanel10 = new javax.swing.JPanel();
        btnMostrarServiciosActivos = new javax.swing.JPanel();
        lblrevertir = new javax.swing.JLabel();
        lblAceptado = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblGestion = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        mover = new javax.swing.JPanel();
        btnSalir = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        btnMini = new javax.swing.JPanel();
        lblMini = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();

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
        btnNuevaSolicitud.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 31, 36));
        btnNuevaSolicitud.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 37, 171, 10));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(204, 204, 204));
        jLabel24.setText("Registrar Nuevo Servicio");
        btnNuevaSolicitud.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 11, -1, -1));

        jPanel2.add(btnNuevaSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 240, 50));

        btnGenerarFacturas.setBackground(new java.awt.Color(102, 0, 0));
        btnGenerarFacturas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnGenerarFacturas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnGenerarFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGenerarFacturasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnGenerarFacturasMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGenerarFacturasMousePressed(evt);
            }
        });
        btnGenerarFacturas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_bill_30px_4.png"))); // NOI18N
        btnGenerarFacturas.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 31, 36));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 204, 204));
        jLabel16.setText("Generar Facturas");
        btnGenerarFacturas.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 11, -1, -1));
        btnGenerarFacturas.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 37, 171, 10));

        jPanel2.add(btnGenerarFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 240, 50));

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
        btnVolver.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 31, 36));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 204, 204));
        jLabel18.setText("Volver");
        btnVolver.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 11, -1, -1));
        btnVolver.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 37, 170, 10));

        jPanel2.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 240, 50));

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

        jPanel2.add(btnNuevaSolicitud1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 240, 50));

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

        jPanel2.add(btnNuevaSolicitud3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 240, 50));

        btnNuevaSolicitud5.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevaSolicitud5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaSolicitud5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaSolicitud5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnNuevaSolicitud5FocusGained(evt);
            }
        });
        btnNuevaSolicitud5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud5MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud5MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud5MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud5MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout btnNuevaSolicitud5Layout = new javax.swing.GroupLayout(btnNuevaSolicitud5);
        btnNuevaSolicitud5.setLayout(btnNuevaSolicitud5Layout);
        btnNuevaSolicitud5Layout.setHorizontalGroup(
            btnNuevaSolicitud5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        btnNuevaSolicitud5Layout.setVerticalGroup(
            btnNuevaSolicitud5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnNuevaSolicitud5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 240, 50));

        btnEditarSolicitud.setBackground(new java.awt.Color(102, 0, 0));
        btnEditarSolicitud.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarSolicitud.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditarSolicitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnEditarSolicitudFocusGained(evt);
            }
        });
        btnEditarSolicitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEditarSolicitudMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEditarSolicitudMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEditarSolicitudMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarSolicitudMouseEntered(evt);
            }
        });
        btnEditarSolicitud.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_edit_30px.png"))); // NOI18N
        btnEditarSolicitud.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 31, 36));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(204, 204, 204));
        jLabel14.setText("Editar Servicio");
        btnEditarSolicitud.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 11, -1, -1));
        btnEditarSolicitud.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 37, 171, 10));

        jPanel2.add(btnEditarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 240, 50));

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

        jPanel2.add(btnNuevaSolicitud4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 240, 50));

        btnEliminarSolicitud.setBackground(new java.awt.Color(102, 0, 0));
        btnEliminarSolicitud.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarSolicitud.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminarSolicitud.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnEliminarSolicitudFocusGained(evt);
            }
        });
        btnEliminarSolicitud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEliminarSolicitudMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnEliminarSolicitudMouseReleased(evt);
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
        btnEliminarSolicitud.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 36));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 204));
        jLabel15.setText("Eliminar Servicio");
        btnEliminarSolicitud.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 11, -1, -1));
        btnEliminarSolicitud.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 37, 172, 10));

        jPanel2.add(btnEliminarSolicitud, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 240, 50));

        btnNuevaSolicitud7.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevaSolicitud7.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaSolicitud7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaSolicitud7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnNuevaSolicitud7FocusGained(evt);
            }
        });
        btnNuevaSolicitud7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud7MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud7MouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud7MouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitud7MouseEntered(evt);
            }
        });

        javax.swing.GroupLayout btnNuevaSolicitud7Layout = new javax.swing.GroupLayout(btnNuevaSolicitud7);
        btnNuevaSolicitud7.setLayout(btnNuevaSolicitud7Layout);
        btnNuevaSolicitud7Layout.setHorizontalGroup(
            btnNuevaSolicitud7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        btnNuevaSolicitud7Layout.setVerticalGroup(
            btnNuevaSolicitud7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnNuevaSolicitud7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 240, 50));
        jPanel2.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 580, 40, 20));
        jPanel2.add(jSeparator15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, 40, 20));
        jPanel2.add(jSeparator14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 40, 10));

        jLabel20.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(204, 204, 204));
        jLabel20.setText("SERVINET");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, -1, -1));

        jLabel17.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("JOTZPOT");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel21.setFont(new java.awt.Font("Bauhaus 93", 1, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 204, 204));
        jLabel21.setText("INTERNET");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(39, 30, 50, -1));

        jLabel19.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(204, 204, 204));
        jLabel19.setText("CLIENTES");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 120, 50));

        jLabel22.setFont(new java.awt.Font("Bauhaus 93", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(204, 204, 204));
        jLabel22.setText("SERVICIOS DE LOS ");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 240, 50));

        btnActualizar.setBackground(new java.awt.Color(102, 0, 0));
        btnActualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnActualizar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnActualizarMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizarMouseEntered(evt);
            }
        });
        btnActualizar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_back_30px.png"))); // NOI18N
        btnActualizar.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 31, 36));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setText("Actualizar");
        btnActualizar.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 11, -1, -1));
        btnActualizar.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 37, 170, 10));

        jPanel2.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 240, 50));

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

        jPanel2.add(btnNuevaSolicitud2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 240, 50));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 290, 610));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtBusqueda.setBackground(new java.awt.Color(204, 204, 204));
        txtBusqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBusquedaKeyTyped(evt);
            }
        });
        jPanel3.add(txtBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 32, 160, -1));

        btnBuscar.setBackground(new java.awt.Color(102, 0, 0));
        btnBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarMouseExited(evt);
            }
        });
        btnBuscar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_search_30px_1.png"))); // NOI18N
        btnBuscar.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

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
        jPanel3.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 180, 10));
        jPanel3.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 180, 10));
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

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_print_30px.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel8MousePressed(evt);
            }
        });
        btnReportes.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 30, 38));

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

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));
        jPanel7.setEnabled(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 170, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 30, 170, 40));

        jdFecha2.setDateFormatString("yyyy-MM-dd ");
        jPanel3.add(jdFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 30, 130, 20));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 930, 80));

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
        lblAceptado.setText("Estado Inactivo");
        btnMostrarServiciosActivos.add(lblAceptado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

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

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 550, 820, 60));

        jScrollPane1.setBorder(null);

        tblGestion.setAutoCreateRowSorter(true);
        tblGestion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        tblGestion.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        tblGestion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblGestion.setGridColor(new java.awt.Color(255, 255, 255));
        tblGestion.setRowHeight(25);
        tblGestion.setSelectionBackground(new java.awt.Color(153, 0, 0));
        tblGestion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGestionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblGestion);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 790, 390));

        jPanel8.setBackground(new java.awt.Color(51, 51, 51));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 10, 200));

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 10, 200));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 10, 200));

        jSeparator11.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, 10, 200));

        jSeparator10.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 10, 200));

        jSeparator12.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel8.add(jSeparator12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 250, 10, 200));

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 140, 110, 470));

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

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_close_window_20px.png"))); // NOI18N
        btnSalir.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 20, 20));

        mover.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 10, 30, 30));

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

        mover.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1150, 20, 30, 30));

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

        mover.add(btnMini, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 10, 30, 30));

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

        mover.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 20, 30, 30));

        jPanel1.add(mover, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1210, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarFacturasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarFacturasMousePressed
        //compara los estados del servicio y facturas para poder realizar una accion
        Calendar fecha = new GregorianCalendar();
        int dia = fecha.get(Calendar.DATE);

        System.out.println(dia);
        int diaRealizar = 23;

        if (diaRealizar < dia) {
            if (tblGestion.getSelectedRows().length > 0) {
                String estado = lgs.traerEstadoFactura(idCliente);
                System.out.println(estado);
                if ("SIN FACTURA".equals(estado)) {
                    frmGenerarFacturas form = new frmGenerarFacturas();
                    //accion = "cargar";
                    form.cargarValores();
                    form.setVisible(true);
                    form.toFront();
                } else if ("CON FACTURA".equals(estado)) {
                    JOptionPane.showMessageDialog(null, "Ya tiene la factura de este mes");
                }
                if ("FACTURA SIN PAGAR".equals(estado)) {
                    frmGenerarFacturas form = new frmGenerarFacturas();
                    //accion = "cargar";
                    form.cargarValoresConSobrecargo();
                    form.setVisible(true);
                    form.toFront();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Se debe de Seleccionar un usuario");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Las facturas se generan desde el 24 de cada mes");
        }
    }//GEN-LAST:event_btnGenerarFacturasMousePressed

    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed

    }//GEN-LAST:event_jPanel1MousePressed

    private void btnVolverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMousePressed
        frmPrincipalMenu form = new frmPrincipalMenu();
        form.setVisible(true);
        form.toFront();
        this.dispose();
    }//GEN-LAST:event_btnVolverMousePressed

    private void tblGestionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGestionMouseClicked
        int fila = tblGestion.rowAtPoint(evt.getPoint());
        idServiciodelCliente = Integer.parseInt(tblGestion.getValueAt(fila, 0).toString());
        idCliente = Integer.parseInt(tblGestion.getValueAt(fila, 0).toString());
        nombreCliente = tblGestion.getValueAt(fila, 1).toString();
        apellidoCliente = tblGestion.getValueAt(fila, 2).toString();
        nroCedula = tblGestion.getValueAt(fila, 3).toString();
        nombreServicio = tblGestion.getValueAt(fila, 4).toString();
        conexion = Integer.parseInt(tblGestion.getValueAt(fila, 5).toString());
        idConexion = lgs.traerId(idCliente);
        System.out.println(idConexion);
        precio = Double.parseDouble(tblGestion.getValueAt(fila, 6).toString());
        fvencimiento = Date.valueOf(tblGestion.getValueAt(fila, 8).toString());
        System.out.println(fvencimiento);
        multa = lgs.traerSobrecargo(idCliente);
        System.out.println(multa);
    }//GEN-LAST:event_tblGestionMouseClicked

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

    private void btnNuevaSolicitud5FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud5FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud5FocusGained

    private void btnNuevaSolicitud5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud5MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud5MouseEntered

    private void btnNuevaSolicitud5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud5MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud5MouseExited

    private void btnNuevaSolicitud5MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud5MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud5MousePressed

    private void btnNuevaSolicitud5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud5MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud5MouseReleased

    private void btnNuevaSolicitud3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3MouseReleased

    private void btnNuevaSolicitud3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3MousePressed

    private void btnNuevaSolicitud3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3MouseExited

    private void btnNuevaSolicitud3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3MouseEntered

    private void btnNuevaSolicitud3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud3FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud3FocusGained

    private void btnNuevaSolicitudMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudMouseReleased

    }//GEN-LAST:event_btnNuevaSolicitudMouseReleased

    private void btnNuevaSolicitudMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudMousePressed
        frmPrincipalSolicitudesNuevo form = new frmPrincipalSolicitudesNuevo();
        form.setVisible(true);
        form.toFront();
        this.dispose();
    }//GEN-LAST:event_btnNuevaSolicitudMousePressed

    private void btnNuevaSolicitudMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudMouseExited
        resetColor(btnNuevaSolicitud);
    }//GEN-LAST:event_btnNuevaSolicitudMouseExited

    private void btnNuevaSolicitudMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudMouseEntered
        setColor(btnNuevaSolicitud);
    }//GEN-LAST:event_btnNuevaSolicitudMouseEntered

    private void btnNuevaSolicitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudFocusGained

    }//GEN-LAST:event_btnNuevaSolicitudFocusGained

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

    private void btnEditarSolicitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnEditarSolicitudFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarSolicitudFocusGained

    private void btnEditarSolicitudMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarSolicitudMouseEntered
        setColor(btnEditarSolicitud);
    }//GEN-LAST:event_btnEditarSolicitudMouseEntered

    private void btnEditarSolicitudMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarSolicitudMouseExited
        resetColor(btnEditarSolicitud);
    }//GEN-LAST:event_btnEditarSolicitudMouseExited

    private void btnEditarSolicitudMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarSolicitudMousePressed
        if (tblGestion.getSelectedRows().length > 0) {
            frmEditarServiciodelCliente form = new frmEditarServiciodelCliente();
            //accion = "modificar";
            form.cargarValores();
            form.setVisible(true);
            form.toFront();
        }
    }//GEN-LAST:event_btnEditarSolicitudMousePressed

    private void btnEditarSolicitudMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarSolicitudMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarSolicitudMouseReleased

    private void btnEliminarSolicitudFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnEliminarSolicitudFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarSolicitudFocusGained

    private void btnEliminarSolicitudMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSolicitudMouseEntered
        setColor(btnEliminarSolicitud);
    }//GEN-LAST:event_btnEliminarSolicitudMouseEntered

    private void btnEliminarSolicitudMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSolicitudMouseExited
        resetColor(btnEliminarSolicitud);
    }//GEN-LAST:event_btnEliminarSolicitudMouseExited

    private void btnEliminarSolicitudMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSolicitudMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarSolicitudMousePressed

    private void btnEliminarSolicitudMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSolicitudMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarSolicitudMouseReleased

    private void btnNuevaSolicitud7FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud7FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud7FocusGained

    private void btnNuevaSolicitud7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud7MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud7MouseEntered

    private void btnNuevaSolicitud7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud7MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud7MouseExited

    private void btnNuevaSolicitud7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud7MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud7MousePressed

    private void btnNuevaSolicitud7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud7MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud7MouseReleased

    private void btnMostrarServiciosActivosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarServiciosActivosMousePressed
        if (lblAceptado.isVisible()) {
            lblAceptado.setVisible(false);
            lblrevertir.setVisible(true);
            mostrarBuscarInactivos("");
            ponerMasMulta();
            empezarMes();
            ordenarTamaños();
        } else {
            lblAceptado.setVisible(true);
            lblrevertir.setVisible(false);
            mostrarBuscar("");
            ponerMasMulta();
            empezarMes();
            ordenarTamaños();
        }

    }//GEN-LAST:event_btnMostrarServiciosActivosMousePressed

    private void jLabel8MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MousePressed
        frmElegirReporteServicios form = new frmElegirReporteServicios();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_jLabel8MousePressed

    private void btnReportesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMousePressed
        frmElegirReporteServicios form = new frmElegirReporteServicios();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnReportesMousePressed

    private void btnGenerarFacturasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarFacturasMouseEntered
        setColor(btnGenerarFacturas);
    }//GEN-LAST:event_btnGenerarFacturasMouseEntered

    private void btnGenerarFacturasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGenerarFacturasMouseExited
        resetColor(btnGenerarFacturas);
    }//GEN-LAST:event_btnGenerarFacturasMouseExited

    private void btnVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseEntered
        setColor(btnVolver);
    }//GEN-LAST:event_btnVolverMouseEntered

    private void btnVolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseExited
        resetColor(btnVolver);
    }//GEN-LAST:event_btnVolverMouseExited

    private void btnMostrarServiciosActivosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarServiciosActivosMouseEntered
        setColor(btnMostrarServiciosActivos);
    }//GEN-LAST:event_btnMostrarServiciosActivosMouseEntered

    private void btnMostrarServiciosActivosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMostrarServiciosActivosMouseExited
        resetColor(btnMostrarServiciosActivos);
    }//GEN-LAST:event_btnMostrarServiciosActivosMouseExited

    private void btnReportesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseEntered
        setColor(btnReportes);
    }//GEN-LAST:event_btnReportesMouseEntered

    private void btnReportesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReportesMouseExited
        resetColor(btnReportes);
    }//GEN-LAST:event_btnReportesMouseExited

    private void btnBuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseEntered
       
    }//GEN-LAST:event_btnBuscarMouseEntered

    private void btnBuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarMouseExited
       
    }//GEN-LAST:event_btnBuscarMouseExited

    private void jLabel25MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel25MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel25MousePressed

    private void btnNuevaSolicitud2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2FocusGained

    private void btnNuevaSolicitud2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2MousePressed

    private void btnNuevaSolicitud2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2MouseReleased

    private void btnNuevaSolicitud2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2MouseExited

    private void btnNuevaSolicitud2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitud2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitud2MouseEntered

    private void btnActualizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMousePressed
        Connection cn = ConexionSingleton.getConnection();
        if (!lblAceptado.isVisible()) {
            mostrarBuscarInactivos("");
            ordenarTamaños();
        } else {
            ConexionSingleton.getConnection();
            mostrarBuscar("");
            ordenarTamaños();
        }
    }//GEN-LAST:event_btnActualizarMousePressed

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        resetColor(btnActualizar);
    }//GEN-LAST:event_btnActualizarMouseExited

    private void btnActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseEntered
        setColor(btnActualizar);
    }//GEN-LAST:event_btnActualizarMouseEntered

    private void btnSalirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseEntered
        setColor(btnSalir);
    }//GEN-LAST:event_btnSalirMouseEntered

    private void btnSalirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMouseExited
        resetColor(btnSalir);
    }//GEN-LAST:event_btnSalirMouseExited

    private void btnSalirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSalirMousePressed
        this.dispose();
    }//GEN-LAST:event_btnSalirMousePressed

    private void btnMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMouseEntered
        setColor(btnMini);
    }//GEN-LAST:event_btnMiniMouseEntered

    private void btnMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMouseExited
        resetColor(btnMini);
    }//GEN-LAST:event_btnMiniMouseExited

    private void moverMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xx, y - xy);
    }//GEN-LAST:event_moverMouseDragged

    private void moverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moverMousePressed
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_moverMousePressed

    private void txtBusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyReleased
        if(!lblAceptado.isVisible()){
            mostrarBuscarInactivos(txtBusqueda.getText());
            ordenarTamaños();
        }else {
            mostrarBuscar(txtBusqueda.getText());
            ordenarTamaños();
        }
    }//GEN-LAST:event_txtBusquedaKeyReleased

    private void lblMiniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMousePressed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_lblMiniMousePressed

    private void btnMiniMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMiniMousePressed
        this.setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btnMiniMousePressed

    private void lblMiniMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMouseEntered
        setColor(btnMini);
    }//GEN-LAST:event_lblMiniMouseEntered

    private void lblMiniMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblMiniMouseExited
        resetColor(btnMini);
    }//GEN-LAST:event_lblMiniMouseExited

    private void txtBusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusquedaKeyTyped
        char validar = evt.getKeyChar();
        if (Character.isLetter(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(rootPane, "Ingresa solo el Nro de Cedula");
            txtBusqueda.setText("");
            txtBusqueda.requestFocus();
        }
    }//GEN-LAST:event_txtBusquedaKeyTyped

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
            java.util.logging.Logger.getLogger(frmGestionarServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmGestionarServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmGestionarServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmGestionarServicios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmGestionarServicios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnActualizar;
    private javax.swing.JPanel btnBuscar;
    private javax.swing.JPanel btnEditarSolicitud;
    private javax.swing.JPanel btnEliminarSolicitud;
    private javax.swing.JPanel btnGenerarFacturas;
    private javax.swing.JPanel btnMini;
    private javax.swing.JPanel btnMostrarServiciosActivos;
    private javax.swing.JPanel btnNuevaSolicitud;
    private javax.swing.JPanel btnNuevaSolicitud1;
    private javax.swing.JPanel btnNuevaSolicitud2;
    private javax.swing.JPanel btnNuevaSolicitud3;
    private javax.swing.JPanel btnNuevaSolicitud4;
    private javax.swing.JPanel btnNuevaSolicitud5;
    private javax.swing.JPanel btnNuevaSolicitud7;
    private javax.swing.JPanel btnReportes;
    private javax.swing.JPanel btnSalir;
    private javax.swing.JPanel btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
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
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private com.toedter.calendar.JDateChooser jdFecha2;
    private javax.swing.JLabel lblAceptado;
    private javax.swing.JLabel lblMini;
    private javax.swing.JLabel lblrevertir;
    private javax.swing.JPanel mover;
    private javax.swing.JTable tblGestion;
    private javax.swing.JTextField txtBusqueda;
    // End of variables declaration//GEN-END:variables
}
