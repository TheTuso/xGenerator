package pl.tuso.xgenerator.biome.handler;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.math.FastNoiseLite;
import pl.tuso.xgenerator.biome.populator.SmallItem;
import pl.tuso.xgenerator.biome.populator.TreePopulator;

import java.util.Random;

public class MessaHandler implements BiomeHandler {

    @Override
    public Material[] getSurfaceCrust(Random random) {
        return new Material[] {Material.ROOTED_DIRT, Material.COARSE_DIRT, Material.SMOOTH_RED_SANDSTONE, Material.RED_SANDSTONE,
                            Material.ORANGE_TERRACOTTA, Material.TERRACOTTA, Material.ROOTED_DIRT, Material.DIRT, Material.COARSE_DIRT,
                            Material.GRASS_BLOCK, Material.BROWN_TERRACOTTA, Material.GRAY_TERRACOTTA};
    }

    @Override
    public SmallItem[] smallIteams() {
        return new SmallItem[] {new SmallItem(Material.GRASS, 32), new SmallItem(Material.FERN, 16), new SmallItem(Material.DEAD_BUSH, 8)};
    }

    @Override
    public TreePopulator[] trees() {
        return new TreePopulator[0];
    }

    @Override
    public Biome getVanillaBiome() {
        return Biome.BADLANDS;
    }

    @Override
    public double getNoise(WorldInfo worldInfo, int x, int y, int z) {
        n.SetSeed((int) worldInfo.getSeed() * 4);
        n.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);
        n.SetFractalOctaves(8);
        n.SetFrequency(0.04f);
        return n.GetNoise(x, y, z) * 0.9f + 1.5;
    }

}
