package model;


public class EnemyGalactic extends Enemy implements MoreLife,Runnable{
    private double health;
    private volatile boolean done;

    public EnemyGalactic(EnemyType type) {
        super(type);
        this.health=getType().getHealth();
    }
    @Override
    public void addLife() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.currentThread().sleep(5000);
                setHealth(-0.0001);
            } catch (InterruptedException e){
            }
        }
    }
}
