import Player.Player;

import java.util.Random;

public class Tablero {
    static String [][] mapa = new String[3][3];
    static void crearTablero(){
        double random,probSuelo;
        Random rand = new Random();
        probSuelo= 0.75;//Más Probabilidad de que haya suelo de que haya zombies.
        for(int i=0;i<mapa.length;i++){
            for (int f=0;f< mapa[i].length; f++) {
                random = rand.nextDouble();
                //203619=zombie 91004=suelo
                mapa[i][f] = random>probSuelo?"203619":"91004";
                if(i==1&&f==1){
                    mapa[i][f]="914434";//jugador
                    System.out.print("P"+" ");
                }else{
                    System.out.print("#"+" ");
                }

            }
            System.out.println(" ");
        }
        System.out.println("Tablero inicial creado.");
    }
    static void mover(){
        int [] PlayerPosition;
        PlayerPosition=Player.posicion;
        int [] casilla = Player.move();
        //Posicion del juegador mapa[PlayerPosition[0]][PlayerPosition[1]]
        mapa[PlayerPosition[0]][PlayerPosition[1]]=mapa[casilla[0]][casilla[1]];
        mapa[casilla[0]][casilla[1]]=mapa[PlayerPosition[0]][PlayerPosition[1]];
    }
    static void mostrarTablero(){
        String [][]mapaDescubrir={{"#","#","#"},{"#","#","#"},{"#","#","#"}};
    }
}
