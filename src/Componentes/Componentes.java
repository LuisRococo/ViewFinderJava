
package Componentes;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class Componentes {
    
    private ViewFinder viewF;
    private MyImageClass imageClass;
    public Componentes() {}
    
    public boolean cargarImagen (File fl){
         try {
            BufferedImage originalImage=ImageIO.read(fl);
            imageClass =new MyImageClass(originalImage);
            return true;
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, "ERROR: IMAGE CAN NOT BE LOADED");
             return false;
        }
    }
    
    public void changePositionImage (int movX,int movY){
        if (imageClass!=null){
            imageClass.changePosition(movX, movY);
        }
    }
    
    public void changeSize (boolean incrementar){
        if (imageClass!=null){
            imageClass.changeSize(incrementar);
        }
    }
    
    public void goodQualityImage (){
        if (imageClass!=null){
            imageClass.changeSizeGoodQuality();
        }
    }
    
    public void cargarViewFinder (ViewFinder viewF){
        this.viewF=viewF;
    }
    
    public ViewFinder getViewFinder (){
        return this.viewF;
    }
    public MyImageClass getMyImageClass (){
        return this.imageClass;
    }
    
}
