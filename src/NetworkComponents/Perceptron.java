package NetworkComponents;

import utils.UtilsFunctions;

public class Perceptron {
    private double weights[];
    private double inputs[];

    public Perceptron(int inputsQuantity) {
        weights = new double[inputsQuantity];
        inputs = new double[inputsQuantity];

        for(int ind=0 ; ind<inputsQuantity ; ind++) {
            double generatedValue = Math.abs(Math.random() - 0.5);
            weights[ind] = ind % 2 == 0 ? generatedValue : -generatedValue;
        }
    }

    public void updateWeightsErrorsBasedOnFactor(
        double factor, double networkLearningRate
    ) {
        for(int ind=0 ; ind<weights.length ; ind++) {
            double iterationInput = inputs[ind];
            double iterationWeight = weights[ind];

            double weightError = factor*iterationInput;
            weights[ind] = iterationWeight - networkLearningRate*weightError;
        }
    }

    public double getSumOfParsedInputs() {
        double result = 0d;

        for(int ind=0 ; ind<inputs.length ; ind++) {
            double iterationWeight = weights[ind];
            double iterationInput = inputs[ind];

            result += iterationInput*iterationWeight;
        }

        return result;
    }

    public double getActivationFunctionResult() {
        double sumOfParsedInputs = getSumOfParsedInputs();
        return UtilsFunctions.activationFunction(sumOfParsedInputs);
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double weights[]) {
        this.weights = weights;
    }

    public double[] getInputs() {
        return inputs;
    }

    public void setInputs(double inputs[]) {
        this.inputs = inputs;
    }
}
