package pt.ulusofona.lp2.fandeisiaGame;

public abstract class Creature {

    //enum é otimo para valores conhecidos finitos
    enum Orientacao {
        Norte, Nordeste, Este, Sul, Sudeste, Oeste, Noroeste, Sudoeste
    }

    int id;
    int idEquipa;
    int[][] mapa = FandeisiaGameManager.mapStartGame;

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
        return id;
    }

    public abstract boolean moveCriatura();

    public String getImagePNG() {//Done---------------------

        if (orientacao.toString().equals("Sul")) {
            if (tipo.equals("Dwarf")) {
                return "dwarf_sul.png";
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
                return "dwarf_norte.png";
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
                return "dwarf_este.png";
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
                return "dwarf_oeste.png";
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

    public Orientacao getOrientacao(){
        return orientacao;
    }

    public String toString() {//Done--------------------

        return id + " | " + tipo + " | " + idEquipa + " | " + pontos + " @ (" + posX + ", " + posY + ") " + orientacao;
    }
}
