package agentesia;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
        jm_busqueda = new javax.swing.JMenu();
        jm_tipoBusqueda = new javax.swing.JMenu();
        jmi_informada = new javax.swing.JMenuItem();
        jmi_noInformada = new javax.swing.JMenuItem();
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

        jm_busqueda.setText("Configuración");

        jm_tipoBusqueda.setText("Algoritmo Busqueda");

        jmi_informada.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        jmi_informada.setText("Informada");
        jmi_informada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_informadaActionPerformed(evt);
            }
        });
        jm_tipoBusqueda.add(jmi_informada);

        jmi_noInformada.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.ALT_MASK));
        jmi_noInformada.setText("No Informada");
        jmi_noInformada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmi_noInformadaActionPerformed(evt);
            }
        });
        jm_tipoBusqueda.add(jmi_noInformada);

        jm_busqueda.add(jm_tipoBusqueda);

        jmb_config.add(jm_busqueda);

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
            obj_escenario.paintEscenario(escen);
            //Para la nueva estructura
            NodoEstado raiz=new NodoEstado("", 0, "", cordXi, cordYi, contI, contN, 0, memoria);
            factoria=new FactoriaBusqueda(raiz, cordXf, cordYf,escen);
        }
    }//GEN-LAST:event_jmi_cargaEscenarioActionPerformed

    private void jmi_noInformadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_noInformadaActionPerformed
        if(factoria!=null)
        {
            Object [] posiblilidades ={"Amplitud","Costo","Profundidad"};
            //En el null va un icono
            String select =(String)JOptionPane.showInputDialog(this,"Busqueda preferente por: ","Busqueda",JOptionPane.PLAIN_MESSAGE,null,posiblilidades,"Amplitud");
            Busqueda obj_busqueda=null;
            obj_busqueda=factoria.crearNoInformada(select);
            if(obj_busqueda!=null)
            {
                long tiempoinicio=System.currentTimeMillis();
                NodoEstado respuesta = obj_busqueda.ejecutar();
                long tiempo=System.currentTimeMillis()-tiempoinicio;
                mostrarResultado(respuesta, select,obj_busqueda.getContNodos(),tiempo);
            }
        } else JOptionPane.showMessageDialog(this, "No se ha cargado un mapa", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jmi_noInformadaActionPerformed

    private void jmi_informadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmi_informadaActionPerformed
        if(factoria!=null)
        {
            Object [] posibilidades ={"Avara","A*"};
            Object [] heuristicas = {"Heuristica 1", "Heuristica 2"};
            //En el null va un icono
            String select =(String)JOptionPane.showInputDialog(this,"Busqueda preferente por: ","Busqueda",JOptionPane.PLAIN_MESSAGE,null,posibilidades,"Avara");
            String selectH =(String)JOptionPane.showInputDialog(this,"Escoja la heuristica a usar: ","Heuristicas",JOptionPane.PLAIN_MESSAGE,null,heuristicas,"Heuristica 1");
            Busqueda obj_busqueda=null;

            obj_busqueda=factoria.crearInformada(select, selectH);
            if(obj_busqueda!=null)
            {
                long tiempoinicio=System.currentTimeMillis();
                NodoEstado respuesta = obj_busqueda.ejecutar();
                long tiempo=System.currentTimeMillis()-tiempoinicio;
                mostrarResultado(respuesta, select,obj_busqueda.getContNodos(),tiempo);
                
            }
        } else JOptionPane.showMessageDialog(this, "No se ha cargado un mapa", "Error", JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_jmi_informadaActionPerformed

    private void mostrarResultado(NodoEstado respuesta, String algoritmo, int nodos, long tiempo)
    {
        if(respuesta==null) JOptionPane.showMessageDialog(this, "No se encontró respuesta con "+algoritmo,"Respuesta",JOptionPane.ERROR);
        else
        {
            JOptionPane.showMessageDialog(this, "Se encontró respuesta con "+algoritmo+"\n"
            + "Ruta: "+respuesta.getRuta()+"("+respuesta.getX()+","+respuesta.getY()+")\n"
            + "Profundidad: "+respuesta.getProfundidadPorOps()+"\n"
            + "Nodos expandidos: "+nodos+"\n"
            + "Tiempo: "+tiempo+" ms\n"
            + "Costo: "+respuesta.getCosto(),"Resultado",JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

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
    private javax.swing.JMenu jm_busqueda;
    private javax.swing.JMenu jm_escenario;
    private javax.swing.JMenu jm_tipoBusqueda;
    private javax.swing.JMenuBar jmb_config;
    private javax.swing.JMenuItem jmi_cargaEscenario;
    private javax.swing.JMenuItem jmi_informada;
    private javax.swing.JMenuItem jmi_noInformada;
    private javax.swing.JPanel jp_juego;
    // End of variables declaration//GEN-END:variables
    private int returnValJFC;
    private EscenarioGrafico obj_escenario;
    public static final int ancho=601;
    public static final int alto=601;
    private FactoriaBusqueda factoria;
}
