/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core;

import dev.Vec3;

/**
 *
 * @author elect
 */
class func_common {

    public static int log2(int value) {
        return (int) (Math.log(value) / Math.log(2));
    }

    public static Vec3 max_(Vec3 v0, Vec3 v1) {
        return max(v0, v1, new Vec3());
    }

    public static Vec3 max(Vec3 v0, Vec3 v1, Vec3 res) {
        return res.set(Math.max(v0.x, v1.x), Math.max(v0.y, v1.y), Math.max(v0.z, v1.z));
    }

    public static Vec3 min_(Vec3 v0, Vec3 v1) {
        return min(v0, v1, new Vec3());
    }

    public static Vec3 min(Vec3 v0, Vec3 v1, Vec3 res) {
        return res.set(Math.min(v0.x, v1.x), Math.min(v0.y, v1.y), Math.min(v0.z, v1.z));
    }

    /**
     * Compare two floating points for equality within a margin of error.
     *
     * This can be used to compensate for inequality caused by accumulated
     * floating point math errors.
     *
     * The error margin is specified in ULPs (units of least precision). A
     * one-ULP difference means there are no representable floats in between.
     * E.g. 0f and 1.4e-45f are one ULP apart. So are -6.1340704f and -6.13407f.
     * Depending on the number of calculations involved, typically a margin of
     * 1-5 ULPs should be enough.
     *
     * @param expected The expected value.
     * @param actual The actual value.
     * @param maxUlps The maximum difference in ULPs.
     * @return
     */
    public static boolean compareFloatEquals(float expected, float actual, int maxUlps) {
        int expectedBits = Float.floatToIntBits(expected) < 0
                ? 0x80000000 - Float.floatToIntBits(expected) : Float.floatToIntBits(expected);
        int actualBits = Float.floatToIntBits(actual) < 0
                ? 0x80000000 - Float.floatToIntBits(actual) : Float.floatToIntBits(actual);
        int difference = expectedBits > actualBits ? expectedBits - actualBits : actualBits - expectedBits;
        if (difference > maxUlps) {
            System.out.println("expected: " + expected + ", actual: " + actual);
            System.out.println("diff " + difference);
        }
        return !Float.isNaN(expected) && !Float.isNaN(actual) && difference <= maxUlps;
    }

    public static boolean compareDoubleEquals(double expected, double actual, int maxUlps) {
        long expectedBits = Double.doubleToLongBits(expected) < 0
                ? 0x8000000000000000L - Double.doubleToLongBits(expected) : Double.doubleToLongBits(expected);
        long actualBits = Double.doubleToLongBits(actual) < 0
                ? 0x8000000000000000L - Double.doubleToLongBits(actual) : Double.doubleToLongBits(actual);
        long difference = expectedBits > actualBits ? expectedBits - actualBits : actualBits - expectedBits;
        if (difference > maxUlps) {
            System.out.println("expected: " + expected + ", actual: " + actual);
            System.out.println("diff " + difference);
        }
        return !Double.isNaN(expected) && !Double.isNaN(actual) && difference <= maxUlps;
    }
}
