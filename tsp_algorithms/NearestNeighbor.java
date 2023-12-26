package tsp_algorithms;

import java.util.Arrays;

public class NearestNeighbor {
    public int[] optimalPath(double[][] distanceMatrix) {
        int numCities = distanceMatrix.length;
        boolean[] visited = new boolean[numCities];
        int[] tour = generateInitialTour(numCities);
        visited[0] = true;

        for (int i = 1; i < numCities; i++) {
            int nearestCity = findNearestCity(tour[i - 1], visited, distanceMatrix);
            tour[i] = nearestCity;
            visited[nearestCity] = true;
        }

        return tour;
    }

    private int[] generateInitialTour(int size) {
        int[] tour = new int[size + 1];
        for (int i = 0; i < size; i++) {
            tour[i] = i;
        }
        tour[size] = 0;
        return tour;
    }

    private int findNearestCity(int currentCity, boolean[] visited, double[][] distanceMatrix) {
        int nearestCity = -1;
        double minDistance = Double.MAX_VALUE;

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i] && i != currentCity && distanceMatrix[currentCity][i] < minDistance) {
                nearestCity = i;
                minDistance = distanceMatrix[currentCity][i];
            }
        }

        return nearestCity;
    }

//    public static void main(String[] args) {
//        double[][] distanceMatrix = {
//                {0.0, 1500.0, 1100.0, 3000.0, 320.0, 710.0},
//                {1200.0, 0.0, 1500.0, 3400.0, 1500.0, 2000.0},
//                {1100.0, 1700.0, 0.0, 2500.0, 1500.0, 1200.0},
//                {2700.0, 3300.0, 2000.0, 0.0, 2500.0, 2200.0},
//                {320.0, 1900.0, 1400.0, 2600.0, 0.0, 400.0},
//                {1800.0, 3000.0, 1800.0, 2900.0, 500.0, 0.0}
//        };
//        int[] resultTour = optimalPath(distanceMatrix);
//        System.out.println("Best Tour: " + Arrays.toString(resultTour));
//    }
}
