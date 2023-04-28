package controller;

import model.Player;

public class LevelTiles {
    private static LevelTiles Tiles=null ;
    private String folderName;
    private String mapName;


    private LevelTiles(){
        this.folderName="res1";
        this.mapName="src/MAPS/MAP1";
        //this.mapName[1]="src/MAPS/MAP2";

    }

    public static LevelTiles getInstance() {
        if (Tiles==null){
            Tiles = new LevelTiles();
        }
        return Tiles;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setMapName(String newMapName){
        this.mapName="src/MAPS/"+newMapName;
    }
    public String getMapName(){
        return this.mapName;
    }
}