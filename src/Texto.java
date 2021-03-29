import javax.swing.*;
import java.awt.*;

public class Texto extends JPanel {

    private String Texto;
    private int w,h;

    public Texto(String Texto, int w, int h){
        this.Texto = Texto;
        this.w = w;
        this.h = h;
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        g.drawString(Texto, w, h);
    }
}
