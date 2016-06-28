package wojna;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Ada
 */
public class Krolestwo extends Thread {

    private Object blokujOkienko = new Object();
    private Object blokujRycerzy = new Object();
    private Object blokujChlopow = new Object();
    private Object blokujGiermkow = new Object();
    
    private Pane okno;
    private int id;
    private Label labelZiemniaki;
    private Label labelRycerz;
    private Label labelKrol;
    private Label labelKsiezniczka;
    private Label labelGiermek;
    private Label labelChlop;
    private Label labelGornik;
    private Label labelRudyZlota;
    private Label labelZloto;
    private Label labelMiedz;
    private Label labelBlyskotki;
    private TextArea komentarze;

    private List<Chlop> listaChlopow;
    private Stolowka stolowka;
    private List<Gornik> listaGornikow;
    private Kopalnia kopalnia;
    private Huta huta;
    private Jubiler jubiler;
    private Ksiezniczka ksiezniczka;
    private List<Rycerz> listaRycerzy;
    private List<Giermek> listaGiermkow;
    private Krol krol;
    private Plaga plaga;

    public Krolestwo() {
    }

    public Krolestwo( Pane okienko, int id, int iloscChlopow, int iloscZiemniakow, int iloscGornikow,
            int iloscRudyZlota, int iloscMiedzi, int kaprysyKsiezniczki, int szczescieKsiezniczki, int iloscRycerzy,
            int hpRycerza, int iloscGiermkow) {

        
        this.okno = okienko;
        this.id = id;
        initOkno();
        wypiszZiemniaki(iloscZiemniakow);

        this.stolowka = new Stolowka(iloscZiemniakow, this);

        
        this.listaChlopow = new ArrayList<Chlop>();
        for (int i = 0; i < iloscChlopow; i++) {
            Chlop nowyChlop = new Chlop(this.stolowka, this);
            nowyChlop.start();
            this.listaChlopow.add(nowyChlop);
        }
        wypiszChlopow();

        this.kopalnia = new Kopalnia(iloscRudyZlota, iloscMiedzi, this);

        wypiszMiedz(iloscMiedzi);
        wypiszZiemniaki(iloscRudyZlota);

        
        this.listaGornikow = new ArrayList<Gornik>();
        for (int i = 0; i < iloscGornikow; i++) {
            Gornik nowyGornik = new Gornik(i, this.kopalnia);
            nowyGornik.start();
            this.listaGornikow.add(nowyGornik);

        }
        wypiszGornikow();

        this.huta = new Huta(this.kopalnia, this);
        this.huta.start();

        this.jubiler = new Jubiler(this, huta);
        this.jubiler.start();

        this.ksiezniczka = new Ksiezniczka(this, kaprysyKsiezniczki, szczescieKsiezniczki, this.jubiler);
        this.ksiezniczka.start();
        wypiszKsiezniczke(szczescieKsiezniczki);

        this.listaRycerzy = new ArrayList<Rycerz>();
        for (int i = 0; i < iloscRycerzy; i++) {
            Rycerz nowyRycerz = new Rycerz(this, this.stolowka, hpRycerza);
            nowyRycerz.start();
            this.listaRycerzy.add(nowyRycerz);

        }
        wypiszRycerzy();

        this.listaGiermkow = new ArrayList<Giermek>();
        for (int i = 0; i < iloscGiermkow; i++) {
            Giermek nowyGiermek = new Giermek(this, this.stolowka);
            nowyGiermek.start();
            this.listaGiermkow.add(nowyGiermek);
        }
        wypiszGiermkow();

        krol = new Krol(this, ksiezniczka);
        krol.start();
        
        plaga=new Plaga(this);
        plaga.start();

    }

    public void wzmocnijRycerzy(int pomoc) {
        synchronized (blokujRycerzy) {
            for (Rycerz rycerz : listaRycerzy) {
                rycerz.wzmocnijRycerza(pomoc);
            }
        }
    }

    public Chlop wezChlopa() {
        synchronized (blokujChlopow) {
            if (listaChlopow.size() >= 1) {
                Chlop danyChlop;
                danyChlop = listaChlopow.get(0);
                listaChlopow.remove(danyChlop);
                return danyChlop;
            } else {
                return null;
            }
        }
    }

    public void dodajChlopa(Chlop chlop) {
        synchronized (blokujChlopow) {
            listaChlopow.add(chlop);
        }
    }

    public Rycerz wezRycerza() {
        synchronized (blokujRycerzy) {
            if (listaRycerzy.size() >= 1) {
                Random generator = new Random();
                int id = generator.nextInt(listaRycerzy.size());
                Rycerz danyRycerz;
                danyRycerz = listaRycerzy.get(id);
                listaRycerzy.remove(danyRycerz);
                return danyRycerz;
            } else {
                return null;
            }
        }
    }

    public void dodajRycerza(Rycerz rycerz) {
        synchronized (blokujRycerzy) {
            listaRycerzy.add(rycerz);
        }
    }

    public Giermek wezGiermka() {
        synchronized (blokujGiermkow) {
            if (listaGiermkow.size() >= 1) {
                Random generator = new Random();
                int id = generator.nextInt(listaGiermkow.size());
                Giermek danyGiermek = listaGiermkow.get(id);
                listaGiermkow.remove(danyGiermek);
                return danyGiermek;
            } else {
                return null;
            }

        }
    }
    public void dodajGiermka(Giermek giermek){
        synchronized (blokujGiermkow){
            listaGiermkow.add(giermek);
        }
    
    }

    private void initOkno() {

        int a=90 + 200 * id;
        komentarze = new TextArea();
        komentarze.setLayoutX(20 + 180 * id);
        komentarze.setLayoutY(270);
        komentarze.setPrefSize(170, 120);
        komentarze.setEditable(false);
        okno.getChildren().add(komentarze);

        labelRycerz = new Label("0");
        labelRycerz.setLayoutX(a);
        labelRycerz.setLayoutY(40);
        okno.getChildren().add(labelRycerz);

        labelKrol = new Label("0");
        labelKrol.setLayoutX(a);
        labelKrol.setLayoutY(60);
        okno.getChildren().add(labelKrol);

        labelKsiezniczka = new Label("0");
        labelKsiezniczka.setLayoutX(a);
        labelKsiezniczka.setLayoutY(80);
        okno.getChildren().add(labelKsiezniczka);

        labelGiermek = new Label("0");
        labelGiermek.setLayoutX(a);
        labelGiermek.setLayoutY(100);
        okno.getChildren().add(labelGiermek);

        labelChlop = new Label("0");
        labelChlop.setLayoutX(a);
        labelChlop.setLayoutY(120);
        okno.getChildren().add(labelChlop);

        labelZiemniaki = new Label("");
        labelZiemniaki.setLayoutX(a);
        labelZiemniaki.setLayoutY(140);
        okno.getChildren().add(labelZiemniaki);

        labelGornik = new Label("0");
        labelGornik.setLayoutX(a);
        labelGornik.setLayoutY(160);
        okno.getChildren().add(labelGornik);

        labelRudyZlota = new Label("0");
        labelRudyZlota.setLayoutX(a);
        labelRudyZlota.setLayoutY(180);
        okno.getChildren().add(labelRudyZlota);

        labelMiedz = new Label("0");
        labelMiedz.setLayoutX(a);
        labelMiedz.setLayoutY(200);
        okno.getChildren().add(labelMiedz);

        labelZloto = new Label("0");
        labelZloto.setLayoutX(a);
        labelZloto.setLayoutY(220);
        okno.getChildren().add(labelZloto);

        labelBlyskotki = new Label("0");
        labelBlyskotki.setLayoutX(a);
        labelBlyskotki.setLayoutY(240);
        okno.getChildren().add(labelBlyskotki);
    }

    public void run() {

        System.out.println("Napis z watku utworzonego");
        System.out.println(listaChlopow.size());

        try {
            Thread.sleep(10000);

        } catch (InterruptedException ex) {
            Logger.getLogger(Krolestwo.class.getName()).log(Level.SEVERE, null, ex);
        }
        //listaChlopow.get(1).setZyje(false);
        //listaChlopow.remove(1);
        System.out.println(listaChlopow.size());
    }

    public void wypiszZiemniaki(int ilosc) {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelZiemniaki.setText(new Integer(ilosc).toString());
                }
            });
        }
    }

    public void wypiszRycerzy() {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelRycerz.setText(new Integer(listaRycerzy.size()).toString());
                }
            });
        }
    }

    public void wypiszKrola(int ilosc) {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelKrol.setText(new Integer(ilosc).toString());
                }
            });
        }
    }

    public void wypiszKsiezniczke(int ilosc) {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelKsiezniczka.setText(new Integer(ilosc).toString());
                }
            });
        }
    }

    public void wypiszGiermkow() {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelGiermek.setText(new Integer(listaGiermkow.size()).toString());
                }
            });

        }
    }

    public void wypiszChlopow() {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelChlop.setText(new Integer(listaChlopow.size()).toString());
                }
            });
        }
    }

    public void wypiszGornikow() {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelGornik.setText(new Integer(listaGornikow.size()).toString());
                }
            });
        }
    }

    public void wypiszRudyZloto(int ilosc) {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelRudyZlota.setText(new Integer(ilosc).toString());
                }
            });
        }
    }

    public void wypiszMiedz(int ilosc) {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelMiedz.setText(new Integer(ilosc).toString());
                }
            });
        }
    }

    public void wypiszZloto(int ilosc) {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelZloto.setText(new Integer(ilosc).toString());
                }
            });
        }
    }

    public void wypiszBlyskotki(int ilosc) {
        synchronized (blokujOkienko) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    labelBlyskotki.setText(new Integer(ilosc).toString());
                }
            });
        }
    }

    public void napiszKomentarz(String napis) {
        synchronized (blokujOkienko) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    komentarze.appendText(napis + "\n");
                }
            });

        }
    }
}
