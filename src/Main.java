import NetworkComponents.NeuralNetwork;

public class Main {
    public static void main(String args[]) {
        double initialInputs[][] = new double[][]{
            {0,1,0,0},
            {1,1,0,0},
            {0,1,0,0},
            {0,1,0,0},
            {1,1,1,0},
        };

        NeuralNetwork neuralNetwork = new NeuralNetwork(new int[]{5,5,5});
        neuralNetwork.executeNetworkLearning(5);
    }
}
