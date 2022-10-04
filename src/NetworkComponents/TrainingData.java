package NetworkComponents;

import java.util.ArrayList;
import java.util.List;

public class TrainingData {
    //Davi (2+1+2+1) = 6
    private static final double FIRST_EXPECTED_RESULT = 0;
    private static final double[][][] FIRST_RECOGNIZED_INPUTS = new double[][][]{
        {
            {1,0,1,0},
            {1,0,1,0},
            {1,1,1,0},
            {0,0,1,0},
            {0,0,1,0}
        },
        {
            {1,0,0,1},
            {1,0,0,1},
            {1,1,1,1},
            {0,0,0,1},
            {0,0,0,1}
        },
        {
            {0,1,0,1},
            {0,1,0,1},
            {0,1,1,1},
            {0,0,0,1},
            {0,0,0,1}
        },
        {
            {0,0,1,1},
            {0,1,0,1},
            {1,0,0,1},
            {1,1,1,1},
            {0,0,0,1}
        },
        {
            {0,0,1,1},
            {0,1,0,1},
            {1,1,1,1},
            {0,0,0,1},
            {0,0,0,1}
        },
        {
            {0,1,0,1},
            {0,1,0,1},
            {0,0,1,1},
            {0,0,0,1},
            {0,0,0,1}
        },
        {
            {1,0,0,1},
            {1,0,0,1},
            {0,1,1,1},
            {0,0,0,1},
            {0,0,0,1}
        }
    };

    //Oliveira (1+2+1+2+1+1+2+1) = 11/3 = 4
    private static final double SECOND_EXPECTED_RESULT = 1;
    private static final double[][][] SECOND_RECOGNIZED_INPUTS = new double[][][]{
        {
            {0,1,1,1},
            {1,0,0,0},
            {1,1,1,0},
            {1,0,0,1},
            {0,1,1,0}
        },
        {
            {1,1,1,1},
            {1,0,0,0},
            {1,1,1,1},
            {1,0,0,1},
            {1,1,1,1}
        },
        {
            {1,1,1,0},
            {1,0,0,0},
            {1,1,1,0},
            {1,0,1,0},
            {1,1,1,0}
        },
        {
            {0,1,1,1},
            {0,1,0,0},
            {0,1,1,1},
            {0,1,0,1},
            {0,1,1,1}
        },
        {
            {0,0,1,1},
            {0,1,0,0},
            {0,1,1,0},
            {0,1,0,1},
            {0,0,1,0}
        },
        {
            {0,1,1,0},
            {1,0,0,0},
            {1,1,0,0},
            {1,0,1,0},
            {0,1,0,0}
        }
    };

    private static final double NOT_RECOGNIZED_EXPECTED_RESULT = 0.5;
    private static final double[][][] NOT_RECOGNIZED_INPUTS = new double[][][]{
        {
            {0, 1, 0, 0},
            {1, 1, 0, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 0}
        },
        {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {1, 1, 1, 1}
        },
        {
            {1, 1, 1, 0},
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 0, 0, 1},
            {1, 1, 1, 0}
        },
        {
            {1, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {0, 0, 0, 1},
            {1, 1, 1, 0}
        },
        {
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 1, 0},
            {0, 1, 0, 0},
            {0, 1, 0, 0}
        },
        {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 1, 1, 0}
        },
        {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 1, 1, 1},
            {0, 0, 0, 1},
            {1, 1, 1, 1}
        },
        {
            {0, 1, 1, 0},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {0, 1, 1, 0}
        }
    };

    private double[][] inputForTraining;
    private double expectedResult;
    
    public TrainingData(double[][] inputForTraining, double expectedResult) {
        this.inputForTraining = inputForTraining;
        this.expectedResult = expectedResult;
    }

    public static List<TrainingData> getDefaultTrainingData() {
        List<TrainingData> trainingDatas = new ArrayList<>(
            FIRST_RECOGNIZED_INPUTS.length +
            SECOND_RECOGNIZED_INPUTS.length +
            NOT_RECOGNIZED_INPUTS.length
        );

        for(double[][] input : FIRST_RECOGNIZED_INPUTS) {
            TrainingData trainingData = new TrainingData(input, FIRST_EXPECTED_RESULT);
            trainingDatas.add(trainingData);
        }

        for(double[][] input : SECOND_RECOGNIZED_INPUTS) {
            TrainingData trainingData = new TrainingData(input, SECOND_EXPECTED_RESULT);
            trainingDatas.add(trainingData);
        }

        for(double[][] input : NOT_RECOGNIZED_INPUTS) {
            TrainingData trainingData = new TrainingData(input, NOT_RECOGNIZED_EXPECTED_RESULT);
            trainingDatas.add(trainingData);
        }

        return trainingDatas;
    }

    public double[][] getInputForTraining() {
        return inputForTraining;
    }

    public double getExpectedResult() {
        return expectedResult;
    }

    public NeuralNetworkResult getParsedExpectedResult() {
        if(expectedResult == 0) return NeuralNetworkResult.FIRST_EXPECTED_VALUE;
        if(expectedResult == 1) return NeuralNetworkResult.SECOND_EXPECTED_VALUE;
        return NeuralNetworkResult.VALUE_NOT_RECOGNIZED;
    }
}
