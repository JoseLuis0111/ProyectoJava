import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Pelota{

    private static File ball = new File("img/Pelota.png");
    private static Image ImgPelota;
    private int PosBallX = (int)(Math.random() * (790 + 1)), PosBallY = -10;

    public Pelota(){
        try{
            ImgPelota = ImageIO.read(ball);
        }catch(IOException e){
            System.out.println("Imagen no encontrada");
        }
    }

    public void setPosBallX(int x){
        PosBallX = x;
    }

    public void setPosBallY(int y){
        PosBallY = y;
    }

    public int getPosBallX(){
        return PosBallX;
    }

    public int getPosBallY(){
        return PosBallY;
    }

    public Image getImgPelota() {
        return ImgPelota;
    }
}
