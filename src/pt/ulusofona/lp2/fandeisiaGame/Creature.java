package pt.ulusofona.lp2.fandeisiaGame;

import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.mapStartGame;

public abstract class Creature {

    //enum é otimo para valores conhecidos finitos
    enum Orientacao {
        Norte, Nordeste, Este, Sul, Sudeste, Oeste, Noroeste, Sudoeste
    }

    enum FeiticoEnum {
        SemFeitico, EmpurraParaNorte, EmpurraParaOeste, EmpurraParaEste, EmpurraParaSul, ReduzAlcance, DuplicaAlcance, Congela, Congela4Ever, Descongela
    }

    int id, idEquipa, custo, ouro = 0, prata = 0, bronze = 0;
    String tipo;
    Orientacao orientacao;
    int[][] mapa = mapStartGame;
    FeiticoEnum feiticoEnum = FeiticoEnum.SemFeitico;
    int alcance, pontos, posX, posY;;
    boolean congelado4Ever = false;
    boolean congeladoNesteTurno = false;
    Creature() {

    }

    public Creature(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient) {
        this.id = id;
        this.idEquipa = idEquipa;
        this.tipo = tipo;
        this.posX = posX;
        this.posY = posY;
        this.orientacao = orient;
    }

    public Creature(int id, int idEquipa, String tipo, int posX, int posY, Orientacao orient, int ouro, int prata, int bronze, int pontos) {
        this.id = id;
        this.idEquipa = idEquipa;
        this.tipo = tipo;
        this.posX = posX;
        this.posY = posY;
        this.orientacao = orient;
        this.ouro = ouro;
        this.prata = prata;
        this.bronze = bronze;
        this.pontos = pontos;
    }

    public int getId() {//Done------------
        return id;
    }

    public abstract int moveCriatura();

    public String getImagePNG() {//Done---------------------

        if (orientacao.toString().equals("Sul")) { if (tipo.equals("Anão")) { return "dwarf_sul.png"; }if (tipo.equals("Gigante")) { return "gigante_sul.png"; }if (tipo.equals("Humano")) { return "skeleton_sul.png"; }if (tipo.equals("Dragão")) { return "super_dragon_sul.png"; }if (tipo.equals("Elfo")) { return "elfo_sul.png"; } }

        if (orientacao.toString().equals("Norte")) {if (tipo.equals("Anão")) { return "dwarf_norte.png"; }if (tipo.equals("Gigante")) { return "gigante_norte.png"; }if (tipo.equals("Humano")) { return "skeleton_norte.png"; }if (tipo.equals("Dragão")) { return "super_dragon_norte.png"; }if (tipo.equals("Elfo")) { return "elfo_norte.png"; } }

        if (orientacao.toString().equals("Este")) {if (tipo.equals("Anão")) { return "dwarf_este.png"; }if (tipo.equals("Gigante")) { return "gigante_este.png"; }if (tipo.equals("Humano")) { return "skeleton_este.png"; }if (tipo.equals("Dragão")) { return "super_dragon_este.png"; }if (tipo.equals("Elfo")) { return "elfo_este.png"; } }

        if (orientacao.toString().equals("Oeste")) { if (tipo.equals("Anão")) { return "dwarf_oeste.png"; }if (tipo.equals("Gigante")) { return "gigante_oeste.png"; }if (tipo.equals("Humano")) { return "skeleton_oeste.png"; }if (tipo.equals("Dragão")) { return "super_dragon_oeste.png"; }if (tipo.equals("Elfo")) { return "elfo_oeste.png"; } }

        if (orientacao.toString().equals("Nordeste")) {if (tipo.equals("Dragão")) { return "super_dragon_nordeste.png"; }if (tipo.equals("Elfo")) { return "elfo_nordeste.png"; }}

        if (orientacao.toString().equals("Sudeste")) {if (tipo.equals("Dragão")) { return "super_dragon_sudeste.png"; }if (tipo.equals("Elfo")) { return "elfo_sudeste.png"; } }
        if (orientacao.toString().equals("Sudoeste")) { if (tipo.equals("Dragão")) { return "super_dragon_sudoeste.png"; }if (tipo.equals("Elfo")) { return "elfo_sudoeste.png"; } }

        if (orientacao.toString().equals("Noroeste")) { if (tipo.equals("Dragão")) { return "super_dragon_noroeste.png"; }if (tipo.equals("Elfo")) { return "elfo_noroeste.png"; } }
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

    public Orientacao getOrientacao() {
        return orientacao;
    }

    public String toString() {//Done--------------------

        return id + " | " + tipo + " | " + idEquipa + " | " + (ouro + prata + bronze) + " @ (" + posX + ", " + posY + ") " + orientacao;
    }

    public int getCusto() {
        return custo;
    }

    public void somaPontos(int pontos) { //NÃO TENHO A CERTEZA SE É INCREMENTAR OU ATRIBUIR O VALOR
        this.pontos += pontos;
    }

    public void calcTrofeus(int valor) { if (valor == 1) {bronze++; }if (valor == 2) { prata++; }if (valor == 3) { ouro++; }}

    public int getBronze() {
        return bronze;
    }

    public int getPrata() {
        return prata;
    }

    public int getOuro() {
        return ouro;
    }

    public FeiticoEnum getFeiticoEnum() {
        return feiticoEnum;
    }

    public void setFeitico(String feitico) {
        this.feiticoEnum = FeiticoEnum.valueOf(feitico);
    }

    public int getAlcance() {
        return alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public boolean getCongelado4Ever() {
        return congelado4Ever;
    }

    public void setCongelado4Ever(boolean congelado4Ever) {
        this.congelado4Ever = congelado4Ever;
    }

    public boolean getcongeladoNesteTurno() {
        return congeladoNesteTurno;
    }

    public void setcongeladoNesteTurno(boolean congeladoNesteTurno) {
        this.congeladoNesteTurno = congeladoNesteTurno;
    }

}
