package view;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
public class GameOver extends Application {
    public static Stage GameOver = new Stage();
    @Override
    public void start(Stage primaryStage) throws Exception {
        music();
        GameOver = primaryStage;
        StackPane root = new StackPane();
        root.setAlignment(Pos.CENTER);
        root.setId("GameOver");
        VBox buttonView = addContent();
        root.getChildren().add(buttonView);
        Scene scene = new Scene(root, 1280, 960);
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
        Label startGameButton = addMenuButton();
        Label quitButton = addQuitButton();
        buttonContainer.getChildren().addAll(startGameButton,quitButton);
        box.getChildren().addAll( buttonContainer);
        return box;}

    private static Label addMenuButton() {
        Label editorButton =  new TileLabel("StartButton","res0",160,40).getLabel();
        editorButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                MainMenu mainMenu = new MainMenu();
                try {
                    mainMenu.start(MainMenu.mainMenuStage);
                }
                catch (Exception e) {
                }
                GameOver.close();}
        });
        return editorButton;
    }
    private static Label addQuitButton() {
        Label quitGameButton =  new TileLabel("QuitButton","res0",160,40).getLabel();
        quitGameButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                GameOver.close();
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
}
