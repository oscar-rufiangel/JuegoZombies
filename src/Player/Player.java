package Player;

import Items.Armor;

public class Player {
    int pv;
    int dmg;
    static String name;
    public static int [] posicion= {1,1};

    public Player(String nam, int pv, int dmg) {
        name = name;
        this.pv = pv;
        this.dmg = dmg;
    }
    public static int [] move(){
        System.out.println("¿A donde quieres moverte?");
        posicion[1]+=1;
        return posicion;
    }
}
