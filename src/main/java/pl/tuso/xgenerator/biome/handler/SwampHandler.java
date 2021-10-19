package pl.tuso.xgenerator.biome.handler;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.math.FastNoiseLite;
import pl.tuso.xgenerator.biome.populator.SmallItem;
import pl.tuso.xgenerator.biome.populator.TreePopulator;
import pl.tuso.xgenerator.biome.util.GenUtils;

import java.util.Random;

public class SwampHandler implements BiomeHandler {

    @Override
    public Material[] getSurfaceCrust(Random random) {
        return new Material[] {Material.GRASS_BLOCK, Material.DIRT, Material.DIRT, GenUtils.randMaterial(random, Material.DIRT, Material.STONE)};
    }

    @Override
    public SmallItem[] smallIteams() {
        return new SmallItem[] {new SmallItem(Material.GRASS, 64), new SmallItem(Material.FERN, 16), new SmallItem(Material.DEAD_BUSH, 8), new SmallItem(Material.BLUE_ORCHID, 8)};
    }

    @Override
    public TreePopulator[] trees() {
        return new TreePopulator[] {new TreePopulator(TreeType.SWAMP, 8)};
    }

    @Override
    public Biome getVanillaBiome() {
        return Biome.SWAMP;
    }

    @Override
    public double getNoise(WorldInfo worldInfo, int x, int y, int z) {
        n.SetSeed((int) worldInfo.getSeed() * 4);
        n.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);
        n.SetFractalOctaves(8);
        n.SetFrequency(0.04f);
        return n.GetNoise(x, y, z) * 0.25f - 0.2;
    }

}
