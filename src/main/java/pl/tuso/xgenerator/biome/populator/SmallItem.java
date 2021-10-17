package pl.tuso.xgenerator.biome.populator;

import org.bukkit.Material;
import org.bukkit.generator.LimitedRegion;

import java.util.Random;

public class SmallItem {
    private final Material item;
    private final int chance;

    public SmallItem(Material item, int chance) {
        this.item = item;
        this.chance = chance;
    }

    public void build(int chunkX, int chunkZ, Random random, LimitedRegion limitedRegion) {
        int amount = random.nextInt(chance) + 1;
        for (int i = 1; i < amount; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            for (int y = 255; y > 0; y--) {
                if (limitedRegion.getType(x, y, z).equals(Material.AIR) && !limitedRegion.getType(x, y - 1, z).equals(Material.AIR)) {
                    limitedRegion.setType(x, y, z, item);
                }
            }
        }
    }
}
