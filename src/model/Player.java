package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import java.util.ArrayList;

public class Player{
    private static Player player=null ;
    private SimpleIntegerProperty Coins = new SimpleIntegerProperty(this,"Coins");
    private SimpleIntegerProperty Score = new SimpleIntegerProperty(this,"Score");
    private SimpleIntegerProperty GameLevel = new SimpleIntegerProperty(this,"GameLevel");
    private SimpleIntegerProperty waveNumberInfo = new SimpleIntegerProperty(this,"Wave Number");
    private ArrayList<Tower> towers;
    private ArrayList<Enemy> enemies;
    private boolean pause;

    private Player() {
        Coins.set(0);
        Score.set(0);
        GameLevel.set(1);
        this.towers=new ArrayList<Tower>();
        this.waveNumberInfo.set(0);
        this.pause=false;
        this.enemies=new ArrayList<Enemy>();
    }

    public static Player getInstance(){
        if (player==null){
            player = new Player();
        }
        return player;
    }

    public void setup(int gamelevel){//initialiation
        Coins.set(200); //*gamelevel;
        Score.set(10);
        this.GameLevel.set(gamelevel);
    }

    public boolean getpause(){
        return pause;
    }

    public void setPause(boolean status){
        this.pause=pause;
    }

    public void modifyLives(int amount){
        this.Score.set(getScore()+amount);
    }

    public int getScore(){
        return Score.get();
    }
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
    public void setEnemies(ArrayList<Enemy> enemies1){
        this.enemies=enemies1;
    }

    public IntegerProperty ScoreProperty(){
        return Score;
    }

    public void setCoins(int amount){
        if (Coins.get() + amount>=0){
        this.Coins.set(getCoins()+amount);}
        else{ System.out.println("Pas assez d'argent");}
    }

    public int getCoins() {
        return Coins.get();
    }

    public IntegerProperty CoinsProperty(){
        return Coins;
    }

    public void setGameLevel(int gamelevel){
        this.GameLevel.set(gamelevel);
    }

    public int getGameLevel(){
        return GameLevel.get();
    }

    public IntegerProperty LevelProperty(){
        return GameLevel;
    }

    public void setWaveNumberInfo(int waveNumber){
        this.waveNumberInfo.set(waveNumber);
    }

    public int getNumberOfWaveInfo(){
        return waveNumberInfo.get();
    }

    public  IntegerProperty WaveNumberProperty(){
        return waveNumberInfo;
    }

    public ArrayList<Tower> getTowers(){
        return towers;
    }


}