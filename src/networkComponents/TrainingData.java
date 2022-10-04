package networkComponents;

import java.util.ArrayList;
import java.util.List;

public class TrainingData {
    //Davi (2+1+2+1) = 6
    public static final float FIRST_EXPECTED_RESULT = 0;
    private static final float[][][] FIRST_RECOGNIZED_INPUTS = new float[][][]{
        {
            {0, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 0, 0, 1},
            {0, 1, 1, 0}
        },
        {
            {1, 1, 1, 1},
            {1, 0, 0, 0},
            {1, 1, 1, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1}
        },
        {
            {1, 1, 1, 0},
            {1, 0, 0, 0},
            {1, 1, 1, 0},
            {1, 0, 1, 0},
            {1, 1, 1, 0}
        }, 
        {
            {0, 1, 1, 1}, 
            {0, 1, 0, 0}, 
            {0, 1, 1, 1}, 
            {0, 1, 0, 1}, 
            {0, 1, 1, 1}
        },
        {
            {0, 0, 1, 1},
            {0, 1, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 0, 1},
            {0, 0, 1, 0}
        },
        {
            {0, 1, 1, 0},
            {1, 0, 0, 0},
            {1, 1, 0, 0},
            {1, 0, 1, 0},
            {0, 1, 0, 0}
        }
    };

    //Oliveira (1+2+1+2+1+1+2+1) = 11/3 = 4
    public static final float SECOND_EXPECTED_RESULT = 1;
    private static final float[][][] SECOND_RECOGNIZED_INPUTS = new float[][][]{
        {
            {1, 0, 1, 0},
            {1, 0, 1, 0},
            {1, 1, 1, 0},
            {0, 0, 1, 0},
            {0, 0, 1, 0}
        },
        {
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1}
        },
        {
            {0, 1, 0, 1},
            {0, 1, 0, 1},
            {0, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1}
        }, 
        {
            {0, 0, 1, 1}, 
            {0, 1, 0, 1}, 
            {1, 0, 0, 1}, 
            {1, 1, 1, 1}, 
            {0, 0, 0, 1}
        },
        {
            {0, 0, 1, 1},
            {0, 1, 0, 1},
            {1, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1}
        }, 
        {
            {0, 1, 0, 1}, 
            {0, 1, 0, 1}, 
            {0, 0, 1, 1}, 
            {0, 0, 0, 1}, 
            {0, 0, 0, 1}
        },
        {
            {1, 0, 0, 1},
            {1, 0, 0, 1},
            {0, 1, 1, 1},
            {0, 0, 0, 1},
            {0, 0, 0, 1}
        }
    };

    public static final float NOT_RECOGNIZED_EXPECTED_RESULT = 0.5f;
    private static final float[][][] NOT_RECOGNIZED_INPUTS = new float[][][]{
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

    private float[][] inputForTraining;
    private float expectedResult;
    
    public TrainingData(float[][] inputForTraining, float expectedResult) {
        this.inputForTraining = inputForTraining;
        this.expectedResult = expectedResult;
    }

    public static List<TrainingData> getDefaultTrainingData() {
        List<TrainingData> trainingDatas = new ArrayList<>(
            FIRST_RECOGNIZED_INPUTS.length +
            SECOND_RECOGNIZED_INPUTS.length +
            NOT_RECOGNIZED_INPUTS.length
        );

        for(float[][] input : FIRST_RECOGNIZED_INPUTS) {
            TrainingData trainingData = new TrainingData(input, FIRST_EXPECTED_RESULT);
            trainingDatas.add(trainingData);
        }

        for(float[][] input : SECOND_RECOGNIZED_INPUTS) {
            TrainingData trainingData = new TrainingData(input, SECOND_EXPECTED_RESULT);
            trainingDatas.add(trainingData);
        }

        for(float[][] input : NOT_RECOGNIZED_INPUTS) {
            TrainingData trainingData = new TrainingData(input, NOT_RECOGNIZED_EXPECTED_RESULT);
            trainingDatas.add(trainingData);
        }

        return trainingDatas;
    }

    public float[][] getInputForTraining() {
        return inputForTraining;
    }

    public float getExpectedResult() {
        return expectedResult;
    }

    public NeuralNetworkResult getParsedExpectedResult() {
        if(expectedResult == 0) return NeuralNetworkResult.FIRST_EXPECTED_VALUE;
        if(expectedResult == 1) return NeuralNetworkResult.SECOND_EXPECTED_VALUE;
        return NeuralNetworkResult.VALUE_NOT_RECOGNIZED;
    }
}