package pt.ulusofona.lp2.fandeisiaGame;

public class Buraco {
    int id;
    int posX;
    int posY;

    public Buraco(int id, int posX, int posy) {
        this.id = id;
        this.posX = posX;
        this.posY = posy;
    }

    public int getId() {
        return id;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}
