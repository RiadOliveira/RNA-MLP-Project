package NetworkComponents;

public class TrainingData {
    //Davi (2+1+2+1)
    private static final double FIRST_EXPECTED_RESULT = 6;
    private static final double[][] FIRST_INPUT = new double[][]{
        {0, 1, 1, 1},
        {1, 0, 0, 0},
        {1, 1, 1, 0},
        {1, 0, 0, 1},
        {0, 1, 1, 0}
    };

    //Oliveira (1+2+1+2+1+1+2+1) = 11/3 = 4
    private static final double SECOND_EXPECTED_RESULT = 4;
    private static final double[][] SECOND_INPUT = new double[][]{
        {1, 0, 1, 0},
        {1, 0, 1, 0},
        {1, 1, 1, 1},
        {0, 0, 1, 0},
        {0, 0, 1, 0}
    };

    private double[][] inputForTraining;
    private double expectedResult;
    
    public TrainingData(double[][] inputForTraining, double expectedResult) {
        this.inputForTraining = inputForTraining;
        this.expectedResult = expectedResult;
    }

    public static TrainingData[] getDefaultTrainingData() {
        TrainingData trainingDatas[] = new TrainingData[2];

        TrainingData firstData = new TrainingData(
            FIRST_INPUT, FIRST_EXPECTED_RESULT
        );
        TrainingData secondData = new TrainingData(
            SECOND_INPUT, SECOND_EXPECTED_RESULT
        );

        trainingDatas[0] = firstData;
        trainingDatas[1] = secondData;

        return trainingDatas;
    }

    public double[][] getInputForTraining() {
        return inputForTraining;
    }

    public double getExpectedResult() {
        return expectedResult;
    }
}
