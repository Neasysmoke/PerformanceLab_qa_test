
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String circleFile = "C:\\QA TEST\\task2\\circle.txt";
        String pointsFile = "C:\\QA TEST\\task2\\points.txt";

        List<String> circle = Files.readAllLines(Paths.get(circleFile));
        String[] circleCoords = circle.get(0).split(" ");
        double circleX = Double.parseDouble(circleCoords[0]);
        double circleY = Double.parseDouble(circleCoords[1]);
        double radius = Double.parseDouble(circle.get(1));

        List<String> points = Files.readAllLines(Paths.get(pointsFile));

        for (String pointLine : points) {
            String[] pointCoords = pointLine.split(" ");
            double x = Double.parseDouble(pointCoords[0]);
            double y = Double.parseDouble(pointCoords[1]);

            double distance = Math.sqrt(Math.pow(x - circleX, 2) + Math.pow(y - circleY, 2));

            if (distance == radius) {
                System.out.println(0);
            } else if (distance < radius) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }

    }
}