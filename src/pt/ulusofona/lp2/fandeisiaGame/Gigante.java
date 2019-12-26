package pt.ulusofona.lp2.fandeisiaGame;
import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.listaCreatures;

public class Gigante extends Creature {

    Gigante(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, posX, posY, orient);
        this.custo = 5;
    }

    @Override
    public int moveCriatura() {

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        int encontrou = 0;

        for(int i = 0; i < listaCreatures.size(); i++){}
        if (orientacao == Orientacao.Norte && (posY - 3) > 0) {
            if (mapa[posY - 3][posX] != 1 || mapa[posY - 3][posX] != 5) {
                if (mapa[posY - 3][posX] == 1 || mapa[posY - 3][posX] == 2 || mapa[posY - 3][posX] == 3) {
                    encontrou = mapa[posY - 3][posX];
                }
                mapa[posY][posX] = 0;
                posY -= 3;
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
            orientacao = Orientacao.Norte;
        }
        return encontrou;
    }

  /*  public int moveCriatura() {

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        boolean encontrou = false;

        //System.out.println("rows" + yMax);
        //System.out.println("columns" + xMax);

        if (orientacao == Orientacao.Norte) {
            if (posY - 3 == 0 || mapa[posY - 4][posX] == 1) {
                orientacao = Orientacao.Nordeste;
            }

            mapa[posY][posX] = 0;
            posY--;
            mapa[posY][posX] = 1;

            if (mapa[posY - 1][posX] == 2) {
                pontos++; //MUDAR!!!
                encontrou = true;
            }
        }


        else if (orientacao == Orientacao.Sul) {
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
    }*/
}
