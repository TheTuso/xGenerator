package pl.tuso.xgenerator.biome.handler;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.math.FastNoiseLite;
import pl.tuso.xgenerator.biome.populator.SmallItem;
import pl.tuso.xgenerator.biome.populator.TreePopulator;

import java.util.Random;

public class TaigaHandler implements BiomeHandler {

    @Override
    public Material[] getSurfaceCrust(Random random) {
        return new Material[] {Material.GRASS_BLOCK, Material.GRASS_BLOCK, Material.ROOTED_DIRT, Material.COARSE_DIRT, Material.GRASS_BLOCK, Material.PODZOL};
    }

    @Override
    public SmallItem[] smallIteams() {
        return new SmallItem[] {new SmallItem(Material.GRASS, 64), new SmallItem(Material.FERN, 16), new SmallItem(Material.DEAD_BUSH, 16), new SmallItem(Material.PUMPKIN, 3)};
    }

    @Override
    public TreePopulator[] trees() {
        return new TreePopulator[] {new TreePopulator(TreeType.REDWOOD, 16), new TreePopulator(TreeType.TALL_REDWOOD, 8), new TreePopulator(TreeType.MEGA_REDWOOD, 2)};
    }

    @Override
    public Biome getVanillaBiome() {
        return Biome.TAIGA;
    }

    @Override
    public double getNoise(WorldInfo worldInfo, int x, int y, int z) {
        n.SetSeed((int) worldInfo.getSeed() * 4);
        n.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);
        n.SetFractalOctaves(8);
        n.SetFrequency(0.03f);
        return n.GetNoise(x, y, z) * 0.7f + 0.6;
    }

}
