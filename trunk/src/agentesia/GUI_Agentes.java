package agentesia;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GUI_Agentes.java
 *
 * Created on 7/09/2010, 07:39:38 AM
 */

/**
 *
 * @author jorgeorm
 */
public class GUI_Agentes extends javax.swing.JFrame {

    /** Creates new form GUI_Agentes */
    public GUI_Agentes() {
        initComponents();
        this.setVisible(true);
        jfc_selectorEscenario.setCurrentDirectory(new File("src/escenarios/"));
        returnValJFC=0;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jfc_selectorEscenario = new javax.swing.JFileChooser();
        jp_juego = new javax.swing.JPanel();
        jmb_config = new javax.swing.JMenuBar();
        jm_agente = new javax.swing.JMenu();
        jmi_tipoAgente = new javax.swing.JMenuItem();
        jm_ambiente = new javax.swing.JMenu();
        jmi_tipoAmbiente = new javax.swing.JMenuItem();
        jm_escenario = new javax.swing.JMenu();
        jmi_cargaEscenario = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SmartRobot");
        getContentPane().setLayout(new java.awt.FlowLayout());

        jp_juego.setPreferredSize(new Dimension(ancho,alto));
        //Creación del Canvas
        obj_escenario=new EscenarioGrafico(600,600);

        javax.swing.GroupLayout jp_juegoLayout = new javax.swing.GroupLayout(jp_juego);
        jp_juego.setLayout(jp_juegoLayout);
        jp_juegoLayout.setHorizontalGroup(
            jp_juegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        jp_juegoLayout.setVerticalGroup(
            jp_juegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        jp_juego.add("Center",obj_escenario);

        getContentPane().add(jp_juego);

        jm_agente.setText("Agente");

        jmi_tipoAgente.setText("Tipo Agente");
        jm_agente.add(jmi_tipoAgente);

        jmb_config.add(jm_agente);

        jm_ambiente.setText("Ambiente");

        jmi_tipoAmbiente.setText("Tipo Ambiente");
        jm_ambiente.add(jmi_tipoAmbiente);

        jmb_config.add(jm_ambiente);

        jm_escenario.setText("Escenario");

        jmi_cargaEscenario.setText("Cargar Escenario");
        jmi_cargaEscenario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_cargaEscenarioActionPerformed(evt);
            }
        });
        jm_escenario.add(jmi_cargaEscenario);

        jmb_config.add(jm_escenario);

        setJMenuBar(jmb_config);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmi_cargaEscenarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_cargaEscenarioActionPerformed
        // TODO add your handling code here:
        returnValJFC=jfc_selectorEscenario.showOpenDialog(this);
        if(returnValJFC == JFileChooser.APPROVE_OPTION)
        {
            File f_escenario;
            f_escenario=jfc_selectorEscenario.getSelectedFile();
            int [][] escen=new int[10][10];
            int [][] memoria=new int [10][10];
            int contN=0,contI=0,cordXi=0,cordYi=0,cordXf=0,cordYf=0;

            try {
                Scanner sc = new Scanner(f_escenario);
                int num;
                int line=0;
                int column=0;

                while(sc.hasNextInt())
                {
                    num=sc.nextInt();
                    System.err.print(num+"");
                    //Saco datos para nodo inicial
                    ///si es inicio
                    if(num==2)
                    {
                        cordXi=column;
                        cordYi=line;
                    }
                    //Salida
                    else if(num==3)
                    {
                        cordXf=column;
                        cordYf=line;
                    }
                    //Nave
                    else if(num==4 || num==5)
                    {
                        contN++;
                    }
                    //item
                    else if(num==6)
                    {
                        contI++;
                    }

                    //asigno los valores a las matrices del mapa y la memoria
                    escen[column][line]=num;
                    memoria[column][line]=num;
                    
                    column++;

                    if(column>=10)
                    {
                        System.err.println();
                        column=0;
                        line++;
                    }
                }
                                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GUI_Agentes.class.getName()).log(Level.SEVERE, null, ex);
            }
            obj_escenario.paintEscenario(escen, obj_escenario.getGraphics());
            //Para la nueva estructura
                

                NodoEstado raiz=new NodoEstado("", 0, "", cordXi, cordYi, contI, contN, 0, memoria);
                Amplitud obj_amplitud=new Amplitud(raiz, cordXf, cordYf);
                NodoEstado respuesta = obj_amplitud.ejecutar();

                if(respuesta.equals(null)) System.out.println("No se encontró respuesta con aplitud");
                else
                {
                    System.out.println("Se encontró respuesta con amplitud");
                    System.out.println("Ruta: "+respuesta.getRuta()+", ("+respuesta.getX()+", "+respuesta.getY()+")");
                    System.out.println("Operadores: "+respuesta.getOperador());
                    System.out.println("Costo: "+respuesta.getCosto());
                }
        }
    }//GEN-LAST:event_jmi_cargaEscenarioActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Agentes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser jfc_selectorEscenario;
    private javax.swing.JMenu jm_agente;
    private javax.swing.JMenu jm_ambiente;
    private javax.swing.JMenu jm_escenario;
    private javax.swing.JMenuBar jmb_config;
    private javax.swing.JMenuItem jmi_cargaEscenario;
    private javax.swing.JMenuItem jmi_tipoAgente;
    private javax.swing.JMenuItem jmi_tipoAmbiente;
    private javax.swing.JPanel jp_juego;
    // End of variables declaration//GEN-END:variables
    private int returnValJFC;
    private EscenarioGrafico obj_escenario;
    public static final int ancho=601;
    public static final int alto=601;
}
