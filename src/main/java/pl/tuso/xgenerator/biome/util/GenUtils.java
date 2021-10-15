package pl.tuso.xgenerator.biome.util;

import org.bukkit.Material;

import java.util.Random;

public class GenUtils {
    public static Material randMaterial(Random random, Material... materials) {
        return materials[random.nextInt(materials.length)];
    }
}
