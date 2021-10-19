package pl.tuso.xgenerator.biome.populator;

import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.source.LayeredBiomeSource;

import java.util.Random;

public class DevItemPopulator extends BlockPopulator {


    @Override
    public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
        Biomes biomes = Biomes.JUNGLE;
        for (SmallItem item : biomes.getHandler().smallIteams()) {
            item.build(chunkX, chunkZ, random, limitedRegion);
        }
        for (TreePopulator tree : biomes.getHandler().trees()) {
            tree.build(chunkX, chunkZ, random, limitedRegion);
        }
    }
}
