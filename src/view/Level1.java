package view;

import controller.LevelTiles;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;
import model.*;

import static javafx.scene.paint.Color.MEDIUMAQUAMARINE;


public class Level1 extends Application {
    public static Stage Stage = new Stage();
    private static String res = LevelTiles.getInstance().getFolderName();
    boolean startWave = false ;
    boolean paused = false;
    public GridPane pane;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Stage.setTitle("Tower Defense");
        Stage = primaryStage;
        System.out.println("dans level1   "+Player.getInstance().getGameLevel());

        BorderPane root = new BorderPane();

        GameUI gameUI = new GameUI();

        gameUI.addGrid(res);

        TilePane menu = gameUI.addTileMenu();
        TilePane tilePane =gameUI.addtilepane2();
        tilePane.setId("infobar");
        this.pane= Grid.getInstance().getGrid();

        root = gameUI.addBorderPane(pane, menu,tilePane);
        Label start = addStartButton();
        menu.setAlignment(Pos.CENTER);
        menu.getChildren().add(start);
        Label pause = addPauseButton();
        menu.getChildren().add(pause);

        Scene scene = new Scene(root, 760, 620);
        Stage.setScene(scene);
        Stage.setResizable(true);
        Stage.show();
        BorderPane finalRoot = root;
        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
        if (startWave){
            WaveManager waveManager= new WaveManager(2);
            for (int i = 0; i < Player.getInstance().getEnemies().size(); i++) {
                Player.getInstance().getEnemies().get(i).changeHealth(Player.getInstance().getEnemies().get(i).getType().getHealth()*Player.getInstance().getGameLevel()/2);
            }
            finalRoot.getChildren().addAll(Player.getInstance().getEnemies().get(0).getImv(),Player.getInstance().getEnemies().get(1).getImv(), Player.getInstance().getEnemies().get(2).getImv());
            waveManager.update();

            pause.setOnMouseClicked((new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    finalRoot.setEffect(new GaussianBlur());
                    waveManager.getParallelTransition().pause();
                    Player.getInstance().setPause(true);
                    VBox pauseRoot = new VBox(5);
                    Label help = addMenuButton();
                    pauseRoot.getChildren().add(help);
                    pauseRoot.setBackground(new Background(new BackgroundFill(MEDIUMAQUAMARINE,null,null)));
                    pauseRoot.setAlignment(Pos.CENTER);
                    pauseRoot.setPadding(new Insets(20));
                    Label resume = addStartButton();
                    pauseRoot.getChildren().add(resume);

                    Stage popupStage = new Stage(StageStyle.TRANSPARENT);
                    popupStage.initOwner(primaryStage);
                    popupStage.initModality(Modality.APPLICATION_MODAL);
                    popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));


                    resume.setOnMouseClicked(event -> {
                        finalRoot.setEffect(null);
                        waveManager.getParallelTransition().play();
                        Player.getInstance().setPause(false);
                        popupStage.hide();
                    });

                    popupStage.show();
                };
                }));
            startWave=false;
        } }};
        gameLoop.start();


    }
    public Label addStartButton(){
        Label startButton = new TileLabel("StartButton","res0",150,40).getLabel();
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!startWave){
                    startWave =true;
                    startButton.setDisable(true);
                }else if(startWave && Player.getInstance().getpause()){
                    Player.getInstance().setPause(false);
                    startButton.setDisable(false);
            }
                else {
                    System.out.println("La partie a déjà commencé ");
                }
            }
        });
        return startButton;
    }
    public Label addMenuButton(){
        Label menuButton = new TileLabel("helpButton","res0",150,40).getLabel();
        menuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Help help = new Help();
                try {
                    help.start(Help.helpStage);
                }
                catch (Exception e) {
                }
            }
        });
        return menuButton;
    }
    public Label addPauseButton(){
        Label pauseButton = new TileLabel("PauseButton", "res0",40,40).getLabel();
        pauseButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!paused){
                    paused =true;
                }
                else {
                    System.out.println("La partie a déjà commencé ");
                }
            }
        });
        return pauseButton;
    }


    public static void main (String[] args){
        launch(args);
    }
}

