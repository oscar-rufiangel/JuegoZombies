package Player;

import Items.Armor;

public class Player {
    int pv;
    int dmg;
    Armor armor;
    String weapon;
    String extraItem;
    String name;

    public Player(String name, int pv, int dmg) {
        this.name = name;
        this.pv = pv;
        this.dmg = dmg;
        this.armor = armor;
        this.weapon = weapon;
        this.extraItem = extraItem;
    }
}
