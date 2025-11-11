
import java.io.Serializable;

public class Personaje extends Humano implements Serializable{

    // Vida máxima del personaje (no puede superarse al curarse)
    final private int maxPV;

    // Estado y recursos del personaje
    private boolean botiquin;     // Indica si tiene un botiquín disponible
    private int cantArmas;        // Cantidad de armas extra que aumentan el daño
    private int cantArmaduras;    // Cantidad de armaduras que reducen daño recibido

    // Getters y setters
    public boolean isBotiquin() {
        
        return botiquin;
    }

    public void setBotiquin(boolean botiquin) {
        this.botiquin = botiquin;
    }

    public int getCantArmas() {
        return cantArmas;
    }

    // Se acumulan armas en lugar de sobrescribir el valor
    public void setCantArmas(int cantArmas) {
        this.cantArmas += cantArmas;
    }

    public int getCantArmaduras() {
        return cantArmaduras;
    }

    // Se acumulan armaduras en lugar de sobrescribir el valor
    public void setCantArmaduras(int cantArmaduras) {
        this.cantArmaduras += cantArmaduras;
    }

    // Constructor: inicializa con valores base
    public Personaje(String nombre) {
        super(20, 4, nombre);  // PV = 20, PA = 4
        this.maxPV = 20;
        this.botiquin = false;
        this.cantArmas = 0;
        this.cantArmaduras = 0;
    }

    @Override
    public int atacar() {
        int daño = super.atacar();
        if (daño != 0) {
            daño += cantArmas; // Se suma el bonus de armas
            System.out.println("Has atacado al zombi y le has hecho " + daño + " puntos de ataque");
        } else {
            System.out.println("Has fallado el ataque estrepitosamente.");
        }
        return daño;
    }

    // Método para curarse usando un botiquín
    public void curarse() {
        if (botiquin) {
            if (getPV() + 4 >= maxPV) {
                setPV(maxPV); // No puede superar la vida máxima
            } else {
                setPV(getPV() + 4);
            }
            System.out.println("Te has curado con el botiquín, tu vida ahora es " + getPV());
            botiquin = false; // Se consume el botiquín
        }
    }

    @Override
    public String toString() {
        String botiquin = isBotiquin() ? "sí" : "no";
        return "===" + nombre + "===\nPV = " + getPV() + "\nPA = " + getPA()
                + "\nBotiquín = " + botiquin
                + "\nCantidad de Armas = " + getCantArmas()
                + "\nCantidad de Armaduras = " + getCantArmaduras();
    }
}
