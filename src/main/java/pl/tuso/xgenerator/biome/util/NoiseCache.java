package pl.tuso.xgenerator.biome.util;

import it.unimi.dsi.fastutil.longs.Long2DoubleLinkedOpenHashMap;
import org.bukkit.generator.WorldInfo;
import pl.tuso.xgenerator.biome.Biomes;

public class NoiseCache {
    private final Long2DoubleLinkedOpenHashMap cache;
    private final int cacheCapacity;

    public NoiseCache(int cacheCapacity) {
        this.cache = new Long2DoubleLinkedOpenHashMap(64, 0.25F);
        this.cache.defaultReturnValue(-512);
        this.cacheCapacity = cacheCapacity;
    }

    public double get(Biomes biomes, WorldInfo worldInfo, int x, int y, int z) {
        long l = BlockPos.asLong(x, y ,z);
        synchronized(this.cache) {
            double i = this.cache.get(l);
            //System.out.println(i);
            if (i != -512) {
                return i;
            } else {
                double j = biomes.getHandler().getNoise(worldInfo, x, y, z);
                this.cache.put(l, j);
                if (this.cache.size() > this.cacheCapacity) {
                    for(int k = 0; k < this.cacheCapacity / 64; ++k) {
                        this.cache.removeFirstDouble();
                    }
                }
                return j;
            }
        }
    }
}
