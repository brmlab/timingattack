package cz.cvut.keyczar.homework;


import java.util.ArrayList;
import java.util.List;

public class CrackerCore {
    public int[] select(List<long[]> stats, byte[] hmac) {
        double[] means = Stats.mean(stats);

        ArrayList<Integer> idx = new ArrayList<Integer>();

        // TODO: implement your code here **************************
        // you can store your stats
        for (int i = 0; i< means.length; i++) {
            // for each byte test the condition
            idx.add(i);
        }
        // *********************************************************

        if (idx.isEmpty() || idx.size() > 6) return null;

        int[] result = new int[idx.size()];
        int i = 0; for (int b : idx) result[i++] = b;

        return result;
    }
}
