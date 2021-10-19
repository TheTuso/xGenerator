package pl.tuso.xgenerator.biome.handler;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.math.FastNoiseLite;
import pl.tuso.xgenerator.biome.populator.SmallItem;
import pl.tuso.xgenerator.biome.populator.TreePopulator;

import java.util.Random;

public class RiverHandler implements BiomeHandler {

    @Override
    public Material[] getSurfaceCrust(Random random) {
        return new Material[] {Material.GRASS_BLOCK, Material.DIRT, Material.DIRT};
    }

    @Override
    public SmallItem[] smallIteams() {
        return new SmallItem[0];
    }

    @Override
    public TreePopulator[] trees() {
        return new TreePopulator[0];
    }

    @Override
    public Biome getVanillaBiome() {
        return Biome.RIVER;
    }

    @Override
    public double getNoise(WorldInfo worldInfo, int x, int y, int z) {
        n.SetSeed((int) worldInfo.getSeed() * 4);
        n.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);
        n.SetFractalOctaves(3);
        n.SetFrequency(0.015f);
        return n.GetNoise(x, z) * 0.15 - 0.5;
    }

}
