package pl.tuso.xgenerator.biome.handler;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.custombiome.BiomeRegistry;
import pl.tuso.xgenerator.biome.math.FastNoiseLite;
import pl.tuso.xgenerator.biome.populator.SmallItem;
import pl.tuso.xgenerator.biome.populator.TreePopulator;

import java.util.Random;

public interface BiomeHandler {

    BiomeRegistry biomeRegistry = new BiomeRegistry();

    FastNoiseLite n = new FastNoiseLite();

    Material[] getSurfaceCrust(Random random);

    SmallItem[] smallIteams();

    TreePopulator[] trees();

    Biome getVanillaBiome();

    void setCustomBiome(World world, int x, int z);

    double getNoise(WorldInfo worldInfo, int x, int y, int z);
}
