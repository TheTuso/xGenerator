package pl.tuso.xgenerator.biome.layer;

import pl.tuso.xgenerator.biome.BiomeLayers;
import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.layer.type.CrossSamplingLayer;
import pl.tuso.xgenerator.biome.layer.type.IdentitySamplingLayer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;

public class AddClimateLayers {
    public static enum AddSpecialBiomesLayer implements IdentitySamplingLayer {
        INSTANCE;

        public int sample(LayerRandomnessSource context, int value) {
            if (!BiomeLayers.isOcean(value) && context.nextInt(13) == 0) {
                value |= 1 + context.nextInt(15) << 8 & 3840;
            }

            return value;
        }
    }

    public static enum AddCoolBiomesLayer implements CrossSamplingLayer {
        INSTANCE;

        public int sample(LayerRandomnessSource context, int n, int e, int s, int w, int center) {
            return center != Biomes.FOREST.getId() ||
                    n != Biomes.PLAINS.getId() && e != Biomes.PLAINS.getId() && w != Biomes.PLAINS.getId() && s != Biomes.PLAINS.getId() &&
                            n != Biomes.DESERT.getId() && e != Biomes.DESERT.getId() && w != Biomes.DESERT.getId() && s != Biomes.DESERT.getId() ? center : Biomes.TAIGA.getId();
        }
    }

    public static enum AddTemperateBiomesLayer implements CrossSamplingLayer {
        INSTANCE;

        public int sample(LayerRandomnessSource context, int n, int e, int s, int w, int center) {
            return center != Biomes.PLAINS.getId() ||
                    n != Biomes.TAIGA.getId() && e != Biomes.TAIGA.getId() && w != Biomes.TAIGA.getId() && s != Biomes.TAIGA.getId() &&
                            n != Biomes.FOREST.getId() && e != Biomes.FOREST.getId() && w != Biomes.FOREST.getId() && s != Biomes.FOREST.getId() ? center : Biomes.DESERT.getId();
        }
    }
}
