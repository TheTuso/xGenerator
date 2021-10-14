package pl.tuso.xgenerator.biome.layer.util;

public interface LayerSampleContext<R extends LayerSampler> extends LayerRandomnessSource {
    void initSeed(long x, long y);

    R createSampler(LayerOperator operator);

    default R createSampler(LayerOperator operator, R parent) {
        return this.createSampler(operator);
    }

    default R createSampler(LayerOperator operator, R firstParent, R secondParent) {
        return this.createSampler(operator);
    }

    default int choose(int a, int b) {
        return this.nextInt(2) == 0 ? a : b;
    }

    default int choose(int a, int b, int c, int d) {
        int i = this.nextInt(4);
        return i == 0 ? a : i == 1 ? b : i == 2 ? c : d;
    }
}
