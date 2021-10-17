package pl.tuso.xgenerator.biome.math;

public class Vector2f {
    public float x;
    public float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(float[] f) {
        if (f.length > 1) {
            this.x = f[0];
            this.y = f[1];
        } else {
            x = -1;
            y = -1;
        }
    }

    public float dot(Vector2f v) {
        return (x * v.x + y * v.y);
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void normalize(Vector2f v) {
        float n = (float) (1F / Math.sqrt(x * x + y * y));
        x *= n;
        y *= n;
    }

    public float angle(Vector2f v) {
        double dot = dot(v) / (length() * v.length());
        if (dot < -1.0) dot = -1.0;
        if (dot > 1.0) dot = 1.0;
        return (float) Math.acos(dot);
    }
}
