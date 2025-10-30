import java.io.*;
import java.util.*;

public class Main {
    
    private static int n;
    private static long k;
    
    public static void main(String... args) throws IOException {
        n = readInt();
        k = readLong();
        
        hanoi(n, k, 1, 2, 3);
    }
    
    private static void hanoi(int n, long k, int s, int t, int e) {
        if (n == 1) {
            System.out.print(s + " " + e);
            return;
        }
        
        long m = 1L << (n-1);
        if (k < m) {
            hanoi(n-1, k, s, e, t);
        } else if (k == m) {
            System.out.print(s + " " + e);
        } else {
            hanoi(n-1, k-m, t, s, e);
        }
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