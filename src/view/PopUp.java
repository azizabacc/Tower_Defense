package view;

import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.stage.Popup;
import model.Tower;

import static javafx.scene.paint.Color.BLUEVIOLET;

public class PopUp {
    public static void display(Tower Tower) {
        Stage popupwindow=new Stage();


        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Info Tourelle");


        Label label1 =new Label(Tower.getType().getTowerProperName(Tower));
        label1.setFont(Font.font("Cambria",20));
        label1.setTextFill(Color.web("#0076a3"));
        label1.setTranslateX(20);
        //label2.setTranslateY(-20);

        Label label2 =new Label("Niveau de la tourelle : " + Tower.getLevel() + "\n" +
                "Portée de la tourelle : " + Tower.getType().getInfosOfTower()[0] + "\n" );
        //label2.setTranslateY(10);
        label2.setTranslateX(10);
        label2.setMaxWidth(220);
        label2.setWrapText(true);

        Label label3 = new Label("Coûts d'amélioration de la tourelle : -" + Tower.getCost() + " coins");
        label3.setTextFill(Color.rgb(40,150,40));
        label3.setTranslateY(10);
        label3.setTranslateX(5);

        Label label4 =new Label("Bonus recyclage : +" + (Tower.getType().getInfosOfTower()[1])/2 +" Coins");
        label4.setTextFill(Color.rgb(175,40,40));
        //label4.setTranslateY(10);
        label4.setTranslateX(5);

        //Button button1= new Button("Close");
        //button1.setOnAction(e -> popupwindow.close());


        VBox layout= new VBox(10);
        layout.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE,null,null)));

        layout.getChildren().addAll(label1,label2,label3,label4);


        Scene scene1= new Scene(layout, 250, 160);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }
}

