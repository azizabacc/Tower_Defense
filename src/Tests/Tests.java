package Tests;

import static controller.MapsController.FindTile;
import static model.TowerType.CannonClassic;
import static model.TowerType.CannonRocket;

import controller.KillController;
import javafx.embed.swing.JFXPanel;
import model.*;
import org.junit.Assert;
import org.junit.Test;


public class Tests {
    JFXPanel jfxPanel = new JFXPanel();  //pour permettre à javafx de s'initialiser

    @Test
    public void testsettile() {
        /* Vérifie que la bonne tile a bien été modifée par la bonne texture ( exemple : Type = Grass)
        */
        int[][] mapps = new int[15][15];
        Tile[][] tilesMap = new Tile[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                tilesMap[i][j] = new Tile(i, j, FindTile(mapps[i][j]));
            }
        }
        tilesMap[2][3].setType(TileType.Grass);
        Assert.assertEquals(TileType.Grass, tilesMap[2][3].getType());

    }
    @Test
    public void testenemeny(){
        // Vérifie que l'ennemi est bien mort lorsqu'il reçoit des dégâts plus élevés que sa vie
        Enemy enemy = new EnemyGalactic(EnemyType.Enemy3);
        enemy.setAttacked(true);
        enemy.damage((float)enemy.getType().getHealth()+10);
        Assert.assertFalse(enemy.getalive());
    }
    @Test
    public void testlevelup(){
        // Vérfie que les attributs des tourelles sont bien modifiés après le levelup
        TowerRocket towerRocket=new TowerRocket(CannonRocket,new Tile(0,0,TileType.Grass));
        towerRocket.levelup();
        Assert.assertEquals(80,towerRocket.getMunitions());
        TowerClassic towerClassic =new TowerClassic(CannonClassic,new Tile(5,9,TileType.Grass));
        towerClassic.levelup();
        Assert.assertEquals(0.2f,towerClassic.getDamage(),0);
    }
}