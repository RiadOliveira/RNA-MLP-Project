import networkComponents.NeuralNetwork;

public class Main {
    public static void main(String args[]) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(new int[]{5, 6, 3, 5, 3, 1});
        neuralNetwork.executeNetworkLearning(100);
    }
}
