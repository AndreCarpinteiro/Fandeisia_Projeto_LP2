
package pt.ulusofona.lp2.fandeisiaGame;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class FandeisiaGameManager {

    static int[][] mapStartGame;
    private int countTurnos;
    private int turn15GameOver;
    private int tesourosTotais;

    private Team tLDR;
    private Team tRST;

    private File save;

    private List<Creature> listaCreatures = new ArrayList<>();
    private List<Tesouro> listaTreasures = new ArrayList<>();

    //--------------------Metodos Obrigratorios---------------------

    public String[][] getCreatureTypes() {//Done mas...-------------

        String[][] creatureTypeOptions = new String[4][4];

        creatureTypeOptions[0] = new String[]{"Skeleton", "skeleton.png", "Lança flechas", "2"};
        creatureTypeOptions[1] = new String[]{"Dwarf", "dwarf.png", "Dá cabeçadas", "3"};
        creatureTypeOptions[2] = new String[]{"Chimera", "chimera.png", "Morde cenas", "1"};
        creatureTypeOptions[3] = new String[]{"Super Dragão", "super_dragon.png", "Comandado pelo Macaco Lider", "5"};

        return creatureTypeOptions;
    }

    public int startGame(String[] content, int rows, int columns) {

        //TODO: buracos, cenas..

        tLDR = new Team(10, 0);
        tRST = new Team(20, 0);
        listaCreatures.clear();
        listaTreasures.clear();
        countTurnos = 0;
        turn15GameOver = 0;
        tesourosTotais = 0;

        tLDR.setPontos(0);
        tRST.setPontos(0);

        mapStartGame = new int[rows][columns];//vamos usar isto nas outras funcoes

        for (int i = 0; i < mapStartGame.length; i++) {
            for (int j = 0; j < mapStartGame[i].length; j++) {
                mapStartGame[i][j] = 0;
            }
        }

        int idTemp;
        String typeTemp = "";
        int xTemp = 0;
        int yTemp = 0;
        int custoTotal = 0;

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
              //  System.out.println(tesouroTemp.toString());
                listaTreasures.add(tesouroTemp);
                    if (typeTemp.equals("silver")){
                        mapStartGame[yTemp][xTemp] = 3;
                    }
                if (typeTemp.equals("gold")){
                    mapStartGame[yTemp][xTemp] = 4;
                }
                if (typeTemp.equals("bronze")){
                    mapStartGame[yTemp][xTemp] = 2;
                }

            } else {
                Creature creatureTemp = new Creature() {
                    @Override//Temporário
                    public boolean moveCriatura() {
                        return false;
                    }
                };

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
            //    System.out.println(creatureTemp.toString());
                listaCreatures.add(creatureTemp);
                mapStartGame[yTemp][xTemp] = 1;
            }
        }
        tesourosTotais = listaTreasures.size();

        //Validar plafond
        for(int i = 0; i < listaCreatures.size(); i++){
            custoTotal += listaCreatures.get(i).getCusto();

            if(custoTotal > tLDR.getPlafond() && custoTotal > tRST.getPlafond()){
                return 1;
            }
            if(custoTotal > tLDR.getPlafond()){
                return 2;
            }
            if(custoTotal > tRST.getPlafond()){
                return 3;
            }
        }


        return 0; //Tudo válido
    }

    public void setInitialTeam(int teamId) {//Done----------------
        Random random = new Random();
        teamId = random.nextInt(3-1) + 1;

        if(teamId == 1) { //Acho que funciona, no debug está bem
            teamId = 10;
            tLDR.setEstado(true);
            tRST.setEstado(false);
        }else {
            teamId = 20;
            tLDR.setEstado(false);
            tRST.setEstado(true);
        }
    }

    public void processTurn() {

        boolean encontrou;

        for (int i = 0; i < listaCreatures.size(); i++) {

                encontrou = listaCreatures.get(i).moveCriatura();

            if (encontrou) {
                turn15GameOver = 0;
                for (int j = 0; j < listaTreasures.size(); j++) {
                    if (listaTreasures.get(j).getPosY() == listaCreatures.get(i).getPosY() && listaTreasures.get(j).getPosX() == listaCreatures.get(i).getPosX()) {
                        listaTreasures.remove(listaTreasures.get(j));
                    }
                }

                if (listaCreatures.get(i).getIdEquipa() == 0) {
                    tLDR.somaPontos();
                    //pontosLDR++;
                } else {
                    tRST.somaPontos();
                    //pontosRST++;
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
    }

    public List<Creature> getCreatures() {//Quase Done--------------
        return listaCreatures;
    }

    public boolean gameIsOver() {

        int conta = tesourosTotais / 2 + 1;

        if (tLDR.getTeamPontos() >= conta) {
            return true; }

        if (tRST.getTeamPontos() >= conta) {
            return true; }

        if (listaTreasures.size() == 0) {
            return true; }

        if (turn15GameOver == 15) {
            return true; }

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

        if (tLDR.getTeamPontos() == tRST.getTeamPontos()) {
            resultado.add("Resultado: EMPATE");
            resultado.add("LDR: " + tLDR.getTeamPontos());
            resultado.add("RESISTENCIA: " + tRST.getTeamPontos());
            resultado.add("Nr. de Turnos jogados: " + countTurnos);
            resultado.add("-----");
            for (int i = 0; i < listaCreatures.size(); i++) {
                resultado.add(listaCreatures.get(i).getId() + " : " + listaCreatures.get(i).getTipo() + " : " + listaCreatures.get(i).getPontos());
            }
        }

        if (tLDR.getTeamPontos() > tRST.getTeamPontos()) {
            resultado.add("Resultado: Vitória da equipa LDR");
            resultado.add("LDR: " + tLDR.getTeamPontos());
            resultado.add("RESISTENCIA: " + tRST.getTeamPontos());
            resultado.add("Nr. de Turnos jogados: " + countTurnos);
            resultado.add("-----");
            for (int i = 0; i < listaCreatures.size(); i++) {
                resultado.add(listaCreatures.get(i).getId() + " : " + listaCreatures.get(i).getTipo() + " : " + listaCreatures.get(i).getPontos());
            }
        }

        if (tLDR.getTeamPontos() < tRST.getTeamPontos()) {
            resultado.add("Resultado: Vitória da equipa RESISTENCIA");
            resultado.add("RESISTENCIA: " + tRST.getTeamPontos());
            resultado.add("LDR: " + tLDR.getTeamPontos());
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

        if (tLDR.getEstado()) {
            return 10; //mudei!!!!
        } else if (tRST.getEstado()) {
            return 20;
        }
        return 2;
    }

    public int getCurrentScore(int teamID) {

        if (0 == teamID) {
            return tLDR.getTeamPontos();

        } else {
            return tRST.getTeamPontos();
        }
    }

    public String[][] getSpellTypes(){
        String[][] spell = new String[10][10];

        spell[0] = new String[]{"Força", "Fica bue bruto", "2"};
        spell[1] = new String[]{"Velocidade", "Bue rápido sócio", "1"};
        spell[2] = new String[]{"Água Benta", "Lá di bairro", "3"};

        return spell;
    }

    public Map<String, Integer> createComputerArmy(){

        Map<String, Integer> mapa = new HashMap<String, Integer>();
        mapa.put("Super Dragão", 2);
        mapa.put("Skeleton", 1);

        return mapa;
    }

    public boolean enchant(int x, int y, String spellName){
      //TODO:
        return true;
    }

    public String getSpell(int x, int y){
        //TODO:
        return "";
    }

    public int getCoinTotal(int teamID){
        if(tLDR.idTeam == teamID){
            return tLDR.getPlafond();
        }else{
            tRST.getPlafond();
        }
        return 2;
    }

    public boolean saveGame(File fich){
        //TODO:
        return true;
    }

    public boolean loadGame(File fich){
        //TODO:
        return true;
    }

    public String whoIsLordEder(){
        //TODO:
        return "Éderzito António Macedo Lopes";
    }
}
