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
public class Jubiler extends Thread {
    private int blyskotki;
    private Krolestwo krolestwo;
    private Huta huta;
    
    
    public Jubiler(Krolestwo krolestwo, Huta huta){
        this.blyskotki=0;
        this.krolestwo=krolestwo;    
        this.huta=huta;
    }
    public void run(){
        while(true){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Jubiler.class.getName()).log(Level.SEVERE, null, ex);
        }
        huta.pobierzZloto();
        synchronized(this){
            blyskotki++;
        }
        krolestwo.wypiszBlyskotki(blyskotki);
        }
        
    }
    public synchronized boolean odbierzBlyskotki(int ilosc){
        if(blyskotki>=ilosc){
            krolestwo.napiszKomentarz("Krolewna pobiera\n"+ ilosc+" blyskotek");
            blyskotki-=ilosc;
            return true;        
        }
        else{
            krolestwo.napiszKomentarz("Nie ma tylu blyskotek\n ksiezniczka jest niesczesliwa");
        
        return false;
        }
    }
    
    
}
