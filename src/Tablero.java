import java.util.Arrays;
import java.util.Random;

public class Tablero {
    public static String [][] mapa = new String[3][3];
    static void crearTablero(){
        double random,probSuelo;
        Random rand = new Random();
        probSuelo= 0.75;//Más Probabilidad de que haya suelo de que haya zombies.
        for(int i=0;i<mapa.length;i++){
            for (int f=0;f< mapa[i].length; f++) {
                random = rand.nextDouble();
                //203619=zombie 91004=suelo
                if(i==1&&f==1) {
                    mapa[i][f] = "914434";//jugador
                }else{
                    mapa[i][f] = random>probSuelo?"203619":"91004";
                }
            }
        }
        mostrarTablero();
        System.out.println("Tablero inicial creado.");
    }
    static void mover(){
        int [] playerPosition;int[] newPlayerPosition;
        playerPosition = Arrays.copyOf(Player.position,Player.position.length);//Coge la ubicación actual
        newPlayerPosition = Player.move();//Coge la nueva posicion del jugador
        //Posicion del juegador mapa[PlayerPosition[0]][PlayerPosition[1]]
        String infoCasilla=mapa[newPlayerPosition[0]][newPlayerPosition[1]];
        mapa[playerPosition[0]][playerPosition[1]]=infoCasilla;
        mapa[newPlayerPosition[0]][newPlayerPosition[1]]=mapa[playerPosition[0]][playerPosition[1]];
        mostrarTablero(newPlayerPosition,playerPosition,infoCasilla);//muestra el tablero actualizado.
    }
    static void mostrarTablero(){
        String [][]mapaDescubrir={{"#","#","#"},{"#","P","#"},{"#","#","#"}};
        for(String[] casilla: mapaDescubrir){
            for(int i=0;i<casilla.length;i++){
                System.out.print(casilla[i]+" ");
            }
            System.out.println(" ");
        }
    }
    static void mostrarTablero(int [] posicionJugador, int[] posicionAnterior, String casillaAnterior){
        String [][]mapaDescubrir={{"#","#","#"},{"#","P","#"},{"#","#","#"}};
        mapaDescubrir[posicionAnterior[0]][posicionAnterior[1]]=casillaAnterior;
        mapaDescubrir[posicionJugador[0]][posicionJugador[1]]="P";
        for(String[] casilla: mapaDescubrir){
            for(int i=0;i<casilla.length;i++){
                System.out.print(casilla[i]+" ");
            }
            System.out.println(" ");
        }
    }
}
