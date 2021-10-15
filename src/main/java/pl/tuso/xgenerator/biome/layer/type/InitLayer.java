package pl.tuso.xgenerator.biome.layer.type;

import pl.tuso.xgenerator.biome.layer.util.LayerFactory;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;
import pl.tuso.xgenerator.biome.layer.util.LayerSampleContext;
import pl.tuso.xgenerator.biome.layer.util.LayerSampler;

public interface InitLayer {
    default <R extends LayerSampler> LayerFactory<R> create(LayerSampleContext<R> context) {
        return () -> context.createSampler((x, z) -> {
            context.initSeed(x, z);
            return this.sample(context, x, z);
        });
    }

    int sample(LayerRandomnessSource context, int x, int y);
}
