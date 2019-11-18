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

    public void moveCriatura(String orientacao) {

        if (orientacao.equals("Norte")) {
            posY--;
        }

        if (orientacao.equals("Sul")) {
            posY++;
        }

        if (orientacao.equals("Oeste")) {
            posX--;
        }

        if (orientacao.equals("Este")) {
            posX++;
        }
    }

    public String getImagePNG() {//Incompleto------------------------------
        /* Deve devolver o nome do ficheiro de imagem
         * (formato PNG) que representa a criatura.
         *
         * (As imagens a usar devem ser colocadas na pasta
         * src/images e devem ter tamanho 50x50. As imagens
         * devem ter fundo transparente para que se consiga
         * ver se estão num quadrado branco ou preto).
         *
         * Caso os alunos não pretendam definir
         * nenhuma imagem, a função pode
         * simplesmente retornar ​null​. Isto fará com
         * que o visualizador use uma imagem
         * pré-definida por omissão.
         * */

        if(orientacao.equals("Sul")) {
            if (tipo.equals("Dwarf")) {
                return "crazy_emoji_White_DOWN.png";
            }else if(tipo.equals("Chimera")){
                return "crazy_emoji_White_DOWN.png";
            }
        }

        if(tipo.equals("Skeleton")){
            return "Skeleton.png";
        }

        if(tipo.equals("Chimera")){
            return "Chimera.png";
        }

        if(tipo.equals("Super Dragão")){
            return "crazy_emoji_White.png";
        }
        return "Chimera.png";
    }

    public String toString() {//Done------------------------------------
        /*Retorna uma ​String​ com a informação sobre a criatura.
         * Sintaxe​:
         * “<ID> | <Tipo> | <ID Equipa> | <Nr Pontos> @ (<x>, <y>) <Orientacão>”
         */
        return id + " | " + tipo + " | " + idEquipa + " | " + pontos + " @ (" + posX + "," + posY + ") " + orientacao;
    }
}
