/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wojna;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ada
 */
public class Ksiezniczka extends Thread{
    
    private Krolestwo krolestwo;
    private int kaprysyKsiezniczki;
    private int szczescieKsiezniczki;
    private Jubiler jubiler;
    
    public Ksiezniczka(Krolestwo krolestwo, int kaprysy, int szczescie, Jubiler jubiler){
        this.krolestwo=krolestwo;
        this.kaprysyKsiezniczki=kaprysy;
        this.szczescieKsiezniczki=szczescie;
        this.jubiler=jubiler;
    
    }
    public void run(){
        try {                
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ksiezniczka.class.getName()).log(Level.SEVERE, null, ex);
            }
        Random generator = new Random();
        int czekaj;
        int zabierz;
        boolean pobrano;
        while(true){
            czekaj=generator.nextInt(kaprysyKsiezniczki)+1;
            try {                
                Thread.sleep(czekaj*5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ksiezniczka.class.getName()).log(Level.SEVERE, null, ex);
            }
            zabierz=generator.nextInt(3)+1;
            pobrano=jubiler.odbierzBlyskotki(zabierz);
            if(pobrano){
                szczescieKsiezniczki+=5*zabierz;
            }
            else{
                if(szczescieKsiezniczki>=2)
                    szczescieKsiezniczki-=2;
                else
                    szczescieKsiezniczki=0;
            }
            krolestwo.wypiszKsiezniczke(szczescieKsiezniczki);
        }
    }
    
    public int getSczescieKsiezniczki(){
        return szczescieKsiezniczki;
    }
    
    
    
    
    
}
