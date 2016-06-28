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
public class Huta extends Thread{
    private int zloto;
    private Kopalnia kopalnia;
    private Krolestwo krolestwo;
    
    public Huta(Kopalnia kopalnia, Krolestwo krolestwo){
        this.zloto=0;
        this.kopalnia=kopalnia;
        this.krolestwo=krolestwo;
    }
    public void run(){
        while(true){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Huta.class.getName()).log(Level.SEVERE, null, ex);
        }
        kopalnia.pobierzZamowienie();
        dodajZloto();
        }
    }
    public synchronized void dodajZloto(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException interruptedException) {
        }
        krolestwo.napiszKomentarz("Hura produkuje zloto");
        zloto++;
        krolestwo.wypiszZloto(zloto);
        if(zloto>=3)
            notify();
        
    }
    public synchronized  void pobierzZloto(){
        if(zloto<3){
            krolestwo.napiszKomentarz("Jubiler czeka na zloto");
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Huta.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        zloto-=3;
        krolestwo.wypiszZloto(zloto);
        krolestwo.napiszKomentarz("Jubiler produkuje blyskotki");
    
    
    }
    
}
