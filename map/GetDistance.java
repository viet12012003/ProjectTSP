package map;

import sender_information.Packages;

public class GetDistance {
    public static double getDistance(Packages packages1, Packages packages2) {
        CalculateDistanceAndTime cal = new CalculateDistanceAndTime(packages1.getAddress(), packages2.getAddress());
        String distance = cal.calculateDistance();
        return extractDistanceValue(distance);
    }

    private static double extractDistanceValue(String jsonString) {
        int startIndex = jsonString.indexOf("[[") + 2;
        int endIndex = jsonString.indexOf("]]", startIndex);
        String distanceString = jsonString.substring(startIndex, endIndex);
        return Double.parseDouble(distanceString);
    }
}
