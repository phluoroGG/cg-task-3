package edu.csf.cg.LinearAlgebra;

import java.util.Arrays;
import static edu.csf.cg.LinearAlgebra.Constants.EPS;

public class Matrix3f {
    float[][] matrix;

    public Matrix3f() {
        matrix = new float[3][3];
    }

    public Matrix3f(float m) {
        matrix = new float[3][3];
        for (float[] arr : matrix) {
            Arrays.fill(arr, m);
        }
    }

    public Matrix3f(boolean isIdentity) {
        matrix = new float[3][3];
        if (isIdentity)
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][i] = 1;
        }
    }

    public Matrix3f(float[][] values) {
        matrix = new float[3][3];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(values[i], 0, matrix[i], 0, matrix[0].length);
        }
    }

    public Matrix3f(float[] values) {
        matrix = new float[3][3];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(values, i * matrix.length, matrix[i], 0, matrix[0].length);
        }
    }

    public boolean equals(Matrix3f other) {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                if (Math.abs(this.matrix[i][j] - other.matrix[i][j]) > EPS) {
                    return false;
                }
            }
        }
        return true;
    }

    public Matrix3f copy() {
        return new Matrix3f(this.matrix);
    }

    public void add(Matrix3f matrix) {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                this.matrix[i][j] += matrix.matrix[i][j];
            }
        }
    }

    public void minus(Matrix3f matrix) {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                this.matrix[i][j] -= matrix.matrix[i][j];
            }
        }
    }

    public void multiply(float k) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= k;
            }
        }
    }

    public Vector3f multiply(Vector3f vector) {
        float[] values = new float[matrix.length];
        float[] vectorValues = vector.toArray();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                values[i] += matrix[i][j] * vectorValues[j];
            }
        }
        return new Vector3f(values);
    }

    public void multiply(Matrix3f matrix) {
        float[][] values = new float[this.matrix.length][this.matrix[0].length];
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[0].length; j++) {
                values[i][j] =
                        this.matrix[i][0] * matrix.matrix[0][j] +
                        this.matrix[i][1] * matrix.matrix[1][j] +
                        this.matrix[i][2] * matrix.matrix[2][j];
            }
        }
        for (int i = 0; i < this.matrix.length; i++) {
            System.arraycopy(values[i], 0, this.matrix[i], 0, this.matrix[0].length);
        }
    }

    public void divide(float k) {
        assert (k != 0);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] /= k;
            }
        }
    }

    public void transpose() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (i < j) {
                    float temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }
    }

    private float determinant2(float[][] matrix) {
        return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
    }

    public float determinant() {
        float[] values = new float[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            float[][] minorMatrix = new float[matrix.length - 1][matrix[0].length - 1];
            for (int j = 1; j < matrix.length; j++) {
                int m = 0;
                for (int k = 0; k < matrix[0].length; k++) {
                    if (i != k) {
                        minorMatrix[j - 1][m] = matrix[j][k];
                        m++;
                    }
                }
                values[i] = matrix[0][i] * determinant2(minorMatrix);
            }
        }
        return values[0] - values[1] + values[2];
    }

    public void inverse() {
        float determinant = determinant();
        assert (determinant != 0);
        float[] algebraicComplementValues = new float[matrix.length * matrix[0].length];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                float[][] minorMatrix = new float[matrix.length - 1][matrix[0].length - 1];
                int k = 0;
                for (int i = 0; i < matrix.length; i++) {
                    int m = 0;
                    for (int j = 0; j < matrix[0].length; j++) {
                        if (row != i && col != j) {
                            minorMatrix[k][m] = matrix[i][j];
                            m++;
                        }
                    }
                    if (row != i) {
                        k++;
                    }
                }
                algebraicComplementValues[row * matrix.length + col] = determinant2(minorMatrix);
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if ((i + j) % 2 != 0) {
                    algebraicComplementValues[i * matrix.length  + j] *= -1;
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(algebraicComplementValues, i * matrix.length, matrix[i], 0, matrix[0].length);
        }
        transpose();
        divide(determinant);
    }

    public Vector3f solveGaussian(Vector3f vector) {
        float[][] matrix = copy().matrix;
        float[] values = vector.toArray();
        boolean[] emptyCols = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            int swapped = i + 1;
            float divider;
            while (true) {
                divider = matrix[i][i];
                if (divider == 0) {
                    if (swapped == matrix.length) {
                        emptyCols[i] = true;
                        for (int j = 0; j < i; j++) {
                            matrix[j][i] = 0;
                        }
                        break;
                    } else {
                        float[] tempArr = new float[matrix.length];
                        System.arraycopy(matrix[i], 0, tempArr, 0, matrix[0].length);
                        System.arraycopy(matrix[swapped], 0, matrix[i], 0, matrix[0].length);
                        System.arraycopy(tempArr, 0, matrix[swapped], 0, matrix[0].length);
                        float temp = values[i];
                        values[i] = values[swapped];
                        values[swapped] = temp;
                        swapped++;
                    }
                } else {
                    break;
                }
            }
            if (emptyCols[i]) {
                continue;
            }
            for (int j = i; j < matrix[0].length; j++) {
                matrix[i][j] /= divider;
            }
            values[i] /= divider;
            for (int j = 0; j < matrix.length; j++) {
                if (j == i) {
                    continue;
                }
                float factor = matrix[j][i];
                for (int k = i; k < matrix[0].length; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
                values[j] -= factor * values[i];
            }
        }
        for (int i = 0; i < emptyCols.length; i++) {
            assert !emptyCols[i] || (values[i] == 0);
        }
        return new Vector3f(values);
    }
}
