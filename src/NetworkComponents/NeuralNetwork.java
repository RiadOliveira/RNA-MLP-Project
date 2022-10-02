package NetworkComponents;
import java.util.ArrayList;
import java.util.List;

public class NeuralNetwork {
    //Davi (2+1+2+1)
    private final int FIRST_EXPECTED_RESULT = 6;

    //Oliveira (1+2+1+2+1+1+2+1) = 11/3 = 4
    private final int SECOND_EXPECTED_RESULT = 4;

    private final double LEARNING_RATE = 0.4;
    private final double TOLERANCE_RATE = 0.10;

    private short epochIndex = 0;
    private short epochStopQuantity = 0;

    private List<Layer> layers = new ArrayList<>();
    private int hiddenLayersQuantity;
    private int[] neuronsQuantityForEachHiddenLayer;

    public NeuralNetwork(int[] neuronsQuantityForEachHiddenLayer) {
        this.hiddenLayersQuantity = neuronsQuantityForEachHiddenLayer.length;
        this.neuronsQuantityForEachHiddenLayer = neuronsQuantityForEachHiddenLayer;
    }

    public void executeNetworkLearning(
        List<Double> initialInputs, short epochStopQuantity
    ) {
        this.epochStopQuantity = epochStopQuantity;
        initializeNetworkLayers(initialInputs);
    }

    private void initializeNewLayer(
        int neuronsQuantity, List<Double> layerInitialInputs
    ) {
        Layer createdLayer = new Layer(neuronsQuantity);
        if(layerInitialInputs != null) {
            createdLayer.setNeuronsInputs(layerInitialInputs);
        }

        layers.add(createdLayer);
    }

    private void initializeNetworkLayers(List<Double> initialInputs) {
        for(int ind=0 ; ind<hiddenLayersQuantity ; ind++) {
            initializeNewLayer(
                neuronsQuantityForEachHiddenLayer[ind],
                ind == 0 ? initialInputs : null
            );
        }

        //Output layer
        initializeNewLayer(1, null);
    }
}