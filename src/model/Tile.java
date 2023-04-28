package model;


import controller.LevelTiles;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.LayoutSample;
import view.Level1;

import java.net.URL;


public class Tile  {
    private int x,y;
    private Image image;
    private TileType type;
    private ImageView imv;
    private  String folderName;



    public Tile(int x, int y ,TileType type){
        this.x=x;
        this.y=y;
        this.type=type;
        this.folderName= LevelTiles.getInstance().getFolderName();
        URL imageURL = getClass().getResource("/res/" + folderName + "/"+type.textureName+".png");
        this.image=  new Image(imageURL.toExternalForm());
        this.imv = new ImageView(
                new Image(LayoutSample.class.getResourceAsStream("/res/" + folderName + "/"+type.textureName+".png")));

    }



    public void setType(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return this.type;
    }

    public Image getImage() {
        return this.image;
    }
    public ImageView getImv() {
        return this.imv;
    }

    public int getX() { return x; }
    public int getY(){
        return y;
    }


    public void setFolderName(String folderName){
        this.folderName = folderName;
    }


}