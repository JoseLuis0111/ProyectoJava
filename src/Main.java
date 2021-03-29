public class Main {
    public static void main(String[] args) {

        Ventana window = new Ventana(800,600);
        window.setTitle("Juego");

        Juego juego = new Juego();
        window.add(juego);

        window.setVisible(true);

    }
}

