
package Componentes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ViewFinder {
    
    private int espacioRectasHori;
    private int espacioRectasVerti;
    private final int grosorRectas;
    private final int grosorExtremos;
    private int ancho,largo;
    private final int numHorizontal;
    private final int numVertical;
    public final static ViewFinder DEFAULT=new ViewFinder(100, 100, 1, 10, 2, 2);
    private BufferedImage image;

    public ViewFinder(double pWidth,double pHeight,int grosorRectas,int grosorExtremos,int numHorizontal,int numVertical) {
        this.grosorRectas=grosorRectas;
        this.grosorExtremos=grosorExtremos;
        this.numHorizontal=numHorizontal;
        this.numVertical=numVertical;
        this.calcularMedidas(pWidth, pHeight);
    }
    public void calcularMedidas (double pWidth, double pHeight){
        double auxW=Util.PANEL_WIDHT/pWidth;
        double auxH=Util.PANEL_HEIGHT/pHeight;
        double aux=Math.min(auxW, auxH);
        this.ancho=(int)(pWidth*aux);
        this.largo=(int)(pHeight*aux);
        
        this.espacioRectasHori=(this.ancho-(grosorExtremos*2+numVertical*grosorRectas))/(numVertical+1);
        this.espacioRectasVerti=(this.largo-(grosorExtremos*2+numHorizontal*grosorRectas))/(numHorizontal+1);
        crearBufferedImage();
    }
    private void crearBufferedImage (){
        this.image=new BufferedImage(this.ancho, this.largo, BufferedImage.TYPE_INT_ARGB);
        Graphics g=this.image.getGraphics();
        g.setColor(Color.BLACK);
        int aux;
        //BORDES
        g.fillRect(0, 0, this.ancho, this.grosorExtremos);
        g.fillRect(0, this.largo-this.grosorExtremos, this.ancho, this.grosorExtremos);
        g.fillRect(0, 0, this.grosorExtremos, this.largo);
        g.fillRect(this.ancho-this.grosorExtremos, 0, this.grosorExtremos, this.largo);
        //RECTAS VERTICALES
        aux=this.grosorExtremos+this.espacioRectasHori;
        for (int i=0;i<this.numVertical;i++){
            g.fillRect(aux, 0, this.grosorRectas, this.largo);
            aux+=this.espacioRectasHori+this.grosorRectas;
        }
        //RECTAS HORIZONTALES
        aux=this.grosorExtremos+this.espacioRectasVerti;
        for (int i=0;i<this.numHorizontal;i++){
            g.fillRect(0, aux, this.ancho, this.grosorRectas);
            aux+=this.espacioRectasVerti+this.grosorRectas;
        }
    }
    
    public void paint (Graphics g){
        g.drawImage(this.image, 0, 0, null);
    }
    
    public int getGrosorMargen (){
        return this.grosorExtremos;
    }
}
