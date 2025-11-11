
import java.io.Serializable;
import java.util.Random;

abstract public class Humano implements Serializable, I_Humano  {
    transient Random rand = new Random();
    // Atributos básicos
    private int PV;              // Puntos de vida
    private int PA;              // Puntos de ataque
    public final String nombre;  // Nombre del personaje

    // Getters y setters
    public int getPV() {
        return PV;
    }

    public void setPV(int PV) {
        this.PV = PV;
        // La vida nunca puede ser negativa
        if (this.PV < 0) {
            this.PV = 0;
        }
    }

    public int getPA() {
        return PA;
    }

    public void setPA(int PA) {
        this.PA = PA;
    }

    // Devuelve un valor aleatorio entre 0 y PA
    public int atacar() {
        return rand.nextInt(getPA() + 1);
    }

    // Constructor: inicializa los valores de vida, ataque y nombre
    public Humano(int PV, int PA, String nombre) {
        this.PV = PV;
        this.PA = PA;
        this.nombre = nombre;
    }
    abstract public String toString();
}
