package pl.tuso.xgenerator.biome.layer.type;

import pl.tuso.xgenerator.biome.layer.util.IdentityCoordinateTransformer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;
import pl.tuso.xgenerator.biome.layer.util.LayerSampleContext;
import pl.tuso.xgenerator.biome.layer.util.LayerSampler;

public interface IdentitySamplingLayer extends ParentedLayer, IdentityCoordinateTransformer {
    int sample(LayerRandomnessSource context, int value);

    default int sample(LayerSampleContext<?> context, LayerSampler parent, int x, int z) {
        return this.sample(context, parent.sample(this.transformX(x), this.transformZ(z)));
    }
}
