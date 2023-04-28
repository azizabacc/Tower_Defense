package view;

import model.TileType;

public interface UI {
    TileLabel[] generateLabelList();
    String getMapName();
    public TileType[] getTypes();
}
