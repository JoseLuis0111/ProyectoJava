import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame /*implements ActionListener*/ {

    public Ventana(int w, int h){

        //Texto hola = new Texto("Hola", 750, 250);
        //add(hola);

        //Fondo background = new Fondo();
        //add(background);

        //Fondo background = new Fondo();
        //Texto hola = new Texto("Hola", 750, 670);
        //background.add(hola);

        //add(background);

        //Teclado teclado = new Teclado();
        //addKeyListener(teclado);

        //Evento Listener = new Evento();

        //JButton b1=new JButton("Boton 1");
        //b1.setBounds(50,100,80,30);
        //b1.setSize(20,50);
        //b1.addActionListener(this);
        //add(b1);

        //setLayout(new FlowLayout());

        setSize(w,h);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Toolkit Screen = Toolkit.getDefaultToolkit();
        Dimension ScreenSize = Screen.getScreenSize();
        int HeightScreen = ScreenSize.height;
        int WidthScreen = ScreenSize.width;
        setLocation((WidthScreen/2)-(w/2) ,(HeightScreen/2)-(h/2) );
        //setVisible(true);
    }
}

