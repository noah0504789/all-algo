import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static Map<Integer, Integer> acc;
    private static List<Integer> list;
    private static Boolean isOpen;
    private static long max, sum;
    private static int n, src, dest, start, end;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        acc = new HashMap<>();

        for (int i = 0; i < n; i++) {
            src = readInt();
            dest = readInt();

            acc.put(src, acc.getOrDefault(src, 0)+1);
            acc.put(dest, acc.getOrDefault(dest, 0)-1);
        }

        list = new ArrayList<>(acc.keySet());
        list.sort(Comparator.naturalOrder());

        sum = max = start = end = 0;
        isOpen = false;

        for (int key : list) {
            sum += acc.get(key);

            if (max < sum) {
                max = sum;
                start = key;
                isOpen = true;
            } else if (max > sum && isOpen) {
                end = key;
                isOpen = false;
            }
        }

        bw.write(max+"\n");
        bw.write(start+" "+end);

        bw.flush();
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();

        while (c <= ' ') c = System.in.read();
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return r;
    }
}
