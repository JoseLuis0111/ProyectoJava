import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Evento implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Acción: " + actionEvent.getActionCommand());
        System.out.println("Botón presionado");
    }
}
