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

public class PlainsHandler implements BiomeHandler {

    private int flowerChance = 3;

    @Override
    public Material[] getSurfaceCrust(Random random) {
        return new Material[] {Material.GRASS_BLOCK, Material.DIRT, GenUtils.randMaterial(random, Material.DIRT, Material.STONE)};
    }

    @Override
    public SmallItem[] smallIteams() {
        return new SmallItem[] {new SmallItem(Material.GRASS, 64), new SmallItem(Material.POPPY, flowerChance), new SmallItem(Material.DANDELION, flowerChance),
                new SmallItem(Material.FERN, 16), new SmallItem(Material.ALLIUM, flowerChance), new SmallItem(Material.AZURE_BLUET, flowerChance),
                new SmallItem(Material.RED_TULIP, flowerChance), new SmallItem(Material.WHITE_TULIP, flowerChance), new SmallItem(Material.ORANGE_TULIP, flowerChance),
                new SmallItem(Material.PINK_TULIP, flowerChance), new SmallItem(Material.OXEYE_DAISY, flowerChance), new SmallItem(Material.LILY_OF_THE_VALLEY, flowerChance)};
    }

    @Override
    public TreePopulator[] trees() {
        return new TreePopulator[] {new TreePopulator(TreeType.BIG_TREE, 2)};
    }

    @Override
    public Biome getVanillaBiome() {
        return Biome.PLAINS;
    }

    @Override
    public double getNoise(WorldInfo worldInfo, int x, int y, int z) {
        n.SetSeed((int) worldInfo.getSeed() * 4);
        n.SetNoiseType(FastNoiseLite.NoiseType.OpenSimplex2S);
        n.SetFractalOctaves(8);
        n.SetFrequency(0.025f);
        return n.GetNoise(x, y, z) * 0.25f + 0.06;
    }

}
