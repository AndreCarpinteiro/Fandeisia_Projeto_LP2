
package pt.ulusofona.lp2.fandeisiaGame;

//import com.sun.org.apache.bcel.internal.generic.RET;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FandeisiaGameManager {

    static int[][] mapStartGame;
    // 0 = vazio
    // 1 = tesouro bronze
    // 2 = tesouro silver
    // 3 = tesouro gold
    // 4 = creatura
    // 5 = buraco

    private int countTurnos = 0;
    private int turnosSemTesouro = 0;
    private int pontuacaoTotalEmJogo = 0;
    private boolean carregouFicheiro = false, encontrouLDR = false, encontrouRST = false;
    ArrayList<String> tiposEmFalta = new ArrayList<>();

    Team tLDR = new Team(10, 0);
    Team tRST = new Team(20, 0);

    static List<Creature> listaCreatures = new ArrayList<>();
    //private HashMap <Integer, Creature> Criaturas = new HashMap<Integer, Creature>();
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
            //System.out.println("dsf");
        }
    };

    //--------------------Metodos Obrigratorios---------------------

    public String[][] getCreatureTypes() {//Done mas...-------------

        String[][] creatureTypeOptions = new String[5][4];

        creatureTypeOptions[0] = new String[]{"Humano", "skeleton.png", "Simples", "3"};
        creatureTypeOptions[1] = new String[]{"Anão", "dwarf.png", "Dispensavel", "1"};
        creatureTypeOptions[2] = new String[]{"Gigante", "gigante.png", "Dá grandes saltos", "5"};
        creatureTypeOptions[3] = new String[]{"Dragão", "super_dragon.png", "Voa sobre criaturas e buracos", "9"};
        creatureTypeOptions[4] = new String[]{"Elfo", "elfo.png", "É fragil", "5"};

        return creatureTypeOptions;
    }

    public void startGame(String[] contents, int rows, int colums) throws
            InsufficientCoinsException{

        listaCreatures.clear();
        listaTreasures.clear();
        tiposEmFalta.add("Dragão");
        tiposEmFalta.add("Anão");
        tiposEmFalta.add("Humano");
        tiposEmFalta.add("Elfo");
        tiposEmFalta.add("Gigante");

        countTurnos = 0;
        mapStartGame = new int[rows][colums];//vamos usar isto nas outras funcoes
        List<Creature> tempCreature = new ArrayList<>();


        String typeTemp = "";
        int xTemp = 0, yTemp = 0, idTemp = 0;
        int teamIdTemp = 0, ouro = 0, prata = 0, bronze = 0, pontosTemp = 0;
        String orientTemp = "Norte";
        String[] dados;
        //Dissecação do parametro "content" para objetos creatures e treasures----

        for (String elemento : contents) {
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
                    } else if (d.startsWith(" ouro:")) {
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
                    if (!carregouFicheiro) {
                        Anao anao = new Anao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                        anao.somaQtdCreatura();
                        listaCreatures.add(anao);
                    } else {
                        Anao anao = new Anao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, bronze, prata, pontosTemp);
                        anao.somaQtdCreatura();
                        listaCreatures.add(anao);
                    }
                }
                if (typeTemp.equals("Dragão")) {
                    if (!carregouFicheiro) {
                        Dragao dragao = new Dragao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                        dragao.somaQtdCreatura();
                        listaCreatures.add(dragao);
                    } else {
                        Dragao dragao = new Dragao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, bronze, prata, pontosTemp);
                        dragao.somaQtdCreatura();
                        listaCreatures.add(dragao);
                    }
                }
                if (typeTemp.equals("Humano")) {
                    if (!carregouFicheiro) {
                        Humano humano = new Humano(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                       humano.somaQtdCreatura();
                        listaCreatures.add(humano);
                    } else {
                        Humano humano = new Humano(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                       humano.somaQtdCreatura();
                        listaCreatures.add(humano);
                    }
                }
                if (typeTemp.equals("Elfo")) {
                    if (!carregouFicheiro) {
                        Elfo elfo = new Elfo(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                        elfo.somaQtdCreatura();
                        listaCreatures.add(elfo);
                    } else {
                        Elfo elfo = new Elfo(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                        elfo.somaQtdCreatura();
                        listaCreatures.add(elfo);
                    }
                }
                if (typeTemp.equals("Gigante")) {
                    if (!carregouFicheiro) {
                        Gigante gigante = new Gigante(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                        gigante.somaQtdCreatura();
                        listaCreatures.add(gigante);
                    } else {
                        Gigante gigante = new Gigante(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                        gigante.somaQtdCreatura();
                        listaCreatures.add(gigante);
                    }
                }
                mapStartGame[yTemp][xTemp] = 4;
                //mapa.put(mapStartGame[yTemp][xTemp], typeTemp);

                tiposEmFalta.remove(typeTemp);
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
                    pontuacaoTotalEmJogo += 3;
                }
                if (typeTemp.equals("silver")) {
                    mapStartGame[yTemp][xTemp] = 2;
                    pontuacaoTotalEmJogo += 2;
                }
                if (typeTemp.equals("bronze")) {
                    mapStartGame[yTemp][xTemp] = 1;
                    pontuacaoTotalEmJogo += 1;
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

        //Ordenar Ids das creaturas!
        Collections.sort(listaCreatures, new ComparadorId());

        //Só para visualizar
        for (Creature creature : listaCreatures) {
            System.out.println(creature.toString());
        }
        for (Tesouro tesouro : listaTreasures) {
            System.out.println(tesouro.toString());
        }
        for (Buraco buraco : listaHoles) {
            System.out.println(buraco.toString());
        }

        //Atualizar plafond

        tLDR.setCusto(0);
        tRST.setCusto(0);
        //Validar plafond
        for (Creature creature : listaCreatures) {
            if (creature.idEquipa == 10) {
                tLDR.somaCusto(creature.getCusto());
            } else {
                tRST.somaCusto(creature.getCusto());
            }
        }

            if (tLDR.getCusto() > tLDR.getPlafond() && tRST.getCusto() > tRST.getPlafond()) {
                System.out.println("1");
                throw new InsufficientCoinsException(tLDR,tRST);
            } if (tLDR.getCusto() > tLDR.getPlafond()) {
                System.out.println("2");
                throw new InsufficientCoinsException(tLDR,tRST);
            } if (tRST.getCusto() > tRST.getPlafond()) {
                System.out.println("3");
                throw new InsufficientCoinsException(tLDR,tRST);
            }


        tLDR.decrementaPlafond(tLDR.getCusto()); //Atualiza plafond
        tRST.decrementaPlafond(tRST.getCusto()); //Atualiza plafond

        //Ver mapa TESTES
        for (int i = 0; i < mapStartGame.length; i++) {
            for (int j = 0; j < mapStartGame[i].length; j++) {
                System.out.print(mapStartGame[i][j]);
            }
            System.out.println();
        }

        //return 0; //Tudo válido
    }

    public void setInitialTeam(int teamId) {//Done----------------
        try {
            if (teamId == 10) { //Acho que funciona, no debug está bem
                tLDR.setEstado(true);
                tRST.setEstado(false);
            } else if(teamId == 20){
                tLDR.setEstado(false);
                tRST.setEstado(true);
            }
        }
        catch(Exception e) {
            System.out.println("setInitialTeam recebeu uma equipa inválida ou não recebeu de todo um valor teamId");
        }

    }

    public void processTurn() {

        int encontrou = 0, tesouroEmpurra = 0;
        encontrouLDR = false;
        encontrouRST = false;
        int flag1 = 0, flag2 = 0;

        //Execução de Feitiços
        for (Creature creature : listaCreatures) {
            switch (creature.getFeiticoEnum()) {//todo falta o caso de encontrar tesouros com o empurra
                case EmpurraParaNorte:
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 0;
                    tesouroEmpurra = mapStartGame[creature.getPosY() - 1][creature.getPosX()];
                    creature.setPosY(creature.getPosY() - 1);
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 4;
                    creature.somaFeitico();
                    creature.maisUmKm();
                    break;
                case EmpurraParaEste:
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 0;
                    tesouroEmpurra = mapStartGame[creature.getPosY()][creature.getPosX() + 1];
                    creature.setPosX(creature.getPosX() + 1);
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 4;
                    creature.somaFeitico();
                    creature.maisUmKm();
                    break;
                case EmpurraParaSul:
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 0;
                    tesouroEmpurra = mapStartGame[creature.getPosY() + 1][creature.getPosX()];
                    creature.setPosY(creature.getPosY() + 1);
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 4;
                    creature.somaFeitico();
                    creature.maisUmKm();
                    break;
                case EmpurraParaOeste:
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 0;
                    tesouroEmpurra = mapStartGame[creature.getPosY()][creature.getPosX() - 1];
                    creature.setPosX(creature.getPosX() - 1);
                    mapStartGame[creature.getPosY()][creature.getPosX()] = 4;
                    creature.somaFeitico();
                    creature.maisUmKm();
                    break;
                case ReduzAlcance:
                    creature.setAlcance(1);
                    creature.somaFeitico();
                    break;
                case DuplicaAlcance:
                    creature.setAlcance(creature.getAlcance() * 2);
                    creature.somaFeitico();
                    break;
                case Congela:
                    creature.setcongeladoNesteTurno(true);
                    creature.somaFeitico();
                    break;
                case Congela4Ever:
                    creature.setCongelado4Ever(true);
                    creature.somaFeitico();
                    break;
                case Descongela:
                    creature.setCongelado4Ever(false);
                    creature.somaFeitico();
                    break;
                case SemFeitico://nao faz nada
                    break;
            }
            flag1 += encontraTesouro(tesouroEmpurra, creature);
        }

        //Execução de movimento
        for (Creature creature : listaCreatures) {

            if (!creature.getCongelado4Ever() && !creature.getcongeladoNesteTurno()) {
                encontrou = creature.moveCriatura();
            }

            // reset para false porque só é para este turno
            creature.setcongeladoNesteTurno(false);
            flag2 += encontraTesouro(encontrou, creature);
        }

        if (flag1 == 0 && flag2 == 0) {
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

        countTurnos++;

        if (tLDR.getEstado()) {
            tLDR.setEstado(false);
            tRST.setEstado(true);
        } else {
            tLDR.setEstado(true);
            tRST.setEstado(false);
        }

        System.out.println("Turnos sem tesouro: " + turnosSemTesouro);
        System.out.println("Pontoação total em jogo: " + pontuacaoTotalEmJogo);

        for (int j = 0; j < listaCreatures.size(); j++) {
            System.out.println(listaCreatures.get(j).toString());
        }
    }

    public List<Creature> getCreatures() {//Quase Done--------------
        return listaCreatures;
    }

    public boolean gameIsOver() {

        if (tLDR.getTeamPontos() > tRST.getTeamPontos() + pontuacaoTotalEmJogo) {
            return true;
        }

        if (tRST.getTeamPontos() > tLDR.getTeamPontos() + pontuacaoTotalEmJogo) {
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
        int id; //= 0;
        int ouros; //= 0;
        int pratas; //= 0;
        int bronze; //= 0;
        String tipo; //= "";
        int pontos; //= 0;

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
        if (spellName == null) {
            return false;
        }
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
                                case "Congela":
                                    if (getCurrentTeamId() == tLDR.getId()) {
                                        tLDR.setPlafond(plafond - custo);
                                    } else {
                                        tRST.setPlafond(plafond - custo);
                                    }
                                    creature.setFeitico(spellName);
                                    return true;
                                case "Congela4Ever":
                                    if (getCurrentTeamId() == tLDR.getId()) {
                                        tLDR.setPlafond(plafond - custo);
                                    } else {
                                        tRST.setPlafond(plafond - custo);
                                    }
                                    creature.setFeitico(spellName);
                                    return true;
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

            for (Creature creature : listaCreatures) {
                idTemp = creature.getId();
                posXTemp = creature.getPosX();
                posyTemp = creature.getPosY();
                tipo = creature.getTipo();
                teamId = creature.getIdEquipa();
                orientacao = creature.getOrientacao();
                ouro = creature.getOuro();
                prata = creature.getPrata();
                bronze = creature.getBronze();
                pontos = creature.getPontos();
                gravarArq.println("id: " + idTemp + ", type: " + tipo + ", teamId: " + teamId + ", x: " + posXTemp + ", y: " + posyTemp +
                        ", orientation: " + orientacao + ", ouro: " + ouro + ", prata: " + prata + ", bronze: " + bronze + ", pontos: " + pontos);
            }
            gravarArq.println();
            for (Tesouro tesouro : listaTreasures) {
                idTemp = tesouro.getId();
                posXTemp = tesouro.getPosX();
                posyTemp = tesouro.getPosY();
                gravarArq.println("id: " + idTemp + ", type: " + tesouro.getTipo() + ", x: " + posXTemp + ", y: " + posyTemp);
            }
            gravarArq.println();
            for (Buraco buraco : listaHoles) {
                idTemp = buraco.getId();
                posXTemp = buraco.getPosX();
                posyTemp = buraco.getPosY();
                gravarArq.println("id: " + idTemp + ", type: hole, x: " + posXTemp + ", y: " + posyTemp);
            }

            gravarArq.println();
            gravarArq.println("id: " + tLDR.getId() + ", plafound: " + tLDR.getPlafond() + ", pontos: " + tLDR.getTeamPontos() + ", estado: " + tLDR.getEstado());
            gravarArq.println("id: " + tRST.getId() + ", plafound: " + tRST.getPlafond() + ", pontos: " + tRST.getTeamPontos() + ", estado: " + tRST.getEstado());
            gravarArq.println("pontuacaoTotal: " + pontuacaoTotalEmJogo);
            gravarArq.println("turnosSemTesouro: " + turnosSemTesouro);
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
                        pontuacaoTotalEmJogo = pontosTemp;
                    }
                    if (linha.startsWith("turnosSemTesouro:")) {
                        pontosTemp = Integer.parseInt(linha.substring(18));
                        turnosSemTesouro = pontosTemp;
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
        } catch (IOException | InsufficientCoinsException e) {
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

    public void toggleAI(boolean active) {
        if (active) {
            Random r = new Random();
            boolean aplica = false;
            int feiticoRandom, creaturaRandom, joga = r.nextInt(6), id = -1;
            List<Integer> array = new ArrayList<>();

            for (Creature creature : listaCreatures) {
                if (creature.getIdEquipa() == 20) {
                    array.add(creature.getId());
                }
            }

            if (joga < 3) {//Para não fazer sempre feitiços...
                while (!aplica) {

                    feiticoRandom = r.nextInt(7);
                    creaturaRandom = r.nextInt(array.size());

                    for (int i = 0; i < listaCreatures.size(); i++) {
                        if (listaCreatures.get(i).getId() == array.get(creaturaRandom)) {
                            id = i;
                            break;
                        }
                    }

                    switch (feiticoRandom) {
                        case 0:
                            aplica = enchant(listaCreatures.get(id).getPosX(),
                                    listaCreatures.get(id).getPosY(),
                                    "EmpurraParaNorte");
                            break;

                        case 1:
                            aplica = enchant(listaCreatures.get(id).getPosX(),
                                    listaCreatures.get(id).getPosY(),
                                    "EmpurraParaSul");
                            break;

                        case 2:
                            aplica = enchant(listaCreatures.get(id).getPosX(),
                                    listaCreatures.get(id).getPosY(),
                                    "EmpurraParaEste");
                            break;

                        case 3:
                            aplica = enchant(listaCreatures.get(id).getPosX(),
                                    listaCreatures.get(id).getPosY(),
                                    "EmpurraParaOeste");
                            break;

                        case 4:
                            aplica = enchant(listaCreatures.get(id).getPosX(),
                                    listaCreatures.get(id).getPosY(),
                                    "Congela");
                            break;

                        case 5:
                            aplica = enchant(listaCreatures.get(id).getPosX(),
                                    listaCreatures.get(id).getPosY(),
                                    "ReduzAlcance");
                            break;

                        case 6:
                            aplica = enchant(listaCreatures.get(id).getPosX(),
                                    listaCreatures.get(id).getPosY(),
                                    "DuplicaAlcance");
                            break;

                        case 7:
                            aplica = enchant(listaCreatures.get(id).getPosX(),
                                    listaCreatures.get(id).getPosY(),
                                    "Congela4Ever");
                            break;
                    }
                }
            }
        }
    }

    public int encontraTesouro(int encontrou, Creature creature) {

        if (encontrou == 1 || encontrou == 2 || encontrou == 3) {
            pontuacaoTotalEmJogo -= encontrou;
            for (int j = 0; j < listaTreasures.size(); j++) {
                if (listaTreasures.get(j).getPosY() == creature.getPosY() && listaTreasures.get(j).getPosX() == creature.getPosX()) {
                    listaTreasures.remove(listaTreasures.get(j));
                }
            }

            if (creature.getIdEquipa() == 10) {
                tLDR.somaPontos(encontrou);
                creature.somaPontos(encontrou);
                encontrouLDR = true;
            }
            if (creature.getIdEquipa() == 20) {
                tRST.somaPontos(encontrou);
                creature.somaPontos(encontrou);
                encontrouRST = true;
            }
            return 1;
        }
        return 0;
    }

    public Map<String, List<String>> getStatistics(){
        Map<String, List<String>> mapa = new HashMap<String, List<String>>();

        //as3MaisCarregadas
        List<String> lista1 = listaCreatures.stream()
                .sorted((creature1,creature2) -> creature2.getQtdTesouros() - creature1.getQtdTesouros())
                .map(creature -> creature.getId() + ":" +creature.getQtdTesouros())
                .limit(3)
                .collect(Collectors.toList());
        mapa.put("as3MaisCarregadas",lista1);

        //as5MaisRicas
        List<String> lista2 = listaCreatures.stream()
                .sorted(Comparator.comparingInt(Creature::getPontos).reversed().thenComparing(Creature::getQtdTesouros))
                .limit(5)
                .map(creature -> creature.getId() + ":" + creature.getPontos() + ":" + creature.getQtdTesouros())
                .collect(Collectors.toList());
        mapa.put("as5MaisRicas",lista2);

        //osAlvosFavoritos
        List<String> lista3 = listaCreatures.stream()
                .sorted((x1, x2) -> x2.getCountFeitico() - x1.getCountFeitico())
                .map(x -> x.getId() + ":" + x.getIdEquipa() + ":" + x.getCountFeitico())
                .limit(3)
                .collect(Collectors.toList());
        mapa.put("osAlvosFavoritos",lista3);

        //os3MaisViajadas
        List<String> lista4 = listaCreatures.stream()
                .sorted((creature1,creature2) -> creature1.getKms() - creature2.getKms())
                .limit(3)
                .map(creature -> creature.getId() + ":" + creature.getKms())
                .collect(Collectors.toList());
        mapa.put("as3MaisViajadas",lista4);

        //tiposDeCriaturaESeusTesouros
        List<String> lista5 = listaCreatures.stream()
                .sorted(Comparator.comparingInt(Creature::getPontosPorCreatura).reversed().thenComparing(Creature::getQtdCreatura))
                .map(x -> x.getTipo() + ":" + x.getQtdCreatura() + ":" + x.getPontosPorCreatura())
                .distinct()
                .collect(Collectors.toList());

        tiposEmFalta.stream()
                .forEach(x -> lista5.add(x + "-1"));

        mapa.put("tiposDeCriaturaESeusTesouros",lista5);

        return mapa;
    }
}
