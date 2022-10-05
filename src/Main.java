import java.util.Scanner;

import networkComponents.NeuralNetwork;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(new int[]{5, 4, 3, 2, 1});
        neuralNetwork.executeNetworkLearning(10000);

        int response = 0;
        do {
            System.out.println(
                "[1] - Definir uma entrada para testar na MLP treinada;\n[0] - Parar"
                );
            response = scanner.nextInt();
            scanner.nextLine();

            if(response == 1) {
                float[][] input = inputForTestNeuralNetwork();
                neuralNetwork.showNetworkResultForInput(input);
                System.out.println();
            }
        } while (response != 0);
    }

    //IT GETS STRING INPUTED BY USER (EX: 0111 1000 1110 1001 0110) AND CONVERTS IT TO A MATRIX
    public static float[][] inputForTestNeuralNetwork(){
        System.out.println("Defina a entrada: ");
        float[][] matrizResult = new float[5][4];

        for(int i=0; i<5; i++){
            String stringForMatriz = scanner.nextLine();

            for(int j=0; j<4; j++){
                matrizResult[i][j] = Float.parseFloat("" + stringForMatriz.charAt(j));
            }
        }
        
        return matrizResult;
    }
}
