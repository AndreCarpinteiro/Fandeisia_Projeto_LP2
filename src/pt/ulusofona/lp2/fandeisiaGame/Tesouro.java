package pt.ulusofona.lp2.fandeisiaGame;

public class Tesouro {

    int id;
    String tipo;
    int posX;
    int posY;
    boolean capturado = false;

    Tesouro(){

    }

    public Tesouro(int id, String tipo, int posX, int posY){
        this.id = id;
        this.tipo = tipo;
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosY(){
        return posY;
    }

    public  int getPosX(){
        return posX;
    }

    public  int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public void setPosX(int posX){
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    public String toString(){ //NÃO SEI SE É ASSIM
        return "id: " + id + ", type: " + tipo + " | " + " (" + posX + "," + posY + ") ";
    }
}
