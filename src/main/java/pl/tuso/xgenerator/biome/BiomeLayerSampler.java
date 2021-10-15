package pl.tuso.xgenerator.biome;

import pl.tuso.xgenerator.biome.layer.util.CachingLayerSampler;
import pl.tuso.xgenerator.biome.layer.util.LayerFactory;

public class BiomeLayerSampler {
    private final CachingLayerSampler sampler;

    public BiomeLayerSampler(LayerFactory<CachingLayerSampler> layerFactory) {
        this.sampler = layerFactory.make();
    }

    public int sample(int x, int z) {
        return this.sampler.sample(x, z);
    }
}
