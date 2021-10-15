package pl.tuso.xgenerator.biome.layer.type;

import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;
import pl.tuso.xgenerator.biome.layer.util.LayerSampleContext;
import pl.tuso.xgenerator.biome.layer.util.LayerSampler;
import pl.tuso.xgenerator.biome.layer.util.NorthWestCoordinateTransformer;

public interface SouthEastSamplingLayer extends ParentedLayer, NorthWestCoordinateTransformer {
    int sample(LayerRandomnessSource context, int se);

    default int sample(LayerSampleContext<?> context, LayerSampler parent, int x, int z) {
        int i = parent.sample(this.transformX(x + 1), this.transformZ(z + 1));
        return this.sample(context, i);
    }
}
