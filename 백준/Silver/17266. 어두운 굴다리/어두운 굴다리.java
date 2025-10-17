import java.io.*;
import java.util.*;

public class Main {
    
    private static int n, m;
    private static int[] lights;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        m = readInt();
        lights = new int[m];
        for (int i = 0; i < m; i++) lights[i] = readInt();
                        
        int max = 0;
        max = Math.max(max, lights[0]);
        for (int i = 1; i < m; i++) {
            int gap = lights[i] - lights[i-1];
            max = Math.max(max, (gap+1)/2);
        }
        
        max = Math.max(max, n-lights[m-1]);

        System.out.print(max);
    }

    public static int readInt() throws IOException {
        int r = 0, c = System.in.read();
        boolean negative = false;       

        while (c <= ' ') c = System.in.read();
        if (c == '-') {
            negative = true;
            c = System.in.read();
        }
        while (c >= '0' && c <= '9') {
            r *= 10;
            r += c - '0';
            c = System.in.read();
        }

        return negative ? -r : r;
    }
}