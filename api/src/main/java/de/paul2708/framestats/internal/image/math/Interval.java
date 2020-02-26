package de.paul2708.framestats.internal.image.math;

/**
 * Class description.
 *
 * @author Paul2708
 */
public class Interval {

    private final double a;
    private final double b;

    private Interval(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public static Interval of(double a, double b) {
        return new Interval(a, b);
    }
}
