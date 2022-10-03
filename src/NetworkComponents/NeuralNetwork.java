package NetworkComponents;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    private TrainingData[] trainingDatas;

    private final double LEARNING_RATE = 0.4;
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
            System.out.println(getEpochResult());
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

    private double getEpochResult() {
        double iterationResult = 0d;

        for(TrainingData trainingData : trainingDatas) {
            layers.get(0).setNeuronsInputs(trainingData.getInputForTraining());
            iterationResult = executeNetworkIteration();
        }

        return iterationResult;
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