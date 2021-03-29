import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Obstaculo{

    private int PosObsX = (int)(Math.random() * (649 + 1)), PosObsY = 140;
    private static Image Obstaculo;
    private static File obstacle = new File("img/Obstaculo150x15.png");

    public Obstaculo(){
        try{
            Obstaculo = ImageIO.read(obstacle);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }
    }

    public Image getImgObstaculo(){
        return Obstaculo;
    }

    public int getPosObsX(){
        return PosObsX;
    }

    public int getPosObsY(){
        return PosObsY;
    }

    public void setPosObsX(int posObsX){
        PosObsX = posObsX;
    }

    public void setPosObsY(int posObsY){
        PosObsY = posObsY;
    }
}
