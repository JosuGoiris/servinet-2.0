/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion.Mantenimiento;

import Logica.ConexionSingleton;
import Presentacion.AtencionalCliente.frmAtencionalCliente;
import Presentacion.Clientes.frmCargando;
import Presentacion.Clientes.frmPrincipalMenu;
import Presentacion.Cuadrillas.frmMenuCuadrillas;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author josug
 */
public class frmElegirCuadrilla extends javax.swing.JFrame {

    /**
     * Creates new form frmLogin
     */
    Connection cn = ConexionSingleton.getConnection();
    
    public frmElegirCuadrilla() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        frmCargando form = new frmCargando();
        form.dispose();
    }
    
    void acceder(String usuario, String contraseña){
        String cap = "";
        String nombre = "";
        int id = 0;
        String SQL = "select u.idUsuario, u.loginUsuario, u.passwordUsuario, t.tipoUsuario, concat(p.nombres,' ',p.apellidos) as usuario from tblusuario \n"
                + "as u inner join tbltipousuario as t on u.tipousuarioId = t.idTipoUsuario \n"
                + "inner join tblpersona as p on u.personaId = p.idPersona \n"
                + "where u.loginUsuario='"+ usuario + "' && u.passwordUsuario='" + contraseña + "'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            
            while(rs.next()){
                cap = rs.getString("t.tipoUsuario");
                nombre = rs.getString("usuario");
                id = rs.getInt("u.idUsuario");
            }
            if(cap.equals("ADMINISTRADOR")){
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bienvenido");
                frmMenuMantenimiento menu = new frmMenuMantenimiento();
                menu.setVisible(true);
            }
            if(cap.equals("ENCARGADO DE CLIENTES")){
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bienvenido");
                frmPrincipalMenu form = new frmPrincipalMenu();
                form.setVisible(true);
                form.toFront();
                form.lblNombreUsuario.setText(nombre);
            }
            if(cap.equals("ENCARGADO DE CUADRILLAS")){
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bienvenido");
                frmMenuCuadrillas form = new frmMenuCuadrillas();
                form.setVisible(true);
                form.toFront();
            }
            if(cap.equals("ENCARGADO DE ATENCION AL CLIENTE")){
                this.setVisible(false);
                JOptionPane.showMessageDialog(null, "Bienvenido");
                frmAtencionalCliente form = new frmAtencionalCliente();
                form.setVisible(true);
                form.toFront();
            }
        } catch (Exception e) {
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnBuscarCuadrilla = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        btnGuardar2 = new javax.swing.JPanel();
        txtBarrio = new javax.swing.JTextField();
        txtIdBarrio = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBuscarCuadrilla.setBackground(new java.awt.Color(102, 0, 0));
        btnBuscarCuadrilla.setPreferredSize(new java.awt.Dimension(40, 40));
        btnBuscarCuadrilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBuscarCuadrillaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBuscarCuadrillaMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnBuscarCuadrillaMousePressed(evt);
            }
        });
        btnBuscarCuadrilla.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_search_20px.png"))); // NOI18N
        btnBuscarCuadrilla.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(btnBuscarCuadrilla, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 40, 40));

        btnGuardar2.setBackground(new java.awt.Color(0, 0, 0));
        btnGuardar2.setEnabled(false);

        javax.swing.GroupLayout btnGuardar2Layout = new javax.swing.GroupLayout(btnGuardar2);
        btnGuardar2.setLayout(btnGuardar2Layout);
        btnGuardar2Layout.setHorizontalGroup(
            btnGuardar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
        btnGuardar2Layout.setVerticalGroup(
            btnGuardar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        jPanel1.add(btnGuardar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, 40, 40));
        jPanel1.add(txtBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 230, -1));
        jPanel1.add(txtIdBarrio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 53, -1));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("CUADRILLA:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        btnGuardar.setBackground(new java.awt.Color(102, 0, 0));
        btnGuardar.setPreferredSize(new java.awt.Dimension(50, 50));
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnGuardarMousePressed(evt);
            }
        });
        btnGuardar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icons8_save_30px.png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(25, 25));
        btnGuardar.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 30, 30));

        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 50, 50));

        btnGuardar1.setBackground(new java.awt.Color(0, 0, 0));

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

        jPanel1.add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 50, 50));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 240));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarCuadrillaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarCuadrillaMouseEntered
        setColor(btnBuscarCuadrilla);
    }//GEN-LAST:event_btnBuscarCuadrillaMouseEntered

    private void btnBuscarCuadrillaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarCuadrillaMouseExited
        resetColor(btnBuscarCuadrilla);
    }//GEN-LAST:event_btnBuscarCuadrillaMouseExited

    private void btnBuscarCuadrillaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarCuadrillaMousePressed
        frmVistaZonaNuevo form = new frmVistaZonaNuevo();
        form.setVisible(true);
        form.toFront();
    }//GEN-LAST:event_btnBuscarCuadrillaMousePressed

    private void btnGuardarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMousePressed
        
    }//GEN-LAST:event_btnGuardarMousePressed

    void setColor(JPanel panel){
        panel.setBackground(new Color(51,0,0));
    }
    
    void setColorFondo(JPanel panel){
        panel.setBackground(new Color(0,0,0));
    }
    
    void resetColorFondo(JPanel panel){
        panel.setBackground(new Color(51,0,0));
    }
    
    void resetColor(JPanel panel){
        panel.setBackground(new Color(102,0,0));
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
            java.util.logging.Logger.getLogger(frmElegirCuadrilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmElegirCuadrilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmElegirCuadrilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmElegirCuadrilla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmElegirCuadrilla().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnBuscarCuadrilla;
    private javax.swing.JPanel btnGuardar;
    private javax.swing.JPanel btnGuardar1;
    private javax.swing.JPanel btnGuardar2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JTextField txtBarrio;
    public static javax.swing.JTextField txtIdBarrio;
    // End of variables declaration//GEN-END:variables
}
