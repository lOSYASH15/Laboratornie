import java.util.*;
import java.util.stream.IntStream;

public class AntColonyTSP {
    private final double[][] distances;
    private final int numCities;
    private double[][] pheromones;
    private final int numAnts;
    private final int maxIterations;
    private final double alpha;
    private final double beta;
    private final double evaporationRate;
    private final double q;
    private final Random random = new Random();

    public AntColonyTSP(double[][] distances, int numAnts, int maxIterations,
                       double alpha, double beta, double evaporationRate, double q) {
        this.distances = distances;
        this.numCities = distances.length;
        this.numAnts = numAnts;
        this.maxIterations = maxIterations;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;
        this.q = q;
        initializePheromones();
    }

    private void initializePheromones() {
        pheromones = new double[numCities][numCities];
        double initialPheromone = 1.0 / numCities;
        for (int i = 0; i < numCities; i++) {
            Arrays.fill(pheromones[i], initialPheromone);
        }
    }

    public List<Integer> solve() {
        List<Integer> bestTour = null;
        double bestLength = Double.MAX_VALUE;

        for (int iter = 0; iter < maxIterations; iter++) {
            List<List<Integer>> antTours = buildAntTours();
            updatePheromones(antTours);

            for (List<Integer> tour : antTours) {
                double tourLength = calculateTourLength(tour);
                if (tourLength < bestLength) {
                    bestLength = tourLength;
                    bestTour = new ArrayList<>(tour);
                }
            }
        }
        return bestTour;
    }

    private List<List<Integer>> buildAntTours() {
        List<List<Integer>> tours = new ArrayList<>();
        for (int ant = 0; ant < numAnts; ant++) {
            tours.add(buildSingleTour());
        }
        return tours;
    }

    private List<Integer> buildSingleTour() {
        List<Integer> tour = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        int startCity = random.nextInt(numCities);
        
        tour.add(startCity);
        visited.add(startCity);

        while (visited.size() < numCities) {
            int currentCity = tour.get(tour.size() - 1);
            int nextCity = selectNextCity(currentCity, visited);
            tour.add(nextCity);
            visited.add(nextCity);
        }
        return tour;
    }

    private int selectNextCity(int currentCity, Set<Integer> visited) {
        double[] probabilities = new double[numCities];
        double total = 0.0;

        for (int city = 0; city < numCities; city++) {
            if (!visited.contains(city)) {
                double pheromone = Math.pow(pheromones[currentCity][city], alpha);
                double heuristic = Math.pow(1.0 / distances[currentCity][city], beta);
                probabilities[city] = pheromone * heuristic;
                total += probabilities[city];
            }
        }

        double roulette = random.nextDouble() * total;
        double cumulative = 0.0;
        for (int city = 0; city < numCities; city++) {
            if (!visited.contains(city)) {
                cumulative += probabilities[city];
                if (cumulative >= roulette) {
                    return city;
                }
            }
        }
        return -1; // Should never happen
    }

    private void updatePheromones(List<List<Integer>> tours) {
        // Evaporation
        for (int i = 0; i < numCities; i++) {
            for (int j = 0; j < numCities; j++) {
                pheromones[i][j] *= (1.0 - evaporationRate);
            }
        }

        // Add new pheromones
        for (List<Integer> tour : tours) {
            double tourLength = calculateTourLength(tour);
            double deposit = q / tourLength;
            
            for (int i = 0; i < tour.size(); i++) {
                int from = tour.get(i);
                int to = tour.get((i + 1) % tour.size());
                pheromones[from][to] += deposit;
                pheromones[to][from] += deposit; // For symmetric TSP
            }
        }
    }

    private double calculateTourLength(List<Integer> tour) {
        return IntStream.range(0, tour.size())
                .mapToDouble(i -> distances[tour.get(i)][tour.get((i + 1) % tour.size())])
                .sum();
    }

    public static void main(String[] args) {
        // Пример матрицы расстояний для 4 городов
        double[][] distanceMatrix = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        AntColonyTSP aco = new AntColonyTSP(
                distanceMatrix,
                10,    // Количество муравьёв
                100,   // Итераций
                1.0,   // Alpha
                2.0,   // Beta
                0.5,   // Коэффициент испарения
                100.0  // Q
        );

        List<Integer> bestTour = aco.solve();
        double bestLength = aco.calculateTourLength(bestTour);

        System.out.println("Лучший маршрут: " + bestTour);
        System.out.printf("Длина маршрута: %.2f\n", bestLength);
    }
}
