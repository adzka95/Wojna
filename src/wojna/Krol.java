/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wojna;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ada
 */
public class Krol extends Thread{
    private Krolestwo krolestwo;
    private Ksiezniczka ksiezniczka;
    
    public Krol(Krolestwo krolestwo, Ksiezniczka ksiezniczka){
        this.krolestwo=krolestwo;
        this.ksiezniczka=ksiezniczka;
        
    }
    
    public void run(){
        int szczescie;
        while(true){
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Krol.class.getName()).log(Level.SEVERE, null, ex);
            }
            szczescie=ksiezniczka.getSczescieKsiezniczki();
            krolestwo.wypiszKrola(szczescie/3);
            if(szczescie>3){
                krolestwo.wzmocnijRycerzy((int)szczescie/3);
                krolestwo.napiszKomentarz("Krol wzmacnia o "+(int)szczescie/3);
            }
            else
                krolestwo.napiszKomentarz("Krolewna jest zbyt smutna\nby krol pomogl");
        
        
        }
    
    
    }
    
}
