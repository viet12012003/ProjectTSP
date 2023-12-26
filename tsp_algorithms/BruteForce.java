package tsp_algorithms;

import java.util.Arrays;

public class BruteForce {
    public int[] optimalPath(double[][] distanceMatrix) {
        int numCities = distanceMatrix.length;
        int[] cities = generateInitialTour(numCities);

        int[] bestTour = null;
        double minCost = Double.MAX_VALUE;

        do {
            double currentCost = calculateTourCost(cities, distanceMatrix);
            if (currentCost < minCost) {
                minCost = currentCost;
                bestTour = Arrays.copyOf(cities, cities.length);
            }
        } while (nextPermutation(cities));

        return bestTour;
    }

    private int[] generateInitialTour(int size) {
        int[] tour = new int[size + 1];
        for (int i = 0; i < size; i++) {
            tour[i] = i;
        }
        tour[size] = 0;
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

    private boolean nextPermutation(int[] array) {
        int i = array.length - 2;
        while (i > 1 && array[i - 1] > array[i]) {
            i--;
        }
        if (i <= 1) {
            return false;
        }

        int j = array.length - 2;
        while (array[j] < array[i - 1]) {
            j--;
        }

        int temp = array[i - 1];
        array[i - 1] = array[j];
        array[j] = temp;

        j = array.length - 2;
        while (i < j) {
            temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }

        return true;
    }
//
//    public static void main(String[] args) {
//        double[][] distanceMatrix = {
//                {0.0, 1500.0, 1100.0, 3000.0, 320.0, 710.0},
//                {1200.0, 0.0, 1500.0, 3400.0, 1500.0, 2000.0},
//                {1100.0, 1700.0, 0.0, 2500.0, 1500.0, 1200.0},
//                {2700.0, 3300.0, 2000.0, 0.0, 2500.0, 2200.0},
//                {320.0, 1900.0, 1400.0, 2600.0, 0.0, 400.0},
//                {1800.0, 3000.0, 1800.0, 2900.0, 500.0, 0.0}
//        };
//
//        int[] resultTour = optimalPath(distanceMatrix);
//
//        System.out.println("Best Tour: " + Arrays.toString(resultTour));
//    }
}
