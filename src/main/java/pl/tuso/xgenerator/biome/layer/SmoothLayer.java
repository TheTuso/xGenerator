package pl.tuso.xgenerator.biome.layer;

import pl.tuso.xgenerator.biome.layer.type.CrossSamplingLayer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;

public enum SmoothLayer implements CrossSamplingLayer {
    INSTANCE;

    @Override
    public int sample(LayerRandomnessSource context, int n, int e, int s, int w, int center) {
        boolean bl = e == w;
        boolean bl2 = n == s;
        if (bl == bl2) {
            if (bl) {
                return context.nextInt(2) == 0 ? w : n;
            } else {
                return center;
            }
        } else {
            return bl ? w : n;
        }
    }
}
