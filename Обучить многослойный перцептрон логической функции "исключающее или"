import java.util.Random; // Импортируем класс Random для генерации случайных чисел

public class MultiLayerPerceptron {
    private double[][] weightsInputHidden; // Веса между входным и скрытым слоем
    private double[][] weightsHiddenOutput; // Веса между скрытым и выходным слоем
    private double learningRate; // Скорость обучения

    // Конструктор для инициализации весов
    public MultiLayerPerceptron(double learningRate) {
        this.learningRate = learningRate; // Установка скорости обучения
        weightsInputHidden = new double[2][2]; // Входной слой (2 нейрона) к скрытому слою (2 нейрона)
        weightsHiddenOutput = new double[2][1]; // Скрытый слой (2 нейрона) к выходному слою (1 нейрон)
        initializeWeights(); // Инициализация весов
    }

    // Метод для инициализации весов случайными значениями
    private void initializeWeights() {
        Random rand = new Random(); // Создаем объект Random
        // Инициализация весов между входным и скрытым слоями
        for (int i = 0; i < weightsInputHidden.length; i++) {
            for (int j = 0; j < weightsInputHidden[i].length; j++) {
                weightsInputHidden[i][j] = rand.nextDouble(); // Случайные значения
            }
        }
        // Инициализация весов между скрытым и выходным слоями
        for (int i = 0; i < weightsHiddenOutput.length; i++) {
            for (int j = 0; j < weightsHiddenOutput[i].length; j++) {
                weightsHiddenOutput[i][j] = rand.nextDouble(); // Случайные значения
            }
        }
    }

    // Метод активации (сигмоидная функция)
    private double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x)); // Вычисляем сигмоид
    }

    // Метод производной сигмоидной функции
    private double sigmoidDerivative(double x) {
        return x * (1 - x); // Вычисляем производную сигмоиды
    }

    // Метод для обучения сети
    public void train(double[][] trainingInputs, double[] trainingOutputs) {
        for (int epoch = 0; epoch < 10000; epoch++) { // Обучаем сеть 10,000 итераций
            for (int i = 0; i < trainingInputs.length; i++) { // Проход по каждому примеру
                // Прямое распространение (forward propagation)
                double[] hiddenLayerOutputs = new double[2]; // Массив для скрытого слоя
                
                // Вычисление выходов скрытого слоя
                for (int j = 0; j < hiddenLayerOutputs.length; j++) {
                    // Суммируем входы, умноженные на веса
                    hiddenLayerOutputs[j] = sigmoid(
                        weightsInputHidden[j][0] * trainingInputs[i][0] +
                        weightsInputHidden[j][1] * trainingInputs[i][1]
                    );
                }
                
                // Вычисление выходного значения
                double output = sigmoid(
                    weightsHiddenOutput[0][0] * hiddenLayerOutputs[0] +
                    weightsHiddenOutput[1][0] * hiddenLayerOutputs[1]
                );

                // Вычисление ошибки
                double outputError = trainingOutputs[i] - output; // Ошибка на выходе
                double[] hiddenLayerErrors = new double[2]; // Ошибка на скрытом слое

                // Обратное распространение (back propagation)
                for (int j = 0; j < hiddenLayerOutputs.length; j++) {
                    // Сохраняем ошибку для скрытого слоя
                    hiddenLayerErrors[j] = outputError * weightsHiddenOutput[j][0] * sigmoidDerivative(output);
                }
                
                // Обновление весов между скрытым и выходным слоями
                for (int j = 0; j < weightsHiddenOutput.length; j++) {
                    weightsHiddenOutput[j][0] += learningRate * outputError * sigmoidDerivative(output) * hiddenLayerOutputs[j]; // Обновляем веса
                }
                
                // Обновление весов между входным и скрытым слоями
                for (int j = 0; j < weightsInputHidden.length; j++) {
                    for (int k = 0; k < weightsInputHidden[j].length; k++) {
                        weightsInputHidden[j][k] += learningRate * hiddenLayerErrors[j] * sigmoidDerivative(hiddenLayerOutputs[j]) * trainingInputs[i][k]; // Обновляем веса
                    }
                }
            }
        }
    }

    // Метод прогнозирования выходных значений
    public double predict(double[] inputs) {
        double[] hiddenLayerOutputs = new double[2]; // Массив для скрытого слоя
        
        // Вычисление выходов скрытого слоя
        for (int j = 0; j < hiddenLayerOutputs.length; j++) {
            hiddenLayerOutputs[j] = sigmoid(
                weightsInputHidden[j][0] * inputs[0] +
                weightsInputHidden[j][1] * inputs[1]
            );
        }
        
        // Вычисление выходного значения
        return sigmoid(
            weightsHiddenOutput[0][0] * hiddenLayerOutputs[0] +
            weightsHiddenOutput[1][0] * hiddenLayerOutputs[1]
        ); // Возвращаем выходное значение
    }

    // Главный метод для тестирования
    public static void main(String[] args) {
        MultiLayerPerceptron mlp = new MultiLayerPerceptron(0.1); // Создаем MLP с заданной скоростью обучения
        double[][] trainingInputs = { {0, 0}, {0, 1}, {1, 0}, {1, 1} }; // Входные данные для XOR
        double[] trainingOutputs = { 0, 1, 1, 0 }; // Ожидаемые выходные данные для XOR

        mlp.train(trainingInputs, trainingOutputs); // Обучаем сеть

        // Тестирование сети
        for (double[] input : trainingInputs) {
            System.out.println("Input: " + Arrays.toString(input) + " => Output: " + mlp.predict(input)); // Выводим результаты
        }
    }
}
