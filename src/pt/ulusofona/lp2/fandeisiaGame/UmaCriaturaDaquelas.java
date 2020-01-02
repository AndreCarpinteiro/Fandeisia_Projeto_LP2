package pt.ulusofona.lp2.fandeisiaGame;

public class UmaCriaturaDaquelas extends Creature {

    @Override
    public int moveCriatura() {
/*
        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;*/
        int encontrou = 1;

        //System.out.println("rows" + yMax);
        //System.out.println("columns" + xMax);

        /*if (orientacao == Orientacao.Norte) {
            if (posY - 3 == 0 || mapa[posY - 4][posX] == 1) {
                orientacao = Orientacao.Nordeste;
            }

            mapa[posY][posX] = 0;
            posY--;
            mapa[posY][posX] = 1;

            if (mapa[posY - 1][posX] == 2) {
                pontos++; //MUDAR!!!
                encontrou = 1;
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
                encontrou = 1;
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
                encontrou = 1;
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
                encontrou = 1;
            }
        }*/
        return encontrou;
    }
}
