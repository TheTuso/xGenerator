package pl.tuso.xgenerator.biome.layer.type;

import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;
import pl.tuso.xgenerator.biome.layer.util.LayerSampleContext;
import pl.tuso.xgenerator.biome.layer.util.LayerSampler;
import pl.tuso.xgenerator.biome.layer.util.NorthWestCoordinateTransformer;

public interface DiagonalCrossSamplingLayer extends ParentedLayer, NorthWestCoordinateTransformer {
    int sample(LayerRandomnessSource context, int sw, int se, int ne, int nw, int center);

    default int sample(LayerSampleContext<?> context, LayerSampler parent, int x, int z) {
        return this.sample(context, parent.sample(this.transformX(x + 0), this.transformZ(z + 2)), //sw
                parent.sample(this.transformX(x + 2), this.transformZ(z + 2)), //se
                parent.sample(this.transformX(x + 2), this.transformZ(z + 0)), //ne
                parent.sample(this.transformX(x + 0), this.transformZ(z + 0)), //nw
                parent.sample(this.transformX(x + 1), this.transformZ(z + 1))); //center
    }
}
