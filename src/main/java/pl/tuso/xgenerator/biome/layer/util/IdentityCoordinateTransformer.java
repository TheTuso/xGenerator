package pl.tuso.xgenerator.biome.layer.util;

public interface IdentityCoordinateTransformer extends CoordinateTransformer {
    default int transformX(int x) {
        return x;
    }

    default int transformZ(int y) {
        return y;
    }
}
