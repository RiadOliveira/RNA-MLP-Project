package utils;

public class UtilsFunctions {
    public static double sigmoidFunction(double x) {
        double eulerComponent = Math.pow(Math.E, -x);
        return 1/(1 + eulerComponent);
    }
}
