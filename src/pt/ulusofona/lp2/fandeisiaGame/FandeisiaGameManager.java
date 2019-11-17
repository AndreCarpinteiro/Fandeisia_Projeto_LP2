
package pt.ulusofona.lp2.fandeisiaGame;
import pt.ulusofona.lp2.guiSimulator.DefaultTreasure;

import java.util.List;
import java.util.ArrayList;

public class FandeisiaGameManager {

    int[][] mapStartGame;

    List<Creature> listaCreatures = new ArrayList<>();
    List<DefaultTreasure> listaTreasures = new ArrayList<>();

    //--------------------Metodos Obrigratorios---------------------
    public String[][] getCreatureTypes() {//Done mas...-------------

         /*(As imagens a usar devem ser colocadas na pasta
         * src/images e devem ter tamanho 50x50. As imagens
         * devem ter fundo transparente para que se consiga
         * ver se estão num quadrado branco ou preto).
         *
         * Caso os alunos não pretendam definir
         * nenhuma imagem, a função pode
         * simplesmente retornar ​null​. Isto fará com
         * que o visualizador use uma imagem
         * pré-definida por omissão.
         */

        String[][] creatureTypeOptions = new String[3][4];

        creatureTypeOptions[0] = new String[]{"Skeleton", "skeleton.png", "Descrição textual", "5"};
        creatureTypeOptions[1] = new String[]{"Dwarf", "crazy_emoji_white.png", "Descrição textual", "10"};
        creatureTypeOptions[2] = new String[]{"Chimera", "simba.png", "Descrição textual", "15"};

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

        rows = 6;
        columns = 8;
        String typeTemp;
        int idTemp;
        int xTemp;
        int yTemp;
        for (String elemento : content) {
            if(elemento.contains("treasure")){
                //DefaultTreasure tesouroTemp = new DefaultTreasure(,,);
                String[] dados = elemento.split(" ");
                for (String d: dados) {
                    System.out.println("uma iteração");
                    String[] dados2 = d.split(",");
                    //if (dados2[0].equals("id:")) {
                    //    idTemp = Integer.parseInt(dados2[1]);
                    //}
                    int k = 0 ;
                    for (String d2:dados2) {
                        System.out.println(d2+ "é " + k);
                        k++;
                    }
                }
                //int idAdd = elemento.
                //listaTreasures.add()
            }
        }

        /* Deve inicializar as estruturas de dados
         * relevantes para processar um jogo.
         * O ​array content​ irá descrever o conteúdo inicial do mundo
         * (criaturas e tesouros), tendo para isso várias Strings.
         *
         * Cada String vai representar um objecto do
         * mundo. As Strings vão ter um dos
         * seguintes formatos:
         *
         * Para criaturas:
         * “id: <id>, type: <type>,teamId: <teamId>,
         * x: <x>, y:<y>, orientation: <orientation>”
         *
         * Para tesouros:
         * “id: <id>, type: treasure, x:<x>, y: <y>”
         *
         * Os argumentos ​rows e columns vão-nos indicar
         * as dimensões do tabuleiro.
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

    }

    public List<Creature> getCreatures() {

        List<Creature> mylist = new ArrayList<>();


        /* Devolve uma lista com todos os objectos
         * "Creature" ​que existem no jogo.
         * */
        return mylist;
    }


    public boolean gameIsOver() {


        /*Deve devolver ​true​ caso já tenha sido
         * alcançada uma das condições de paragem
         * do jogo e ​false​ em caso contrário.
         * */
        return true;
    }

    public List<String> getAuthors() {//Done----------------

        /*Devolve uma lista de ​Strings​ com os
         * nomes dos autores do projecto.
         * Esta informação será usada para mostrar o
         * conteúdo da janela que aparece ao
         * carregar no botão de “Créditos”.
         */
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

    public int getElementId(int x, int y) {


        /*for(int i = 0;i < mapStartGame.length;i++){
            for(int j = 0;i < mapStartGame[i].length;j++){
                if (x == i && y == j){

                }
            }
        }*/

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
        return 1;
    }

    public int getCurrentTeamId() {
        /*Deve devolver o ​ID​ da ​equipa​ que está
         * activa​ no turno actual.  */
        return 0;
    }

    public int getCurrentScore(int teamID) {

        /*Deve devolver o número actual de pontos
         * da equipa que tem o ID teamID. */
        return 1;
    }

//--------------------Metodos Nao Obrigratorios--------------------------

    //public String getIcon(String iconName) {
        /*Deve devolver o icon??? do que? nao sei. */
      //  return "bird.png";
    //}
}
