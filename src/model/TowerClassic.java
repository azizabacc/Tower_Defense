package model;

public class TowerClassic extends Tower  implements SelectiveTarget{

    public TowerClassic(TowerType type, Tile startTile) {
        super(type, startTile);
        this.setLevel2cost(10);
        this.setLevel3cost(15);
    }



    @Override
    public void damage(Enemy target) {
        Thread thread =new Thread(this);
        super.target=target;
        thread.start();
    }

    @Override
    public void levelup() {
        System.out.println("TCLASSIC My current level is " + super.getLevel());
        if (super.getLevel() == 1) {

            ///changer un truc au niveau 2
            setDamage(0.2f);
            super.setLevel(2);
            //Player.modifyCoins(-getLevel2cost());
            System.out.println("levelclassic " + getLevel());
        } else if (super.getLevel() == 2) {
            super.maxDistanceEnemyTower = 3;
            super.setLevel(3);
            //data.Player.modifyCoins(- getLevel3cost());
            System.out.println("levelclassic " + getLevel());
        } else {
            System.out.println("I'm at max level");
        }
    }


    @Override
    public void run() {
        while (true){
        target.damage(this.getDamage());
        //super.checkalive(target);
        //System.out.println("l'ennemi a été attaqué   "+target.getHealth());
        target.setAttacked(true);
        try{
            Thread.sleep(5000);
            //System.out.println("l'ennemi a été attaqué   "+target.getHealth());
            target.setAttacked(false);
        }catch (Exception e){

        }
        }
    }
    @Override
    public void veriftype(Enemy target) {
        if(target.getType()==EnemyType.EnemyAlien){
            System.out.println("Ne peut pas l'attaquer");
        }else{
            damage(target);
        }
    }

}