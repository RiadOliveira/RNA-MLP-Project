package NetworkComponents;

import java.util.ArrayList;
import java.util.List;

import utils.UtilsFunctions;

public class Layer {
    private List<Perceptron> neurons = new ArrayList<>();

    public Layer(int neuronsQuantity, int inputsQuantityForNeurons) {
        for(int ind=0 ; ind<neuronsQuantity ; ind++) {
            neurons.add(new Perceptron(inputsQuantityForNeurons));
        }
    }

    public double[] getNeuronsResultsWithoutActivation() {
        double results[] = new double[neurons.size()];
        for(int ind=0 ; ind<neurons.size() ; ind++) {
            results[ind] = neurons.get(ind).getSumOfParsedInputs();
        }

        return results;
    }

    public double[] getNeuronsResults() {
        double results[] = new double[neurons.size()];
        for(int ind=0 ; ind<neurons.size() ; ind++) {
            results[ind] = neurons.get(ind).getActivationFunctionResult();
        }

        return results;
    }

    public double getErrorOfOutputLayerNeuron(
        double desiredValue, double predictedValue
    ) {
        Perceptron neuron = neurons.get(0);
        double valuesDifference = desiredValue - predictedValue;

        return valuesDifference * UtilsFunctions.activationFunctionDerivative(
            neuron.getSumOfParsedInputs()
        );
    }

    private double getNeuronErrorAdditionalBasedOnPreviousErrorsAndWeights(
        double previousLayerNeuronsErrors[],
        List<Perceptron> previousNeurons,
        int currentNeuronIndex
    ) {
        double additionalError = 0;
        for(
            int previousNeuronsInd=0 ;
            previousNeuronsInd<previousNeurons.size() ;
            previousNeuronsInd++
        ) {
            Perceptron iterationPreviousNeuron =
                previousNeurons.get(previousNeuronsInd);
            double previousNeuronWeightRelatedToIterationNeuron =
                iterationPreviousNeuron.getWeights()[currentNeuronIndex];

            double iterationPreviousNeuronError = 
                previousLayerNeuronsErrors[previousNeuronsInd];

            additionalError +=
                iterationPreviousNeuronError*
                previousNeuronWeightRelatedToIterationNeuron;
        }

        return additionalError;
    }

    public double[] getNeuronsErrors(
        double previousLayerNeuronsErrors[],
        List<Perceptron> previousNeurons
    ) {
        double neuronsErrors[] = new double[neurons.size()];

        for(int neuronInd=0 ; neuronInd<neurons.size() ; neuronInd++) {
            Perceptron iterationNeuron = neurons.get(neuronInd);
            double neuronErrorResult = UtilsFunctions.activationFunctionDerivative(
                iterationNeuron.getSumOfParsedInputs()
            );
            
            neuronErrorResult *= 
                getNeuronErrorAdditionalBasedOnPreviousErrorsAndWeights(
                    previousLayerNeuronsErrors, previousNeurons,
                    neuronInd
                );
            neuronsErrors[neuronInd] = neuronErrorResult;
        }

        return neuronsErrors;
    }

    public void handleUpdateWeightsOfNeurons(
        double errors[], double learningRate
    ) {
        for(int neuronsInd=0 ; neuronsInd<neurons.size() ; neuronsInd++) {
            Perceptron neuron = neurons.get(neuronsInd);

            double neuronWeights[] = neuron.getWeights();
            double neuronInputs[] = neuron.getInputs();
    
            for(int weightsInd=0 ; weightsInd<neuronWeights.length ; weightsInd++) {
                double iterationWeight = neuronWeights[weightsInd];
                double iterationInput = neuronInputs[weightsInd];
    
                neuronWeights[weightsInd] = 
                    iterationWeight + learningRate*errors[neuronsInd]*iterationInput;
            }
        }
    }

    public void setNeuronsInputs(double inputs[][]) {
        for(int ind=0 ; ind<inputs.length ; ind++) {
            neurons.get(ind).setInputs(inputs[ind]);
        }
    }

    public void setSameInputsForEachNeuron(double inputs[]) {
        neurons.forEach(neuron -> neuron.setInputs(inputs));
    }

    public List<Perceptron> getNeurons() {
        return neurons;
    }
}
