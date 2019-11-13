package pt.ulusofona.lp2.fandeisiaGame;

public class Creatures {

    int id;
    String tipo;
    int posX;
    int posY;
    String orientação;

    public Creatures(int id, String tipo, String orientação){
        this.id = id;
        this.orientação = orientação;
        this.tipo = tipo;
    }

    public int getId(){
        /* Deve devolver o ID da criatura.*/
        return 99;
    }

    public String getImagePNG(){
        /*Deve devolver o nome do ficheiro de imagem
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
        return "incompleto";
    }

    public String toString(){
        /*
         * Retorna uma ​String​ com a informação sobre a criatura.
         *
         * Sintaxe​:
         * “<ID> | <Tipo> | <ID Equipa> | <Nr Pontos> @ (<x>, <y>) <Orientacão>”
         *
         * Onde <Nr Pontos> corresponde a quantidade
         * de Tesouros encontrados pela criatura.
         * */
        return "toString incompleto";
    }
}
