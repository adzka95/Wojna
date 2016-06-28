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
public class Stolowka extends Thread{
    
    private int iloscZiemniakow;
    private Krolestwo krolestwo;
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    
    public Stolowka(int iloscZiemniakow, Krolestwo krolestwo){
        this.iloscZiemniakow=iloscZiemniakow;  
        this.krolestwo=krolestwo;
    }
    
    public synchronized void dodajZiemniaka(){       
        try {            
            Thread.sleep(750);
            iloscZiemniakow++;
            krolestwo.wypiszZiemniaki(iloscZiemniakow);          
        } catch (InterruptedException ex) {
            Logger.getLogger(Stolowka.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public synchronized boolean zjedzZiemniaka(int ilosc){
        if(iloscZiemniakow>=ilosc){
            iloscZiemniakow-=ilosc;
            krolestwo.wypiszZiemniaki(iloscZiemniakow);
            return true;
        }
        else{
            
            return false;
        }
    
    }
    
}
