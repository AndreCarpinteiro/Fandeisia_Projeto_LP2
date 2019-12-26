package pt.ulusofona.lp2.fandeisiaGame;

public class Anao extends Creature {

    Anao(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, posX, posY, orient);
        this.custo = 1;
    }

    @Override //Done--------------
    public int moveCriatura() {

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        int encontrou = 0;

        if (orientacao == Orientacao.Norte) {
            if (posY == 0 || mapa[posY - 1][posX] == 4 || mapa[posY - 1][posX] == 5) {
                orientacao = Orientacao.Este;
            }else {
                if (mapa[posY - 1][posX] == 1 || mapa[posY - 1][posX] == 2 || mapa[posY - 1][posX] == 3) {
                    encontrou = mapa[posY - 1][posX];
                }
                mapa[posY][posX] = 0;
                posY--;
                mapa[posY][posX] = 4;
            }
        } else if (orientacao == Orientacao.Sul) {
            if (posY == yMax || mapa[posY + 1][posX] == 4 || mapa[posY - 1][posX] == 5) {
                orientacao = Orientacao.Oeste;
            }else {
                if (mapa[posY + 1][posX] == 1 || mapa[posY + 1][posX] == 2 || mapa[posY + 1][posX] == 3) {
                    encontrou = mapa[posY + 1][posX];
                }
                mapa[posY][posX] = 0;
                posY++;
                mapa[posY][posX] = 4;
            }
        } else if (orientacao == Orientacao.Oeste) {
            if (posX == 0 || mapa[posY][posX - 1] == 4 || mapa[posY - 1][posX] == 5) {
                orientacao = Orientacao.Norte;
            }else {
                if (mapa[posY][posX - 1] == 1 || mapa[posY][posX - 1] == 2 || mapa[posY][posX - 1] == 3) {
                    encontrou = mapa[posY][posX - 1];
                }
                mapa[posY][posX] = 0;
                posX--;
                mapa[posY][posX] = 4;
            }
        } else if (orientacao == Orientacao.Este) {
            if (posX == xMax || mapa[posY][posX + 1] == 4 || mapa[posY - 1][posX] == 5) {
                orientacao = Orientacao.Sul;
            }else {
                if (mapa[posY][posX + 1] == 1 || mapa[posY][posX + 1] == 2 || mapa[posY][posX + 1] == 3) {
                    encontrou = mapa[posY - 1][posX];
                }
                mapa[posY][posX] = 0;
                posX++;
                mapa[posY][posX] = 4;
            }
        }
        return encontrou;
    }
}
