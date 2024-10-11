import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Укажите путь к файлу");
            return;
        }

        String filePath = args[0];
        List<Integer> nums = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    nums.add(scanner.nextInt());
                } else {
                    scanner.next();
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка" + e.getMessage());
            return;
        }

        if (nums.isEmpty()) {
            System.out.println("Файл не содержит чисел.");
            return;
        }

        Collections.sort(nums);

        int median;
        int size = nums.size();
        if (size % 2 == 1) {
            median = nums.get(size / 2);
        } else {
            median = nums.get(size / 2);
        }

        int minMoves = 0;
        for (int num : nums) {
            minMoves += Math.abs(num - median);
        }
        System.out.println(minMoves);
    }
}