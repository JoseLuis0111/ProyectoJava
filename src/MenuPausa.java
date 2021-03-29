import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuPausa{

    private static File PauseMenu = new File("img/Pausa.bmp");
    private static Image ImgMenuPausa;

    public MenuPausa(){
        try{
            ImgMenuPausa = ImageIO.read(PauseMenu);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }
    }

    public Image getImgMenuPausa(){
        return ImgMenuPausa;
    }
}
