package networkComponents;

import utils.UtilsFunctions;

public class Perceptron {
    private float weights[];
    private float inputs[];

    public Perceptron(int inputsQuantity) {
        weights = new float[inputsQuantity];
        inputs = new float[inputsQuantity];

        for(int ind=0 ; ind<inputsQuantity ; ind++) {
            float generatedValue = (float) Math.abs(Math.random() - 0.5);
            weights[ind] = ind % 2 == 0 ? generatedValue : -generatedValue;
        }
    }

    public void updateWeightsErrorsBasedOnFactor(
        float factor, float networkLearningRate
    ) {
        for(int ind=0 ; ind<weights.length ; ind++) {
            float iterationInput = inputs[ind];
            float iterationWeight = weights[ind];

            float weightError = factor*iterationInput;
            weights[ind] = iterationWeight - networkLearningRate*weightError;
        }
    }

    public float getSumOfParsedInputs() {
        float result = 0f;

        for(int ind=0 ; ind<inputs.length ; ind++) {
            float iterationWeight = weights[ind];
            float iterationInput = inputs[ind];

            result += iterationInput*iterationWeight;
        }

        return result;
    }

    public float getActivationFunctionResult() {
        float sumOfParsedInputs = getSumOfParsedInputs();
        return UtilsFunctions.activationFunction(sumOfParsedInputs);
    }

    public float[] getWeights() {
        return weights;
    }

    public void setWeights(float weights[]) {
        this.weights = weights;
    }

    public float[] getInputs() {
        return inputs;
    }

    public void setInputs(float inputs[]) {
        this.inputs = inputs;
    }
}
