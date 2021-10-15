package pl.tuso.xgenerator.biome.layer;

import pl.tuso.xgenerator.biome.BiomeLayers;
import pl.tuso.xgenerator.biome.layer.type.IdentitySamplingLayer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;

public enum SimpleLandNoiseLayer implements IdentitySamplingLayer {
    INSTANCE;

    @Override
    public int sample(LayerRandomnessSource context, int value) {
        return BiomeLayers.isOcean(value) ? value : context.nextInt(299999) + 2;
    }
}
