package tsp_algorithms;

import java.util.Arrays;

public class TwoOpt {
    public int[] optimalPath(double[][] distanceMatrix) {
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
    private double calculateDelta(int[] tour, int i, int j, double[][] distanceMatrix) {
        return distanceMatrix[tour[i - 1]][tour[j]] + distanceMatrix[tour[i]][tour[j + 1]]
                - distanceMatrix[tour[i - 1]][tour[i]] - distanceMatrix[tour[j]][tour[j + 1]];
    }

    // Thực hiện đổi chỗ 2-opt trong chuỗi hành trình
    private void performTwoOptSwap(int[] tour, int i, int j) {
        while (i < j) {
            int temp = tour[i];
            tour[i] = tour[j];
            tour[j] = temp;
            i++;
            j--;
        }
    }

    // Tạo hành trình ban đầu
    private int[] generateInitialTour(int size) {
        int[] tour = new int[size + 1];
        for (int i = 1; i < size; i++) {
            tour[i] = i;
        }
        tour[0] = tour[size] = 0;
        return tour;
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
