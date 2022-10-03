package utils;

public class UtilsFunctions {
    public static double sigmoidFunction(double x) {
        double eulerComponent = Math.pow(Math.E, -x);
        return 1/(1 + eulerComponent);
    }

    public static double sigmoidDerivative(double x) {
        double numeratorValue = Math.pow(Math.E, -x);
        double denominatorValue = Math.pow(1 + numeratorValue, 2);

        return numeratorValue/denominatorValue;
    }
}
