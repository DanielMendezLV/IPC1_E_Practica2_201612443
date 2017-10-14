/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Admin_bdgsa
 */
public class VentanaInicial extends JFrame{
    JTextField txtUsuario;
    
    public VentanaInicial(){
        this.configurarPantalla();
    }
    
    public void configurarPantalla(){
        this.agregarComponentes(this.getContentPane());
        //Configuraciones
        setSize(300,200);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Game of life");
        setLocationRelativeTo(null);
        Toolkit miPantalla = Toolkit.getDefaultToolkit();
    }
    
     public void agregarComponentes(Container contentPane){
        contentPane.setLayout(new BorderLayout(5,5));
        JLabel headerLabel = new JLabel();      
        
        headerLabel.setText("              ! Bienvenido ! al juego de la vida"); 
        headerLabel.setPreferredSize(new Dimension(200, 50));
        contentPane.add(headerLabel, BorderLayout.PAGE_START);
//        
//        JPanel p = new JPanel(new SpringLayout());

        JLabel  lblNombre= new JLabel("Tamaño del tablero: ", JLabel.RIGHT);
        txtUsuario = new JTextField(6);
        
        JPanel pnlV = new JPanel();
        JPanel panelMedio = new JPanel();
        
        pnlV.setSize(300,300);
        
        BoxLayout boxlayoutPedazo = new BoxLayout(pnlV, BoxLayout.Y_AXIS);
         
        JButton loginButton = new JButton("Iniciar");
        pnlV.setLayout(boxlayoutPedazo);
        
        pnlV.add(lblNombre);
        pnlV.add(txtUsuario);
        pnlV.add(loginButton);
        panelMedio.add(pnlV);

        contentPane.add(panelMedio, BorderLayout.CENTER);
        
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
              
                VentanaPanelGenerado vm = new VentanaPanelGenerado(Integer.parseInt(txtUsuario.getText()));
                JOptionPane.showMessageDialog(null, "Generacion de tablero exitosa");
                setVisible(false); //you can't see me!
                dispose(); //Destroy the JFrame object
                
            }
        });
    }
     
     
}
