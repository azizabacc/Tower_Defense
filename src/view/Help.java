package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;


public class Help extends Application {

    static Stage helpStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //music();
        helpStage = primaryStage;

        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);


        VBox content = new VBox();
        content.maxWidth(750);
        content.maxHeight(450);
        content.setSpacing(10);
        content.setPadding(new Insets(10, 10, 10, 10));
        content.setAlignment(Pos.CENTER);
        content.setStyle("-fx-background-color: rgba(20, 20, 20, 0.5);");

        Label title = new Label("COMMENT JOUER?");
        title.setTextFill(Color.rgb(250,250,250));
        title.setFont(Font.font(null, 30));

        ScrollPane ruleContainer = new ScrollPane();

        VBox rules = new VBox();
        rules.setPadding(new Insets(5, 5, 5, 5));
        rules.setSpacing(10);
        rules.setAlignment(Pos.CENTER); // POUR LES VALEURS, FAIRE AVEC GET COIN ET GET SCORE ET GET AMOUNT OF WAVE, PAS EN STRING
        rules.setStyle("-fx-background-color: rgba(20, 20, 20, 0.5);");


        //RULE 1 ------------------------------------------------------------------------------------------------------------------------------
        Text rulePart1 = new Text("     Dans un Tower Defense, vous devez empêcher des Ennemis de parvenir à la fin d'un certain parcours. Vous disposez pour cela de différentes " +
                "Tourelles que vous pouvez placer sur la carte et qui leur infligeront des dégats. Chaque Ennemi tué vous rapportera de l'argent, ce qui vous permettra "+
                "d'acheter ou d'améliorer les tourelles. Cependant, chaque Ennemi qui survie vous retira un point de vie. Vous disposez au départ de :\n" +
                "\n- 10 points de vie  \n" +
                "\n- 200 pièces\n" +
                "  \n   Le jeu se déroule dans deux Mondes différents (Terre et Glace), où les Ennemis apparaîtront par vague. Il y a 3 vagues par Monde, et toujours "+
                " peuplées de 3 Ennemis. A chaque niveau, vous partez avec 10 points de vie, et vos anciennes tourelles disparaîtront. Si vous parvenez au bout " +
                "des 2 Mondes, vous avez gagné !!");

        rulePart1.setWrappingWidth(800);
        rulePart1.setFont(Font.font(null, 16));
        rulePart1.setFill(Color.rgb(250,250,250));


        //RULE 2 ------------------------------------------------------------------------------------------------------------------------------
        Text rulePart2 = new Text("  Afin de pimenter votre jeu, certains ennemis disposent d'abilités uniques qui leurs permettent de gagner de la vie à partir d'un certain moment. " +
                "");
        rulePart2.setWrappingWidth(800);
        rulePart2.setFont(Font.font(null, 16));
        rulePart2.setFill(Color.rgb(250,250,250));


        //RULE 3 ------------------------------------------------------------------------------------------------------------------------------
        Text rulePart3 = new Text("Il existe 3 types de Tourelles : \n" +
                "- La Tourelle classique, infligeant régulièrement des dégats aux ennemis mais elle ne peut pas tous les attaquer. \n" +
                "- La Tourelle Rocket, infligeant plus de dégats aux ennemis mais ayant un nombre limité de munitions. \n" +
                "- La Tourelle Laser, infligeant plus de dégats aux ennemis mais elle ne peut pas tous les attaquer. \n" +
                "Lorsque vous êtes sur une Tourelle, vous pouvez : \n" +
                "\n- Cliquer dessus pour afficher les informations de la Tourelle ; \n" +
                "- Appuyer sur la touche DELETE, avant de cliquer sur la Tourelle, pour l'effacer ; \n" +
                "- Appuyer sur la touche Flèche-UP, avant de cliquer sur la Tourelle, pour l'augmenter d'un niveau. \n");
        rulePart3.setWrappingWidth(800);
        rulePart3.setFont(Font.font(null, 16));
        rulePart3.setFill(Color.rgb(250,250,250));

        //RULE 4 ------------------------------------------------------------------------------------------------------------------------------
        Text rulePart4 = new Text("           L'Editeur vous permet de modifier une carte à votre sauce et l'enregistrer. Toutefois, il est déconseillé d'utiliser \n"+"       cette fonctionnalité car les ennemis ne pourront pas suivre le chemin choisi." +

                "\n \n \n                                                                                                BON JEU!!!");
        rulePart4.setWrappingWidth(900);
        rulePart4.setFont(Font.font(null, 16));
        rulePart4.setFill(Color.rgb(250,250,250));

        // ------------------------------------------------------------------------------------------------------------------------------------


        rules.getChildren().addAll(rulePart1, rulePart2, rulePart3, rulePart4);
        ruleContainer.setContent(rules);

        Button backButton = new Button("BACK TO MENU");
        backButton.setOnAction(event -> {
            MainMenu mainMenuScreen = new MainMenu();
            try {
                mainMenuScreen.start(MainMenu.mainMenuStage);
            } catch (Exception e) {

            }
            helpStage.close();
        });

        content.getChildren().addAll(title, ruleContainer, backButton);

        root.getChildren().addAll(content);

        Scene scene = new Scene(root,950,700);
        scene.getStylesheets().add(Help.class.getResource("application.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    public void music(){
        String path = "src/view/pirate.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        //AudioClip sound = new AudioClip(this.getClass().getResource(path).toString());
        //sound.play();
    }
}
