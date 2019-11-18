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
}
