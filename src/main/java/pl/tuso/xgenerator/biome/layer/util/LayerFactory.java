package pl.tuso.xgenerator.biome.layer.util;

public interface LayerFactory<A extends LayerSampler> {
    A make();
}
