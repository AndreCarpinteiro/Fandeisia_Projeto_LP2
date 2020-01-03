package pt.ulusofona.lp2.fandeisiaGame;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.mapStartGame;

public class TestFandeisiaGameManager {

    @Test
    public void test01FronteirasAnao() {//Done-------------
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[4];
        conteudoMundo[0] = "id: 1, type: Anão, teamId: 10, x: 0, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Anão, teamId: 20, x: 3, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Anão, teamId: 10, x: 3, y: 0, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 20, x: 0, y: 3, orientation: Oeste";
        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Este, gameManager.getCreatures().get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Oeste, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(2).getOrientacao());
        assertEquals(Creature.Orientacao.Norte, listaCreatures.get(3).getOrientacao());

        //Verifica possições
        assertEquals(1, gameManager.getElementId(0, 0));
        assertEquals(2, gameManager.getElementId(3, 3));
        assertEquals(3, gameManager.getElementId(3, 0));
        assertEquals(4, gameManager.getElementId(0, 3));
        assertEquals(0, gameManager.getElementId(1, 0));
        assertEquals(0, gameManager.getElementId(2, 0));
        assertEquals(0, gameManager.getElementId(0, 1));
        assertEquals(0, gameManager.getElementId(1, 1));
        assertEquals(0, gameManager.getElementId(2, 1));
        assertEquals(0, gameManager.getElementId(3, 1));
        assertEquals(0, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(1, 2));
        assertEquals(0, gameManager.getElementId(2, 2));
        assertEquals(0, gameManager.getElementId(3, 2));
        assertEquals(0, gameManager.getElementId(1, 3));
        assertEquals(0, gameManager.getElementId(2, 3));
    }

    @Test
    public void test02FronteirasHumano() {//Done------------------------
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[4];
        conteudoMundo[0] = "id: 1, type: Humano, teamId: 10, x: 0, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Humano, teamId: 20, x: 3, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Humano, teamId: 10, x: 3, y: 0, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Humano, teamId: 20, x: 0, y: 3, orientation: Oeste";
        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Este, gameManager.getCreatures().get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Oeste, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(2).getOrientacao());
        assertEquals(Creature.Orientacao.Norte, listaCreatures.get(3).getOrientacao());

        //Verifica possições
        assertEquals(1, gameManager.getElementId(0, 0));
        assertEquals(2, gameManager.getElementId(3, 3));
        assertEquals(3, gameManager.getElementId(3, 0));
        assertEquals(4, gameManager.getElementId(0, 3));
        assertEquals(0, gameManager.getElementId(1, 0));
        assertEquals(0, gameManager.getElementId(2, 0));
        assertEquals(0, gameManager.getElementId(0, 1));
        assertEquals(0, gameManager.getElementId(1, 1));
        assertEquals(0, gameManager.getElementId(2, 1));
        assertEquals(0, gameManager.getElementId(3, 1));
        assertEquals(0, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(1, 2));
        assertEquals(0, gameManager.getElementId(2, 2));
        assertEquals(0, gameManager.getElementId(3, 2));
        assertEquals(0, gameManager.getElementId(1, 3));
        assertEquals(0, gameManager.getElementId(2, 3));
    }

    @Test
    public void test03FronteirasGigante() {//Done---------------
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[4];
        conteudoMundo[0] = "id: 1, type: Gigante, teamId: 10, x: 0, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Gigante, teamId: 20, x: 3, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Gigante, teamId: 10, x: 3, y: 0, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Gigante, teamId: 20, x: 0, y: 3, orientation: Oeste";
        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Este, gameManager.getCreatures().get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Oeste, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(2).getOrientacao());
        assertEquals(Creature.Orientacao.Norte, listaCreatures.get(3).getOrientacao());

        //Verifica possições
        assertEquals(1, gameManager.getElementId(0, 0));
        assertEquals(2, gameManager.getElementId(3, 3));
        assertEquals(3, gameManager.getElementId(3, 0));
        assertEquals(4, gameManager.getElementId(0, 3));
        assertEquals(0, gameManager.getElementId(1, 0));
        assertEquals(0, gameManager.getElementId(2, 0));
        assertEquals(0, gameManager.getElementId(0, 1));
        assertEquals(0, gameManager.getElementId(1, 1));
        assertEquals(0, gameManager.getElementId(2, 1));
        assertEquals(0, gameManager.getElementId(3, 1));
        assertEquals(0, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(1, 2));
        assertEquals(0, gameManager.getElementId(2, 2));
        assertEquals(0, gameManager.getElementId(3, 2));
        assertEquals(0, gameManager.getElementId(1, 3));
        assertEquals(0, gameManager.getElementId(2, 3));
    }

    @Test
    public void test04FronteirasDragao() {//Done---------------------
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[8];
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 10, x: 1, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 10, x: 2, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Dragão, teamId: 10, x: 3, y: 1, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Dragão, teamId: 10, x: 0, y: 2, orientation: Oeste";
        conteudoMundo[4] = "id: 5, type: Dragão, teamId: 20, x: 0, y: 0, orientation: Noroeste";
        conteudoMundo[5] = "id: 6, type: Dragão, teamId: 20, x: 3, y: 0, orientation: Nordeste";
        conteudoMundo[6] = "id: 7, type: Dragão, teamId: 20, x: 3, y: 3, orientation: Sudeste";
        conteudoMundo[7] = "id: 8, type: Dragão, teamId: 20, x: 0, y: 3, orientation: Sudoeste";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Nordeste, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sudoeste, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Sudeste, listaCreatures.get(2).getOrientacao());
        assertEquals(Creature.Orientacao.Noroeste, listaCreatures.get(3).getOrientacao());
        assertEquals(Creature.Orientacao.Norte, listaCreatures.get(4).getOrientacao());
        assertEquals(Creature.Orientacao.Este, listaCreatures.get(5).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(6).getOrientacao());
        assertEquals(Creature.Orientacao.Oeste, listaCreatures.get(7).getOrientacao());

        //Verifica possições
        assertEquals(5, gameManager.getElementId(0, 0));
        assertEquals(1, gameManager.getElementId(1, 0));
        assertEquals(0, gameManager.getElementId(2, 0));
        assertEquals(6, gameManager.getElementId(3, 0));
        assertEquals(0, gameManager.getElementId(0, 1));
        assertEquals(0, gameManager.getElementId(1, 1));
        assertEquals(0, gameManager.getElementId(2, 1));
        assertEquals(3, gameManager.getElementId(3, 1));
        assertEquals(4, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(1, 2));
        assertEquals(0, gameManager.getElementId(2, 2));
        assertEquals(0, gameManager.getElementId(3, 2));
        assertEquals(8, gameManager.getElementId(0, 3));
        assertEquals(0, gameManager.getElementId(1, 3));
        assertEquals(2, gameManager.getElementId(2, 3));
        assertEquals(7, gameManager.getElementId(3, 3));
    }

    @Test
    public void test05FronteirasElfo() {//Done---------------------
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[8];
        conteudoMundo[0] = "id: 1, type: Elfo, teamId: 10, x: 1, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Elfo, teamId: 10, x: 2, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Elfo, teamId: 10, x: 3, y: 1, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Elfo, teamId: 10, x: 0, y: 2, orientation: Oeste";
        conteudoMundo[4] = "id: 5, type: Elfo, teamId: 20, x: 0, y: 0, orientation: Noroeste";
        conteudoMundo[5] = "id: 6, type: Elfo, teamId: 20, x: 3, y: 0, orientation: Nordeste";
        conteudoMundo[6] = "id: 7, type: Elfo, teamId: 20, x: 3, y: 3, orientation: Sudeste";
        conteudoMundo[7] = "id: 8, type: Elfo, teamId: 20, x: 0, y: 3, orientation: Sudoeste";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Nordeste, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sudoeste, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Sudeste, listaCreatures.get(2).getOrientacao());
        assertEquals(Creature.Orientacao.Noroeste, listaCreatures.get(3).getOrientacao());
        assertEquals(Creature.Orientacao.Norte, listaCreatures.get(4).getOrientacao());
        assertEquals(Creature.Orientacao.Este, listaCreatures.get(5).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(6).getOrientacao());
        assertEquals(Creature.Orientacao.Oeste, listaCreatures.get(7).getOrientacao());

        //Verifica possições
        assertEquals(5, gameManager.getElementId(0, 0));
        assertEquals(1, gameManager.getElementId(1, 0));
        assertEquals(0, gameManager.getElementId(2, 0));
        assertEquals(6, gameManager.getElementId(3, 0));
        assertEquals(0, gameManager.getElementId(0, 1));
        assertEquals(0, gameManager.getElementId(1, 1));
        assertEquals(0, gameManager.getElementId(2, 1));
        assertEquals(3, gameManager.getElementId(3, 1));
        assertEquals(4, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(1, 2));
        assertEquals(0, gameManager.getElementId(2, 2));
        assertEquals(0, gameManager.getElementId(3, 2));
        assertEquals(8, gameManager.getElementId(0, 3));
        assertEquals(0, gameManager.getElementId(1, 3));
        assertEquals(2, gameManager.getElementId(2, 3));
        assertEquals(7, gameManager.getElementId(3, 3));
    }

    @Test
    public void test06SaltosDragao() {//Done---------------------
        /*dragao:
            -2 buracos ✔
            -3 buracos X
            -2 criaturas ✔
            -3 criaturas X*/
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[14];
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 20, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 20, x: 0, y: 1, orientation: Este";
        conteudoMundo[2] = "id: 3, type: Dragão, teamId: 20, x: 0, y: 2, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Dragão, teamId: 20, x: 0, y: 3, orientation: Este";
        //criaturas paras os saltos
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 10, x: 1, y: 2, orientation: Norte";
        conteudoMundo[5] = "id: 6, type: Anão, teamId: 10, x: 2, y: 2, orientation: Norte";
        conteudoMundo[6] = "id: 7, type: Anão, teamId: 20, x: 1, y: 3, orientation: Norte";
        conteudoMundo[7] = "id: 8, type: Anão, teamId: 10, x: 2, y: 3, orientation: Norte";
        conteudoMundo[8] = "id: 9, type: Anão, teamId: 20, x: 3, y: 3, orientation: Norte";
        //buracos
        conteudoMundo[9] = "id: -500, type: hole, x: 1, y: 0";
        conteudoMundo[10] = "id: -501, type: hole, x: 2, y: 0";
        conteudoMundo[11] = "id: -502, type: hole, x: 1, y: 1";
        conteudoMundo[12] = "id: -503, type: hole, x: 2, y: 1";
        conteudoMundo[13] = "id: -504, type: hole, x: 3, y: 1";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Este, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sudeste, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Este, listaCreatures.get(2).getOrientacao());
        assertEquals(Creature.Orientacao.Sudeste, listaCreatures.get(3).getOrientacao());

        //Verifica possições
        assertEquals(0, gameManager.getElementId(0, 0));
        assertEquals(1, gameManager.getElementId(3, 0));
        assertEquals(2, gameManager.getElementId(0, 1));
        assertEquals(-504, gameManager.getElementId(3, 1));
        assertEquals(0, gameManager.getElementId(0, 2));
        assertEquals(3, gameManager.getElementId(3, 2));
        assertEquals(4, gameManager.getElementId(0, 3));
        assertEquals(9, gameManager.getElementId(3, 3));
    }

    @Test
    public void test07SaltosElfo() {//Done---------------------
        /*elfo:
            -1 buraco ✔
            -2 buracos X
            -1 criatura X
            */

        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[7];
        conteudoMundo[0] = "id: 1, type: Elfo, teamId: 10, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Elfo, teamId: 10, x: 0, y: 1, orientation: Este";
        conteudoMundo[2] = "id: 3, type: Elfo, teamId: 10, x: 0, y: 2, orientation: Este";
        //criaturas paras os saltos
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 20, x: 1, y: 2, orientation: Norte";

        //buracos
        conteudoMundo[4] = "id: -500, type: hole, x: 1, y: 0";
        conteudoMundo[5] = "id: -501, type: hole, x: 1, y: 1";
        conteudoMundo[6] = "id: -502, type: hole, x: 2, y: 1";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Este, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sudeste, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Sudeste, listaCreatures.get(2).getOrientacao());

        //Verifica possições
        assertEquals(0, gameManager.getElementId(0, 0));
        assertEquals(1, gameManager.getElementId(2, 0));
        assertEquals(2, gameManager.getElementId(0, 1));
        assertEquals(-502, gameManager.getElementId(2, 1));
        assertEquals(3, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(2, 2));

    }

    @Test
    public void test08SaltosGigante() {//Done---------------------

        /*gigante:
            -2 buracos ✔
            -3 buracos X
            -2 criaturas ✔
            -3 criaturas X
             */
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[14];
        conteudoMundo[0] = "id: 1, type: Gigante, teamId: 20, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Gigante, teamId: 20, x: 0, y: 1, orientation: Este";
        conteudoMundo[2] = "id: 3, type: Gigante, teamId: 20, x: 0, y: 2, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Gigante, teamId: 20, x: 0, y: 3, orientation: Este";
        //criaturas paras os saltos
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 10, x: 1, y: 2, orientation: Norte";
        conteudoMundo[5] = "id: 6, type: Anão, teamId: 10, x: 2, y: 2, orientation: Norte";
        conteudoMundo[6] = "id: 7, type: Anão, teamId: 10, x: 1, y: 3, orientation: Norte";
        conteudoMundo[7] = "id: 8, type: Anão, teamId: 10, x: 2, y: 3, orientation: Norte";
        conteudoMundo[8] = "id: 9, type: Anão, teamId: 10, x: 3, y: 3, orientation: Sul";

        //buracos
        conteudoMundo[9] = "id: -500, type: hole, x: 1, y: 0";
        conteudoMundo[10] = "id: -501, type: hole, x: 2, y: 0";
        conteudoMundo[11] = "id: -502, type: hole, x: 1, y: 1";
        conteudoMundo[12] = "id: -503, type: hole, x: 2, y: 1";
        conteudoMundo[13] = "id: -504, type: hole, x: 3, y: 1";


        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Este, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Este, listaCreatures.get(2).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(3).getOrientacao());

        //Verifica possições
        assertEquals(0, gameManager.getElementId(0, 0));
        assertEquals(1, gameManager.getElementId(3, 0));
        assertEquals(2, gameManager.getElementId(0, 1));
        assertEquals(-504, gameManager.getElementId(3, 1));
        assertEquals(0, gameManager.getElementId(0, 2));
        assertEquals(3, gameManager.getElementId(3, 2));
        assertEquals(4, gameManager.getElementId(0, 3));
        assertEquals(9, gameManager.getElementId(3, 3));

    }

    @Test
    public void test09SaltosHumano() {//Done---------------------
        /*humano:
            -1 buraco X
            -2 buracos X
            -1 criaturas X
            -2 criaturas X
            */

        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[10];
        conteudoMundo[0] = "id: 1, type: Humano, teamId: 20, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Humano, teamId: 20, x: 0, y: 1, orientation: Este";
        conteudoMundo[2] = "id: 3, type: Humano, teamId: 20, x: 0, y: 2, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Humano, teamId: 20, x: 0, y: 3, orientation: Este";

        //criaturas paras os saltos
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 10, x: 1, y: 2, orientation: Sul";
        conteudoMundo[5] = "id: 6, type: Anão, teamId: 10, x: 1, y: 3, orientation: Sul";
        conteudoMundo[6] = "id: 7, type: Anão, teamId: 10, x: 2, y: 3, orientation: Sul";

        //buracos
        conteudoMundo[7] = "id: -500, type: hole, x: 1, y: 0";
        conteudoMundo[8] = "id: -501, type: hole, x: 1, y: 1";
        conteudoMundo[9] = "id: -502, type: hole, x: 2, y: 1";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(2).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(3).getOrientacao());

        //Verifica possições
        assertEquals(1, gameManager.getElementId(0, 0));
        assertEquals(0, gameManager.getElementId(2, 0));
        assertEquals(2, gameManager.getElementId(0, 1));
        assertEquals(-502, gameManager.getElementId(2, 1));
        assertEquals(3, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(2, 2));
        assertEquals(4, gameManager.getElementId(0, 3));
        assertEquals(7, gameManager.getElementId(2, 3));

    }

    @Test
    public void test10SaltosAnao() {//Done---------------------
        /*anao:
            -1 buraco X
            -1 criatura X
            */

        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[4];
        conteudoMundo[0] = "id: 1, type: Anão, teamId: 20, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Anão, teamId: 20, x: 0, y: 1, orientation: Este";

        //criaturas paras os saltos
        conteudoMundo[2] = "id: 3, type: Anão, teamId: 10, x: 1, y: 1, orientation: Norte";

        //buracos
        conteudoMundo[3] = "id: -500, type: hole, x: 1, y: 0";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(1).getOrientacao());

        //Verifica possições
        assertEquals(1, gameManager.getElementId(0, 0));
        assertEquals(-500, gameManager.getElementId(1, 0));
        assertEquals(2, gameManager.getElementId(0, 1));
        assertEquals(3, gameManager.getElementId(1, 1));
    }

    @Test
    public void test11SaltosEspeciaisDragao01() {//Done---------------------
        /*dragao:
            -2 buraco diagonal ✔
            -2 criaturas diagonal ✔
            */
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[6];
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 20, x: 0, y: 0, orientation: Sudeste";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 20, x: 0, y: 3, orientation: Nordeste";
        //criaturas paras os saltos
        conteudoMundo[2] = "id: 3, type: Anão, teamId: 10, x: 1, y: 1, orientation: Sul";
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 10, x: 2, y: 2, orientation: Norte";

        //buracos
        conteudoMundo[4] = "id: -500, type: hole, x: 2, y: 1";
        conteudoMundo[5] = "id: -501, type: hole, x: 1, y: 2";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Sudeste, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Nordeste, listaCreatures.get(1).getOrientacao());


        //Verifica possições
        assertEquals(0, gameManager.getElementId(0, 0));
        assertEquals(2, gameManager.getElementId(3, 0));
        assertEquals(0, gameManager.getElementId(0, 3));
        assertEquals(1, gameManager.getElementId(3, 3));
    }

    @Test
    public void test12SaltosEspeciaisDragao02() {//Done---------------------

        /*dragao:
            -3 buracos diagonal X
            -2 criaturas mas aterrar num buraco X
            */
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[8];
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 10, x: 0, y: 0, orientation: Sudeste";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 10, x: 0, y: 3, orientation: Nordeste";
        //criaturas paras os saltos
        conteudoMundo[2] = "id: 3, type: Anão, teamId: 20, x: 1, y: 1, orientation: Sul";
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 20, x: 2, y: 2, orientation: Norte";

        //buracos
        conteudoMundo[4] = "id: -500, type: hole, x: 2, y: 1";
        conteudoMundo[5] = "id: -501, type: hole, x: 1, y: 2";
        conteudoMundo[6] = "id: -502, type: hole, x: 3, y: 0";
        conteudoMundo[7] = "id: -503, type: hole, x: 3, y: 3";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Este, listaCreatures.get(1).getOrientacao());


        //Verifica possições
        assertEquals(1, gameManager.getElementId(0, 0));
        assertEquals(-502, gameManager.getElementId(3, 0));
        assertEquals(2, gameManager.getElementId(0, 3));
        assertEquals(-503, gameManager.getElementId(3, 3));
    }

    @Test
    public void test13SaltosEspeciaisElfo() {//Done---------------------
        /*elfo:
            -1 buraco diagonal ✔
            -2 buracos diagonal X
            */
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[5];
        conteudoMundo[0] = "id: 1, type: Elfo, teamId: 10, x: 0, y: 0, orientation: Sudeste";
        conteudoMundo[1] = "id: 2, type: Elfo, teamId: 10, x: 1, y: 0, orientation: Sudeste";

        //buracos
        conteudoMundo[2] = "id: -500, type: hole, x: 1, y: 1";
        conteudoMundo[3] = "id: -501, type: hole, x: 2, y: 1";
        conteudoMundo[4] = "id: -502, type: hole, x: 3, y: 2";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Sudeste, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(1).getOrientacao());


        //Verifica possições
        assertEquals(0, gameManager.getElementId(0, 0));
        assertEquals(2, gameManager.getElementId(1, 0));
        assertEquals(1, gameManager.getElementId(2, 2));
        assertEquals(-502, gameManager.getElementId(3, 2));


    }

    @Test
    public void test14SaltosEspeciaisGigante() {//Done---------------------
        /*gigante:
            -2 criaturas mas aterrar num buraco X
            -2 criaturas mas uma delas é um gigante X
            */
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[7];
        conteudoMundo[0] = "id: 1, type: Gigante, teamId: 10, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Gigante, teamId: 10, x: 0, y: 2, orientation: Este";
        //criaturas paras os saltos
        conteudoMundo[2] = "id: 3, type: Gigante, teamId: 20, x: 2, y: 2, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 20, x: 1, y: 0, orientation: Norte";
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 20, x: 2, y: 0, orientation: Norte";
        conteudoMundo[5] = "id: 6, type: Anão, teamId: 10, x: 1, y: 2, orientation: Este";

        //buracos
        conteudoMundo[6] = "id: -500, type: hole, x: 3, y: 0";


        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(1).getOrientacao());


        //Verifica possições
        assertEquals(1, gameManager.getElementId(0, 0));
        assertEquals(-500, gameManager.getElementId(3, 0));
        assertEquals(2, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(3, 2));

    }

    @Test
    public void test15getCoin() {//Done---------------------

        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[7];
        conteudoMundo[0] = "id: 1, type: Anão, teamId: 10, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Anão, teamId: 10, x: 0, y: 2, orientation: Este";
        //criaturas paras os saltos
        conteudoMundo[2] = "id: 3, type: Gigante, teamId: 10, x: 2, y: 2, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 20, x: 1, y: 0, orientation: Norte";
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 20, x: 2, y: 0, orientation: Norte";
        conteudoMundo[5] = "id: 6, type: Anão, teamId: 20, x: 1, y: 2, orientation: Este";

        //buracos
        conteudoMundo[6] = "id: -500, type: hole, x: 3, y: 0";


        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Verifica orientações
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(1).getOrientacao());


        //Verifica possições
        assertEquals(1, gameManager.getElementId(0, 0));
        assertEquals(-500, gameManager.getElementId(3, 0));
        assertEquals(2, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(3, 2));
    }

    @Test
    public void test16SaveGame()
            throws IOException {
        boolean atual;
        File file = new File("fandeisia.txt");
        FandeisiaGameManager gg = new FandeisiaGameManager();
        atual = gg.saveGame(file);
        assertEquals(true, atual);
    }

    @Test
    public void test17LoadGame()
            throws IOException {
        boolean atual;
        File file = new File("fandeisia.txt");
        FandeisiaGameManager gg = new FandeisiaGameManager();
        atual = gg.loadGame(file);
        assertEquals(true, atual);
    }

    @Test
    public void test18getIdTesouro() {
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[7];
        conteudoMundo[0] = "id: 1, type: Elfo, teamId: 10, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Elfo, teamId: 10, x: 0, y: 1, orientation: Este";
        conteudoMundo[2] = "id: 3, type: Elfo, teamId: 20, x: 0, y: 2, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 20, x: 1, y: 2, orientation: Norte";

        //tesouros
        conteudoMundo[4] = "id: -100, type: gold, x: 1, y: 0";
        conteudoMundo[5] = "id: -101, type: bronze, x: 1, y: 1";
        conteudoMundo[6] = "id: -102, type: silver, x: 2, y: 1";

        gameManager.startGame(conteudoMundo, 4, 4);

        //Verifica possições
        assertEquals(-100, gameManager.getElementId(1, 0));
        assertEquals(-101, gameManager.getElementId(1, 1));
        assertEquals(-102, gameManager.getElementId(2, 1));
    }

    @Test
    public void test19GeralTesouro() {
        Tesouro tesouro = new Tesouro(-100, "gold", 1, 0);
        assertEquals("Tesouro{" + "id=" + -100 + ", tipo='" + "gold" + '\'' + ", posX=" + 1 + ", posY=" + 0 + '}', tesouro.toString());
        tesouro.setPosX(0);
        assertEquals(0, tesouro.getPosX());
        tesouro.setPosY(1);
        assertEquals(1, tesouro.getPosY());
        assertEquals("gold", tesouro.getTipo());
        tesouro.setTipo("silver");
        assertEquals("silver", tesouro.getTipo());
        tesouro.setId(1);
        assertEquals(1, tesouro.getId());
    }

    @Test
    public void test20GeralTeam() {
        Team team = new Team(10, 5);
        team.somaPlafond(2);
        assertEquals(52,  team.getPlafond());
        team.setEstado(true);
        assertEquals(true, team.getEstado());
        assertEquals(10, team.getId());
        team.setPontosTeam(10);
        team.somaPontos(2);
        assertEquals(12, team.getTeamPontos());
        team.decrementaPlafond(2);
        assertEquals(50,  team.getPlafond());
        team.setPlafond(30);
        assertEquals(30,  team.getPlafond());
        team.incrementaPontos();
        assertEquals(13,  team.getTeamPontos());
    }

    @Test
    public void test21Eder(){
        FandeisiaGameManager gg = new FandeisiaGameManager();
        assertEquals("Ederzito António Macedo Lopes" ,gg.whoIsLordEder());
    }

    @Test
    public void test22CoinTotal(){
        FandeisiaGameManager gg = new FandeisiaGameManager();
        assertEquals(50 ,gg.getCoinTotal(10));
    }

    @Test
    public void test23FeiticoEmpurraParaNorteAnao(){

        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[8];
        conteudoMundo[0] = "id: 1, type: Anão, teamId: 10, x: 0, y: 3, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Anão, teamId: 10, x: 1, y: 3, orientation: Oeste";
        conteudoMundo[2] = "id: 3, type: Anão, teamId: 10, x: 2, y: 3, orientation: Norte";
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 10, x: 3, y: 3, orientation: Sul";
        //criaturas paras as sobreposição
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 10, x: 0, y: 2, orientation: Norte";
        conteudoMundo[5] = "id: 6, type: Anão, teamId: 10, x: 1, y: 2, orientation: Sul";
        //buracos
        conteudoMundo[6] = "id: -500, type: hole, x: 2, y: 2";
        conteudoMundo[7] = "id: -501, type: hole, x: 3, y: 2";


        gameManager.startGame(conteudoMundo, 4, 4);
        gameManager.enchant(0,3, "EmpurraParaNorte");
        gameManager.enchant(1,3, "EmpurraParaNorte");
        gameManager.enchant(2,3, "EmpurraParaNorte");
        gameManager.enchant(3,3, "EmpurraParaNorte");

        //Verifica
        assertEquals(false, gameManager.enchant(0, 3, "EmpurraParaNorte"));//Erro na movimentação
        assertFalse(gameManager.enchant(1, 3, "EmpurraParaNorte"));
        assertFalse(gameManager.enchant(2, 3, "EmpurraParaNorte"));
        assertFalse(gameManager.enchant(3, 3, "EmpurraParaNorte"));
        gameManager.processTurn();
    }

    @Test
    public void test24FeiticoEmpurraParaNorteHumano(){
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[2];
        conteudoMundo[0] = "id: 1, type: Anão, teamId: 10, x: 1, y: 1, orientation: Sul";
        conteudoMundo[1] = "id: 2, type: Humano, teamId: 20, x: 4, y: 3, orientation: Oeste";
        gameManager.startGame(conteudoMundo, 5, 5);
        gameManager.enchant(4,3, "EmpurraParaNorte");
        gameManager.processTurn();

        //Verifica possições
        assertEquals(2, gameManager.getElementId(2, 2));
    }

    @Test
    public void test25FeiticoEmpurraParaNorteDragao(){
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[2];
        conteudoMundo[0] = "id: 1, type: Anão, teamId: 10, x: 1, y: 1, orientation: Sul";
        conteudoMundo[1] = "id: 2, type: Humano, teamId: 20, x: 4, y: 3, orientation: Oeste";
        gameManager.startGame(conteudoMundo, 5, 5);
        gameManager.enchant(4,3, "EmpurraParaNorte");
        gameManager.processTurn();

        //Verifica possições
        assertEquals(2, gameManager.getElementId(2, 2));
    }

    @Test
    public void test26ApanhaTesouros() {//Done-------------
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[8];
        conteudoMundo[0] = "id: 1, type: Anão, teamId: 10, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Anão, teamId: 10, x: 3, y: 3, orientation: Oeste";
        conteudoMundo[2] = "id: 3, type: Anão, teamId: 10, x: 3, y: 0, orientation: Sul";
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 20, x: 0, y: 3, orientation: Norte";
        conteudoMundo[4] = "id: -1, type: bronze, x: 1, y: 0";
        conteudoMundo[5] = "id: -2, type: silver, x: 2, y: 3";
        conteudoMundo[6] = "id: -3, type: gold, x: 3, y: 1";
        conteudoMundo[7] = "id: -4, type: bronze, x: 0, y: 2";

        gameManager.startGame(conteudoMundo, 4, 4);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        for (int i = 0; i < gameManager.mapStartGame.length; i++) {
            for (int j = 0; j < gameManager.mapStartGame[i].length; j++) {
                System.out.print(gameManager.mapStartGame[i][j]);
            }
            System.out.println();
        }
        //Verifica orientações
        assertEquals(Creature.Orientacao.Este, gameManager.getCreatures().get(0).getOrientacao());
        assertEquals(Creature.Orientacao.Oeste, listaCreatures.get(1).getOrientacao());
        assertEquals(Creature.Orientacao.Sul, listaCreatures.get(2).getOrientacao());
        assertEquals(Creature.Orientacao.Norte, listaCreatures.get(3).getOrientacao());

        //Verifica possições
        assertEquals(1, gameManager.getElementId(1, 0));
        assertEquals(2, gameManager.getElementId(2, 3));
        assertEquals(3, gameManager.getElementId(3, 1));
        assertEquals(4, gameManager.getElementId(0, 2));
        System.out.println(listaCreatures.get(0).getPontos());
        assertEquals(1, listaCreatures.get(0).getBronze());
        assertEquals(1, listaCreatures.get(1).getPrata());
        assertEquals(1, listaCreatures.get(2).getOuro());
        assertEquals(1, listaCreatures.get(3).getBronze());
    }
}
