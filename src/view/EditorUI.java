package view;

import controller.LevelTiles;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Grid;
import model.Player;
import model.TileType;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import static controller.MapsController.SaveMaps;
import static view.Editor.EditorStage;

public class EditorUI {
    private TileType[] types;
   // private String mapName;
    private TileLabel[] TilesLabel;
    private Grid grid;
    private static int [][] map;
    private String myMapForEditor;


    public EditorUI() {
        //this.mapName = "src/MAPtruc";
        this.types = new TileType[11];
        this.types[0] = TileType.Grass;
        this.types[1] = TileType.RoadVerti;
        this.types[2] = TileType.Water;
        this.types[3] = TileType.RoadHoriz;
        this.types[4] = TileType.RoadCornerTL;
        this.types[5] = TileType.RoadCornerTR;
        this.types[6] = TileType.RoadCornerBR;
        this.types[7] = TileType.RoadCornerBL;
        this.types[8] =TileType.GrassDeco1;
        this.types[9] =TileType.GrassDeco2;
        this.types[10] =TileType.GrassDeco3;
        this.TilesLabel = new TileLabel[11];
    }


    public TileLabel[] generateLabelList(){
        for (int i=0;i<types.length;i++){
            TilesLabel[i]=(new TileLabel(types[i].getTextureName(), LevelTiles.getInstance().getFolderName(),40,40));
            }
        return TilesLabel;}

    public void addGrid(int[] index,String folderName){
        GridPane g = Grid.getInstance().getGrid();
        Grid.getInstance().GridAppendMap(folderName);
        g.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int colonneIndex = (int) (Math.floor(e.getX() / 40));
                int ligneIndex = (int) (Math.floor(e.getY() - 1) / 40);
                Grid.getInstance().setTile(colonneIndex, ligneIndex, types[index[0]]);
            }
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()== KeyCode.S){
                    SaveMaps(myMapForEditor,map);
                    System.out.println("Saved!");
                }else if(keyEvent.getCode()==KeyCode.M) {
                    SaveMaps(myMapForEditor,map);
                    System.out.println("Saved! Back to Menu");
                    MainMenu mainMenuScreen = new MainMenu();
                    try { mainMenuScreen.start(MainMenu.mainMenuStage);} catch (Exception e){
                    }
                }
            }
        });
    }

    public Label addSaveButton(){
        map=Grid.getInstance().getMap();
        myMapForEditor=LevelTiles.getInstance().getMapName();
        Label startButton = new TileLabel("SaveButton","res0",150,35).getLabel();
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            MainMenu mainMenu = new MainMenu();
            try {
                SaveMaps(myMapForEditor,map);
                System.out.println("Saved!");
                mainMenu.start(MainMenu.mainMenuStage);
            }
            catch (Exception e) {
            }
            LevelTiles.getInstance().setMapName("MAP1");
            EditorStage.close();}
    });
        return startButton;
    }

    public TilePane addTileMenu(int[] index){
        TilesLabel=generateLabelList();
        TilePane vb = new TilePane();
        vb.setPadding(new Insets(60, 5, 5, 5));
        vb.setId("TileMenu");
        vb.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
        vb.setHgap(5);
        vb.setVgap(5);
        vb.setMaxHeight(600);
        vb.setMaxWidth(160);
        for (int i = 0; i < TilesLabel.length; i++) {
            vb.getChildren().add(TilesLabel[i].getLabel());
        }
        vb.getChildren().add(addSaveButton());
        for (TileLabel l : TilesLabel) {
            l.getLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    for(int i=0;i<types.length;i++){
                        if(l.getImageName()==types[i].getTextureName()){
                            index[0] =i;
                        }
                    }
                }
            });
        }
        return vb;
    }

    public BorderPane addBorderPane(GridPane g, TilePane t){
        BorderPane root = new BorderPane();
        root.setCenter(g);
        root.setLeft(t);
        root.setId("borderPaneEditor");
        root.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
        return root;
    }
}

