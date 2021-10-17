package pl.tuso.xgenerator.biome.source;

import pl.tuso.xgenerator.biome.BiomeLayers;

public class LayeredBiomeSource {
    private final BiomeLayerSampler biomeSampler;
    private final long seed;

    public LayeredBiomeSource(long seed, int biomeSize, int riverSize) {
        this.seed = seed;
        this.biomeSampler = BiomeLayers.build(seed, biomeSize, riverSize);
    }

    public int getBiomeForNoiseGen(int biomeX, int biomeZ) {
        return this.biomeSampler.sample(biomeX, biomeZ);
    }
}
