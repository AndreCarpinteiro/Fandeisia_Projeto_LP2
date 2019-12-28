package pt.ulusofona.lp2.fandeisiaGame;

import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.mapStartGame;

public abstract class Creature {

    //enum é otimo para valores conhecidos finitos
    enum Orientacao {
        Norte, Nordeste, Este, Sul, Sudeste, Oeste, Noroeste, Sudoeste
    }

    int id;
    int idEquipa;
    int custo;
    int ouro = 0;
    int prata = 0;
    int bronze = 0;
    String feitico = null;
    int[][] mapa = mapStartGame;

    String tipo;
    int pontos; //Numero de tesouros apanhados (1 tesouro = 1 ponto)

    int posX;
    int posY;
    Orientacao orientacao;

    Creature() {

    }

    public Creature(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        //Done
        this.id = id;
        this.idEquipa = idEquipa;
        this.tipo = tipo;
        this.posX = posX;
        this.posY = posY;
        this.orientacao = orient;
    }

    public int getId() {//Done------------
        return id;
    }

    public abstract int moveCriatura();

    public String getImagePNG() {//Done---------------------

        if (orientacao.toString().equals("Sul")) {
            if (tipo.equals("Anão")) {
                return "dwarf_sul.png";
            }
            if (tipo.equals("Gigante")) {
                return "chimera_sul2.png";
            }
            if (tipo.equals("Humano")) {
                return "skeleton_sul.png";
            }
            if (tipo.equals("Dragão")) {
                return "super_dragon_sul.png";
            }
        }

        if (orientacao.toString().equals("Norte")) {
            if (tipo.equals("Anão")) {
                return "dwarf_norte.png";
            }
            if (tipo.equals("Gigante")) {
                return "chimera_norte2.png";
            }
            if (tipo.equals("Humano")) {
                return "skeleton_norte.png";
            }
            if (tipo.equals("Dragão")) {
                return "super_dragon_norte.png";
            }
        }

        if (orientacao.toString().equals("Este")) {
            if (tipo.equals("Anão")) {
                return "dwarf_este.png";
            }
            if (tipo.equals("Gigante")) {
                return "chimera_este2.png";
            }
            if (tipo.equals("Humano")) {
                return "skeleton_este.png";
            }
            if (tipo.equals("Dragão")) {
                return "super_dragon_este.png";
            }
        }

        if (orientacao.toString().equals("Oeste")) {
            if (tipo.equals("Anão")) {
                return "dwarf_oeste.png";
            }
            if (tipo.equals("Gigante")) {
                return "chimera_oeste.png";
            }
            if (tipo.equals("Humano")) {
                return "skeleton_oeste.png";
            }
            if (tipo.equals("Dragão")) {
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

    public int getCusto(){
        return custo;
    }

    public String getFeitico(){
        return feitico;
    }

    public void setFeitico(String feitico) {
        this.feitico = feitico;
    }

    public void somaPontos(){ //NÃO TENHO A CERTEZA SE É INCREMENTAR OU ATRIBUIR O VALOR
        this.pontos++;
    }

    public void calcTrofeus(int valor){
        if(valor == 1){
            bronze++;
        }
        if(valor == 2){
            prata++;
        }
        if(valor == 3){
            ouro++;
        }
    }

    public int getBronze() {
        return bronze;
    }

    public int getPrata() {
        return prata;
    }

    public int getOuro() {
        return ouro;
    }
}
