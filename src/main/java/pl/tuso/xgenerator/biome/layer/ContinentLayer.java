package pl.tuso.xgenerator.biome.layer;

import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.layer.type.InitLayer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;

public enum ContinentLayer implements InitLayer {
    INSTANCE;

    @Override
    public int sample(LayerRandomnessSource context, int x, int y) {
        return Biomes.PLAINS.getId();
    }
}
