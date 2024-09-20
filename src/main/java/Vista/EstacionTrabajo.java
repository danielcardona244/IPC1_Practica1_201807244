package Vista;

import Controlador.ControladorArchivoProductos;
import Modelo.Producto;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstacionTrabajo extends javax.swing.JFrame {
    private String codigoProducto;
    private int cantidad;
    private Producto productoActual;
    private long tiempoInicio;
    private Timer timerContador;
    
    private static final Map<String, Integer> TIEMPOS_ENSAMBLADO = new HashMap<>();
    private static final Map<String, Integer> TIEMPOS_PINTURA = new HashMap<>();
    
        //inicializador 
    static {
        TIEMPOS_ENSAMBLADO.put("metal", 15);
        TIEMPOS_ENSAMBLADO.put("madera", 25);
        TIEMPOS_ENSAMBLADO.put("vidrio", 10);
        TIEMPOS_ENSAMBLADO.put("nylon", 20);
        TIEMPOS_ENSAMBLADO.put("hule", 10);
        TIEMPOS_ENSAMBLADO.put("poliester", 5);
        
        TIEMPOS_PINTURA.put("verde", 15);
        TIEMPOS_PINTURA.put("negro", 25);
        TIEMPOS_PINTURA.put("na", 0);
        TIEMPOS_PINTURA.put("azul", 20);
        TIEMPOS_PINTURA.put("rojo", 10);
        TIEMPOS_PINTURA.put("amarillo", 5);
    }

    public EstacionTrabajo(String codigoProducto, int cantidad) {
        initComponents();
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        if (validarProducto()) {
            iniciarProduccion();
        } else {
            this.dispose();
        }
    }

    private boolean validarProducto() {
        ControladorArchivoProductos controlador = new ControladorArchivoProductos();
        List<Producto> productos = controlador.leerProductosDesdeCSV();
        
        for (Producto p : productos) {
            if (p.getCodigo().equals(codigoProducto)) {
                productoActual = p;
                break;
            }
        }
        
        if (productoActual == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String material = productoActual.getMaterial().toLowerCase();
        String color = productoActual.getColor().toLowerCase();
        
        if (!TIEMPOS_ENSAMBLADO.containsKey(material)) {
            JOptionPane.showMessageDialog(this, "Material no válido: " + material, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (!TIEMPOS_PINTURA.containsKey(color)) {
            JOptionPane.showMessageDialog(this, "Color no válido: " + color, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }

    private void iniciarProduccion() {
        int tiempoEnsamblado = productoActual.calcularTiempoEnsamblaje() * cantidad;
        int tiempoPintura = productoActual.calcularTiempoPintura() * cantidad;
        int tiempoEmpaquetado = 10 * cantidad;  // 10 segundos por unidad
        
        tiempoInicio = System.currentTimeMillis();
        iniciarContadorTiempo();
        //hilo principal
        new Thread(() -> {
            actualizarBarra(jProgressBar1, tiempoEnsamblado, "Ensamblado");
            actualizarBarra(jProgressBar2, tiempoPintura, "Pintura");
            actualizarBarra(jProgressBar3, tiempoEmpaquetado, "Empaquetado");
            finalizarProduccion();
        }).start();
    }
        //simula la etapa
    private void actualizarBarra(JProgressBar barra, int tiempoTotal, String proceso) {
        for (int i = 0; i <= 100; i++) {
            final int progreso = i;
            SwingUtilities.invokeLater(() -> {
                barra.setValue(progreso);
                actualizarEtiquetas(proceso, progreso);
            });
            try {
                Thread.sleep(tiempoTotal * 10); // Convertir segundos a milisegundos y dividir por 100 para los pasos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void actualizarEtiquetas(String proceso, int progreso) {
        switch (proceso) {
            case "Ensamblado":
                jLabel2.setText("Ensamblaje: " + progreso + "%");
                break;
            case "Pintura":
                jLabel3.setText("Pintura: " + progreso + "%");
                break;
            case "Empaquetado":
                jLabel4.setText("Empaque: " + progreso + "%");
                break;
        }
        jLabel6.setText("Productos: " + codigoProducto);
    }

    private void iniciarContadorTiempo() {
        timerContador = new Timer(1000, e -> {
            long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
            jLabel5.setText("Tiempo: " + formatearTiempo(tiempoTranscurrido));
        });
        timerContador.start();
    }
    //detiene el timer, calcula los resultados finales y abre la estacion de resultados
    private void finalizarProduccion() {
        SwingUtilities.invokeLater(() -> {
            timerContador.stop();
            long tiempoTotal = System.currentTimeMillis() - tiempoInicio;
            double costoTotal = productoActual.calcularCostoProduccion() * cantidad;
            EstacionResultados resultados = new EstacionResultados(productoActual, cantidad, tiempoTotal, costoTotal);
            resultados.setVisible(true);
            this.dispose();
        });
    }
    private String formatearTiempo(long milisegundos) {
        long totalSegundos = milisegundos / 1000;
        long minutos = totalSegundos / 60;
        long segundos = totalSegundos % 60;
        return String.format("%02d:%02d", minutos, segundos);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Estación de Trabajo");

        jLabel2.setText("Ensamblaje");

        jLabel3.setText("Pintura");

        jLabel4.setText("Empaque");

        jLabel5.setText("Tiempo");

        jLabel6.setText("Productos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(128, 128, 128))
            .addGroup(layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(EstacionTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EstacionTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EstacionTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EstacionTrabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstacionTrabajo( "codigoProducto", 0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    // End of variables declaration//GEN-END:variables
}
