package pt.ulusofona.lp2.fandeisiaGame;

public class Dragao extends Creature {

    Dragao(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, posX, posY, orient);
        this.custo = 9;
    }

    @Override
    public int moveCriatura() {//Done------------

        //x Horizontal
        //y vertical

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        int encontrou = 0;

        if (orientacao == Orientacao.Norte && (posY - 3) > 0) {
            if (mapa[posY - 3][posX] != 1 || mapa[posY - 3][posX] != 5) {
                if (mapa[posY - 3][posX] == 1 || mapa[posY - 3][posX] == 2 || mapa[posY - 3][posX] == 3) {
                    encontrou = mapa[posY - 3][posX];
                }
                mapa[posY][posX] = 0;
                posY -= 3;
                mapa[posY][posX] = 4;
            }
            orientacao = Orientacao.Nordeste;
        }
        if (orientacao == Orientacao.Nordeste && (posY - 3) > 0 && (posX + 3) < xMax) {
            if (mapa[posY - 3][posX + 3] != 1 || mapa[posY - 3][posX + 3] != 5) {
                if (mapa[posY - 3][posX + 3] == 1 || mapa[posY - 3][posX + 3] == 2 || mapa[posY - 3][posX + 3] == 3) {
                    encontrou = mapa[posY - 3][posX + 3];
                }
                mapa[posY][posX] = 0;
                posY -= 3;
                posX += 3;
                mapa[posY][posX] = 4;
            }
            orientacao = Orientacao.Este;
        }
        if (orientacao == Orientacao.Este && (posX + 3) < xMax) {
            if (mapa[posY][posX + 3] != 1 || mapa[posY][posX + 3] != 5) {
                if (mapa[posY][posX + 3] == 1 || mapa[posY][posX + 3] == 2 || mapa[posY][posX + 3] == 3) {
                    encontrou = mapa[posY][posX + 3];
                }
                mapa[posY][posX] = 0;
                posX += 3;
                mapa[posY][posX] = 4;
            }
            orientacao = Orientacao.Sudeste;
        }
        if (orientacao == Orientacao.Sudeste && (posY + 3) < yMax && (posX + 3) < xMax) {
            if (mapa[posY + 3][posX + 3] != 1 || mapa[posY + 3][posX + 3] != 5) {
                if (mapa[posY + 3][posX + 3] == 1 || mapa[posY + 3][posX + 3] == 2 || mapa[posY + 3][posX + 3] == 3) {
                    encontrou = mapa[posY + 3][posX + 3];
                }
                mapa[posY][posX] = 0;
                posX += 3;
                posY += 3;
                mapa[posY][posX] = 4;
            }
            orientacao = Orientacao.Sul;
        }
        if (orientacao == Orientacao.Sul && (posY + 3) < yMax) {
            if (mapa[posY + 3][posX] != 1 || mapa[posY + 3][posX] != 5) {
                if (mapa[posY + 3][posX] == 1 || mapa[posY + 3][posX] == 2 || mapa[posY + 3][posX] == 3) {
                    encontrou = mapa[posY + 3][posX];
                }
                mapa[posY][posX] = 0;
                posY += 3;
                mapa[posY][posX] = 4;
            }
            orientacao = Orientacao.Sudeste;
        }
        if (orientacao == Orientacao.Sudoeste && (posY + 3) < yMax && (posX - 3) > 0) {
            if (mapa[posY + 3][posX - 3] != 1 || mapa[posY + 3][posX - 3] != 5) {
                if (mapa[posY + 3][posX - 3] == 1 || mapa[posY + 3][posX - 3] == 2 || mapa[posY + 3][posX - 3] == 3) {
                    encontrou = mapa[posY + 3][posX - 3];
                }
                mapa[posY][posX] = 0;
                posY += 3;
                posX -= 3;
                mapa[posY][posX] = 4;
            }
            orientacao = Orientacao.Oeste;
        }
        if (orientacao == Orientacao.Oeste && (posX - 3) > 0) {
            if (mapa[posY][posX - 3] != 1 || mapa[posY][posX - 3] != 5) {
                if (mapa[posY][posX - 3] == 1 || mapa[posY][posX - 3] == 2 || mapa[posY][posX - 3] == 3) {
                    encontrou = mapa[posY][posX - 3];
                }
                mapa[posY][posX] = 0;
                posX -= 3;
                mapa[posY][posX] = 4;
            }
            orientacao = Orientacao.Noroeste;
        }
        if (orientacao == Orientacao.Noroeste && (posY - 3) > 0 && (posX - 3) > 0) {
            if (mapa[posY - 3][posX - 3] != 1 || mapa[posY - 3][posX - 3] != 5) {
                if (mapa[posY - 3][posX - 3] == 1 || mapa[posY - 3][posX - 3] == 2 || mapa[posY - 3][posX - 3] == 3) {
                    encontrou = mapa[posY - 3][posX - 3];
                }
                mapa[posY][posX] = 0;
                posX -= 3;
                posY -= 3;
                mapa[posY][posX] = 4;
            }
            orientacao = Orientacao.Norte;
        }
        return encontrou;
    }
}
