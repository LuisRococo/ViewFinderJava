
package Componentes;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class MyImageClass {
    
    private final BufferedImage originalImage;
    private BufferedImage modifieImage;
    private int posX;
    private int posY;
    
    private int zoom;
    private final int jumpsZoom=10;
    
    public MyImageClass(BufferedImage image) {
        this.originalImage=image;
        this.modifieImage=image;
        this.posX=0;
        this.posY=0;
        this.zoom=100;
    }
    
    public void paintImage (Graphics g){
        g.drawImage(modifieImage, posX, posY, null);
        modifieImage.flush();
        g.dispose();
    }
    
    public void changePosition (int movX,int movY){
        this.posX+=movX;
        this.posY+=movY;
    }
    
    public void changeSize(boolean incremetar){
        if (!newZoom(zoom, incremetar)) return;
        
        Medidas med=aspectRario(this.originalImage.getWidth(), this.originalImage.getHeight(), ((double)this.zoom/100));
        if (med==null) return;
        
        BufferedImage nueva = new BufferedImage(med.getVariable1(), med.getVariable2(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = nueva.createGraphics();
        resizeCenter(modifieImage.getWidth(), modifieImage.getHeight(), med.getVariable1(), med.getVariable2());
        
        g.drawImage(originalImage,0, 0, med.getVariable1(), med.getVariable2(), null);
        g.dispose();
        modifieImage.flush();
        this.modifieImage=nueva;
        nueva.flush();
    }
    
    private boolean newZoom (int zoom,boolean incrementar){
        int nuevoZoom;
        if (incrementar) nuevoZoom=zoom+this.jumpsZoom;
        else nuevoZoom=zoom-this.jumpsZoom;
        if (nuevoZoom>=Util.MIN_ZOOM && nuevoZoom<=Util.MAX_ZOOM){
            this.zoom=nuevoZoom;
            return true;
        } else return false;
    }
    
    private Medidas aspectRario (int widthOri,int heightOri,double valor){
        int widthNew=(int)(widthOri*valor);
        int heightNew=(int)(heightOri*valor);
        if (widthNew<=0 || heightNew<=0) return null;
        else return new Medidas(widthNew, heightNew);
    }
    
    private void resizeCenter (int xOld,int yOld,int xNew,int yNew){
        int auxX=(xOld-xNew)/2; //SI NEGATIVO SIG QUE INCREMENTO
        int auxY=(yOld-yNew)/2;
        this.changePosition(auxX, auxY);
    }
    
    public void changeSizeGoodQuality (){
        Image nueva=(Image)originalImage;
        nueva=nueva.getScaledInstance(modifieImage.getWidth(),modifieImage.getHeight(), Image.SCALE_SMOOTH);
        BufferedImage nuevaImagen=new BufferedImage(modifieImage.getWidth(),modifieImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g=nuevaImagen.createGraphics();
        g.drawImage(nueva, 0, 0, null);
        g.dispose();
        this.modifieImage.flush();
        this.modifieImage=nuevaImagen;
        nuevaImagen.flush();
    }
}
