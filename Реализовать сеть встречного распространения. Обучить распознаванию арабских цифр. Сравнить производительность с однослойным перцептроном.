import java.util.Arrays;

public class MultiLayerPerceptron {
    private int inputSize;
    private int hiddenSize;
    private int outputSize;
    private double[][] inputHiddenWeights;
    private double[][] hiddenOutputWeights;
    private double learningRate;

    public MultiLayerPerceptron(int inputSize, int hiddenSize, int outputSize, double learningRate) {
        this.inputSize = inputSize;
        this.hiddenSize = hiddenSize;
        this.outputSize = outputSize;
        this.learningRate = learningRate;
        this.inputHiddenWeights = new double[inputSize][hiddenSize];
        this.hiddenOutputWeights = new double[hiddenSize][outputSize];

        // Инициализация весов случайными значениями
        for (int i = 0; i < inputSize; i++) {
            for (int j = 0; j < hiddenSize; j++) {
                this.inputHiddenWeights[i][j] = Math.random();
            }
        }
        for (int i = 0; i < hiddenSize; i++) {
            for (int j = 0; j < outputSize; j++) {
                this.hiddenOutputWeights[i][j] = Math.random();
            }
        }
    }

    public void train(double[][] inputs, int[] labels) {
        for (int epoch = 0; epoch < 1000; epoch++) {
            for (int i = 0; i < inputs.length; i++) {
                // Прямое распространение
                double[] hiddenLayerOutput = new double[hiddenSize];
                for (int j = 0; j < hiddenSize; j++) {
                    hiddenLayerOutput[j] = 0;
                    for (int k = 0; k < inputSize; k++) {
                        hiddenLayerOutput[j] += inputs[i][k] * inputHiddenWeights[k][j];
                    }
                    hiddenLayerOutput[j] = sigmoid(hiddenLayerOutput[j]);
                }

                double[] outputLayerOutput = new double[outputSize];
                for (int j = 0; j < outputSize; j++) {
                    outputLayerOutput[j] = 0;
                    for (int k = 0; k < hiddenSize; k++) {
                        outputLayerOutput[j] += hiddenLayerOutput[k] * hiddenOutputWeights[k][j];
                    }
                    outputLayerOutput[j] = sigmoid(outputLayerOutput[j]);
                }

                // Обратное распространение
                double[] outputErrors = new double[outputSize];
                for (int j = 0; j < outputSize; j++) {
                    double target = (j == labels[i]) ? 1.0 : 0.0;
                    outputErrors[j] = target - outputLayerOutput[j];
                }

                double[] hiddenErrors = new double[hiddenSize];
                for (int j = 0; j < hiddenSize; j++) {
                    hiddenErrors[j] = 0;
                    for (int k = 0; k < outputSize; k++) {
                        hiddenErrors[j] += outputErrors[k] * hiddenOutputWeights[j][k];
                    }
                }

                // Обновление весов
                for (int j = 0; j < hiddenSize; j++) {
                    for (int k = 0; k < outputSize; k++) {
                        hiddenOutputWeights[j][k] += learningRate * outputErrors[k] * hiddenLayerOutput[j];
                    }
                }
                for (int j = 0; j < inputSize; j++) {
                    for (int k = 0; k < hiddenSize; k++) {
                        inputHiddenWeights[j][k] += learningRate * hiddenErrors[k] * inputs[i][j];
                    }
                }
            }
        }
    }

    public double[] predict(double[] input) {
        double[] hiddenLayerOutput = new double[hiddenSize];
        for (int j = 0; j < hiddenSize; j++) {
            hiddenLayerOutput[j] = 0;
            for (int k = 0; k < inputSize; k++) {
                hiddenLayerOutput[j] += input[k] * inputHiddenWeights[k][j];
            }
            hiddenLayerOutput[j] = sigmoid(hiddenLayerOutput[j]);
        }

        double[] outputLayerOutput = new double[outputSize];
        for (int j = 0; j < outputSize; j++) {
            outputLayerOutput[j] = 0;
            for (int k = 0; k < hiddenSize; k++) {
                outputLayerOutput[j] += hiddenLayerOutput[k] * hiddenOutputWeights[k][j];
            }
            outputLayerOutput[j] = sigmoid(outputLayerOutput[j]);
        }
        return outputLayerOutput;
    }

    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }
}
