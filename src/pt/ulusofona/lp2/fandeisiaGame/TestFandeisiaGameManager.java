package pt.ulusofona.lp2.fandeisiaGame;

import org.junit.Test;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class TestFandeisiaGameManager {

    @Test
    public void test01Turno() {//Done-------------
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[3];
        conteudoMundo[0] = "id: 1, type: creature, teamId: 0, x: 0, y: 0, orientation: Norte";
        conteudoMundo[1] = "id: 2, type: creature, teamId: 1, x: 1, y: 1, orientation: Sul";
        conteudoMundo[2] = "id: -1, type: treasure, x: 2, y: 2";
        gameManager.startGame(conteudoMundo, 3, 3);

        gameManager.processTurn();

        //Verifica possições
        assertEquals(1, gameManager.getElementId(0, 0));
        assertEquals(2, gameManager.getElementId(1, 2));
        assertEquals(-1, gameManager.getElementId(2, 2));
        assertEquals(0, gameManager.getElementId(1, 1));
        assertEquals(0, gameManager.getElementId(2, 1));
        assertEquals(0, gameManager.getElementId(1, 0));
        assertEquals(0, gameManager.getElementId(0, 2));
        assertEquals(0, gameManager.getElementId(0, 1));
        assertEquals(0, gameManager.getElementId(2, 0));
    }

    @Test
    public void test02Turno() {//Done------------------------
        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[3];
        conteudoMundo[0] = "id: 1, type: creature, teamId: 0, x: 0, y: 1, orientation: Este";
        conteudoMundo[1] = "id: 2, type: creature, teamId: 1, x: 2, y: 1, orientation: Oeste";
        conteudoMundo[2] = "id: -1, type: treasure, x: 2, y: 2";
        gameManager.startGame(conteudoMundo, 3, 3);

        gameManager.processTurn();

        List<Creature> listaCreatures = gameManager.getCreatures();

        //Vai verificar se a segunda criatura mudou orientção para NORTE
        assertEquals(Creature.Orientacao.Norte, listaCreatures.get(1).getOrientacao());
    }

    @Test
    public void test03Turno() {//Done---------------

        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[3];
        conteudoMundo[0] = "id: 1, type: creature, teamId: 0, x: 0, y: 1, orientation: Oeste";
        conteudoMundo[1] = "id: 2, type: creature, teamId: 1, x: 1, y: 1, orientation: Sul";
        conteudoMundo[2] = "id: -1, type: treasure, x: 2, y: 2";
        conteudoMundo[2] = "id: -1, type: treasure, x: 2, y: 1";
        gameManager.startGame(conteudoMundo, 3, 3);

        gameManager.processTurn();

        //Verifica se a equipa mudou corretamente
        assertEquals(1, gameManager.getCurrentTeamId());  // continua no mesmo sítio
    }

    @Test
    public void test04Turno() {//Done---------------------

        FandeisiaGameManager gameManager = new FandeisiaGameManager();
        String[] conteudoMundo = new String[3];
        conteudoMundo[0] = "id: 1, type: creature, teamId: 0, x: 2, y: 1, orientation: Este";
        conteudoMundo[1] = "id: 2, type: creature, teamId: 1, x: 1, y: 2, orientation: Este";
        conteudoMundo[2] = "id: -1, type: treasure, x: 2, y: 2";
        gameManager.startGame(conteudoMundo, 3, 3);

        gameManager.processTurn();

        //Verificar se tesouro foi apanhado
        assertEquals(2, gameManager.getElementId(2,2));
    }
}
