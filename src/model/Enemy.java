package model;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;
import java.util.Random;

public abstract class Enemy {
    private ImageView imv ;
    private boolean alive;
    private EnemyType type;
    private double speed;
    private double health;
    private boolean attacked;
    private boolean gainCoins;

    public Enemy(EnemyType type){
        this.type=type;
        this.imv = type.getImv();
        this.speed=type.getSpeed();
        this.health=type.getHealth();
        this.alive=true;
        this.attacked=false;
        this.gainCoins=false;
    }

    public void setAttacked(boolean status){
        this.attacked=status;
    }

    public ImageView getImv(){
        return imv;
    }

    public boolean isGainCoins() {
        return gainCoins;
    }

    public void setGainCoins(boolean gainCoins) {
        this.gainCoins = gainCoins;
    }

    public PathTransition move(){
        imv.setVisible(true);
        PathTransition t = new PathTransition(Duration.millis(speed*20000), createPathAleatoire(),imv);
        t.setInterpolator(Interpolator.LINEAR);
        return t;
    }

    public void setHealth(double amount){
        this.health-=amount;
    }

    public void damage(float damage){
        if(attacked) {
            setHealth(damage);
        }
            if (health <= 0) {
                this.alive = false;
                imv.setVisible(false);
            }

    }

    public double getHealth(){
        return health;
    }
    public boolean getalive(){
        return alive;
    }

    public void setSpeed(double speed){
        this.speed=speed;
    }

    private Path createPathAleatoire() {
        Path[] paths = new Path[2];
        System.out.println("il capte vite fait" + Player.getInstance().getGameLevel());

        if (Player.getInstance().getGameLevel() == 1) {
            Path path = createPath1();
            paths[0] = path;
            Path path1 = createPath2();
            paths[1] = path1;

        }else if (Player.getInstance().getGameLevel()==2){
            Path path = createPath3();
            paths[0] = path;
            Path path1 = createPath4();
            paths[1] = path1;
        }
        Random random = new Random();
        return paths[random.nextInt(2)];

}

    private Path createPath1() {
        Path path = new Path();
        MoveTo spawn = new MoveTo(170.0, 35.0);
        LineTo line1 = new LineTo(500.0, 35.0);
        LineTo line2 = new LineTo(500.0, 80.0);
        LineTo line3 = new LineTo(540.0, 80.0);
        LineTo line4 = new LineTo(540.0, 155.0);
        LineTo line5 = new LineTo(660.0, 155.0);
        LineTo line6 = new LineTo(660.0, 280.0);
        LineTo line7 = new LineTo(700.0, 280.0);
        LineTo line8 = new LineTo(700.0, 600.0);
        path.getElements().addAll(spawn, line1, line2, line3, line4, line5, line6, line7, line8);
        return path;
    }

    private Path createPath2() {
        Path path1 = new Path();
        MoveTo spawn1 = new MoveTo(170.0, 155.0);
        LineTo line1 = new LineTo(220.0, 155.0);
        LineTo line2 = new LineTo(220.0, 115.0);
        LineTo line3 = new LineTo(300.0, 115.0);
        LineTo line4 = new LineTo(300.0, 160.0);
        LineTo line5 = new LineTo(420.0, 160.0);
        LineTo line6 = new LineTo(420.0, 280.0);
        LineTo line7 = new LineTo(300.0, 280.0);
        LineTo line8 = new LineTo(300.0, 400.0);
        LineTo line9 = new LineTo(620.0, 400.0);
        LineTo line10 = new LineTo(620.0, 560.0);
        LineTo line11 = new LineTo(420.0, 560.0);
        LineTo line12 = new LineTo(420.0, 520.0);
        LineTo line13 = new LineTo(170.0, 520.0);

        path1.getElements().addAll(spawn1, line1, line2, line3, line4, line5, line6, line7, line8, line9, line10, line11, line12, line13);
        return path1;
    }
    private Path createPath3(){
        Path path1 = new Path();
        MoveTo spawn1 = new MoveTo(170.0, 115.0);
        LineTo line1 = new LineTo(500.0, 115.0);
        LineTo line2 = new LineTo(500.0, 400.0);
        LineTo line3 = new LineTo(580.0, 400.0);
        LineTo line4 = new LineTo(580.0, 320.0);
        LineTo line5 = new LineTo(660.0, 320.0);
        LineTo line6 = new LineTo(660.0, 80.0);
        LineTo line7 = new LineTo(760.0, 80.0);

        path1.getElements().addAll(spawn1, line1, line2, line3, line4, line5, line6, line7);
        return path1;
    }
    private Path createPath4(){
        Path path1 = new Path();
        MoveTo spawn1 = new MoveTo(170.0, 235.0);
        LineTo line1 = new LineTo(380.0, 235.0);
        LineTo line2 = new LineTo(380.0, 440.0);
        LineTo line3 = new LineTo(225.0, 440.0);
        LineTo line4 = new LineTo(225.0, 560.0);
        LineTo line5 = new LineTo(580.0, 560.0);
        LineTo line6 = new LineTo(580.0, 480.0);
        LineTo line7 = new LineTo(700.0, 480.0);
        LineTo line8 = new LineTo(700.0, 600.0);
        path1.getElements().addAll(spawn1, line1, line2, line3, line4, line5, line6, line7, line8);
        return path1;
    }

    public EnemyType getType(){
        return type;
    }

    public void changeHealth(double Health){
        this.health = Health;
    }
}