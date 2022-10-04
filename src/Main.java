import networkComponents.NeuralNetwork;

public class Main {
    public static void main(String args[]) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(new int[]{5, 4, 3, 2, 1});
        neuralNetwork.executeNetworkLearning(10000);

        neuralNetwork.showNetworkResultForInput(new float[][]{
            {1, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        });
    }
}
