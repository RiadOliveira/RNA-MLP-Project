import NetworkComponents.NeuralNetwork;

public class Main {
    public static void main(String args[]) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(new int[]{5, 5, 5});
        neuralNetwork.executeNetworkLearning(100000);
    }
}
