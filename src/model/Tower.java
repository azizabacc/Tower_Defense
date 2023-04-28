package model;


public abstract class Tower implements Runnable{
    private float x,y,damage;
    float maxDistanceEnemyTower;
    private int cost;
    private int level;
    private int level2cost, level3cost;
    Enemy target;
    private TowerType type;


    public Tower(TowerType type, Tile startTile){
        this.type=type;
        this.maxDistanceEnemyTower=type.maxDistanceEnemyTower;
        this.cost= type.cost;
        this.damage=type.damage;
        this.x=startTile.getX();
        this.y=startTile.getY();
        this.level=1;
    }

    public abstract void damage(Enemy target);


    public void setDamage(float damage){
        this.damage=damage;
    }
    public float getDamage(){
        return damage;
    }
    public abstract void levelup();

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }


    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getCost(){
        if(level == 2)
            return level2cost;
        if(level == 3)
            return level3cost;
        return cost;
    }

    public void setLevel2cost(int level2cost){
        this.level2cost =level2cost;
    }

    public void setLevel3cost(int level3cost){
        this.level3cost =level3cost;
    }

    public void setLevel(int level){
        this.level=level;
    }
    public int getLevel(){
        return level;
    }

    public TowerType getType(){
        return type;
    }
}
