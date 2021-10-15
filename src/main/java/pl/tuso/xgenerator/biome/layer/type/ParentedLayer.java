package pl.tuso.xgenerator.biome.layer.type;

import pl.tuso.xgenerator.biome.layer.util.CoordinateTransformer;
import pl.tuso.xgenerator.biome.layer.util.LayerFactory;
import pl.tuso.xgenerator.biome.layer.util.LayerSampleContext;
import pl.tuso.xgenerator.biome.layer.util.LayerSampler;

public interface ParentedLayer extends CoordinateTransformer {
    default <R extends LayerSampler> LayerFactory<R> create(LayerSampleContext<R> context, LayerFactory<R> parent) {
        return () -> {
            R layerSampler = parent.make();
            return context.createSampler((x, z) -> {
                context.initSeed(x, z);
                return this.sample(context, layerSampler, x, z);
            }, layerSampler);
        };
    }

    int sample(LayerSampleContext<?> context, LayerSampler parent, int x, int z);
}
