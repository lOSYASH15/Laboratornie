import java.util.Arrays; // Импортируем массивы для использования в классе

public class SingleLayerPerceptron {
    private double[][] weights; // Массив весов для входов
    private double learningRate; // Скорость обучения

    // Конструктор класса
    public SingleLayerPerceptron(int inputSize, int outputSize, double learningRate) {
        // Инициализация весов случайными значениями
        weights = new double[outputSize][inputSize + 1]; // +1 для bias
        this.learningRate = learningRate; // Установка скорости обучения
        initializeWeights(); // Инициализация весов
    }

    // Метод для инициализации весов случайными значениями
    private void initializeWeights() {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                // Извлекаем случайные значения для весов
                weights[i][j] = Math.random() * 0.01; // Маленькие случайные значения
            }
        }
    }

    // Активирующая функция (пороговая функция)
    private int activationFunction(double x) {
        return x >= 0 ? 1 : 0; // Возвращаем 1, если x >= 0, иначе 0
    }

    // Метод предсказания
    public int predict(double[] inputs) {
        double[] outputs = new double[weights.length]; // Массив для хранения выходов

        // Вычисляем взвешенные суммы для каждого выхода
        for (int i = 0; i < weights.length; i++) {
            double summedInput = weights[i][0]; // Начинаем с bias
            for (int j = 0; j < inputs.length; j++) {
                summedInput += weights[i][j + 1] * inputs[j]; // Суммируем взвешенные входы
            }
            outputs[i] = activationFunction(summedInput); // Применяем активационную функцию
        }

        // Возвращаем индекс выхода с максимальным значением
        return getMaxIndex(outputs);
    }

    // Метод для получения индекса максимального значения
    private int getMaxIndex(double[] outputs) {
        int maxIndex = 0;
        for (int i = 1; i < outputs.length; i++) {
            // Находим индекс, соответствующий максимальному значению
            if (outputs[i] > outputs[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex; // Возвращаем индекс максимального значения
    }

    // Метод обучения
    // Принимает массив входных данных и соответствующие метки
    public void train(double[][] trainingInputs, int[] labels, int epochs) {
        for (int n = 0; n < epochs; n++) {
            // Итерация по всем обучающим примерам
            for (int i = 0; i < trainingInputs.length; i++) {
                double[] inputs = trainingInputs[i]; // Получаем текущие входные данные
                int expectedOutput = labels[i]; // Получаем ожидаемый выход

                // Получаем предсказанный выход
                int predictedOutput = predict(inputs);

                // Обновляем веса для каждого выхода
                for (int j = 0; j < weights.length; j++) {
                    // Если предсказанный выход не соответствует ожидаемому
                    if (j == expectedOutput) {
                        // Увеличиваем вес
                        weights[j][0] += learningRate; // Обновление bias
                        for (int k = 0; k < inputs.length; k++) {
                            weights[j][k + 1] += learningRate * inputs[k]; // Обновление весов
                        }
                    } else {
                        // Уменьшаем вес для неактивных классов
                        weights[j][0] -= learningRate; // Обновление bias
                        for (int k = 0; k < inputs.length; k++) {
                            weights[j][k + 1] -= learningRate * inputs[k]; // Уменьшаем веса
                        }
                    }
                }
            }
        }
    }

    // Пример использования перцептрона
    public static void main(String[] args) {
        int inputSize = 100; // Размер входа (10x10)
        int outputSize = 10; // Количество классов (цифры 0-9)
        double learningRate = 0.1; // Скорость обучения
        SingleLayerPerceptron perceptron = new SingleLayerPerceptron(inputSize, outputSize, learningRate); // Создаем объект перцептрона

        double[][] trainingData = new double[/* количество примеров */][inputSize]; // Обучающие данные
        int[] labels = new int[/* количество примеров */]; // Метки для обучающих данных

        // Заполнение trainingData и labels данными для тренировки...

        int epochs = 1000; // Количество эпох для обучения
        perceptron.train(trainingData, labels, epochs); // Обучаем перцептрон

        // Пример тестирования перцептрона
        double[] testInput = new double[inputSize]; // Тестовые данные
        // Заполнение testInput данными для теста...

        int predictedLabel = perceptron.predict(testInput); // Получаем предсказание
        System.out.println("Предсказанная метка: " + predictedLabel); // Выводим предсказанную метку
    }
}
