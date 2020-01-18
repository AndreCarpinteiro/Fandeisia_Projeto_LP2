package pt.ulusofona.lp2.fandeisiaGame;

public class Team {
    int idTeam;
    int pontosTeam;
    int plafond = 50;
    int custo = 0;
    boolean ativo;

    Team(int idTeam,int pontosTeam){
        this.idTeam = idTeam;
        this.pontosTeam = pontosTeam;
    }

    public void setEstado(boolean ativo){
        this.ativo = ativo;
    }

    public boolean getEstado(){
        return ativo;
    }

    public int getTeamPontos(){
        return pontosTeam;
    }

    public void somaPontos(int pontos){
        this.pontosTeam += pontos;
    }

    public void incrementaPontos(){
        this.pontosTeam++;
    }

    public int getPlafond(){
        return plafond;
    }

    public void somaPlafond(int plafond){
        this.plafond += plafond;
    }

    public void decrementaPlafond(int plafond){
        this.plafond -= plafond;
    }

    public int getId(){
        return idTeam;
    }

    public void setPlafond(int plafond){
        this.plafond = plafond;
    }

    public void setPontosTeam(int pontos){
        this.pontosTeam = pontos;
    }

    public void somaCusto(int custo){
        this.custo += custo;
    }

    public int getCusto(){
        return custo;
    }

    public void setCusto(int custo){
        this.custo = custo;
    }
}