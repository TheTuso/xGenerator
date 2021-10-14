package pl.tuso.xgenerator.biome.layer.util;

import it.unimi.dsi.fastutil.longs.Long2IntLinkedOpenHashMap;

public class CachingLayerContext implements LayerSampleContext<CachingLayerSampler> {
    private final Long2IntLinkedOpenHashMap cache;
    private final int cacheCapacity;
    private final long worldSeed;
    private long localSeed;

    public CachingLayerContext(int cacheCapacity, long seed, long salt) {
        this.worldSeed = addSalt(seed, salt);
        this.cache = new Long2IntLinkedOpenHashMap(16, 0.25F);
        this.cache.defaultReturnValue(-2147483648);
        this.cacheCapacity = cacheCapacity;
    }

    public CachingLayerSampler createSampler(LayerOperator layerOperator) {
        return new CachingLayerSampler(this.cache, this.cacheCapacity, layerOperator);
    }

    public CachingLayerSampler createSampler(LayerOperator layerOperator, CachingLayerSampler cachingLayerSampler) {
        return new CachingLayerSampler(this.cache, Math.min(1024, cachingLayerSampler.getCapacity() * 4), layerOperator);
    }

    public CachingLayerSampler createSampler(LayerOperator layerOperator, CachingLayerSampler cachingLayerSampler, CachingLayerSampler cachingLayerSampler2) {
        return new CachingLayerSampler(this.cache, Math.min(1024, Math.max(cachingLayerSampler.getCapacity(), cachingLayerSampler2.getCapacity()) * 4), layerOperator);
    }

    public void initSeed(long x, long y) {
        long l = this.worldSeed;
        l = SeedMixer.mixSeed(l, x);
        l = SeedMixer.mixSeed(l, y);
        l = SeedMixer.mixSeed(l, x);
        l = SeedMixer.mixSeed(l, y);
        this.localSeed = l;
    }

    public int nextInt(int bound) {
        int i = (int) Math.floorMod(this.localSeed >> 24, bound);
        this.localSeed = SeedMixer.mixSeed(this.localSeed, this.worldSeed);
        return i;
    }

    private static long addSalt(long seed, long salt) {
        long l = SeedMixer.mixSeed(salt, salt);
        l = SeedMixer.mixSeed(l, salt);
        l = SeedMixer.mixSeed(l, salt);
        long m = SeedMixer.mixSeed(seed, l);
        m = SeedMixer.mixSeed(m, l);
        m = SeedMixer.mixSeed(m, l);
        return m;
    }
}
