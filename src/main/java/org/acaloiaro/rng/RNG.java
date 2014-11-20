package org.acaloiaro.rng;

import org.acaloiaro.mersennetwister.MersenneTwister;

/**
 * A wrapper around MersenneTwister to provide an R-like API
 *
 * @author Adriano Caloiaro
 * @date 11/20/14
 */
public class RNG {
    static MersenneTwister mt = new MersenneTwister();

    /**
     * Sets the RNG seed
     * @param seed The seed
     */
    public static void setSeed(long seed) {
        mt.setSeed(seed);
    }
    /**
     * Generates <b>n</b> random values
     * @param n
     * @return
     */
    public static double[] runif(int n) {
        if (n <= 0) throw new RuntimeException("n must be a positive value.");
        double[] rands = new double[n];
        for(int i = 0; i < n; i++) {
            rands[i] = mt.nextDouble();
        }
        return rands;
    }
}
