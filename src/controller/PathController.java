package controller;
import javafx.animation.Transition;
import model.*;
import view.GameOver;
import view.Level1;
public class PathController {
    private Transition transition;
    private Enemy en1;
    public PathController(Transition transition, Enemy en1){
        this.transition=transition;
        this.en1=en1;
    }
    public void endofMaze(){
        transition.setOnFinished(event -> {
            en1.getImv().setX(en1.getImv().getX() + en1.getImv().getTranslateX());
            en1.getImv().setY(en1.getImv().getY() + en1.getImv().getTranslateY());
            en1.getImv().setTranslateX(0);
            en1.getImv().setTranslateY(0);
            if (Player.getInstance().getScore()-1>0){
                if(en1.getImv().isVisible()){
                    Player.getInstance().modifyLives(-1);
                    en1.getImv().setVisible(false);
                }
            }

            else{
                LevelTiles.getInstance().setFolderName("res0");
                GameOver gameOver =new GameOver();
                try {
                    gameOver.start(GameOver.GameOver);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Level1.Stage.close();
            }

        });
    }

}
