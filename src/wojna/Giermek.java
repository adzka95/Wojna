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
public class Giermek extends Thread{
    private Krolestwo krolestwo;
    private Stolowka stolowka;
    private boolean zyje;
    private int hp;
    
    public Giermek(Krolestwo krolestwo, Stolowka stolowka){
        this.krolestwo=krolestwo;
        this.stolowka=stolowka;
        this.zyje=true;
        this.hp=5;
    
    }
    
    public void run(){
        Random generator = new Random();
        int czekaj;
        int ilosc;
        boolean najedzony;
        while(zyje){
            czekaj=generator.nextInt(3)+10;
            try {
                Thread.sleep(czekaj*2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Chlop.class.getName()).log(Level.SEVERE, null, ex);
            }
           
            najedzony=stolowka.zjedzZiemniaka(1);
            if(najedzony){
                hp+=1;
            }
            else{
                krolestwo.napiszKomentarz("Giermek gloduje");
                if(hp>=1)
                    hp-=1;
                else
                    hp=0;
            }
            
        }
    
    
    }
    public void setZyje(boolean przezyl){
        zyje=przezyl;
    }
    public int getHp(){
        return hp;
    }
}
