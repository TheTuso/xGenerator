package pl.tuso.xgenerator.biome.handler;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.math.FastNoiseLite;

import java.util.Random;

public interface BiomeHandler {

    FastNoiseLite n = new FastNoiseLite();

    Material[] getSurfaceCrust(Random random);

    Biome getVanillaBiome();

    void setCustomBiome();

    double getNoise(WorldInfo worldInfo, int x, int y, int z);
}
