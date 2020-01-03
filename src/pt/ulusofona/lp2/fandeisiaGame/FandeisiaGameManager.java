
package pt.ulusofona.lp2.fandeisiaGame;

//import com.sun.org.apache.bcel.internal.generic.RET;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class FandeisiaGameManager {

    static int[][] mapStartGame;
    // 0 = vazio
    // 1 = tesouro bronze
    // 2 = tesouro silver
    // 3 = tesouro gold
    // 4 = creatura
    // 5 = buraco

    private int countTurnos = 0; //FALTA GUARDAR
    private int turn15GameOver = 0;
    private int tesourosNoMapa = 0;
    private int turnosSemTesouro = 0;
    private int pontuacaoTotal = 0;
    private boolean carregouFicheiro = false;

    private Team tLDR = new Team(10, 0);
    private Team tRST = new Team(20, 0);

    static List<Creature> listaCreatures = new ArrayList<>();
    private List<Tesouro> listaTreasures = new ArrayList<>();
    private List<Buraco> listaHoles = new ArrayList<>();

    private HashMap<String, Integer> custoFeiticos = new HashMap<String, Integer>() {
        {
            put("EmpurraParaNorte", 1);
            put("EmpurraParaEste", 1);
            put("EmpurraParaSul", 1);
            put("EmpurraParaOeste", 1);
            put("ReduzAlcance", 2);
            put("DuplicaAlcance", 3);
            put("Congela", 3);
            put("Congela4Ever", 10);
            put("Descongela", 8);
        }
    };

    //--------------------Metodos Obrigratorios---------------------

    public String[][] getCreatureTypes() {//Done mas...-------------

        String[][] creatureTypeOptions = new String[5][4];

        creatureTypeOptions[0] = new String[]{"Humano", "skeleton.png", "Muito fixe", "3"};
        creatureTypeOptions[1] = new String[]{"Anão", "dwarf.png", "Dá cabeçadas", "1"};
        creatureTypeOptions[2] = new String[]{"Gigante", "gigante.png", "Bue grande", "5"};
        creatureTypeOptions[3] = new String[]{"Dragão", "super_dragon.png", "Comandado pelo Macaco Lider", "9"};
        creatureTypeOptions[4] = new String[]{"Elfo", "elfo.png", "Muito fixe", "5"};

        return creatureTypeOptions;
    }

    public int startGame(String[] content, int rows, int columns) {

        listaCreatures.clear();
        listaTreasures.clear();

        countTurnos = 0;
        turn15GameOver = 0;
        pontuacaoTotal = 0;

        // tLDR.setPontos(0); //Estava aqui na primeira parte
        // tRST.setPontos(0);

        mapStartGame = new int[rows][columns];//vamos usar isto nas outras funcoes

        String typeTemp = "";
        int xTemp = 0, yTemp = 0, custoLDR = 0, custoRST = 0, idTemp = 0;
        int teamIdTemp = 0, ouro = 0, prata = 0, bronze = 0, pontosTemp = 0;
        String orientTemp = "Norte";
        String[] dados;
        //Dissecação do parametro "content" para objetos creatures e treasures----

        for (String elemento : content) {
            if (elemento.contains("Anão") || elemento.contains("Dragão") || elemento.contains("Gigante") || elemento.contains("Elfo") || elemento.contains("Humano")) {
                dados = elemento.split(",");
                for (String d : dados) {
                    if (d.startsWith("id:")) {
                        idTemp = Integer.parseInt(d.substring(4));
                    } else if (d.startsWith(" type:")) {
                        typeTemp = d.substring(7);
                    } else if (d.startsWith(" teamId:")) {
                        teamIdTemp = Integer.parseInt(d.substring(9));
                    } else if (d.startsWith(" x:")) {
                        xTemp = Integer.parseInt(d.substring(4));
                    } else if (d.startsWith(" y:")) {
                        yTemp = Integer.parseInt(d.substring(4));
                    } else if (d.startsWith(" orientation:")) {
                        orientTemp = d.substring(14);
                    }else if (d.startsWith(" ouro:")) {
                        ouro = Integer.parseInt(d.substring(7));
                    } else if (d.startsWith(" prata:")) {
                        prata = Integer.parseInt(d.substring(8));
                    } else if (d.startsWith(" bronze:")) {
                        bronze = Integer.parseInt(d.substring(9));
                    } else if (d.startsWith(" pontos:")) {
                        pontosTemp = Integer.parseInt(d.substring(9));
                    }
                }
                if (typeTemp.equals("Anão")) {
                    if(!carregouFicheiro) {
                        Anao anao = new Anao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                        listaCreatures.add(anao);
                    }else{
                        Anao anao = new Anao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, bronze, prata, pontosTemp);
                        listaCreatures.add(anao);
                    }
                }
                if (typeTemp.equals("Dragão")) {
                    if(!carregouFicheiro) {
                        Dragao dragao = new Dragao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                        listaCreatures.add(dragao);
                    }else {
                        Dragao dragao = new Dragao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, bronze, prata, pontosTemp);
                        listaCreatures.add(dragao);
                    }
                }
                if (typeTemp.equals("Humano")) {
                    if(!carregouFicheiro) {
                        Humano humano = new Humano(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                        listaCreatures.add(humano);
                    }else{
                        Humano humano = new Humano(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                        listaCreatures.add(humano);
                    }
                }
                if (typeTemp.equals("Elfo")) {
                    if(!carregouFicheiro) {
                        Elfo elfo = new Elfo(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                        listaCreatures.add(elfo);
                    }else{
                        Elfo elfo = new Elfo(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                        listaCreatures.add(elfo);
                    }
                }
                if (typeTemp.equals("Gigante")) {
                    if(!carregouFicheiro) {
                        Gigante gigante = new Gigante(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                        listaCreatures.add(gigante);
                    }else{
                        Gigante gigante = new Gigante(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                        listaCreatures.add(gigante);
                    }
                }
                mapStartGame[yTemp][xTemp] = 4;
                //mapa.put(mapStartGame[yTemp][xTemp], typeTemp);
            }
            if (!elemento.contains("orientation") && (elemento.contains("silver") || elemento.contains("gold") || elemento.contains("bronze"))) {
                dados = elemento.split(",");
                for (String d : dados) {
                    if (d.startsWith("id:")) {
                        idTemp = Integer.parseInt(d.substring(4));
                    } else if (d.startsWith(" type:")) {
                        typeTemp = d.substring(7);
                    } else if (d.startsWith(" x:")) {
                        xTemp = Integer.parseInt(d.substring(4));
                    } else if (d.startsWith(" y:")) {
                        yTemp = Integer.parseInt(d.substring(4));
                    }
                }
                Tesouro tesouroTemp = new Tesouro(idTemp, typeTemp, xTemp, yTemp);
                //Truque, se houver tempo fazer melhor
                if (typeTemp.equals("gold")) {
                    mapStartGame[yTemp][xTemp] = 3;
                    pontuacaoTotal += 3;
                }
                if (typeTemp.equals("silver")) {
                    mapStartGame[yTemp][xTemp] = 2;
                    pontuacaoTotal += 2;
                }
                if (typeTemp.equals("bronze")) {
                    mapStartGame[yTemp][xTemp] = 1;
                    pontuacaoTotal += 1;
                }
                listaTreasures.add(tesouroTemp);
            }
            if (elemento.contains("hole")) {
                dados = elemento.split(Pattern.quote(","));

                for (String d : dados) {
                    if (d.startsWith("id:")) {
                        idTemp = Integer.parseInt(d.substring(4));
                    } else if (d.startsWith(" x:")) {
                        xTemp = Integer.parseInt(d.substring(4));
                    } else if (d.startsWith(" y:")) {
                        yTemp = Integer.parseInt(d.substring(4));
                    }
                }
                Buraco buracoTemp = new Buraco(idTemp, xTemp, yTemp);
                listaHoles.add(buracoTemp);
                mapStartGame[yTemp][xTemp] = 5;
            }
        }

        //Só para visualizar
        for (int i = 0; i < listaCreatures.size(); i++) {
            System.out.println(listaCreatures.get(i).toString());
        }
        for (int i = 0; i < listaTreasures.size(); i++) {
            System.out.println(listaTreasures.get(i).toString());
        }
        for (int i = 0; i < listaHoles.size(); i++) {
            System.out.println(listaHoles.get(i).toString());
        }

        //   pontuacaoTotal = listaTreasures.size();

        //Atualizar plafond

        //Validar plafond
        for (int i = 0; i < listaCreatures.size(); i++) {
            if (listaCreatures.get(i).idEquipa == 10) {
                custoLDR += listaCreatures.get(i).getCusto();
            } else {
                custoRST += listaCreatures.get(i).getCusto();
            }
        }

        if (custoLDR > tLDR.getPlafond() && custoRST > tRST.getPlafond()) {
            return 1;
        }
        if (custoLDR > tLDR.getPlafond()) {
            return 2;
        }
        if (custoRST > tRST.getPlafond()) {
            return 3;
        }

        tLDR.decrementaPlafond(custoLDR); //Atualiza plafond
        tRST.decrementaPlafond(custoRST); //Atualiza plafond

        //Ver mapa TESTES
        for (int i = 0; i < mapStartGame.length; i++) {
            for (int j = 0; j < mapStartGame[i].length; j++) {
               System.out.print(mapStartGame[i][j]);
            }
          System.out.println();
        }
        tesourosNoMapa = listaTreasures.size();
        return 0; //Tudo válido
    }

    public void setInitialTeam(int teamId) {//Done----------------

        if (teamId == 10) { //Acho que funciona, no debug está bem
            tLDR.setEstado(true);
            tRST.setEstado(false);
        } else {
            tLDR.setEstado(false);
            tRST.setEstado(true);
        }
    }

    public void processTurn() {

        int encontrou = 0;
        boolean encontrouLDR = false;
        boolean encontrouRST = false;

        //Execução de Feitiços
        for (Creature creature:listaCreatures) {
            switch (creature.getFeiticoEnum()) {//todo falta o caso de encontrar tesouros com o empurra
                case EmpurraParaNorte:
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 0;
                    creature.setPosY(creature.getPosY() - 1);
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 4;
                    break;
                case EmpurraParaEste:
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 0;
                    creature.setPosX(creature.getPosX() + 1);
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 4;
                    break;
                case EmpurraParaSul:
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 0;
                    creature.setPosY(creature.getPosY() + 1);
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 4;
                    break;
                case EmpurraParaOeste:
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 0;
                    creature.setPosX(creature.getPosX() - 1);
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 4;
                    break;
                case ReduzAlcance:
                    creature.setAlcance(1);
                    break;
                case DuplicaAlcance:
                    creature.setAlcance(creature.getAlcance() * 2);
                    break;
                case Congela:
                    creature.setcongeladoNesteTurno(true);
                    break;
                case Congela4Ever:
                    creature.setCongelado4Ever(true);
                    break;
                case Descongela:
                    creature.setCongelado4Ever(false);
                    break;
                case SemFeitico://nao faz nada
                    break;


            }
        }

        //Execução de movimento
        for (Creature creature : listaCreatures) {

            if(!creature.getCongelado4Ever() && !creature.getcongeladoNesteTurno()){
                encontrou = creature.moveCriatura();
            }

            // reset para false porque só é para este turno
            creature.setcongeladoNesteTurno(false);

            if (encontrou == 1 || encontrou == 2 || encontrou == 3) {

                turn15GameOver = 0;
                for (int j = 0; j < listaTreasures.size(); j++) {
                    if (listaTreasures.get(j).getPosY() == creature.getPosY() && listaTreasures.get(j).getPosX() == creature.getPosX()) {
                        listaTreasures.remove(listaTreasures.get(j));
                    }
                }

                if (creature.getIdEquipa() == 10) {
                    tLDR.somaPontos(encontrou);
                    creature.somaPontos(encontrou); //NÃO TENHO A CERTEZA SE É PARA INCREMENTAR OU ATRIBUIR O VALOR
                    encontrouLDR = true; //Para saber se tenho de atribuir uma só moeda
                }
                if (creature.getIdEquipa() == 20) {
                    tRST.somaPontos(encontrou);
                    creature.somaPontos(encontrou); //NÃO TENHO A CERTEZA SE É PARA INCREMENTAR OU ATRIBUIR O VALOR
                    encontrouRST = true;//Para saber se tenho de atribuir uma só moeda
                }
            }
        }

        if (encontrou == 0) {
            turnosSemTesouro++;
        } else {
            turnosSemTesouro = 0;
        }

        limparFeiticosPorTurno();//AQUI É LIMPO OS FEITIÇOS

        if (!encontrouLDR) {//Regra das moedas fantásticas
            tLDR.somaPlafond(1);
        } else {
            tLDR.somaPlafond(2);
        }
        if (!encontrouRST) {
            tRST.somaPlafond(1);
        } else {
            tRST.somaPlafond(2);
        }

        if(encontrou == 0) {
            turn15GameOver++;
              }
        countTurnos++;

        if (tLDR.getEstado()) {
            tLDR.setEstado(false);
            tRST.setEstado(true);
        } else {
            tLDR.setEstado(true);
            tRST.setEstado(false);
        }

        //   for (int j = 0; j < listaTreasures.size(); j++) {
        //     System.out.println(listaTreasures.get(j).toString());
        //}

        for (int j = 0; j < listaCreatures.size(); j++) {
            System.out.println(listaCreatures.get(j).toString());
        }

        /*for (int i = 0; i < mapStartGame.length; i++) {
                  for (int j = 0; j < mapStartGame[i].length; j++) {
                    System.out.print(mapStartGame[i][j]);
              }
            System.out.println();
        }*/
    }

    public List<Creature> getCreatures() {//Quase Done--------------
        return listaCreatures;
    }

    public boolean gameIsOver() {

        //System.out.println("pontuacao total:" + pontuacaoTotal);
        int conta = pontuacaoTotal / 2 + 1;

        //System.out.println("conta:" + conta);
        if (tLDR.getTeamPontos() >= conta) {
            return true;
        }

        if (tRST.getTeamPontos() >= conta) {
            return true;
        }

        if (listaTreasures.size() == 0) {
            return true;
        }

        if (turnosSemTesouro == 15) {
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
        int id = 0;
        int ouros = 0;
        int pratas = 0;
        int bronze = 0;
        String tipo = "";
        int pontos = 0;

        resultado.add("Welcome to FANDEISIA");

        if (tLDR.getTeamPontos() == tRST.getTeamPontos()) {
            resultado.add("Resultado: EMPATE");
            resultado.add("LDR: " + tLDR.getTeamPontos());
            resultado.add("RESISTENCIA: " + tRST.getTeamPontos());
            resultado.add("Nr. de Turnos jogados: " + countTurnos);
            resultado.add("-----");
        }

        if (tLDR.getTeamPontos() > tRST.getTeamPontos()) {
            resultado.add("Resultado: Vitória da equipa LDR");
            resultado.add("LDR: " + tLDR.getTeamPontos());
            resultado.add("RESISTENCIA: " + tRST.getTeamPontos());
            resultado.add("Nr. de Turnos jogados: " + countTurnos);
            resultado.add("-----");
        }

        if (tLDR.getTeamPontos() < tRST.getTeamPontos()) {
            resultado.add("Resultado: Vitória da equipa RESISTENCIA");
            resultado.add("RESISTENCIA: " + tRST.getTeamPontos());
            resultado.add("LDR: " + tLDR.getTeamPontos());
            resultado.add("Nr. de Turnos jogados: " + countTurnos);
            resultado.add("-----");
        }
        for (Creature creature : listaCreatures) {
            id = creature.getId();
            ouros = creature.getOuro();
            pratas = creature.getPrata();
            bronze = creature.getBronze();
            pontos = creature.getPontos();
            tipo = creature.getTipo();
            resultado.add(id + " : " + tipo + " : " + ouros + " : " + pratas + " : " + bronze + " : " + pontos);
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

        for (Buraco buracoTemp : listaHoles) {
            if (buracoTemp.getPosX() == x && buracoTemp.getPosY() == y) {
                return buracoTemp.getId();
            }
        }

        return 0;
    }

    public int getCurrentTeamId() {

        if (tLDR.getEstado()) {
            return 10;
        } else {
            return 20;
        }
    }

    public int getCurrentScore(int teamID) {

        if (10 == teamID) {
            return tLDR.getTeamPontos();
        } else {
            return tRST.getTeamPontos();
        }
    }

    public String[][] getSpellTypes() { //Done-------------
        String[][] spell = new String[9][3];

        spell[0] = new String[]{"EmpurraParaNorte", "Move a criatura 1 unidade para Norte", "1"};
        spell[1] = new String[]{"EmpurraParaEste", "Move a criatura 1 unidade para Este", "1"};
        spell[2] = new String[]{"EmpurraParaSul", "Move a criatura 1 unidade para Sul", "1"};
        spell[3] = new String[]{"EmpurraParaOeste", "Move a criatura 1 unidade para Oeste", "1"};
        spell[4] = new String[]{"ReduzAlcance", "Reduz o alcance da criatura para: MIN (alcance original, 1)", "2"};
        spell[5] = new String[]{"DuplicaAlcance", "Aumenta o alcance da criatura para o dobro", "3"};
        spell[6] = new String[]{"Congela", "A criatura alvo não se move neste turno", "3"};
        spell[7] = new String[]{"Congela4Ever", "A criatura alvo não se move mais até ao final do jogo", "10"};
        spell[8] = new String[]{"Descongela", "Inverte a aplicação de um Feitiço Congela4Ever", "8"};
        return spell;
    }

    public Map<String, Integer> createComputerArmy() {

        Random random = new Random();
        Map<String, Integer> mapa = new HashMap<String, Integer>();

        int gigante, elfo, humano, dragao, anao, conta;
        Random r = new Random();
        do {
            gigante = Math.abs(r.nextInt()) % 5;
            elfo = Math.abs(r.nextInt()) % 5;
            humano = Math.abs(r.nextInt()) % 5;
            dragao = Math.abs(r.nextInt()) % 5;
            anao = Math.abs(r.nextInt()) % 5;
            conta = gigante * 5 + dragao * 9 + humano * 3 + elfo * 5 + anao;
        } while (tRST.getPlafond() < conta || conta < 35);

        mapa.put("Dragão", dragao);
        mapa.put("Anão", anao);
        mapa.put("Humano", humano);
        mapa.put("Elfo", elfo);
        mapa.put("Gigante", gigante);

        return mapa;
    }//Done---------

    public boolean enchant(int x, int y, String spellName) {//TODO: Aplicar efeito
        int plafond;
        int custo = custoFeiticos.get(spellName);//TODO: Rebenta nesta linha quando abro os feiticos de novo e depois fecho
        System.out.println(custo);
        int yMax = mapStartGame.length - 1;
        int xMax = mapStartGame[0].length - 1;

        if (getCurrentTeamId() == tLDR.getId()) {
            plafond = tLDR.getPlafond();
        } else {
            plafond = tRST.getPlafond();
        }

        if (custo < plafond) {
            for (Creature creature : listaCreatures) {
                if (creature.posX == x && creature.posY == y) {
                    if (spellName.equals("Descongela") && creature.getFeiticoEnum().toString().equals("Congela4Ever")) {
                        if (getCurrentTeamId() == tLDR.getId()) {
                            tLDR.setPlafond(plafond - custo);
                        } else {
                            tRST.setPlafond(plafond - custo);
                        }
                        creature.setFeitico(spellName);
                        return true;
                    } else if (spellName.equals("Congela") && creature.getFeiticoEnum().toString().equals("Congela4Ever")) {
                        return false;
                    } else {
                        boolean condicao = false;

                        do {
                            switch (spellName) {
                                case "EmpurraParaNorte":
                                    if (y - 1 >= 0) {
                                        if (mapStartGame[y - 1][x] != 4 && mapStartGame[y - 1][x] != 5) {
                                            if (getCurrentTeamId() == tLDR.getId()) {
                                                tLDR.setPlafond(plafond - custo);
                                                if (!condicao) {
                                                    creature.setFeitico(spellName);
                                                } else {
                                                    creature.setFeitico("ReduzAlcance");
                                                }
                                            } else {
                                                tRST.setPlafond(plafond - custo);
                                                if (!condicao) {
                                                    creature.setFeitico(spellName);
                                                } else {
                                                    creature.setFeitico("ReduzAlcance");
                                                }
                                            }
                                            return true;
                                        }
                                    }
                                    return false;
                                case "EmpurraParaEste":
                                    if (x + 1 <= xMax) {
                                        if (mapStartGame[y][x + 1] != 4 && mapStartGame[y][x + 1] != 5) {
                                            if (getCurrentTeamId() == tLDR.getId()) {
                                                tLDR.setPlafond(plafond - custo);
                                                if (!condicao) {
                                                    creature.setFeitico(spellName);
                                                } else {
                                                    creature.setFeitico("ReduzAlcance");
                                                }
                                            } else {
                                                tRST.setPlafond(plafond - custo);
                                                if (!condicao) {
                                                    creature.setFeitico(spellName);
                                                } else {
                                                    creature.setFeitico("ReduzAlcance");
                                                }
                                            }
                                            return true;
                                        }
                                    }
                                    return false;
                                case "EmpurraParaSul":
                                    if (y + 1 <= yMax) {
                                        if (mapStartGame[y + 1][x] != 4 && mapStartGame[y + 1][x] != 5) {
                                            if (getCurrentTeamId() == tLDR.getId()) {
                                                tLDR.setPlafond(plafond - custo);
                                                if (!condicao) {
                                                    creature.setFeitico(spellName);
                                                } else {
                                                    creature.setFeitico("ReduzAlcance");
                                                }
                                            } else {
                                                tRST.setPlafond(plafond - custo);
                                                if (!condicao) {
                                                    creature.setFeitico(spellName);
                                                } else {
                                                    creature.setFeitico("ReduzAlcance");
                                                }
                                            }
                                            return true;
                                        }

                                    }
                                    return false;
                                case "EmpurraParaOeste":
                                    if (x - 1 >= 0) {
                                        if (mapStartGame[y][x - 1] != 4 && mapStartGame[y][x - 1] != 5) {
                                            if (getCurrentTeamId() == tLDR.getId()) {
                                                tLDR.setPlafond(plafond - custo);
                                                if (!condicao) {
                                                    creature.setFeitico(spellName);
                                                } else {
                                                    creature.setFeitico("ReduzAlcance");
                                                }
                                            } else {
                                                tRST.setPlafond(plafond - custo);
                                                if (!condicao) {
                                                    creature.setFeitico(spellName);
                                                } else {
                                                    creature.setFeitico("ReduzAlcance");
                                                }
                                            }
                                            return true;
                                        }

                                    }
                                    return false;
                                    //break;
                                case "ReduzAlcance":
                                    switch (creature.getOrientacao()) {
                                        case Norte:
                                            condicao = true;
                                            spellName = "EmpurraParaNorte";
                                            break;
                                        case Nordeste://todo deve estar bem agora
                                            if (creature.getPosY() - 1 >= 0 && creature.getPosX() + 1 <= xMax) {
                                                if (mapStartGame[y - 1][x + 1] != 4 && mapStartGame[y - 1][x + 1] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }
                                            return false;
                                        case Este:
                                            condicao = true;
                                            spellName = "EmpurraParaEste";
                                            break;
                                        case Sudeste://todo deve estar bem agora
                                            if (creature.getPosY() + 1 <= yMax && creature.getPosX() + 1 <= xMax) {
                                                if (mapStartGame[y + 1][x + 1] != 4 && mapStartGame[y + 1][x + 1] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }

                                            return false;
                                        case Sul:
                                            condicao = true;
                                            spellName = "EmpurraParaSul";
                                            break;
                                        case Sudoeste://todo deve estar bem agora
                                            if (creature.getPosY() + 1 <= yMax && creature.getPosX() - 1 >= 0) {
                                                if (mapStartGame[y + 1][x - 1] != 4 && mapStartGame[y + 1][x - 1] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }

                                            return false;
                                        case Oeste:
                                            condicao = true;
                                            spellName = "EmpurraParaOeste";
                                            break;
                                        case Noroeste://todo deve estar bem agora
                                            if (creature.getPosY() - 1 >= 0 && creature.getPosX() - 1 >= 0) {
                                                if (mapStartGame[y - 1][x - 1] != 4 && mapStartGame[y - 1][x - 1] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }
                                            return false;
                                    }
                                    break;
                                case "DuplicaAlcance":
                                    int alcanceDuplTemp = creature.getAlcance() * 2;
                                    switch (creature.getOrientacao()) {
                                        case Norte:
                                            if (creature.getPosY() - alcanceDuplTemp >= 0) {
                                                if (mapStartGame[y - alcanceDuplTemp][x] != 4 && mapStartGame[y - alcanceDuplTemp][x] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }

                                        case Nordeste://todo deve estar bem agora
                                            if (creature.getPosY() - alcanceDuplTemp >= 0 && creature.getPosX() + alcanceDuplTemp <= xMax) {
                                                if (mapStartGame[y - alcanceDuplTemp][x + alcanceDuplTemp] != 4 && mapStartGame[y - alcanceDuplTemp][x + alcanceDuplTemp] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }


                                            break;
                                        case Este:
                                            if (creature.getPosX() + alcanceDuplTemp <= xMax) {
                                                if (mapStartGame[y][x + alcanceDuplTemp] != 4 && mapStartGame[y][x + alcanceDuplTemp] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }

                                            break;
                                        case Sudeste://todo deve estar bem agora
                                            if (creature.getPosY() + alcanceDuplTemp <= yMax && creature.getPosX() + alcanceDuplTemp <= xMax) {
                                                if (mapStartGame[y + alcanceDuplTemp][x + alcanceDuplTemp] != 4 && mapStartGame[y + alcanceDuplTemp][x + alcanceDuplTemp] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }

                                            break;
                                        case Sul:
                                            if (creature.getPosY() + alcanceDuplTemp <= yMax) {
                                                if (mapStartGame[y + alcanceDuplTemp][x] != 4 && mapStartGame[y + alcanceDuplTemp][x] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }

                                            break;
                                        case Sudoeste://todo deve estar bem agora
                                            if (creature.getPosY() + alcanceDuplTemp <= yMax && creature.getPosX() - alcanceDuplTemp >= 0) {
                                                if (mapStartGame[y + alcanceDuplTemp][x - alcanceDuplTemp] != 4 && mapStartGame[y + alcanceDuplTemp][x - alcanceDuplTemp] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }

                                            break;
                                        case Oeste:
                                            if (creature.getPosX() - alcanceDuplTemp >= 0) {
                                                if (mapStartGame[y][x - alcanceDuplTemp] != 4 && mapStartGame[y][x - alcanceDuplTemp] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }

                                            break;
                                        case Noroeste://todo deve estar bem agora
                                            if (creature.getPosY() - alcanceDuplTemp >= 0 && creature.getPosX() - alcanceDuplTemp >= 0) {
                                                if (mapStartGame[y - alcanceDuplTemp][x - alcanceDuplTemp] != 4 && mapStartGame[y - alcanceDuplTemp][x - alcanceDuplTemp] != 5) {
                                                    if (getCurrentTeamId() == tLDR.getId()) {
                                                        tLDR.setPlafond(plafond - custo);
                                                    } else {
                                                        tRST.setPlafond(plafond - custo);
                                                    }
                                                    creature.setFeitico(spellName);
                                                    return true;
                                                }
                                            }
                                            break;
                                    }
                                    break;
                            }
                        } while (condicao);
                    }
                }
            }
        }
        return false;
    }

    public String getSpell(int x, int y) { //Done-------
        for (Creature creature : listaCreatures) {
            if (creature.posX == x && creature.posY == y) {
                if (creature.getFeiticoEnum().toString().equals("SemFeitico")) {
                    return null;
                }
                return creature.getFeiticoEnum().toString();
            }
        }
        return null;
    }

    public int getCoinTotal(int teamID) {
        if (10 == teamID) {
            return tLDR.getPlafond();
        } else {
            return tRST.getPlafond();
        }
    }

    public boolean saveGame(File fich) {

        try {
            int idTemp, posXTemp, posyTemp;
            String tipo;
            int teamId, ouro, prata, bronze, pontos;
            Creature.Orientacao orientacao;
            PrintWriter gravarArq = new PrintWriter(fich);

            gravarArq.println();

            gravarArq.println("my: " + mapStartGame.length);
            gravarArq.println("mx: " + mapStartGame[0].length);

            for (int i = 0; i < listaCreatures.size(); i++) {
                idTemp = listaCreatures.get(i).getId();
                posXTemp = listaCreatures.get(i).getPosX();
                posyTemp = listaCreatures.get(i).getPosY();
                tipo = listaCreatures.get(i).getTipo();
                teamId = listaCreatures.get(i).getIdEquipa();
                orientacao = listaCreatures.get(i).getOrientacao();
                ouro = listaCreatures.get(i).getOuro();
                prata = listaCreatures.get(i).getPrata();
                bronze = listaCreatures.get(i).getBronze();
                pontos = listaCreatures.get(i).getPontos();
                gravarArq.println("id: " + idTemp + ", type: " + tipo + ", teamId: " + teamId + ", x: " + posXTemp + ", y: " + posyTemp +
                        ", orientation: " + orientacao + ", ouro: " + ouro + ", prata: " + prata + ", bronze: " + bronze + ", pontos: " + pontos);
            }
            gravarArq.println();
            for (int i = 0; i < listaTreasures.size(); i++) {
                idTemp = listaTreasures.get(i).getId();
                posXTemp = listaTreasures.get(i).getPosX();
                posyTemp = listaTreasures.get(i).getPosY();
                gravarArq.println("id: " + idTemp + ", type: " + listaTreasures.get(i).getTipo() + ", x: " + posXTemp + ", y: " + posyTemp);
            }
            gravarArq.println();
            for (int i = 0; i < listaHoles.size(); i++) {
                idTemp = listaHoles.get(i).getId();
                posXTemp = listaHoles.get(i).getPosX();
                posyTemp = listaHoles.get(i).getPosY();
                gravarArq.println("id: " + idTemp + ", type: hole, x: " + posXTemp + ", y: " + posyTemp);
            }

            gravarArq.println();
            gravarArq.println("id: " + tLDR.getId() + ", plafound: " + tLDR.getPlafond() + ", pontos: " + tLDR.getTeamPontos() + ", estado: " + tLDR.getEstado());
            gravarArq.println("id: " + tRST.getId() + ", plafound: " + tRST.getPlafond() + ", pontos: " + tRST.getTeamPontos() + ", estado: " + tRST.getEstado());
            gravarArq.println("pontuacaoTotal: " + pontuacaoTotal);
            gravarArq.println("turnosSemTesouro: " + turn15GameOver);
            gravarArq.println("turnos: " + countTurnos);

            gravarArq.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace(); //Temporário
            return false;
        }
    }

    public boolean loadGame(File fich) {

        int idTemp = 0, yTemp, tamanhoY = 0, tamanhoX = 0, plafoundTemp = 0;
        int pontosTemp = 0, ouro, prata, bronze, i = 0;
        boolean estado = false;
        List<String> stringArrayList = new ArrayList<>();
        String[] dados;

        try {
            FileReader arq = new FileReader(fich);
            BufferedReader lerArq = new BufferedReader(arq);
            String linha = lerArq.readLine();

            while (true) {
                System.out.printf("%s\n", linha);
                linha = lerArq.readLine();

                if (linha != null) {
                    stringArrayList.add(linha);
                    if (linha.contains("my:")) {
                        tamanhoY = Integer.parseInt(linha.substring(4));
                    }
                    if (linha.contains("mx: ")) {
                        tamanhoX = Integer.parseInt(linha.substring(4));
                    }
                    if (linha.contains("plafound")) {
                        dados = linha.split(Pattern.quote(","));

                        for (String d : dados) {
                            if (d.startsWith("id:")) {
                                idTemp = Integer.parseInt(d.substring(4));
                            } else if (d.startsWith(" plafound:")) {
                                plafoundTemp = Integer.parseInt(d.substring(11));
                            } else if (d.startsWith(" pontos:")) {
                                pontosTemp = Integer.parseInt(d.substring(9));
                            } else if (d.startsWith(" estado:")) {
                                estado = Boolean.parseBoolean(d.substring(9));
                            }
                        }
                        if (idTemp == 10) {
                            tLDR.setPlafond(plafoundTemp);
                            tLDR.setEstado(estado);
                            tLDR.setPontosTeam(pontosTemp);
                        }
                        if (idTemp == 20) {
                            tRST.setPlafond(plafoundTemp);
                            tRST.setEstado(estado);
                            tRST.setPontosTeam(pontosTemp);
                        }
                    }
                    if (linha.startsWith("pontuacaoTotal:")) {
                        pontosTemp = Integer.parseInt(linha.substring(16));
                        pontuacaoTotal = pontosTemp;
                    }
                    if (linha.startsWith("turnosSemTesouro:")) {
                        pontosTemp = Integer.parseInt(linha.substring(18));
                        turn15GameOver = pontosTemp;
                    }
                    if (linha.startsWith("turnos:")) {
                        yTemp = Integer.parseInt(linha.substring(8));
                        countTurnos = yTemp; //Aproveitei a variavel...
                    }
                    i++;
                } else {
                    String[] dsfs = new String[stringArrayList.size()];
                    for (int j = 0; j < stringArrayList.size(); j++) {
                        dsfs[j] = stringArrayList.get(j);
                    }
                    carregouFicheiro = true;
                    startGame(dsfs, tamanhoY, tamanhoX);
                    break;
                }
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
            return false;
        }
        System.out.println();
        return true;
    }

    public String whoIsLordEder() {
        return "Ederzito António Macedo Lopes";
    }

    private void limparFeiticosPorTurno() {
        for (Creature creature : listaCreatures) {
            if (!creature.getFeiticoEnum().toString().equals("Congela4Ever")) {
                creature.setFeitico("SemFeitico");
            }
        }
    }

    public void toggleAI(boolean active){
        if(active){
            int posXT, posYT;

            Random rnd = new Random();
            int randNum = rnd.nextInt((100-50)+1) + 50;
            for (int i = 0; i < listaCreatures.size(); i++){
                if(randNum > 55) {
          //          enchant();
                }
            }
        }

            for (int i = 0; i < listaCreatures.size(); i++){
                if(listaCreatures.get(i).getIdEquipa() == 20){
                    for(int j = 0; j < listaTreasures.size(); j++){
           //             if()
                    }
                }
            }
    }

    private void aplicarFeiticosPorTurno() {
        for (Creature creature : listaCreatures) {
            switch (creature.getFeiticoEnum()) {
                case EmpurraParaNorte:
                    break;
                case EmpurraParaEste:
                    break;
                case EmpurraParaSul:
                    break;
                case EmpurraParaOeste:
                    break;
                case ReduzAlcance:
                    break;
                case DuplicaAlcance:
                    break;
                case Congela:
                    break;
                case Congela4Ever:
                    break;
                case Descongela:
                    break;
            }
        }
    }
}
