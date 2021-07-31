
package Componentes;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class MyCanvas extends JPanel{

    private final Control cl;
    private ViewFinder vf;
    private MyImageClass imageClass;
    public MyCanvas(Control cl) {
        this.cl=cl;
        this.init(cl);
    }
    private void init (Control cl){
        MyListener listener=new MyListener(cl);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
    }
    
    public void setViewFinder (ViewFinder vf){
        this.vf=vf;
        this.repaint();
    }
    public void setImageClass (MyImageClass imageClass){
        this.imageClass=imageClass;
        this.repaint();
    }
    
    @Override
    public void paintComponent (Graphics g){
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, Util.PANEL_WIDHT, Util.PANEL_HEIGHT);
        if (imageClass!=null){
            imageClass.paintImage(g);
        }
        if (this.vf!=null){
            vf.paint(g);
        }
    }
}
