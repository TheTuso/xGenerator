package pl.tuso.xgenerator.biome.layer.util;

public interface NorthWestCoordinateTransformer extends CoordinateTransformer {
    default int transdormX(int x) {
        return x - 1;
    }

    default int transdormZ(int y) {
        return y - 1;
    }
}
