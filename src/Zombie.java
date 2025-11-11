import java.io.Serializable;
import java.util.Random;

public class Zombie extends Humano implements Serializable{
    // El constructor genera un zombie con PV y PA aleatorios
    // en función del número de habitación (dificultad creciente).
    public Zombie(int numHabitacion, int rand) {
        super(
            rand + 2 + (numHabitacion - 1), // PV base + aleatorio
            rand + 2 + (numHabitacion - 1), // PA base + aleatorio
            "Zombie"
        );
    }

    @Override
    public int atacar() {
        int daño = super.atacar();
        System.out.println("El zombie ataca y te hace " + daño + " puntos de daño");
        return daño;
    }

    @Override
    public String toString() {
        return "\n===" + nombre + "===\nPV = " + getPV() + "\nPA = " + getPA();
    }
}
