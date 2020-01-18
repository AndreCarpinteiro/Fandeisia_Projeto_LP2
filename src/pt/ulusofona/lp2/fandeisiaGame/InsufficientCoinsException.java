package pt.ulusofona.lp2.fandeisiaGame;

public class InsufficientCoinsException extends Exception {

    String mensagem;
    Team tLDR;//todo dar como parametro no gmamanager
    Team tRST;
    InsufficientCoinsException(Team tLDR ,Team tRST){
        this.tLDR = tLDR;
        this.tRST = tRST;
 //       this.mensagem = mensagem;
    }

   // public String getMensagem() {
      //  return mensagem;
    //}

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
