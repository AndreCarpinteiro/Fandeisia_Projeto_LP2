package pt.ulusofona.lp2.fandeisiaGame;

import org.junit.Test;

public class TestCreature {

    @Test
    public void test01moveCriatura(){

        int[][] mapStartGame;
        mapStartGame = new int[4][4];

        for (int i = 0; i < mapStartGame.length; i++) {
            for (int j = 0; j < mapStartGame[i].length; j++) {
                mapStartGame[i][j] = 0;
                System.out.print(mapStartGame[i][j] + " ");
            }
            System.out.println();
        }

        Creature ola = new Creature(1, 1,"Super Dragão",0,3,5, Creature.Orientacao.Norte);
        ola.moveCriatura();
        System.out.println(ola.toString());
       // String expect = "1 | Super Dragão | 1 | 0 @ (posX, \" + posY + \") \" + orientacao;";
    }
}
