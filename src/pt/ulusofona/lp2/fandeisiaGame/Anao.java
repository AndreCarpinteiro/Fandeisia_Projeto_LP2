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
            if(posY - 1 > 0) {
                if (mapa[posY - 1][posX] != 1 || mapa[posY - 1][posX] != 5) {
                    if (mapa[posY - 1][posX] == 1 || mapa[posY - 1][posX] == 2 || mapa[posY - 1][posX] == 3) {
                        encontrou = mapa[posY - 1][posX];
                    }
                    mapa[posY][posX] = 0;
                    posY -= 1;
                    mapa[posY][posX] = 4;
                }
                orientacao = Orientacao.Este;
            }
            orientacao = Orientacao.Este;
        }
        if (orientacao == Orientacao.Este) {
            if(posX + 1 < xMax) {
                if (mapa[posY][posX + 1] != 1 || mapa[posY][posX + 1] != 5) {
                    if (mapa[posY][posX + 1] == 1 || mapa[posY][posX + 1] == 2 || mapa[posY][posX + 1] == 3) {
                        encontrou = mapa[posY][posX + 1];
                    }
                    mapa[posY][posX] = 0;
                    posX += 1;
                    mapa[posY][posX] = 4;
                }
                orientacao = Orientacao.Sul;
            }
            orientacao = Orientacao.Sul;
        }
        if (orientacao == Orientacao.Sul) {
            if(posY + 1 < yMax) {
                if (mapa[posY + 1][posX] != 1 || mapa[posY + 1][posX] != 5) {
                    if (mapa[posY + 1][posX] == 1 || mapa[posY + 1][posX] == 2 || mapa[posY + 1][posX] == 3) {
                        encontrou = mapa[posY + 2][posX];
                    }
                    mapa[posY][posX] = 0;
                    posY += 1;
                    mapa[posY][posX] = 4;
                }
                orientacao = Orientacao.Oeste;
            }
            orientacao = Orientacao.Oeste;
        }
        if (orientacao == Orientacao.Oeste) {
            if(posX - 1 > 0) {
                if (mapa[posY][posX - 1] != 1 || mapa[posY][posX - 1] != 5) {
                    if (mapa[posY][posX - 1] == 1 || mapa[posY][posX - 1] == 2 || mapa[posY][posX - 1] == 3) {
                        encontrou = mapa[posY][posX - 2];
                    }
                    mapa[posY][posX] = 0;
                    posX -= 1;
                    mapa[posY][posX] = 4;
                }
                orientacao = Orientacao.Norte;
            }
            orientacao = Orientacao.Norte;
        }
        return encontrou;
    }
}
