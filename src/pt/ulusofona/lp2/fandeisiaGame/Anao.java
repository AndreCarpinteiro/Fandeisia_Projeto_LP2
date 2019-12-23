package pt.ulusofona.lp2.fandeisiaGame;

public class Anao extends Creature {

    Anao(int id, int idEquipa, String tipo, int pontos, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, pontos, posX, posY, orient);
        this.custo = 2;
    }

    @Override //Falta contagem tesouros....
    public boolean moveCriatura() {

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        boolean encontrou = false;

        //System.out.println("rows" + yMax);
        //System.out.println("columns" + xMax);

        if (orientacao == Orientacao.Norte) {
            if (posY == 0 || mapa[posY - 1][posX] == 1) {
                orientacao = Orientacao.Este;
            }
            mapa[posY][posX] = 0;
            posY--;
            mapa[posY][posX] = 1;
            if (mapa[posY - 1][posX] == 2) {
                pontos++;
                encontrou = true;
            }
        } else if (orientacao == Orientacao.Sul) {
            if (posY == yMax || mapa[posY + 1][posX] == 1) {
                orientacao = Orientacao.Oeste;
            }
            mapa[posY][posX] = 0;
            posY++;
            mapa[posY][posX] = 1;
            if (mapa[posY + 1][posX] == 2) {
                pontos++;
                encontrou = true;
            }
        } else if (orientacao == Orientacao.Oeste) {
            if (posX == 0 || mapa[posY][posX - 1] == 1) {
                orientacao = Orientacao.Norte;
            }
            mapa[posY][posX] = 0;
            posX--;
            mapa[posY][posX] = 1;
            if (mapa[posY][posX - 1] == 2) {
                pontos++;
                encontrou = true;
            }
        } else if (orientacao == Orientacao.Este) {
            if (posX == xMax || mapa[posY][posX + 1] == 1) {
                orientacao = Orientacao.Sul;
            }
            mapa[posY][posX] = 0;
            posX++;
            mapa[posY][posX] = 1;
            if (mapa[posY][posX + 1] == 2) {
                pontos++;
                encontrou = true;
            }
        }
        return encontrou;
    }
}