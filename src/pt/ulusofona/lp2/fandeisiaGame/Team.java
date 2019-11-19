package pt.ulusofona.lp2.fandeisiaGame;

import java.util.ArrayList;
import java.util.List;

public class Team {
    int idTeam;
    List<Creature> teamCreatures = new ArrayList<>();
    int pontosTeam;
    boolean ativo;

    Team(int idTeam,int pontosTeam,boolean ativo){
        this.idTeam = idTeam;
        //this.teamCreatures = teamCreatures;
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
