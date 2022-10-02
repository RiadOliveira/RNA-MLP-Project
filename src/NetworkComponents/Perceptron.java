package NetworkComponents;
import java.util.ArrayList;
import java.util.List;

public class Perceptron {
    private List<Double> weights = new ArrayList<>();
    private List<Double> inputs = new ArrayList<>();

    public Perceptron() {
        super();
    }

    private Double executeSumOfParsedInputs() {
        Double result = 0d;

        for(int ind=0 ; ind<inputs.size() ; ind++) {
            Double iterationWeight = weights.get(ind);
            Double iterationInput = inputs.get(ind);

            result += iterationInput*iterationWeight;
        }

        return result;
    }

    public Double getActivationFunctionResult() {
        Double sumOfParsedInputs = executeSumOfParsedInputs();
        return sumOfParsedInputs;
    }

    public List<Double> getWeights() {
        return weights;
    }

    public void setWeights(List<Double> weights) {
        this.weights = weights;
    }

    public List<Double> getInputs() {
        return inputs;
    }

    public void setInputs(List<Double> inputs) {
        this.inputs = inputs;
    }
}
