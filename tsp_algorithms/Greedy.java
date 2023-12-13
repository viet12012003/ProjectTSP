package tsp_algorithms;

import java.util.Arrays;

public class Greedy {
    public static int[] optimalPath(double[][] distanceMatrix) {
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

    private static int[] generateInitialTour(int size) {
        int[] tour = new int[size + 1];
        for (int i = 0; i < size; i++) {
            tour[i] = i;
        }
        tour[size] = 0;
        return tour;
    }

    private static double calculateTourCost(int[] tour, double[][] distanceMatrix) {
        double cost = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            cost += distanceMatrix[tour[i]][tour[i + 1]];
        }
        // Thêm chi phí cho cạnh cuối cùng về đỉnh xuất phát
        cost += distanceMatrix[tour[tour.length - 1]][tour[0]];
        return cost;
    }

    private static boolean nextPermutation(int[] array) {
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

    public static void main(String[] args) {
        double[][] distanceMatrix = {
                {0, 5, 6, 9, 7},
                {3, 0, 10, 7, 3},
                {3, 6, 0, 2, 1},
                {2, 6, 1, 0, 3},
                {5, 2, 5, 8, 0}
        };

        int[] resultTour = optimalPath(distanceMatrix);

        System.out.println("Best Tour: " + Arrays.toString(resultTour));
    }
}
