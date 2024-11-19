import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static List<Integer> list;
    private static int[] a, b;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        a = new int[n];
        for (int i = 0; i < n; i++) a[readInt()-1] = i;

        b = new int[n];
        for (int i = 0; i < n; i++) b[i] = a[readInt()-1];

        list = new ArrayList<>();

        bw.write(lis(b)+"");
        bw.flush();
    }

    public static int lis(int[] arr) {
        for (int num : arr) {
            int pos = Collections.binarySearch(list, num);
            if (pos < 0) pos = -(pos+1);

            if (pos >= list.size()) list.add(num);
            else list.set(pos, num);
        }

        return list.size();
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
