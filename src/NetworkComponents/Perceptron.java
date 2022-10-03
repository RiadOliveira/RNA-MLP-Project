package NetworkComponents;

public class Perceptron {
    private double weights[];
    private double inputs[];

    public Perceptron(int inputsQuantity) {
        weights = new double[inputsQuantity];
        inputs = new double[inputsQuantity];

        for(int ind=0 ; ind<inputsQuantity ; ind++) {
            weights[ind] = 1;
        }
    }

    private Double executeSumOfParsedInputs() {
        Double result = 0d;

        for(int ind=0 ; ind<inputs.length ; ind++) {
            Double iterationWeight = weights[ind];
            Double iterationInput = inputs[ind];

            result += iterationInput*iterationWeight;
        }

        return result;
    }

    public Double getActivationFunctionResult() {
        Double sumOfParsedInputs = executeSumOfParsedInputs();
        return sumOfParsedInputs;
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
