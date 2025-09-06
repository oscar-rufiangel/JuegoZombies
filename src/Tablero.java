import java.util.Arrays;
import java.util.Random;

public class Tablero {
    public static String [][] mapa = new String[3][3];
    public static String [][]mapaDescubrir={{"#","#","#"},{"#","P","#"},{"#","#","#"}};
    static void crearTablero(){
        double random,probSuelo;
        Random rand = new Random();
        probSuelo= 0.75;//Más Probabilidad de que haya suelo de que haya zombies.
        for(int i=0;i<mapa.length;i++){
            for (int f=0;f< mapa[i].length; f++) {
                random = rand.nextDouble();
                //203619=zombie 91004=suelo
                if(i==1&&f==1) {
                    mapa[i][f] = "91004";//Suelo para que no haya un zombie debajo del jugador
                }else{
                    mapa[i][f] = random>probSuelo?"203619":"91004";
                }
                System.out.print(mapa[i][f]+" ");
            }
            System.out.println();
        }
        mostrarTablero();
        System.out.println("Tablero inicial creado.");
    }

    //oscar arregla esta puta mierda no se que has hecho
    static void mover(){
        int [] oldPlayerPosition;int[] newPlayerPosition;
        oldPlayerPosition = Arrays.copyOf(Player.position,Player.position.length);//Coge la ubicación actual
        newPlayerPosition = Player.move();//Coge la nueva posicion del jugador
        //Posicion del juegador mapa[PlayerPosition[0]][PlayerPosition[1]]
        String infoCasilla=mapa[newPlayerPosition[0]][newPlayerPosition[1]];
        mapa[oldPlayerPosition[0]][oldPlayerPosition[1]]=infoCasilla;
        mapa[newPlayerPosition[0]][newPlayerPosition[1]]=mapa[oldPlayerPosition[0]][oldPlayerPosition[1]];
        mostrarTablero(newPlayerPosition,oldPlayerPosition);//muestra el tablero actualizado.
    }
    static void mostrarTablero(){
        String [][]mapaSinDescubrir={{"#","#","#"},{"#","P","#"},{"#","#","#"}};
        for(String[] casilla: mapaSinDescubrir){
            for(int i=0;i<casilla.length;i++){
                System.out.print(casilla[i]+" ");
            }
            System.out.println(" ");
        }
    }

    static void mostrarTablero(int [] newPlayerPosition, int[] oldPlayerPosition){

        if(!mapaDescubrir[oldPlayerPosition[0]][oldPlayerPosition[1]].equals(mapa[oldPlayerPosition[0]][oldPlayerPosition[1]])){
            mapaDescubrir[oldPlayerPosition[0]][oldPlayerPosition[1]]=mapa[oldPlayerPosition[0]][oldPlayerPosition[1]];
        }
        mapaDescubrir[newPlayerPosition[0]][newPlayerPosition[1]]="P";
        for(String[] casilla: mapaDescubrir){
            for(int i=0;i<casilla.length;i++){
                System.out.print(casilla[i]+" ");
            }
            System.out.println(" ");
        }
    }
}
