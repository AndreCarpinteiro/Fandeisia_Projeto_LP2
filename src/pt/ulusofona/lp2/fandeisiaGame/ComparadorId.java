package pt.ulusofona.lp2.fandeisiaGame;

import java.util.Comparator;

public class ComparadorId implements Comparator<Creature> {
    public int compare(Creature arg1, Creature arg2) {
        if (arg1.getId() < arg2.getId()){
            return -1;}
        else if (arg1.getId() > arg2.getId()){
            return +1;
        }
        else {
            return 0;
        }
    }
}
