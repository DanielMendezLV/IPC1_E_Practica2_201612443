/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import cons.Constant;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import pojo.Casilla;
import tr.ThreadWorker;

/**
 *
 * @author Admin_bdgsa
 */
public class VentanaPanelGenerado extends JFrame implements ActionListener, ChangeListener {
    Integer tam = 0;
    Integer tamJaula = 3;
    String[][] jaulaDeOcho = new String[3][3];
    String[][] strCasillas;
    String[][] strCasillaTemporal;
    String[][] strAuxiliar;
    JButton[][] btnCasillas;
    Boolean seguir = true;
    ThreadWorker tw;
    
    
    public VentanaPanelGenerado(Integer tam){
        this.tam = tam;
        this.configurarPantalla();
    }
    
     public void configurarPantalla(){
        this.agregarComponentes(this.getContentPane());
        //Configuraciones
        
    }
    
    public void agregarComponentes(Container contentPane){
        JFrame frame = this;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        btnCasillas = new JButton[tam][tam]; 
        strCasillas = new String[tam][tam];
        strCasillaTemporal = new String[tam][tam];
        strAuxiliar = new String[tam][tam];
        BorderLayout bd = new BorderLayout();
        Panel panelGeneral = new Panel();
        panelGeneral.setLayout(new BorderLayout());
        
        JPanel buttonPane = new JPanel(new GridLayout(tam, tam));
        int columna = 0;
        int fila = 0;
        
        for (int i = 0; i < (tam*tam); i++) {
            //Logica de arreglo
            JButton b = new JButton();
            b.setToolTipText(""+i);
            b.addActionListener(this);
            b.setPreferredSize(new Dimension(15, 25));
            b.setBackground(Color.LIGHT_GRAY);
            b.setToolTipText(""+fila+"-"+columna);
            btnCasillas[fila][columna] = b;
            strCasillas[fila][columna] = "";
            strCasillaTemporal[fila][columna] = "";
            strAuxiliar[fila][columna] = "";
            if(columna==(tam-1)){
                columna = 0;
                fila++;
            }else{
                columna++;
            }
        }
        
        for(int ar = 0; ar<tam; ar++){
            for(int cl = 0; cl<tam; cl++){
                buttonPane.add(btnCasillas[ar][cl]);
            }
        }

        
        JPanel sliderPane = new JPanel(new GridLayout(3, 3));
        JSlider sl = new JSlider(JSlider.HORIZONTAL,
                                      Constant.MINV, Constant.MAXV, Constant.INIT);
        JButton pausar = new JButton();
        pausar.setText("Iniciar");
        sl.addChangeListener(this);
        pausar.addActionListener(this);
        sliderPane.add(new JLabel("Controles"));
        sliderPane.add(new JLabel());
        sliderPane.add(new JLabel());
        sliderPane.add(new JLabel("Velocidad"));
        sliderPane.add(sl);
        sliderPane.add(pausar);
        sliderPane.add(new JLabel());
        sliderPane.add(new JLabel());
        sliderPane.add(new JLabel());
        
        
        panelGeneral.add(buttonPane, BorderLayout.CENTER);
        panelGeneral.add(sliderPane, BorderLayout.PAGE_END);
        frame.add(panelGeneral);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
                
    }
    
    public void pintarSiguienteIteracion(String[][] arr){
        for(int ar = 0; ar<tam; ar++){
            for(int cl = 0; cl<tam; cl++){
                if(arr[ar][cl].contains("V")){
                    btnCasillas[ar][cl].setBackground(Color.BLACK);
                }else{
                    btnCasillas[ar][cl].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }
    
    public void igualarListas(){
        int fila = 0;
        int columna = 0;
        
        for (int i = 0; i < (tam*tam); i++) {
            //Logica de arreglo
            strCasillaTemporal[fila][columna] = strCasillas[fila][columna];
            strAuxiliar[fila][columna] = strCasillas[fila][columna];
            if(columna==(tam-1)){
                columna = 0;
                fila++;
            }else{
                columna++;
            }
        }
    }
    
    public void ejecucionThread(){
    
    }
    
    public void actionPerformed(ActionEvent e) {
        JButton btnClick = (JButton) e.getSource();
        
        if(btnClick.getText().equals("Iniciar")){
            
            while(true){
                Thread t = new Thread(new ThreadWorker(tam, strCasillas, strCasillaTemporal, strAuxiliar, btnCasillas));
                t.start();
                
            }
        }else if(btnClick.getText().equals("Pausar")){
            
        }
        else{
            Integer fila = Integer.parseInt(btnClick.getToolTipText().split("-")[0]);
            Integer columna = Integer.parseInt(btnClick.getToolTipText().split("-")[1]);
            strCasillas[fila][columna] = "V";
            pintarSiguienteIteracion(strCasillas);
        }
        
    } 

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        System.out.println("Hola me cambiaste");
    }
    
}
