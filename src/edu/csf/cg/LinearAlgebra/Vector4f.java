package edu.csf.cg.LinearAlgebra;

import static edu.csf.cg.LinearAlgebra.Constants.eps;

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
        return Math.abs(x - other.x) <= eps &&
                Math.abs(y - other.y) <= eps &&
                Math.abs(z - other.z) <= eps &&
                Math.abs(w - other.w) <= eps;
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
