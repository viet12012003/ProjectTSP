package tsp_algorithms;

import java.util.Arrays;

public class NearestNeighbor {
    public static int[] optimalPath(double[][] distanceMatrix) {
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

    private static int[] generateInitialTour(int size) {
        int[] tour = new int[size + 1];
        for (int i = 0; i < size; i++) {
            tour[i] = i;
        }
        tour[size] = 0;
        return tour;
    }

    private static int findNearestCity(int currentCity, boolean[] visited, double[][] distanceMatrix) {
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

    public static void main(String[] args) {
        double[][] distanceMatrix = {
                {0.0, 1576.0, 1137.0, 2975.0, 326.0, 712.0},
                {1254.0, 0.0, 1483.0, 3415.0, 1580.0, 2009.0},
                {1190.0, 1772.0, 0.0, 2536.0, 1512.0, 1223.0},
                {2743.0, 3325.0, 2068.0, 0.0, 2584.0, 2296.0},
                {326.0, 1901.0, 1461.0, 2667.0, 0.0, 404.0},
                {1873.0, 3028.0, 1881.0, 2948.0, 524.0, 0.0}
        };

        int[] resultTour = optimalPath(distanceMatrix);

        System.out.println("Best Tour: " + Arrays.toString(resultTour));
    }
}
