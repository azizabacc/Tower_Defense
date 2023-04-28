package model;

import controller.LevelTiles;
import controller.MapsController;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

import java.util.Random;

public class PathForEnemy {
    private int[] dir;
    private int[] newPosition;
    private int[][] map;
    private int[] StartTilePos;
    private int[] EndTilePos;

    public PathForEnemy(){
        this.dir = new int[2];
        this.newPosition = new int[2];
        this.map=Grid.getInstance().getMap();
        this.StartTilePos=whatStartAndEndPos(LevelTiles.getInstance().getFolderName())[0];
        this.EndTilePos=whatStartAndEndPos(LevelTiles.getInstance().getFolderName())[1];
}

    private int[] findNextDirection(int x, int y, int[] directions, int[][] map){ //dernière version
//        int[] dir= new int[2];

        //map[][] est une liste des Tiles ID (herbe = ID 0 etc)

        //si au bord
        if(x==0){//si le startTile est à gauche
            if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerTR))) { //si je suis sur un Corner Top Right
                dir[0]=0; //direction en x devient 0, c'est-à dire ne pas bouger en x
                dir[1]=1; //direction en y devient 1, c'est-à-dire bouger vers les y positif (Y POSITIF + VERS LE BAS)
            }else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerBR))) { //si je suis sur un Corner Bottom Right
                dir[0] = 0;
                dir[1] = -1;
            }else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadHoriz))) { //si je suis une route Horizontale
                dir[0]=1;
                dir[1]=0;
            }
        }
        else if(x==14){ //si le startTile est à droite
            if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerTL))) {
                dir[0]=0;
                dir[1]=1;
            }else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerBL))) {
                dir[0]=0;
                dir[1]=-1;
            }else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadHoriz))) {
                dir[0]=-1;
                dir[1]=0;
            }
        }
        else if(y==0){//si le startTile est en haut
            if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerBL))) {
                dir[0]=-1;
                dir[1]=0;
            }else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerBR))) {
                dir[0]=1;
                dir[1]=0;
            }else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadVerti))) {
                dir[0]=0;
                dir[1]=1;
            }
        }
        else if(y==14){ //si le startTile est en bas
            if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerTR))) {
                dir[0]=-1;
                dir[1]=0;
            }else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerTL))) {
                dir[0]=1;
                dir[1]=0;
            }else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadVerti))) {
                dir[0]=0;
                dir[1]=-1;
            }
        }

        //si pas au bord
        else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerTL))) { //si je suis sur un Corner Top Left
            if(directions[0]==0 && directions[1]==-1){//si je viens par le bas
                dir[0] = 1;//aller vers la droite
                dir[1] = 0;
            }
            else if(directions[0]==-1 && directions[1]==0){//si je viens par la droite
                dir[0] = 0;
                dir[1] = 1;//aller vers le bas
            }
        }
        else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerTR))) { //si je suis sur un Corner Top Right
            if(directions[0]==1 && directions[1]==0){
                dir[0] = 0;
                dir[1] = 1;
            }
            else if(directions[0]==0 && directions[1]==-1){
                dir[0] = -1;
                dir[1] = 0;
            }
        }
        else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerBR))) { //si je suis sur un Corner Bottom Right
            if(directions[0]==1 && directions[1]==0){
                dir[0] = 0;
                dir[1] = -1;
            }
            else if(directions[0]==0 && directions[1]==1){
                dir[0] = -1;
                dir[1] = 0;
            }
        }
        else if(map[y][x] == Integer.parseInt(MapsController.getTileID(TileType.RoadCornerBL))) { //si je suis sur un Corner Bottom Left
            if(directions[0]==-1 && directions[1]==0){
                dir[0] = 0;
                dir[1] = -1;
            }
            else if(directions[0]==0 && directions[1]==1){
                dir[0] = 1;
                dir[1] = 0;
            }
        }
        else{
            dir[0]=2;//directions[0];
            dir[1]=2;//directions[1];
        }
        return dir;
    }

    private int[] findPositionOfNextCorner(int x, int y, int[] directions, int[][] map) {
//        int[] newPosition = new int[2];

        //map +1 direction == map + *2 directions car quand il part d'un checkpoint il voit une route après lui donc condition du while non remplie
        while (map[y+directions[1]][x+directions[0]] == map[y+2*directions[1]][x+2*directions[0]] && !(x+directions[0]<0) && !(x+directions[0]>600) && !(x+directions[1]<0) && !(x+directions[0]>600)){
            x += directions[0];
            y += directions[1];
        }
        //On s'arrête à 2 pas avant donc il faut encore ajouter deux pas
        newPosition[0] = (x+2*directions[0]);
        newPosition[1] = (y+2*directions[1]);

        return newPosition;
    }

    public Path createPath() {
        int[] STartDir = {0, 0}; //Peu importe les chiffres ici car findNextDirection trouve la direction si on est au bord (x ou y <0 ou >15)

        Path path = new Path();
        MoveTo spawn = new MoveTo(StartTilePos[0]*40+20, StartTilePos[1]*40+20); //Path a besoin de positions en Pixels, et nos Tiles sont des carrés de côté 40px (milieu du tile = 20)
        path.getElements().add(spawn);


        //Vers 1er corner
        int[] nextD = findNextDirection(StartTilePos[0], StartTilePos[1], STartDir, map);
        int[] newStartTile = findPositionOfNextCorner(StartTilePos[0], StartTilePos[1], nextD, map); //Le nouveau starttile est notre 1ier corner
        LineTo line1 = new LineTo(newStartTile[0]*40+20, newStartTile[1]*40+20);
        path.getElements().add(line1); //PTET SANS LE ALL


        //Vers 2e corner
        int[] nextD1 = findNextDirection(newStartTile[0], newStartTile[1], nextD, map);
        int[] newStartTile1 = findPositionOfNextCorner(newStartTile[0], newStartTile[1], nextD1, map); //Le nouveau starttile est notre 2e corner
        LineTo line2 = new LineTo(newStartTile1[0]*40+20, newStartTile1[1]*40+20);
        path.getElements().add(line2);


        //Vers 3e corner
        int[] nextD2 = findNextDirection(newStartTile1[0], newStartTile1[1], nextD1, map);
        int[] newStartTile2 = findPositionOfNextCorner(newStartTile1[0], newStartTile1[1], nextD2, map); //Le nouveau starttile est notre 3e corner
        LineTo line3 = new LineTo(newStartTile2[0]*40+20, newStartTile2[1]*40+20);
        path.getElements().add(line3);


        //Vers 4e corner
        int[] nextD3 = findNextDirection(newStartTile2[0], newStartTile2[1], nextD2, map);
        int[] newStartTile3 = findPositionOfNextCorner(newStartTile2[0], newStartTile2[1], nextD3, map); //Le nouveau starttile est notre 4e corner
        LineTo line4 = new LineTo(newStartTile3[0]*40+20, newStartTile3[1]*40+20);
        path.getElements().add(line4);


        //Vers la sortie
        LineTo endLine = new LineTo(EndTilePos[0]*40+20, EndTilePos[1]*40+20);
        path.getElements().add(endLine);


        return path;
    }

    public boolean isNotBorder(int coordinates){
        boolean isNotBorder = true;
        if (coordinates<0 || coordinates>15 ){
            isNotBorder = false;
        }
        return isNotBorder;
    }

    public int[][] whatStartAndEndPos(String MapInt){
        int[][] thePositions = new int[2][2];
        int[] StartTilePos = {0, 0};
        int[] EndTilePos = {0,0};

        if(LevelTiles.getInstance().getFolderName()=="src/MAPS/MAP1"){
            StartTilePos[0] = 0;
            StartTilePos[1]= 11;
            EndTilePos[0] = 14;
            EndTilePos[1]= 3;
        }else if(LevelTiles.getInstance().getFolderName()=="src/MAPS/MAP2"){
            StartTilePos[0] = 0;
            StartTilePos[1]= 2;
            EndTilePos[0] = 14;
            EndTilePos[1]= 7;
        }else if(LevelTiles.getInstance().getFolderName()=="src/MAPS/MAP3"){
            StartTilePos[0] = 7;
            StartTilePos[1]= 0;
            EndTilePos[0] = 7;
            EndTilePos[1]= 14;
        }else if(LevelTiles.getInstance().getFolderName()=="src/MAPS/MAP4"){
            StartTilePos[0] = 0;
            StartTilePos[1]= 2;
            EndTilePos[0] = 3;
            EndTilePos[1]= 14;
        }else if(LevelTiles.getInstance().getFolderName()=="src/MAPS/MAP5"){
            StartTilePos[0] = 0;
            StartTilePos[1]= 11;
            EndTilePos[0] = 14;
            EndTilePos[1]= 2;
        }
        thePositions[0] = StartTilePos;
        thePositions[1] = EndTilePos;

        return thePositions;
    }

    public Path createPathAleatoire() {
        Path[] paths = new Path[3];
        Path path = createPath1();
        paths[0]=path;
        Path path1 = createPath2();
        paths[1]=path1;
        Path path2 = createPath3();
        paths[2]=path2;

        Random random = new Random();
        return paths[random.nextInt(3)];
    }

    public Path createPath1() {
        Path path = new Path();
        MoveTo spawn = new MoveTo(6.0, 100.0);
        LineTo line1 = new LineTo(220.0, 100.0);
        LineTo line2 = new LineTo(220.0, 500.0);
        LineTo line3 = new LineTo(265.0, 500.0);
        LineTo line4 = new LineTo(420.0, 500.0);
        LineTo line5 = new LineTo(420.0, 300.0);
        LineTo line6 = new LineTo(580.0, 300.0);
        path.getElements().addAll(spawn, line1, line2, line3, line4, line5,
                line6);
        return path;
    }

    public Path createPath2() {
        Path path1 = new Path();
        MoveTo spawn1 = new MoveTo(6.0, 500.0);
        LineTo line10 = new LineTo(580.0, 500.0);
        path1.getElements().addAll(spawn1, line10);
        return path1;
    }

    public Path createPath3(){
        Path path2 =new Path();
        MoveTo spawn2 = new MoveTo(6.0, 100.0);
        LineTo line20 = new LineTo(580.0, 100.0);
        path2.getElements().addAll(spawn2, line20);
        return path2;
    }

}
