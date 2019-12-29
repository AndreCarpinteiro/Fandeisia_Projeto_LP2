package pt.ulusofona.lp2.fandeisiaGame;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestFandeisiaGameManager {

    @Test
    public void test01FronteirasAnao() {//Done-------------
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[4];
        conteudoMundo[0] = "id: 1, type: Anão, teamId: 0, x: 0, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Anão, teamId: 1, x: 3, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Anão, teamId: 0, x: 3, y: 0, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 1, x: 0, y: 3, orientation: Oeste";
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
        conteudoMundo[0] = "id: 1, type: Humano, teamId: 0, x: 0, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Humano, teamId: 1, x: 3, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Humano, teamId: 0, x: 3, y: 0, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Humano, teamId: 1, x: 0, y: 3, orientation: Oeste";
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
        conteudoMundo[0] = "id: 1, type: Gigante, teamId: 0, x: 0, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Gigante, teamId: 1, x: 3, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Gigante, teamId: 0, x: 3, y: 0, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Gigante, teamId: 1, x: 0, y: 3, orientation: Oeste";
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
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 0, x: 1, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 0, x: 2, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Dragão, teamId: 0, x: 3, y: 1, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Dragão, teamId: 0, x: 0, y: 2, orientation: Oeste";
        conteudoMundo[4] = "id: 5, type: Dragão, teamId: 1, x: 0, y: 0, orientation: Noroeste";
        conteudoMundo[5] = "id: 6, type: Dragão, teamId: 1, x: 3, y: 0, orientation: Nordeste";
        conteudoMundo[6] = "id: 7, type: Dragão, teamId: 1, x: 3, y: 3, orientation: Sudeste";
        conteudoMundo[7] = "id: 8, type: Dragão, teamId: 1, x: 0, y: 3, orientation: Sudoeste";

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
        conteudoMundo[0] = "id: 1, type: Elfo, teamId: 0, x: 1, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: Elfo, teamId: 0, x: 2, y: 3, orientation: Sul";
        conteudoMundo[2] = "id: 3, type: Elfo, teamId: 0, x: 3, y: 1, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Elfo, teamId: 0, x: 0, y: 2, orientation: Oeste";
        conteudoMundo[4] = "id: 5, type: Elfo, teamId: 1, x: 0, y: 0, orientation: Noroeste";
        conteudoMundo[5] = "id: 6, type: Elfo, teamId: 1, x: 3, y: 0, orientation: Nordeste";
        conteudoMundo[6] = "id: 7, type: Elfo, teamId: 1, x: 3, y: 3, orientation: Sudeste";
        conteudoMundo[7] = "id: 8, type: Elfo, teamId: 1, x: 0, y: 3, orientation: Sudoeste";

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
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[14];
        conteudoMundo[0] = "id: 1, type: Dragão, teamId: 0, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Dragão, teamId: 0, x: 0, y: 1, orientation: Este";
        conteudoMundo[2] = "id: 3, type: Dragão, teamId: 0, x: 0, y: 2, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Dragão, teamId: 0, x: 0, y: 3, orientation: Este";
        //criaturas paras os saltos
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 0, x: 1, y: 2, orientation: Norte";
        conteudoMundo[5] = "id: 6, type: Anão, teamId: 0, x: 2, y: 2, orientation: Norte";
        conteudoMundo[6] = "id: 7, type: Anão, teamId: 0, x: 1, y: 3, orientation: Norte";
        conteudoMundo[7] = "id: 8, type: Anão, teamId: 0, x: 2, y: 3, orientation: Norte";
        conteudoMundo[8] = "id: 9, type: Anão, teamId: 0, x: 3, y: 3, orientation: Norte";
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
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[7];
        conteudoMundo[0] = "id: 1, type: Elfo, teamId: 0, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Elfo, teamId: 0, x: 0, y: 1, orientation: Este";
        conteudoMundo[2] = "id: 3, type: Elfo, teamId: 0, x: 0, y: 2, orientation: Este";
        //criaturas paras os saltos
        conteudoMundo[3] = "id: 4, type: Anão, teamId: 0, x: 1, y: 2, orientation: Norte";

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
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[14];
        conteudoMundo[0] = "id: 1, type: Gigante, teamId: 0, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Gigante, teamId: 0, x: 0, y: 1, orientation: Este";
        conteudoMundo[2] = "id: 3, type: Gigante, teamId: 0, x: 0, y: 2, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Gigante, teamId: 0, x: 0, y: 3, orientation: Este";
        //criaturas paras os saltos
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 0, x: 1, y: 2, orientation: Norte";
        conteudoMundo[5] = "id: 6, type: Anão, teamId: 0, x: 2, y: 2, orientation: Norte";
        conteudoMundo[6] = "id: 7, type: Anão, teamId: 0, x: 1, y: 3, orientation: Norte";
        conteudoMundo[7] = "id: 8, type: Anão, teamId: 0, x: 2, y: 3, orientation: Norte";
        conteudoMundo[8] = "id: 9, type: Anão, teamId: 0, x: 3, y: 3, orientation: Sul";

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
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[10];
        conteudoMundo[0] = "id: 1, type: Humano, teamId: 0, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Humano, teamId: 0, x: 0, y: 1, orientation: Este";
        conteudoMundo[2] = "id: 3, type: Humano, teamId: 0, x: 0, y: 2, orientation: Este";
        conteudoMundo[3] = "id: 4, type: Humano, teamId: 0, x: 0, y: 3, orientation: Este";

        //criaturas paras os saltos
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 0, x: 1, y: 2, orientation: Sul";
        conteudoMundo[5] = "id: 6, type: Anão, teamId: 0, x: 1, y: 3, orientation: Sul";
        conteudoMundo[6] = "id: 7, type: Anão, teamId: 0, x: 2, y: 3, orientation: Sul";

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
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[10];
        conteudoMundo[0] = "id: 1, type: Anão, teamId: 0, x: 0, y: 0, orientation: Este";
        conteudoMundo[1] = "id: 2, type: Anão, teamId: 0, x: 0, y: 1, orientation: Este";


        //criaturas paras os saltos
        conteudoMundo[4] = "id: 5, type: Anão, teamId: 0, x: 1, y: 1, orientation: Sul";


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
}
