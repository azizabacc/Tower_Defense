package model;
import controller.KillController;
import controller.LevelTiles;
import controller.PathController;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import view.Level1;
import view.Winner;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class WaveManager{
    private Wave2 wave;
    private AtomicInteger waveNumber;
    private int numberOfWaves;
    private ParallelTransition parallelTransition;
    private KillController killController;

    public WaveManager(int numberOfWaves){
        this.numberOfWaves=numberOfWaves;
        Player.getInstance().getEnemies();
        Player.getInstance().setEnemies(EnemylistGerator());
        this.wave = new Wave2(Player.getInstance().getEnemies());
        this.parallelTransition = wave.firstWave();
        this.waveNumber= new AtomicInteger(1);
        Player.getInstance().setWaveNumberInfo(this.waveNumber.intValue());
        this.killController=new KillController();
    }
    private ArrayList<Enemy> EnemylistGerator(){
        ArrayList<Enemy>enemies=new ArrayList<Enemy>();
        Enemy en1 = new EnemyAlien(EnemyType.EnemyAlien);
        Enemy en2 = new EnemyUFO(EnemyType.EnemyUFO);
        Enemy en3 = new EnemyGalactic(EnemyType.Enemy3);
        enemies.add(en1);
        enemies.add(en2);
        enemies.add(en3);
        return enemies;
    }
    public void update(){
        parallelTransition.play();
        parallelTransition.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(Player.getInstance().getGameLevel()<=2) {
                    System.out.println("dans wavemannager update   "+Player.getInstance().getGameLevel());
                    if (waveNumber.intValue() < numberOfWaves) {
                        Player.getInstance().getEnemies().clear();
                        Player.getInstance().setEnemies(EnemylistGerator());
                        for (int i = 0; i < Player.getInstance().getEnemies().size(); i++) {
                            Player.getInstance().getEnemies().get(i).setSpeed(Player.getInstance().getEnemies().get(i).getType().getSpeed()/2/Player.getInstance().getGameLevel());
                        }
                        killController.checkPosition();
                        parallelTransition.getChildren().clear();
                        PathTransition[] pathTransitions = new PathTransition[Player.getInstance().getEnemies().size()];
                        PathController[] pathControllers = new PathController[Player.getInstance().getEnemies().size()];
                        for (int i = 0; i < Player.getInstance().getEnemies().size(); i++) {
                            pathTransitions[i] = Player.getInstance().getEnemies().get(i).move();
                            pathControllers[i] = new PathController(pathTransitions[i], Player.getInstance().getEnemies().get(i));
                            pathControllers[i].endofMaze();
                        }
                        waveNumber.getAndIncrement();
                        Player.getInstance().setWaveNumberInfo(waveNumber.intValue());
                        for (int j = 0; j < Player.getInstance().getEnemies().size(); j++) {
                            parallelTransition.getChildren().add(pathTransitions[j]);
                        }
                        parallelTransition.play();
                    } else {
                        parallelTransition.getChildren().clear();
                        System.out.println("last wave");
                        //nextlevel();
                        Player.getInstance().setGameLevel(Player.getInstance().getGameLevel()+1);
                    }
                    if(Player.getInstance().getGameLevel()==2){
                        //LevelTiles.getInstance().setFolderName("res2");
                        nextlevel();
                    }else if(Player.getInstance().getGameLevel()==3){
                        System.out.println("win");
                        winner();}
                }
            }
        });}

    public ParallelTransition getParallelTransition(){
        return parallelTransition;
    }
    private void nextlevel(){
        LevelTiles.getInstance().setFolderName("res2");
        LevelTiles.getInstance().setMapName("MAP2");
        //Player.getInstance().setGameLevel(2);
        Player.getInstance().setup(Player.getInstance().getGameLevel());
        Level1 level1 =new Level1();
        Player.getInstance().getTowers().clear();
        Player.getInstance().getEnemies().clear();
        Player.getInstance().setEnemies(EnemylistGerator());
        System.out.println("dans next level   "+Player.getInstance().getGameLevel());
        try {
            level1.start(Level1.Stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void winner(){
        Winner winner = new Winner();
        try {
            winner.start(Winner.winner);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Level1.Stage.close();
    }
}