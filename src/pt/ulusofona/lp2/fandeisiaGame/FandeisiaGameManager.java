
package pt.ulusofona.lp2.fandeisiaGame;

import java.util.List;
import java.util.ArrayList;

public class FandeisiaGameManager {

    private int[][] mapStartGame;
    private int countTurnos;
    private int turn15GameOver;
    private int tesourosTotais;

    private int pontosLDR;
    private int pontosRST;

    private Team tLDR;
    private Team tRST;

    private List<Creature> listaCreatures = new ArrayList<>();
    private List<Tesouro> listaTreasures = new ArrayList<>();

    //--------------------Metodos Obrigratorios---------------------

    public String[][] getCreatureTypes() {//Done mas...-------------

        String[][] creatureTypeOptions = new String[4][4];

        creatureTypeOptions[0] = new String[]{"Skeleton", "skeleton.png", "Descrição textual", "5"};
        creatureTypeOptions[1] = new String[]{"Dwarf", "crazy_emoji_white.png", "Dá cabeçadas", "10"};
        creatureTypeOptions[2] = new String[]{"Chimera", "chimera.png", "Morde cenas", "15"};
        creatureTypeOptions[3] = new String[]{"Super Dragão", "super_dragonn.png", "Comandado pelo Macaco Lider", "11"};

        return creatureTypeOptions;
    }

    public void startGame(String[] content, int rows, int columns) {

        //TODO NAO SEI O PROPOSITO ESPECIFICO DOS PARAMETROS ROWS E COLLUMNS
        System.out.println(1 / 2);
        System.out.println(2 / 2);
        System.out.println(3 / 2);
        tLDR = new Team(0, 0, false);
        tRST = new Team(1, 0, false);
        countTurnos = 0;
        turn15GameOver = 0;
        tesourosTotais = 0;
        pontosLDR = 0;
        pontosRST = 0;
        mapStartGame = new int[rows][columns];//vamos usar isto nas outras funcoes

        for (int i = 0; i < mapStartGame.length; i++) {
            for (int j = 0; j < mapStartGame[i].length; j++) {
                mapStartGame[i][j] = 0;
                System.out.print(mapStartGame[i][j] + " ");
            }
            System.out.println();
        }

        int idTemp;
        String typeTemp;
        int xTemp = 0;
        int yTemp = 0;

        int teamIdTemp;
        String orientTemp;

        //Dissecação do parametro "content" para objetos creatures e treasures----

        for (String elemento : content) {
            if (elemento.contains("treasure")) {
                Tesouro tesouroTemp = new Tesouro();

                String[] dados = elemento.split(",");

                for (String d : dados) {
                    if (d.startsWith("id:")) {
                        idTemp = Integer.parseInt(d.substring(4));
                        tesouroTemp.setId(idTemp);

                    } else if (d.startsWith(" type:")) {
                        typeTemp = d.substring(7);
                        tesouroTemp.setTipo(typeTemp);

                    } else if (d.startsWith(" x:")) {
                        xTemp = Integer.parseInt(d.substring(4));
                        tesouroTemp.setPosX(xTemp);

                    } else if (d.startsWith(" y:")) {
                        yTemp = Integer.parseInt(d.substring(4));
                        tesouroTemp.setPosY(yTemp);
                    }
                }
                System.out.println(tesouroTemp.toString());
                listaTreasures.add(tesouroTemp);
                mapStartGame[yTemp][xTemp] = 2;

            } else {
                Creature creatureTemp = new Creature();

                String[] dados = elemento.split(",");

                for (String d : dados) {
                    if (d.startsWith("id:")) {
                        idTemp = Integer.parseInt(d.substring(4));
                        creatureTemp.setId(idTemp);

                    } else if (d.startsWith(" type:")) {
                        typeTemp = d.substring(7);
                        creatureTemp.setTipo(typeTemp);

                    } else if (d.startsWith(" teamId:")) {
                        teamIdTemp = Integer.parseInt(d.substring(9));
                        creatureTemp.setIdEquipa(teamIdTemp);

                    } else if (d.startsWith(" x:")) {
                        xTemp = Integer.parseInt(d.substring(4));
                        creatureTemp.setPosX(xTemp);

                    } else if (d.startsWith(" y:")) {
                        yTemp = Integer.parseInt(d.substring(4));
                        creatureTemp.setPosY(yTemp);

                    } else if (d.startsWith(" orientation:")) {
                        orientTemp = d.substring(14);
                        creatureTemp.setOrientacao(Creature.Orientacao.valueOf(orientTemp));

                    }
                }
                System.out.println(creatureTemp.toString());
                listaCreatures.add(creatureTemp);
                mapStartGame[yTemp][xTemp] = 1;
            }
        }
        tesourosTotais = listaTreasures.size();

        for (int i = 0; i < mapStartGame.length; i++) {
            for (int j = 0; j < mapStartGame[i].length; j++) {
                System.out.print(mapStartGame[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setInitialTeam(int teamId) {//Done----------------

        teamId = 0; //É sempre a equipa LDR, como indicado no enunciado
        tLDR.setEstado(true);
    }

    public void processTurn() {

        boolean encontrou = false;

        for (int i = 0; i < listaCreatures.size(); i++) {
         //   System.out.println("Id a mover " + listaCreatures.get(i).getIdEquipa());

            if (getCurrentTeamId() == listaCreatures.get(i).getIdEquipa()) {
                encontrou = listaCreatures.get(i).moveCriatura(mapStartGame);
            }

            if (encontrou) {
                turn15GameOver = 0;
                for (int j = 0; j < listaTreasures.size(); j++) {
                    if (listaTreasures.get(j).getPosY() == listaCreatures.get(i).getPosY() && listaTreasures.get(j).getPosX() == listaCreatures.get(i).getPosX()) {
                        listaTreasures.remove(listaTreasures.get(j));
                    }
                }

                if (listaCreatures.get(i).getIdEquipa() == 0) {
                    pontosLDR++;
                } else {
                    pontosRST++;
                }
            }
        }

        turn15GameOver++;
        countTurnos++;


        if (tLDR.getEstado()) {
            tLDR.setEstado(false);
            tRST.setEstado(true);
        } else {
            tLDR.setEstado(true);
            tRST.setEstado(false);
        }

        for (int j = 0; j < listaTreasures.size(); j++) {
            System.out.println(listaTreasures.get(j).toString());
        }

        for (int j = 0; j < listaCreatures.size(); j++) {
            System.out.println(listaCreatures.get(j).toString());
        }

        for (int i = 0; i < mapStartGame.length; i++) {
            for (int j = 0; j < mapStartGame[i].length; j++) {
                System.out.print(mapStartGame[i][j] + " ");
            }
            System.out.println();
        }
    }

    public List<Creature> getCreatures() {//Quase Done--------------------------
        return listaCreatures;
    }


    public boolean gameIsOver() {
        // Verificação de gameIsOver

        int conta = tesourosTotais / 2 + 1;

        if (pontosLDR >= conta) {
            System.out.println();
            System.out.println("pontos do vencedor:" + pontosLDR);
            System.out.println("necessarios:" + conta);
            System.out.println("totais:" + tesourosTotais);
            System.out.println(1);
            return true;
        }

        if (pontosRST >= conta) {
            System.out.println();
            System.out.println("pontos do vencedor:" + pontosRST);
            System.out.println("necessarios:" + conta);
            System.out.println("totais:" + tesourosTotais);
            System.out.println(2);
            return true;
        }

        if (listaTreasures.size() == 0) {
            System.out.println();
            System.out.println(3);
            return true;
        }

        if (turn15GameOver == 15) {
            System.out.println();
            System.out.println(4);
            return true;
        }

        return false;
    }

    public List<String> getAuthors() {//Done----------------

        List<String> listaAuthors = new ArrayList<>();

        listaAuthors.add("André Carpinteiro");
        listaAuthors.add("David Silva");

        return listaAuthors;
    }

    public List<String> getResults() {//Done---------------

        List<String> resultado = new ArrayList<>();

        resultado.add("Welcome to FANDEISIA");

        if (pontosLDR == pontosRST) {
            resultado.add("Resultado: EMPATE");
            resultado.add("LDR: " + pontosLDR);
            resultado.add("RESISTENCIA: " + pontosRST);
            resultado.add("Nr. de Turnos jogados: " + countTurnos);
            resultado.add("-----");
            for (int i = 0; i < listaCreatures.size(); i++) {
                resultado.add(listaCreatures.get(i).getId() + " : " + listaCreatures.get(i).getTipo() + " : " + listaCreatures.get(i).getPontos());
            }
        }

        if (pontosLDR > pontosRST) {
            resultado.add("Resultado: Vitória da equipa LDR");
            resultado.add("LDR: " + pontosLDR);
            resultado.add("RESISTENCIA: " + pontosRST);
            resultado.add("Nr. de Turnos jogados: " + countTurnos);
            resultado.add("-----");
            for (int i = 0; i < listaCreatures.size(); i++) {
                resultado.add(listaCreatures.get(i).getId() + " : " + listaCreatures.get(i).getTipo() + " : " + listaCreatures.get(i).getPontos());
            }
        }

        if (pontosLDR < pontosRST) {
            resultado.add("Resultado: Vitória da equipa RESISTENCIA");
            resultado.add("RESISTENCIA: " + pontosRST);
            resultado.add("LDR: " + pontosLDR);
            resultado.add("Nr. de Turnos jogados: " + countTurnos);
            resultado.add("-----");
            for (int i = 0; i < listaCreatures.size(); i++) {
                resultado.add(listaCreatures.get(i).getId() + " : " + listaCreatures.get(i).getTipo() + " : " + listaCreatures.get(i).getPontos());
            }
        }
        return resultado;
    }

    public int getElementId(int x, int y) {//Done-------------------

        for (Creature creatureTemp : listaCreatures) {
            if (creatureTemp.getPosX() == x && creatureTemp.getPosY() == y) {
                return creatureTemp.getId();
            }
        }

        for (Tesouro tesouroTemp : listaTreasures) {
            if (tesouroTemp.getPosX() == x && tesouroTemp.getPosY() == y) {
                return tesouroTemp.getId();
            }
        }
        return 0;
    }

    public int getCurrentTeamId() {
        /*Deve devolver o ​ID​ da ​equipa​ que está activa​ no turno actual.  */
        if (tLDR.getEstado()) {
            return 0;
        } else if (tRST.getEstado()) {
            return 1;
        }
        return 2;
    }

    public int getCurrentScore(int teamID) {
        /*Deve devolver o número actual de pontos da equipa que tem o ID teamID. */
        if (tLDR.getIdTeam() == teamID) {
            return tLDR.getTeamPontos();

        } else {
            return tRST.getTeamPontos();
        }
    }
}
