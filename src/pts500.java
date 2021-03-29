import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class pts500{

    private static File pts500C1 = new File("img/500pC1.png");
    private static File pts500C2 = new File("img/500pC2.png");
    private static Image pts_500c1, pts_500c2;

    public pts500(){

        try{
            pts_500c1 = ImageIO.read(pts500C1);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }

        try{
            pts_500c2 = ImageIO.read(pts500C2);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }

    }

    public Image getImgPts500c1(){
        return pts_500c1;
    }

    public Image getImgPts500c2(){
        return pts_500c2;
    }
}
