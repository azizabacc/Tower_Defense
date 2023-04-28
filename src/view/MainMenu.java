package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class MainMenu extends Application {

    static Stage mainMenuStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        //music();
        mainMenuStage = primaryStage;
        StackPane root = new StackPane();
        root.setId("pane");
        VBox buttonView = addContent();
        root.getChildren().add(buttonView);
        Scene scene = new Scene(root,1280,960);
        scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
        primaryStage.setTitle("TowerDefense");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private static VBox addContent() {
        VBox box = new VBox();
        box.prefWidth(600);
        box.setAlignment(Pos.CENTER);
        box.setSpacing(300);
        VBox buttonContainer = new VBox();
        buttonContainer.setSpacing(20);
        buttonContainer.setAlignment(Pos.CENTER);
        Label startGameButton = addStartButton();
        Label editorButton = addEditorButton();
        Label helpButton = addHelpButton();
        Label quitButton = addQuitButton();
        buttonContainer.getChildren().addAll(startGameButton, editorButton,helpButton,addQuitButton());
        box.getChildren().addAll( buttonContainer);

        return box;}
    private static Label addStartButton() {
        Label startGameButton = new TileLabel("StartButton","res0",160,40).getLabel();
     startGameButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                Level1 game = new Level1();
                try {
                    game.start(Level1.Stage);
                }
                catch (Exception e) {
                }
                mainMenuStage.close();}
            });

        return startGameButton;
    }
    private static Label addEditorButton() {
        Label editorButton =  new TileLabel("editorButton","res0",160,40).getLabel();
        editorButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                Editor editor = new Editor();
                try {
                    editor.start(Help.helpStage);
                }
                catch (Exception e) {
                }
                mainMenuStage.close();}
            });

        return editorButton;
    }

    private static Label addHelpButton() {
        Label helpButton = new TileLabel("helpButton","res0",160,40).getLabel();
        helpButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                Help help = new Help();
                try {
                    help.start(Help.helpStage);
                }
                catch (Exception e) {
                }
                mainMenuStage.close();}
            });
        return helpButton;
    }
    private static Label addQuitButton() {

        Label quitGameButton =  new TileLabel("QuitButton","res0",160,40).getLabel();
        quitGameButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                mainMenuStage.close();
            }
        });
        return quitGameButton;
    }
    public void music(){
        String path = "src/view/pirate.mp3";
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        //AudioClip sound = new AudioClip(this.getClass().getResource(path).toString());
        //sound.play();
    }
    public static void main(String[] args) {launch(args);}


}




