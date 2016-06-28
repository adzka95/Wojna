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
public class Gornik extends Thread{
    
    private int idGornika;
    private Kopalnia kopalnia;
    private boolean zyje;
    
    public Gornik(int id, Kopalnia kopalnia){
        this.zyje=true;
        this.idGornika=id;
        this.kopalnia= kopalnia;        
    
    }
    public void run(){
        Random generator = new Random();
        int czekaj;
        int znalazlZlota;
        int znalazlMiedzi;
        while(zyje){
            czekaj=generator.nextInt(10)+3;
            try {
                Thread.sleep(czekaj*2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Chlop.class.getName()).log(Level.SEVERE, null, ex);
            }
            znalazlZlota=generator.nextInt(3);
            znalazlMiedzi=generator.nextInt(3);
            kopalnia.dodajMaterialy(znalazlZlota, znalazlMiedzi);
            
            
            
        }
 
    
    }
    
    
    
}
