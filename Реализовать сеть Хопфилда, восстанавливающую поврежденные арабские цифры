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
                int newValue = activation >= 0 ? 1 : -1;
                if (newValue != output[i]) {
                    output[i] = newValue;
                    isStable = false;
                }
            }
        } while (!isStable);
        return output;
    }

    public static void main(String[] args) {
        // Примеры шаблонов для цифр от 0 до 9
        int[][] digits = {
            {1, 1, 1, 1, 1, 0, 1, 1, 1, 1}, // 0
            {0, 0, 1, 0, 0, 0, 0, 0, 1, 0}, // 1
            {1, 1, 1, 0, 1, 1, 0, 1, 1, 1}, // 2
            {1, 1, 1, 0, 1, 1, 0, 1, 1, 1}, // 3
            {1, 1, 0, 1, 1, 0, 0, 0, 0, 0}, // 4
            {1, 1, 1, 1, 0, 1, 0, 1, 1, 1}, // 5
            {1, 0, 1, 1, 1, 1, 1, 1, 1, 1}, // 6
            {1, 1, 1, 0, 0, 0, 0, 0, 0, 0}, // 7
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 8
            {1, 1, 1, 1, 1, 0, 0, 1, 1, 1}  // 9
        };

        int size = 10;
        HopfieldNetwork network = new HopfieldNetwork(size);

        // Обучение сети
        for (int[] digit : digits) {
            network.train(digit);
        }

        // Пример поврежденного изображения (число 5 с небольшими искажениями)
        int[] noisyImage = {1, 1, 1, 1, 0, 0, 0, 1, 1, 1};

        // Восстановление
        int[] restoredImage = network.recall(noisyImage);

        // Вывод восстановленного изображения
        System.out.println("Restored Image:");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(restoredImage[j] == 1 ? "■" : " ");
            }
            System.out.println();
        }
    }
}
