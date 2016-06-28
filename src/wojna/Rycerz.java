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
public class Rycerz extends Thread{
    private Krolestwo krolestwo;
    private Stolowka stolowka;
    private boolean zyje;
    private int hp;
    
    public Rycerz(Krolestwo krolestwo, Stolowka stolowka, int hp){
        this.krolestwo=krolestwo;
        this.stolowka=stolowka;
        this.zyje=true;
        this.hp=hp;
    
    }
    
    public void run(){
        Random generator = new Random();
        int czekaj;
        int ilosc;
        boolean najedzony;
        while(zyje){
            czekaj=generator.nextInt(5)+10;
            try {
                Thread.sleep(czekaj*1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Chlop.class.getName()).log(Level.SEVERE, null, ex);
            }
            ilosc=generator.nextInt(3)+1;
            najedzony=stolowka.zjedzZiemniaka(1);
            if(najedzony){
                hp+=ilosc;
            }
            else{
                krolestwo.napiszKomentarz("Rycerz gloduje");
                if(hp>=2)
                    hp-=2;
                else
                    hp=0;
            }
        }
   
    }
    public void setZyje(boolean przezyl){
        zyje=przezyl;
    }
    public void wzmocnijRycerza(int pomoc){
        hp+=pomoc;
    }
    public int getHp(){
        return hp;
    }
    public Stolowka getStolowka(){
        return stolowka;
    }
    
}
