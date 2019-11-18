package pt.ulusofona.lp2.fandeisiaGame;

//Provavelmente ainda falta alguns metodos para gerir a criatura
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

    public int getId() {//Done---------------------------------
        /* Deve devolver o ID da criatura.*/
        return id;// usar this.id??
    }

    public void capturaPonto(){
        pontos++;

    }

    public void moveCriatura(String orientacao, int[][] mapa) {

        int yMax = mapa.length;
        int xMax = mapa[0].length;

        System.out.println("rows" + yMax);
        System.out.println("columns" + xMax);

        if (orientacao.equals("Norte")) {
            if(posY == 0){
                orientacao = "Este";
            }else if(mapa[posX ][posY - 1] == 1) {
                orientacao = "Este";
            } else {
                mapa[posX][posY] = 0;
                posY--;
                mapa[posX][posY] = 1;
            }
        }else if (orientacao.equals("Sul")) {
            if(posY == yMax){
                orientacao = "Oeste";
            }else if(mapa[posX][posY+ 1] == 1) {
                orientacao = "Oeste";
            }else{
                mapa[posX][posY] = 0;
                posY++;
                mapa[posX][posY] = 1;
            }
        }else if (orientacao.equals("Oeste")) {
            if (posX == 0) {
                orientacao = "Norte";
            }else if(mapa[posX - 1][posY] == 1){
                orientacao = "Norte";
            } else{
                mapa[posX][posY] = 0;
                posX--;
                mapa[posX][posY] = 1;
            }
        }else if (orientacao.equals("Este")) {
            if(posX == xMax){
                orientacao = "Sul";
            }else if (mapa[posX + 1][posY] == 1) {
                orientacao = "Sul";
            } else{
                mapa[posX][posY] = 0;
                posX++;
                mapa[posX][posY] = 1;
            }
        }
    }

    public String getImagePNG() {//Incompleto---------------------

        if (orientacao.toString().equals("Sul")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_white_DOWN.png";
            }
            if (tipo.equals("Chimera")) {
                return "chimera_sul2.png";
            }
            if(tipo.equals("Skeleton")){
                return "skeleton_sul.png";
            }
        }

        if (orientacao.toString().equals("Norte")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_white_UP.png";
            }
            if (tipo.equals("Chimera")) {
                return "chimera_norte2.png";
            }
            if(tipo.equals("Skeleton")){
                return "skeleton_norte.png";
            }
        }

        if (orientacao.toString().equals("Este")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_white_LEFT.png";
            }
            if (tipo.equals("Chimera")) {
                return "chimera_este2.png";
            }
            if(tipo.equals("Skeleton")){
                return "skeleton_este.png";
            }
        }

        if (orientacao.toString().equals("Oeste")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_white_RIGHT.png";
            }
            if (tipo.equals("Chimera")) {
                return "chimera_oeste.png";
            }
            if(tipo.equals("Skeleton")){
                return "skeleton_oeste.png";
            }
        }

        if (tipo.equals("Skeleton")) {
            return "skeleton.png";
        }

        if (tipo.equals("Super Dragão")) {
            return "super_dragonn.png";
        }
        return null;
    }

    public String toString() {//Done------------------------------------
        /*Retorna uma ​String​ com a informação sobre a criatura.
         * Sintaxe​:
         * “<ID> | <Tipo> | <ID Equipa> | <Nr Pontos> @ (<x>, <y>) <Orientacão>”
         */
        return id + " | " + tipo + " | " + idEquipa + " | " + pontos + " @ (" + posX + ", " + posY + ") " + orientacao;
    }
}
