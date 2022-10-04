package NetworkComponents;
import java.util.ArrayList;
import java.util.List;

import utils.UtilsFunctions;

public class NeuralNetwork {
    private TrainingData[] trainingDatas;

    private final double LEARNING_RATE = 0.40;
    private final double TOLERANCE_RATE = 0.00001;

    private List<Layer> layers = new ArrayList<>();
    private int hiddenLayersQuantity;
    private int[] neuronsQuantityForEachHiddenLayer;

    public NeuralNetwork(int[] neuronsQuantityForEachHiddenLayer) {
        this.trainingDatas = TrainingData.getDefaultTrainingData();

        this.hiddenLayersQuantity = neuronsQuantityForEachHiddenLayer.length;
        this.neuronsQuantityForEachHiddenLayer = neuronsQuantityForEachHiddenLayer;
    }

    public void executeNetworkLearning(int epochStopQuantity) {
        initializeNetworkLayers();

        for(int ind=0 ; ind<epochStopQuantity ; ind++) {
            iterateThroughOneEpoch();
        }
    }

    private double executeNetworkIteration() {
        double neuronsResult[] = null;

        for(int ind=0 ; ind<layers.size() ; ind++) {
            Layer layer = layers.get(ind);

            if(ind != 0) layer.setSameInputsForEachNeuron(neuronsResult);
            neuronsResult = layer.getNeuronsResults();
        }

        return neuronsResult[0];
    }

    private double getNetworkOutputLayerError(double desiredValue, double predictedValue) {
        int outputLayerIndex = layers.size() - 1;
        Layer outputLayer = layers.get(outputLayerIndex);

        Perceptron neuron = outputLayer.getNeurons().get(0);
        double valuesDifference = desiredValue - predictedValue;

        return valuesDifference * UtilsFunctions.activationFunctionDerivative(
            neuron.getSumOfParsedInputs()
        );
    }

    private void handleAdjustNeuronsWeights(
        double networkOutputLayerError
    ) {
        int outputLayerIndex = layers.size() - 1;
        
        double layersNeuronErrors[][] = new double[layers.size()][];
        layersNeuronErrors[outputLayerIndex] = new double[]{networkOutputLayerError};
        int firstHiddenLayerIndex = outputLayerIndex-1;

        for(int ind=firstHiddenLayerIndex ; ind>=0 ; ind--) {
            Layer iterationLayer = layers.get(ind);
            Layer previousIterationLayer = layers.get(ind+1);

            double iterationLayerNeuronsErrors[] = iterationLayer.getNeuronsErrors(
                layersNeuronErrors[ind + 1],
                previousIterationLayer.getNeurons()
            );

            layersNeuronErrors[ind] = iterationLayerNeuronsErrors;
        }

        for(int ind=0 ; ind<layers.size() ; ind++) {
            layers.get(ind).handleUpdateWeightsOfNeurons(
                layersNeuronErrors[ind], LEARNING_RATE
            );
        }
    }

    private double getNetworkIterationError(
        double networkOutputLayerError
    ) {
        double parsedValuesDifference = Math.pow(networkOutputLayerError, 2);
        return parsedValuesDifference/2;
    }

    private void iterateThroughOneEpoch() {
        for(TrainingData trainingData : trainingDatas) {
            layers.get(0).setNeuronsInputs(trainingData.getInputForTraining());
            double predictedResult = executeNetworkIteration();

            double networkOutputLayerError = getNetworkOutputLayerError(
                trainingData.getExpectedResult(), predictedResult
            );
            double networkError = getNetworkIterationError(
                networkOutputLayerError
            );
            if(networkError > TOLERANCE_RATE) {
                handleAdjustNeuronsWeights(networkOutputLayerError);
            }
        }
    }

    private void initializeNewLayer(
        int neuronsQuantity, int inputsQuantityForNeurons
    ) {
        Layer createdLayer = new Layer(neuronsQuantity, inputsQuantityForNeurons);
        layers.add(createdLayer);
    }

    private void initializeNetworkLayers() {
        for(int ind=0 ; ind<hiddenLayersQuantity ; ind++) {
            initializeNewLayer(
                neuronsQuantityForEachHiddenLayer[ind],
                ind == 0 ? 4 : neuronsQuantityForEachHiddenLayer[ind - 1]
            );
        }

        //Output layer
        int inputsQuantityForOutputLayer = neuronsQuantityForEachHiddenLayer[
            neuronsQuantityForEachHiddenLayer.length - 1
        ];
        initializeNewLayer(1, inputsQuantityForOutputLayer);
    }
}