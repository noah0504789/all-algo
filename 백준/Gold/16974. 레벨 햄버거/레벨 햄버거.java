import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long x;
    private static long[] size, pat;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        x = readLong();
        
        size = new long[51];
        pat = new long[51];
        size[0] = pat[0] = 1;
        
        for (int i = 1; i<= n; i++) {
            size[i] = 2*size[i-1]+3;
            pat[i] = 2*pat[i-1]+1;
        }
        
        System.out.print(eat(n, x));
    }
    
    private static long eat(int n, long x) {
        if (x <= 0) return 0;
        if (n == 0) return x >= 1 ? 1 : 0;
        
        long leftSize = size[n-1];
        
        if (x == 1) return 0;
        else if (x <= 1 + leftSize) return eat(n-1, x-1);
        else if (x == 1 + leftSize + 1) return pat[n-1] + 1;
        else if (x <= 1 + leftSize + 1 + leftSize) return pat[n-1] + 1 + eat(n-1, x-(1+leftSize+1));
        else return pat[n];
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
    
    public static long readLong() throws IOException {
        long r = 0, c = System.in.read();
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