package model;

import controller.LevelTiles;
import controller.MapsController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import sample.LayoutSample;

import static controller.MapsController.FindTile;

public  class Grid extends GridPane {
    private static Grid Grid=null ;
    private Tile[][] tilesMap;
    private TileType towerType;
    private ImageView imv;
    private int gridWide=15;
    private int gridHigh=15;
    private int TILE_SIZE =40;
    private GridPane grid;
    private int[][] map;
    private Grid(){
        this.grid = new GridPane();
    }

    public static Grid getInstance() {
        if (Grid==null){
            Grid = new Grid();
        }
        return Grid;
    }

    public void GridAppendMap(String folderName) {
        gridRowColum(grid);
        map=new int[gridHigh][gridWide];
        map=MapsController.loadMyMap(LevelTiles.getInstance().getMapName());
        //map=MapsController.loadMyMap(mapName);
        //System.out.println("dans grid   "+mapName);
        tilesMap=new Tile[gridHigh][gridWide];
        for (int j = 0; j < gridHigh; j++) {
            for (int i = 0; i< gridWide;i++) {
                tilesMap[j][i]= new Tile(i,j,FindTile(map[i][j]));
                tilesMap[j][i].setFolderName(folderName);
                String name = "/res/" + folderName + "/"+tilesMap[j][i].getType().getTextureName()+".png";
                imv=tilesMap[j][i].getImv();
                imv.setFitWidth(TILE_SIZE);
                imv.setFitHeight(TILE_SIZE);
                imv.setPreserveRatio(true);
                grid.getChildren().remove(tilesMap[j][i].getImv());
                grid.add(imv,j,i);

            }
        }}


    private void gridRowColum(GridPane grid) {
        int columns =gridWide;
        int rows =gridHigh;
        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(TILE_SIZE);
            grid.getColumnConstraints().add(column);
        }
        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(TILE_SIZE);
            grid.getRowConstraints().add(row);
        }}

    public int[][] getMap() {
        return map;
    }

    public Tile[][] getTilesMap() {
        return tilesMap;
    }
    public GridPane getGrid(){
        return grid;

    }
    public Tile getTile(int xPlace,int yPlace){
        if(xPlace<gridWide && yPlace<gridHigh && xPlace>-1 && yPlace>-1)
            return new Tile(xPlace,yPlace,FindTile(map[xPlace][yPlace]));
        else
            return new Tile(0,0, TileType.NULL);
    }//il permet définir les limites de la map


    public void setTile(int i, int j, TileType tileType) {
        tilesMap[i][j].setType(tileType);
        imv=new ImageView(new Image(LayoutSample.class.getResourceAsStream("/res/" + LevelTiles.getInstance().getFolderName() + "/"+tilesMap[i][j].getType().getTextureName()+".png")));
        imv.setFitWidth(TILE_SIZE);
        imv.setFitHeight(TILE_SIZE);
        imv.setPreserveRatio(true);
        grid.getChildren().remove(tilesMap[i][j].getImv());
        map[j][i] = Integer.parseInt(MapsController.getTileID(tileType)); //[j][i] au lieu de [i][j] car array et par exemple [0][4] = 5e terme de la 1iere = x vaut 4 et y vaut 0
        grid.add(imv,i,j);
    }

    public void setTileTower(int i, int j, TileType towerType) {
        this.towerType = towerType;
        this.grid.getChildren().remove(tilesMap[i][j].getImv());
        tilesMap[i][j].setType(towerType);
        imv = new ImageView(new Image(LayoutSample.class.getResourceAsStream("/res/res0/" + towerType.getTextureName() + ".png")));
        imv.setFitWidth(TILE_SIZE);
        imv.setFitHeight(TILE_SIZE);
        imv.setPreserveRatio(true);
        grid.add(imv,i,j);
    }


    public void setTileCanon(int i, int j, TowerType TowerType) {
        imv = new ImageView(new Image(LayoutSample.class.getResourceAsStream("/res/res0/" + TowerType.getGunFileName() + ".png")));
        imv.setFitWidth(TILE_SIZE);
        imv.setFitHeight(TILE_SIZE);
        imv.setPreserveRatio(true);
        imv.setRotate(180);
        grid.add(imv,i,j);
    }
}