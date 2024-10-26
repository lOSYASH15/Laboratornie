  import java.util.Arrays;

public class Perceptron {
    private double[] weights; // Массив весов для входов и bias
    private double learningRate; // Скорость обучения

    // Конструктор класса Perceptron
    public Perceptron(int inputSize, double learningRate) {
        // Инициализация весов, +1 для bias
        weights = new double[inputSize + 1]; 
        this.learningRate = learningRate; // Установка скорости обучения
    }

    // Активирующая функция (пороговая функция)
    // Возвращает 1, если x >= 0, иначе 0
    private int activationFunction(double x) {
        return x >= 0 ? 1 : 0;
    }

    // Метод предсказания
    // Принимает массив входных данных и возвращает предсказанный результат
    public int predict(double[] inputs) {
        double summedInput = weights[0]; // Начинаем со смещения (bias)
        
        // Вычисление взвешенной суммы входных данных
        for (int i = 0; i < inputs.length; i++) {
            summedInput += weights[i + 1] * inputs[i]; // Учитываем вес для каждого входа
        }
        
        // Применяем активирующую функцию к взвешенной сумме
        return activationFunction(summedInput);
    }

    // Метод обучения
    // Принимает массив входных данных, метки и количество эпох
    public void train(double[][] trainingInputs, int[] labels, int epochs) {
        // Итерация по количеству эпох
        for (int n = 0; n < epochs; n++) {
            // Итерация по всем обучающим примерам
            for (int i = 0; i < trainingInputs.length; i++) {
                // Получаем предсказание текущего примера
                int prediction = predict(trainingInputs[i]);
                
                // Обновление весов на основе ошибки (разности между меткой и предсказанием)
                weights[0] += learningRate * (labels[i] - prediction); // Обновление bias
                
                // Обновление весов для каждого входа
                for (int j = 0; j < trainingInputs[i].length; j++) {
                    weights[j + 1] += learningRate * (labels[i] - prediction) * trainingInputs[i][j];
                }
            }
        }
    }

    // Основной метод для тестирования перцептрона
    public static void main(String[] args) {
        // Пример использования: обучение перцептрона на логической функции "И"
        
        // Входные данные для логической функции И
        double[][] andInputs = {
            {0, 0},
            {0, 1},
            {1, 0},
            {1, 1}
        };
        
        // Соответствующие выходные метки
        int[] andLabels = {0, 0, 0, 1}; // Результаты логической функции "И"

        // Создаем перцептрон с 2 входами и заданной скоростью обучения
        Perceptron andPerceptron = new Perceptron(2, 0.1);

        // Обучаем перцептрон
        andPerceptron.train(andInputs, andLabels, 10);

        // Проверяем обучение на новых данных
        System.out.println("Тестирование функции И:");
        for (double[] input : andInputs) {
            System.out.println(Arrays.toString(input) + " -> " + andPerceptron.predict(input));
        }
    }
// Обучение логической функции "НЕ"
        double[][] notInputs = {{0}, {1}};
        int[] notLabels = {1, 0};

        Perceptron notPerceptron = new Perceptron(1, 0.1);
        notPerceptron.train(notInputs, notLabels, 10);

        System.out.println("\nЛогическая функция NOT:");
        for (double[] input : notInputs) {
            System.out.println(Arrays.toString(input) + " => " + notPerceptron.predict(input));
        }
}
// Обучение логической функции "ИЛИ"
        double[][] orInputs = {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[] orLabels = {0, 1, 1, 1};

        Perceptron orPerceptron = new Perceptron(2, 0.1);
        orPerceptron.train(orInputs, orLabels, 10);

        System.out.println("\nЛогическая функция OR:");
        for (double[] input : orInputs) {
            System.out.println(Arrays.toString(input) + " => " + orPerceptron.predict(input));
        }
}
