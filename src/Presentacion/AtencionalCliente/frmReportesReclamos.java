/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.AtencionalCliente;

import Presentacion.Cuadrillas.*;
import Presentacion.Clientes.*;
import Logica.ConexionSingleton;
import java.awt.Color;
import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author hecto
 */
public class frmReportesReclamos extends javax.swing.JFrame {

    Connection cn = ConexionSingleton.getConnection();
    /**
     * Creates new form frmElegirReporteSolicitudes
     */
    public frmReportesReclamos() {
        initComponents();
        this.setLocationRelativeTo(null);
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
        jPanel2 = new javax.swing.JPanel();
        btnReporteReclamosPendientes = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblReportePendiente = new javax.swing.JLabel();
        btnReporteReclamosAtendidos = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lblReporteAtendido = new javax.swing.JLabel();
        btnReporteTotal = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lbltReporteTotal = new javax.swing.JLabel();
        btnVolver = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnNuevaSolicitudFondo = new javax.swing.JPanel();
        btnEliminarSolicitudFondo = new javax.swing.JPanel();
        btnVolveralMenuFondo = new javax.swing.JPanel();
        btnEditarSolicitudFondo = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator21 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        bar = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnReporteReclamosPendientes.setBackground(new java.awt.Color(102, 0, 0));
        btnReporteReclamosPendientes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReporteReclamosPendientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReporteReclamosPendientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btnReporteReclamosPendientesFocusGained(evt);
            }
        });
        btnReporteReclamosPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReporteReclamosPendientesMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnReporteReclamosPendientesMouseReleased(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReporteReclamosPendientesMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReporteReclamosPendientesMouseEntered(evt);
            }
        });
        btnReporteReclamosPendientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_plus_math_30px_1.png"))); // NOI18N
        btnReporteReclamosPendientes.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 31, 36));
        btnReporteReclamosPendientes.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 37, 230, 10));

        lblReportePendiente.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblReportePendiente.setForeground(new java.awt.Color(204, 204, 204));
        lblReportePendiente.setText("Reporte de Reclamos Pendientes");
        lblReportePendiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblReportePendienteMousePressed(evt);
            }
        });
        btnReporteReclamosPendientes.add(lblReportePendiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jPanel2.add(btnReporteReclamosPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 300, 50));

        btnReporteReclamosAtendidos.setBackground(new java.awt.Color(102, 0, 0));
        btnReporteReclamosAtendidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReporteReclamosAtendidos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReporteReclamosAtendidos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReporteReclamosAtendidosMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReporteReclamosAtendidosMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReporteReclamosAtendidosMouseEntered(evt);
            }
        });
        btnReporteReclamosAtendidos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_edit_30px.png"))); // NOI18N
        btnReporteReclamosAtendidos.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 31, 36));
        btnReporteReclamosAtendidos.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 37, 230, 10));

        lblReporteAtendido.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblReporteAtendido.setForeground(new java.awt.Color(204, 204, 204));
        lblReporteAtendido.setText("Reporte de Reclamos Atendidos");
        lblReporteAtendido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblReporteAtendidoMousePressed(evt);
            }
        });
        btnReporteReclamosAtendidos.add(lblReporteAtendido, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jPanel2.add(btnReporteReclamosAtendidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, 300, 50));

        btnReporteTotal.setBackground(new java.awt.Color(102, 0, 0));
        btnReporteTotal.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnReporteTotal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnReporteTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnReporteTotalMousePressed(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnReporteTotalMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnReporteTotalMouseEntered(evt);
            }
        });
        btnReporteTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_trash_30px.png"))); // NOI18N
        btnReporteTotal.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 31, 36));
        btnReporteTotal.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 37, 230, 10));

        lbltReporteTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbltReporteTotal.setForeground(new java.awt.Color(204, 204, 204));
        lbltReporteTotal.setText("Reporte de Totales");
        lbltReporteTotal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbltReporteTotalMousePressed(evt);
            }
        });
        btnReporteTotal.add(lbltReporteTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jPanel2.add(btnReporteTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 300, 50));

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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_back_30px.png"))); // NOI18N
        btnVolver.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 30, 30));

        jPanel2.add(btnVolver, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 50, 50));

        jLabel17.setFont(new java.awt.Font("Bauhaus 93", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 204, 204));
        jLabel17.setText("JOTZPOT");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, -1, 30));

        btnNuevaSolicitudFondo.setBackground(new java.awt.Color(0, 0, 0));
        btnNuevaSolicitudFondo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnNuevaSolicitudFondo.setEnabled(false);
        btnNuevaSolicitudFondo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnNuevaSolicitudFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnNuevaSolicitudFondoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout btnNuevaSolicitudFondoLayout = new javax.swing.GroupLayout(btnNuevaSolicitudFondo);
        btnNuevaSolicitudFondo.setLayout(btnNuevaSolicitudFondoLayout);
        btnNuevaSolicitudFondoLayout.setHorizontalGroup(
            btnNuevaSolicitudFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        btnNuevaSolicitudFondoLayout.setVerticalGroup(
            btnNuevaSolicitudFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnNuevaSolicitudFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 300, 50));

        btnEliminarSolicitudFondo.setBackground(new java.awt.Color(0, 0, 0));
        btnEliminarSolicitudFondo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEliminarSolicitudFondo.setEnabled(false);
        btnEliminarSolicitudFondo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminarSolicitudFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEliminarSolicitudFondoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout btnEliminarSolicitudFondoLayout = new javax.swing.GroupLayout(btnEliminarSolicitudFondo);
        btnEliminarSolicitudFondo.setLayout(btnEliminarSolicitudFondoLayout);
        btnEliminarSolicitudFondoLayout.setHorizontalGroup(
            btnEliminarSolicitudFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        btnEliminarSolicitudFondoLayout.setVerticalGroup(
            btnEliminarSolicitudFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnEliminarSolicitudFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 300, 50));

        btnVolveralMenuFondo.setBackground(new java.awt.Color(0, 0, 0));
        btnVolveralMenuFondo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnVolveralMenuFondo.setEnabled(false);
        btnVolveralMenuFondo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnVolveralMenuFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnVolveralMenuFondoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout btnVolveralMenuFondoLayout = new javax.swing.GroupLayout(btnVolveralMenuFondo);
        btnVolveralMenuFondo.setLayout(btnVolveralMenuFondoLayout);
        btnVolveralMenuFondoLayout.setHorizontalGroup(
            btnVolveralMenuFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        btnVolveralMenuFondoLayout.setVerticalGroup(
            btnVolveralMenuFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnVolveralMenuFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 50, 50));

        btnEditarSolicitudFondo.setBackground(new java.awt.Color(0, 0, 0));
        btnEditarSolicitudFondo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnEditarSolicitudFondo.setEnabled(false);
        btnEditarSolicitudFondo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEditarSolicitudFondo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnEditarSolicitudFondoMousePressed(evt);
            }
        });

        javax.swing.GroupLayout btnEditarSolicitudFondoLayout = new javax.swing.GroupLayout(btnEditarSolicitudFondo);
        btnEditarSolicitudFondo.setLayout(btnEditarSolicitudFondoLayout);
        btnEditarSolicitudFondoLayout.setHorizontalGroup(
            btnEditarSolicitudFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        btnEditarSolicitudFondoLayout.setVerticalGroup(
            btnEditarSolicitudFondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.add(btnEditarSolicitudFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 300, 50));

        jLabel21.setFont(new java.awt.Font("Bauhaus 93", 1, 10)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(204, 204, 204));
        jLabel21.setText("INTERNET");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 50, -1));
        jPanel2.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 210, 10));

        jSeparator18.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator18, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 20, 210));

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 20, 210));
        jPanel2.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 370, 10));
        jPanel2.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 250, 10));
        jPanel2.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 250, 10));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 430, 330));

        bar.setBackground(new java.awt.Color(204, 204, 204));
        bar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setBackground(new java.awt.Color(0, 0, 0));
        jLabel19.setFont(new java.awt.Font("Arial Unicode MS", 1, 18)); // NOI18N
        jLabel19.setText("Elige una opción");
        bar.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 210, 40));

        jPanel1.add(bar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteReclamosPendientesFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btnReporteReclamosPendientesFocusGained

    }//GEN-LAST:event_btnReporteReclamosPendientesFocusGained

    private void btnReporteReclamosPendientesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteReclamosPendientesMouseEntered
        setColor(btnReporteReclamosPendientes);
    }//GEN-LAST:event_btnReporteReclamosPendientesMouseEntered

    private void btnReporteReclamosPendientesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteReclamosPendientesMouseExited
        resetColor(btnReporteReclamosPendientes);
    }//GEN-LAST:event_btnReporteReclamosPendientesMouseExited

    private void btnReporteReclamosPendientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteReclamosPendientesMousePressed
        Map p = new HashMap();
        
        p.put("estado", "Pendiente");
        JasperReport report;
        JasperPrint print;
        
        try {
            report = JasperCompileManager.compileReport(new File("")
                    .getAbsolutePath()+"/src/Reportes/reporte_atencionalcliente_reclamo_pendiente.jrxml");
            print = JasperFillManager.fillReport(report, p, cn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Reclamos Pendientes");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnReporteReclamosPendientesMousePressed

    private void btnReporteReclamosPendientesMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteReclamosPendientesMouseReleased

    }//GEN-LAST:event_btnReporteReclamosPendientesMouseReleased

    private void btnReporteReclamosAtendidosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteReclamosAtendidosMouseEntered
        setColor(btnReporteReclamosAtendidos);
    }//GEN-LAST:event_btnReporteReclamosAtendidosMouseEntered

    private void btnReporteReclamosAtendidosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteReclamosAtendidosMouseExited
        resetColor(btnReporteReclamosAtendidos);
    }//GEN-LAST:event_btnReporteReclamosAtendidosMouseExited

    private void btnReporteReclamosAtendidosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteReclamosAtendidosMousePressed
        Map q = new HashMap();
        
        q.put("estado", "Atendido");
        JasperReport report;
        JasperPrint print;
        
        try {
            report = JasperCompileManager.compileReport(new File("")
                    .getAbsolutePath()+"/src/Reportes/reporte_atencionalcliente_reclamo_atendido.jrxml");;
            print = JasperFillManager.fillReport(report, q, cn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Reclamos Atendidos");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnReporteReclamosAtendidosMousePressed

    private void btnReporteTotalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteTotalMouseEntered
        setColor(btnReporteTotal);
    }//GEN-LAST:event_btnReporteTotalMouseEntered

    private void btnReporteTotalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteTotalMouseExited
        resetColor(btnReporteTotal);
    }//GEN-LAST:event_btnReporteTotalMouseExited

    private void btnReporteTotalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnReporteTotalMousePressed
        Map p = new HashMap();
        Map q = new HashMap();
        
        p.put("estado", "Pendiente");
        p.put("estado2", "Atendido");
        
        JasperReport report;
        JasperPrint print;
        
        try {
            report = JasperCompileManager.compileReport(new File("")
                    .getAbsolutePath()+"/src/Reportes/reporte_atencionalcliente_reclamo_total.jrxml");
            print = JasperFillManager.fillReport(report, p, cn);
            JasperViewer view = new JasperViewer(print, false);
            
            view.setTitle("Reporte Total de Reclamos");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnReporteTotalMousePressed

    private void btnVolverMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseEntered
        setColor(btnVolver);
    }//GEN-LAST:event_btnVolverMouseEntered

    private void btnVolverMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMouseExited
        resetColor(btnVolver);
    }//GEN-LAST:event_btnVolverMouseExited

    private void btnVolverMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolverMousePressed
        
    }//GEN-LAST:event_btnVolverMousePressed

    private void btnNuevaSolicitudFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNuevaSolicitudFondoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNuevaSolicitudFondoMousePressed

    private void btnEliminarSolicitudFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarSolicitudFondoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEliminarSolicitudFondoMousePressed

    private void btnVolveralMenuFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnVolveralMenuFondoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVolveralMenuFondoMousePressed

    private void btnEditarSolicitudFondoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarSolicitudFondoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarSolicitudFondoMousePressed

    private void lblReporteAtendidoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReporteAtendidoMousePressed
        Map q = new HashMap();
        
        q.put("estado", "Atendido");
        JasperReport report;
        JasperPrint print;
        
        try {
            report = JasperCompileManager.compileReport(new File("")
                    .getAbsolutePath()+"/src/Reportes/reporte_atencionalcliente_reclamo_atendido.jrxml");;
            print = JasperFillManager.fillReport(report, q, cn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Reclamos Atendidos");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblReporteAtendidoMousePressed

    private void lblReportePendienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblReportePendienteMousePressed
        Map p = new HashMap();
        
        p.put("estado", "Pendiente");
        JasperReport report;
        JasperPrint print;
        
        try {
            report = JasperCompileManager.compileReport(new File("")
                    .getAbsolutePath()+"/src/Reportes/reporte_atencionalcliente_reclamo_pendiente.jrxml");
            print = JasperFillManager.fillReport(report, p, cn);
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("Reporte de Reclamos Pendientes");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lblReportePendienteMousePressed

    private void lbltReporteTotalMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbltReporteTotalMousePressed
        Map p = new HashMap();
        Map q = new HashMap();
        
        p.put("estado", "Pendiente");
        p.put("estado2", "Atendido");
        
        JasperReport report;
        JasperPrint print;
        
        try {
            report = JasperCompileManager.compileReport(new File("")
                    .getAbsolutePath()+"/src/Reportes/reporte_atencionalcliente_reclamo_total.jrxml");
            print = JasperFillManager.fillReport(report, p, cn);
            JasperViewer view = new JasperViewer(print, false);
            
            view.setTitle("Reporte Total de Reclamos");
            view.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_lbltReporteTotalMousePressed

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
            java.util.logging.Logger.getLogger(frmReportesReclamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmReportesReclamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmReportesReclamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmReportesReclamos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new frmReportesReclamos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bar;
    private javax.swing.JPanel btnEditarSolicitudFondo;
    private javax.swing.JPanel btnEliminarSolicitudFondo;
    private javax.swing.JPanel btnNuevaSolicitudFondo;
    private javax.swing.JPanel btnReporteReclamosAtendidos;
    public javax.swing.JPanel btnReporteReclamosPendientes;
    private javax.swing.JPanel btnReporteTotal;
    private javax.swing.JPanel btnVolver;
    private javax.swing.JPanel btnVolveralMenuFondo;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblReporteAtendido;
    private javax.swing.JLabel lblReportePendiente;
    private javax.swing.JLabel lbltReporteTotal;
    // End of variables declaration//GEN-END:variables
}
