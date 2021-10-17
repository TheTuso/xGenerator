package pl.tuso.xgenerator.biome.populator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.source.LayeredBiomeSource;

import java.util.Random;

public class DevPopulator extends BlockPopulator {

    private final World world;

    public DevPopulator(World world) {
        this.world = world;
    }

    @Override
    public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
        Biomes biomes = Biomes.FOREST;
        for (SmallItem item : biomes.getHandler().smallIteams()) {
            item.build(chunkX, chunkZ, random, limitedRegion);
        }
    }
}
