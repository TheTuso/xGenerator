package pl.tuso.xgenerator.biome.layer;

import pl.tuso.xgenerator.biome.BiomeLayers;
import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.layer.type.SouthEastSamplingLayer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;

public enum AddColdClimatesLayer implements SouthEastSamplingLayer {
    INSTANCE;

    public int sample(LayerRandomnessSource context, int se) {
        if (BiomeLayers.isOcean(se)) {
            return se;
        } else {
            int i = context.nextInt(6);
            if (i == 0) {
                return Biomes.FOREST.getId();
            } else {
                return i == 1 ? Biomes.TAIGA.getId() : Biomes.PLAINS.getId();
            }
        }
    }
}
