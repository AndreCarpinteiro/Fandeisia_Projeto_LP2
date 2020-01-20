package pt.ulusofona.lp2.fandeisiaGame;

import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.listaCreatures;
import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.mapStartGame;

public class Gigante extends Creature {

    static int countTipoGigante = 0;
    static int capturasPorTipoGigante = 0;

    Gigante(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, posX, posY, orient);
        this.custo = 5;
        this.alcance = 3;
    }

    public Gigante(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient, int ouro, int prata, int bronze, int pontos) {
        super(id, idEquipa, tipo, posX, posY, orient, ouro, prata, bronze, pontos);
        this.custo = 5;
        this.alcance = 3;
    }

    @Override
    public int moveCriatura() {

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        int encontrou = 0;

        if (orientacao == Orientacao.Norte) {
            if (posY - alcance >= 0 && encontraGigante()) {
                if (mapa[posY - alcance][posX] != 4 && mapa[posY - alcance][posX] != 5) {
                    if (mapa[posY - alcance][posX] == 1 || mapa[posY - alcance][posX] == 2 || mapa[posY - alcance][posX] == 3) {
                        encontrou = mapa[posY - alcance][posX];
                        calcTrofeus(encontrou);
                        capturasPorTipoGigante++;
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
            if (posX + alcance <= xMax && encontraGigante()) {
                if (mapa[posY][posX + alcance] != 4 && mapa[posY][posX + alcance] != 5) {
                    if (mapa[posY][posX + alcance] == 1 || mapa[posY][posX + alcance] == 2 || mapa[posY][posX + alcance] == 3) {
                        encontrou = mapa[posY][posX + alcance];
                        calcTrofeus(encontrou);
                        capturasPorTipoGigante++;
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
            if (posY + alcance <= yMax && encontraGigante()) {
                if (mapa[posY + alcance][posX] != 4 && mapa[posY + alcance][posX] != 5) {
                    if (mapa[posY + alcance][posX] == 1 || mapa[posY + alcance][posX] == 2 || mapa[posY + alcance][posX] == 3) {
                        encontrou = mapa[posY + alcance][posX];
                        calcTrofeus(encontrou);
                        capturasPorTipoGigante++;
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
            if (posX - alcance >= 0 && encontraGigante()) {
                if (mapa[posY][posX - alcance] != 4 && mapa[posY][posX - alcance] != 5) {
                    if (mapa[posY][posX - alcance] == 1 || mapa[posY][posX - alcance] == 2 || mapa[posY][posX - alcance] == 3) {
                        encontrou = mapa[posY][posX - alcance];
                        calcTrofeus(encontrou);
                        capturasPorTipoGigante++;
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

    private boolean encontraGigante() {
        int xGig;
        int yGig;

        for (Creature creature : listaCreatures) {
            if (creature.getTipo().equals("Gigante")) {
                xGig = creature.getPosX();
                yGig = creature.getPosY();
                for (int j = 1; j < alcance; j++) {

                    if (orientacao == Orientacao.Norte) {
                        if ((posY - j == yGig && posX == xGig)) {
                            return false;
                        }
                    }
                    if (orientacao == Orientacao.Este) {
                        if ((posY == yGig && posX + j == xGig)) {
                            return false;
                        }
                    }
                    if (orientacao == Orientacao.Sul) {
                        if ((posY + j == yGig && posX == xGig)) {
                            return false;
                        }
                    }
                    if (orientacao == Orientacao.Oeste) {
                        if ((posY == yGig && posX - j == xGig)) {
                            return false;
                        }
                    }
                }
            } else {

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
            }
        }
        return true;
    }

    @Override //Done--------------
    public void somaQtdCreatura() {
        countTipoGigante++;
    }

    @Override //Done--------------
    public int getQtdCreatura() {
        return countTipoGigante;
    }

    @Override //Done--------------
    public int getCapturasPorCreatura() {
        return capturasPorTipoGigante;
    }

    @Override //Done--------------
    public void setQtdCreatura() {
         countTipoGigante = 0;
    }

    @Override //Done--------------
    public void setCapturaPorCreatura() {
        capturasPorTipoGigante = 0;
    }
}
