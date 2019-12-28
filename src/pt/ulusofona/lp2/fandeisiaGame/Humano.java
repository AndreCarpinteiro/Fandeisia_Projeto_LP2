package pt.ulusofona.lp2.fandeisiaGame;

public class Humano extends Creature {

  Humano(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, posX, posY, orient);
        this.custo = 3;
    }

    @Override
    public int moveCriatura() {//Done-------------

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        int encontrou = 0;

        if (orientacao == Orientacao.Norte) {
            if(posY - 2 >= 0) {
                if (mapa[posY - 2][posX] != 4 && mapa[posY - 2][posX] != 5 && mapa[posY - 1][posX] != 4 && mapa[posY - 1][posX] != 5) {
                    if (mapa[posY - 2][posX] == 1 || mapa[posY - 2][posX] == 2 || mapa[posY - 2][posX] == 3) {
                        encontrou = mapa[posY - 2][posX];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posY -= 2;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Este;}
            }else{orientacao = Orientacao.Este;}
            return encontrou;
        }
        if (orientacao == Orientacao.Este) {
            if(posX + 2 <= xMax) {
                if (mapa[posY][posX + 2] != 4 && mapa[posY][posX + 2] != 5 && mapa[posY][posX + 1] != 4 && mapa[posY][posX + 1] != 5) {
                    if (mapa[posY][posX + 2] == 1 || mapa[posY][posX + 2] == 2 || mapa[posY][posX + 2] == 3) {
                        encontrou = mapa[posY][posX + 2];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posX += 2;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Sul;}
            }else{orientacao = Orientacao.Sul;}
            return encontrou;
        }
        if (orientacao == Orientacao.Sul) {
            if(posY + 2 <= yMax) {
                if (mapa[posY + 2][posX] != 4 && mapa[posY + 2][posX] != 5 && mapa[posY + 1][posX] != 4 && mapa[posY + 1][posX] != 5) {
                    if (mapa[posY + 2][posX] == 1 || mapa[posY + 2][posX] == 2 || mapa[posY + 2][posX] == 3) {
                        encontrou = mapa[posY + 2][posX];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posY += 2;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Oeste;}
            }else{orientacao = Orientacao.Oeste;}
            return encontrou;
        }
        if (orientacao == Orientacao.Oeste) {
            if(posX - 2 >= 0) {
                if (mapa[posY][posX - 2] != 4 && mapa[posY][posX - 2] != 5 && mapa[posY][posX - 1] != 4 && mapa[posY][posX - 1] != 5) {
                    if (mapa[posY][posX - 2] == 1 || mapa[posY][posX - 2] == 2 || mapa[posY][posX - 2] == 3) {
                        encontrou = mapa[posY][posX - 2];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posX -= 2;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Norte;}
            }else{orientacao = Orientacao.Norte;}
        }
        return encontrou;
    }
}
