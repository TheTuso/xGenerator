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

public class TestPopulator extends BlockPopulator {

    private final World world;

    public TestPopulator(World world) {
        this.world = world;
    }

    @Override
    public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);
        LayeredBiomeSource layeredBiomeSource = new LayeredBiomeSource(world.getSeed(), 4, 3);
        Biomes biomes = Biomes.getBiomeById(layeredBiomeSource.getBiomeForNoiseGen(x, z));
        if (biomes == Biomes.FOREST) {
            int amount = random.nextInt(16);
            for (int i = 1; i < amount; i++) {
                x = chunkX * 16 + random.nextInt(16);
                z = chunkZ * 16 + random.nextInt(16);
                for (int y = 255; y > 0; y--) {
                    if (limitedRegion.getType(x, y, z).equals(Material.AIR) && !limitedRegion.getType(x, y - 1, z).equals(Material.AIR)) {
                        //limitedRegion.generateTree(l, random, TreeType.TREE);
                        limitedRegion.generateTree(new Location(null, x, y, z), random, TreeType.BIG_TREE);
                    }
                }
            }
        }
    }
}
