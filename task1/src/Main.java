import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        int[] array = new int[n];
        for(int i = 0; i < n; i++) {
            array[i] = i + 1;
        }
        List<Integer> result = new ArrayList<>();
        int index = 0;
        result.add(array[index]);

        for (int i = 1; i < n; i++) {
            index = (index + m - 1) % n;
            result.add(array[index]);
        }

        for (int num : result) {
            System.out.print(num);
        }
        System.out.println();

    }
}