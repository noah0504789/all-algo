import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static long sum;
    private static int[] dists;
    private static int n, src, dest, max, cur, min;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();

        dists = new int[n+1];
        
        sum = 0;
        for (int i = 0; i < n; i++) {
            dists[i] = readInt();
            sum += dists[i];
        }

        src = dest = max = 0;
        cur = dists[src];
        
        while (src <= dest && dest < n) {
            min = Integer.min(cur, (int) sum-cur);
            max = Integer.max(max, min);
            
            if (cur == min) cur += dists[++dest];
            else cur -= dists[src++];
        }

        bw.write(max+"");
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
