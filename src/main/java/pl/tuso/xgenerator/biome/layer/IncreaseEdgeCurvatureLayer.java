package pl.tuso.xgenerator.biome.layer;

import pl.tuso.xgenerator.biome.BiomeLayers;
import pl.tuso.xgenerator.biome.layer.type.DiagonalCrossSamplingLayer;
import pl.tuso.xgenerator.biome.layer.util.LayerRandomnessSource;

public enum IncreaseEdgeCurvatureLayer implements DiagonalCrossSamplingLayer {
    INSTANCE;

    @Override
    public int sample(LayerRandomnessSource context, int sw, int se, int ne, int nw, int center) {
        if (!BiomeLayers.isOcean(center) || BiomeLayers.isOcean(nw) && BiomeLayers.isOcean(ne) && BiomeLayers.isOcean(sw) && BiomeLayers.isOcean(se)) {
            if (!BiomeLayers.isOcean(center) && (BiomeLayers.isOcean(nw) || BiomeLayers.isOcean(sw) || BiomeLayers.isOcean(ne) || BiomeLayers.isOcean(se)) && context.nextInt(5) == 0) {
                if (BiomeLayers.isOcean(nw)) {
                    return center == 4 ? 4 : nw;
                }

                if (BiomeLayers.isOcean(sw)) {
                    return center == 4 ? 4 : sw;
                }

                if (BiomeLayers.isOcean(ne)) {
                    return center == 4 ? 4 : ne;
                }

                if (BiomeLayers.isOcean(se)) {
                    return center == 4 ? 4 : se;
                }
            }

            return center;
        } else {
            int i = 1;
            int j = 1;
            if (!BiomeLayers.isOcean(nw) && context.nextInt(i++) == 0) {
                j = nw;
            }

            if (!BiomeLayers.isOcean(ne) && context.nextInt(i++) == 0) {
                j = ne;
            }

            if (!BiomeLayers.isOcean(sw) && context.nextInt(i++) == 0) {
                j = sw;
            }

            if (!BiomeLayers.isOcean(se) && context.nextInt(i++) == 0) {
                j = se;
            }

            if (context.nextInt(3) == 0) {
                return j;
            } else {
                return j == 4 ? 4 : center;
            }
        }
    }
}
