package pl.tuso.xgenerator;

import org.bukkit.*;
import org.bukkit.generator.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class XChunkGenerator extends ChunkGenerator {

    @Override
    public void generateNoise(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData) {
    }

    @Override
    public void generateSurface(WorldInfo worldInfo, Random random, int chunkX, int chunkZ, ChunkGenerator.ChunkData chunkData) {
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
}
