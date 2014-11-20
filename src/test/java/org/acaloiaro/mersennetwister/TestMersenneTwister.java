package org.acaloiaro.mersennetwister;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;

/**
 * Unit tests for Mersenne Twister. Uses native java assertions: run with -ea flag
 *
 * @author Adriano Caloiaro
 * @date 11/20/14
 */
public class TestMersenneTwister {
    static FileInputStream fstream = null;
    static double[] validationData;
    static final int NUMBER_OF_SAMPLES = 100000;
    static final int R_SEED = 4357;

    static {

        try {
            fstream = new FileInputStream("src/test/resources/gnu_r_runif_100000_seed_4357.csv");
            // Get the object of DataInputStream

            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String doublesString = br.readLine();
            String[] doubleStrings = doublesString.split(",");
            validationData = new double[doublesString.length()];

            for(int i = 0; i < doubleStrings.length; i++) {
                validationData[i] = Double.parseDouble(doubleStrings[i]);
            }
        } catch (IOException e) {
            System.out.println("There was a problem reading the validation file: " + e.getMessage());
            System.exit(1);
        }

    }

    public static void main(String args[]) {
        MersenneTwister mt = new MersenneTwister(R_SEED);
        double[] javaResults = IntStream.range(0, NUMBER_OF_SAMPLES).mapToDouble(i -> mt.nextDouble()).toArray();

        IntStream.range(0, NUMBER_OF_SAMPLES).forEach(i -> {

            // This deliberately a very imprecise comparison, but that is only due to the String->double conversion
            BigDecimal javaValue = new BigDecimal(javaResults[i]).setScale(2, RoundingMode.HALF_EVEN);
            BigDecimal rValue = new BigDecimal(validationData[i]).setScale(2, RoundingMode.HALF_EVEN);

            try {
                assert (javaValue.equals(rValue));
            } catch(AssertionError e) {
                System.out.printf("ERROR: r: %f java: %f \n", javaValue, rValue);;
            }

        });
    }
}
