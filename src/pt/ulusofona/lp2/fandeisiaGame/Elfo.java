package pt.ulusofona.lp2.fandeisiaGame;

import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.mapStartGame;

public class Elfo extends Creature {

    Elfo(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, posX, posY, orient);
        this.custo = 5;
        this.alcance = 2;
    }

    public Elfo(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient, int ouro, int prata, int bronze, int pontos) {
        super(id, idEquipa, tipo, posX, posY, orient, ouro, prata, bronze, pontos);
        this.custo = 5;
        this.alcance = 2;
    }

    @Override
    public int moveCriatura() {
        //x Horizontal
        //y vertical

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        int encontrou = 0;

        if (orientacao == Orientacao.Norte) {
            if (posY - alcance >= 0 && semObstaculo()) { if (mapa[posY - alcance][posX] != 4 && mapa[posY - alcance][posX] != 5) { if (mapa[posY - alcance][posX] == 1 || mapa[posY - alcance][posX] == 2 || mapa[posY - alcance][posX] == 3) { encontrou = mapa[posY - alcance][posX];calcTrofeus(encontrou); }mapa[posY][posX] = 0;posY -= alcance;mapa[posY][posX] = 4; } else { orientacao = Orientacao.Nordeste; }
            } else {orientacao = Orientacao.Nordeste;}
            return encontrou;
        }
        if (orientacao == Orientacao.Nordeste) {
            if (posY - alcance >= 0 && posX + alcance <= xMax && semObstaculo()) {if (mapa[posY - alcance][posX + alcance] != 4 && mapa[posY - alcance][posX + alcance] != 5) { if (mapa[posY - alcance][posX + alcance] == 1 || mapa[posY - alcance][posX + alcance] == 2 || mapa[posY - alcance][posX + alcance] == 3) { encontrou = mapa[posY - alcance][posX + alcance];calcTrofeus(encontrou); }mapa[posY][posX] = 0;posY -= alcance;posX += alcance;mapa[posY][posX] = 4; } else { orientacao = Orientacao.Este; }
            } else {orientacao = Orientacao.Este; }
            return encontrou;
        }
        if (orientacao == Orientacao.Este) {
            if (posX + alcance <= xMax && semObstaculo()) {
                if (mapa[posY][posX + alcance] != 4 && mapa[posY][posX + alcance] != 5) {
                    if (mapa[posY][posX + alcance] == 1 || mapa[posY][posX + alcance] == 2 || mapa[posY][posX + alcance] == 3) {encontrou = mapa[posY][posX + alcance];calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posX += alcance;
                    mapa[posY][posX] = 4;
                } else {
                    orientacao = Orientacao.Sudeste;
                }
            } else {
                orientacao = Orientacao.Sudeste;
            }
            return encontrou;
        }
        if (orientacao == Orientacao.Sudeste) {
            if (posY + alcance <= yMax && posX + alcance <= xMax && semObstaculo()) {
                if (mapa[posY + alcance][posX + alcance] != 4 && mapa[posY + alcance][posX + alcance] != 5) {
                    if (mapa[posY + alcance][posX + alcance] == 1 || mapa[posY + alcance][posX + alcance] == 2 || mapa[posY + alcance][posX + alcance] == 3) { encontrou = mapa[posY + alcance][posX + alcance];calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posX += alcance;
                    posY += alcance;
                    mapa[posY][posX] = 4;
                } else {
                    orientacao = Orientacao.Sul;
                }
            } else {
                orientacao = Orientacao.Sul;
            }
            return encontrou;
        }
        if (orientacao == Orientacao.Sul) {
            if (posY + alcance <= yMax && semObstaculo()) { if (mapa[posY + alcance][posX] != 4 && mapa[posY + alcance][posX] != 5) { if (mapa[posY + alcance][posX] == 1 || mapa[posY + alcance][posX] == 2 || mapa[posY + alcance][posX] == 3) { encontrou = mapa[posY + alcance][posX];calcTrofeus(encontrou); }mapa[posY][posX] = 0;posY += alcance;mapa[posY][posX] = 4; } else { orientacao = Orientacao.Sudoeste; }
            } else {
                orientacao = Orientacao.Sudoeste;
            }
            return encontrou;
        }
        if (orientacao == Orientacao.Sudoeste) {
            if (posY + alcance <= yMax && posX - alcance >= 0 && semObstaculo()) { if (mapa[posY + alcance][posX - alcance] != 4 && mapa[posY + alcance][posX - alcance] != 5) {if (mapa[posY + alcance][posX - alcance] == 1 || mapa[posY + alcance][posX - alcance] == 2 || mapa[posY + alcance][posX - alcance] == 3) { encontrou = mapa[posY + alcance][posX - alcance];calcTrofeus(encontrou); }mapa[posY][posX] = 0;posY += alcance;posX -= alcance;mapa[posY][posX] = 4; } else { orientacao = Orientacao.Oeste; }
            } else {
                orientacao = Orientacao.Oeste;
            }
            return encontrou;
        }
        if (orientacao == Orientacao.Oeste) {
            if (posX - alcance >= 0 && semObstaculo()) {if (mapa[posY][posX - alcance] != 4 && mapa[posY][posX - alcance] != 5) { if (mapa[posY][posX - alcance] == 1 || mapa[posY][posX - alcance] == 2 || mapa[posY][posX - alcance] == 3) { encontrou = mapa[posY][posX - alcance];calcTrofeus(encontrou); }mapa[posY][posX] = 0;posX -= alcance;mapa[posY][posX] = 4; } else { orientacao = Orientacao.Noroeste; }
            } else {
                orientacao = Orientacao.Noroeste;
            }
            return encontrou;
        }
        if (orientacao == Orientacao.Noroeste) {
            if (posY - alcance >= 0 && posX - alcance >= 0 && semObstaculo()) { if (mapa[posY - alcance][posX - alcance] != 4 && mapa[posY - alcance][posX - alcance] != 5) { if (mapa[posY - alcance][posX - alcance] == 1 || mapa[posY - alcance][posX - alcance] == 2 || mapa[posY - alcance][posX - alcance] == 3) { encontrou = mapa[posY - alcance][posX - alcance];calcTrofeus(encontrou); }mapa[posY][posX] = 0;posX -= alcance;posY -= alcance;mapa[posY][posX] = 4; } else { orientacao = Orientacao.Norte; } } else { orientacao = Orientacao.Norte; }
        }
        return encontrou;
    }

    private boolean semObstaculo() {

        int countCreatures = 0;// 4
        int countBuracos = 0;// 5

        for (int i = 1; i < alcance; i++) {
            if (orientacao == Orientacao.Norte) { if (mapStartGame[posY - i][posX] == 4) { countCreatures++; } else if (mapStartGame[posY - i][posX] == 5) { countBuracos++; } }
            if (orientacao == Orientacao.Nordeste) { if (mapStartGame[posY - i][posX + i] == 4) { countCreatures++; } else if (mapStartGame[posY - i][posX + i] == 5) { countBuracos++; } }
            if (orientacao == Orientacao.Este) { if (mapStartGame[posY][posX + i] == 4) { countCreatures++; } else if (mapStartGame[posY][posX + i] == 5) { countBuracos++; } }
            if (orientacao == Orientacao.Sudeste) { if (mapStartGame[posY + i][posX + i] == 4) { countCreatures++; } else if (mapStartGame[posY + i][posX + i] == 5) { countBuracos++; } }
            if (orientacao == Orientacao.Sul) { if (mapStartGame[posY + i][posX] == 4) { countCreatures++; } else if (mapStartGame[posY + i][posX] == 5) { countBuracos++; } }
            if (orientacao == Orientacao.Sudoeste) { if (mapStartGame[posY + i][posX - i] == 4) { countCreatures++; } else if (mapStartGame[posY + i][posX - i] == 5) { countBuracos++; } }
            if (orientacao == Orientacao.Oeste) { if (mapStartGame[posY][posX - i] == 4) { countCreatures++; } else if (mapStartGame[posY][posX - i] == 5) { countBuracos++; } }
            if (orientacao == Orientacao.Noroeste) {if (mapStartGame[posY - i][posX - i] == 4) { countCreatures++; } else if (mapStartGame[posY - i][posX - i] == 5) { countBuracos++; } }
        }

        if (countBuracos > 1 || countCreatures > 0) {
            return false;
        }


        return true;
    }
}
