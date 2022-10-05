import java.util.Scanner;

import javax.swing.SwingConstants;

import networkComponents.NeuralNetwork;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        NeuralNetwork neuralNetwork = new NeuralNetwork(new int[]{5, 4, 3, 2, 1});
        neuralNetwork.executeNetworkLearning(10000);

        // int test = 0;
        // do {
        //     System.out.println(
        //         "[1] - Definir uma entrada para testar na MLP treinada;\n[0] - Parar"
        //         );
        //     test = scanner.nextInt();
        //     if(test==1){
        //         neuralNetwork.showNetworkResultForInput(inputForTestNeuralNetwork());
        //     }
        // } while (test != 0);

        neuralNetwork.showNetworkResultForInput(inputForTestNeuralNetwork());
    }

    //PEGA A STRING DIGITADA PELO USUARIO (EX: 01111000111010010110) E TRANSFORMA EM UMA MATRIZ
    public static float[][] inputForTestNeuralNetwork(){
        System.out.println("Defina a entrada: ");
        String stringForMatriz = scanner.nextLine();

        float[][] matrizResult = new float[5][4];

        int cont = 0;
        for(int i=0; i<5; i++){
            for(int j=0; j<4; j++){
                matrizResult[i][j] = stringForMatriz.charAt(cont);
                cont++;
            }
        }
        
        return matrizResult;

    }
}
