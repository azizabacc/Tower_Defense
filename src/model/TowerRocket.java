package model;


public class TowerRocket extends Tower implements LimitedMunition{
    int[] shoots={0,0,0,0,0,0,0,0};
    private int munitions;
    private int wavenumber;

    public TowerRocket(TowerType type, Tile startTile) {
        super(type, startTile);
        this.setLevel2cost(15);
        this.setLevel3cost(20);
        this.munitions=50;
        this.wavenumber=0;
    }


    @Override
    public void damage(Enemy target) {
        Thread thread =new Thread(this);
        this.target=target;
        this.wavenumber=Player.getInstance().getNumberOfWaveInfo();
        thread.start();

    }

    @Override
    public void levelup() {
        System.out.println("TROCKET My current level is " + super.getLevel());
        if (super.getLevel() == 1) {

            ///vérifier les trucs à évoluer

            this.setLevel(2);
            this.munitions=80;
           // Player.modifyCoins(-getCost());
            System.out.println("levelrocket " + this.getLevel());
        }else if (super.getLevel() == 2){
            super.maxDistanceEnemyTower = 3;
            this.setLevel(3);
            //Player.modifyCoins(- getCost());
            System.out.println("levelrocket " + this.getLevel());
        }else{
            System.out.println("I'm at max level");
        }
    }

    @Override
    public int getMunitions() {
        return munitions;
    }

    @Override
    public int getShoots(int wavenumber) {
        return shoots[wavenumber];
    }


    @Override
    public void run() {
        while (true){
            target.damage(this.getDamage());
            shoots[wavenumber]++;
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

}
