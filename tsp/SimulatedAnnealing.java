package tsp;

import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealing {
    private final double INITIAL_TEMPERATURE = 1000.0;
    private final double COOLING_RATE = 0.03;

    public int[] simulatedAnnealing(double[][] distanceMatrix) {
        int[] currentTour = generateRandomTour(distanceMatrix.length);
        int[] bestTour = Arrays.copyOf(currentTour, currentTour.length);

        double currentCost = calculateTourCost(currentTour, distanceMatrix);
        double bestCost = currentCost;

        double temperature = INITIAL_TEMPERATURE;

        while (temperature > 1.0) {
            int[] newTour = perturbTour(Arrays.copyOf(currentTour, currentTour.length));
            double newCost = calculateTourCost(newTour, distanceMatrix);

            if (acceptNewSolution(currentCost, newCost, temperature)) {
                currentTour = Arrays.copyOf(newTour, newTour.length);
                currentCost = newCost;
            }

            if (newCost < bestCost) {
                bestTour = Arrays.copyOf(newTour, newTour.length);
                bestCost = newCost;
            }

            temperature *= 1 - COOLING_RATE;
        }

        return bestTour;
    }

    private int[] generateRandomTour(int size) {
        int[] tour = new int[size + 1];
        for (int i = 1; i < size; i++) {
            tour[i] = i;
        }
        tour[0] = tour[size] = 0;
        return tour;
    }

    private int[] perturbTour(int[] tour) {
        int tourLength = tour.length;

        // Chọn ngẫu nhiên một đoạn trong hành trình
        int start = new Random().nextInt(tourLength - 3) + 1; // Không chọn điểm xuất phát
        int end = new Random().nextInt(tourLength - start - 2) + start + 1; // Không chọn điểm kết thúc

        // Đảo ngược đoạn được chọn
        while (start < end) {
            int temp = tour[start];
            tour[start] = tour[end];
            tour[end] = temp;
            start++;
            end--;
        }

        return tour;
    }

    private double calculateTourCost(int[] tour, double[][] distanceMatrix) {
        double cost = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            cost += distanceMatrix[tour[i]][tour[i + 1]];
        }
        // Thêm chi phí cho cạnh cuối cùng về đỉnh xuất phát
        cost += distanceMatrix[tour[tour.length - 1]][tour[0]];
        return cost;
    }

    private boolean acceptNewSolution(double currentCost, double newCost, double temperature) {
        if (newCost < currentCost) {
            return true;
        } else {
            double probability = Math.exp((currentCost - newCost) / temperature);
            return Math.random() < probability;
        }
    }

}
