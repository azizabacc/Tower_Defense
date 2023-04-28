package controller;

import model.TileType;
import java.io.*;

public class MapsController {
    public static int[][] loadMyMap(String dataMapName){
        int[][] newMap = new int[15][15];
        try{
            BufferedReader br = new BufferedReader(new FileReader(dataMapName));
            for (int i = 0; i <= 14 ; i++ ){ //-1 car le dernier enemi ajouté à lui même encore ajouté un \n
                String data = br.readLine();
                String[] subDatas = data.split(",");
                for (int j = 0; j <= subDatas.length -1; j++){
                    newMap[i][j] = Integer.parseInt(subDatas[j]);
                }
            }
            br.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return newMap;
    }


    public static void SaveMaps(String dataMapName, int[][] map){

        String dataMapsFolder = dataMapName;
        String mapData ="";
        for (int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                mapData += Integer.toString(map[i][j]);
                mapData += ",";
            }
            mapData += "\n";
        }

        try {
            File file = new File(dataMapsFolder);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(mapData);
            bw.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static TileType FindTile(int id) {
        TileType t = TileType.NULL;
        switch (id){
            case 0:
                t =TileType.Grass;
                break;
            case 1:
                t =TileType.Water;
                break;
            case 2:
                t =TileType.RoadVerti;
                break;
            case 3:
                t =TileType.RoadHoriz;
                break;
            case 4:
                t =TileType.RoadCornerBL;
                break;
            case 5:
                t =TileType.RoadCornerBR;
                break;
            case 6:
                t =TileType.RoadCornerTL;
                break;
            case 7:
                t =TileType.RoadCornerTR;
                break;
            case 8:
                t =TileType.GrassDeco1;
                break;
            case 9:
                t =TileType.GrassDeco2;
                break;
            case 10:
                t =TileType.GrassDeco3;
                break;
            case 11:
                t =TileType.TowerLaser;
                break;
            case 13:
                t =TileType.TowerClassic;
                break;
            case 14:
                t =TileType.TowerRocket;
                break;
            case 15:
                t =TileType.NULL;
                break;
        }
        return t;
    }
    public static String getTileID(TileType t){
        String ID = "E";
        switch (t){
            case Grass:
                ID = "0";
                break;
            case Water:
                ID = "1";
                break;
            case RoadVerti:
                ID = "2";
                break;
            case RoadHoriz:
                ID = "3";
                break;
            case RoadCornerBL:
                ID = "4";
                break;
            case RoadCornerBR:
                ID = "5";
                break;
            case RoadCornerTL:
                ID = "6";
                break;
            case RoadCornerTR:
                ID = "7";
                break;
            case GrassDeco1:
                ID = "8";
                break;
            case GrassDeco2:
                ID = "9";
                break;
            case GrassDeco3:
                ID = "10";
                break;
            case NULL:
                ID = "11";
                break;
        }
        return ID;
    }
}