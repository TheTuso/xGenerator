package pl.tuso.xgenerator.biome.layer.util;

import pl.tuso.xgenerator.biome.source.LayeredBiomeSource;

public class Seed {
    public static final long SEED = 462814629468174L;
    private static LayeredBiomeSource layeredBiomeSource = new LayeredBiomeSource(SEED, 5, 3);

    public static LayeredBiomeSource getLayeredBiomeSource() {
        return layeredBiomeSource;
    }
}
