package NetworkComponents;
import java.util.ArrayList;
import java.util.List;

import utils.UtilsFunctions;

public class NeuralNetwork {
    private TrainingData[] trainingDatas;

    private final double LEARNING_RATE = 0.40;
    private final double TOLERANCE_RATE = 0.10;

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
    
    private double getErrorDerivativeRelatedToPredicted(
        double desiredValue, double predictedValue
    ) {
        return predictedValue - desiredValue;
    }

    private double getPredictedDerivativeRelatedToOutputSum(
        double outputSum
    ) {
        double sigmoidResult = UtilsFunctions.sigmoidFunction(outputSum);
        return sigmoidResult*(1 - sigmoidResult);
    }

    private void handleAdjustNeuronsWeights(double desiredValue, double predictedValue) {
        double errorDerivativeRelatedToPredicted = getErrorDerivativeRelatedToPredicted(
            desiredValue, predictedValue
        );
        double predictedDerivativeRelatedToOutputSum = getPredictedDerivativeRelatedToOutputSum(
            layers.get(hiddenLayersQuantity).getNeuronsResultsWithoutActivation()[0]
        );

        double multiplicationFactor =
            errorDerivativeRelatedToPredicted * predictedDerivativeRelatedToOutputSum;

        layers.forEach(
            layer -> layer.handleUpdateNeuronsWeights(multiplicationFactor, LEARNING_RATE)
        );
    }

    private double getNetworkIterationError(
        double desiredValue, double predictedValue
    ) {
        double parsedValuesDifference = Math.pow(desiredValue - predictedValue, 2);
        return parsedValuesDifference/2;
    }

    private boolean networkIterationResultIsValidBasedOnToleranceRate(
        double desiredValue, double predictedValue
    ) {
        double iterationError = getNetworkIterationError(desiredValue, predictedValue);
        return iterationError <= TOLERANCE_RATE;
    }

    private void iterateThroughOneEpoch() {
        for(TrainingData trainingData : trainingDatas) {
            layers.get(0).setNeuronsInputs(trainingData.getInputForTraining());
            double predictedResult = executeNetworkIteration();

            boolean iterationResultIsValid = networkIterationResultIsValidBasedOnToleranceRate(
                trainingData.getExpectedResult(), predictedResult
            );
            if(!iterationResultIsValid) {
                handleAdjustNeuronsWeights(
                    trainingData.getExpectedResult(), predictedResult
                );
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