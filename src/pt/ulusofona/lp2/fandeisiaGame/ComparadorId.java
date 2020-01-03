package pt.ulusofona.lp2.fandeisiaGame;

import java.util.Comparator;

public class ComparadorId implements Comparator<Creature> {
    public int compare(Creature o1, Creature o2) {
        if (o1.getId() < o2.getId()) return -1;
        else if (o1.getId() > o2.getId()) return +1;
        else return 0;
    }
}
