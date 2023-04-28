package view;

import com.sun.javafx.logging.PlatformLogger;
import controller.LevelTiles;
import model.Grid;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class Editor extends Application {
    static Stage EditorStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        final int[] index = new int[1];
        EditorStage = primaryStage;
        LevelTiles.getInstance().setMapName("MAP2");
        EditorUI editor = new EditorUI();
        editor.addGrid(index, LevelTiles.getInstance().getFolderName());
        TilePane editorMenu = editor.addTileMenu(index);
        editorMenu.setPrefWidth(160);
        Grid.getInstance().getGrid().setPrefWidth(600);
        BorderPane root = editor.addBorderPane(Grid.getInstance().getGrid(),editorMenu);
        EditorStage.setTitle("Tower Defense EDITOR");
        Scene scene = new Scene(root, 760, 600);
        EditorStage.setScene(scene);
        EditorStage.setResizable(true);
        EditorStage.show();
        }

        public static void main (String[] args){
            launch(args);
        }
    }
