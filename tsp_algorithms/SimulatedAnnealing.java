package tsp_algorithms;

import java.util.Arrays;
import java.util.Random;

public class SimulatedAnnealing {
    private final double INITIAL_TEMPERATURE = 1000.0; // Nhiệt độ ban đầu cho thuật toán Simulated Annealing
    private final double COOLING_RATE = 0.18; // Hệ số làm mát, quyết định tốc độ giảm nhiệt độ theo thời gian

    public int[] optimalPath(double[][] distanceMatrix) {
        // Khởi tạo hành trình ban đầu và gán nó là hành trình tốt nhất hiện tại
        int[] currentTour = generateInitialTour(distanceMatrix.length);
        int[] bestTour = Arrays.copyOf(currentTour, currentTour.length);

        // Tính chi phí của hành trình hiện tại và gán nó là chi phí tốt nhất hiện tại
        double currentCost = calculateTourCost(currentTour, distanceMatrix);
        double bestCost = currentCost;

        // Khởi tạo nhiệt độ ban đầu
        double temperature = INITIAL_TEMPERATURE;

        // Bắt đầu thuật toán Simulated Annealing
        while (temperature > 1) {

            // Tạo một hành trình mới bằng cách đảo ngược một phần của hành trình hiện tại
            int[] newTour = perturbTour(Arrays.copyOf(currentTour, currentTour.length));

            // Tính chi phí của hành trình mới
            double newCost = calculateTourCost(newTour, distanceMatrix);

            // Chấp nhận hành trình mới nếu nó tốt hơn hoặc theo xác suất nếu chi phí mới cao hơn
            if (acceptNewSolution(currentCost, newCost, temperature)) {
                currentTour = Arrays.copyOf(newTour, newTour.length);
                currentCost = newCost;
            }

            // Cập nhật hành trình tốt nhất nếu hành trình mới có chi phí thấp hơn
            if (newCost < bestCost) {
                bestTour = Arrays.copyOf(newTour, newTour.length);
                bestCost = newCost;
            }

            // Làm mát nhiệt độ
            temperature *= 1 - COOLING_RATE;
        }

        return bestTour;
    }

    // Tạo hành trình ban đầu
    private int[] generateInitialTour(int size) {
        int[] tour = new int[size + 1];
        for (int i = 0; i < size; i++) {
            tour[i] = i;
        }
        tour[size] = 0;
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
        // Tính chi phí của hành trình bằng cách cộng tổng các khoảng cách giữa các điểm
        double cost = 0;
        for (int i = 0; i < tour.length - 1; i++) {
            cost += distanceMatrix[tour[i]][tour[i + 1]];
        }
        // Thêm chi phí cho cạnh cuối cùng về đỉnh xuất phát
        cost += distanceMatrix[tour[tour.length - 1]][tour[0]];
        return cost;
    }

    // Chấp nhận hành trình mới nếu nó tốt hơn hoặc theo xác suất nếu chi phí mới cao hơn
    private boolean acceptNewSolution(double currentCost, double newCost, double temperature) {
        if (newCost <= currentCost) {
            return true;
        } else {
            double probability = Math.exp((currentCost - newCost) / temperature);
            return Math.random() < probability;
        }
    }

    public static void main(String[] args) {
        double[][] distanceMatrix = {
                {0.0, 1500.0, 1100.0, 3000.0, 320.0, 710.0},
                {1200.0, 0.0, 1500.0, 3400.0, 1500.0, 2000.0},
                {1100.0, 1700.0, 0.0, 2500.0, 1500.0, 1200.0},
                {2700.0, 3300.0, 2000.0, 0.0, 2500.0, 2200.0},
                {320.0, 1900.0, 1400.0, 2600.0, 0.0, 400.0},
                {1800.0, 3000.0, 1800.0, 2900.0, 500.0, 0.0}
        };
        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing();
        int[] result = simulatedAnnealing.optimalPath(distanceMatrix);
        // In ra ket qua
        System.out.println(Arrays.toString(result));
    }
}