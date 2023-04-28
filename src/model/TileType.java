package model;

public enum TileType {
    Grass("green", true),
    GrassDeco1("greenDeco1", false),
    GrassDeco2("greenDeco2", false),
    GrassDeco3("greenDeco3", false),
    Water("blue", false),
    NULL("black", false),
    RoadHoriz("brownHoriz", false),
    RoadVerti("brownVerti", false),
    RoadCornerTL("brownCornerTL", false),
    RoadCornerBL("brownCornerBL", false),
    RoadCornerBR("brownCornerBR", false),
    RoadCornerTR("brownCornerTR", false),
    TowerLaser("paveTowerSlowLvl3",false),
    TowerClassic("paveTowerNormLvl1",false),
    TowerRocket("paveTowerNormLvl2",false);
    String textureName;
    //ImageView imv;
    boolean buildable;

    TileType(String textureName, boolean buildable){
        this.buildable=buildable;
        this.textureName = textureName;

    }
    public String getTextureName(){
        return textureName;
    }
    public boolean isBuildable(){
        return buildable;
    }
}


