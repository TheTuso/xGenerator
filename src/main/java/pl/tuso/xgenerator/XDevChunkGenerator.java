package pl.tuso.xgenerator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.Biomes;
import pl.tuso.xgenerator.biome.populator.DevItemPopulator;
import pl.tuso.xgenerator.biome.populator.ItemPopulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XDevChunkGenerator extends ChunkGenerator {

    public static final Object LOCK = new Object();

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int realX = chunkX * 16 + x;
                int realZ = chunkZ * 16 + z;

                Biomes biomes = Biomes.JUNGLE;

                for (int y = 0; y < 256; y++) {
                    double finalHeight = biomes.getHandler().getNoise(worldInfo, realX, y, realZ);
                    finalHeight -= ((y / 14.5) - 4.5);
                    if (finalHeight > 0) {
                        setBlockSync(chunkData, x, y, z, Material.STONE);
                    }
                }
            }
        }
    }

    @Override
    public void generateSurface(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                int realX = chunkX * 16 + x;
                int realZ = chunkZ * 16 + z;

                Biomes biomes = Biomes.JUNGLE;

                Material[] materials = biomes.getHandler().getSurfaceCrust(random);

                int index = 0;
                for (int y = 256; y >= 0; y--) {
                    if (biomes == Biomes.DESERT || biomes == Biomes.MESSA || biomes == Biomes.SAVANNA || biomes == Biomes.TAIGA) {
                        if (!chunkData.getBlockData(x, y, z).equals(Material.AIR.createBlockData())) {
                            if (y > 48) {
                                setBlockSync(chunkData, x, y, z, materials[y % materials.length]);
                            }
                        }
                    } else {
                        if (!chunkData.getBlockData(x, y, z).equals(Material.AIR.createBlockData())) {
                            if (index < materials.length) {
                                setBlockSync(chunkData, x, y, z, materials[index]);
                                index++;
                            }
                        } else {
                            index = 0;
                        }
                    }
                }

                for (int y = 62; y > 16; y--) {
                    if (chunkData.getBlockData(x, y, z).equals(Material.AIR.createBlockData())) {
                        setBlockSync(chunkData, x, y, z, Material.WATER);
                    }
                }

                for (int y = 62; y > 16; y--) {
                    if (chunkData.getBlockData(x, y, z).equals(Material.WATER.createBlockData()) &&
                            !chunkData.getBlockData(x, y-1, z).equals(Material.WATER.createBlockData())) {
                        setBlockSync(chunkData, x, y - 1, z, Material.DIRT);
                        setBlockSync(chunkData, x, y - 2, z, Material.DIRT);
                        setBlockSync(chunkData, x, y - 3, z, Material.DIRT);
                    }
                }
            }
        }
    }

    @Override
    public void generateBedrock(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData) {
    }

    @Override
    public void generateCaves(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData) {
    }

    @Override
    public BiomeProvider getDefaultBiomeProvider(WorldInfo worldInfo) {
        return new XDevBiomeProvider();
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        List<BlockPopulator> populators = new ArrayList<>();
        populators.add(new DevItemPopulator());
        return populators;
    }

    @Override
    public boolean shouldGenerateNoise() {
        return false;
    }

    @Override
    public boolean shouldGenerateSurface() {
        return false;
    }

    @Override
    public boolean shouldGenerateBedrock() {
        return false;
    }

    @Override
    public boolean shouldGenerateCaves() {
        return false;
    }

    @Override
    public boolean shouldGenerateDecorations() {
        return false;
    }

    @Override
    public boolean shouldGenerateMobs() {
        return false;
    }

    @Override
    public boolean shouldGenerateStructures() {
        return false;
    }

    private static void setBlockSync(ChunkData chunkData, int x, int y, int z, Material material) {
        synchronized (LOCK) {
            chunkData.setBlock(x, y, z, material);
        }
    }
}
