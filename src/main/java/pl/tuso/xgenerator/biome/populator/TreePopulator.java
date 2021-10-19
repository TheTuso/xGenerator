package pl.tuso.xgenerator.biome.populator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.generator.LimitedRegion;

import java.util.Random;

public class TreePopulator {
    private final TreeType tree;
    private final int chance;
    private final Material[] on = new Material[] {Material.GRASS_BLOCK, Material.DIRT};

    public TreePopulator(TreeType tree, int chance) {
        this.tree = tree;
        this.chance = chance;
    }

    public void build(int chunkX, int chunkZ, Random random, LimitedRegion limitedRegion) {
        int amount = random.nextInt(chance) + 1;
        for (int i = 1; i < amount; i++) {
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            for (int y = 255; y > 0; y--) {
                if (limitedRegion.getType(x, y, z).equals(Material.AIR) && canBePlacedOn(limitedRegion.getType(x, y - 1, z))) {
                    limitedRegion.generateTree(new Location(null, x, y, z), random, tree);
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
