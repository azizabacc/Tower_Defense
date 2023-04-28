package view;

import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class GameUI {
    private boolean holdingTower;
    private Tower tempTower;
    private Tower[] towertypes;
    private TileLabel[] TilesLabel;
    private int level;
    private boolean deleted = false;
    private boolean WantToUpgrade = false;
    private Tile[][] tileMap;

    private int[][] mapOfMyTowers = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};




    public GameUI() {
        tileMap = new Tile[15][15];
        Player.getInstance().setup(Player.getInstance().getGameLevel());
        //towerList = player.getTowers() ;
        this.level=level;
        //this.mapName = "src/MAPS/MAP1";
        this.towertypes = new Tower[3];
        this.towertypes[0] = new TowerClassic(TowerType.CannonClassic,new Tile(0,0,TileType.Grass));
        this.towertypes[1] = new TowerLaser(TowerType.CannonLaser,new Tile(0,0,TileType.Grass)) ;
        this.towertypes[2] = new TowerLaser(TowerType.CannonRocket,new Tile(0,0,TileType.Grass)) ;
        this.TilesLabel = new TileLabel[3];
        this.holdingTower=false;
        this.tempTower=null;

    }
    public TileLabel[] generateLabelList(){
        for (int i=0;i<towertypes.length;i++){
            TilesLabel[i]=(new TileLabel(towertypes[i].getType().getTextureName(),towertypes[i].getType().getFolderName(),40,40));
        }

        return TilesLabel;}


    public void addGrid(String folderName){
        Grid.getInstance().GridAppendMap(folderName);
        tileMap = Grid.getInstance().getTilesMap();

        Grid.getInstance().getGrid().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                int colonneIndex = (int) (Math.floor(e.getX() / 40));
                int ligneIndex = (int) (Math.floor(e.getY() - 1) / 40);
                if (deleted){ //If pour effacer la tour
                    Grid.getInstance().setTile(colonneIndex, ligneIndex,TileType.Grass);
                    Tower towerdelete =getTower(e);
                    Player.getInstance().getTowers().remove(towerdelete);
                    mapOfMyTowers[colonneIndex][ligneIndex] = 0;
                    Player.getInstance().setCoins(+towerdelete.getCost()-4);
                    deleted = false;
                }if (WantToUpgrade && mapOfMyTowers[colonneIndex][ligneIndex] == 1){ //If pour améliorer la tour
                    getTower(e).levelup();
                    Player.getInstance().setCoins(-tempTower.getCost());
                    WantToUpgrade = false;
                }if (!holdingTower && !WantToUpgrade && !deleted){ //If pour afficher les informations de la tour
                    PopUp.display(getTower(e));
                }if (holdingTower && mapOfMyTowers[colonneIndex][ligneIndex] !=1 && Grid.getInstance().getTile(ligneIndex, colonneIndex).getType().isBuildable()){ //If pour placer la tour
                    Grid.getInstance().setTileTower(colonneIndex, ligneIndex, tempTower.getType().getTileType());
                    Grid.getInstance().setTileCanon(colonneIndex,ligneIndex,tempTower.getType());
                    tempTower.setX(colonneIndex);
                    tempTower.setY(ligneIndex);
                    Player.getInstance().getTowers().add(tempTower);
                    System.out.println("gamui   "+Player.getInstance().getTowers().size());
                    mapOfMyTowers[colonneIndex][ligneIndex] = 1;
                    Player.getInstance().setCoins(-tempTower.getCost());
                    holdingTower = false;
                }else{
                    System.out.println("Action non valide");
                }
            }
        });
    }

    private Tower getTower(MouseEvent e) {
        int colonneIndex = (int) (Math.floor(e.getX() / 40));
        int ligneIndex = (int) (Math.floor(e.getY() - 1) / 40);
        for (Tower t : Player.getInstance().getTowers()) {
            if (t.getX() == colonneIndex && t.getY() == ligneIndex) {
                return t;
            }
        }
        return null;
    }

    public Label addLvlUpButton(){
        Label LvlUpButton = new TileLabel("Upgrade", "res0",40,40).getLabel();
        LvlUpButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                WantToUpgrade = true;
            }
        });
        return LvlUpButton;
    }

    public Label addRecycleButton(){
        Label RecycleButton = new TileLabel("Recycle", "res0",40,40).getLabel();
        RecycleButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deleted = true;
            }
        });
        return RecycleButton;
    }

    public Label[] addInfos(){
        Label score = new Label();
        score.textProperty().bind(Bindings.convert(Player.getInstance().ScoreProperty()));
        score.setTextFill(Color.rgb(250,36,33));
        ImageView imageView =new ImageView(new Image("res/res0/coeur.png"));
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        score.setGraphic(imageView);


        Label coins = new Label();
        coins.textProperty().bind(Bindings.convert(Player.getInstance().CoinsProperty()));
        coins.setTextFill(Color.rgb(250,36,33));
        ImageView imageView1 =new ImageView(new Image("res/res0/coin.png"));
        imageView1.setFitWidth(20);
        imageView1.setFitHeight(20);
        coins.setGraphic(imageView1);

        Label waveNumber = new Label();
        waveNumber.textProperty().bind(Bindings.convert(Player.getInstance().WaveNumberProperty()));
        waveNumber.setTextFill(Color.rgb(250,36,33));
        ImageView imageView2 =new ImageView(new Image("res/res0/wave.jpg"));
        imageView2.setFitWidth(20);
        imageView2.setFitHeight(20);
        waveNumber.setGraphic(imageView2);


        Label Level = new Label();
        Level.textProperty().bind(Bindings.convert(Player.getInstance().LevelProperty()));
        ImageView imageView3 =new ImageView(new Image("res/res0/level.png"));
        imageView3.setFitWidth(20);
        imageView3.setFitHeight(20);
        Level.setGraphic(imageView3);

        Level.setTextFill(Color.rgb(250,36,33));
        return new Label[]{score, coins, waveNumber, Level};
    }

    public TilePane addTileMenu(){  ///int[] index
        TilesLabel=generateLabelList();
        TilePane vb = new TilePane();
        vb.setPadding(new Insets(60, 5, 5, 5));
        vb.setId("TileMenu");
        vb.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
        vb.setHgap(2);
        vb.setVgap(10);
        vb.setMaxHeight(600);
        vb.setMaxWidth(160);
        for (int i = 0; i < TilesLabel.length; i++) {
            vb.getChildren().add(TilesLabel[i].getLabel());
        }
        vb.getChildren().addAll(addLvlUpButton(),addRecycleButton());


        for (TileLabel l : TilesLabel) {
            l.getLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent e) {
                    //System.out.println("tower clicked " + l.getImageName());
                    for(int i=0;i<towertypes.length;i++) {
                        //System.out.println(towertypes[i].getType().getTextureName());
                        if (l.getImageName() == towertypes[i].getType().getTextureName()) {
                            holdingTower = true;
                            //System.out.println("Tower picked is really " + getTempTower(l).getType().getTextureName());
                            tempTower = getTempTower(l);
                        }
                    }
                }
            });
        }
        return vb;
    }

    public Tower getTempTower(TileLabel label){
        if(label.getImageName()=="paveTowerSlowLvl3"){
            return new TowerLaser(TowerType.CannonLaser,new Tile(0,0,TileType.Grass)) ;
        }else if(label.getImageName()=="paveTowerNormLvl1") {
            return new TowerClassic(TowerType.CannonClassic, new Tile(0, 0, TileType.Grass));
        }else if(label.getImageName()=="paveTowerNormLvl2") {
            return new TowerRocket(TowerType.CannonRocket, new Tile(0, 0, TileType.Grass));
        }
        return null;
    }

    public BorderPane addBorderPane(GridPane g,TilePane t,TilePane tilePane){
        BorderPane root = new BorderPane();
        root.setCenter(g);
        root.setLeft(t);
        root.setTop(tilePane);
        root.setId("borderPaneEditor");
        root.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
        return root;
    }
    public TilePane addtilepane2(){
        TilePane tilePane =new TilePane();
        //tilePane.setAlignment(Pos.CENTER);
        tilePane.setHgap(50);
        for (int i=0; i < addInfos().length; i++){
            tilePane.getChildren().add(addInfos()[i]);
        }
        return tilePane;
    }


}
