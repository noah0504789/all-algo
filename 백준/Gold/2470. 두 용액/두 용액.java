import java.io.*;
import java.util.*;

public class Main {
    private static int[] values;
    private static int n, min, max, best = 2_000_100_000;

    public static void main(String... args) throws IOException {
        n = readInt();
        values = new int[n];

        for (int i = 0; i < n; i++) values[i] = readInt();

        Arrays.sort(values);

        int l = 0, r = n-1;
        while (l < r) {
            int abs = Math.abs(values[l] + values[r]);

            if (abs < best) {
                best = abs;
                min = values[l];
                max = values[r];
            }

            if (values[l] + values[r] > 0) r--;
            else l++;
        }

        System.out.print(min + " " + max);
    }

    private static int readInt() throws IOException {
        int result = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();

        boolean isNegative = false;

        while (c == '-') {
            isNegative = true;
            c = System.in.read();
        }

        while (c >= '0' && c <= '9') {
            result *= 10;
            result += c - '0';
            c = System.in.read();
        }

        if (isNegative) result *= -1;

        return result;
    }
}
