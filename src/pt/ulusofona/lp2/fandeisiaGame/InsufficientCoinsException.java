package pt.ulusofona.lp2.fandeisiaGame;

import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.tLDR;
import static pt.ulusofona.lp2.fandeisiaGame.FandeisiaGameManager.tRST;

public class InsufficientCoinsException extends Exception {

    public boolean teamRequiresMoreCoins(int teamId) {

        if (teamId == 10) {
            if (tLDR.getCusto() > 50) {
                return true;
            }
        } else {
            if (tRST.getCusto() > 50) {
                return true;
            }
        }
        return false;
    }

    public int getRequiredCoinsForTeam(int teamID){

        if(teamID == 10){
            return tLDR.getCusto();
        }else {
            return tRST.getCusto();
        }
    }
}
