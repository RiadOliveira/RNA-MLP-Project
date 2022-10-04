package networkComponents;

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

    public float[] getNeuronsResultsWithoutActivation() {
        float results[] = new float[neurons.size()];
        for(int ind=0 ; ind<neurons.size() ; ind++) {
            results[ind] = neurons.get(ind).getSumOfParsedInputs();
        }

        return results;
    }

    public float[] getNeuronsResults() {
        float results[] = new float[neurons.size()];
        for(int ind=0 ; ind<neurons.size() ; ind++) {
            results[ind] = neurons.get(ind).getActivationFunctionResult();
        }

        return results;
    }

    public float getErrorOfOutputLayerNeuron(
        float desiredValue, float predictedValue
    ) {
        Perceptron neuron = neurons.get(0);
        float valuesDifference = desiredValue - predictedValue;

        return valuesDifference * UtilsFunctions.activationFunctionDerivative(
            neuron.getSumOfParsedInputs()
        );
    }

    private float getNeuronErrorAdditionalBasedOnPreviousErrorsAndWeights(
        float previousLayerNeuronsErrors[],
        List<Perceptron> previousNeurons,
        int currentNeuronIndex
    ) {
        float additionalError = 0;
        for(
            int previousNeuronsInd=0 ;
            previousNeuronsInd<previousNeurons.size() ;
            previousNeuronsInd++
        ) {
            Perceptron iterationPreviousNeuron =
                previousNeurons.get(previousNeuronsInd);
            float previousNeuronWeightRelatedToIterationNeuron =
                iterationPreviousNeuron.getWeights()[currentNeuronIndex];

            float iterationPreviousNeuronError = 
                previousLayerNeuronsErrors[previousNeuronsInd];

            additionalError +=
                iterationPreviousNeuronError*
                previousNeuronWeightRelatedToIterationNeuron;
        }

        return additionalError;
    }

    public float[] getNeuronsErrors(
        float previousLayerNeuronsErrors[],
        List<Perceptron> previousNeurons
    ) {
        float neuronsErrors[] = new float[neurons.size()];

        for(int neuronInd=0 ; neuronInd<neurons.size() ; neuronInd++) {
            Perceptron iterationNeuron = neurons.get(neuronInd);
            float neuronErrorResult = UtilsFunctions.activationFunctionDerivative(
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
        float errors[], float learningRate
    ) {
        for(int neuronsInd=0 ; neuronsInd<neurons.size() ; neuronsInd++) {
            Perceptron neuron = neurons.get(neuronsInd);

            float neuronWeights[] = neuron.getWeights();
            float neuronInputs[] = neuron.getInputs();
    
            for(int weightsInd=0 ; weightsInd<neuronWeights.length ; weightsInd++) {
                float iterationWeight = neuronWeights[weightsInd];
                float iterationInput = neuronInputs[weightsInd];
    
                neuronWeights[weightsInd] = 
                    iterationWeight + learningRate*errors[neuronsInd]*iterationInput;
            }
        }
    }

    public void setNeuronsInputs(float inputs[][]) {
        for(int ind=0 ; ind<inputs.length ; ind++) {
            neurons.get(ind).setInputs(inputs[ind]);
        }
    }

    public void setSameInputsForEachNeuron(float inputs[]) {
        neurons.forEach(neuron -> neuron.setInputs(inputs));
    }

    public List<Perceptron> getNeurons() {
        return neurons;
    }
}
