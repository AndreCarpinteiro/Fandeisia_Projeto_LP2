
package pt.ulusofona.lp2.fandeisiaGame;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class FandeisiaGameManager {

    int[][] mapStartGame;
    int countTurnos;

    Team tLDR = new Team(0,0,true);
    Team tRST = new Team(1,0,false);

    List<Creature> listaCreatures = new ArrayList<>();
    List<Tesouro> listaTreasures = new ArrayList<>();

    //--------------------Metodos Obrigratorios---------------------
    public String[][] getCreatureTypes() {//Done mas....-------------

        String[][] creatureTypeOptions = new String[4][4];

        creatureTypeOptions[0] = new String[]{"Skeleton", "skeleton.png", "Descrição textual", "5"};
        creatureTypeOptions[1] = new String[]{"Dwarf", "crazy_emoji_white.png", "Dá cabeçadas", "10"};
        creatureTypeOptions[2] = new String[]{"Chimera", "chimera.png", "Morde cenas", "15"};
        creatureTypeOptions[3] = new String[]{"Super Dragão", "super_dragonn.png", "Comandado pelo Macaco Lider", "11"};

        return creatureTypeOptions;
    }

    public void startGame(String[] content, int rows, int columns) {

        //TODO NAO SEI O PROPOSITO ESPECIFICO DOS PARAMETROS ROWS E COLLUMNS

        mapStartGame = new int[rows][columns];//vamos usar isto nas outras funcoes
        //rows = y
        // columns = x
        int idTemp;
        String typeTemp;
        int xTemp = 0;
        int yTemp = 0;

        int teamIdTemp;
        String orientTemp;
        //Dissecação do parametro "content" para objetos creatures e treasures----
        for (String elemento : content) {

            if(elemento.contains("treasure")){
                Tesouro tesouroTemp = new Tesouro();

                String[] dados = elemento.split(",");

                for (String d: dados) {
                    if (d.startsWith("id:")) {
                        idTemp = Integer.parseInt(d.substring(4));
                        tesouroTemp.id = idTemp;

                    } else if (d.startsWith(" type:")) {
                        typeTemp = d.substring(7);
                        tesouroTemp.tipo = typeTemp;

                    }else if(d.startsWith(" x:")){
                        xTemp = Integer.parseInt(d.substring(4));
                        tesouroTemp.posX = xTemp;

                    }else if(d.startsWith(" y:")){
                        yTemp = Integer.parseInt(d.substring(4));
                        tesouroTemp.posY = yTemp;
                    }
                }
                System.out.println(tesouroTemp.toString());
                listaTreasures.add(tesouroTemp);
                mapStartGame[yTemp][xTemp] = 2;

            }else{
                Creature creatureTemp = new Creature();

                String[] dados = elemento.split(",");

                for (String d: dados) {
                    if (d.startsWith("id:")) {
                        idTemp = Integer.parseInt(d.substring(4));
                        creatureTemp.id = idTemp;

                    } else if (d.startsWith(" type:")) {
                        typeTemp = d.substring(7);
                        creatureTemp.tipo = typeTemp;

                    } else if (d.startsWith(" teamId:")) {
                        teamIdTemp = Integer.parseInt(d.substring(9));
                        creatureTemp.idEquipa = teamIdTemp;

                    }else if(d.startsWith(" x:")){
                        xTemp = Integer.parseInt(d.substring(4));
                        creatureTemp.posX = xTemp;

                    }else if(d.startsWith(" y:")){
                        yTemp = Integer.parseInt(d.substring(4));
                        creatureTemp.posY = yTemp;

                    }else if(d.startsWith(" orientation:")){
                        orientTemp = d.substring(14);
                        creatureTemp.orientacao = Creature.Orientacao.valueOf(orientTemp);
                    }
                }
                System.out.println(creatureTemp.toString());
                listaCreatures.add(creatureTemp);
                mapStartGame[yTemp][xTemp] = 1;
            }
        }

        for(int i = 0 ; i < mapStartGame.length; i++){
            for(int j = 0; j < mapStartGame[i].length; j++){
                System.out.print(mapStartGame[i][j] + " ");
            }
            System.out.println();
        }
        //TODO o que é isto ---> mapStartGame[xTemp][yTemp] = 1;

    }

    public void setInitialTeam(int teamId) {//Done----------------
        /* Indica qual das equipas vai jogar no
         * primeiro turno do jogo. */

        teamId = 0;//É sempre a equipa LDR, como indicado no enunciado
    }

    public void processTurn() {
        /* Deve processar um turno do jogo considerando a equipa actual.
         * Inclui o movimento das criaturas. */

        int aux;

        if(getCurrentTeamId() == 0){

            //Ordenar IDs
            for(int i=0; i < listaCreatures.size(); i++){
               // for (int j = 0; j < listaCreatures.size() - 1; j++) {
                 //   if (listaCreatures.get(j).getId() > listaCreatures.get(j+1).getId()) {
                   //     aux = listaCreatures.get(j).getId();
                     //   listaCreatures.get(j).id = listaCreatures.get(j+1).getId();
                       // listaCreatures.get(j+1).id = aux;
                    //}
               // }
                System.out.println("Id a mover " + listaCreatures.get(i).id);
                listaCreatures.get(i).moveCriatura( mapStartGame);



            }


        }

    }

    public List<Creature> getCreatures() {//Quase Done--------------------------
                                          /*TODO esta incompleto, falta as criaturas
                                           *TODO do adversario */
        /* Devolve uma lista com todos os objectos
         * "Creature" ​que existem no jogo.
         * */
        return listaCreatures;
    }


    public boolean gameIsOver() {
        /*Deve devolver ​true​ caso já tenha sido
         * alcançada uma das condições de paragem
         * do jogo e ​false​ em caso contrário.
         * */

        if(listaTreasures.size() == 0) { //não está completo
            return false;
        }

        return false;
    }

    public List<String> getAuthors() {//Done----------------

        List<String> mylist = new ArrayList<>();

        mylist.add("André Carpinteiro");
        mylist.add("David Silva");

        return mylist;
    }

    public List<String> getResults() {

        List<String> mylist = new ArrayList<String>();
        /* Devolve uma lista de ​Strings​ que
         * representem os resultados do jogo,
         * conforme descrito na secção dos
         * “Resultados da execução …”.
         *
         * Este método não pode devolver ​null​.
         * Caso não calculem a informação
         * respectiva, devem devolver ​uma lista vazia​.
         * */

        return mylist;
    }

    public int getElementId(int x, int y) {//Done-------------------

        for (Creature creatureTemp : listaCreatures) {
            if(creatureTemp.posX == x && creatureTemp.posY == y){
                return creatureTemp.id;
            }
        }

        for (Tesouro tesouroTemp : listaTreasures){
            if(tesouroTemp.posX == x && tesouroTemp.posY == y){
                return tesouroTemp.id;
            }
        }
         /*Deve devolver o ID do objecto/elemento
         * que se encontra na posição indicada pelas
         * coordenadas (x,y) passadas por
         * argumento.
         *
         * Por objecto/elemento entende-se: criatura ou tesouro.
         * Caso não exista nenhuma criatura ou
         * tesouro na posição indicada, o método
         * deve devolver o valor 0 (zero) que representa o vazio.
         */
        return 0;
    }

    public int getCurrentTeamId() {//Done(mas falta mudar a equipa ativa no processTurn ou noutra qualquer)------
        /*Deve devolver o ​ID​ da ​equipa​ que está
         * activa​ no turno actual.  */
        if(tLDR.ativo){
            return 0;
        }else if(tRST.ativo){
            return 1;
        }
        return 2;
    }

    public int getCurrentScore(int teamID) {///Done(mas falta atualizar os pontos no processTurn ou noutra qualquer)---
        /*Deve devolver o número actual de pontos
         * da equipa que tem o ID teamID. */
        if(tLDR.idTeam == teamID){
            return tLDR.pontosTeam;
        }else{
            return tRST.pontosTeam;
        }
    }

//--------------------Metodos Nao Obrigratorios--------------------------


    public int[][] getMapa(){ //Devolve o mapa para ter acesso em outras classes
        return mapStartGame;
    }

    public List<Tesouro> getTesourosLista(){
                //Devolve o lista tesouros para ter acesso em outras classes
        return listaTreasures;
    }
    //public String getIcon(String iconName) {
        /*Deve devolver o icon??? do que? nao sei. */
      //  return "bird.png";
    //}
}
