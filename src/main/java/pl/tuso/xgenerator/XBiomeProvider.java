package pl.tuso.xgenerator;

import com.google.common.collect.Lists;
import org.bukkit.block.Biome;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.source.LayeredBiomeSource;

import java.util.List;

public class XBiomeProvider extends BiomeProvider {

    @Override
    public @NotNull Biome getBiome(@NotNull WorldInfo worldInfo, int x, int y, int z) {
        LayeredBiomeSource layeredBiomeSource = new LayeredBiomeSource(worldInfo.getSeed(), 4, 4);
        Biomes biomes = Biomes.getBiomeById(layeredBiomeSource.getBiomeForNoiseGen(x, z));
        return biomes.getHandler().getVanillaBiome();
    }

    @Override
    public @NotNull List<Biome> getBiomes(@NotNull WorldInfo worldInfo) {
        return Lists.newArrayList(Biome.PLAINS, Biome.DESERT, Biome.FOREST, Biome.JUNGLE, Biome.BADLANDS, Biome.RIVER, Biome.SAVANNA,
                Biome.SNOWY_TAIGA, Biome.SNOWY_TUNDRA, Biome.TAIGA, Biome.SWAMP);
    }
}
