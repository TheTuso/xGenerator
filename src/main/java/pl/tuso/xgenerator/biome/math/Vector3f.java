package pl.tuso.xgenerator.biome.math;

public class Vector3f {
    public float x;
    public float y;
    public float z;

    public Vector3f(float x1, float y1, float z1) {
        x = x1;
        y = y1;
        z = z1;
    }

    public Vector3f(float[] i) {
        if (i.length > 2) {
            x = i[0];
            y = i[1];
            z = i[2];
        } else {
            x = -1;
            y = -1;
            z = -1;
        }
    }

    public float lengthSquared() {
        return (x*x + y*y + z*z);
    }

    public float length() {
        return (float)Math.sqrt(this.lengthSquared());
    }

    public float dot(Vector3f v) {
        return (x * v.x + y * v.y + z * v.z);
    }

    public void normalize(Vector3f v) {
        float n = (float) (1.0 / Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z));
        x = v.x * n;
        y = v.y * n;
        z = v.z * n;
    }

    public void normalize() {
        float n = (float) (1.0 / Math.sqrt(x * x + y * y + z * z));
        x *= n;
        y *= n;
        z *= n;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }
}
