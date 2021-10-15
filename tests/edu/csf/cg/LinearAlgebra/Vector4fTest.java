package edu.csf.cg.LinearAlgebra;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Vector4fTest {

    @Test
    void testAdd01() {
        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
        Vector4f vector2 = new Vector4f(4, 3, 2, 1);
        vector1.add(vector2);
        Vector4f expectedResult = new Vector4f(5, 5, 5, 5);
        Assertions.assertTrue(vector1.equals(expectedResult));
    }

    @Test
    void testAdd02() {
        Vector4f vector1 = new Vector4f(1.25f, 2.5f, 3.75f, 5.25f);
        Vector4f vector2 = new Vector4f(2.5f, 3.25f, 3.75f, 4.5f);
        vector1.add(vector2);
        Vector4f expectedResult = new Vector4f(3.75f, 5.75f, 7.5f, 9.75f);
        Assertions.assertTrue(vector1.equals(expectedResult));
    }

    @Test
    void testMinus01() {
        Vector4f vector1 = new Vector4f(5, 5, 5, 5);
        Vector4f vector2 = new Vector4f(4, 3, 2, 1);
        vector1.minus(vector2);
        Vector4f expectedResult = new Vector4f(1, 2, 3, 4);
        Assertions.assertTrue(vector1.equals(expectedResult));
    }

    @Test
    void testMinus02() {
        Vector4f vector1 = new Vector4f(3.75f, 5.75f, 7.5f, 9.75f);
        Vector4f vector2 = new Vector4f(2.5f, 3.25f, 3.75f, 4.5f);
        vector1.minus(vector2);
        Vector4f expectedResult = new Vector4f(1.25f, 2.5f, 3.75f, 5.25f);
        Assertions.assertTrue(vector1.equals(expectedResult));
    }

    @Test
    void testMultiply01() {
        Vector4f vector = new Vector4f(1, 2, 3, 4);
        float k = 3;
        vector.multiply(k);
        Vector4f expectedResult = new Vector4f(3, 6, 9, 12);
        Assertions.assertTrue(vector.equals(expectedResult));
    }

    @Test
    void testMultiply02() {
        Vector4f vector = new Vector4f(1.25f, 2.5f, 3.75f, 5.25f);
        float k = 3;
        vector.multiply(k);
        Vector4f expectedResult = new Vector4f(3.75f, 7.5f, 11.25f, 15.75f);
        Assertions.assertTrue(vector.equals(expectedResult));
    }

    @Test
    void testDivide01() {
        Vector4f vector = new Vector4f(3, 6, 9, 12);
        float k = 3;
        vector.divide(k);
        Vector4f expectedResult = new Vector4f(1, 2, 3, 4);
        Assertions.assertTrue(vector.equals(expectedResult));
    }

    @Test
    void testDivide02() {
        Vector4f vector = new Vector4f(3.75f, 7.5f, 11.25f, 15.75f);
        float k = 3;
        vector.divide(k);
        Vector4f expectedResult = new Vector4f(1.25f, 2.5f, 3.75f, 5.25f);
        Assertions.assertTrue(vector.equals(expectedResult));
    }

    @Test
    void testDivide03() {
        Vector4f vector = new Vector4f(1, 1, 1, 1);
        float k = 0;
        Assertions.assertThrows(AssertionError.class, () -> vector.divide(k));
    }

    @Test
    void testLength01() {
        Vector4f vector = new Vector4f(1, 2, 3, 4);
        float result = vector.length();
        float expectedResult = (float) Math.sqrt(30);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testLength02() {
        Vector4f vector = new Vector4f(-1.5f, -2.5f, -3.5f, -4.5f);
        float result = vector.length();
        float expectedResult = (float) Math.sqrt(41);
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testDotProduct01() {
        Vector4f vector1 = new Vector4f(1, 2, 3, 4);
        Vector4f vector2 = new Vector4f(4, 3, 2, 1);
        float result = vector1.dotProduct(vector2);
        float expectedResult = 20;
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    void testDotProduct02() {
        Vector4f vector1 = new Vector4f(-1.5f, -2.5f, -3.5f, -4.5f);
        Vector4f vector2 = new Vector4f(-1.5f, -2.5f, -3.5f, 4.5f);
        float result = vector1.dotProduct(vector2);
        float expectedResult = 0.5f;
        Assertions.assertEquals(expectedResult, result);
    }
}