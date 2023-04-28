package model;

import controller.KillController;
import controller.PathController;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import java.util.ArrayList;

public class Wave2 {
    private ArrayList<Enemy> enemylist;
    private PathController[] pathControllers;
    private KillController killController;

    public Wave2(ArrayList<Enemy> enemylist){
        this.enemylist=enemylist;
        this.pathControllers = new PathController[3];
        this.killController=new KillController();
        killController.checkPosition();
    }
    public ParallelTransition firstWave(){
        PathTransition[] pathTransitions = new PathTransition[3];
        ParallelTransition parallelTransition = new ParallelTransition();
        for (int i=0; i<pathTransitions.length;i++){
            pathTransitions[i]= enemylist.get(i).move();
            pathControllers[i]= new PathController(pathTransitions[i],enemylist.get(i));
            pathControllers[i].endofMaze();
            parallelTransition.getChildren().add(pathTransitions[i]);
        }
        return parallelTransition;
    }
}
