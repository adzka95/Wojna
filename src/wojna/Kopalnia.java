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
public class Kopalnia extends Thread {

    private int rudyZlota;
    private int miedz;
    private Krolestwo krolestwo;

    public Kopalnia(int rudyZlota, int miedzi, Krolestwo krolestwo) {
        this.rudyZlota = rudyZlota;
        this.miedz = miedzi;
        this.krolestwo = krolestwo;

    }

    public synchronized void dodajMaterialy(int iloscRudZlota, int iloscMiedzi) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Kopalnia.class.getName()).log(Level.SEVERE, null, ex);
        }
        rudyZlota += iloscRudZlota;
        miedz += iloscMiedzi;
        krolestwo.wypiszRudyZloto(rudyZlota);
        krolestwo.wypiszMiedz(miedz);
        if (rudyZlota >= 5 && miedz >= 4) {
            notify();
        }
    }

    public synchronized void pobierzZamowienie() {
        if (rudyZlota < 5 || miedz < 4) {
            krolestwo.napiszKomentarz("Huta czeka na zamowienie");
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Kopalnia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        rudyZlota -= 5;
        miedz -= 4;
    }

}
