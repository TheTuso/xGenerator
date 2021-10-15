package pl.tuso.xgenerator.biome.layer;

import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.layer.type.CrossSamplingLayer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;

public enum NoiseToRiverLayer implements CrossSamplingLayer {
    INSTANCE;

    public int sample(LayerRandomnessSource context, int n, int e, int s, int w, int center) {
        int i = isValidForRiver(center);
        return i == isValidForRiver(w) && i == isValidForRiver(n) && i == isValidForRiver(e) && i == isValidForRiver(s) ? -1 : Biomes.RIVER.getId();
    }

    private static int isValidForRiver(int value) {
        return value >= 2 ? 2 + (value & 1) : value;
    }
}
