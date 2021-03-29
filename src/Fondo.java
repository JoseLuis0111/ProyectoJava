import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Fondo{

    private static File background = new File("img/Escenario.bmp");
    private static Image Fondo;

    public Fondo(){
        try{
            Fondo = ImageIO.read(background);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }
    }

    public Image getImgFondo(){
        return Fondo;
    }


}
