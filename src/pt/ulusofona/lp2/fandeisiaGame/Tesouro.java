package pt.ulusofona.lp2.fandeisiaGame;

public class Tesouro {

    int id;
    String tipo;
    int posX;
    int posY;


    Tesouro(){

    }
    public Tesouro(int id, String tipo, int posX, int posY){
        this.id = id;
        this.tipo = tipo;
        this.posX = posX;
        this.posY = posY;
    }

    public String toString(){ //NÃO SEI SE É ASSIM
        return "id: " + id + ", type: " + tipo + " | " + " (" + posX + "," + posY + ") ";
    }
}
