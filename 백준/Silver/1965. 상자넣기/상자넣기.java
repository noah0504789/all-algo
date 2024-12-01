import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static List<Integer> list;

    private static int[] boxes;
    private static int n;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        boxes = new int[n];
        for (int i = 0; i < n; i++) boxes[i] = readInt();

        list = new ArrayList<>();



        bw.write(lis()+"");
        bw.flush();
    }

    private static long lis() {
        list.clear();

        for (int box : boxes) {
            int pos = lowerbound(0, list.size(), box);

            if (pos < 0) pos = -(pos+1);
            if (pos >= list.size()) list.add(box);
            else list.set(pos, box);
        }

        return list.size();
    }

    private static int lowerbound(int l, int r, int key) {
        while (l < r) {
            int mid = (l+r)/2;
            if (list.get(mid) < key) l = mid+1;
            else r = mid;
        }
        
        return l;
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
