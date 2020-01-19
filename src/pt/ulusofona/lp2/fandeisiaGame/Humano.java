package pt.ulusofona.lp2.fandeisiaGame;

import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.mapStartGame;

public class Humano extends Creature {

    static int countTipoHumano = 0;
    static int capturasPorTipoHumano = 0;

    Humano(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, posX, posY, orient);
        this.custo = 3;
        this.alcance = 2;
    }

    Humano(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient, int ouro, int prata, int bronze, int pontos) {
        super(id, idEquipa, tipo, posX, posY, orient, ouro, prata, bronze, pontos);
        this.custo = 3;
        this.alcance = 2;
    }

    @Override
    public int moveCriatura() {//Done-------------

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        int encontrou = 0;

        if (orientacao == Orientacao.Norte) {
            if (posY - alcance >= 0 && semObstaculo()) {
                if (mapa[posY - alcance][posX] != 4 && mapa[posY - alcance][posX] != 5) {
                    if (mapa[posY - alcance][posX] == 1 || mapa[posY - alcance][posX] == 2 || mapa[posY - alcance][posX] == 3) {
                        encontrou = mapa[posY - alcance][posX];
                        calcTrofeus(encontrou);
                        capturasPorTipoHumano++;
                    }
                    mapa[posY][posX] = 0;
                    posY -= alcance;
                    mapa[posY][posX] = 4;
                    addKms();
                } else {
                    orientacao = Orientacao.Este;
                }
            } else {
                orientacao = Orientacao.Este;
            }
            return encontrou;
        }
        if (orientacao == Orientacao.Este) {
            if (posX + alcance <= xMax && semObstaculo()) {
                if (mapa[posY][posX + alcance] != 4 && mapa[posY][posX + alcance] != 5) {
                    if (mapa[posY][posX + alcance] == 1 || mapa[posY][posX + alcance] == 2 || mapa[posY][posX + alcance] == 3) {
                        encontrou = mapa[posY][posX + alcance];
                        calcTrofeus(encontrou);
                        capturasPorTipoHumano++;
                    }
                    mapa[posY][posX] = 0;
                    posX += alcance;
                    mapa[posY][posX] = 4;
                    addKms();
                } else {
                    orientacao = Orientacao.Sul;
                }
            } else {
                orientacao = Orientacao.Sul;
            }
            return encontrou;
        }
        if (orientacao == Orientacao.Sul) {
            if (posY + alcance <= yMax && semObstaculo()) {
                if (mapa[posY + alcance][posX] != 4 && mapa[posY + alcance][posX] != 5) {
                    if (mapa[posY + alcance][posX] == 1 || mapa[posY + alcance][posX] == 2 || mapa[posY + alcance][posX] == 3) {
                        encontrou = mapa[posY + alcance][posX];
                        calcTrofeus(encontrou);
                        capturasPorTipoHumano++;
                    }
                    mapa[posY][posX] = 0;
                    posY += alcance;
                    mapa[posY][posX] = 4;
                    addKms();
                } else {
                    orientacao = Orientacao.Oeste;
                }
            } else {
                orientacao = Orientacao.Oeste;
            }
            return encontrou;
        }
        if (orientacao == Orientacao.Oeste) {
            if (posX - alcance >= 0 && semObstaculo()) {
                if (mapa[posY][posX - alcance] != 4 && mapa[posY][posX - alcance] != 5) {
                    if (mapa[posY][posX - alcance] == 1 || mapa[posY][posX - alcance] == 2 || mapa[posY][posX - alcance] == 3) {
                        encontrou = mapa[posY][posX - alcance];
                        calcTrofeus(encontrou);
                        capturasPorTipoHumano++;
                    }
                    mapa[posY][posX] = 0;
                    posX -= alcance;
                    mapa[posY][posX] = 4;
                    addKms();
                } else {
                    orientacao = Orientacao.Norte;
                }
            } else {
                orientacao = Orientacao.Norte;
            }
        }
        return encontrou;
    }

    private boolean semObstaculo() {

        int countCreatures = 0;// 4
        int countBuracos = 0;// 5

        for (int i = 1; i < alcance; i++) {
            if (orientacao == Orientacao.Norte) {
                if (mapStartGame[posY - i][posX] == 4) {
                    countCreatures++;
                } else if (mapStartGame[posY - i][posX] == 5) {
                    countBuracos++;
                }
            }
            if (orientacao == Orientacao.Este) {
                if (mapStartGame[posY][posX + i] == 4) {
                    countCreatures++;
                } else if (mapStartGame[posY][posX + i] == 5) {
                    countBuracos++;
                }
            }
            if (orientacao == Orientacao.Sul) {
                if (mapStartGame[posY + i][posX] == 4) {
                    countCreatures++;
                } else if (mapStartGame[posY + i][posX] == 5) {
                    countBuracos++;
                }
            }
            if (orientacao == Orientacao.Oeste) {
                if (mapStartGame[posY][posX - i] == 4) {
                    countCreatures++;
                } else if (mapStartGame[posY][posX - i] == 5) {
                    countBuracos++;
                }
            }

        }

        if (countBuracos > 0 || countCreatures > 0) {
            return false;
        }

        return true;
    }

    @Override //Done--------------
    public void somaQtdCreatura() {
        countTipoHumano++;
    }

    @Override //Done--------------
    public int getQtdCreatura() {
        return countTipoHumano;
    }

    @Override //Done--------------
    public int getCapturasPorCreatura() {
        return capturasPorTipoHumano;
    }

    @Override //Done--------------
    public int setQtdCreatura() {
        return countTipoHumano = 0;
    }
}
