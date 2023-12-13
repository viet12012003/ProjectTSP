package tsp_algorithms;

import java.util.Arrays;

public class TwoOpt {
    private static int[] optimalPath(double[][] distanceMatrix) {
        int tourLength = distanceMatrix.length;
        int[] currentTour = generateInitialTour(tourLength);

        boolean improvement = true;
        while (improvement) {
            improvement = false;
            for (int i = 1; i < tourLength - 2; i++) {
                for (int j = i + 1; j < tourLength - 1; j++) {
                    double delta = calculateDelta(currentTour, i, j, distanceMatrix);

                    // Nếu delta âm, thực hiện đổi chỗ 2-opt
                    if (delta < 0) {
                        performTwoOptSwap(currentTour, i, j);
                        improvement = true;
                    }
                }
            }
        }

        return currentTour;
    }

    // Tính toán sự thay đổi trong khoảng cách bằng cách thực hiện đổi chỗ 2-opt
    private static double calculateDelta(int[] tour, int i, int j, double[][] distanceMatrix) {
        return distanceMatrix[tour[i - 1]][tour[j]] + distanceMatrix[tour[i]][tour[j + 1]]
                - distanceMatrix[tour[i - 1]][tour[i]] - distanceMatrix[tour[j]][tour[j + 1]];
    }

    // Thực hiện đổi chỗ 2-opt trong chuỗi hành trình
    private static void performTwoOptSwap(int[] tour, int i, int j) {
        while (i < j) {
            int temp = tour[i];
            tour[i] = tour[j];
            tour[j] = temp;
            i++;
            j--;
        }
    }

    // Tạo hành trình ban đầu
    private static int[] generateInitialTour(int size) {
        int[] tour = new int[size + 1];
        for (int i = 1; i < size; i++) {
            tour[i] = i;
        }
        tour[0] = tour[size] = 0;
        return tour;
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
