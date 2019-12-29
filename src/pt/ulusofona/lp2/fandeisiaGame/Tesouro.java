package pt.ulusofona.lp2.fandeisiaGame;

public class Tesouro {

    int id;
    String tipo;
    int posX;
    int posY;

    Tesouro(int id, String tipo, int posX, int posY) {
        this.id = id;
        this.tipo = tipo;
        this.posX = posX;
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getTipo() {
        return tipo;
    }

    //Só para efeitos de teste
    public String toString() {
        return "Tesouro{" + "id=" + id + ", tipo='" + tipo + '\'' + ", posX=" + posX + ", posY=" + posY + '}';
    }
}
