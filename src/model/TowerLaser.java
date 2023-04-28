package model;

public class TowerLaser extends Tower implements SelectiveTarget{
    public TowerLaser(TowerType type, Tile startTile){
        super(type,startTile);
        this.setLevel2cost(70);
        this.setLevel3cost(100);
    }



    @Override
    public void damage(Enemy target) {
        Thread thread =new Thread(this);
        this.target=target;
        thread.start();

    }

    @Override
    public void levelup() {
        System.out.println("TLASER My current level is " + super.getLevel());
        if (super.getLevel() == 1){
            setDamage(0.3f);
            super.setLevel(2);
            //Player.modifyCoins(- getLevel2cost());
            System.out.println("levellaser " + getLevel());

        }else if (super.getLevel() == 2){
            super.maxDistanceEnemyTower = 3;
            super.setLevel(3);
            //Player.modifyCoins(- getLevel3cost());
            System.out.println("levellaser " + getLevel());
        }else{
            System.out.println("I'm at max level");
        }
    }


    @Override
    public void run() {
        while (true){
            target.damage(this.getDamage());
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
        if(target.getType()==EnemyType.Enemy3){
            System.out.println("Ne peut pas l'attaquer");
        }else{
            damage(target);
        }

    }
}