package pl.tuso.xgenerator.biome.handler;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.math.FastNoiseLite;
import pl.tuso.xgenerator.biome.populator.SmallItem;
import pl.tuso.xgenerator.biome.populator.TreePopulator;

import java.util.Random;

public class RedwoodForestHandler implements BiomeHandler {

    @Override
    public Material[] getSurfaceCrust(Random random) {
        return new Material[] {Material.GREEN_TERRACOTTA};
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
        return Biome.PLAINS;
    }

    @Override
    public void setCustomBiome(World world, int x, int z) {

    }

    @Override
    public double getNoise(WorldInfo worldInfo, int x, int y, int z) {
        n.SetSeed((int) worldInfo.getSeed() * 4);
        n.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);
        n.SetFractalOctaves(8);
        n.SetFrequency(0.025f);
        return n.GetNoise(x, y, z) * 0.9f + 0.5;
    }

}
