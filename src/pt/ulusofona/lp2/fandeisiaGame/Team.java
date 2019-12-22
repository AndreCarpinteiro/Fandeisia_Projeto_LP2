package pt.ulusofona.lp2.fandeisiaGame;

public class Team {
    int idTeam;
    int pontosTeam;
    int plafond = 3;
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

    public void setPontos(int pontos){
        this.pontosTeam = pontos;
    }

    public void somaPontos(){
        this.pontosTeam++;
    }

    public int getPlafond(){
        return plafond;
    }

    public void setPlafond(int plafond){
        this.plafond = plafond;
    }
}
