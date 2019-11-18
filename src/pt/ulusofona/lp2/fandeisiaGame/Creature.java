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

        if (orientacao.equals("Norte")) {
            if (mapa[posX][posY - 1] != 1) {
                posY--;
            } else {
                orientacao = "Oeste";

            }
        }

        if (orientacao.equals("Sul")) {
            if (mapa[posX][posY + 1] == 0) {
                posY++;
            }else{
                orientacao = "Este";
            }
        }

        if (orientacao.equals("Oeste")) {
            if (mapa[posX - 1][posY] == 0) {
                posX--;
            }else{
                orientacao = "Sul";
            }
        }

        if (orientacao.equals("Este")) {
            if (mapa[posX + 1][posY] == 0) {
                posX++;
            } else{
                orientacao = "Norte";
            }
        }
    }

    public String getImagePNG() {//Incompleto---------------------

        if (orientacao.toString().equals("Sul")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_White_DOWN.png";
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
                return "crazy_emoji_White_UP.png";
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
                return "crazy_emoji_White_LEFT.png";
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
                return "crazy_emoji_White_RIGHT.png";
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
            return "crazy_emoji_White.png";
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
