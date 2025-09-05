import java.util.Scanner;

public class Player {
    int pv;
    int dmg;
    static String name;
    public static int [] position= {1,1};

    public Player(String nam, int pv, int dmg) {
        name = name;
        this.pv = pv;
        this.dmg = dmg;
    }
    public static int [] move(){
        Scanner sc = new Scanner(System.in);
        int direccion=0;
        while(direccion<1 || direccion>4){
            System.out.println("¿A donde quieres moverte?(1 = Derecha, 2 = Izquierda, 3 = Arriba, 4 = Abajo)");
            direccion = sc.nextInt();
            switch (direccion){
                case 1 -> {
                    if(position[1]>= Tablero.mapa.length){
                        System.out.println("No se puede mover más hacia la derecha.");
                        direccion=5;
                    }else{
                        position[1]+=1;
                    }
                }
                case 2->position[1]-=1;
                case 3->position[0]-=1;
                case 4->position[0]+=1;
                default ->  System.out.println("Opcion no correcta");
            }

        }
        return position;
    }
}
