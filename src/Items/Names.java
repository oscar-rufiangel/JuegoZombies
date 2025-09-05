package Items;

public class Names {
    enum ArmorNames {
        //Armaduras Comunes (21-40)
        armor1("Conjunto del Recolector de Basura"),
        armor2("Vestidura del Refugio Abandonado"),
        armor3("Equipo del Civil Fortificado"),
        armor4("Armadura del Vigilante de Barrios"),
        //Armaduras Poco Comunes (41-60)
        armor5("Atuendo del Campamento Improvisado"),
        armor6("Set del Rastreador de Carroñeros"),
        armor7("Armadura del Motero Blindado"),
        armor8("Equipo de la Patrulla del Silencio"),
        armor9("Vestimenta del Mensajero del Pánico"),
        armor10("Conjunto del Mecánico de Frontera"),
        //Armaduras Raras (61-80)
        armor11("Armadura del Asaltante del Metro"),
        armor12("Set del Exiliado Radiactivo"),
        armor13("Indumentaria del Francotirador de Tejados"),
        armor14("Equipo del Cazador de Infectados"),
        armor15("Vestidura del Guardián del Subterráneo"),
        //Armaduras Épicas (81-100)
        armor16("Armadura del Paladín del Ocaso"),
        armor17("Vestidura del General de las Ruinas"),
        armor18("Set del Profeta del Colapso"),
        armor19("Equipo de la Resistencia Fantasma"),
        armor20("Indumentaria del Sargento del Último Muro"),
        //Armaduras Legendarias (100>)
        armor21("Armadura de Vharkon, el Matador de Plagas"),
        armor22("Set del Último Heraldo de la Humanidad"),
        armor23("Vestidura de Arz’Nok, Señor de las Torretas"),
        armor24("Conjunto de la Legión Inmortal del Norte"),
        armor25("Equipo de Helix, el Portador de la Cura Prohibida");

        private final String name;

        ArmorNames(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    enum WeaponNames {
        weapon1("Pico de Minero"),
        weapon2("Hacha de Madera"),
        weapon3("Pico de Hierro"),
        weapon4("Hacha de Hierro"),
        weapon5("Hacha de Acero");
        private final String name;

        WeaponNames(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
