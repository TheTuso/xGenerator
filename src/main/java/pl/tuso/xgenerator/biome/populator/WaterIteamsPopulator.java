package pl.tuso.xgenerator.biome.populator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;
import pl.tuso.xgenerator.biome.Biomes;

import java.util.Random;

public class WaterIteamsPopulator extends BlockPopulator {

    private final Material[] on = new Material[] {Material.SAND, Material.DIRT, Material.CLAY};

    @Override
    public void populate(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull LimitedRegion limitedRegion) {
        int amount = random.nextInt(64) + 64;
        for (int i = 1; i < amount; i++) {
            int realX = chunkX * 16 + random.nextInt(16);
            int realZ = chunkZ * 16 + random.nextInt(16);
            for (int y = 62; y > 0; y--) {
                if (limitedRegion.getType(realX, y, realZ) == Material.WATER &&
                        canBePlacedOn(limitedRegion.getType(realX, y - 1, realZ))) {
                    limitedRegion.setType(new Location(null, realX, y, realZ), Material.SEAGRASS);
                }
            }
        }
    }

    private boolean canBePlacedOn(Material material) {
        for (Material materials : on) {
            if (materials == material) {
                return true;
            }
        }
        return false;
    }
}
