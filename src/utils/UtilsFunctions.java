package utils;

public class UtilsFunctions {
    public static final ActivationFunction ACTIVATION_FUNCTION_SELECTED = ActivationFunction.HYPERBOLIC_TANGENT;

    public static float activationFunction(float x) {
        switch(ACTIVATION_FUNCTION_SELECTED) {
            case LINEAR: return linearFunction(x);
            case LOGISTICS: return logisticsFunction(x);
            case HYPERBOLIC_TANGENT: return hyperbolicTangentFunction(x);
            default: return linearFunction(x);
        }
    }

    public static float activationFunctionDerivative(float x) {
        switch(ACTIVATION_FUNCTION_SELECTED) {
            case LINEAR: return linearDerivative(x);
            case LOGISTICS: return logisticsDerivative(x);
            case HYPERBOLIC_TANGENT: return hyperbolicTangentDerivative(x);
            default: return linearDerivative(x);
        }
    }

    private static float linearFunction(float x) {
        return x;
    }

    private static float linearDerivative(float x) {
        return 1;
    }

    private static float logisticsFunction(float x) {
        float eulerComponent = (float) Math.pow(Math.E, -x);
        return 1/(1 + eulerComponent);
    }

    private static float logisticsDerivative(float x) {
        float numeratorValue = (float) Math.pow(Math.E, -x);
        float denominatorValue = (float) Math.pow(1 + numeratorValue, 2);

        return numeratorValue/denominatorValue;
    }

    private static float hyperbolicTangentFunction(float x) {
        float eulerComponent = (float) Math.pow(Math.E, -2*x);
        return (1 - eulerComponent)/(1 + eulerComponent);
    }

    private static float hyperbolicTangentDerivative(float x) {
        float hyperbolicTangentResult = (float) Math.pow(hyperbolicTangentFunction(x), 2);
        return 1 - hyperbolicTangentResult;
    }
}
