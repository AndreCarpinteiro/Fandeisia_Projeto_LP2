package pt.ulusofona.lp2.fandeisiaGame;

import java.util.Objects;

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

        if (orientacao == Orientacao.Norte) {
            if (posY - 3 > 0 || encontraGigante()) {
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
            orientacao = Orientacao.Este;
        }
        if (orientacao == Orientacao.Este) {
            if (posX + 3 < xMax || encontraGigante()) {
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
            orientacao = Orientacao.Sul;
        }
        if (orientacao == Orientacao.Sul) {
            if (posY + 3 < yMax || encontraGigante()) {
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
            orientacao = Orientacao.Oeste;
        }
        if (orientacao == Orientacao.Oeste) {
            if (posX - 3 > 0 || encontraGigante()) {
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
            orientacao = Orientacao.Norte;
        }
        return encontrou;
    }

    public boolean encontraGigante() {
        int xGig = 0;
        int yGig = 0;

        for (int i = 0; i < listaCreatures.size(); i++) {
            if (listaCreatures.get(i).getTipo().equals("Gigante")) {
                xGig = listaCreatures.get(i).getPosX();
                yGig = listaCreatures.get(i).getPosY();

                if (orientacao == Orientacao.Norte) {
                    if ((posY - 1 == yGig && posX == xGig) || (posY - 2 == yGig && posX == xGig)) {
                        return false;
                    }
                }
                if (orientacao == Orientacao.Este) {
                    if ((posY == yGig && posX + 1 == xGig) || (posY == yGig && posX + 2 == xGig)) {
                        return false;
                    }
                }
                if (orientacao == Orientacao.Sul) {
                    if ((posY + 1 == yGig && posX == xGig) || (posY + 2 == yGig && posX == xGig)) {
                        return false;
                    }
                }
                if (orientacao == Orientacao.Oeste) {
                    if ((posY == yGig && posX - 1 == xGig) || (posY == yGig && posX - 2 == yGig)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
