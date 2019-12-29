
package pt.ulusofona.lp2.fandeisiaGame;

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

    private int countTurnos;
    private int turn15GameOver;
    private int pontuacaoTotal;

    private Team tLDR = new Team(10, 0);
    private Team tRST = new Team(20, 0);

    static List<Creature> listaCreatures = new ArrayList<>();
    private List<Tesouro> listaTreasures = new ArrayList<>();
    private List<Buraco> listaHoles = new ArrayList<>();

    //Criei isto para saber o conteudo de cada posicao TESTE
    //static HashMap<Integer, String> mapa = new HashMap<Integer, String>();

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

  System.out.println("sdf");
        listaCreatures.clear();
        listaTreasures.clear();

        countTurnos = 0;
        turn15GameOver = 0;
        pontuacaoTotal = 0;

        // tLDR.setPontos(0); //Estava aqui na primeira parte
        // tRST.setPontos(0);

        mapStartGame = new int[rows][columns];//vamos usar isto nas outras funcoes

        for (int i = 0; i < mapStartGame.length; i++) {
            for (int j = 0; j < mapStartGame[i].length; j++) {
                mapStartGame[i][j] = 0;
                //mapa.put(mapStartGame[i][j], "vazio");
            }
        }
        //mapa.containsValue("vazio");

        int idTemp = 0;
        String typeTemp = "";
        int xTemp = 0;
        int yTemp = 0;
        int custoLDR = 0;
        int custoRST = 0;
        int teamIdTemp = 0;
        String orientTemp = "Norte";

        //Dissecação do parametro "content" para objetos creatures e treasures----

        for (String elemento : content) {
            String[] dados = elemento.split(",");

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
                }
            }

            if (typeTemp.equals("Anão")) {
                Anao anao = new Anao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                listaCreatures.add(anao);
            }
            if (typeTemp.equals("Dragão")) {
                Dragao dragao = new Dragao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                listaCreatures.add(dragao);
            }
            if (typeTemp.equals("Humano")) {//Falta criar na interface...
                Humano humano = new Humano(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                listaCreatures.add(humano);
            }
            if (typeTemp.equals("Elfo")) {//Falta criar na interface...
                Elfo elfo = new Elfo(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                listaCreatures.add(elfo);
            }
            if (typeTemp.equals("Gigante")) {//Falta criar na interface...
                Gigante gigante = new Gigante(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp));
                listaCreatures.add(gigante);
            }
            mapStartGame[yTemp][xTemp] = 4;
            //mapa.put(mapStartGame[yTemp][xTemp], typeTemp);

            if (elemento.contains("silver") || elemento.contains("gold") || elemento.contains("bronze")) {
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

        return 0; //Tudo válido
    }

    public void setInitialTeam(int teamId) {//Done----------------

        if (teamId == 10) { //Acho que funciona, no debug está bem
            // teamId = 10;
            tLDR.setEstado(true);
            tRST.setEstado(false);
        } else {
            //teamId = 20;
            tLDR.setEstado(false);
            tRST.setEstado(true);
        }
    }

    public void processTurn() {

        int encontrou = 0;
        boolean encontrouLDR = false;
        boolean encontrouRST = false;

        for (int i = 0; i < listaCreatures.size(); i++) {

            encontrou = listaCreatures.get(i).moveCriatura();

            if (encontrou == 1 || encontrou == 2 || encontrou == 3) {
                turn15GameOver = 0;
                for (int j = 0; j < listaTreasures.size(); j++) {
                    if (listaTreasures.get(j).getPosY() == listaCreatures.get(i).getPosY() && listaTreasures.get(j).getPosX() == listaCreatures.get(i).getPosX()) {
                        listaTreasures.remove(listaTreasures.get(j));
                    }
                }
                //pontuacaoTotal -= encontrou; //Serve para validar GameIsOver

                if (listaCreatures.get(i).getIdEquipa() == 10) {
                    tLDR.somaPontos(encontrou);
                    listaCreatures.get(i).somaPontos(encontrou); //NÃO TENHO A CERTEZA SE É PARA INCREMENTAR OU ATRIBUIR O VALOR
                    encontrouLDR = true; //Para saber se tenho de atribuir uma só moeda
                }
                if (listaCreatures.get(i).getIdEquipa() == 20) {
                    tRST.somaPontos(encontrou);
                    listaCreatures.get(i).somaPontos(encontrou); //NÃO TENHO A CERTEZA SE É PARA INCREMENTAR OU ATRIBUIR O VALOR
                    encontrouRST = true;//Para saber se tenho de atribuir uma só moeda
                }
            }
        }

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

        if (turn15GameOver == 15) {
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
        for (int i = 0; i < listaCreatures.size(); i++) {
            id = listaCreatures.get(i).getId();
            ouros = listaCreatures.get(i).getOuro();
            pratas = listaCreatures.get(i).getPrata();
            bronze = listaCreatures.get(i).getBronze();
            pontos = listaCreatures.get(i).getPontos();
            tipo = listaCreatures.get(i).getTipo();
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
        int calhou = random.nextInt(4 - 1) + 1;

        Map<String, Integer> mapa = new HashMap<String, Integer>();
/*
        if (calhou == 1) {
            mapa.put("Dragão", 2);
            mapa.put("Anão", 3);
            mapa.put("Humano", 2);
            mapa.put("Elfo", 2);
        }

        if (calhou == 2) {
            mapa.put("Dragão", 1);
            mapa.put("Anão", 3);
            mapa.put("Gigante", 3);
            mapa.put("Elfo", 2);
        }

        if (calhou == 3) {
            mapa.put("Dragão", 1);
            mapa.put("Gigante", 2);
            mapa.put("Anão", 3);
            mapa.put("Humano", 1);
            mapa.put("Elfo", 3);
        }*/

        mapa.put("Anão", 7);

        return mapa;
    }//Done---------

    public boolean enchant(int x, int y, String spellName) {
        //TODO: Aplicar efeito

        for (int i = 0; i < listaCreatures.size(); i++) {
            if (listaCreatures.get(i).posX == x && listaCreatures.get(i).posY == y) {
                listaCreatures.get(i).setFeitico(spellName);
            }
        }
        return true;
    }

    public String getSpell(int x, int y) { //Done-------
        for (int i = 0; i < listaCreatures.size(); i++) {
            if (listaCreatures.get(i).posX == x && listaCreatures.get(i).posY == y) {
                return listaCreatures.get(i).getFeitico();
            }
        }
        return null;
    }

    public int getCoinTotal(int teamID) {
        if (tLDR.getId() == teamID) {
            return tLDR.getPlafond();
        } else {
            return tRST.getPlafond();
        }
    }

    public boolean saveGame(File fich) {

        try {
            int idTemp;
            int posXTemp;
            int posyTemp;
            String tipo;
            int teamId;
            int ouro;
            int prata;
            int bronze;
            int pontos;
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
            gravarArq.println("pontos: " + tLDR.getTeamPontos() + ", plafound: " + tLDR.getPlafond() + ", estado: " + tLDR.getEstado() + ", id: " + tLDR.getId());
            gravarArq.println("pontos: " + tRST.getTeamPontos() + ", plafound: " + tRST.getPlafond() + ", estado: " + tRST.getEstado() + ", id: " + tRST.getId());

            gravarArq.close();
            return true;

        } catch (IOException e) {
            e.printStackTrace(); //Temporário
            return false;
        }
    }

    public boolean loadGame(File fich) {

        int idTemp = 0;
        String typeTemp = "";
        int xTemp = 0;
        int yTemp = 0;
        int teamIdTemp = 0;
        int tamanhoY = 0;
        int tamanhoX;
        int plafoundTemp = 0;
        int pontosTemp = 0;
        int ouro = 0;
        int prata = 0;
        int bronze = 0;
        boolean estado = true;
        String orientTemp = "Norte";

        try {
            FileReader arq = new FileReader(fich);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = lerArq.readLine();

            while (true) {
                System.out.printf("%s\n", linha);
                linha = lerArq.readLine();

                if (linha != null) {
                    if (linha.contains("my:")) {
                        tamanhoY = Integer.parseInt(linha.substring(4));
                    }
                    if (linha.contains("mx: ")) {
                        tamanhoX = Integer.parseInt(linha.substring(4));
                        mapStartGame = new int[tamanhoY][tamanhoX];
                    }

                    String[] dados = linha.split(Pattern.quote(","));

                    if (linha.contains("Anão") || linha.contains("Dragão") || linha.contains("Gigante") || linha.contains("Elfo") || linha.contains("Humano")) {
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
                            }else if (d.startsWith(" prata:")) {
                                prata = Integer.parseInt(d.substring(8));
                            }else if (d.startsWith(" bronze:")) {
                                bronze = Integer.parseInt(d.substring(9));
                            }else if (d.startsWith(" pontos:")) {
                                pontosTemp = Integer.parseInt(d.substring(9));
                            }
                        }

                        if (typeTemp.equals("Anão")) {
                            Anao anao = new Anao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                            listaCreatures.add(anao);
                        }
                        if (typeTemp.equals("Dragão")) {
                            Dragao dragao = new Dragao(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                            listaCreatures.add(dragao);
                        }
                        if (typeTemp.equals("Humano")) {//Falta criar na interface...
                            Humano humano = new Humano(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                            listaCreatures.add(humano);
                        }
                        if (typeTemp.equals("Elfo")) {//Falta criar na interface...
                            Elfo elfo = new Elfo(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                            listaCreatures.add(elfo);
                        }
                        if (typeTemp.equals("Gigante")) {//Falta criar na interface...
                            Gigante gigante = new Gigante(idTemp, teamIdTemp, typeTemp, xTemp, yTemp, Creature.Orientacao.valueOf(orientTemp), ouro, prata, bronze, pontosTemp);
                            listaCreatures.add(gigante);
                        }
                        mapStartGame[yTemp][xTemp] = 4;
                    }
                    if (linha.contains("silver") || linha.contains("gold") || linha.contains("bronze")) {
                        dados = linha.split(Pattern.quote(","));
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
                        }
                        if (typeTemp.equals("silver")) {
                            mapStartGame[yTemp][xTemp] = 2;
                        }
                        if (typeTemp.equals("bronze")) {
                            mapStartGame[yTemp][xTemp] = 1;
                        }
                        listaTreasures.add(tesouroTemp);
                    }
                    if (linha.contains("hole")) {
                        dados = linha.split(Pattern.quote(","));

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
                            if (idTemp == 10) {
                                tLDR.setPlafond(plafoundTemp);
                                tLDR.setPontosTeam(pontosTemp);
                                tLDR.setEstado(estado);
                            }
                            if (idTemp == 20) {
                                tRST.setPlafond(plafoundTemp);
                                tRST.setPontosTeam(pontosTemp);
                                tRST.setEstado(estado);
                            }
                        }
                    }
                } else {
                    break;
                }
            }
            arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
        System.out.println();
        return true;
    }

    public String whoIsLordEder() {
        return "Ederzito António Macedo Lopes";
    }
}
