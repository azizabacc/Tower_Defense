package model;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.LayoutSample;

public enum EnemyType {
    EnemyUFO("EnemyLvl1","res0",0.7,500),
    EnemyAlien("EnemyLvl2","res0",0.9,300),
    Enemy3("EnemyLvl3","res0",1.5,400),
    Enemy4("EnemyLvl4","res0",2,300);
    private String textureName,folderName;
    private ImageView imv;
    private double speed,health;

    EnemyType(String textureName, String folderName,double speed,double health){
        this.textureName=textureName;
        this.folderName="res0";
        this.speed=speed;
        this.health=health;
        this.imv=new ImageView(new Image(LayoutSample.class.getResourceAsStream("/res/"+this.folderName+"/"+textureName+".png")));
        imv.setFitWidth(40);
        imv.setFitHeight(40);
        imv.setPreserveRatio(true);
    }
    public String getTextureName(){
        return textureName;
    }
    public String getFolderName(){
        return folderName;
    }

    public ImageView getImv(){
        return imv;
    }
    public double getSpeed(){
        return speed;
    }
    public double getHealth(){
        return health;
    }

}

