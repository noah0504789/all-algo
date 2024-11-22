import java.io.*;
import java.util.*;

public class Main {
    private static BufferedWriter bw;

    private static int[] up, down;
    private static int n, h, min, height, cnt, total;

    public static void main(String... args) throws IOException {
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = readInt();
        h = readInt();

        down = new int[h+1];
        up = new int[h+1];

        for (int i = 0; i < n; i++) {
            height = readInt();
            
            if (i % 2 == 0) down[height]++;
            else up[height]++;
        }
        
        for (int i = h-1; i >= 0; i--) {
            down[i] += down[i+1];
            up[i] += up[i+1];
        }
        
        min = Integer.MAX_VALUE;
        cnt = 0;
        
        for (int i = 1; i <= h; i++) {
            total = down[i] + up[h-i+1];
            if (min > total) {
                min = total;
                cnt = 1;
            } else if (min == total) {
                cnt++;
            }
        }

        bw.write(min + " " + cnt);
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
