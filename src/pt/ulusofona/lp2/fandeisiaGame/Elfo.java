package pt.ulusofona.lp2.fandeisiaGame;

import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.listaCreatures;

public class Elfo extends Creature {

    Elfo(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, posX, posY, orient);
        this.custo = 5;
    }

    @Override
    public int moveCriatura() {
        //x Horizontal
        //y vertical

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        int encontrou = 0;

        if (orientacao == Orientacao.Norte || encontraCreatura()) {
            if (posY - 2 > 0) {//Tenho de fazer codigo repetido porque nao posso verificar isto no mesmo if, index out of bounds
                if (mapa[posY - 2][posX] != 1 || mapa[posY - 2][posX] != 5) {
                    if (mapa[posY - 2][posX] == 1 || mapa[posY - 2][posX] == 2 || mapa[posY - 2][posX] == 3) {
                        encontrou = mapa[posY - 2][posX];
                    }
                    mapa[posY][posX] = 0;
                    posY -= 2;
                    mapa[posY][posX] = 4;
                    return encontrou;
                }else{orientacao = Orientacao.Nordeste;}
            }else{orientacao = Orientacao.Nordeste;}
        }
        if (orientacao == Orientacao.Nordeste || encontraCreatura()) {
            if (posY - 2 > 0 && posX + 2 < xMax) {
                if (mapa[posY - 2][posX + 2] != 1 || mapa[posY - 2][posX + 2] != 5) {
                    if (mapa[posY - 2][posX + 2] == 1 || mapa[posY - 2][posX + 2] == 2 || mapa[posY - 2][posX + 2] == 3) {
                        encontrou = mapa[posY - 2][posX + 2];
                    }
                    mapa[posY][posX] = 0;
                    posY -= 2;
                    posX += 2;
                    mapa[posY][posX] = 4;
                    return encontrou;
                }else{orientacao = Orientacao.Este;}
            }else{orientacao = Orientacao.Este;}
        }
        if (orientacao == Orientacao.Este || encontraCreatura()) {
            if (posX + 2 < xMax) {
                if (mapa[posY][posX + 2] != 1 || mapa[posY][posX + 2] != 5) {
                    if (mapa[posY][posX + 2] == 1 || mapa[posY][posX + 2] == 2 || mapa[posY][posX + 2] == 3) {
                        encontrou = mapa[posY][posX + 2];
                    }
                    mapa[posY][posX] = 0;
                    posX += 2;
                    mapa[posY][posX] = 4;
                    return encontrou;
                }else{orientacao = Orientacao.Sudeste;}
            }else{orientacao = Orientacao.Sudeste;}
        }
        if (orientacao == Orientacao.Sudeste || encontraCreatura()) {
            if (posY + 2 < yMax && posX + 2 < xMax) {
                if (mapa[posY + 2][posX + 2] != 1 || mapa[posY + 2][posX + 2] != 5) {
                    if (mapa[posY + 2][posX + 2] == 1 || mapa[posY + 2][posX + 2] == 2 || mapa[posY + 2][posX + 2] == 3) {
                        encontrou = mapa[posY + 2][posX + 2];
                    }
                    mapa[posY][posX] = 0;
                    posX += 2;
                    posY += 2;
                    mapa[posY][posX] = 4;
                    return encontrou;
                }else{orientacao = Orientacao.Sul;}
            }else{orientacao = Orientacao.Sul;}
        }
        if (orientacao == Orientacao.Sul || encontraCreatura()) {
            if (posY + 2 < yMax) {
                if (mapa[posY + 2][posX] != 1 || mapa[posY + 2][posX] != 5) {
                    if (mapa[posY + 2][posX] == 1 || mapa[posY + 2][posX] == 2 || mapa[posY + 2][posX] == 3) {
                        encontrou = mapa[posY + 2][posX];
                    }
                    mapa[posY][posX] = 0;
                    posY += 2;
                    mapa[posY][posX] = 4;
                    return encontrou;
                }else{orientacao = Orientacao.Sudeste;}
            }else{orientacao = Orientacao.Sudeste;}
        }
        if (orientacao == Orientacao.Sudoeste || encontraCreatura()) {
            if (posY + 2 < yMax && posX - 2 > 0) {
                if (mapa[posY + 2][posX - 2] != 1 || mapa[posY + 2][posX - 2] != 5) {
                    if (mapa[posY + 2][posX - 2] == 1 || mapa[posY + 2][posX - 2] == 2 || mapa[posY + 2][posX - 2] == 3) {
                        encontrou = mapa[posY + 2][posX - 3];
                    }
                    mapa[posY][posX] = 0;
                    posY += 2;
                    posX -= 2;
                    mapa[posY][posX] = 4;
                    return encontrou;
                }else{orientacao = Orientacao.Oeste;}
            }else{orientacao = Orientacao.Oeste;}
        }
        if (orientacao == Orientacao.Oeste || encontraCreatura()) {
            if (posX - 2 > 0) {
                if (mapa[posY][posX - 2] != 1 || mapa[posY][posX - 2] != 5) {
                    if (mapa[posY][posX - 2] == 1 || mapa[posY][posX - 2] == 2 || mapa[posY][posX - 2] == 3) {
                        encontrou = mapa[posY][posX - 2];
                    }
                    mapa[posY][posX] = 0;
                    posX -= 2;
                    mapa[posY][posX] = 4;
                    return encontrou;
                }else{orientacao = Orientacao.Noroeste;}
            }else{orientacao = Orientacao.Noroeste;}
        }
        if (orientacao == Orientacao.Noroeste || encontraCreatura()) {
            if (posY - 2 > 0 && posX - 2 > 0) {
                if (mapa[posY - 2][posX - 2] != 1 || mapa[posY - 2][posX - 2] != 5) {
                    if (mapa[posY - 2][posX - 2] == 1 || mapa[posY - 2][posX - 2] == 2 || mapa[posY - 2][posX - 2] == 3) {
                        encontrou = mapa[posY - 2][posX - 2];
                    }
                    mapa[posY][posX] = 0;
                    posX -= 2;
                    posY -= 2;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Norte;}
            }else{orientacao = Orientacao.Norte;}
        }
        return encontrou;
    }

    public boolean encontraCreatura() {

        int xCrea;
        int yCrea;

        for (Creature listaCreature : listaCreatures) {
            yCrea = listaCreature.getPosY();
            xCrea = listaCreature.getPosX();

            if (orientacao == Orientacao.Norte) {
                if (posY - 1 == yCrea && posX == xCrea) {
                    return false;
                }
            }
            if (orientacao == Orientacao.Nordeste) {
                if (posY - 1 == yCrea && posX + 1 == xCrea) {
                    return false;
                }
            }
            if (orientacao == Orientacao.Este) {
                if (posY == yCrea && posX + 1 == xCrea) {
                    return false;
                }
            }
            if (orientacao == Orientacao.Sudeste) {
                if (posY + 1 == yCrea && posX + 1 == xCrea) {
                    return false;
                }
            }
            if (orientacao == Orientacao.Sul) {
                if (posY + 1 == yCrea && posX == xCrea) {
                    return false;
                }
            }
            if (orientacao == Orientacao.Sudoeste) {
                if (posY + 1 == yCrea && posX - 1 == xCrea) {
                    return false;
                }
            }
            if (orientacao == Orientacao.Oeste) {
                if (posY == yCrea && posX - 1 == xCrea) {
                    return false;
                }
            }
            if (orientacao == Orientacao.Noroeste) {
                if (posY - 1 == yCrea && posX - 1 == xCrea) {
                    return false;
                }
            }
        }
        return true;
    }
}
