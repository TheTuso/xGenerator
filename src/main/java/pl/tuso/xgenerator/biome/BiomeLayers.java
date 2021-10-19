package pl.tuso.xgenerator.biome;

import pl.tuso.xgenerator.biome.layer.*;
import pl.tuso.xgenerator.biome.layer.type.ParentedLayer;
import pl.tuso.xgenerator.biome.layer.util.*;
import pl.tuso.xgenerator.biome.source.BiomeLayerSampler;

import java.util.function.LongFunction;

public class BiomeLayers {

    private static <T extends LayerSampler, C extends LayerSampleContext<T>> LayerFactory<T> stack(long seed, ParentedLayer layer, LayerFactory<T> parent, int count, LongFunction<C> contextProvider) {
        LayerFactory<T> layerFactory = parent;

        for (int i = 0; i < count; ++i) {
            layerFactory = layer.create(contextProvider.apply(seed + i), layerFactory);
        }

        return layerFactory;
    }

    private static <T extends LayerSampler, C extends LayerSampleContext<T>> LayerFactory<T> build(int biomeSize, int riverSize, LongFunction<C> contextProvider) {
        LayerFactory<T> layerFactory = ContinentLayer.INSTANCE.create(contextProvider.apply(1));
        layerFactory = ScaleLayer.FUZZY.create(contextProvider.apply(2000), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create(contextProvider.apply(1), layerFactory);
        layerFactory = ScaleLayer.NORMAL.create(contextProvider.apply(2001), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create(contextProvider.apply(2), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create(contextProvider.apply(50), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create(contextProvider.apply(70), layerFactory);
        layerFactory = AddColdClimatesLayer.INSTANCE.create(contextProvider.apply(2), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create(contextProvider.apply(3), layerFactory);
        layerFactory = AddClimateLayers.AddTemperateBiomesLayer.INSTANCE.create(contextProvider.apply(2), layerFactory);
        layerFactory = AddClimateLayers.AddCoolBiomesLayer.INSTANCE.create(contextProvider.apply(2), layerFactory);
        layerFactory = AddClimateLayers.AddSpecialBiomesLayer.INSTANCE.create(contextProvider.apply(3), layerFactory);
        layerFactory = ScaleLayer.NORMAL.create(contextProvider.apply(2002), layerFactory);
        layerFactory = ScaleLayer.NORMAL.create(contextProvider.apply(2003), layerFactory);
        layerFactory = IncreaseEdgeCurvatureLayer.INSTANCE.create(contextProvider.apply(4), layerFactory);
        layerFactory = stack(1000, ScaleLayer.NORMAL, layerFactory, 0, contextProvider);
        LayerFactory<T> layerFactory2 = stack(1000, ScaleLayer.NORMAL, layerFactory, 0, contextProvider);
        layerFactory2 = SimpleLandNoiseLayer.INSTANCE.create(contextProvider.apply(100), layerFactory2);
        LayerFactory<T> layerFactory3 = new AddBaseBiomesLayer().create(contextProvider.apply(200), layerFactory);
        layerFactory3 = stack(1000, ScaleLayer.NORMAL, layerFactory3, 2, contextProvider);
        layerFactory2 = stack(1000, ScaleLayer.NORMAL, layerFactory2, 2, contextProvider);
        layerFactory2 = stack(1000, ScaleLayer.NORMAL, layerFactory2, riverSize, contextProvider);
        layerFactory2 = NoiseToRiverLayer.INSTANCE.create(contextProvider.apply(1), layerFactory2);
        layerFactory2 = ScaleLayer.NORMAL.create(contextProvider.apply(2004), layerFactory2);//
        layerFactory2 = ScaleLayer.NORMAL.create(contextProvider.apply(2005), layerFactory2);//
        layerFactory2 = SmoothLayer.INSTANCE.create(contextProvider.apply(1000), layerFactory2);

        for (int i = 0; i < biomeSize; ++i) {
            layerFactory3 = ScaleLayer.NORMAL.create(contextProvider.apply(1000 + i), layerFactory3);
            if (i == 0) {
                layerFactory3 = IncreaseEdgeCurvatureLayer.INSTANCE.create(contextProvider.apply(3), layerFactory3);
            }
        }

        layerFactory3 = SmoothLayer.INSTANCE.create(contextProvider.apply(1000), layerFactory3);
        layerFactory3 = ApplyRiverLayer.INSTANCE.create(contextProvider.apply(100), layerFactory3, layerFactory2);
        return layerFactory3;
    }

    public static BiomeLayerSampler build(long seed, int biomeSize, int riverSize) {
        LayerFactory<CachingLayerSampler> layerFactory = build(biomeSize, riverSize, (salt) -> new CachingLayerContext(32, seed, salt));
        return new BiomeLayerSampler(layerFactory);
    }

    public static boolean isOcean(int id) {
        return id == 0;
    }
}
