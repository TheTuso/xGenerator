package pl.tuso.xgenerator;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.Biomes;

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

                Biomes biomes = Biomes.FOREST;

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

                Biomes biomes = Biomes.FOREST;

                Material[] materials = biomes.getHandler().getSurfaceCrust(random);

                int index = 0;
                for (int y = 256; y >= 0; y--) {
                    if (!chunkData.getBlockData(x, y, z).equals(Material.AIR.createBlockData())) {
                        if (index < materials.length) {
                            setBlockSync(chunkData, x, y, z, materials[index]);
                            index++;
                        }
                    } else {
                        index = 0;
                    }
                }

                for (int y = 62; y > 16; y--) {
                    if (chunkData.getBlockData(x, y, z).equals(Material.AIR.createBlockData())) {
                        setBlockSync(chunkData, x, y, z, Material.WATER);
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
        return null;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return new ArrayList<BlockPopulator>();
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
