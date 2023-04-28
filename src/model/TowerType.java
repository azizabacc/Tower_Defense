package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.LayoutSample;


public enum TowerType  {

    CannonLaser("paveTowerSlowLvl3","TowerSlowLvl2Gun",2,0.2f,50,TileType.TowerLaser),
    CannonClassic("paveTowerNormLvl1","TowerNormLvl2Gun",2,1.5f,10,TileType.TowerClassic),
    CannonRocket("paveTowerNormLvl2","TowerNormLvl4Gun",2,0.2f,15, TileType.TowerRocket);


    ImageView[] textures;
    private String textureName,gunFileName;
    float maxDistanceEnemyTower;
    int cost;
    float damage;
    private TileType TileTypeTower;


    TowerType (String textureName,String gunFileName,float maxDistanceEnemyTower,float damage,int cost,TileType TileTypeTower){
        this.textureName=textureName;
        this.gunFileName=gunFileName;
        this.textures=new ImageView[]{new ImageView(new Image(LayoutSample.class.getResourceAsStream("/res/res0/"+textureName+".png"))),new ImageView(new Image(LayoutSample.class.getResourceAsStream("/res/res0/"+gunFileName+".png")))};
        textures[0].setFitWidth(40);
        textures[0].setFitHeight(40);
        textures[0].setPreserveRatio(true);
        textures[1].setFitWidth(40);
        textures[1].setFitHeight(40);
        textures[1].setPreserveRatio(true);
        this.damage=damage;
        this.maxDistanceEnemyTower=maxDistanceEnemyTower;
        this.cost=cost;
        this.TileTypeTower = TileTypeTower;

    }
    public String getTextureName(){
        return textureName;
    }
    public ImageView[] getImv(){
        return textures;
    }
    public String getFolderName(){return "res0";}
    public float getMaxDistanceEnemyTower(){
        return maxDistanceEnemyTower;
    }
    public String getTowerProperName(Tower Tower) {
        String ProperName = "";
        if (Tower.getType().getTextureName() == "paveTowerSlowLvl3"){
            ProperName = "Canon Laser";
        }else if (Tower.getType().getTextureName() == "paveTowerSlowLvl4"){
            ProperName = "Canon Freeze";
        }else if (Tower.getType().getTextureName() == "paveTowerNormLvl1"){
            ProperName = "Canon Classic";
        }else if (Tower.getType().getTextureName() == "paveTowerNormLvl2"){
            ProperName = "Canon Rocket";
        }
        return ProperName;
    }

    public int[] getInfosOfTower(){
        int Infos[] = {(int) maxDistanceEnemyTower,cost};
        return Infos;
    }

    public TileType getTileType(){
        return TileTypeTower;
    }

    public String getGunFileName(){
        return gunFileName;
    }


}
