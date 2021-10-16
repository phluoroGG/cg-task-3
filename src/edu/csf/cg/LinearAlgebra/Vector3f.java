package edu.csf.cg.LinearAlgebra;

import static edu.csf.cg.LinearAlgebra.Constants.EPS;

public class Vector3f {
    float x;
    float y;
    float z;

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3f(float[] values) {
        x = values[0];
        y = values[1];
        z = values[2];
    }

    public boolean equals(Vector3f other) {
        return Math.abs(x - other.x) <= EPS &&
                Math.abs(y - other.y) <= EPS &&
                Math.abs(z - other.z) <= EPS;
    }

    public void add(Vector3f vector) {
        this.x += vector.x;
        this.y += vector.y;
        this.z += vector.z;
    }

    public void minus(Vector3f vector) {
        this.x -= vector.x;
        this.y -= vector.y;
        this.z -= vector.z;
    }

    public void multiply(float k) {
        x *= k;
        y *= k;
        z *= k;
    }

    public void divide(float k) {
        assert (k != 0);
        x /= k;
        y /= k;
        z /= k;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y + z * z);
    }

    public void normalize() {
        divide(length());
    }

    public float dotProduct(Vector3f vector) {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z;
    }

    public Vector3f crossProduct(Vector3f vector) {
        return new Vector3f(
                this.y * vector.z - this.z * vector.y,
                this.z * vector.x - this.x * vector.z,
                this.x * vector.y - this.y * vector.x);
    }

    public float[] toArray() {
        return new float[]{x, y, z};
    }
}
