package io.github.nyghtfull.util.math;

import java.security.SecureRandom;

public class RandomUtil {
    public static float getAdvancedRandom(float min, float max) {
        SecureRandom random = new SecureRandom();

        long finalSeed = System.nanoTime();

        for (int i = 0; i < 5; ++i) {
            long seed = (long) (Math.random() * 1_000_000_000);

            seed ^= (seed << 13);
            seed ^= (seed >>> 17);
            seed ^= (seed << 15);

            finalSeed += seed;
        }

        random.setSeed(finalSeed);

        return random.nextFloat() * (max - min) + min;
    }

    public static int getAdvancedRandom(int min, int max) {
        SecureRandom random = new SecureRandom();

        long finalSeed = System.nanoTime();

        for (int i = 0; i < 5; ++i) {
            long seed = (long) (Math.random() * 1_000_000_000);

            seed ^= (seed << 13);
            seed ^= (seed >>> 17);
            seed ^= (seed << 15);

            finalSeed += seed;
        }

        random.setSeed(finalSeed);

        long rangeSize = (long) max - (long) min + 1;
        long randomLong = (random.nextLong() & Long.MAX_VALUE) % rangeSize;

        return (int) (randomLong + min);
    }
}
