package Items;

import java.util.Random;

public class Armor {
    String name;
    int pv;

    public Armor(int pv) {
        this.pv = pv;
        this.name= getName();
    }

    private String getName() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(Names.ArmorNames.values().length);
        return Names.ArmorNames.values()[randomIndex].getName();
    }

    @Override
    public String toString() {
        return "Armor{" +
                "name='" + name + '\'' +
                ", pv=" + pv +
                '}';
    }
}
