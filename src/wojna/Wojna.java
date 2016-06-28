/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wojna;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ada
 */
public class Wojna extends Application {

    private Pane okno = new Pane();
    private Krolestwo niebiescy;
    private Krolestwo czerwoni;
    private TextArea komentarze;
    private Walka walka;

    @Override
    public void start(Stage primaryStage) {

        Scene scene = new Scene(okno, 400, 550);
        initOkno();
        
        niebiescy = new Krolestwo(okno, 0, 20, 10, 3, 2,2,10,4,25,10, 3);
        niebiescy.start();
        czerwoni = new Krolestwo(okno, 1, 15, 15, 10,1,1,4,5,25,10,1);
        czerwoni.start();
        walka=new Walka(niebiescy, czerwoni, this);
        walka.start();
        primaryStage.setTitle("Krolestwa");
        primaryStage.setScene(scene);
        primaryStage.show();
//        
    }
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    private void initOkno(){
        
        Label niebiescy =new Label("Niebiescy:");
        niebiescy.setLayoutX(65);
        niebiescy.setLayoutY(13);
        niebiescy.setStyle("-fx-text-fill: blue;" + "-fx-font-weight: bold;"+"-fx-font-size: 15");
        okno.getChildren().add(niebiescy);
        
        Label czerwoni =new Label("Czerwoni:");
        czerwoni.setLayoutX(265);
        czerwoni.setLayoutY(13);
        czerwoni.setStyle("-fx-text-fill: red;" + "-fx-font-weight: bold;"+"-fx-font-size: 15");
        okno.getChildren().add(czerwoni);
        
        Label wojna =new Label("Wojna:");
        wojna.setLayoutX(170);
        wojna.setLayoutY(395);
        wojna.setStyle("-fx-text-fill: brown;" + "-fx-font-weight: bold;"+"-fx-font-size: 15");
        okno.getChildren().add(wojna);
        
        komentarze=new TextArea();
        komentarze.setLayoutX(10);
        komentarze.setLayoutY(420);
        komentarze.setPrefSize(370, 120);
        komentarze.setEditable(false);
        okno.getChildren().add(komentarze);
        
        int a=170;
        Label labelRycerz= new Label("Rycerze");
        labelRycerz.setLayoutX(a);
        labelRycerz.setLayoutY(40);
        okno.getChildren().add(labelRycerz);
        
        Label labelKrol= new Label("Krol");
        labelKrol.setLayoutX(a);
        labelKrol.setLayoutY(60);
        okno.getChildren().add(labelKrol);
        
        Label labelKsiezniczka= new Label("Ksiezniczka");
        labelKsiezniczka.setLayoutX(a);
        labelKsiezniczka.setLayoutY(80);
        okno.getChildren().add(labelKsiezniczka);
        
        Label labelGiermek= new Label("Giermkowie");
        labelGiermek.setLayoutX(a);
        labelGiermek.setLayoutY(100);
        okno.getChildren().add(labelGiermek);
        
        Label labelChlop= new Label("Chlopi");
        labelChlop.setLayoutX(a);
        labelChlop.setLayoutY(120);
        okno.getChildren().add(labelChlop);
        
        Label labelZiemniaki= new Label("Ziemniaki");
        labelZiemniaki.setLayoutX(a);
        labelZiemniaki.setLayoutY(140);
        okno.getChildren().add(labelZiemniaki);
        
        Label labelGornik= new Label("Gornikow");
        labelGornik.setLayoutX(a);
        labelGornik.setLayoutY(160);
        okno.getChildren().add(labelGornik);
        
        Label labelRudyZlota= new Label("Rudy zlota");
        labelRudyZlota.setLayoutX(a);
        labelRudyZlota.setLayoutY(180);
        okno.getChildren().add(labelRudyZlota);
        
        Label labelMiedz= new Label("Miedz");
        labelMiedz.setLayoutX(a);
        labelMiedz.setLayoutY(200);
        okno.getChildren().add(labelMiedz);
        
        Label labelZloto= new Label("Zloto");
        labelZloto.setLayoutX(a);
        labelZloto.setLayoutY(220);
        okno.getChildren().add(labelZloto);
        
        Label labelBlyskotki= new Label("Blyskotki");
        labelBlyskotki.setLayoutX(a);
        labelBlyskotki.setLayoutY(240);
        okno.getChildren().add(labelBlyskotki);
    }

    public synchronized void napiszKomentarz(String napis){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                komentarze.appendText(napis+"\n");
            }});
    
    
    }
}
