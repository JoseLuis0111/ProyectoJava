import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Timer;

public class Juego extends JPanel implements KeyListener, ActionListener {

    private Fondo Fondo = new Fondo();
    private Pelota Pelota = new Pelota();
    private Mono Mono = new Mono();
    private MenuPausa MenuPausa = new MenuPausa();
    private pts500 pts500 = new pts500();
    private pts1000 pts1000 = new pts1000();
    private Obstaculo Obstaculo = new Obstaculo();

    private int Balls = 8, Score = 0, VelPelota = 3, VelMono = 25;
    private String Nombre = "", Tmp = "", NewScore;

    private int PosIniBall = (int)(Math.random() * (780 + 10)), p = 0;

    boolean ObsMovDer = true, ObsMovIzq = false, Golpear = false, PosIniBallMovDer = true, PosIniBallMovIzq = false;
    boolean BajarP = true, SubirPIzq = false, SubirPDer = false, BajarPIzq = false, BajarPDer = false, end = false, pause = false;

    private ArrayList<String> Puntuaciones = new ArrayList<>();

    public Juego(){

        addKeyListener(this);
        setFocusable(true);
        int delay = 10;
        Timer timer = new Timer(delay, this);
        timer.start();
    }

    public int PuntoColision(int x1, int x2){

        int p = 0;

        while(x1 != x2){
            x1++;
            p++;
            if(x1 == x2){
                return p;
            }
        }
        return p;
    }

    public void paint(Graphics g){

        g.drawImage(Fondo.getImgFondo(), 0, 0, null);
        g.drawImage(Obstaculo.getImgObstaculo(), Obstaculo.getPosObsX(), Obstaculo.getPosObsY(), null);

        if(!Golpear){
            g.drawImage(Mono.getImgMonoC1(), Mono.getPosMonoX(), Mono.getPosMonoY(), null);
        }else{
            g.drawImage(Mono.getImgMonoC2(), Mono.getPosMonoX(), Mono.getPosMonoY(), null);
        }

        if(Pelota.getPosBallX() + 6 >= 125 && Pelota.getPosBallX() + 6 <= 245 && Pelota.getPosBallY() <= 18 && (SubirPIzq || SubirPDer)){
            g.drawImage(pts500.getImgPts500c2(), 125, 0, null);
            Score+=500;
        }else{
            g.drawImage(pts500.getImgPts500c1(), 125, 0, null);
        }

        if(Pelota.getPosBallX() + 6 >= 370 && Pelota.getPosBallX() + 6 <= 430 && Pelota.getPosBallY() <= 18 && (SubirPIzq || SubirPDer)){
            g.drawImage(pts1000.getImgPts1000c2(), 370, 0, null);
            Score+=1000;
        }else{
            g.drawImage(pts1000.getImgPts1000c1(), 370, 0, null);
        }

        if(Pelota.getPosBallX() + 6 >= 555 && Pelota.getPosBallX() + 6 <= 675 && Pelota.getPosBallY() <= 18 && (SubirPIzq || SubirPDer)){
            g.drawImage(pts500.getImgPts500c2(), 555, 0, null);
            Score+=500;
        }else{
            g.drawImage(pts500.getImgPts500c1(), 555, 0, null);
        }

        if(Balls > 0)
            g.drawImage(Pelota.getImgPelota(), Pelota.getPosBallX(), Pelota.getPosBallY(), null);

        g.setColor(Color.MAGENTA);
        g.drawString("Score: " + Score, 5, 15);
        g.drawString("Balls: " + Balls, 745, 15);
        //g.drawString("P.Col.: " + p, 745, 35);

        if(end){
            g.drawString("Fin del juego",370, 220);
            g.drawString("Ingrese su nombre:",350, 270);
            g.drawString(Nombre,350, 290);
        }

        if(pause)
            g.drawImage(MenuPausa.getImgMenuPausa(), 100, 100, null);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //Mov. obstaculo
        if(Obstaculo.getPosObsX() <= 650 && ObsMovDer && !pause){
            Obstaculo.setPosObsX( Obstaculo.getPosObsX() + 4 );
            if(Obstaculo.getPosObsX() >= 650){
                ObsMovDer = false;
                ObsMovIzq = true;
            }
        }

        if(Obstaculo.getPosObsX() >= 0 && ObsMovIzq && !pause){
            Obstaculo.setPosObsX( Obstaculo.getPosObsX() - 4 );
            if(Obstaculo.getPosObsX() <= 0){
                ObsMovDer = true;
                ObsMovIzq = false;
            }
        }

        //Lanzar pelota
        if(PosIniBall <= 790 && PosIniBallMovDer){
            PosIniBall += 5;
            if(PosIniBall >= 790){
                PosIniBallMovDer = false;
                PosIniBallMovIzq = true;
            }
        }

        if(PosIniBall >= 0 && PosIniBallMovIzq){
            PosIniBall -= 5;
            if(Obstaculo.getPosObsX() <= 0){
                PosIniBallMovDer = true;
                PosIniBallMovIzq = false;
            }
        }

        if(Balls == 0){
            end = true;
            //Ventana Menu = new Ventana(600,400);
            //Menu.setTitle("Juego - Puntuaciones");
            //Menu.setVisible(true);
            //timer.stop();
        }

        if(end){
            BajarPDer = false;
            BajarPIzq = false;
            BajarP = false;
        }


        //****************************************************************************
        //Comportamiento Pelota

        if( Pelota.getPosBallX() + 6 >= Mono.getPosMonoX() + 48 && (Pelota.getPosBallX()+6)<Mono.getPosMonoX()+77 && (Pelota.getPosBallY()+12)>=Mono.getPosMonoY()+36 && (Pelota.getPosBallY()+12)<=Mono.getPosMonoY()+70){
            p = 28 - PuntoColision(Mono.getPosMonoX() + 48, Pelota.getPosBallX() + 6);
            BajarP = false;
            BajarPIzq = false;
            BajarPDer = false;
            SubirPIzq = true;
        }

        if( Pelota.getPosBallX() + 6 >= Mono.getPosMonoX()+77 && Pelota.getPosBallX() + 6 <= Mono.getPosMonoX()+105 && (Pelota.getPosBallY()+12)>=Mono.getPosMonoY()+36 && (Pelota.getPosBallY()+12)<=Mono.getPosMonoY()+70){
            p = PuntoColision(Mono.getPosMonoX() + 77, Pelota.getPosBallX() + 6);
            BajarP = false;
            BajarPIzq = false;
            BajarPDer = false;
            SubirPDer = true;
        }

        if(BajarP && !pause){
            Pelota.setPosBallY( Pelota.getPosBallY() + VelPelota );
            if(Pelota.getPosBallY() >= 600){
                Pelota.setPosBallX( (int)(Math.random() * (780 + 10)) );
                Pelota.setPosBallY(16);
                Balls--;
                VelPelota = 3;
            }
            if(Pelota.getPosBallX() + 6 >= Obstaculo.getPosObsX() && Pelota.getPosBallX() + 6 <= Obstaculo.getPosObsX()+80 && Pelota.getPosBallY() + 12 >= 150 && Pelota.getPosBallY() <= 160){
                BajarP = false;
                SubirPIzq = true;
            }
        }

        if(SubirPIzq && !pause){
            Pelota.setPosBallY( Pelota.getPosBallY() - VelPelota );
            if(Pelota.getPosBallX() > 0){
                if(p < 20){
                    Pelota.setPosBallX( Pelota.getPosBallX() - (int)((p*2)*0.1) );
                }else{
                    Pelota.setPosBallX( Pelota.getPosBallX() - (int)((p)*0.1) );
                }
            }
            if(Pelota.getPosBallX() <= 0){
                SubirPIzq = false;
                SubirPDer = true;
            }
            if(Pelota.getPosBallY() <= 0 || Pelota.getPosBallX()+6>=370 && Pelota.getPosBallX()+6<=430 && Pelota.getPosBallY() <= 15 || Pelota.getPosBallX() + 6 >= 125 && Pelota.getPosBallX() + 6 <= 245 && Pelota.getPosBallY() <= 15 || Pelota.getPosBallX() + 6 >= 555 && Pelota.getPosBallX() + 6 <= 675 && Pelota.getPosBallY() <= 15 || Pelota.getPosBallX() + 6 >= Obstaculo.getPosObsX() && Pelota.getPosBallX() + 6 <= Obstaculo.getPosObsX()+150 && Pelota.getPosBallY() <= 155 && Pelota.getPosBallY() >= 140){
                SubirPIzq = false;
                BajarPIzq = true;
            }
        }

        if(SubirPDer && !pause){
            Pelota.setPosBallY( Pelota.getPosBallY() - VelPelota );
            if(Pelota.getPosBallX() + 12 < 800){
                if(p<20){
                    Pelota.setPosBallX( Pelota.getPosBallX() + (int)((p*2)*0.1) );
                }else{
                    Pelota.setPosBallX( Pelota.getPosBallX() + (int)((p)*0.1) );
                }
            }
            if(Pelota.getPosBallX() + 12 >= 800){
                SubirPDer = false;
                SubirPIzq = true;
            }
            if(Pelota.getPosBallY() <= 0 || Pelota.getPosBallX() + 6 >= 370 && Pelota.getPosBallX() + 6 <= 430 && Pelota.getPosBallY() <= 15 || Pelota.getPosBallX() + 6 >= 125 && Pelota.getPosBallX() + 6 <= 245 && Pelota.getPosBallY() <= 15 || Pelota.getPosBallX() + 6 >= 555 && Pelota.getPosBallX() + 6 <= 675 && Pelota.getPosBallY() <= 15 || Pelota.getPosBallX() + 6 >= Obstaculo.getPosObsX() && Pelota.getPosBallX() + 6 <= Obstaculo.getPosObsX()+150 && Pelota.getPosBallY() <= 155 && Pelota.getPosBallY() >= 140){
                SubirPDer = false;
                BajarPDer = true;
            }
        }

        if(BajarPIzq && !pause){
            Pelota.setPosBallY( Pelota.getPosBallY() + VelPelota );
            if(Pelota.getPosBallX() > 0){
                Pelota.setPosBallX( Pelota.getPosBallX() - (int)((p*10)*0.01) );
            }
            if(Pelota.getPosBallX() <= 0){
                BajarPIzq = false;
                BajarPDer = true;
            }
            if( Pelota.getPosBallY() >= 600){
                BajarPIzq = false;
                BajarP = true;
                Balls--;
                VelPelota = 3;
            }
            if(Pelota.getPosBallX() + 6 >= Obstaculo.getPosObsX() && Pelota.getPosBallX() + 6 <= Obstaculo.getPosObsX() + 150 && Pelota.getPosBallY() + 12 >= 140 && Pelota.getPosBallY() <= 155){
                BajarPIzq = false;
                SubirPIzq = true;
            }
        }

        if(BajarPDer && !pause){
            Pelota.setPosBallY( Pelota.getPosBallY() + VelPelota );
            if(Pelota.getPosBallX() + 12 < 800){
                Pelota.setPosBallX( Pelota.getPosBallX() + (int)((p)*0.1) );
            }
            if(Pelota.getPosBallX() + 12 >= 800){
                BajarPDer = false;
                BajarPIzq = true;
            }
            if(Pelota.getPosBallY() >= 600){
                BajarPDer = false;
                BajarP = true;
                Balls--;
                VelPelota = 3;
            }
            if(Pelota.getPosBallX() + 6 >= Obstaculo.getPosObsX() && Pelota.getPosBallX() + 6 <= Obstaculo.getPosObsX() + 150 && Pelota.getPosBallY() + 12 >= 140 && Pelota.getPosBallY() <= 155){
                BajarPDer = false;
                SubirPDer = true;
            }
        }

        if(Golpear)
            VelPelota = 5;

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        //System.out.println("hola");
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        //System.out.println(keyEvent.getKeyCode());
        //System.out.println(keyEvent.getKeyChar());
        if(end && keyEvent.getKeyCode() != 10)
            Nombre = Nombre + keyEvent.getKeyChar();
        //System.out.println(Nombre);

        switch(keyEvent.getKeyCode()){
            case 37:
                if(Mono.getPosMonoX() > -50 && !pause)
                    Mono.setPosMonoX( Mono.getPosMonoX() - VelMono );
                /*System.out.println("Izquierda");*/ break;
            case 39:
                if(Mono.getPosMonoX() + 100 <= 800 && !pause)
                    Mono.setPosMonoX( Mono.getPosMonoX() + VelMono );
                /*System.out.println("Derecha");*/ break;
            case 40:
                if(Mono.getPosMonoY() + 100 <= 560 && !pause)
                    Mono.setPosMonoY( Mono.getPosMonoY() + VelMono );
                /*System.out.println("Abajo");*/ break;
            case 38:
                if(Mono.getPosMonoY() + 100 >= 350 && !pause)
                    Mono.setPosMonoY( Mono.getPosMonoY() - VelMono );
                /*System.out.println("Arriba");*/ break;
            case 88:
                Golpear = true; break;
            case 27:
                System.exit(0); break;
        }

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

        if(keyEvent.getKeyCode() == 88)
            Golpear = false;

        if(keyEvent.getKeyCode() == 10 && Balls > 0)
            if(!pause)
                pause = true;
            else
                pause = false;

        if(end && keyEvent.getKeyCode() == 10){
            //System.out.println("fin");

            NewScore = Nombre + ": " + Score + "\n";
            Puntuaciones.add(NewScore);

            try {
                FileReader Archivo = new FileReader("Puntuaciones.txt");
                int c;

                do{
                    c = Archivo.read();
                    //System.out.print((char) c);
                    if(c != -1)
                        Tmp = Tmp + (char)c;
                }while(c != -1);

                Archivo.close();

                Tmp = Tmp + "\n";

                //System.out.println("Puntuaciones:\n--------------------------------\n" + Tmp);
                System.out.println("Puntuaciones:\n--------------------------------\n");

                Puntuaciones.add(Tmp);
                Collections.sort(Puntuaciones);

                for (String i : Puntuaciones) {
                    System.out.println(i);
                }

                try {
                    FileWriter NewFile = new FileWriter("Puntuaciones.txt");

                    NewScore = Tmp + NewScore;

                    for(int i=0 ;i<NewScore.length(); i++){
                        NewFile.write(NewScore.charAt(i));
                    }

                    NewFile.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            } catch (IOException e) {
                try {
                    FileWriter Archivo = new FileWriter("Puntuaciones.txt");

                    for(int i=0 ;i<NewScore.length(); i++){
                        Archivo.write(NewScore.charAt(i));
                    }

                    Archivo.close();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            //Ventana Menu = new Ventana(600,400);
            //Menu.setTitle("Juego - Puntuaciones");
            //Menu.setVisible(true);
            //timer.stop();

            System.exit(0);
        }
    }
}
