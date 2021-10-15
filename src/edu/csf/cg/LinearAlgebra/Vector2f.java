package edu.csf.cg.LinearAlgebra;

import static edu.csf.cg.LinearAlgebra.Constants.eps;

public class Vector2f {
    float x;
    float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(float[] values) {
        x = values[0];
        y = values[1];
    }

    public boolean equals(Vector2f other) {
        return Math.abs(x - other.x) <= eps &&
                Math.abs(y - other.y) <= eps;
    }

    public void add(Vector2f vector) {
        this.x += vector.x;
        this.y += vector.y;
    }

    public void minus(Vector2f vector) {
        this.x -= vector.x;
        this.y -= vector.y;
    }

    public void multiply(float k) {
        x *= k;
        y *= k;
    }

    public void divide(float k) {
        assert (k != 0);
        x /= k;
        y /= k;
    }

    public float length() {
        return (float) Math.sqrt(x * x + y * y);
    }

    public void normalize() {
        divide(length());
    }

    public float dotProduct(Vector2f vector) {
        return this.x * vector.x + this.y * vector.y;
    }

    public float[] toArray() {
        return new float[]{x, y};
    }
}
