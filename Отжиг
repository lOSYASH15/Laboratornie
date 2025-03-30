import java.util.Random;

public class SimulatedAnnealing {
    private final Random random = new Random();
    private double temp;
    private final double coolingRate;
    private final int iterations;

    public SimulatedAnnealing(double startTemp, double coolingRate, int iterations) {
        this.temp = startTemp;
        this.coolingRate = coolingRate;
        this.iterations = iterations;
    }

    public double[] solve() {
        // Начальное решение
        double[] current = {random.nextDouble()*10 - 5, random.nextDouble()*10 - 5};
        double[] best = current.clone();
        
        for (int i = 0; i < iterations; i++) {
            double[] next = getNeighbor(current);
            
            double currentEnergy = evaluate(current);
            double neighborEnergy = evaluate(next);
            
            if (acceptanceProbability(currentEnergy, neighborEnergy, temp) > random.nextDouble()) {
                current = next.clone();
            }
            
            if (evaluate(current) > evaluate(best)) {
                best = current.clone();
            }
            
            temp *= 1 - coolingRate;
        }
        return best;
    }

    private double[] getNeighbor(double[] point) {
        return new double[]{
            point[0] + random.nextGaussian() * 0.5,
            point[1] + random.nextGaussian() * 0.5
        };
    }

    private double evaluate(double[] point) {
        double x = point[0];
        double y = point[1];
        return 1.0 / (1 + x*x + y*y);
    }

    private double acceptanceProbability(double current, double neighbor, double temp) {
        return neighbor > current ? 1.0 : Math.exp((neighbor - current)/temp);
    }

    public static void main(String[] args) {
        SimulatedAnnealing sa = new SimulatedAnnealing(
            10000,  // Начальная температура
            0.003,  // Скорость охлаждения
            10000   // Число итераций
        );
        
        double[] solution = sa.solve();
        double value = sa.evaluate(solution);
        
        System.out.printf("Максимум найден в точке (%.4f, %.4f)%n", solution[0], solution[1]);
        System.out.printf("Значение функции: %.6f%n", value);
    }
}
