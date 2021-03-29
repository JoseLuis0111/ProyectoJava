import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Mono{

    private static int PosMonoX = 370, PosMonoY = 480;
    private static File playerC1 = new File("img/MonoCuadro1.png");
    private static File playerC2 = new File("img/MonoCuadro2.png");
    private static Image MonoC1, MonoC2;

    public Mono(){

        try{
            MonoC1 = ImageIO.read(playerC1);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }

        try{
            MonoC2 = ImageIO.read(playerC2);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }

    }

    public Image getImgMonoC1(){
        return MonoC1;
    }

    public Image getImgMonoC2(){
        return MonoC2;
    }

    public int getPosMonoX(){
        return PosMonoX;
    }

    public int getPosMonoY(){
        return PosMonoY;
    }

    public void setPosMonoX(int posMonoX){
        PosMonoX = posMonoX;
    }

    public void setPosMonoY(int posMonoY){
        PosMonoY = posMonoY;
    }
}
