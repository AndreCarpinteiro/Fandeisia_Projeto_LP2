
package pt.ulusofona.lp2.fandeisiaGame;

import javafx.stage.Screen;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class FandeisiaGameManager {

    int[][] mapStartGame;
    int countTurnos;

    Team LDR = new Team(0,0,true);
    Team RST = new Team(1,0,false);

    List<Creature> listaCreatures = new ArrayList<>();
    List<Tesouro> listaTreasures = new ArrayList<>();

    //--------------------Metodos Obrigratorios---------------------
    public String[][] getCreatureTypes() {//Done mas...-------------

         /*(As imagens a usar devem ser colocadas na pasta
         * src/images e devem ter tamanho 50x50. As imagens
         * devem ter fundo transparente para que se consiga
         * ver se estão num quadrado branco ou preto).
         */

        String[][] creatureTypeOptions = new String[4][4];

        creatureTypeOptions[0] = new String[]{"Skeleton", "skeleton.png", "Descrição textual", "5"};
        creatureTypeOptions[1] = new String[]{"Dwarf", "crazy_emoji_white.png", "Descrição textual", "10"};
        creatureTypeOptions[2] = new String[]{"Chimera", "simba.png", "Descrição textual", "15"};
        creatureTypeOptions[3] = new String[]{"Super Dragão", "crazy_emoji_black.png", "Descrição textual", "11"};

        /*
        * Deve retornar os tipos de criatura que
        * existem no jogo e que podem ser
        * escolhidos para os exércitos dos dois
        * jogadores.
        *
        * O retorno deve ser um ​array 2d de
        * String​, em que cada “linha” tem a
        * seguinte informação:
        * -Nome do tipo (posição 0)
        * -Imagem PNG (posição 1)
        * -Descrição textual (posição 2)
        * -Custo (posição 3) (O valor do “Custo” não será
        * usado nesta P1).
        */
        return creatureTypeOptions;
    }

    public void startGame(String[] content, int rows, int columns) {

        //TODO NAO SEI O PROPOSITO ESPECIFICO DOS PARAMETROS ROWS E COLLUMNS

        mapStartGame = new int[rows][columns];//vamos usar isto nas outras funcoes

        int idTemp;
        String typeTemp;
        int xTemp;
        int yTemp;

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
            }
        }

        /* Deve inicializar as estruturas de dados
         * relevantes para processar um jogo.
         * O ​array content​ irá descrever o conteúdo inicial do mundo
         * (criaturas e tesouros), tendo para isso várias Strings.
         *
         * Cada String vai representar um objecto do
         * mundo. As Strings vão ter um dos seguintes formatos.
         * Para criaturas:
         * “id: <id>, type: <type>,teamId: <teamId>, x: <x>, y:<y>, orientation: <orientation>”
         * Para tesouros:
         * “id: <id>, type: treasure, x: <x>, y: <y>”
         * Os argumentos ​rows e columns vão-nos indicar as dimensões do tabuleiro.
         * */
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
                System.out.println("IDDDDD " + listaCreatures.get(i).id);
                listaCreatures.get(i).moveCriatura(listaCreatures.get(i).orientacao.toString());

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
            return true;
        }

        return true;
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
        if(LDR.ativo){
            return 0;
        }else if(RST.ativo){
            return 1;
        }
        return 2;
    }

    public int getCurrentScore(int teamID) {///Done(mas falta atualizar os pontos no processTurn ou noutra qualquer)---
        /*Deve devolver o número actual de pontos
         * da equipa que tem o ID teamID. */
        if(LDR.idTeam == teamID){
            return LDR.pontosTeam;
        }else{
            return RST.pontosTeam;
        }
    }

//--------------------Metodos Nao Obrigratorios--------------------------


    public int[][] getMapa(){ //Devolve o mapa para ter acesso em outras classes
        return mapStartGame;
    }

    //public String getIcon(String iconName) {
        /*Deve devolver o icon??? do que? nao sei. */
      //  return "bird.png";
    //}
}
