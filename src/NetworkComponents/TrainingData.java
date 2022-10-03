package NetworkComponents;

public class TrainingData {
    //Davi (2+1+2+1) = 6
    private static final double FIRST_EXPECTED_RESULT = 0;
    private static final double[][] FIRST_RECOGNIZED_INPUT = new double[][]{
        {0, 1, 1, 1},
        {1, 0, 0, 0},
        {1, 1, 1, 0},
        {1, 0, 0, 1},
        {0, 1, 1, 0}
    };

    //Oliveira (1+2+1+2+1+1+2+1) = 11/3 = 4
    private static final double SECOND_EXPECTED_RESULT = 1;
    private static final double[][] SECOND_RECOGNIZED_INPUT = new double[][]{
        {1, 0, 1, 0},
        {1, 0, 1, 0},
        {1, 1, 1, 1},
        {0, 0, 1, 0},
        {0, 0, 1, 0}
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

    public static TrainingData[] getDefaultTrainingData() {
        TrainingData trainingDatas[] = new TrainingData[2 + NOT_RECOGNIZED_INPUTS.length];

        TrainingData firstData = new TrainingData(
            FIRST_RECOGNIZED_INPUT, FIRST_EXPECTED_RESULT
        );
        trainingDatas[0] = firstData;

        TrainingData secondData = new TrainingData(
            SECOND_RECOGNIZED_INPUT, SECOND_EXPECTED_RESULT
        );
        trainingDatas[1] = secondData;

        for(int ind=0 ; ind<NOT_RECOGNIZED_INPUTS.length ; ind++) {
            TrainingData notRecognizedData = new TrainingData(
                NOT_RECOGNIZED_INPUTS[ind], NOT_RECOGNIZED_EXPECTED_RESULT
            );

            trainingDatas[ind + 2] = notRecognizedData;
        }

        return trainingDatas;
    }

    public double[][] getInputForTraining() {
        return inputForTraining;
    }

    public double getExpectedResult() {
        return expectedResult;
    }
}
