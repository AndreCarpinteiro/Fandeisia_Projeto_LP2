package pt.ulusofona.lp2.fandeisiaGame;

public class Dragao extends Creature {

    Dragao(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        super(id, idEquipa, tipo, posX, posY, orient);
        this.custo = 9;
    }

    public Dragao(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient, int ouro, int prata, int bronze, int pontos) {
        super(id, idEquipa, tipo, posX, posY, orient, ouro, prata, bronze, pontos);
    }

    @Override
    public int moveCriatura() {//Done------------

        //x Horizontal
        //y vertical

        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        int encontrou = 0;

        if (orientacao == Orientacao.Norte) {
            if(posY - 3 >= 0) {//Tenho de fazer codigo repetido porque nao posso verificar isto no mesmo if, index out of bounds
                if (mapa[posY - 3][posX] != 4 && mapa[posY - 3][posX] != 5) {
                    if (mapa[posY - 3][posX] == 1 || mapa[posY - 3][posX] == 2 || mapa[posY - 3][posX] == 3) {
                        encontrou = mapa[posY - 3][posX];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posY -= 3;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Nordeste;}
            }else{orientacao = Orientacao.Nordeste;}
            return encontrou;
        }
        if (orientacao == Orientacao.Nordeste) {
            if(posY - 3 >= 0 && posX + 3 <= xMax) {
                if (mapa[posY - 3][posX + 3] != 4 && mapa[posY - 3][posX + 3] != 5) {
                    if (mapa[posY - 3][posX + 3] == 1 || mapa[posY - 3][posX + 3] == 2 || mapa[posY - 3][posX + 3] == 3) {
                        encontrou = mapa[posY - 3][posX + 3];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posY -= 3;
                    posX += 3;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Este;}
            }else{orientacao = Orientacao.Este;}
            return encontrou;
        }
        if (orientacao == Orientacao.Este) {
            if(posX + 3 <= xMax) {
                if (mapa[posY][posX + 3] != 4 && mapa[posY][posX + 3] != 5) {
                    if (mapa[posY][posX + 3] == 1 || mapa[posY][posX + 3] == 2 || mapa[posY][posX + 3] == 3) {
                        encontrou = mapa[posY][posX + 3];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posX += 3;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Sudeste;}
            }else{orientacao = Orientacao.Sudeste;}
            return encontrou;
        }
        if (orientacao == Orientacao.Sudeste) {
            if(posY + 3 <= yMax && posX + 3 <= xMax) {
                if (mapa[posY + 3][posX + 3] != 4 && mapa[posY + 3][posX + 3] != 5) {
                    if (mapa[posY + 3][posX + 3] == 1 || mapa[posY + 3][posX + 3] == 2 || mapa[posY + 3][posX + 3] == 3) {
                        encontrou = mapa[posY + 3][posX + 3];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posX += 3;
                    posY += 3;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Sul;}
            }else{orientacao = Orientacao.Sul;}
            return encontrou;
        }
        if (orientacao == Orientacao.Sul) {
            if(posY + 3 <= yMax) {
                if (mapa[posY + 3][posX] != 4 && mapa[posY + 3][posX] != 5) {
                    if (mapa[posY + 3][posX] == 1 || mapa[posY + 3][posX] == 2 || mapa[posY + 3][posX] == 3) {
                        encontrou = mapa[posY + 3][posX];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posY += 3;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Sudoeste;}
            }else{orientacao = Orientacao.Sudoeste;}
            return encontrou;
        }
        if (orientacao == Orientacao.Sudoeste) {
            if(posY + 3 <= yMax && posX - 3 >= 0) {
                if (mapa[posY + 3][posX - 3] != 4 && mapa[posY + 3][posX - 3] != 5) {
                    if (mapa[posY + 3][posX - 3] == 1 || mapa[posY + 3][posX - 3] == 2 || mapa[posY + 3][posX - 3] == 3) {
                        encontrou = mapa[posY + 3][posX - 3];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posY += 3;
                    posX -= 3;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Oeste;}
            }else{orientacao = Orientacao.Oeste;}
            return encontrou;
        }
        if (orientacao == Orientacao.Oeste) {
            if(posX - 3 >= 0) {
                if (mapa[posY][posX - 3] != 4 && mapa[posY][posX - 3] != 5) {
                    if (mapa[posY][posX - 3] == 1 || mapa[posY][posX - 3] == 2 || mapa[posY][posX - 3] == 3) {
                        encontrou = mapa[posY][posX - 3];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posX -= 3;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Noroeste;}
            }else{orientacao = Orientacao.Noroeste;}
            return encontrou;
        }
        if (orientacao == Orientacao.Noroeste) {
            if(posY - 3 >= 0 && posX - 3 >= 0) {
                if (mapa[posY - 3][posX - 3] != 4 && mapa[posY - 3][posX - 3] != 5) {
                    if (mapa[posY - 3][posX - 3] == 1 || mapa[posY - 3][posX - 3] == 2 || mapa[posY - 3][posX - 3] == 3) {
                        encontrou = mapa[posY - 3][posX - 3];
                        calcTrofeus(encontrou);
                    }
                    mapa[posY][posX] = 0;
                    posX -= 3;
                    posY -= 3;
                    mapa[posY][posX] = 4;
                }else{orientacao = Orientacao.Norte;}
            }else{orientacao = Orientacao.Norte;}
        }
        return encontrou;
    }
}
