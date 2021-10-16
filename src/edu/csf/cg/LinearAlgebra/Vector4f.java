package edu.csf.cg.LinearAlgebra;

import static edu.csf.cg.LinearAlgebra.Constants.EPS;

public class Vector4f {
    float x;
    float y;
    float z;
    float w;

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vector4f(float[] values) {
        x = values[0];
        y = values[1];
        z = values[2];
        w = values[3];
    }

    public boolean equals(Vector4f other) {
        return Math.abs(x - other.x) <= EPS &&
                Math.abs(y - other.y) <= EPS &&
                Math.abs(z - other.z) <= EPS &&
                Math.abs(w - other.w) <= EPS;
    }

    public void add(Vector4f vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
        this.w += vector.w;
    }

    public void minus(Vector4f vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        this.z -= vector.z;
        this.w -= vector.w;
    }

    public void multiply(float k) {
        x *= k;
        y *= k;
        z *= k;
        w *= k;
    }

    public void divide(float k) {
        assert (k != 0);
        x /= k;
        y /= k;
        z /= k;
        w /= k;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public void normalize() {
        divide(length());
    }

    public float dotProduct(Vector4f vector) {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z + this.w * vector.w;
    }

    public float[] toArray() {
        return new float[]{x, y, z, w};
    }
}
