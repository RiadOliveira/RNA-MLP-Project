package networkComponents;
import java.util.ArrayList;
import java.util.List;

import utils.UtilsFunctions;

public class NeuralNetwork {
    private List<TrainingData> trainingDatas;

    private final float LEARNING_RATE = 0.40f;
    private final float TOLERANCE_RATE = 0.010f;

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
            boolean hasLearned = iterateThroughOneEpochAndVerifyIfHasLearned(false);

            if(hasLearned || ind == epochStopQuantity - 1) {
                System.out.println("Últimos valores obtidos pela rede:");
                System.out.println();
                iterateThroughOneEpochAndVerifyIfHasLearned(true);
                
                System.out.println();
                System.out.println(
                    "A rede " + (!hasLearned ? "não " : "") +
                    "aprendeu com " + (ind+1) + " épocas, " +
                    "utilizando a função de ativação " + UtilsFunctions.ACTIVATION_FUNCTION_SELECTED +
                    ", taxa de aprendizado de " + LEARNING_RATE + " e tolerância de " +
                    TOLERANCE_RATE + "." 
                );
                System.out.println();
                break;
            }
        }
    }

    private NeuralNetworkResult getNetworkResultBasedOnPredictedValue(
        float predictedValue
    ) {
        float differenceInRelationToFirstResult = 
            Math.abs(predictedValue - TrainingData.FIRST_EXPECTED_RESULT);
        float differenceInRelationToSecondResult = 
            Math.abs(predictedValue - TrainingData.SECOND_EXPECTED_RESULT);
        float differenceInRelationToNotRecognizedResult = 
            Math.abs(predictedValue - TrainingData.NOT_RECOGNIZED_EXPECTED_RESULT);

        float lowerDifferenceBetweenFirstAndSecondResults = 
            Math.min(differenceInRelationToFirstResult, differenceInRelationToSecondResult);

        float lowerDifferenceBetweenAllResults = Math.min(
            lowerDifferenceBetweenFirstAndSecondResults,
            differenceInRelationToNotRecognizedResult
        );

        if(lowerDifferenceBetweenAllResults == differenceInRelationToFirstResult) {
            return NeuralNetworkResult.FIRST_EXPECTED_VALUE;
        }

        if(lowerDifferenceBetweenAllResults == differenceInRelationToSecondResult) {
            return NeuralNetworkResult.SECOND_EXPECTED_VALUE;
        }

        return NeuralNetworkResult.VALUE_NOT_RECOGNIZED;
    }

    public void showNetworkResultForInput(float input[][]) {
        if(layers.size() == 0) {
            System.out.println("A Rede não foi treinada!");
            return;
        }

        layers.get(0).setNeuronsInputs(input);
        float predictedValue = getNetworkIterationPredictedValue();
        NeuralNetworkResult networkResult = getNetworkResultBasedOnPredictedValue(
            predictedValue
        );
        
        System.out.println(
            "O resultado previsto pela rede, para esta entrada foi de: " +
            predictedValue + ", o qual se encontra mais próximo do resultado: " +
            networkResult
        );
    }

    private float getNetworkIterationPredictedValue() {
        float neuronsResult[] = null;

        for(int ind=0 ; ind<layers.size() ; ind++) {
            Layer layer = layers.get(ind);

            if(ind != 0) layer.setSameInputsForEachNeuron(neuronsResult);
            neuronsResult = layer.getNeuronsResults();
        }

        return neuronsResult[0];
    }

    private float getNetworkOutputLayerError(float desiredValue, float predictedValue) {
        int outputLayerIndex = layers.size() - 1;
        Layer outputLayer = layers.get(outputLayerIndex);

        Perceptron neuron = outputLayer.getNeurons().get(0);
        float valuesDifference = desiredValue - predictedValue;

        return valuesDifference * UtilsFunctions.activationFunctionDerivative(
            neuron.getSumOfParsedInputs()
        );
    }

    private void handleAdjustNeuronsWeights(
        float networkOutputLayerError
    ) {
        int outputLayerIndex = layers.size() - 1;
        float previousLayerNeuronsErrors[] = new float[]{networkOutputLayerError};

        for(int ind=outputLayerIndex-1 ; ind>=0 ; ind--) {
            Layer iterationLayer = layers.get(ind);
            Layer previousIterationLayer = layers.get(ind+1);

            float iterationLayerNeuronsErrors[] = iterationLayer.getNeuronsErrors(
                previousLayerNeuronsErrors,
                previousIterationLayer.getNeurons()
            );
            previousIterationLayer.handleUpdateWeightsOfNeurons(
                previousLayerNeuronsErrors, LEARNING_RATE
            );

            previousLayerNeuronsErrors = iterationLayerNeuronsErrors;
        }
    }

    private float getNetworkIterationError(
        float networkOutputLayerError
    ) {
        float parsedValuesDifference = (float) Math.pow(networkOutputLayerError, 2);
        return parsedValuesDifference/2;
    }

    private boolean iterateThroughOneEpochAndVerifyIfHasLearned(boolean showEpochResults) {
        boolean hasLearned = true;

        for(TrainingData trainingData : trainingDatas) {
            layers.get(0).setNeuronsInputs(trainingData.getInputForTraining());
            float predictedResult = getNetworkIterationPredictedValue();

            float networkOutputLayerError = getNetworkOutputLayerError(
                trainingData.getExpectedResult(), predictedResult
            );
            float networkError = getNetworkIterationError(
                networkOutputLayerError
            );
            if(networkError > TOLERANCE_RATE) {
                hasLearned = false;
                handleAdjustNeuronsWeights(networkOutputLayerError);
            }

            if(showEpochResults) {
                System.out.println(
                    "Valor esperado: " + trainingData.getExpectedResult() +
                    " | Valor representado pelo esperado: " +
                    trainingData.getParsedExpectedResult() +
                    " | Valor obtido: " + predictedResult
                );
            }
        }

        return hasLearned;
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