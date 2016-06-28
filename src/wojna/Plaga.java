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
public class Plaga extends Thread{
    private Krolestwo krolestwo;
    
    public Plaga(Krolestwo krolestwo){
        this.krolestwo=krolestwo;    
    }
    
    public void run(){
        Random generator = new Random();
        int chlopow, rycerzy, czekaj;
        int zabitoChlopow, zabitoRycerzy;
        Rycerz rycerz;
        Chlop chlop;
        while(true){
            zabitoChlopow=0;
            zabitoRycerzy=0;
            czekaj=generator.nextInt(10)+20;
            try {
                Thread.sleep(czekaj*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Plaga.class.getName()).log(Level.SEVERE, null, ex);
            }
            chlopow=generator.nextInt(5);
            rycerzy=generator.nextInt(3);
            for(int i=0; i<chlopow; i++){
                chlop=krolestwo.wezChlopa();
                if(chlop!=null){
                    chlop.setZyje(false);
                    zabitoChlopow++;
                }
            }
            for(int i=0; i<rycerzy; i++){
                rycerz=krolestwo.wezRycerza();
                if(rycerz!=null){
                    rycerz.setZyje(false);
                    zabitoRycerzy++;
                }           
            }
            krolestwo.napiszKomentarz("Plaga! Zmarlo "+zabitoChlopow+ " chlopow\ni "+zabitoRycerzy+" rycerzy");
            krolestwo.wypiszChlopow();
            krolestwo.wypiszRycerzy();
        
        }
    
    }
    
    
    
    
    
    
    
    
}
