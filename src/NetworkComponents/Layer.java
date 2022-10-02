package NetworkComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Layer {
    private List<Perceptron> neurons;

    public Layer(int neuronsQuantity) {
        neurons = new ArrayList<>(neuronsQuantity);
    }

    public List<Double> getNeuronsResults() {
        return neurons.stream().map(
            neuron -> neuron.getActivationFunctionResult()
        ).collect(Collectors.toList());
    }

    public void setNeuronsInputs(List<Double> inputs) {
        neurons.forEach(neuron -> neuron.setInputs(inputs));
    }
}
