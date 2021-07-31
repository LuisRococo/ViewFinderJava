
package Componentes;

import java.awt.Cursor;
import java.awt.Dimension;
import java.io.File;
import javax.swing.JOptionPane;

public class Control {
    
    private final Componentes cm;
    private MyCanvas canvas;
    private final Frames.Frame_Main frame;
    public Control (Frames.Frame_Main frame) {
        this.frame=frame;
        this.cm=new Componentes();
        initCanvas();
    }
    private void initCanvas (){
        Dimension dim=new Dimension(Util.PANEL_WIDHT, Util.PANEL_HEIGHT);
        this.canvas=new MyCanvas(this);
        this.canvas.setMinimumSize(dim);
        this.canvas.setMaximumSize(dim);
        this.canvas.setPreferredSize(dim);
        this.canvas.setSize(dim);
        setCursorCanvas(Cursor.HAND_CURSOR);
        frame.agegarCanvas(canvas);
    }
    
    public void moveImage (int mX,int mY){
        this.cm.changePositionImage(mX, mY);
        this.canvas.repaint();
    }
    
    public void cambiarTamañoImagen (boolean incrementar){
        this.cm.changeSize(incrementar);
        this.canvas.repaint();
    }
    
    public void setCursorCanvas (int tipoCursor){
        this.canvas.setCursor(new Cursor (tipoCursor));
    } 
    
    public void imageGoodQuality (){
        this.cm.goodQualityImage();
        this.canvas.repaint();
    }
    
    public void cargarImagen (File fl){
        if (!fl.isFile()){
            JOptionPane.showMessageDialog(null, "ERROR: DEBE DE SELECCIONAR UNA IMAGEN");
        } else {
            if (cm.cargarImagen(fl)){
                this.canvas.setImageClass(cm.getMyImageClass());
            } else {
                JOptionPane.showMessageDialog(null, "ERROR: THERE WAS A PROBLEM TRYING TO UPLOAD THE PICTURE");
            }
        }
    }
    
    public void crearViewFinder (ViewFinder vf){
        cm.cargarViewFinder(vf);
        canvas.setViewFinder(cm.getViewFinder());
    }
    
    public ViewFinder getViewFinder (){
        return cm.getViewFinder();
    }   
    
    public void repaint(){
        this.canvas.repaint();
    }
}
