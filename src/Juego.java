
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Juego implements Serializable {

    transient Scanner sc = new Scanner(System.in);
    transient Random rand = new Random();
    // Variables estáticas que controlan el estado global del juego
    int nivel = 1;          // Nivel o sala actual
    int dificultad;         // Número total de salas a superar
    int cantZombie = 1;     // Cantidad de zombies en la sala
    int contBuscar = 3;     // Intentos disponibles para buscar en la sala
    Personaje p;

    public Juego(int dificultad, Personaje p) {
        this.dificultad = dificultad;
        this.p = p;
    }

    public void juego() {
        System.out.println("Sala: " + nivel);
        

        // El bucle principal del juego: se repite mientras el jugador siga vivo
        // y no haya superado todas las salas
        while (!comprobarVida(p.getPV()) && nivel != this.dificultad + 1) {
            System.out.println(p);
            if (cantZombie > 0) {
                System.out.println("\nHa aparecido un Zombie.");
                Zombie z = new Zombie(nivel, rand.nextInt(1, 2));
                System.out.println(z);
                // Combate entre el jugador y el zombie hasta que uno muera
                while (!comprobarVida(p.getPV()) && !comprobarVida(z.getPV())) {
                    System.out.println("¿Qué quieres hacer?");
                    System.out.println("1.-Atacar");
                    sc.next();
                    combate(p, z, 0);
                    if (comprobarVida(z.getPV())) {
                        System.out.println("Zombie derrotado");
                        cantZombie--;
                    } else {
                        System.out.println("El zombie va a atacar");
                        combate(z, p, p.getCantArmaduras());
                    }
                }
            } else {
                // Si no quedan zombies, se muestra el menú de opciones
                menu();
            }
        }
        // Resultado final del juego
        String condicion;
        if (comprobarVida(p.getPV())) {
            System.out.println("Has sido derrotado\n:(");
            condicion = "DERROTA";
        } else {
            System.out.println("Has ganado, ¡enhorabuena!");
            condicion = "VICTORIA";
        }
        guardarHistorial(condicion);
    }

    void menu() {
        int opcionMin, opcionMax;
        int opcion = 10;

        // Caso especial: última sala -> opción de salir
        if (nivel == dificultad) {
            System.out.println("3.-Salir de la mansión.");
            nivel++;
            sc.next();
            return;
        }

        // Opciones disponibles según si el jugador tiene botiquín y/o intentos de búsqueda
        if (p.isBotiquin() && contBuscar > 0) {
            System.out.println("2.-Buscar en la habitación. Tienes " + contBuscar + " intentos.");
            System.out.println("3.-Avanzar de sala.");
            System.out.println("4.-Curarse.");
            opcionMin = 2;
            opcionMax = 5;
        } else if (p.isBotiquin()) {
            System.out.println("3.-Avanzar de sala.");
            System.out.println("4.-Curarse.");
            opcionMin = 3;
            opcionMax = 5;
        } else if (contBuscar > 0) {
            System.out.println("2.-Buscar en la habitación. Tienes " + contBuscar + " intentos.");
            System.out.println("3.-Avanzar de sala.");
            opcionMin = 2;
            opcionMax = 5;
        } else {
            System.out.println("3.-Avanzar de sala.");
            opcionMin = 3;
            opcionMax = 5;
        }

        // Comprueba que la opción es valida.
        while (opcion < opcionMin || opcion > opcionMax) {
            System.out.println("Elige una opción");
            opcion = sc.nextInt();
        }

        // Ejecuta según la la acción seleccionada
        switch (opcion) {
            case 2 ->
                buscarEnHabitacion();
            case 3 -> {
                nivel++;
                contBuscar = 3;   // Se reinician intentos de búsqueda
                cantZombie = 1;   // Nuevo zombie en la siguiente sala
                System.out.println("Avanzas a la siguiente sala. Lvl:" + nivel);
                System.out.println("Ha aparecido un Zombie.");
            }
            case 4 -> {
                p.curarse();
            }
            case 5 -> {
                Main.guardarPartida();
            }
            default ->
                System.out.println("Opción no válida.");
        }
    }

    void buscarEnHabitacion() {
        contBuscar--;
        System.out.println("Rebuscas en la habitación...");
        int resultado = rand.nextInt(1, 100);

        // Probabilidades de lo que se encuentra al buscar
        if (resultado <= 75) {
            ruido(); // Puede atraer zombies
        } else if (resultado <= 90) {
            System.out.println("Has encontrado un botiquin.\n");
            p.setBotiquin(true);
        } else if (resultado <= 95) {
            System.out.println("Has encontrado una armadura.\n");
            p.setCantArmaduras(1);
        } else {
            System.out.println("Has encontrado un arma.\n");
            p.setCantArmas(1);
        }
    }

    void combate(Humano atacante, Humano defensor, int armadura) {
        int daño = atacante.atacar() - armadura;
        if (daño <= 0) {
            daño = 0;
        }
        defensor.setPV(defensor.getPV() - daño);
        System.out.println("Ha " + defensor.nombre + " le quedan " + defensor.getPV() + " PV");
    }

    // Devuelve true si está muerto
    boolean comprobarVida(int PV) {
        return PV <= 0;
    }

    private void ruido() {
        int resultado = rand.nextInt(1, 100);
        // Al azar, puede no pasar nada, aparecer 1 zombie o 2
        if (resultado <= 40) {
            System.out.println("Haces ruido pero parece que todo sigue igual.");
        } else if (resultado <= 80) {
            System.out.println("Ha aparecido un zombie!!!");
            cantZombie = 1;
        } else {
            System.out.println("¡Cuidado! Han aparecido 2 zombies.");
            cantZombie = 2;

        }
    }

    private void guardarHistorial(String condicion) {
        String dificultadSt;
        if (dificultad == 5) {
            dificultadSt = "FACIL";
        } else if (dificultad == 7) {
            dificultadSt = "NORMAL";
        } else {
            dificultadSt = "DIFICIL";
        }
        try (BufferedWriter bW = new BufferedWriter(new FileWriter("src/ficheros/historial.txt", true))) {
            bW.write("Resultado = " + condicion
                    + " Dificultad = " + dificultadSt
                    + " Habitación = " + nivel
                    + " PVRestantes = " + p.getPV()
                    + " ¿Botiquin? = " + p.isBotiquin()
                    + " Armas = " + p.getCantArmas()
                    + " Armaduras = " + p.getCantArmaduras() + "\n");
        } catch (IOException e) {
            System.out.println("Error al encontrar el historial de la partida.");
        }

    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException {

        in.defaultReadObject();

        this.sc = new Scanner(System.in);
        this.rand = new Random();
    }

}
