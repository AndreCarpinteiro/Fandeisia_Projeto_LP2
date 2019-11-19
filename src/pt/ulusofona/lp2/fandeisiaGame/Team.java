package pt.ulusofona.lp2.fandeisiaGame;

public class Team {
    int idTeam;
    int pontosTeam;
    boolean ativo;

    Team(int idTeam,int pontosTeam,boolean ativo){
        this.idTeam = idTeam;
        this.pontosTeam = pontosTeam;
        this.ativo = ativo;
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

    public int getIdTeam(){
        return idTeam;
    }
}
