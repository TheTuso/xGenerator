package pl.tuso.xgenerator.biome.layer;

import pl.tuso.xgenerator.biome.BiomeLayers;
import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.layer.type.IdentitySamplingLayer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;

public class AddBaseBiomesLayer implements IdentitySamplingLayer {
    private static final int[] DRY_BIOMES = new int[]{Biomes.DESERT.getId(), Biomes.DESERT.getId(), Biomes.DESERT.getId(), Biomes.SAVANNA.getId(), Biomes.SAVANNA.getId(), Biomes.MESSA.getId(), Biomes.PLAINS.getId()};
    private static final int[] TEMPERATE_BIOMES = new int[]{Biomes.FOREST.getId(), Biomes.AUTUMN_FOREST.getId(), Biomes.REDWOOD_FOREST.getId(), Biomes.PLAINS.getId(), Biomes.SWAMP.getId()};
    private static final int[] COOL_BIOMES = new int[]{Biomes.FOREST.getId(), Biomes.SNOWY_TUNDRA.getId(), Biomes.SNOWY_TAIGA.getId(), Biomes.TAIGA.getId(), Biomes.PLAINS.getId()};

    @Override
    public int sample(LayerRandomnessSource context, int value) {
        int i = (value & 3840) >> 8;
        value &= -3841;
        if (!BiomeLayers.isOcean(value)) {
            switch(value) {
                case 1:
                    return this.DRY_BIOMES[context.nextInt(this.DRY_BIOMES.length)];
                case 2:
                    if (i > 0) {
                        return Biomes.JUNGLE.getId();
                    }

                    return TEMPERATE_BIOMES[context.nextInt(TEMPERATE_BIOMES.length)];
                case 3:
                    return COOL_BIOMES[context.nextInt(COOL_BIOMES.length)];
                default:
                    return Biomes.PLAINS.getId();//
            }
        } else {
            return value;
        }
    }
}
