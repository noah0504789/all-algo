import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long[] w;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        
        w = new long[Math.max(3, n+1)+1];
        w[1] = 1;
        w[2] = 1;
        w[3] = 2;
        for (int i = 4; i <= n+1; i++) w[i] = w[i-2] + w[i-1];
        
        System.out.print(w[n] * 2 + w[n+1] * 2);
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