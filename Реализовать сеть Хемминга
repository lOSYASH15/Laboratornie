import java.util.Arrays;

public class HopfieldNetwork {
    private int size;
    private int[][] weights;

    public HopfieldNetwork(int size) {
        this.size = size;
        this.weights = new int[size][size];
    }

    public void train(int[] pattern) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != j) {
                    weights[i][j] += pattern[i] * pattern[j];
                }
            }
        }
    }

    public int[] recall(int[] pattern) {
        int[] output = Arrays.copyOf(pattern, size);
        boolean isStable;
        do {
            isStable = true;
            for (int i = 0; i < size; i++) {
                int activation = 0;
                for (int j = 0; j < size; j++) {
                    activation += weights[i][j] * output[j];
                }
                output[i] = activation > 0 ? 1 : -1; // Используем двоичную активацию
            }
            for (int i = 0; i < size; i++) {
                if (output[i] != pattern[i]) {
                    isStable = false;
                }
            }
        } while (!isStable);
        return output;
    }
}

СЕТЬ Хемминга
public class HammingNetwork {
    private int[][] patterns;
    private int patternCount;
    private int size;

    public HammingNetwork(int[][] patterns) {
        this.patternCount = patterns.length;
        this.size = patterns[0].length;
        this.patterns = patterns;
    }

    public int[] recognize(int[] input) {
        int bestMatchIndex = -1;
        int bestMatchDistance = Integer.MAX_VALUE;

        for (int i = 0; i < patternCount; i++) {
            int distance = hammingDistance(input, patterns[i]);
            if (distance < bestMatchDistance) {
                bestMatchDistance = distance;
                bestMatchIndex = i;
            }
        }

        return patterns[bestMatchIndex]; // Возвращаем распознанный паттерн
    }

    private int hammingDistance(int[] a, int[] b) {
        int distance = 0;
        for (int i = 0; i < size; i++) {
            if (a[i] != b[i]) {
                distance++;
            }
        }
        return distance;
    }
}

ПРИМЕР
public class Main {
    public static void main(String[] args) {
        HopfieldNetwork hopfield = new HopfieldNetwork(10); // Предположим, 10 элементов
        int[] digitPattern = {1, -1, 1, 1, -1, -1, 1, -1, 1, 1}; // Пример паттерна для цифры

        // Обучаем сеть Хопфилда
        hopfield.train(digitPattern);

        // Восстанавливаем паттерн
        int[] defectivePattern = {1, -1, 1, 1, -1, -1, 1, -1, 1, -1}; // Испорченный паттерн
        int[] restoredPattern = hopfield.recall(defectivePattern);

        // Создаем сеть Хемминга с паттернами
        int[][] patterns = {digitPattern}; // Добавляем другие паттерны по необходимости
        HammingNetwork hamming = new HammingNetwork(patterns);

        // Распознаем восстановленный паттерн
        int[] recognizedPattern = hamming.recognize(restoredPattern);
        System.out.println("Распознанный паттерн: " + Arrays.toString(recognizedPattern));
    }
}
