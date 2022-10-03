package NetworkComponents;

import java.util.ArrayList;
import java.util.List;

public class Layer {
    private List<Perceptron> neurons = new ArrayList<>();

    public Layer(int neuronsQuantity, int inputsQuantityForNeurons) {
        for(int ind=0 ; ind<neuronsQuantity ; ind++) {
            neurons.add(new Perceptron(inputsQuantityForNeurons));
        }
    }

    public double[] getNeuronsResults() {
        double results[] = new double[neurons.size()];

        for(int ind=0 ; ind<neurons.size() ; ind++) {
            results[ind] = neurons.get(ind).getActivationFunctionResult();
        }

        return results;
    }

    public void setNeuronsInputs(double inputs[][]) {
        for(int ind=0 ; ind<inputs.length ; ind++) {
            neurons.get(ind).setInputs(inputs[ind]);
        }
    }

    public void setSameInputsForEachNeuron(double inputs[]) {
        neurons.forEach(neuron -> neuron.setInputs(inputs));
    }
}
