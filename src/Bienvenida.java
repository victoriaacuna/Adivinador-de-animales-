
import javax.swing.JOptionPane;
import java.io.*;
import javax.swing.JFileChooser;

public class Bienvenida extends javax.swing.JFrame {

    String nombreUsuario;
    ArbolBinario tree;
    JFileChooser seleccionado = new JFileChooser();
    File archivo;
    Archivo gestion = new Archivo();
    
    public Bienvenida() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.tree=new ArbolBinario();
        do{
            this.nombreUsuario=JOptionPane.showInputDialog(null, "¡Bienvenido/a!\n¿Cuál es tu nombre?");
        } while(this.nombreUsuario==null);
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnIniciar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCargarArchivo = new javax.swing.JButton();
        btnVaciar = new javax.swing.JButton();
        btnGuardarArchivo = new javax.swing.JButton();
        btnMostrarArbol = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 250, 40));

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 660, 90, 40));

        btnCargarArchivo.setText("Cargar archivo");
        btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarArchivoActionPerformed(evt);
            }
        });
        getContentPane().add(btnCargarArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 280, 250, 40));

        btnVaciar.setText("Vaciar");
        btnVaciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVaciarActionPerformed(evt);
            }
        });
        getContentPane().add(btnVaciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 350, 248, 40));

        btnGuardarArchivo.setText("Guardar archivo");
        btnGuardarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarArchivoActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardarArchivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 420, 248, 40));

        btnMostrarArbol.setText("Mostrar base de conocimientos");
        btnMostrarArbol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarArbolActionPerformed(evt);
            }
        });
        getContentPane().add(btnMostrarArbol, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 490, 250, 40));

        jLabel1.setFont(new java.awt.Font("Skia", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Adivinador de animales");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 410, 40));

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icon.jpg"))); // NOI18N
        getContentPane().add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        boolean pregunto=false;
        Nodo actual;
        if(tree.getRoot()==null){
            String primerAnimal = JOptionPane.showInputDialog(null, "No tengo información suficiente.\n¿En qué animal estás pensando?");
            while(primerAnimal==null){
                JOptionPane.showMessageDialog(null, "Lo siento. Tienes que responder esta pregunta");
                primerAnimal=JOptionPane.showInputDialog(null, "No tengo información suficiente.\n¿En qué animal estás pensando?");
            }
            tree.setRoot(new Nodo(primerAnimal));
        } else {
            actual=tree.getRoot();
            while(!pregunto){
                if(actual.esHoja()){
                    this.tree.preguntarAnimal(actual, this.nombreUsuario, this.tree);
                    pregunto=true;
                }else{
                    actual=this.tree.moverse(this.tree.preguntar(actual, this.tree), actual);
                }
            }
        }
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnVaciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVaciarActionPerformed
        this.tree=new ArbolBinario();
    }//GEN-LAST:event_btnVaciarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnGuardarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarArchivoActionPerformed
        
        this.tree.setContenido("Archivo de texto del adivinador de animales\n");
        this.tree.PreOrderGuardarContenido(this.tree.getRoot(), tree);
        if(seleccionado.showDialog(null, "Guardar") == JFileChooser.APPROVE_OPTION){
            archivo = seleccionado.getSelectedFile();
            if(archivo.getName().endsWith("txt")){
                gestion.GuardarTexto(archivo, this.tree.getContenido());
            } else {
                JOptionPane.showMessageDialog(null, "NO SE PUDO GUARDAR EL ARCHIVO, INTÉNTELO DE NUEVO.\nEl texto se debe guardar en un formato de texto.\n(Agréguele \".txt\" al nombre "
                        + "de su archivo cuando lo vaya a guardar).");
            }
        }
        
    }//GEN-LAST:event_btnGuardarArchivoActionPerformed

    private void btnCargarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarArchivoActionPerformed
        if(seleccionado.showDialog(null, "Abrir") == JFileChooser.APPROVE_OPTION){
            this.archivo = seleccionado.getSelectedFile();
            if(archivo.canRead()){
                if(archivo.getName().endsWith("txt")){
                    if(Archivo.archivoValido(archivo)){
                        
                        this.tree= new ArbolBinario();
                        this.tree.setContenido(gestion.AbrirTexto(archivo));
                        this.tree=Archivo.crearArbolDesdeArchivo(archivo, this.tree);
                        
                    } else {
                        JOptionPane.showMessageDialog(null, "El archivo que seleccionó, no contiene la información adecuada "
                                + "para jugar.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, seleccione un archivo de texto.\n(Tiene que terminar en "
                            + "\".txt\")");
                }
            }
        }
    }//GEN-LAST:event_btnCargarArchivoActionPerformed

    private void btnMostrarArbolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarArbolActionPerformed

           if(this.tree.getRoot()==null){
               JOptionPane.showMessageDialog(null, "La base de datos está vacía.\nInténtelo cuando haya introducido información"
                       + " al programa.", "Error", JOptionPane.ERROR_MESSAGE);
           } else {
               Impresion i = new Impresion(this.tree);
           }
           
    }//GEN-LAST:event_btnMostrarArbolActionPerformed

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
            java.util.logging.Logger.getLogger(Bienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bienvenida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bienvenida().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarArchivo;
    private javax.swing.JButton btnGuardarArchivo;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnMostrarArbol;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnVaciar;
    private javax.swing.JLabel icon;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
