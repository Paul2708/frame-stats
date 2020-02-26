package de.paul2708.framestats.internal.image.math;

import java.util.function.Function;

/**
 *
 * @author Paul2708
 */
public final class LinearTransformation {

    public static Function<Double, Double> transform(Interval from, Interval to) {
        double a = from.getA();
        double b = from.getB();

        double alpha = to.getA();
        double beta = to.getB();

        return x -> alpha + ((beta - alpha) / (b - a)) * (x - a);
    }
}