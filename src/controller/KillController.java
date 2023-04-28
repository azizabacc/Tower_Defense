package controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import model.*;
public class KillController {

    public KillController(){
    }
    public void checkPosition() {
        for (int i = 0; i < Player.getInstance().getEnemies().size(); i++) {

                double[] position = new double[2];
                int finalI = i;
                ChangeListener<Number> listener = new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
                        Bounds boundsInScene = Player.getInstance().getEnemies().get(finalI).getImv().localToScene(Player.getInstance().getEnemies().get(finalI).getImv().getBoundsInLocal());
                        double xInScene = boundsInScene.getMinX();
                        double yInScene = boundsInScene.getMinY();
                        position[0] = xInScene * 15 / 600;
                        position[1] = yInScene * 15 / 600;
                        if (!Player.getInstance().getpause()  && Player.getInstance().getEnemies().get(finalI).getImv().isVisible()) {
                            for (Tower t : Player.getInstance().getTowers()) {
                                double xdistance = Math.round(Math.abs(position[0] - t.getX()));
                                double ydistance = Math.round(Math.abs(position[1] - t.getY()));
                                if (xdistance < t.getType().getMaxDistanceEnemyTower() && ydistance < t.getType().getMaxDistanceEnemyTower()) {
                                    if (t instanceof LimitedMunition) {
                                        if (((LimitedMunition) t).getMunitions() > ((LimitedMunition) t).getShoots(Player.getInstance().getNumberOfWaveInfo())) {
                                            t.damage(Player.getInstance().getEnemies().get(finalI));
                                        }
                                    }
                                    if (t instanceof SelectiveTarget) {
                                        ((SelectiveTarget) t).veriftype(Player.getInstance().getEnemies().get(finalI));
                                    } else {
                                        t.damage(Player.getInstance().getEnemies().get(finalI));
                                    }
                                }
                            }
                            if (Player.getInstance().getEnemies().get(finalI) instanceof MoreLife) {
                                ((MoreLife) Player.getInstance().getEnemies().get(finalI)).addLife();System.out.println(Player.getInstance().getEnemies().get(finalI).getHealth());
                            }
                        }else if(!Player.getInstance().getEnemies().get(finalI).getalive() && !Player.getInstance().getEnemies().get(finalI).isGainCoins()){
                            Player.getInstance().setCoins(10);
                            Player.getInstance().getEnemies().get(finalI).setGainCoins(true);
                        }
                    }
                };
                Player.getInstance().getEnemies().get(finalI).getImv().translateXProperty().addListener(listener);
                Player.getInstance().getEnemies().get(finalI).getImv().translateYProperty().addListener(listener);
            }

    }
}