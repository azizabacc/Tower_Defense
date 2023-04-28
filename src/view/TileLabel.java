package view;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import sample.LayoutSample;

public class TileLabel  {
    private int w,h;
    private String imageName, folderName;
    private Label l;
    private ImageView imv;

    public TileLabel(String imageName,String folderName, int w, int h){
        this.imageName=imageName;
        this.folderName= folderName;
        this.w=w;
        this.h=h;
        l=createLabel(this.imageName,this.folderName,this.w,this.h);

    }
    private Label createLabel(String imageName,String folderName, int w, int h){
        this.imv =new ImageView(new Image(LayoutSample.class.getResourceAsStream("/res/"+folderName+"/"+imageName+".png")));
        imv.setFitWidth(w);
        imv.setFitHeight(h);
        Label l =new Label();
        l.setGraphic(imv);
        return l;
    }

    public int getH() {
        return h;
    }

    public int getW() {
        return w;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getImageName() {
        return imageName;
    }
    public Label getLabel(){
        return l;
    }
    public ImageView getImageview(){
        return imv;
    }

}
