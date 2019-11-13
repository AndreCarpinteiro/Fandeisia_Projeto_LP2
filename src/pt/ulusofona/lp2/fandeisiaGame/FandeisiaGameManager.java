package pt.ulusofona.lp2.fandeisiaGame;

import java.util.List;

public class FandeisiaGameManager {

    FandeisiaGameManager() {


        //OLAAAAAAAAAAAA
    }

    public String[][] getCreatureTypes() {

        String texto = "32132131321312";

        String[][] array2dtemporario = new String[5][5];


        int x = 4;

        x = 3;
        x=2;
        /*
        Deve retornar os tipos de criatura que
        existem no jogo e que podem ser
        escolhidos para os exércitos dos dois
        jogadores.

        O retorno deve ser um ​array 2d de
        String​, em que cada “linha” tem a
        seguinte informação:
        -Nome do tipo (posição 0)
        -Imagem PNG (posição 1)
        -Descrição textual (posição 2)
        -Custo (posição 3) (O valor do “Custo” não será
         usado nesta P1).
         */
        return array2dtemporario;
    }

    public void startGame(String[] content, int rows, int columns) {
        /*
         * Deve inicializar as estruturas de dados
         * relevantes para processar um jogo.
         * O ​array content​ irá descrever o conteúdo inicial do mundo (criaturas e
         * tesouros), tendo para isso várias Strings.
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
         * as dimensões dotabuleiro.
         * */
    }

    public void setInitialTeam(int teamId){
        /*Indica qual das equipas vai jogar no
         * primeiro turno do jogo. */
    }

    public void processTurn(){
        /*Deve processar um turno do jogo considerando a equipa actual.
         * Inclui o movimento das criaturas. */
    }

    public List<Creatures> getCreatures(){
        Creatures criaturasTemp = new List<>();
        /*Devolve uma lista com todos os objectos
         * Creature ​que existem no jogo.
         * */
    }


    public boolean gameIsOver(){
        /*Deve devolver ​true​ caso já tenha sido
         * alcançada uma das condições de paragem
         * do jogo e ​false​ em caso contrário.
         * */
    }

    public List<String> getAuthors(){
        /*Devolve uma lista de ​Strings​ com os
         * nomes dos autores do projecto.
         * Esta informação será usada para mostrar o
         * conteúdo da janela que aparece ao
         * carregar no botão de “Créditos”.
         */
    }

    public List<String> getResults(){
        /*Devolve uma lista de ​Strings​ que
         * representem os resultados do jogo,
         * conforme descrito na secção dos
         * “Resultados da execução …”.
         *
         * Este método não pode devolver ​null​.
         * Caso não calculem a informação
         * respectiva, devem devolver ​uma lista vazia​.
         * */
    }

    public int getElementId(int x, int y){
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
    }

    public int getCurrentTeamId(){
        /*Deve devolver o ​ID​ da ​equipa​ que está
         * activa​ no turno actual.  */
    }

    public int getCurrentScore(int teamID){
        /*Deve devolver o número actual de pontos
         * da equipa que tem o ID teamID. */
    }
}
