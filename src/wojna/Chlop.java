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
public class Chlop extends Thread {

    
    private boolean zyje;
    private Stolowka stolowka;
    private Krolestwo krolestwo;

    public Chlop( Stolowka stolowka, Krolestwo krolestwo) {
        
        this.zyje = true;
        this.stolowka = stolowka;
        this.krolestwo = krolestwo;

    }

    public void setZyje(boolean przezyl) {
        zyje = przezyl;
    }

    public void run() {
        Random generator = new Random();
        int czekaj;
        while (zyje) {
            czekaj = generator.nextInt(10) + 1;
            try {
                Thread.sleep(czekaj * 1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Chlop.class.getName()).log(Level.SEVERE, null, ex);
            }

            stolowka.dodajZiemniaka();
            try {
                Thread.sleep(1500);

            } catch (InterruptedException ex) {
                Logger.getLogger(Chlop.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (generator.nextInt(7) == 1) {
                krolestwo.napiszKomentarz("Rodzi sie nowy chlop");
                Chlop nowy= new Chlop(stolowka, krolestwo);
                nowy.start();
                krolestwo.dodajChlopa(nowy);
                krolestwo.wypiszChlopow();
            } else {
                if (generator.nextInt(10) == 1) {
                    krolestwo.napiszKomentarz("Chlop staje sie giermkiem");
                    Giermek nowy=new Giermek(krolestwo, stolowka);
                    nowy.start();
                    krolestwo.wezChlopa().setZyje(false);
                    krolestwo.dodajGiermka(nowy);
                    krolestwo.wypiszChlopow();
                    krolestwo.wypiszGiermkow();
                   
                }
            }
        }

    }
}
