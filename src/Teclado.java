import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {
    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //System.out.println(keyEvent.getKeyCode());
        //System.out.println("hola");
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        //System.out.println(keyEvent.getKeyCode());
        //System.out.println(keyEvent.getKeyChar());
        //System.out.println("hola");
        switch(keyEvent.getKeyCode()){
            case 37:
                System.out.println("Izquierda"); break;
            case 39:
                System.out.println("Derecha"); break;
            case 40:
                System.out.println("Abajo"); break;
            case 38:
                System.out.println("Arriba"); break;
            case 27:
                System.exit(0); break;
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        //System.out.println("hola");

    }
}
