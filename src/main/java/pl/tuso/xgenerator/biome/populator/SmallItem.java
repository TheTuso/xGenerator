package pl.tuso.xgenerator.biome.populator;

import org.bukkit.Material;
import org.bukkit.generator.LimitedRegion;

import java.util.Random;

public class SmallItem {
    private final Material item;
    private final int chance;
    private final Material[] on = new Material[] {Material.GRASS_BLOCK, Material.DIRT, Material.COARSE_DIRT, Material.SAND, Material.SANDSTONE, Material.SMOOTH_SANDSTONE, Material.ROOTED_DIRT};

    public SmallItem(Material item, int chance) {
        this.item = item;
        this.chance = chance;
    }

    public void build(int realX, int realZ, Random random, LimitedRegion limitedRegion) {
        int amount = random.nextInt(chance) + 1;
        for (int i = 1; i < amount; i++) {
            int x = realX * 16 + random.nextInt(16);
            int z = realZ * 16 + random.nextInt(16);
            for (int y = 255; y > 0; y--) {
                if (limitedRegion.getType(x, y, z).equals(Material.AIR) && canBePlacedOn(limitedRegion.getType(x, y - 1, z))) {
                    limitedRegion.setType(x, y, z, item);
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
