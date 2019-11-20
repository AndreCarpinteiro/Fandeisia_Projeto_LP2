package pt.ulusofona.lp2.fandeisiaGame;

public class Creature {

    //enum é otimo para valores conhecidos finitos
    enum Orientacao {
        Norte, Este, Sul, Oeste
    }

    int id;
    int idEquipa;

    String tipo;
    int pontos; //Numero de tesouros apanhados (1 tesouro = 1 ponto)

    int posX;
    int posY;
    Orientacao orientacao;

    Creature() {

    }

    public Creature(int id, int idEquipa, String tipo, int pontos, int posX, int posY, Orientacao orient) {
        //Done
        this.id = id;
        this.idEquipa = idEquipa;
        this.tipo = tipo;
        this.pontos = pontos;
        this.posX = posX;
        this.posY = posY;
        this.orientacao = orient;
    }

    public int getId() {//Done------------
        /* Deve devolver o ID da criatura.*/
        return id;// usar this.id??
    }

    public boolean moveCriatura() {

        int[][] mapa = FandeisiaGameManager.mapStartGame;
        int yMax = mapa.length - 1;
        int xMax = mapa[0].length - 1;
        boolean encontrou = false;

        //System.out.println("rows" + yMax);
        //System.out.println("columns" + xMax);

        if (orientacao == Orientacao.Norte) {
            if (posY == 0) {
                orientacao = Orientacao.Este;
            } else if (mapa[posY - 1][posX] == 1) {
                orientacao = Orientacao.Este;
            } else if (mapa[posY - 1][posX] == 2) {
                mapa[posY][posX] = 0;
                posY--;
                mapa[posY][posX] = 1;
                pontos++;
                encontrou = true;

            } else {
                mapa[posY][posX] = 0;
                posY--;
                mapa[posY][posX] = 1;
            }
        } else if (orientacao == Orientacao.Sul) {
            if (posY == yMax) {
                orientacao = Orientacao.Oeste;
            } else if (mapa[posY + 1][posX] == 1) {
                orientacao = Orientacao.Oeste;
            } else if (mapa[posY + 1][posX] == 2) {
                mapa[posY][posX] = 0;
                posY++;
                mapa[posY][posX] = 1;
                pontos++;
                encontrou = true;
            } else {
                mapa[posY][posX] = 0;
                posY++;
                mapa[posY][posX] = 1;
            }
        } else if (orientacao == Orientacao.Oeste) {
            if (posX == 0) {
                orientacao = Orientacao.Norte;
            } else if (mapa[posY][posX - 1] == 1) {
                orientacao = Orientacao.Norte;
            } else if (mapa[posY][posX - 1] == 2) {
                mapa[posY][posX] = 0;
                posX--;
                mapa[posY][posX] = 1;
                pontos++;
                encontrou = true;
            } else {
                mapa[posY][posX] = 0;
                posX--;
                mapa[posY][posX] = 1;
            }
        } else if (orientacao == Orientacao.Este) {
            if (posX == xMax) {
                orientacao = Orientacao.Sul;
            } else if (mapa[posY][posX + 1] == 1) {
                orientacao = Orientacao.Sul;
            } else if (mapa[posY][posX + 1] == 2) {
                mapa[posY][posX] = 0;
                posX++;
                mapa[posY][posX] = 1;
                pontos++;
                encontrou = true;
            } else {
                mapa[posY][posX] = 0;
                posX++;
                mapa[posY][posX] = 1;
            }
        }
        return encontrou;
    }

    public String getImagePNG() {//Done---------------------

        if (orientacao.toString().equals("Sul")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_white_DOWN.png";
            }
            if (tipo.equals("Chimera")) {
                return "chimera_sul2.png";
            }
            if (tipo.equals("Skeleton")) {
                return "skeleton_sul.png";
            }
            if (tipo.equals("Super Dragão")) {
                return "super_dragon_sul.png";
            }
        }

        if (orientacao.toString().equals("Norte")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_white_UP.png";
            }
            if (tipo.equals("Chimera")) {
                return "chimera_norte2.png";
            }
            if (tipo.equals("Skeleton")) {
                return "skeleton_norte.png";
            }
            if (tipo.equals("Super Dragão")) {
                return "super_dragon_norte.png";
            }
        }

        if (orientacao.toString().equals("Este")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_white_RIGHT.png";
            }
            if (tipo.equals("Chimera")) {
                return "chimera_este2.png";
            }
            if (tipo.equals("Skeleton")) {
                return "skeleton_este.png";
            }
            if (tipo.equals("Super Dragão")) {
                return "super_dragon_este.png";
            }
        }

        if (orientacao.toString().equals("Oeste")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_white_LEFT.png";
            }
            if (tipo.equals("Chimera")) {
                return "chimera_oeste.png";
            }
            if (tipo.equals("Skeleton")) {
                return "skeleton_oeste.png";
            }
            if (tipo.equals("Super Dragão")) {
                return "super_dragon_oeste.png";
            }
        }
        return null;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPontos() {
        return pontos;
    }

    public int getIdEquipa() {
        return idEquipa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdEquipa(int idEquipa) {
        this.idEquipa = idEquipa;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }

    public String toString() {//Done--------------------

        return id + " | " + tipo + " | " + idEquipa + " | " + pontos + " @ (" + posX + ", " + posY + ") " + orientacao;
    }

}
