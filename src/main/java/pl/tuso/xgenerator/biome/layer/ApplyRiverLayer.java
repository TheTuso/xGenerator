package pl.tuso.xgenerator.biome.layer;

import pl.tuso.xgenerator.biome.BiomeLayers;
import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.layer.type.MergingLayer;
import pl.tuso.xgenerator.biome.layer.util.IdentityCoordinateTransformer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;
import pl.tuso.xgenerator.biome.layer.util.LayerSampler;

public enum ApplyRiverLayer implements MergingLayer, IdentityCoordinateTransformer {
    INSTANCE;

    public int sample(LayerRandomnessSource context, LayerSampler sampler1, LayerSampler sampler2, int x, int z) {
        int i = sampler1.sample(this.transformX(x), this.transformZ(z));
        int j = sampler2.sample(this.transformX(x), this.transformZ(z));
        if (BiomeLayers.isOcean(i)) {
            return i;
        } else if (j == Biomes.RIVER.getId()) {
            return j & 255;
        } else {
            return i;
        }
    }
}
