/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.SwingWorker;

/**
 *
 * @author danie
 */
public class ThreadWorker implements Runnable{
    Integer tam = 0;
    Integer tamJaula = 3;
    String[][] jaulaDeOcho = new String[3][3];
    String[][] strCasillas;
    String[][] strCasillaTemporal;
    String[][] strAuxiliar;
    JButton[][] btnCasillas;

    public Integer getTam() {
        return tam;
    }

    public void setTam(Integer tam) {
        this.tam = tam;
    }

    public Integer getTamJaula() {
        return tamJaula;
    }

    public void setTamJaula(Integer tamJaula) {
        this.tamJaula = tamJaula;
    }

    public String[][] getJaulaDeOcho() {
        return jaulaDeOcho;
    }

    public void setJaulaDeOcho(String[][] jaulaDeOcho) {
        this.jaulaDeOcho = jaulaDeOcho;
    }

    public String[][] getStrCasillas() {
        return strCasillas;
    }

    public void setStrCasillas(String[][] strCasillas) {
        this.strCasillas = strCasillas;
    }

    public String[][] getStrCasillaTemporal() {
        return strCasillaTemporal;
    }

    public void setStrCasillaTemporal(String[][] strCasillaTemporal) {
        this.strCasillaTemporal = strCasillaTemporal;
    }

    public String[][] getStrAuxiliar() {
        return strAuxiliar;
    }

    public void setStrAuxiliar(String[][] strAuxiliar) {
        this.strAuxiliar = strAuxiliar;
    }

    public JButton[][] getBtnCasillas() {
        return btnCasillas;
    }

    public void setBtnCasillas(JButton[][] btnCasillas) {
        this.btnCasillas = btnCasillas;
    }
    
    

    public ThreadWorker(Integer tam,String[][] strCasillas, String[][] strCasillaTemporal, String[][] strAuxiliar, JButton[][] btnCasillas) {
        this.strCasillas = strCasillas;
        this.tam = tam;
        this.strCasillaTemporal = strCasillaTemporal;
        this.strAuxiliar = strAuxiliar;
        this.btnCasillas = btnCasillas;
    }
    
    
      
   
  
    public void run() {
        try{
            for(int i= 0 ; i < tam-2 ; i++)
            {      
                //Columnas
                for(int j = 0 ; j < tam-2 ; j++)
                {
                    //Jaulas que analizaremos una por una, este es el ciclo iterativo
                    jaulaDeOcho[0][0] = strCasillas[(i)][(j)];
                    jaulaDeOcho[0][1] = strCasillas[(i)][(j+1)];
                    jaulaDeOcho[0][2] = strCasillas[(i)][(j+2)];

                    jaulaDeOcho[1][0] = strCasillas[(i+1)][(j)];
                    jaulaDeOcho[1][1] = strCasillas[(i+1)][(j+1)];
                    jaulaDeOcho[1][2] = strCasillas[(i+1)][(j+2)];

                    jaulaDeOcho[2][0] = strCasillas[(i+2)][(j)];
                    jaulaDeOcho[2][1] = strCasillas[(i+2)][(j+1)];
                    jaulaDeOcho[2][2] = strCasillas[(i+2)][(j+2)]; 
                    //No vivos
                    Integer noVivosParaRealizarAccion = 0;

                    //Análizando los centros
                    for(int fil = 0 ; fil < 3 ; fil++)
                    {
                        for(int cl= 0 ; cl< 3 ; cl++)
                        {
                            if( !(cl == 1 && fil == 1))
                            {
                                //Si contiene esto esta vivo
                                if(jaulaDeOcho[fil][cl].contains("V"))
                                {
                                    noVivosParaRealizarAccion++;
                                }
                            }
                        }
                    }


                    if(noVivosParaRealizarAccion < 2 && jaulaDeOcho[1][1].contains("V")) 
                    {  
                        strCasillaTemporal[(i+1)][(j+1)] = "";
                    }

                    if(noVivosParaRealizarAccion > 3 && jaulaDeOcho[1][1].contains("V")) 
                    { 
                        strCasillaTemporal[(i+1)][(j+1)] = "";
                    }

                    if(noVivosParaRealizarAccion == 3 && !jaulaDeOcho[1][1].contains("V")) 
                    { 
                        strCasillaTemporal[(i+1)][(j+1)] = "V";
                    }

                    if(noVivosParaRealizarAccion == 3 && jaulaDeOcho[1][1].contains("V")) 
                    { 
                        strCasillaTemporal[(i+1)][(j+1)] = "V";
                    }

                    if(noVivosParaRealizarAccion == 2) 
                    {
                        strCasillaTemporal[(i+1)][(j+1)] = strCasillas[(i+1)][(j+1)];
                    }

                }
            }

            strAuxiliar = strCasillas;
            strCasillas = strCasillaTemporal;
            strCasillaTemporal = strAuxiliar;
            
            for(int i = 0 ; i < tam ; i++)
            {
                for(int j = 0; j < tam ; j++)
                {
                    if(strCasillaTemporal[i][j].contains("V"))
                    {
                        btnCasillas[i][j].setBackground(Color.BLACK);
                    }

                    if(strCasillaTemporal[i][j].contains(""))
                    {
                        btnCasillas[i][j].setBackground(Color.LIGHT_GRAY);
                    }

                }
            }
            
            
            for(int i = 0 ; i < tam ; i++)
            {
                for(int j= 0 ; j < tam ; j++)
                {
                    strCasillaTemporal[i][j] = "V";
                }
            }
            
            
            Thread.sleep(10);
        }catch(InterruptedException e){
        
        }        
    }
}
