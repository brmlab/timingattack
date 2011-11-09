package cz.cvut.keyczar.homework;

import java.util.List;

/**
 *
 * @author pborky
 */
public class Stats {
    public static double[] mean(List<long[]> series) {
        int width = series.get(0).length;
        int height = series.size();
        
        double[] sums = new double[width];
        double[] means = new double[width];

        for (long[] s: series) {
            for (int i = 0; i<s.length; i++) {
                sums[i] += s[i];
            }
        }
        for (int i = 0; i < width; i++) {
            means[i] = sums[i]/height;
        }
        return means;
    }
    public static double mean(long[] series) {
        double sum = 0;
        for (long s: series) {
            sum += s;
        }
        return sum/series.length;
    }
    public static double mean(double[] series) {
        double sum = 0;
        for (double s: series) {
            sum += s;
        }
        return sum/series.length;
    }
    public static double std(List<long[]> series, double mean) {
        int width = series.get(0).length;
        int height = series.size();

        double stD = 0;
        double sumsQ = 0;

        for (long[] s: series) {
            for (int i = 0; i<s.length; i++) {
                double diff = s[i] - mean;
                sumsQ += diff * diff;
            }
        }

        return Math.sqrt(sumsQ/(width*height));
    }
    public static double[] std(List<long[]> series, double[] means) {
        int width = series.get(0).length;
        int height = series.size();

        double[] stD = new double[width];
        double[] sumsQ = new double[width];

        for (long[] s: series) {
            for (int i = 0; i<s.length; i++) {
                double diff = s[i] - means[i];
                sumsQ[i] += diff * diff;
            }
        }
        for (int i = 0; i < width; i++) {
            stD[i] = Math.sqrt(sumsQ[i]/height);
        }

        return stD;
    }
    public static double std(long[] series) {
        return std(series, mean(series));
    }
    public static double std(long[] series, double mean) {
        double sqDiffSum = 0;
        for(long s: series ) {
           double diff = s - mean;
           sqDiffSum += diff * diff;
        }
        return Math.sqrt(sqDiffSum / series.length);
    }
    public static double std(double[] series) {
        return std(series, mean(series));
    }
    public static double std(double[] series, double mean) {
        double sqDiffSum = 0;
        for(double s: series) {
           double diff = s - mean;
           sqDiffSum += diff * diff;
        }
        return Math.sqrt(sqDiffSum / series.length);
    }

}
