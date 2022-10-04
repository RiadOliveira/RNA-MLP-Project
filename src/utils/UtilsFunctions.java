package utils;

public class UtilsFunctions {
    private static final ActivationFunction ACTIVATION_FUNCTION_SELECTED = ActivationFunction.LINEAR;

    public static double activationFunction(double x) {
        switch(ACTIVATION_FUNCTION_SELECTED) {
            case LINEAR: return linearFunction(x);
            case LOGISTICS: return logisticsFunction(x);
            case HYPERBOLIC_TANGENT: return hyperbolicTangentFunction(x);
            default: return linearFunction(x);
        }
    }

    public static double activationFunctionDerivative(double x) {
        switch(ACTIVATION_FUNCTION_SELECTED) {
            case LINEAR: return linearDerivative(x);
            case LOGISTICS: return logisticsDerivative(x);
            case HYPERBOLIC_TANGENT: return hyperbolicTangentDerivative(x);
            default: return linearDerivative(x);
        }
    }

    private static double linearFunction(double x) {
        return x;
    }

    private static double linearDerivative(double x) {
        return 1;
    }

    private static double logisticsFunction(double x) {
        double eulerComponent = Math.pow(Math.E, -x);
        return 1/(1 + eulerComponent);
    }

    private static double logisticsDerivative(double x) {
        double numeratorValue = Math.pow(Math.E, -x);
        double denominatorValue = Math.pow(1 + numeratorValue, 2);

        return numeratorValue/denominatorValue;
    }

    private static double hyperbolicTangentFunction(double x) {
        double eulerComponent = Math.pow(Math.E, -2*x);
        return (1 - eulerComponent)/(1 + eulerComponent);
    }

    private static double hyperbolicTangentDerivative(double x) {
        return 1 - Math.pow(hyperbolicTangentFunction(x), 2);
    }
}
