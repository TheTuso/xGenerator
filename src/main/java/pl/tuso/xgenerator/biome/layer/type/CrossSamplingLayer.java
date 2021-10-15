package pl.tuso.xgenerator.biome.layer.type;

import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;
import pl.tuso.xgenerator.biome.layer.util.LayerSampleContext;
import pl.tuso.xgenerator.biome.layer.util.LayerSampler;
import pl.tuso.xgenerator.biome.layer.util.NorthWestCoordinateTransformer;

public interface CrossSamplingLayer extends ParentedLayer, NorthWestCoordinateTransformer {
    int sample(LayerRandomnessSource context, int n, int e, int s, int w, int center);

    default int sample(LayerSampleContext<?> context, LayerSampler parent, int x, int z) {
        return this.sample(context, parent.sample(this.transformX(x + 1), this.transformZ(z + 0)), //n
                parent.sample(this.transformX(x + 2), this.transformZ(z + 1)), //e
                parent.sample(this.transformX(x + 1), this.transformZ(z + 2)), //s
                parent.sample(this.transformX(x + 0), this.transformZ(z + 1)), //w
                parent.sample(this.transformX(x + 1), this.transformZ(z + 1))); //center
    }
}
