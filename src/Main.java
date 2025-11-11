
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

public class Main {

    // Objetos compartidos para generar aleatoriedad y leer entradas del usuario
    static Scanner sc = new Scanner(System.in);
    static Juego partida;

    public static void main(String[] args) {
        int opcion = 10;

        int dificultad = 5;
        System.out.println("En este juego entrarás en una mansión abandonada llena de zombies.\n"
                + "Tu objetivo será explorar, sobrevivir\n"
                + "y descubrir los secretos que se esconden en su interior.");
        // Se pide el nombre del jugador
        System.out.println("Escribe tu nombre: ");
        String nombre = sc.nextLine();
        Personaje p = new Personaje(nombre);

        while (opcion != 5) {
            System.out.println("1.-Jugar(por defecto=facil \"5 niveles\")"
                    + "\n2.-Cargar Partida"
                    + "\n3.-Ver historico"
                    + "\n4.-Seleccionar dificultad"
                    + "\n5.-Salir del juego");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    partida = new Juego(dificultad, p);
                    partida.juego();
                    opcion = 5;
                }
                case 2 ->
                    cargarPartida();
                case 3 ->
                    mostrarHistorico();
                case 4 ->
                    dificultad = menuDificultad();
                case 5 ->
                    System.out.println("Saliendo del juego...");
                default ->
                    System.out.println("Opción no valida.");
            }
        }

        // Se inicia el juego
    }

    // Menú de selección de dificultad
    static int menuDificultad() {
        int opcion = 10;      // Se inicializa con un valor inválido para obligar a iniciar el bucle
        System.out.println("Escribe la dificultad:"
                + "\n 1.-Fácil (5 niveles)"
                + "\n 2.-Normal(7 niveles)"
                + "\n 3.-Difícil (10 niveles)");

        // Comprueba que la opción sea validó
        while (opcion < 0 || opcion > 4) {
            opcion = sc.nextInt();

        }
        // Se crea la partida según la dificultad elegida
        switch (opcion) {
            case 1:
                opcion = 5;
                break;
            case 2:
                opcion = 7;
                break;
            case 3:
                opcion = 10;
                break;
            default:
                throw new AssertionError();
        }
        return opcion;
    }

    private static void mostrarHistorico() {
        String lineaActual = "";
        try (BufferedReader bR = new BufferedReader(new FileReader("src/ficheros/historial.txt"))) {
            while ((lineaActual = bR.readLine()) != null) {
                System.out.println(lineaActual);
            }
        } catch (IOException e) {
            System.out.println("Error al encontrar el historial de la partida.");
        }
    }

    private static void cargarPartida() {

        try (FileInputStream fileIn = new FileInputStream("src/ficheros/save.bin");
             ObjectInputStream objIn = new ObjectInputStream(fileIn)) {
            partida = (Juego) objIn.readObject();
            System.out.println("¡Partida cargada con éxito!");
            partida.juego(); 
        } catch (IOException e) {
            System.out.println("ERROR: Ocurrió un error al leer el archivo de guardado.");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Las clases del juego no coinciden con las guardadas.");
            ex.printStackTrace();
        }
    }

    public static void guardarPartida() {
        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream("src/ficheros/save.bin"))) {
            objOut.writeObject(partida);
            System.out.println("Guardando partida...");
        } catch (IOException e) {
            System.out.println("--- ¡¡ERROR!! NO SE PUDO GUARDAR ---");
            e.printStackTrace();
        }
    }

}
