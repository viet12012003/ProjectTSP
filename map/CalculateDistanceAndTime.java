package map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CalculateDistanceAndTime {

    // Tạo một pool kết nối HTTP để sử dụng cho tất cả các yêu cầu
    HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public CalculateDistanceAndTime() {
    }

    public String calculateDistance(String address1, String address2) {
        String apiKey = "AhLWCVR0GXp_WtyyQMUJ0yub-3yrCfSlM3hvX98pe87fW1w7tUiWuJysdYjrRZm3";
        try {
            // Encode các địa chỉ để sử dụng trong URL
            String encodedAddress1 = URLEncoder.encode(address1, "UTF-8");
            String encodedAddress2 = URLEncoder.encode(address2, "UTF-8");

            // Xây dựng URL cho Geocoding API
            String apiUrl1 = "http://dev.virtualearth.net/REST/v1/Locations?q=" + encodedAddress1 + "&key=" + apiKey;
            String apiUrl2 = "http://dev.virtualearth.net/REST/v1/Locations?q=" + encodedAddress2 + "&key=" + apiKey;


            // Gửi yêu cầu HTTP và nhận phản hồi
            // Sử dụng pool kết nối trong việc gửi các yêu cầu
            HttpResponse<String> response1 = client.send(HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl1))
                    .build(), HttpResponse.BodyHandlers.ofString());
            HttpResponse<String> response2 = client.send(HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl2))
                    .build(), HttpResponse.BodyHandlers.ofString());

            // Trích xuất thông tin vị trí từ phản hồi JSON
            double[] location1 = extractLocation(response1.body());
            double[] location2 = extractLocation(response2.body());

            HttpRequest request1 = HttpRequest.newBuilder()
                    .uri(URI.create("https://trueway-matrix.p.rapidapi.com/CalculateDrivingMatrix?origins=" + location1[0] + "%2C" + location1[1] + "&destinations=" + location2[0] + "%2C" + location2[1]))
                    .header("X-RapidAPI-Key", "f99ff80a8amshbf4b50882c5ad4ep1061b4jsn8c3ae7fea36c")
                    .header("X-RapidAPI-Host", "trueway-matrix.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();
            HttpResponse<String> response3 = HttpClient.newHttpClient().send(request1, HttpResponse.BodyHandlers.ofString());
            return response3.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static double[] extractLocation(String json) {
        try {
            JsonObject jsonResponse = JsonParser.parseString(json).getAsJsonObject();
            JsonArray resourceSets = jsonResponse.getAsJsonArray("resourceSets");
            JsonObject resourceSet = resourceSets.get(0).getAsJsonObject();
            JsonArray resources = resourceSet.getAsJsonArray("resources");
            JsonObject location = resources.get(0).getAsJsonObject();
            JsonObject point = location.getAsJsonObject("point");

            double latitude = point.get("coordinates").getAsJsonArray().get(0).getAsDouble();
            double longitude = point.get("coordinates").getAsJsonArray().get(1).getAsDouble();

            return new double[]{latitude, longitude};

        } catch (Exception e) {
            e.printStackTrace();
            return new double[]{0.0, 0.0};
        }
    }

    public static void main(String[] args) {
        CalculateDistanceAndTime cal = new CalculateDistanceAndTime();
        System.out.println(cal.calculateDistance("7 phố Huỳnh Thúc Kháng, Láng Thượng, Đống Đa", "21 phố Thái Hà, Trung Liệt, Đống Đa"));
    }
}