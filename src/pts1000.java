import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class pts1000{

    private static File pts1000C1 = new File("img/1000pC1.png");
    private static File pts1000C2 = new File("img/1000pC2.png");
    private static Image pts1000c1, pts1000c2;

    public pts1000(){

        try{
            pts1000c1 = ImageIO.read(pts1000C1);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }

        try{
            pts1000c2 = ImageIO.read(pts1000C2);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }

    }

    public Image getImgPts1000c1(){
        return pts1000c1;
    }

    public Image getImgPts1000c2(){
        return pts1000c2;
    }
}
