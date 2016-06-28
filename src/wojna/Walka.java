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
public class Walka extends Thread {
    private Krolestwo niebiesy;
    private Krolestwo czerwoni;
    private Wojna wojna;
    private boolean rozgrywana;
    
    public Walka(Krolestwo niebieskie, Krolestwo czerwone, Wojna wojna){
        rozgrywana=true;
        this.niebiesy=niebieskie;
        this.czerwoni=czerwone;
        this.wojna=wojna;    
    
    }
    public void run(){
        Random generator = new Random();
        Rycerz niebieskiRycerz;
        Rycerz czerwonyRycerz;
        int silaNiebieskich;
        int silaCzerwonych;
        Giermek niebieskiGiernek;
        Giermek czerwonyGiermek;
        
        while(rozgrywana){
             try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Wojna.class.getName()).log(Level.SEVERE, null, ex);
            }
            wojna.napiszKomentarz("Rozpoczyna sie walka!");
            niebieskiRycerz=niebiesy.wezRycerza();
            czerwonyRycerz=czerwoni.wezRycerza();
            niebieskiGiernek=null;
            czerwonyGiermek=null;
            
            if(niebieskiRycerz!=null && czerwonyRycerz!=null){
                String niebieskiNapis="Niebieski rycerz: "+niebieskiRycerz.getHp();
                String czerwonyNapis="Czerwony rycerz: "+ czerwonyRycerz.getHp();
                silaNiebieskich=niebieskiRycerz.getHp();
                silaCzerwonych=czerwonyRycerz.getHp();
                
                if(generator.nextInt(4)==1){
                    czerwonyGiermek=czerwoni.wezGiermka();
                    if(czerwonyGiermek!=null){
                        czerwonyNapis+=" + giermek: "+czerwonyGiermek.getHp();
                        silaCzerwonych+=czerwonyGiermek.getHp();
                    }                    
                }
                if(generator.nextInt(4)==1){
                    niebieskiGiernek=niebiesy.wezGiermka();
                    if(niebieskiGiernek!=null){
                        niebieskiNapis+=" + giermek: "+niebieskiGiernek.getHp();
                        silaNiebieskich+=niebieskiGiernek.getHp();
                    }                    
                }                
                
                wojna.napiszKomentarz(niebieskiNapis+ "\n\t\tVS\n"+czerwonyNapis);
                if(silaCzerwonych>silaNiebieskich){
                    wojna.napiszKomentarz("Wygrywaja czerwoni!");
                    czerwoni.dodajRycerza(czerwonyRycerz);
                    if(czerwonyGiermek!=null){
                        wojna.napiszKomentarz("Czerwony giermek staje sie rycerzem");
                        czerwonyGiermek.setZyje(false);
                        Rycerz nowy=new Rycerz(czerwoni, czerwonyRycerz.getStolowka(), 10);
                        czerwoni.dodajRycerza(nowy);
                    }
                    niebieskiRycerz.setZyje(false);
                    if(niebieskiGiernek!=null)
                        niebieskiGiernek.setZyje(false);              
                
                }
                else if(silaCzerwonych<silaNiebieskich){
                    wojna.napiszKomentarz("Wygrywaja niebiescy!");
                    niebiesy.dodajRycerza(niebieskiRycerz);
                    if(niebieskiGiernek!=null){
                        wojna.napiszKomentarz("Niebieski giermek staje sie rycerzem");
                        niebieskiGiernek.setZyje(false);
                        Rycerz nowy= new Rycerz(niebiesy, niebieskiRycerz.getStolowka(), 10);
                        niebiesy.dodajRycerza(nowy);                    
                    }
                    czerwonyRycerz.setZyje(false);
                    if(czerwonyGiermek!=null)
                        czerwonyGiermek.setZyje(false);   
                }
                else{
                wojna.napiszKomentarz("Remis!");
                niebieskiRycerz.setZyje(false);
                czerwonyRycerz.setZyje(false);
                if(czerwonyGiermek!=null)
                    czerwonyGiermek.setZyje(false);
                if(niebieskiGiernek!=null)
                    niebieskiGiernek.setZyje(false);                
                }        
            }
            niebiesy.wypiszRycerzy();
            czerwoni.wypiszRycerzy();
            niebiesy.wypiszGiermkow();
            czerwoni.wypiszGiermkow();
            if(niebieskiRycerz==null&&czerwonyRycerz==null){
               wojna.napiszKomentarz("Oba krolestwa nie posiadaja juz wojska!"); 
               rozgrywana=false;
               return;
            }
            if(niebieskiRycerz==null){
                wojna.napiszKomentarz("Wojne wygrywaja czerwoni!"); 
               rozgrywana=false;            
            }
            if(czerwonyRycerz==null){
                wojna.napiszKomentarz("Wojne wygrywaja niebiescy!"); 
               rozgrywana=false;
            }
        }
    
    }
    
}
